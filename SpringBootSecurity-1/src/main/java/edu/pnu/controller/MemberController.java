package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/api.members")
	public ResponseEntity<String> createMember(@Validated @RequestBody Member member, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Invalid member data");
		}
		memberService.save(member);
		return ResponseEntity.ok("Member created successfully");
	}
	
}
