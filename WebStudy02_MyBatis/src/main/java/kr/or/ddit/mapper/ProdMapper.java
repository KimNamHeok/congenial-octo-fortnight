package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리(CRUD)를 위한 Persistence Layer
 */
public interface ProdMapper {
	/**
	 * 신규 상품 등록
	 * 상품 등록시 primary key 자동 생성 구조 필요함.
	 * @param prod
	 * @return
	 */
	public int insertProd(ProdVO prod);
	/**
	 * 상품 목록 조회, 차후에 페이징과 검색 지원 예정.
	 * @return
	 */
	public List<ProdVO> selectProdList();
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return 없다면, null 반환
	 */
	public ProdVO selectProd(String prodId);
	/**
	 * 상품 수정
	 * @param prod
	 * @return
	 */
	public int updateProd(ProdVO prod);
	/**
	 * 상품 삭제??????
	 * @param prodId
	 * @return
	 */
	public default int deleteProd(String prodId) {
//		return -1;
		throw new IllegalStateException("상품은 삭제 불가");
	};
}
















