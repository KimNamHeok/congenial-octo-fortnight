package kr.or.ddit.mapper.impl;

import java.util.List;

import kr.or.ddit.mapper.BuyerMapper;
import kr.or.ddit.mapper.util.MapperProxyTemplate;
import kr.or.ddit.vo.BuyerVO;

public class BuyerMapperImpl implements BuyerMapper {
	private MapperProxyTemplate<BuyerMapper> template =
			new MapperProxyTemplate<>(BuyerMapper.class);

	@Override
	public BuyerVO selectBuyer(String buyerId) {
		return template.execute((mp->mp.selectBuyer(buyerId)));
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		return template.execute((mp)->mp.selectBuyerList());
	}
	
	@Override
	public int insertBuyer(BuyerVO buyer) {
		return template.execute((mp)->mp.insertBuyer(buyer));
	}
	
	@Override
	public int updateBuyer(BuyerVO buyer) {
		
		return template.execute((mp)->mp.updateBuyer(buyer));
	}
}










