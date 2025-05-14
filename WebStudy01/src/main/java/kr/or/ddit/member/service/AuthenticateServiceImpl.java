package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * Layered Architecture : 하나의 명령이 처리되는 동안 순차적으로 객체들이 동작하는 구조
 * 		계층을 구성하고 있는 객체들간의 (의존)관계를 기반으로 한 설계 구조
 */
public class AuthenticateServiceImpl implements AuthenticateService {
	private MemberDAO dao = new MemberDaoImpl();
	
	@Override
	public boolean authenticate(MemberVO inputData) {
		MemberVO saved = dao.selectMember(inputData.getMemId());
		if(saved!=null) {
			String inputPass = inputData.getMemPassword();
			String savedPass = saved.getMemPassword();
			return savedPass.equals(inputPass);
		}else {
			return false;
		}
	}

}
