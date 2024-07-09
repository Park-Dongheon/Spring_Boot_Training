package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LogDAO {
//	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
//	private static String URL = "jdbc:mysql://localhost:3306/musthave";
//	private static String USER = "scott";
//	private static String PASSWORD = "tiger";
	private final Connection con;
	
//	public LogDAO() throws ClassNotFoundException, SQLException {
//		Class.forName(DRIVER);
//		con = DriverManager.getConnection(url, username, password);
//	}


	// addLog(): 데이터베이스 연결을 설정하고, 로그를 dblog 테이블에 삽입하는 메서드
	public void addLog(String sqlstring, boolean success, String method) throws SQLException {
		
		String query = "INSERT INTO dblog (sqlstring, success, method) VALUES (?, ?, ?); ";
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, sqlstring);
			ps.setBoolean(2,  success);
			ps.setString(3, method);
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
