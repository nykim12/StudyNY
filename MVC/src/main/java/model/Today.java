package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Today {
	
//	메소드
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		System.out.println(today);

	}

}