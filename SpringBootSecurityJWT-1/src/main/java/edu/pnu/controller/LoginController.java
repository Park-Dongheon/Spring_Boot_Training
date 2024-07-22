package edu.pnu.controller;



import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	private final AuthenticationConfiguration authenticationConfiguration;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginProc(@ResponseBody Member member, HttpServletRequest request, HttpServletResponse response) {
		try {
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
		} catch (Exception e) {
			log.info(e.getMessage());		// “자격 증명에 실패하였습니다.” 로그 출력
		}
		
		return response.setStatus(HttpStatus.UNAUTHORIZED.value());		// 자격 증명에 실패하면 응답코드 리턴		
	}
}
