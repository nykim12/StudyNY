package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDAO;

public class DeleteService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(optNo.orElse("0"));
		
		response.setContentType("application/json; charset=UTF-8");
		
		JSONObject obj = new JSONObject();
		obj.put("res", MemberDAO.getInstance().deleteMember(no));		
		
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
		
	}

}