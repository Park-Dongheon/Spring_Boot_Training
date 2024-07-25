package edu.pnu.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final MemberService memberService;
	
	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청입니다.");
	}
	 
	@GetMapping("/loginSuccess")
	public void loginSuccess() {		// loginSuccess.html
		System.out.println("loginSuccess 요청입니다.");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}

	// 로그인 세션 정보 확인용 URL
	@GetMapping("/oauth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
		
		if (user == null) return "OAuth2:null";
		
		// 자동 회원가임을 한다면 이용할 정보 확인
		System.out.println("attributes:" + user.getAttributes());
		
		return "OAuth2:" + user;
	}
	
	@GetMapping("/join")
	public String join() {
		System.out.println("join 요청입니다.");
		return "join";
	}
	
	@PostMapping("/join")
	public String joinProc(@ModelAttribute("member") Member member, Model model) {
		memberService.save(member);
		model.addAttribute("member", member);
		return "redirect:welcome";
	}
	
	@GetMapping("/welcome")
	public String welcome(@ModelAttribute("member") Member member, Model model) {
		System.out.println("welcome 요청입니다.");
		return "welcome";
	}
	
}
