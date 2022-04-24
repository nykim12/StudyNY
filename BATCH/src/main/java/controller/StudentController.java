package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.ActionForward;
import service.AddService;
import service.DeleteService;
import service.DetailService;
import service.ListService;
import service.ModifyService;
import service.SearchService;
import service.StudentService;

@WebServlet("*.do")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

//		URLMapping
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);

//		StudentService 인스턴스
		StudentService service = null;

//		ActionForward 인스턴스
		ActionForward af = null;

		switch (command) {
		case "list.do":
			service = new ListService();
			break;
		case "insertPage.do":
			af = new ActionForward("student/insert.jsp", false);
			break;
		case "insert.do":
			service = new AddService();
			break;
		case "detail.do":
			service = new DetailService();
			break;
		case "update.do":
			service = new ModifyService();
			break;
		case "remove.do":
			service = new DeleteService();
			break;
		case "search.do":
			service = new SearchService();
			break;
		}

		try {
			if (service != null) {
				af = service.execute(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (af != null) {
			if (af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
