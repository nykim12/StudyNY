package com.goodee.ex15.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtils {

//	XSS
	public static String xss(String str) {

		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("\'", "&#x27;");
		str = str.replaceAll("&", "&amp;");

		return str;

	}

//	인증 코드
	public static String authCode(int length) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (Math.random() < 0.5) {
				sb.append((char) ((int) (Math.random() * 10) + '0'));
			} else {
				sb.append((char) ((int) (Math.random() * 26) + 'A'));
			}
		}

		return sb.toString();

	}

//	java.security 패키지 이용한 암호화
//	SHA-256 : 입력된 값을 256비트(32 바이트) 암호화 처리
	public static String sha256(String password) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

//		md.digest()	: 입력된 비밀번호가 암호화되어있는 32 바이트 크기의 배열
		byte[] bytes = md.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02X", b)); // %X : 대문자로 표시한 16진수, 02 : 두 자리로 나타냄
		}
		return sb.toString();

	}

//	commons-codec 디펜던시를 이용한 암호화/복호화

//	암호화
	public static String encodeBase64(String str) {
		return new String(Base64.encodeBase64(str.getBytes()));
	}

//	복호화
	public static String decodeBase64(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}

}