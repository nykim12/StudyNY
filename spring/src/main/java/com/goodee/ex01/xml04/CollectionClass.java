package com.goodee.ex01.xml04;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionClass {

	private List<String> list;
	private Set<String> set;
	private Map<String, Object> map;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void info() {
		for (int i = 0, size = list.size(); i < size; i++) {
			System.out.println((i + 1) + "번째 요소 : " + list.get(i));
		}
		System.out.println();
		
		for (String str : set) {
			if (str != null) {
				System.out.println(str);
			}
		}
		System.out.println();
		
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}