package com.rubypaper.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//============================================================
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(TemporalType.DATE)		// JPA(Java Persistence API)에서 날짜와 시간 타입의 데이터를 매핑할 때 사용하는 어노테이션
	private Date createDate;
	private Long cnt;
//============================================================
	
//	public Long getSeq() {
//		return seq;
//	}
//	
//	public void setSeq(Long seq) {
//		this.seq = seq;
//	}
//	
//	public String getTitle() {
//		return title;
//	}
//	
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	
//	public String getWriter() {
//		return writer;
//	}
//	
//	public void setWriter(String writer) {
//		this.writer = writer;
//	}
//	
//	public String getContent() {
//		return content;
//	}
//	
//	public void setContent(String content) {
//		this.content = content;
//	}
//	
//	public Date getCreateDate() {
//		return createDate;
//	}
//	
//	public void setCreateDate(Date createDate) {
//		this.createDate = createDate;
//	}
//	
//	public Long getCnt() {
//		return cnt;
//	}
//	
//	public void setCnt(Long cnt) {
//		this.cnt = cnt;
//	}
//	
//	@Override
//	public String toString() {
//		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
//				+ ", createDate=" + createDate + ", cnt=" + cnt + "]";
//	}
	
}
