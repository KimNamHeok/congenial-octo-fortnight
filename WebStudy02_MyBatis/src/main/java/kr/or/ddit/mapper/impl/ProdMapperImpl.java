package kr.or.ddit.mapper.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mapper.ProdMapper;
import kr.or.ddit.mapper.util.MapperProxyTemplate;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdMapperImpl implements ProdMapper {
//	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	private MapperProxyTemplate<ProdMapper> template =
			new MapperProxyTemplate<>(ProdMapper.class);
	
	@Override
	public int insertProd(ProdVO prod) {
		return template.execute((mp)->mp.insertProd(prod));
	}

	@Override
	public List<ProdVO> selectProdList() {
//		1. 세션 개방
//		2. 쿼리 아이디로 쿼리 실행, 필요한 인자 전달
//		3. 세션 종료
		return template.execute((mp)->mp.selectProdList());
//		try(
//			SqlSession sqlSesion = factory.openSession();
//		){
//			ProdMapper proxy = sqlSesion.getMapper(ProdMapper.class);
//			return proxy.selectProdList();
////			return sqlSesion.selectList("kr.or.ddit.mapper.ProdMapper.selectProdList");
//		}
	}

	@Override
	public ProdVO selectProd(String prodId) {
		return template.execute((mp)->mp.selectProd(prodId));
//		1. 세션 개방
//		2. Mapper proxy 생성
//		3. proxy 가 가진 메소드 실행
//		4. 세션 종료		
//		try(
//			SqlSession sqlSesion = factory.openSession();
//		){
//			ProdMapper proxy = sqlSesion.getMapper(ProdMapper.class);
//			return proxy.selectProd(prodId);
//			
////			return sqlSesion.selectOne("kr.or.ddit.mapper.ProdMapper.selectProd");
//		}
	}

	@Override
	public int updateProd(ProdVO prod) {
//		개발자가 개발해야 하는 기능 구현이 미리 만들어져 있는 코드 집합의 형태
//		라이브러리 : ioc 가 없음.
//		프레임워크 : ioc(Inversion Of Control , 제어권이 역전됨.) 구조가 있음.
//		개발을 위해 필요한 객체가 코드 집합 내에서 자동 생성되는 구조를 ioc 라고 표현함.
//		ioc 를 지금은 DI(Dependency Injection) 라고 표현함.
		return template.execute((mp)->mp.updateProd(prod));
	}

}












