package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class RectangleService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String strWidth = request.getParameter("width");
		int width = Integer.parseInt(strWidth, 10);

		String strHeight = request.getParameter("height");
		int height = Integer.parseInt(strHeight, 10);

		int area = width * height;

		request.setAttribute("result", area);
		request.setAttribute("width", width);
		request.setAttribute("height", height);

		ActionForward af = new ActionForward();
		af.setView("views/rectangle.jsp");
		af.setRedirect(false);

		return af;

	}

}