package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ConnectionFactoryTest {

	@Test
	void testGetConnection() {
		try (Connection conn = ConnectionFactory.getConnection()) {
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
