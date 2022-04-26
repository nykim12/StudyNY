package service;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.ActionForward;
import repository.FreeDAO;

public class DetailService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Optional<String> optNo = Optional.ofNullable(request.getParameter("freeNo"));
		Long No = Long.parseLong(optNo.orElse("0"));

		request.setAttribute("free", FreeDAO.getInstance().selectFreeByNo(No));

		return new ActionForward("free/detail.jsp", false);
	}

}