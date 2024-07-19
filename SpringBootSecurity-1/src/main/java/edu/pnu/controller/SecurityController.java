package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/index")
	public String index() {
		System.out.println("index 요청");
		return "index";
	}
	
	@GetMapping("/member")
	public void member() {
		System.out.println("member 요청");
	}
	
	@GetMapping("/manager")
	public void manager() {
		System.out.println("manager 요청");
	}
	
	@GetMapping("/admin")
	public void admin() {
		System.out.println("admin 요청");
	}
	
}
