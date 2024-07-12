package com.rubypaper.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rubypaper.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> { // 인터페이스로 만든 객체를 자동으로 빈객체로 등록, 타입 Long
// JPARepository가 Board 엔티티를 다룸, 그 엔티티의 기본 키(Primary Key)가 Long 타입
// BoardRepository 인터페이스에 여러 가지 메소드가 있을 수 있으며, 각 메소드의 리턴 타입은 다름
}
