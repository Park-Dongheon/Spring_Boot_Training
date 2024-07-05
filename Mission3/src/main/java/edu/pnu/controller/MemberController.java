package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	MemberService service;

	public MemberController() {
		service = new MemberService();
	}
	
	// 검색(Read - select)
	@GetMapping("/members")
	public List<MemberDAO> getAllMember() {
		return service.getAllMember();
	}
	
	// 검색(Read - select)
	@GetMapping("/member")
	public MemberDTO getMemberById(Integer id) {
//		for (MemberDTO m : service.getAllMember()) {
//			if (m.getId() == id)
//				return m;
//		}
		return null;
	}
	
	// 입력(Create - insert)
	@PostMapping("/member")
	public MemberDTO addMember(MemberDTO memberDTO) {
		if (getMemberById(memberDTO.getId()) != null) {
			System.out.println(memberDTO.getId() + "가 이미 존재합니다.");
			return null;
		}
		memberDTO.setRegidate(new Date());
//		service.getAllMember().add(memberDTO);
		return memberDTO;
	}
	
	// 입력(Create - insert)
	@PostMapping("/memberJSON")
	public MemberDTO addMemberJSON(@RequestBody MemberDTO memberDTO) {
		if (getMemberById(memberDTO.getId()) != null) {
			System.out.println(memberDTO.getId() + "가 이미 존재합니다.");
			return null;
		}
		memberDTO.setRegidate(new Date());
//		service.getAllMember().add(memberDTO);
		return memberDTO;
	}
	
	// 수정(Update - update)
	@PutMapping("/member")
	public int updateMember(MemberDTO memberDTO) {
		MemberDTO m = getMemberById(memberDTO.getId());
		if (m == null)
			return 0;
		m.setName(memberDTO.getName());
		m.setPass(memberDTO.getPass());
		return 1;
	}
	
	// 삭제(Delete - delete)
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		try {
			service.getAllMember().remove(getMemberById(id));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	
	}
}
