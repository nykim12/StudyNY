package com.goodee.ex11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	private int departmentId;
	private String departmentName;
	private int managerId;
	private int locationId;

}