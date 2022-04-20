package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Emp;
import repository.EmpDAO;

public class DeleteService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		Long empNo = Long.parseLong(request.getParameter("empNo"));

		Emp emp = new Emp();
		emp.setEmpNo(empNo);

		int res = EmpDAO.getInstance().removeEmp(emp);

		if (res > 0) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('사원 삭제가 완료되었습니다.')");
				out.println("location.href='/DBCP/list.do'");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('사원 삭제가 실패했습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}
}