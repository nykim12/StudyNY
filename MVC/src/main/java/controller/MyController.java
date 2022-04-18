package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lotto;
import model.Myservice;
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

//		모든 model은 MyService 인터페이스를 구현하는 클래스이므로, MyService 타입의 인스턴스이다.
		Myservice service = null;

		switch (command) {
		case "today.do":
			service = new Today();
			break;
		case "now.do":
			service = new Now();
			break;
		case "lotto.do":
			service = new Lotto();
			break;
		}

//		model의 실행(execute() 메소드의 호출)
		if (service != null) {
			service.execute(request, response);
		}

//		request를 응답 View로 전달(forward)한다.
		request.getRequestDispatcher("/views/output.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}