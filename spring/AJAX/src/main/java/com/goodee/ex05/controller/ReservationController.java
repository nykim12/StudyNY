package com.goodee.ex05.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex05.domain.ReservationDTO;
import com.goodee.ex05.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationservice;

//	produces는 ResponseEntity 내부에 포함 (application/json; charset=UTF-8)
//	@ResponseBody 작성하지 않아도 됨

	@GetMapping(value = "reservation/detail1")
	public ResponseEntity<ReservationDTO> detail1(HttpServletRequest request) {
		return reservationservice.detail1(request);
	}

	@GetMapping(value = "reservation/detail2")
	public ResponseEntity<ReservationDTO> detail2(@RequestParam long no) {
		return reservationservice.detail2(no);
	}
	
	@PostMapping(value = "reservation/detail3")
	public ResponseEntity<ReservationDTO> detail3(@RequestBody ReservationDTO reservation) {
		return reservationservice.detail3(reservation);
	}

	@GetMapping(value = "reservation/image")
	public ResponseEntity<byte[]> image() {
		return reservationservice.image();
	}

}