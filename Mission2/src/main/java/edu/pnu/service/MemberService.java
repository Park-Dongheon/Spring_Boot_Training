package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.MemberVO;


@Service
public class MemberService {
	private List<MemberVO> list = new ArrayList<MemberVO>();
	
	public MemberService() {
		for (int i = 1; i <= 10; i++) {
			list.add(MemberVO.builder()
				.id(i).name("name" + i)
				.pass("pass" + i)
				.regidate(new Date()).build());
		}	
	}

	public List<MemberVO> getAllMember() {
		return list;
	}
	
}
