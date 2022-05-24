package com.goodee.ex11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.goodee.ex11.domain.Employee;
import com.goodee.ex11.mapper.EmployeeMapper;
import com.goodee.ex11.util.PageUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public void findEmployees(HttpServletRequest request, Model model) {

		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));

		int totalRecord = employeeMapper.selectEmployeeCount();

		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);

		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());

		List<Employee> employees = employeeMapper.selectEmployees(map);

		model.addAttribute("employees", employees);

	}

}