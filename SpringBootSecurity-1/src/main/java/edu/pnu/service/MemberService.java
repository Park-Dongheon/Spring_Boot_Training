package edu.pnu.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

//회원 정보를 처리하는 서비스 클래스, 회원 가입 시 비밀번호를 암호화하고 회원 활성화 상태로 만든 후, 데이터베이스에 저장하는 기능을 담당
@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepo;
	private final PasswordEncoder passwordEncoder;
	private final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	
	@Transactional
	public void save(Member member) {
		validateMember(member);
		checkDuplicateMember(member);
		// 회원의 비밀번호를 인코딩(암호화), 평문 비밀번호를 암호화된 형태로 변환
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setEnabled(true);
		try {
			memberRepo.save(member);
			logger.info("Member saved Successfully: {}", member.getUsername());
		} catch (DataAccessException e) {
			logger.error("Error saving member", e);
			throw new ServiceException("Failed to save member", e);
		}
	}
	
	// 유효성 검사
	private void validateMember(Member member) {
		if (member.getUsername() == null || member.getUsername().isEmpty()) {
			throw new IllegalArgumentException("Username cannot be empty");
		}
		if (member.getPassword() == null || member.getPassword().isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
	}
	
	// 중복성 검사
	private void checkDuplicateMember(Member member) {
		if (memberRepo.existsById(member.getUsername())) {
			throw new DuplicateMemberException("Username already exists: " + member.getUsername());
		}
	}
	
	
	// Define custom exceptions
	public static class ServiceException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ServiceException(String message, Throwable cause) {
			super(message, cause);
		}
	}
	
	public static class DuplicateMemberException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DuplicateMemberException(String message) {
			super(message);
		}
	}
	
}
