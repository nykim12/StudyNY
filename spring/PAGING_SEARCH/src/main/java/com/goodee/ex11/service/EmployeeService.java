package com.goodee.ex11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeService {

	public void findEmployees(HttpServletRequest request, Model model);

	

}