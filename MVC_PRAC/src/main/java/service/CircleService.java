package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class CircleService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String strRadius = request.getParameter("radius");

		double pi = 3.14;
		double radius = Double.parseDouble(strRadius);

		double area = radius * radius * pi;

		request.setAttribute("radius", radius);
		request.setAttribute("result", area);

		ActionForward af = new ActionForward();
		af.setView("views/circle.jsp");
		af.setRedirect(false);

		return af;

	}

}