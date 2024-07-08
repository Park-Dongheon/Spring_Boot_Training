package edu.pnu.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	private MemberService memberService;
	
	public MemberController() throws ClassNotFoundException, SQLException {
		memberService = new MemberService();
	}

	// 검색(Read - Select)
	@GetMapping("/members")
	public List<MemberDTO> getAllMembers() throws ClassNotFoundException, SQLException {
		return memberService.getAllMembers();
	}
	
	// 검색(Read - Select)
	@GetMapping("/member")
	public MemberDTO getMemberById(Integer id) throws ClassNotFoundException, SQLException {
		for (MemberDTO m : memberService.getAllMembers()) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	// 입력(Create - Insert)
	@PostMapping("/member")
	public MemberDTO addMember(MemberDTO memberDTO) throws ClassNotFoundException, SQLException {
		if (getMemberById(memberDTO.getId())!= null) {
			System.out.println(memberDTO.getId() + "가 이미 존재합니다.");
			return null;
		}
		memberDTO.setRegidate(new Date());
		int result = memberService.addMember(memberDTO);
		return result > 0 ? memberDTO : null;
	}
	
	// 입력(Create - Insert)
	@PostMapping("/memberJSON")
	public MemberDTO addMemberJSON(@RequestBody MemberDTO memberDTO) throws ClassNotFoundException, SQLException {
		if (getMemberById(memberDTO.getId()) != null) {
			System.out.println(memberDTO.getId() + "가 이미 존재합니다.");
			return null;
		}
		memberDTO.setRegidate(new Date());
		int result = memberService.addMember(memberDTO);
		return result > 0 ? memberDTO : null;
	}
	
	// 수정(Update - Update)
	@PutMapping("/member")
	public int updateMember(MemberDTO memberDTO) throws ClassNotFoundException, SQLException {
		MemberDTO m = getMemberById(memberDTO.getId());
		if (m == null)
			return 0;
		m.setName(memberDTO.getName());
		m.setPass(memberDTO.getPass());
		return memberService.updateMember(memberDTO);
	}
	
	// 삭제(Delete - Delete)
	@DeleteMapping("/member")
	public int removeMapping(Integer id) {
		try {
			memberService.removeMember(id);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
}
