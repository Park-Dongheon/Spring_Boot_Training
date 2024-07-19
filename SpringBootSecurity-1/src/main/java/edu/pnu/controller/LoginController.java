package edu.pnu.controller;

import java.lang.reflect.Member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final MemberRepository memberRepo;
	
	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/join")
	public void join() {
		System.out.println("join 요청");
	}
	
	@PostMapping("/join")
	public String joinProc(@ModelAttribute("member") Member member, Model model) {
		System.out.println("join 요청");
		model.addAttribute("member", member);
		memberRepo.save(member);
		
		return "redirect:welcome";
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied 요청");
	}
	
}
