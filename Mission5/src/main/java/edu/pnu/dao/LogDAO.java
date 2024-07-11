package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.rubypaper.jdbc.util.JDBCConnectionManager;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LogDAO {
	private final JDBCConnectionManager jdbcConnectionManager;

	// addLog(): 데이터베이스 연결을 설정하고, 로그를 dblog 테이블에 삽입하는 메서드
	public void addLog(String sqlstring, boolean success, String method) throws SQLException {
		
		String query = "INSERT INTO dblog (sqlstring, success, method) VALUES (?, ?, ?); ";
		
		try (Connection con = jdbcConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, sqlstring);
			ps.setBoolean(2,  success);
			ps.setString(3, method);
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
