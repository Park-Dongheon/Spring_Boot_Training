package com.rubypaper;

import java.util.Date;		// 날짜 처리를 위한 클래스 import

import org.springframework.boot.ApplicationArguments;		// 애플리케이션 실행 시 전달된 인자를 처리하기 위한 클래스 import
import org.springframework.boot.ApplicationRunner;		// 애플리케이션 시작 시 특정 코드를 실행하기 위한 인터페이스 import
import org.springframework.stereotype.Component;		// 스프링 컴포넌트로 등록하기 위한 어노테이션 import

import com.rubypaper.domain.Board;		// Board 엔티티 클래스 import
import com.rubypaper.domain.Member;		// Member 엔티티 클래스 import
import com.rubypaper.persistence.BoardRepository;		// BoardRepository 인터페이스 import
import com.rubypaper.persistence.MemberRepository;		// MemberRepository 인터페이스 import

import lombok.RequiredArgsConstructor;		// final 필드에 대한 생성자를 자동으로 생성하기 위한 어노테이션 import

/* 이 클래스는 Spring Boot 애플리케이션이 시작될 때 실행되는 초기 데이터 설정을 담당합니다.
 * ApplicationRunner 인터페이스를 구현하여 애플리케이션 시작 시 run 메서드가 실행되도록 하고, 
 * 이 메서드 내에서 Member와 Board 엔티티 객체를 생성하고 데이터베이스에 저장합니다. 
 * Lombok의 어노테이션을 사용하여 코드의 간결함을 유지하고, 
 * @Component 어노테이션을 통해 스프링 컨텍스트가 이 클래스를 관리하도록 합니다.
 */
@Component		 // 이 클래스를 스프링 컴포넌트로 등록하여 스프링 컨텍스트가 관리
@RequiredArgsConstructor		// final 필드에 대한 생성자를 Lombok이 자동으로 생성
public class DataInit implements ApplicationRunner{		// ApplicationRunner 인터페이스를 구현하여 애플리케이션 시작 시 특정 코드를 실행

	private final BoardRepository boardRepo;		// BoardRepository를 주입받기 위한 final 필드
	private final MemberRepository memberRepo;		// MemberRepository를 주입받기 위한 final 필드
	
	@Override		// ApplicationRunner 인터페이스의 run 메서드를 구현
	public void run(ApplicationArguments args) throws Exception {		// 애플리케이션 실행 시 실행될 코드를 정의
		
		Member m1 = Member.builder()		// 첫 번째 회원 객체를 빌더 패턴을 사용하여 생성
				.id("member1")				// 회원 ID 설정
				.password("member111")		// 회원 비밀번호 설정
				.name("둘리")					// 회원 이름 설정
				.role("User").build();		// 회원 역할 설정 및 객체 생성
		memberRepo.save(m1);				// 첫 번째 회원 객체를 데이터베이스에 저장
		
		Member m2 = Member.builder()		// 두 번째 회원 객체를 빌더 패턴을 사용하여 생성
				.id("member2")
				.password("member222")
				.name("도우너")
				.role("Admin").build();
		memberRepo.save(m2);
		
		for (int i = 1; i <= 100; i++) {		// 100개의 게시물을 생성하기 위한 반복문
			boardRepo.save(Board.builder()		// 게시물 객체를 빌더 패턴을 사용하여 생성
					.title("title" + i)			// 게시물 제목 설정
//					.writer("member1")			// 게시물 작성자 설정
					.content("content" + i)		// 게시물 내용 설정
					.createDate(new Date())		// 게시물 생성 날짜 설정
					.cnt((long)(Math.random() * 100))		// 게시물 조회수 설정 (랜덤 값)
					.member(m1)
					.build());					// 객체 생성 및 저장
		}
		
		for (int i = 1; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
//					.writer("member2")
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random() * 100))
					.member(m2)
					.build());
		}
	}
	
}
