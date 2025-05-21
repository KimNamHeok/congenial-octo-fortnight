package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mapper.impl.MbtiMapperImpl;
import kr.or.ddit.vo.MbtiVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
class MbtiMapperTest {
//	Logger log = LoggerFactory.getLogger(MbtiMapperTest.class);
	
	MbtiMapper mapper = new MbtiMapperImpl();

	@Test
	void testInsertMbti() {
		MbtiVO newMbti = new MbtiVO("nnnn");
		newMbti.setMtTitle("AAAA 타입 요약");
		newMbti.setMtContent("AAAA 타입 상세");
		int cnt = mapper.insertMbti(newMbti);
		assertEquals(1, cnt);
	}

	@Test
	void testSelectMbtiList() {
		List<MbtiVO> list = mapper.selectMbtiList();
		if(log.isTraceEnabled()) {
			log.trace("기록될까?");
		}else {
//			log.info("기록되겠지? {}", list);
//			list.forEach((mt)->log.info("===>{}", mt));
		}
	}

	@Test
	void testSelectMbti() {
		assertNull( mapper.selectMbti("aaaa") );
	}

	@Test
	void testUpdateMbti() {
//		put / patch
		MbtiVO mbti = mapper.selectMbti("aaaa");
		mbti.setMtTitle("AAAA 타입 수정");
		assertEquals(1, mapper.updateMbti(mbti));
	}

	@Test
	void testDeleteMbti() {
		assertEquals(1, mapper.deleteMbti("aaaa"));
	}

}








