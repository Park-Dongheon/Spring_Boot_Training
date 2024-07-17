package com.rubypaper.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String password;
	private String name;
	private String role;
	
}
