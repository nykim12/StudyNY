package com.goodee.ex10.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//	@mapper를 사용하고 있음
@MapperScan(basePackages = { "com.goodee.ex10.mapper" } )

//	mybatis/properties/db.properties 파일의 내용을 참조
@PropertySource(value = { "classpath:mybatis/properties/db.properties" })
@EnableTransactionManagement

@Configuration
public class DBConfig {

//	mybatis/properties/db.properties 파일에 등록된 프로퍼티 값을 변수에 저장
//	프로퍼티들은 ${}로 처리

	@Value(value = "${hikariConfig.driverClassName}")
	private String driverClassName;
	@Value(value = "${hikariConfig.jdbcUrl}")
	private String jdbcUrl;
	@Value(value = "${hikariConfig.username}")
	private String username;
	@Value(value = "${hikariConfig.password}")
	private String password;

//	HikariCP 환경 설정
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		return hikariConfig;
	}

//	HikariCP Datasource
	@Bean(destroyMethod = "close")
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}

//	SqlSessionFactory
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(hikariDataSource());
		sqlSessionFactoryBean.setConfigLocation(
				new PathMatchingResourcePatternResolver().getResource("mybatis/config/mybatis-config.xml"));
		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mybatis/mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

//	SqlSessionTemplate : 지금까지 만든 bean은 모두 SqlSessionTemplate을 위해 존재
//	SqlSessionTemplate은 SqlSesion을 의미
//	모든 Repository에서 이 bean을 사용
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

//	트랜잭션 매니저
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(hikariDataSource());
	}

}