package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.ActionForward;
import repository.StudentDAO;

public class DeleteService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Optional<String> optNo = Optional.ofNullable(request.getParameter("stuNo"));
		Long no = Long.parseLong(optNo.orElse("0"));

		int res = StudentDAO.getInstance().deleteStudent(no);

		ActionForward af = null;

		if (res > 0) {
			af = new ActionForward("/BATCH/list.do", true);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학생 삭제가 실패되었습니다.')");
			out.println("history.back()");
			out.flush();
			out.close();
		}
		return af;
	}
}