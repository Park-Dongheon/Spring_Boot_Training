package com.rubypaper;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberDTO;

@SpringBootTest
public class MemberDaoTest {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void test() throws SQLException {
		List<MemberDTO> list = memberDAO.getAllmembers();
		for (MemberDTO m : list) {
			System.out.println(m.toString());
		}
	}
	
}
