package com.goodee.ex05.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goodee.ex05.domain.ProductDTO;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<ProductDTO> list1() {

		ProductDTO product1 = new ProductDTO(1, "빼빼로", "롯데", 1500);
		ProductDTO product2 = new ProductDTO(2, "바나나킥", "농심", 1500);
		ProductDTO product3 = new ProductDTO(3, "죠스바", "롯데", 1000);
		List<ProductDTO> list = Arrays.asList(product1, product2, product3);
		return list;

	}

	@Override
	public List<Map<String, Object>> list2() {

		Map<String, Object> product1 = new HashMap<String, Object>();
		product1.put("no", 1);
		product1.put("name", "빼빼로");
		product1.put("maker", "롯데");
		product1.put("price", 1500);

		Map<String, Object> product2 = new HashMap<String, Object>();
		product2.put("no", 2);
		product2.put("name", "바나나킥");
		product2.put("maker", "농심");
		product2.put("price", 1500);

		Map<String, Object> product3 = new HashMap<String, Object>();
		product3.put("no", 3);
		product3.put("name", "죠스바");
		product3.put("maker", "롯데");
		product3.put("price", 1000);

		return Arrays.asList(product1, product2, product3);
	}

	@Override
	public Map<String, Object> list3() {

		ProductDTO product1 = new ProductDTO(1, "빼빼로", "롯데", 1500);
		ProductDTO product2 = new ProductDTO(2, "바나나킥", "농심", 1500);
		ProductDTO product3 = new ProductDTO(3, "죠스바", "롯데", 1000);
		List<ProductDTO> products = Arrays.asList(product1, product2, product3);
		Map<String, Object> list = new HashMap<String, Object>();

//		list.put("products", Arrays.asList(product1, product2, product3));
		list.put("products", products);
		return list;

	}

}