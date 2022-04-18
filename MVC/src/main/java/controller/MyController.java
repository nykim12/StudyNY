package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lotto;
import model.Now;
import model.Today;

//	suffix값이 .do 인 모든 URLMapping 을 처리하라
@WebServlet("*.do")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		요청
		request.setCharacterEncoding("UTF-8");

//		/MVC/today.to	/MVC/now.do	구분 방법
		String requestURI = request.getRequestURI(); // /MVC/today.to
		String contextPath = request.getContextPath(); // /MVC
		String command = requestURI.substring(contextPath.length() + 1); // /today.to

		switch (command) {
		case "today.do":
			Today today = new Today();
			today.execute(request, response); // request.setAttribute("result", today);
			break;
		case "now.do":
			Now now = new Now();
			now.execute(request, response); // request.setAttribute("result", now);
			break;
		case "lotto.do":
			Lotto lotto = new Lotto();
			lotto.execute(request, response);
			break;
		}

//		request를 응답 View로 전달(forward)한다.
		request.getRequestDispatcher("/views/output.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
