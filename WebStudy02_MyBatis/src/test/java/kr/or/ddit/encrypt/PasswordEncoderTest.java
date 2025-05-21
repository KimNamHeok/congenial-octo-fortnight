package kr.or.ddit.encrypt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PasswordEncoderTest {
	PasswordEncoder pe = 
			PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Test
	void testAuthenticate() {
		String savedPassword = "{bcrypt}$2a$10$OfxLe5TDoO9m56SLe4KiHetDwnp7FmLdgqDs9ws733k5sRVGsf5cS";
		String inputPassword = "java";
		boolean authenticated = pe.matches(inputPassword, savedPassword);
		log.info("===> 인증결과 : {}", authenticated);
	}
	
	@Test
	void test() {
		String plain = "java";
		String encoded = pe.encode(plain);
		log.info("===>{}" + encoded);
	}

}
