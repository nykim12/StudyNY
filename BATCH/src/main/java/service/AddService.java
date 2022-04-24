package service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.ActionForward;
import domain.Student;
import repository.StudentDAO;

public class AddService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String name = request.getParameter("name");
		Integer kor = Integer.parseInt(request.getParameter("kor"));
		Integer eng = Integer.parseInt(request.getParameter("eng"));
		Integer mat = Integer.parseInt(request.getParameter("mat"));
		double avg = (kor + eng + mat) / 3.0;
		String grade;

		if (avg >= 90) {
			grade = "A";
		} else if (avg >= 80) {
			grade = "B";
		} else if (avg >= 70) {
			grade = "C";
		} else if (avg >= 60) {
			grade = "D";
		} else {
			grade = "F";
		}

		Student student = new Student();
		student.setName(name);
		student.setKor(kor);
		student.setEng(eng);
		student.setMat(mat);
		student.setAvg(avg);
		student.setGrade(grade);

		int res = StudentDAO.getInstance().insertStudent(student);

		ActionForward af = null;

		if (res > 0) {
			af = new ActionForward("/BATCH/list.do", true);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학생 등록이 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}
		return af;
	}
}
