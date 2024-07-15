package com.rubypaper;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {		// 서버 구동 시 빈 객체를 등록 후 먼저 자동 실행 인터페이스
	
	private final BoardRepository boardRepo;	// BoardRepository 

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		for (int i = 1; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
//					.writer("member1")
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random() * 100))
					.build());
		}
		
		for (int i = 1; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
//					.writer("member2")
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random() * 100))
					.build());
		}
	}

}
