package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.MemberDTO;
import repository.MemberDAO;

public class ModifyService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		
		MemberDTO member = MemberDTO.builder()
				.id(id)
				.name(name)
				.gender(gender)
				.address(address)
				.build();
		
		response.setContentType("application; charset=UTF-8");
		
		JSONObject obj = new JSONObject();
		obj.put("res", MemberDAO.getInstance().modifyMember(member));
		
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();

	}

}