package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rubypaper.jdbc.util.JDBCConnectionManager;

import edu.pnu.domain.MemberDTO;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class MemberDAO {
	private final JDBCConnectionManager jdbcConnectionManager;
		
	public Map<String, Object> getAllMembers() throws SQLException, ClassNotFoundException {
		Map<String, Object> map = new HashMap<>();
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		String query = "SELECT * FROM member";
		
        try (Connection con = jdbcConnectionManager.getConnection();
        	Statement st = con.createStatement();
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
	
	private String getSQLString(PreparedStatement ps) {
		String[] sqls = ps.toString().split(": ");
		return sqls[1];
	}
	
	public Map<String, Object> addMember(MemberDTO memberDTO) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		String query = "INSERT INTO member (pass, name) VALUES (?, ?); ";
		
		int result = 0;
		try (Connection con = jdbcConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, memberDTO.getPass());
			ps.setString(2, memberDTO.getName());
			
			result = ps.executeUpdate();
			
			query = getSQLString(ps);
		}

		// 정규표현식을 이용한 ? 표현 대체: ? --> \?(정규표현식)
//		query = query.replaceFirst("\\?", memberDTO.getPass());
//		query = query.replaceFirst("\\?", memberDTO.getName());
		
		map.put("result", result);
		map.put("sqlstring", query);
		map.put("success", true);
		
		return map;
	}

	public Map<String, Object> updateMember(MemberDTO memberDTO) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		String query = "UPDATE member SET pass = ?, name = ? WHERE id = ?; ";
		
		int result = 0;
		try (Connection con = jdbcConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, memberDTO.getPass());
			ps.setString(2, memberDTO.getName());
			ps.setInt(3, memberDTO.getId());
			
			result = ps.executeUpdate();
			
			query = getSQLString(ps);
		}
		
//		query = query.replaceFirst("\\?", memberDTO.getPass());
//		query = query.replaceFirst("\\?", memberDTO.getName());
//		query = query.replaceFirst("\\?", memberDTO.getId().toString());
		
		map.put("result", result);
		map.put("sqlstring", query);
		map.put("success", true);
		
		return map;
	}

	public Map<String, Object> removeMember(Integer id) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		
		String query = "DELETE FROM member WHERE id = ?; ";
		
		int deleteRows = 0;
		try (Connection con = jdbcConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			deleteRows = ps.executeUpdate();
			
			query = getSQLString(ps);
		}
		
//		query = query.toString().replace("?", id.toString());
		
		
		
		map.put("result", deleteRows);
		map.put("sqlstring", query);
		map.put("success", true);
		
		return map;
	}

}
