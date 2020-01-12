package common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/*
 * sha512 알고리즘을 이용한 암호화
 * 
 * 1. 암호화
 * 2. 인코딩
 * 
 */
public class Utils {
	public static String getSha512(String password) {
		String encPwd = null;
		MessageDigest md = null;
		
		//1. 암호화
		try {
			//md5, sha-1, sha-256, sha-384, sha-512
			md = MessageDigest.getInstance("sha-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// 문자열 => 바이트배열로 변환
		byte[] bytes = null;
		try {
			bytes = password.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//md객체에 byte배열 전달
		md.update(bytes);
		
		//해싱 함수를 이용한 암호화
		byte[] encBytes = md.digest();
//		System.out.println(">>>암호화 처리 후 인코딩 전 : " + new String(encBytes));
		
		//2. 인코딩
		//base64 = 64개문자로 표현(대/소문자 52개 + 숫자 10개 + "+" + "/" + "패딩문자="))
		encPwd = Base64.getEncoder().encodeToString(encBytes);
		
		return encPwd;
	}
}
