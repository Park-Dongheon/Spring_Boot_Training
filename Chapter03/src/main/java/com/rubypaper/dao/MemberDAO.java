package com.rubypaper.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rubypaper.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
	private final Connection con;	// 이 필드는 BoardAutoConfiguration에서 설정한 빈으로 주입
	
	public List<MemberDTO> getAllmembers() throws SQLException {
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
		
		return list;
	}

}
