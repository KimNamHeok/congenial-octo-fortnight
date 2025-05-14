package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.vo.MemberVO;

class AuthenticateServiceImplTest {
	AuthenticateService service = new AuthenticateServiceImpl();
	
	@Test
	void testAuthenticate() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		inputData.setMemPassword("asdfasdf");
		boolean result = service.authenticate(inputData);
		assertTrue(result);
	}

}
