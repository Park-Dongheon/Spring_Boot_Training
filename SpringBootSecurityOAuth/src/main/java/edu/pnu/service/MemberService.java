package edu.pnu.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepo;
	private final PasswordEncoder passwordEncoder;
	
	public void save(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setEnabled(true);
		memberRepo.save(member);
	}
	
}
