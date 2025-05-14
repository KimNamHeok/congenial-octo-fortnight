package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl implements MemberDAO {

	@Override
	public MemberVO selectMember(String username) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MEM_ID, MEM_PASSWORD, MEM_NAME, MEM_MAIL ");
		sql.append(" FROM MEMBER                                     ");
		sql.append(" WHERE MEM_ID = ?                 ");
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			MemberVO member = null;
			if(rs.next()) {
				member = new MemberVO();
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemPassword(rs.getString("MEM_PASSWORD"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemMail(rs.getString("MEM_MAIL"));
			}
			return member;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
