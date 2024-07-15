package com.rubypaper;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // 테스트를 @Order 번호 순서대로 실행
public class BoardRepositoryTest {
	@Autowired private BoardRepository boardRepo; // 빈 객체 등록된 BoardRepository 인스턴스 객체 사용
	
	@Test
	@Order(1)
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("첫 번째 게시글");
//		board.setWriter("테스터");
		board.setContent("잘 등록되나요?");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board);	// 객체를 저장하고, 저장된 객체를 리턴
	}
	
	@Test
	@Order(2)
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();		// ID를 기반으로 객체를 조회, Optional<Board> 타입을 리턴
		System.out.println(board);
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		System.out.println("== 1번 게시글 조회 ==");
		Board board = boardRepo.findById(1L).get();
		System.out.println("== 게시글 제목 수정 ==");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	}
	
	@Test
	@Order(4)
	public void testDeleteBoard() {
		boardRepo.deleteById(1L);		// ID를 기반으로 객체를 삭제, 리턴 타입이 없음
	}

	
}
