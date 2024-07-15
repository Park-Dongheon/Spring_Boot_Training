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

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
//	private String writer;
	private String content;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createDate;	// 카맬 notation -> 언더 스코어로 변경
	private Long cnt;
	
	@ManyToOne					// Board 엔티티가 Member 엔티티와 다대일 관계(Many-to-One)
	@JoinColumn(name="MEMBER_ID")			// Member 테이블의 Id 필드와 연결될 필드명, Member 테이블의 기본기 Id를 Board 테이블의 외래 키로 컬럼을 명시적으로 지정하는 데 사용
	private Member member;					// Member_id 형태로 속성이 추가 -> 명시적으로 지정한 이름으로 추가-
}
