package com.rubypaper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	
	
	@GetMapping("/hello2")
	public String hello() {
		// /WEB-INF/board/hello.jsp
		return "hello";
	}
	
	// localhost:8080/hello?name=홍길동
	@GetMapping("/hello")
	public String hello(String name, Model model) {
		// /WEB-INF/board/hello.jsp
		model.addAttribute("name", name);
		
		return "hello";
	}
	
	// localhost:8080/hello?name=홍길동
	@GetMapping("/hello1")
	public ModelAndView hello1(String name) {
		// /WEB-INF/board/hello.jsp
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("name", name);
		mv.setViewName("hello");
		
		return mv;
	}
}
