package com.rubypaper.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter		// Lombok 어노테이션: 모든 필드에 대한 getter 메서드를 생성
@Setter		// Lombok 어노테이션: 모든 필드에 대한 setter 메서드를 생성
@ToString		// Lombok 어노테이션: 모든 필드를 포함한 toString 메서드를 생성
@Builder		// Lombok 어노테이션: 빌더 패턴을 사용
@AllArgsConstructor		// Lombok 어노테이션: 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
@NoArgsConstructor		// Lombok 어노테이션: 매개변수가 없는 기본 생성자를 자동으로 생성
@Entity		// JPA 어노테이션: 이 클래스가 JPA 엔티티임을 나타냄 
public class Board {
	@Id		// JPA 어노테이션: 이 필드가 엔티티의 기본 키임을 나타냄
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 기본 키의 값을 자동으로 생성하도록 설정, 여기서는 IDENTITY 전략을 사용
	private Long seq;		// 게시물 고유 식별자
	private String title;		// 게시물의 제목을 저장하는 필드
//	private String writer;		// 작성자를 저장하는 필드
	private String content;		// 게시물의 내용을 저장하는 필드
	@Temporal(value=TemporalType.TIMESTAMP)		// JPA 어노테이션: 날짜/시간 정보를 TIMESTAMP로 매핑
	private Date createDate;	// 게시물이 생성된 날짜/시간을 저장하는 필드
	private Long cnt;		// 게시물 조회수를 저장하는 필드
	
	@ManyToOne		// JPA 어노테이션: 여러 게시물이 하나의 회원과 관계를 가짐을 나타냄 (다대일 관계)
	@JoinColumn(name="MEMBER_ID")		// JPA 어노테이션: MEMBER_ID라는 외래 키 컬럼과 회원 테이블의 관계를 정의
	private Member member;		// 게시물과 연관된 회원 엔티티를 나타내는 필드
}
