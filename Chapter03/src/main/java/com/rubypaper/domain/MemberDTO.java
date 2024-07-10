package com.rubypaper.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MemberDTO {
	private int id;
	private String pass;
	private String name;
	private Date regidate;
}
