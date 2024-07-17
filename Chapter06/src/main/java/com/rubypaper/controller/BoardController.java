package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.service.BoardService;

@Controller
@SessionAttributes("member")
public class BoardController {

	@Autowired
	private BoardService boardService;

//	@GetMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		List<Board> boardList = new ArrayList<Board>();
//
//		for (int i = 1; i <= 10; i++) {
//			boardList.add(Board.builder()
//					.seq((long)i)
//					.title("게시판 프로그램 테스트")
//					.writer("도우너")
//					.content("게시판 프로그램 테스트입니다...")
//					.createDate(new Date())
//					.cnt(0L)
//					.build());
//		}
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}

	@ModelAttribute("member")
	public Member getMember() {
		return new Member();
	}
	
	@RequestMapping(value = "/getBoardList", method = {RequestMethod.GET, RequestMethod.POST})
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
		
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		List<Board> boardList = boardService.getBoardList(board);

		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}

	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}

	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	
	
	


}
