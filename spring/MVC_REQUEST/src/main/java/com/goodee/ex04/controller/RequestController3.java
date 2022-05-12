package com.goodee.ex04.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex04.domain.Product;

@Controller
public class RequestController3 {

	@PostMapping("remove1")
	public String remove1(HttpServletRequest request, Model md) throws UnsupportedEncodingException {

		String model = request.getParameter("model");
		int price = Integer.parseInt(request.getParameter("price"));

		Product product = new Product(model, price);
		
		

		md.addAttribute("product", product);

		return "product";

	}

	@PostMapping("remove2")
	public String remove2(@RequestParam(value = "model", required = false) String model,
			@RequestParam(value = "price", required = false) int price, Model md) {

		md.addAttribute("product", new Product(model, price));

		return "product";
	}

	@PostMapping("remove3")
	public String remove3(Product product, Model md) {

		md.addAttribute("product", product);

		return "product";
	}

}