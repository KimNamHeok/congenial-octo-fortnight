package kr.or.ddit.mapper.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberMapperImpl implements MemberMapper {
   SqlSessionFactory factory = 
		   CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
   
	@Override
	public MemberVO selectMember(String username) {
		try (SqlSession sqlSession = factory.openSession()) {
			return sqlSession.selectOne("kr.or.ddit.mapper.MemberMapper.selectMember", username);
		}
	}

}











