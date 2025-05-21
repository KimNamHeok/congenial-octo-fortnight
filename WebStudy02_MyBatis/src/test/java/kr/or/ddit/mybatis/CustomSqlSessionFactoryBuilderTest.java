package kr.or.ddit.mybatis;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

class CustomSqlSessionFactoryBuilderTest {

	@Test
	void testGetSqlSessionFactory() {
		SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
		try (SqlSession sqlSession = factory.openSession()) {
			System.out.println(sqlSession);
		}
	}

}
