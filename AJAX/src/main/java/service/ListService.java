package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.MemberDTO;
import repository.MemberDAO;

public class ListService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

//		응답 데이터 형식(JSON)
		response.setContentType("application/json; charset=UTF-8");

//		응답 데이터 예시
//		
//		{
//		  "count":7,
//		  "members": [
//			{
//				"no": 1,
//				"id": "user1",
//				"name": "사용자1",
//				"gender": "남",
//				"address": "서울시 금천구 가산동"
//			},
//			...
//		  ]
//		}
		
		JSONObject obj = new JSONObject();
		int count = MemberDAO.getInstance().selectgetMemberCount();
		List<MemberDTO> members = MemberDAO.getInstance().selectMemberList();
		
		obj.put("count", count);
		obj.put("members", members);

		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();

	}

}