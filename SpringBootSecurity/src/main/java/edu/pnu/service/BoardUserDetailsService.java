package edu.pnu.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// memRepo에서 사용자 정보를 검색해서
		Member member = memberRepo.findById(username).orElseThrow(() -> new UsernameNotFoundException("Not Found"));
		// 검색된 사용자 정보를 console에 출력해서 확인
		System.out.println(member);
		
		// UserDetails 타입의 객체를 생성해서 리턴 (o.s.s.core.userdetails.User)
		// 여기에서 리턴된 User 객체와 로그인 요청 정보를 비교한다.
		return new User(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
