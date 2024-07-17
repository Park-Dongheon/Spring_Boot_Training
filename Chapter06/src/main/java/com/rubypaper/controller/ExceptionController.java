package com.rubypaper.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rubypaper.exception.BoardNotFoundException;

@Controller
public class ExceptionController {

	@RequestMapping(value = "/boardError", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardError() {
		throw new BoardNotFoundException("검색된 게시글이 없습니다.");
	}
	
	@RequestMapping(value = "/illegalArgumentError", method= {RequestMethod.GET, RequestMethod.POST})
	public String illegalArgumentError() {
		throw new IllegalArgumentException("부적절한 인자가 전달되었습니다.");
	}
	
	@RequestMapping(value = "/sqlError", method= {RequestMethod.GET, RequestMethod.POST})
	public String sqlError() throws SQLException {
		throw new SQLException("SQL 구문에 오류가 있습니다.");
	}
	
	@ExceptionHandler(SQLException.class)
	public String numberFormatError(SQLException exception, Model model) {
		model.addAttribute("exception", exception.getMessage());
		return "/errors/sqlError";
	}
	
}
