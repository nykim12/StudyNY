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
import service.FreeService;
import service.ListServive;

@WebServlet("*.do")
public class FreeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text-html; charset=UTF-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);

		FreeService service = null;

		ActionForward af = null;

		switch (command) {
		case "list.do":
			service = new ListServive();
			break;
		case "insertPage.do":
			af = new ActionForward("free/insert.jsp", false);
			break;
		case "insert.do":
			service = new AddService();
			break;
		case "remove.do":
			service = new DeleteService();
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