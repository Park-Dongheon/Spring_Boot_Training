package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	private MemberDAO memberDAO;

	public MemberService() throws ClassNotFoundException, SQLException {
		this.memberDAO = new MemberDAO();
	}

	public List<MemberDTO> getAllMembers() throws ClassNotFoundException, SQLException {
		return memberDAO.getAllMembers();
	}
	
	public int addMember(MemberDTO memberDTO) throws SQLException {
		return memberDAO.addMember(memberDTO);
	}
	
	public int updateMember(MemberDTO memberDTO) throws SQLException {
		return memberDAO.updateMember(memberDTO);
	}

	public int removeMember(Integer id) throws SQLException {
		return memberDAO.removeMember(id);
	}
	
}
