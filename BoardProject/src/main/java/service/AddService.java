package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class AddService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Free free = new Free();
		free.setWriter(writer);
		free.setTitle(title);
		free.setContent(content);

		int res = FreeDAO.getInstance().insertFree(free);

		ActionForward af = null;

		if (res > 0) {
			af = new ActionForward("/BoardProject/list.do", true);
		}

		return af;
	}

}
