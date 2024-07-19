package edu.pnu;

import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberInitialize implements ApplicationRunner {
	
	private final MemberRepository memRepo;
	private final 
}
