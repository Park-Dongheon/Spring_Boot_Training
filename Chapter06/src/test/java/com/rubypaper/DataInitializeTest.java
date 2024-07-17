package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
public class DataInitializeTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testDataInsert() {
		Member m1 = new Member();
		m1.setId("member1");
		m1.setName("둘리");
		m1.setPassword("member111");
		m1.setRole("ROLE_USER");
		memberRepo.save(m1);
		
		Member m2 = new Member();
		m2.setId("member2");
		m2.setName("도우너");
		m2.setPassword("member222");
		m2.setRole("ROLE_ADMIN");
		memberRepo.save(m2);
		
		for (int i = 1; i <= 3; i++) {
			Board b1 = new Board();
			b1.setWriter("둘리");
			b1.setTitle("둘리가 등록한 게시물" + i);
			b1.setContent("둘리가 등록한 게시글 내용" + i);
			boardRepo.save(b1);
		}
		
		for (int i = 1; i <= 3; i++) {
			Board b2 = new Board();
			b2.setWriter("도우너");
			b2.setTitle("도우너가 등록한 게시물" + i);
			b2.setContent("도우너가 등록한 게시글 내용" + i);
			boardRepo.save(b2);
		}
	}
	
}
