package com.goodee.ex15.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.ex15.domain.MemberDTO;
import com.goodee.ex15.domain.SignOutMemberDTO;
import com.goodee.ex15.mapper.MemberMapper;
import com.goodee.ex15.util.SecurityUtils;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public MemberDTO login(HttpServletRequest request) {

		String id = SecurityUtils.xss(request.getParameter("id"));
		String pw = SecurityUtils.sha256(request.getParameter("pw"));

		MemberDTO member = MemberDTO.builder()
				.id(id)
				.pw(pw)
				.build();

//		계정(ID, PASSWORD)이 일치하는 회원 조회
		MemberDTO loginMember = memberMapper.selectMemberByAccount(member);
		
//		로그인 기록 남기기
		if(loginMember != null) {
			memberMapper.insertMemberLog(id);
		}

		return loginMember;

	}

	@Transactional
	@Override
	public void reSignIn(HttpServletRequest request, HttpServletResponse response) {

		long memberNo = Long.parseLong(request.getParameter("memberNo"));
		String id = request.getParameter("id");
		String pw = SecurityUtils.sha256(request.getParameter("pw"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");		
		int agreeState = Integer.parseInt("agreeState");

		MemberDTO member = MemberDTO.builder()
				.memberNo(memberNo)
				.id(id)
				.pw(pw)
				.name(name)
				.email(email)
				.agreeState(agreeState)
				.build();
		
		int res1 = memberMapper.reInsertMember(member);
		int res2 = memberMapper.deleteSignOutMember(id);

		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res1 == 1 && res2 == 1) {
				out.println("<script>");
				out.println("alert('회원 가입이 완료되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('회원 가입에 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Map<String, Object> idCheck(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", memberMapper.selectMemberById(id));
		return map;
	}

	@Override
	public Map<String, Object> emailCheck(String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", memberMapper.selectMemberByEmail(email));
		return map;
	}

	@Override
	public Map<String, Object> sendAuthCode(String email) {

//		인증 코드 생성
		String authCode = SecurityUtils.authCode(6);

//		필수 속성
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");	// 구글 메일로 전송
		props.put("mail.smtp.port", "587");				// 구글 메일 전송하는 포트
		props.put("mail.smtp.auth", "true");			// 인증된 사용자
		props.put("mail.smtp.starttls.enable", "true");	// TLS 허용

//		메일 보내는 사용자 정보
		final String USERNAME = "nknk93975@gmail.com";
		final String PASSWORD = "lpqzkaggmbxrzfon";

//		사용자 정보 javax.mail.Session에 저장
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

//		이메일 전송하기
		try {
			Message message = new MimeMessage(session);
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			message.setFrom(new InternetAddress(USERNAME, "인증코드 관리자"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("인증 요청 메일입니다.");
			message.setText("인증번호는 " + authCode + "입니다.");
			Transport.send(message);
		} catch(Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("authCode", authCode);
		return map;
	}

	@Override
	public SignOutMemberDTO findSignOutMember(String id) {

		return memberMapper.selectSignOutMemberById(id);
	}
	
	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) {

		String id = SecurityUtils.xss(request.getParameter("id"));
		String pw = SecurityUtils.sha256(request.getParameter("pw"));	// SHA-256 암호화
		String name = SecurityUtils.xss(request.getParameter("name"));
		String email = SecurityUtils.xss(request.getParameter("email"));
		String location = request.getParameter("location");
		String promotion = request.getParameter("promotion");

		int agreeState = 1;	// 필수 동의
		if(location.isEmpty() == false && promotion.isEmpty() == false) {
			agreeState = 4;	// 필수 + 위치 + 프로모션 동의
		} else if(location.isEmpty() == false && promotion.isEmpty()) {
			agreeState = 2;	// 필수 + 위치 동의
		} else if(location.isEmpty() && promotion.equals("promotion")) {
//		=> } else if(location.isEmpty() && promotion.isEmpty() == false) {
			agreeState = 3;	// 필수 + 프로모션 동의
		}

		MemberDTO member = MemberDTO.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.email(email)
				.agreeState(agreeState)
				.build();

		int res = memberMapper.insertMember(member);

		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res == 1) {
				out.println("<script>");
				out.println("alert('회원 가입이 완료되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('회원 가입에 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void signOut(HttpServletRequest request, HttpServletResponse response) {

//		파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));
		long memberNo = Long.parseLong(opt.orElse("0"));

//		Member 테이블에서 member 삭제
		int res = memberMapper.deleteMember(memberNo);

		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res == 1) {
				out.println("<script>");
				out.println("alert('탈퇴 완료')");
				out.println("location.href='" + request.getContextPath() + "'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('탈퇴 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}