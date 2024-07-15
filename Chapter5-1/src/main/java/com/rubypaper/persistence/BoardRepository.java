package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;		// 페이징 처리를 위한 인터페이스를 import 함
import org.springframework.data.jpa.repository.JpaRepository;		// JPA 리포지토리 기능을 사용하기 위해 import 함
import org.springframework.data.jpa.repository.Query;		// JPQL 쿼리를 사용하기 위해 import 함
import org.springframework.data.repository.query.Param;		// 쿼리 매개변수를 바인딩하기 위해 import 함

import com.rubypaper.domain.Board;		// Board 엔티티를 import 함

/* 이 인터페이스는 Board 엔티티에 대한 다양한 쿼리 메서드를 정의합니다. 
 * 기본적으로 JpaRepository를 상속받아 기본 CRUD 메서드를 제공하며, 
 * 메서드 이름을 통해 쿼리를 생성하는 스프링 데이터 JPA의 기능을 활용하여 다양한 검색 조건에 따라 게시물을 찾는 메서드를 정의합니다. 
 * 또한, @Query 어노테이션을 사용하여 JPQL을 직접 작성하여 복잡한 쿼리도 실행할 수 있습니다.
 */
public interface BoardRepository extends JpaRepository<Board, Long> {		// JpaRepository를 상속하여 Board 엔티티에 대한 기본 CRUD 메서드를 제공
	
	List<Board> findByTitle(String title);		// 제목이 특정 문자열과 일치하는 게시물 목록을 찾음
	
	List<Board> findByContentContaining(String searchKeyword);		// 내용에 특정 키워드가 포함된 게시물 목록을 찾음
	
	List<Board> findByTitleContainingOrContentContaining(String title, String content);		// 제목 또는 내용에 특정 키워드가 포함된 게시물 목록을 찾음
	
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);		// 제목에 특정 키워드가 포함된 게시물 목록을 내림차순으로 정렬하여 찾음
	
	List<Board> findByTitleContaining(String searchKeyword, Pageable paging);		// 제목에 특정 키워드가 포함된 게시물 목록을 페이징 처리하여 찾음
	
//	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")		// JPQL을 사용하여 제목에 특정 키워드가 포함된 게시물 목록을 내림차순으로 찾는 쿼리를 정의
//	List<Board> queryAnnotationTest1(String searchKeyword);		// 위의 JPQL 쿼리를 실행하는 메서드
	
//	@Query("SELECT b.seq, b.title, b.writer, b.createDate, b.cnt FROM Board b "		// JPQL을 사용하여 게시물의 특정 필드를 선택하여 찾는 쿼리를 정의
//			+ "WHERE b.title LIKE %?1% ORDER BY b.seq DESC ")
//	List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);		// 위의 JPQL 쿼리를 실행하는 메서드로, 결과는 Object 배열로 반환
}
