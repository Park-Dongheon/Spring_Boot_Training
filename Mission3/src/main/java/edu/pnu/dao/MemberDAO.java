package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberDTO;

public class MemberDAO {

	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/musthave";
	private static String USER = "scott";
	private static String PASSWORD = "tiger";
	private Connection con;
	List<MemberDTO> memberDTO;

	// 명시한 데이터베이스로의 연결이 완료된 MemberDAO 객체를 생성합니다.
	public MemberDAO() throws SQLException {
		con = DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public List<MemberDTO> getAllMembers() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		System.out.println("로딩 성공");
		
		memberDTO = new ArrayList<MemberDTO>();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM member");
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String pass = rs.getString("pass");
			String name = rs.getString("name");
			Date regidate = rs.getDate("regidate");
			
			memberDTO.add(new MemberDTO(id, pass, name, regidate));
		}
		
		return memberDTO;
	}

	public void setName(String name) {
		
	}

	public void setPass(String pass) {
		
	}

	public void setRegidate(Date date) {
		
	}

}
