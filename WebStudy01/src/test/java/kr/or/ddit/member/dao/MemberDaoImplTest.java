package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.vo.MemberVO;

class MemberDaoImplTest {
	MemberDAO dao = new MemberDaoImpl();
	@Test
	void testNotExist() {
		String username = "asdasdf' OR '1'='1";
		MemberVO member = dao.selectMember(username);
		System.out.println(member);
		assertNull(member);
//		Optional.ofNullable(dao.selectMember(username))
//			.ifPresent(System.out::print);
	}
	@Test
	void testExist() {
		String username = "a001";
		MemberVO member = dao.selectMember(username);
		assertNotNull(member);
//		Optional.ofNullable(dao.selectMember(username))
//			.ifPresent(System.out::print);
	}
}
