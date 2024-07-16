package com.rubypaper;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
public class RelationMappingTest {
//	@Autowired
//	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void testManyToOneInsert() {
		Member m1 = Member.builder()
				.id("member1")
				.password("member111")
				.name("둘리")
				.role("User").build();
//		memberRepo.save(m1);
		
		Member m2 = Member.builder()
				.id("member2")
				.password("member222")
				.name("도우너")
				.role("Admin").build();
//		memberRepo.save(m2);
		
		for (int i = 1; i <= 100; i++) {
			Board b1 = Board.builder()
					.title("title" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random() * 100))
					.member(m1)
					.build();
//			boardRepo.save(b1);
		}
		memberRepo.save(m1);
		
		for (int i = 1; i <= 100; i++) {
			Board b2 = Board.builder()
					.title("title" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random() * 100))
					.member(m2)
					.build();
//			boardRepo.save(b2);
		}
		memberRepo.save(m2);
		
	}
	
//	@Test
//	public void testTwoWayMapping() {
//		Member member = memberRepo.findById("member1").get();
//		
//		
//		System.out.println("===========================");
//		System.out.println(member.getName() + "가(이) 저장한 게시글 목록");
//		System.out.println("===========================");
//		List<Board> list = member.getBoardList();
//		for (Board board : list) {
//			System.out.println(board.toString());
//		}
//	}
	
	@Test
	public void testCascadeDelete() {
		memberRepo.deleteById("member1");
	}

}
