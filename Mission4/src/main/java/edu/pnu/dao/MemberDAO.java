package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberDTO;


public class MemberDAO {
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/musthave";
	private static String USER = "scott";
	private static String PASSWORD = "tiger";
	private Connection con;
	
	public MemberDAO() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public Map<String, Object> getAllMembers() throws SQLException, ClassNotFoundException {
		
		Map<String, Object> map = new HashMap<>();
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		String query = "SELECT * FROM member";
		
        try (Statement st = con.createStatement();
        	ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                list.add(MemberDTO.builder()
                        .id(rs.getInt("id"))
                        .pass(rs.getString("pass"))
                        .name(rs.getString("name"))
                        .regidate(rs.getDate("regidate")).build());
            }
        }
        
        map.put("result",  list);
        map.put("sqlstring", query);
        map.put("success", true);
        
		return map;
	}
	
	public Map<String, Object> addMember(MemberDTO memberDTO) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		String query = "INSERT INTO member (pass, name) VALUES (?, ?); ";
		
		int result = 0;
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, memberDTO.getPass());
			ps.setString(2, memberDTO.getName());
			
			result = ps.executeUpdate();
		}
		
		map.put("result", result);
		map.put("sqlstring", query);
		map.put("success", true);
		
		return map;
	}

	public Map<String, Object> updateMember(MemberDTO memberDTO) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		String query = "UPDATE member SET pass = ?, name = ? WHERE id = ?; ";
		
		int result = 0;
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, memberDTO.getPass());
			ps.setString(2, memberDTO.getName());
			ps.setInt(3, memberDTO.getId());
			
			result = ps.executeUpdate();
		}
		
		map.put("result", result);
		map.put("sqlstring", query);
		map.put("success", true);
		
		return map;
	}

	public Map<String, Object> removeMember(Integer id) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		String query = "DELETE FROM member WHERE id = ?; ";
		
		int deleteRows = 0;
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			deleteRows = ps.executeUpdate();
		}
		
		map.put("result", deleteRows);
		map.put("sqlstring", query);
		map.put("success", true);
		
		return map;
	}

}
