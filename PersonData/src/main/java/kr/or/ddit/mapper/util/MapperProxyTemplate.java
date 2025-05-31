package kr.or.ddit.mapper.util;

import java.util.function.Function;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;

/**
 * Template Method Pattern : 명확하게 고정되어있는 단계에 따라 반복되는 작업 구조를 표현하는 방식.
 * Execute Around Pattern : 어떤 작업을 실행하기 위한 전/후 과정이 고정되어있고,
 * 							작업의 방식이 실행 시점에 달라지는 구조를 표현하는 방식.
 * @param <T>
 */
public class MapperProxyTemplate<T> {
	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	private final Class<T> mapperType;
	public MapperProxyTemplate(Class<T> mapperType) {
		super();
		this.mapperType = mapperType;
	}


	public <R> R execute(Function<T, R> lambda) {
//		1. 세션 생성
//		2. Mapper proxy 생성
//		3. 매번 다른 쿼리가 실행되어야 함.
//		4. 세션 종료
		try(
			SqlSession sqlSession = factory.openSession(true);	
		){
			T mapperProxy = sqlSession.getMapper(mapperType);
			return lambda.apply(mapperProxy);
		}
	}
}







