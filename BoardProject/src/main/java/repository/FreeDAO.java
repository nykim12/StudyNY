package repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Free;
import mybatis.config.MybatisConfig;

public class FreeDAO {

	private SqlSessionFactory factory;

	private static FreeDAO freeDAO = new FreeDAO();

	private FreeDAO() {
		factory = MybatisConfig.getInstance().getSqlSessionFactory();
	}

	public static FreeDAO getInstance() {
		return freeDAO;
	}

	public List<Free> selectFreeList() {
		SqlSession ss = factory.openSession();
		List<Free> student = ss.selectList("mybatis.mapper.free.selectFreeList");
		ss.close();
		return student;
	}

	public Free selectFreeByTitle(String title) {
		SqlSession ss = factory.openSession();
		Free free = ss.selectOne("mybatis.mapper.free.selectFreeByTitle", title);
		ss.close();
		return free;
	}

	public int insertFree(Free free) {
		SqlSession ss = factory.openSession();
		int res = ss.insert("mybatis.mapper.free.insertFree", free);
		if (res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}

	public int updateFree(Map<String, String> map) {
		SqlSession ss = factory.openSession();
		int res = ss.update("mybatis.mapper.free.updateFree", map);
		if (res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}

	public int deleteFree(Long freeNo) {
		SqlSession ss = factory.openSession();
		int res = ss.delete("mybatis.mapper.free.deleteFree", freeNo);
		if (res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}

}