package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.controller.LoginController;
import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());				// CSRF 보호 비활성화
		
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyAuthority("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasAnyRole("ADMIN")
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin -> frmLogin.disable());	// Form 을 이용한 로그인을 사용하지 않겠다는 설정
		
		http.httpBasic(basic -> basic.disable());		// HTTP Basic 인증 방식을 사용하지 않겠다는 설정
		
		// 세션을 유지하지 않겠다고 설정 -> Url 호출 뒤 응답할 때 까지는 유지되지만 응답 후 삭제된다는 의미. // localhost:8080/ -> index 호출 // localhost:8080/login -> 404 에러, // localhost:8080/member -> 403 에러
		http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// JWT는 세션을 유지하지 않음, 세션 생성 시 JWT 토큰을 전달하고 세션 연결을 삭제
		
		//스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터를 추가한다.
//		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))	
		
		
		
		return http.build();
	}
}
