package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Myservice {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);

}