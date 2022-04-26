package repository;

import java.util.List;

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

	public Free selectFreeByNo(Long freeNo) {
		SqlSession ss = factory.openSession();
		Free free = ss.selectOne("mybatis.mapper.free.selectFreeByNo", freeNo);
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

	public int updateFree(Free free) {
		SqlSession ss = factory.openSession();
		int res = ss.update("mybatis.mapper.free.updateFree", free);
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
	
	public List<Free> getFreeRank() {
		SqlSession ss = factory.openSession();
		List<Free> list = ss.selectList("mybatis.mapper.free.getFreeRank");
		ss.close();
		return list;
	}

}