package com.rubypaper.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
public class BoardController {
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
	

}
