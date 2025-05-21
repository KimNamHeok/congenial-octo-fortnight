package kr.or.ddit.encrypt;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 *	데이터의 표현을 바꾸는 방식
 *	Encoding(부호화) : 2-tier 이상이 되어 저장이나 전송을 목적으로 데이터의 표현을 바꾸는 작업
 *			   저장이나 전송의 대상이 되는 매체가 이해할 수 있는 방식으로 표현을 바꿈. 
 *			   가장 많이 사용되는 방식 2가지
 *				 1. 네트워크 전송용 URLEncoding
 *			     2. 미디어에서 사용할 수 있도록 2진 데이터를 byte 배열로 바꾸는 Base64 encoding
 *  Encrtypting(암호화) : 저장이나 전송을 목적으로 매체를 사용하는 경우,
 *  			의도하지 않은 노출이 발생하더라도 권한(key)이 없는 사용자의 위변조를 막기위해 표현을 바꾸는 작업.
 *  		단방향 암호화 : 복호화가 불가능한 방식. 해시 함수로 표현하기도 함.
 *  			security 패키지가 지원
 *  			copher 패키지가 지원
 *  			해시 함수? 원문 데이터를 겹치지않는 일정 길이의 코드로 만들어 내는 함수.
 *  			--> 비밀번호 암호화에 주로 사용됨. (그래서 사이트에서 비번 찾기가 아니라 바꾸게 함)
 *  			해시 함수에서는 해시코드 충돌을 방지하기 위해 코드 길이를 늘려야함.
 *  		양방향 암호화 : 복호화를 통해 원문 복원이 가능한 방식.
 *  			대칭키 방식 : 하나의 비밀키로 암/복호화가 모두 처리됨, 단점 : 키가 전송될 때 노출이 되는걸 막을 수 없음
 *  			--> 전송데이터나 저장데이터 암호화에 주로 사용.
 *  			비대칭키 방식 : 한쌍의 키(공개키, 개인키)로 암/복호화를 처리함. (한 쌍의 키가 서로 반대되는 구조)  -->전자서명에 사용
 *  				ex) 공개키로 암호화하면 개인키로 복호화
 *  				ex) 개인키로 암호화하면 공개키로 복호화
 *
 */
@Slf4j
class EncryptTest {
	@Test
	void testRSA() throws Exception{
		String plain = "암호화하기 전의 단문 데이터";
		byte[] input = plain.getBytes();
		Cipher cipher = Cipher.getInstance("RSA");
		KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
		pairGen.initialize(2048);
		KeyPair pair = pairGen.genKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		
		//전자서명용이면?
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		log.info("RSA 암호화 결과 : {}", encoded);
		
//		--> 암호문과 공개키 전송
		byte[] decoded = Base64.getDecoder().decode(encoded);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] decrypted = cipher.doFinal(decoded);
		String afterPlain = new String(decrypted);
		log.info("RSA 복호화 결과 : {}", afterPlain);
	}
	
	@Test
	void testAES() throws Exception{
		String plain = "암호화하기 전의 장문 데이터";
		byte[] input = plain.getBytes();
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey key = keyGen.generateKey();
		
		// 첫번째 블럭의 역할을 할 초기 백터 생성(128bit)
		byte[] iv = new byte[128/8];
		new SecureRandom().nextBytes(iv);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		log.info("AES 암호화 결과 : {}", encoded);
		
//		--> 반대쪽에 암호문과 비밀키와 초기벡터를 함께 전송해야함.
		
//		원문데이터 복원
		byte[] decoded = Base64.getDecoder().decode(encoded);
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		byte[] decrypted = cipher.doFinal(decoded);
		String afterPlain = new String(decrypted);
		log.info("AES 복호화 결과 : {}", afterPlain);
	}
	
	
	@Test
	void testHash() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		String plain = "java";
		byte[] input = plain.getBytes();
		byte[] encrypted = md.digest(input);
		log.info("해시 길이 : {}", encrypted.length * 8);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		log.info("단방향 암호화 결과 : {}", encoded);
	}
	
	@Test
	void testEncoding() throws Exception{
		String original = "인코딩전 원문";
		String encoded = URLEncoder.encode(original, "UTF-8");
		log.info("encoded : {}", encoded);
	}

	@Test
	void testDecoding() throws Exception{
		String encoded = "%EC%9D%B8%EC%BD%94%EB%94%A9%EC%A0%84+%EC%9B%90%EB%AC%B8";
		String decode = URLDecoder.decode(encoded, "UTF-8");
		log.info("decode : {}", decode);
	}
}
