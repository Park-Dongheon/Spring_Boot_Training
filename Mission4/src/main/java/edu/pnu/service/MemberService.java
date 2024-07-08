package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

public class MemberService {
	private MemberDAO memberDAO;
	private LogDAO logDAO;

	public MemberService() throws ClassNotFoundException, SQLException {
		memberDAO = new MemberDAO();
		logDAO = new LogDAO();
	}

	public List<MemberDTO>  getAllMembers() throws ClassNotFoundException, SQLException {
		Map<String, Object> map = memberDAO.getAllMembers();
		
		@SuppressWarnings("unchecked")
		List<MemberDTO> list = (List<MemberDTO>)map.get("result");
		String sqlstring = (String) map.get("sqlstring");
		boolean success = (boolean) map.get("success");

		logDAO.addLog(sqlstring, success, "GET");
		
		return list;
	}
	
	public int addMember(MemberDTO memberDTO) throws SQLException {
		Map<String, Object> map = memberDAO.addMember(memberDTO);
		
		int result = (int) map.get("result");
		String sqlstring = (String) map.get("sqlstring");
		boolean success = (boolean) map.get("success");
		
		logDAO.addLog(sqlstring, success, "POST");
		
		return result;
	}
	
	public int updateMember(MemberDTO memberDTO) throws SQLException {
		Map<String, Object> map = memberDAO.updateMember(memberDTO);
		
		int result = (int) map.get("result");
		String sqlstring = (String) map.get("sqlstring");
		boolean success = (boolean) map.get("success");
		
		logDAO.addLog(sqlstring, success, "PUT");
		
		return result;
	}

	public int removeMember(Integer id) throws SQLException {
		Map<String, Object> map = memberDAO.removeMember(id);
		
		int result = (int) map.get("result");
		String sqlstring = (String) map.get("sqlstring");
		boolean success = (boolean) map.get("success");
		
		logDAO.addLog(sqlstring, success, "DELETE");
		
		return result;
	}

	
}
