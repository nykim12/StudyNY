package mybatis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.prod")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);

		ProductService service = null;

		ActionForward af = null;

		switch (command) {
		case "addPage.prod":
			af = new ActionForward("product2/add.jsp", false);
			break;
		case "list.prod":
			service = new ListService();
			break;
		case "add.prod":
			service = new AddService();
			break;
		case "detail.prod":
			service = new DetailService();
			break;
		case "remove.prod":
			service = new DeleteService();
			break;
		}

		if (service != null) {
			af = service.execute(request, response);
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