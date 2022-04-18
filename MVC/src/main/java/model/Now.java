package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Now implements Myservice {

//	메소드
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

//		응답
		String now = new SimpleDateFormat("a h:mm:ss").format(new Date());

//		응답 결과는 request에 속성(attribute)으로 저장한다.
		request.setAttribute("result", now);
	}
	

}