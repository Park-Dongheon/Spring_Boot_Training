package edu.pnu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;

@Service
public class MemberService {
	private List<MemberDAO> list = new ArrayList<MemberDAO>();

	public List<MemberDAO> getAllMember() {
		return list;
	}

	
}
