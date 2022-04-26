package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class ListServive implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<Free> list = FreeDAO.getInstance().selectFreeList();
		
		request.setAttribute("list", list);
		return new ActionForward("free/list.jsp", false);
	}

}