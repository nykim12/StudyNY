package com.goodee.ex11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.ex11.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/employee/searchPage")
	public String searchPage() {
		return "employee/search";
	}

	@GetMapping("/employee/list")
	public String list(HttpServletRequest request, Model model) {
		employeeService.findEmployees(request, model);
		return "employee/search";
	}

}