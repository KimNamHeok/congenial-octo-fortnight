package kr.or.ddit.member.dao;

import kr.or.ddit.member.vo.MemberVO;

public interface MemberDAO {
	/**
	 * 한사람의 회원 정보 조회
	 * @param username 조회할 대한 pk
	 * @return 조회한 사람의 정보를 가진 VO
	 * 			해당 회원이 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMember(String username);
}
