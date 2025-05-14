package kr.or.ddit.db;






import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Factory Method Pattern -> Factory Object Pattern 으로 단순화 시킬 수 있음.
 * 의존 객체를 직접 생성하지 않고, 객체 생성을 전담하는 factory(Simple Factory) 객체를 별도로 운영하는 구조.
 * 
 * A a = new A();
 * A b = new A();
 * 1. A 라는 클래스를 메모리에 로딩함.
 * 2. 메모리에 로딩된 클래스를 참조하여 객체를 생성함.
 * 3. a 변수의 공간을 형셩하고, 2번 객체의 레퍼런스를 a 변수에 할당함.
 * 
 */
public class ConnectionFactory {
	private static HikariDataSource ds;

	static {
		ResourceBundle dbInfo = ResourceBundle.getBundle("kr.or.ddit.db.DBInfo");
		String driverClassName = dbInfo.getString("driverClassName");
		String url = dbInfo.getString("url");
		String username = dbInfo.getString("username");
		String password = dbInfo.getString("password");
		boolean autoCommit = Optional.of(dbInfo.getString("autoCommit"))
				.map(Boolean::parseBoolean)
				.orElseThrow();
		int minimumIdle = Optional.of(dbInfo.getString("minimumIdle"))
				.map(Integer::parseInt)
				.orElseThrow();
		int maximumPoolSize = Optional.of(dbInfo.getString("maximumPoolSize"))
				.map(Integer::parseInt)
				.orElseThrow();
		long connectionTimeout = Optional.of(dbInfo.getString("connectionTimeout"))
				.map(Long::parseLong)
				.orElseThrow();
		ds = new HikariDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setAutoCommit(autoCommit);
		ds.setMinimumIdle(minimumIdle);
		ds.setMaximumPoolSize(maximumPoolSize);
		ds.setConnectionTimeout(connectionTimeout);
	}
	
	/*
	 * 예외 처리 방식
	 * 1. 예외 회피 : throws 로 호출자에게 예외 처리를 떠넘기는 구조.
	 * 2. 예외 복구 : 일정 주기에 따라 반복적으로 재실행해보는 구조.
	 * 3. 예외 전환 : 발생한 예외를 wrapping 하고 있는 다른 예외로 변경하여 throw 하는 구조.
	 * 		1) checked exception -> unchecked exception 으로 전환
	 * 		2) 범용으로 사용되는 예외가 발생한 경우, 구체적인 정보를 표현하는 커스텀 예외를 변경할 떄,
	 */
	
	
		
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
}
