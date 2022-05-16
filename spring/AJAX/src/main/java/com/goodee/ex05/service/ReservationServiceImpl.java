package com.goodee.ex05.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.goodee.ex05.domain.ReservationDTO;

public class ReservationServiceImpl implements ReservationService {

/*
	▶ ResponseEntity<T>
		1.	실제 응답 데이터는 T 타입
		2.	HttpHeaders 클래스를 이용하여 응답 데이터의 content-Type 지정
			produces 사용하지 않음
		3.	응답 코드(HttpStatus)를 저장
*/
	
	@Override
	public ResponseEntity<ReservationDTO> detail1(HttpServletRequest request) {

		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		long no = Long.parseLong(optNo.orElse("0"));

		ResponseEntity<ReservationDTO> responseEntity = null;

//		ResponseEntity로 전달할 결과 데이터
		ReservationDTO reservation = new ReservationDTO(no, "예약자");

//		ResponseEntity로 전달할 응답 데이터의 Content-Type
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");

		if (no > 0) {
//			ajax의 success
			responseEntity = new ResponseEntity<ReservationDTO>(reservation, header, HttpStatus.OK);
		} else {
//			ajax의 error
			responseEntity = new ResponseEntity<ReservationDTO>(null, header, HttpStatus.NOT_FOUND);
		}

		return responseEntity;

	}

	@Override
	public ResponseEntity<ReservationDTO> detail2(long no) {

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<>(new ReservationDTO(no, "예약자"), header, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ReservationDTO> detail3(ReservationDTO reservation) {
		return null;
	}

}