package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Now {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String now = new SimpleDateFormat("a h:mm:ss").format(new Date());
		System.out.println(now);
	}

}