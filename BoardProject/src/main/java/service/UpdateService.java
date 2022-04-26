package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class UpdateService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Long freeNo = Long.parseLong(request.getParameter("freeNo"));
		Long hit = Long.parseLong(request.getParameter("hit"));
		String ip = request.getParameter("ip");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Free free = new Free();
		free.setFreeNo(freeNo);
		free.setTitle(title);
		free.setWriter(writer);
		free.setContent(content);
		free.setHit(hit);
		free.setIp(ip);

		int res = FreeDAO.getInstance().updateFree(free);

		if (res > 0) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 수정 완료되었습니다.')");
				out.println("location.href='/BoardProject/detail.do?freeNo=" + freeNo + "'");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
