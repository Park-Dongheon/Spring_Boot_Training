package edu.pnu.controll;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
	@GetMapping("/auth")
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal User user) {
		if (user == null) {
			return ResponseEntity.ok("로그인 상태가 아닙니다.");
		}
		return ResponseEntity.ok(user);
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
