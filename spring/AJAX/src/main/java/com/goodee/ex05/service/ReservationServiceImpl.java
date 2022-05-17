package com.goodee.ex05.service;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

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

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		ResponseEntity<ReservationDTO> result = null;

		if(reservation.getNo() > 100) {
			result = new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);  // status : 500
		} else {
			result = new ResponseEntity<>(new ReservationDTO(reservation.getNo(), "예약자"), header, HttpStatus.OK);
		}

		return result;
	}

	@Override
	public ResponseEntity<byte[]> image() {
		File file = new File("C:\\Users\\GDJ45\\Desktop", "pika.png"); // new File("C:\\pika.png")
		ResponseEntity<byte[]> result = null;
		try {
//			C:\\pika.png 파일을 복사해서 byte[] 배열에 저장하고 해당 byte[] 배열 반환
			byte[] b = FileCopyUtils.copyToByteArray(file);
//			HttpHeaders : 반환할 데이터의 Content-Type
//			jpg 이미지의 Content-Type은 image/jpg
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "image/jpg");
//			반환할 ResponseEntity
			result = new ResponseEntity<>(b, header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}