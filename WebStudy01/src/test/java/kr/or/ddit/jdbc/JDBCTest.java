package kr.or.ddit.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import oracle.jdbc.pool.OracleDataSource;

/**
 * JDBC(Java DataBase Connectivity) 프로그래밍
 * 1. 벤더(제조사)의 드라이버를 검색하고 빌드패스에 추가(원래 jar파일을 직접 넣어야하는 maven 써서 pom.xml에 추가)
 * 	  드라이버? java.sql 패키지의 인터페이스들에 대한 구현제 집합
 * 2. 드라이버를 클래스를 메모리에 로딩 (버츄얼 머신한테 어떤 드라이버인지 알려줄려고)
 * 3. 드라이버를 통해 Connection 생성 (연결 통로 수립)
 * 4. 쿼리 객체 생성 : 쿼리를 컴파일하고, 실행하고, 명령을 전달하는 역할.
 * 		1) Statement			
 * 		2) PreparedStatement	위랑 똑같은데 분리함 -> 다름
 * 		3) CallableStatement
 * 5. 쿼리(SQL) 실행
 * 		DDL(데이터 정의어 / create(생성), alter(수정), drop(삭제) ), 
 * 		DML(데이터 조작어 / CRUD -> insert, select, update, delete),
 * 		TCL(트랜지션? 제어 / commit, rollback)
 * 		1) executeQuery메소드 (db에서 뭔가 조회할 때) : select  ->  ResultSet(=cursor, object X)이 반환됨
 * 		2) executeUpdate메소드 (db에 뭔가 반영할 때) : insert, update, delete 
 * 								성공이나 실패를 알려야하니 int로 반환(몇개가 삽입, 수정, 삭제... 됐는지 알려줌)
 *		
 *		DDL, TCL은 뭘 써도 상관없음  executeQuery메소드 사용하면 null
 *						   		executeUpdate메소드 사용하면 0
 * 
 * 6. 쿼리 결과(ResultSet) 핸들링 : while 문 형태로 커서의 포인터를 이동시켜 가며 접근함.
 *    							 (set은 인덱스가 없어서 for문사용 불가 -> 포인터 사용)
 * 7. 쿼리 객체 종료, Connection 종료 (닫는 순서 고려 -> try-with-resource 구문으로 해결)
 */

class JDBCTest {

	@Test
	void testCase2() {
		try {
			OracleDataSource dataSource = new OracleDataSource();
			
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "KNH99";
			String password = "java";
			dataSource.setURL(url);
			dataSource.setUser(user);
			dataSource.setUser(password);
			try (
				Connection conn = dataSource.getConnection();
				Statement stmt = conn.createStatement()
			) {
				System.out.println(conn);
				String sql = "SELECT NAME FROM V$CONTAINERS";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testCase1() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			String user = "KNH99";
			String password = "java";
			try (
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement()
			) {
				System.out.println(conn);
				String sql = "SELECT NAME FROM V$CONTAINERS";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String value = rs.getString("NAME");
					System.out.println(value);
				}
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
