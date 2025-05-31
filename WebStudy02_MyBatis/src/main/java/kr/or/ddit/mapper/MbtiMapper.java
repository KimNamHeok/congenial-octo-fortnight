package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.MbtiVO;

/**
 * Persistence Layer : 테이블을 대상으로 CRUD 메소드(사용할 쿼리 반영)가 기본 요건.
 * 
 * ***Mybatis 에서는 Mapper 인터페이스와 Mapper xml 이 1:1 구조로 개발됨.
 *    프레임워크 내부에서 리플렉션을 하기 위해서는 해당 인터페이스를 찾기 위한 조건이 필요하고,
 *    그 조건을 xml 의 namespace 로 설정함.
 * *** 인터페이스의 메소드와 xml 내부의 쿼리 하나가 1:1 구조로 개발됨.
 * 		쿼리의 아이디를 기준으로 메소드와 1:1 구조가 형성됨.
 * 
 *    
 */
public interface MbtiMapper {
	public int insertMbti(MbtiVO mbti);
	/**
	 * 다건 조회, 데이터 매퍼는 다건조회에서 null값을 반환하지 않음. empty list 반환됨.
	 * @return
	 */
	public List<MbtiVO> selectMbtiList();
	/**
	 * 단건 조회
	 * @param mtType
	 * @return 검색 조건에 해당하는 데이터가 없는 경우, null 값이 반환됨.
	 */
	public MbtiVO selectMbti(String mtType);
	public int updateMbti(MbtiVO mbti);
	public int deleteMbti(String mtType);
}













