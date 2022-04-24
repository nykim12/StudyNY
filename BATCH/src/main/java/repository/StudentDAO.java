package repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import domain.Student;
import mybatis.config.MybatisConfig;

public class StudentDAO {

//	SqlSessionFactory 인스턴스를 필드로 생성해두고,
//	모든 메소드는 factory로부터 SqlSession ss 인스턴스를 받아 사용한 후 닫는다.

//	필드
	private SqlSessionFactory factory;

//	singleton
	private static StudentDAO StudentDAO = new StudentDAO();

	private StudentDAO() {
		factory = MybatisConfig.getInstance().getSqlSessionFactory();
	}

	public static StudentDAO getInstance() {
		return StudentDAO;
	}

//	1.	전체 학생 조회
	public List<Student> selectStudentList() {
		SqlSession ss = factory.openSession();
		List<Student> list = ss.selectList("mybatis.mapper.student.selectStudentList");
		ss.close();
		return list;
	}

	public int getTotalCount() {
		SqlSession ss = factory.openSession();
		int getTotalCount = ss.selectOne("mybatis.mapper.student.getTotalCount");
		ss.close();
		return getTotalCount;
	}

	public Double getTotalAverage() {
		SqlSession ss = factory.openSession();
		Double getTotalAverage = ss.selectOne("mybatis.mapper.student.getTotalAverage");
		ss.close();
		return getTotalAverage;
	}

//	2.	학생 상세 조회
	public Student selectStuByNo(Long stuNo) {
		SqlSession ss = factory.openSession();
		Student student = ss.selectOne("mybatis.mapper.student.selectStuByNo", stuNo);
		ss.close();
		return student;
	}

//	3.	학생 삽입
	public int insertStudent(Student student) {
		SqlSession ss = factory.openSession();
		int res = ss.insert("mybatis.mapper.student.insertStudent", student);
		if (res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}

//	4.	학생 수정
	public int updateStudent(Map<String, String> map) {
		SqlSession ss = factory.openSession();
		int res = ss.update("mybatis.mapper.student.updateStudent", map);
		if (res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}

//	5.	학생 삭제
	public int deleteStudent(Long stuNo) {
		SqlSession ss = factory.openSession();
		int res = ss.delete("mybatis.mapper.student.deleteStudent", stuNo);
		if (res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}

	public List<Student> searchStudentList(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		List<Student> list = ss.selectList("mybatis.mapper.student.selectStudentByAvg", map);
		ss.close();
		return list;
	}

	public int searchCount(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		int searchCount = ss.selectOne("mybatis.mapper.student.getSearchCount", map);
		ss.close();
		return searchCount;
	}

	public double searchAverage(Map<String, Integer> map) {
		SqlSession ss = factory.openSession();
		double searchAverage = ss.selectOne("mybatis.mapper.student.getSearchAverage", map);
		ss.close();
		return searchAverage;
	}
}