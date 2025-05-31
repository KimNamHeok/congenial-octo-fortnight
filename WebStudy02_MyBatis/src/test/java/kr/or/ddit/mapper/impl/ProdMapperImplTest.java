package kr.or.ddit.mapper.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.mapper.ProdMapper;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdMapperImplTest {
	
	private ProdMapper mapper = new ProdMapperImpl();

	@Test
	void testInsertProd() {
//		ProdVO prod = new ProdVO();
//		prod.setProdId("P101000008");
//		prod.setLprodGu("P101");
//		prod.setBuyerId("P10101");
//		prod.setProdName("테스트용 상품");
		ProdVO prod = ProdVO.builder()
						.prodId("P101000009")
						.lprodGu("P101")
						.buyerId("P10101")
						.prodName("테스트 상품")
						.build();
		
		assertEquals(1, mapper.insertProd(prod));
	}
	
	@Test
	void testSelectProdListForMap() {
		mapper.selectProdListForMap()
				.forEach(map->log.info("===>{}", map.get("BUYER_ADD1")));
	}

	@Test
	void testSelectProdList() {
		List<ProdVO> list = mapper.selectProdList();
		list.forEach((prod)->{
			log.info("상품명 : {}, 분류명 : {}, 제조사명 : {}",
				prod.getProdName()
				, prod.getLprod().getLprodName()
				, prod.getBuyer().getBuyerName()
			);
		});
			
	}

	@Test
	void testSelectProd() {
		assertNotNull(mapper.selectProd("P101000001"));
//		assertNull(mapper.selectProd("asdfasdf"));
	}

	@Test
	void testUpdateProd() {
		fail("Not yet implemented");
	}

}
