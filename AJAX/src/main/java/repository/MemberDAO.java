package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.MemberDTO;

public class MemberDAO {

	private SqlSessionFactory factory;
	private static MemberDAO instance = new MemberDAO();

	private MemberDAO() {
		try {
			String resource = "mybatis/config/Mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	public List<MemberDTO> selectMemberList() {
		SqlSession ss = factory.openSession();
		List<MemberDTO> member = ss.selectList("mybatis.mapper.member.selectMemberList");
		ss.close();
		return member;
	}
	
	public int selectgetMemberCount() {
		SqlSession ss = factory.openSession();
		int res = ss.selectOne("mybatis.mapper.member.selectgetMemberCount");
		ss.close();
		return res;
	}
	
	public MemberDTO selectMemberListByNo(Long no) {
		SqlSession ss = factory.openSession();
		MemberDTO member = ss.selectOne("mybatis.mapper.member.selectMemberListByNo");
		ss.close();
		return member;
	}

	public int insertMember(MemberDTO member) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert("mybatis.mapper.member.insertMember", member);
		if (res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	
}