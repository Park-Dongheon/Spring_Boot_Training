package com.rubypaper.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Member {
	@Id		// Primary Key 지정
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	@JsonIgnore // controller의 반환 값이 순환되는 것을 제외
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade=CascadeType.ALL) // Board 에 있는 foreing key member랑 연결
	private List<Board> boardList = new ArrayList<Board>();
	
	
}
