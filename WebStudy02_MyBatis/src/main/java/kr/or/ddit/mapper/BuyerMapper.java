
package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;

/**
 * 
 * 제조사 관리용 Persistence layer
 */
public interface BuyerMapper {
	public BuyerVO selectBuyer(String buyerId);
	public List<BuyerVO> selectBuyerList();
	public int insertBuyer(BuyerVO buyer);
	public int updateBuyer(BuyerVO buyer);
}
