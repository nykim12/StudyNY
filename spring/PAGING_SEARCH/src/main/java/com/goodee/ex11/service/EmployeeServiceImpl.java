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

//	전체 사원 조회
	@Override
	public void getEmployees(HttpServletRequest request, Model model) {

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
		model.addAttribute("beginNo", totalRecord - pageUtils.getRecordPerPage() * (page - 1));
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/employee/list"));

	}

//	검색 결과 조회
	@Override
	public void findEmployees(HttpServletRequest request, Model model) {

		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// column, query, begin, end 파라미터 꺼내기
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		
		// column + query + begin + end => Map
		Map<String, Object> map = new HashMap<>();
		map.put("column", column);
		map.put("query", query);
		map.put("begin", begin);
		map.put("end", end);
		
		// 검색된 레코드 갯수 가져오기
		int findRecord = employeeMapper.selectFindCount(map);
		
		// findRecord와 page를 알면 PageEntity를 모두 계산할 수 있다.
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(findRecord, page);
		
		// beginRecord + endRecord => Map
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		
		// beginRecord ~ endRecord 사이 검색된 목록 가져오기
		List<Employee> employees = employeeMapper.selectFindList(map);
		
		// list.jsp로 forward할 때 가지고 갈 속성 저장하기
		model.addAttribute("employees", employees);
		model.addAttribute("beginNo", findRecord - pageUtils.getRecordPerPage() * (page - 1));
		
		// 검색 카테고리에 따라서 전달되는 파라미터가 다름
		switch(column) {
		case "EMPLOYEE_ID":
		case "FIRST_NAME":
			model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/employee/search?column=" + column + "&query=" + query));
			break;
		case "HIRE_DATE":
		case "SALARY":
			model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/employee/search?column=" + column + "&begin=" + begin + "&end=" + end));
			break;
		}
	}

	@Override
	public Map<String, Object> autoComplete(HttpServletRequest request) {

		String column = request.getParameter("column");
		String query = request.getParameter("query");

		Map<String, Object> map = new HashMap<>();
		map.put("column", column);
		map.put("query", query);

		List<Employee> list = employeeMapper.autoComplete(map);

		Map<String, Object> res = new HashMap<>();

		if (list.size() == 0) {
			res.put("status", 400);
			res.put("list", null);
		} else {
			res.put("status", 200);
			res.put("list", list);
		}
		
		if(column.equals("EMPLOYEE_ID")) {
			res.put("column", "employeeId");
		} else if(column.equals("FIRST_NAME")) {
			res.put(column, "firstName");
		}

		return res;

	}

}