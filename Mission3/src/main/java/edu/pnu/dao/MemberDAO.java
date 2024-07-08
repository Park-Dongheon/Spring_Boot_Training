package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberDTO;


public class MemberDAO {
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/musthave";
	private static String USER = "scott";
	private static String PASSWORD = "tiger";
	private Connection con;
	private List<MemberDTO> memberDTO;
	
	public MemberDAO() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public List<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException {		
		memberDTO = new ArrayList<MemberDTO>();
		
		String query = "SELECT * FROM member; ";
		
        try (Statement st = con.createStatement(); 
        	ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                memberDTO.add(MemberDTO.builder()
                        .id(rs.getInt("id"))
                        .pass(rs.getString("pass"))
                        .name(rs.getString("name"))
                        .regidate(rs.getDate("regidate")).build());
            }
        }
		return memberDTO;
	}
	
	public int addMember(MemberDTO memberDTO) throws SQLException {
		String query = "INSERT INTO member (id, pass, name) VALUES (?, ?, ?); ";
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, memberDTO.getId());
			ps.setString(2, memberDTO.getPass());
			ps.setString(3, memberDTO.getName());
			/*
			 * ps.setTimestamp(4, new
			 * java.sql.Timestamp(memberDTO.getRegidate().getTime()));
			 */
			
			return ps.executeUpdate();
		}
	}

	public int updateMember(MemberDTO memberDTO) throws SQLException {
		String query = "UPDATE member SET pass = ?, name = ? WHERE id = ?; ";
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, memberDTO.getPass());
			ps.setString(2, memberDTO.getName());
			ps.setInt(3, memberDTO.getId());
			
			return ps.executeUpdate();
		}
	}

	public int removeMember(Integer id) throws SQLException {
		String query = "DELETE FROM member WHERE id = ?; ";
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			int deleteRows = ps.executeUpdate();
			return deleteRows;
		}
	}

}
