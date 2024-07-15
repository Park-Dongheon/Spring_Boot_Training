package com.rubypaper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // 테스트를 @Order 번호 순서대로 실행
public class QueryAnnotationTest {

	@Autowired
	private BoardRepository boardRepo;			// BoardRepository 로 부터 상속받은 메서드 재정의
	
//	@Test
//	public void testQueryAnnotationTest1() {
//		List<Board> boardList = boardRepo.queryAnnotationTest1("title10");
//		
//		System.out.println("'title10' 이 포함된 데이터를 seq 내림차순으로 정렬, 검색 결과");
//		for (Board b : boardList)
//			System.out.println(b);
//		System.out.println();
//	}
	
//	@Test
//	public void testQueryAnnotationTest2() {
//		List<Object[]> boardList = boardRepo.queryAnnotationTest2("title10");
//		
//		System.out.println("'title10' 이 포함된 데이터를 seq 내림차순으로 정렬, 검색 결과");
//		for (Object[] b : boardList)
//			System.out.println(Arrays.toString(b));
//		System.out.println();
//	}
	
}
