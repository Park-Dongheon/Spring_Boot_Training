package com.rubypaper.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepo;
	
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if (findMember.isPresent())
			return findMember.get();
		else return null;
	}
	
}
