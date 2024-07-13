package com.rubypaper.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TestController {

	private final BoardRepository boardRepo;
	
	@GetMapping("/board")
	public List<Board> getBoards() {
		return boardRepo.findAll();
	}
	
	@GetMapping("/board/{seq}")	// 경로로 들어오는 HTTP GET 요청을 해당 메서드에 매핑, 경로 변수 (Path Variable)로, URL 경로의 일부를 변수로 사용
	public Board getBoard(@PathVariable Long seq) {	// (name="seq")
		return boardRepo.findById(seq).orElseThrow();
//		Optional<Board> b = boardRepo.findById(seq);
//		return b.get();
	}
	
	@PostMapping("/board")
	public Board postBoard(@RequestBody Board board) {
		board.setCreateDate(new Date());
		board.setCnt((long)(Math.random() * 100));
		return boardRepo.save(board);
	}
	
	@PutMapping("/board")
	public Board putBoard(@RequestBody Board board) {
		Board b = boardRepo.findById(board.getSeq()).orElseThrow();
		
		if (board.getTitle() != null) b.setTitle(board.getTitle());
		if (board.getContent() != null) b.setContent(board.getContent());
		if (board.getWriter() != null) b.setWriter(board.getWriter());		
		b.setCreateDate(new Date());
		b.setCnt((long)(Math.random() * 100));
		return boardRepo.save(b);
	}
	
	@DeleteMapping("/board/{seq}")
	public Board deleteBoard(@PathVariable Long seq) {
		Board b = boardRepo.findById(seq).orElseThrow();
		boardRepo.deleteById(seq);
		return b;
	}	
}
