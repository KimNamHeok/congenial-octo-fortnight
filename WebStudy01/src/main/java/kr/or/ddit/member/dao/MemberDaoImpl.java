package kr.or.ddit.member.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.text.WordUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl implements MemberDAO {

	@Override
	public MemberVO selectMember(String username) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MEM_ID, MEM_PASSWORD, MEM_NAME, MEM_MAIL, MEM_BIR, MEM_ADD1, MEM_ADD2");
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
//				dataMapping : entity -> object 변환할 예정
				member = entityToObject(rs, MemberVO.class);

			}
			return member;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private <T> T entityToObject(ResultSet rs, Class<T> resultType) throws SQLException {
		try {
			T resultObject = (T) resultType.getConstructor().newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCnt = rsmd.getColumnCount();
			for(int i=1; i<=columnCnt; i++) {
				String columnName = rsmd.getColumnName(i);
				String columnValue = rs.getString(columnName);
				// mem_id, memId
				String propertyName = WordUtils.capitalize(columnName.toLowerCase(), '_')
										.replace("_", "");
				PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultType);
				pd.getWriteMethod().invoke(resultObject, columnValue);
			}
			return resultObject;
		} catch (Exception e) {
			throw new SQLException(e);
		}
//		member = new MemberVO();
//		member.setMemId(rs.getString("MEM_ID"));
//		member.setMemPassword(rs.getString("MEM_PASSWORD"));
//		member.setMemName(rs.getString("MEM_NAME"));
//		member.setMemMail(rs.getString("MEM_MAIL"));
//		member.setMemBir(rs.getString("MEM_BIR"));
//		return member;
	}

}
