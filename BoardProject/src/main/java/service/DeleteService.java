package service;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.FreeDAO;

public class DeleteService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Optional<String> freeNo = Optional.ofNullable(request.getParameter("freeNo"));
		Long no = Long.parseLong(freeNo.orElse("0"));

		int res = FreeDAO.getInstance().deleteFree(no);

		ActionForward af = null;

		if (res > 0) {
			af = new ActionForward("BoardProject/list.do", true);
		}

		return af;
	}
}