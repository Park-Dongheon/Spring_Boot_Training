package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // 테스트를 @Order 번호 순서대로 실행
public class QueryMethodTest {

	@Autowired
	private BoardRepository boardRepo;			// BoardRepository 로 부터 상속받은 메서드 재정의
	
	@Test
	@Order(1)
	public void testFindByTitle() {
		
		List<Board> list = boardRepo.findByTitle("title10");
		System.out.println("--> testFindByTitle");
		for (Board b : list)
			System.out.println(b);
	}
	
	@Test
	@Order(2)
	public void testByContentContaining() {
		List<Board> boardlist = boardRepo.findByContentContaining("5");
		
		System.out.println("content에 숫자 5가 포함된 데이터 검색 결과");
		for (Board b : boardlist)
			System.out.println(b);
		System.out.println();
	}
	
	@Test
	@Order(3)
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardlist = boardRepo.findByTitleContainingOrContentContaining("5", "7");
		
		System.out.println("title에 숫자 5가 포함되었거나, content에 숫자 7이 포함된 데이터 검색 결과");
		for (Board b : boardlist)
			System.out.println(b);
		System.out.println();
	}
	
	@Test
	@Order(4)
	public void testFindByTitleContainingOrderBySeqDesc() {
		List<Board> boardlist = boardRepo.findByTitleContainingOrderBySeqDesc("17");
		
		System.out.println("게시글 제목에 특정 단어가 포함된 글 목록을 내림차순으로 조회, 검색 결과");
		for (Board b : boardlist)
			System.out.println(b);
		System.out.println();
	}
	
	@Test
	@Order(5)
	public void testFindByTitleContaining() {
		Pageable paging = PageRequest.of(0, 5);
		List<Board> boardList = boardRepo.findByTitleContaining("title", paging);
		
		System.out.println("한 화면에 다섯 개의 데이터를 보여주는 페이징, 검색 결과");
		for (Board b : boardList)
			System.out.println(b);
		System.out.println();
		
	}
	
}
