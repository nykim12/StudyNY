package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Emp;
import repository.EmpDAO;

public class ModifyService implements EmpService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		Long empNo = Long.parseLong(request.getParameter("empNo"));
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");

		Emp emp = new Emp();
		emp.setEmpNo(empNo);
		emp.setName(name);
		emp.setDept(dept);

		int res = EmpDAO.getInstance().updateEmp(emp);

		if (res > 0) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('사원 수정이 완료되었습니다.')");
				out.println("location.href='/DBCP/detail.do?empNo=" + empNo + "'");
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
				out.println("alert('사원 수정이 실패했습니다.')");
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