package com.rubypaper.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.rubypaper.domain.Board;

public interface DynamicBoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {		// BoardRepository 인터페이스가 QuerydslPredicateExecutor 인터페이스를 추가로 상속
	
}
