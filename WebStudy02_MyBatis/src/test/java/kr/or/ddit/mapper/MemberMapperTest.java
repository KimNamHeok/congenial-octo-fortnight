package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.mapper.impl.MemberMapperImpl;
import kr.or.ddit.vo.MemberVO;

class MemberMapperTest {
	MemberMapper mapper = new MemberMapperImpl();

	@Test
	void testSelectMember() {
		MemberVO member = mapper.selectMember("a001");
		System.out.println(member);
		assertNotNull(member);
	}

}
