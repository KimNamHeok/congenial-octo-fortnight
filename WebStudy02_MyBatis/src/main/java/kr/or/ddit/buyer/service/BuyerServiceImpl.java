package kr.or.ddit.buyer.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.mapper.BuyerMapper;
import kr.or.ddit.mapper.impl.BuyerMapperImpl;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements BuyerService {

	private BuyerMapper mapper = new BuyerMapperImpl();
	
	@Override
	public Optional<BuyerVO> readBuyer(String buyerId) {
		return Optional.ofNullable(mapper.selectBuyer(buyerId));
	}
	
	@Override
	public List<BuyerVO> readBuyerList() {
		return mapper.selectBuyerList();
	}

	@Override
	public void createBuyer(BuyerVO buyer) {
		
		mapper.insertBuyer(buyer);
	}
	
	@Override
	public void modifyBuyer(BuyerVO buyer) {
		mapper.updateBuyer(buyer);
		
	}
}













