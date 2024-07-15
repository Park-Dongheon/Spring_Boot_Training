package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> { // 인터페이스로 만든 객체를 자동으로 빈객체로 등록, 타입 Long
// JPARepository가 Board 엔티티를 다룸, 그 엔티티의 기본 키(Primary Key)가 Long 타입
// BoardRepository 인터페이스에 여러 가지 메소드가 있을 수 있으며, 각 메소드의 리턴 타입은 다름
	List<Board> findByTitle(String title);	// 쿼리 메소드 정의
	
	List<Board> findByContentContaining(String searchKeyword);
	
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
//	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")			// 사용자 입력값이 '?1' 에 바인딩
//	List<Board> queryAnnotationTest1(String searchKeyword);
	
//	@Query("SELECT b.seq, b.title, b.writer, b.createDate, b.cnt FROM Board b "
//			+ "WHERE b.title LIKE %?1% ORDER BY b.seq DESC ")
//	List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);		// 메서드 매개변수와 쿼리 파라미터를 매핑, searchKeyword 매개변수가 쿼리에서 사용될 파라미터임
	
}
