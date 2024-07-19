package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration		// 이 클래스가 설정 클래스라고 정의(IoC 컨테이너에 로드)
@EnableWebSecurity	// 스프링 시큐리티 적용에 필요한 필터 객체들 자동 생성
public class SecurityConfig {
	
	@Bean			// 이 메서드가 리턴 하는 객체를 IoC 컨테이너에 등록하라는 지시
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// SecurityFilterChain 객체를 생성해서 Bean 으로 등록하면 기본 로그인 화면이 나타나지 않는다.
		// 기본 로그인 화면을 사용하거나 사용자가 작성한 로그인 화면을 사용하겠다는 설정을 해야한다
		
		// Http 요청에 대한 보안 설정, authorizeHttpRequests() 메소드를 사용하여 각 요청 경로에 대한 접근 제어 규칙을 정의
		http.authorizeHttpRequests(security -> security							// 람다 표현식, HttpSecurity 객체의 설정을 정의
				.requestMatchers("/member/**").authenticated()					// /member/ 로 시작하는 모든 경로에 대해 접근 제어를 설정, authenticated() 메소드로 로그인된 사용자만 접근 가능
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")	// /manager/ 로 시작하는 모든 경로에 대해 접근 제어를 설정, hasAnyRole("MANAGER", "ADMIN") 메소드로 이 경로에 접근하려면 사용자가 'MANAGER', 'ADMIN' 역학(Role)을 가지고 있어야 함
																				// 실제로는 'ROLE_MANAGER' 또는 'ROLE_ADMIN' 역할을 확인
				.requestMatchers("/admin/**").hasAnyRole("ADMIN")				// /admin/ 로 시작하는 모든 경로에 대해 접근 제어를 설정, hasAnyRole("ADMIN") 메소드로 이 경로에 접근하려면 사용자가 'ADMIN' 역할(Role)을 가지고 있어야 함
																				// 실제로는 'ROLE_ADMIN' 역할을 확인
				.anyRequest().permitAll()										// 위에 명시되지 않은 모든 요청 경로에 모든 사용자에게 접근을 허용
				);	
		
		
		// SpringBoot가 제공해주는 로그인 사용하겠다는 설정
		http.formLogin(formLogin -> formLogin
				.loginPage("/login")		// 사용자 정의 로그인 페이지 경로 설정(기본 로그인 화면을 사용하려면 삭제)
				.defaultSuccessUrl("/loginSuccess", true)	// 로그인 성공 시 Redirect URL
				.permitAll()	// 로그인 페이지는 인증 없이 접근 가능	
		);
		
		// CSRF 보호 비활성화(사이트간 요청 위조)
		http.csrf(csrf -> csrf.disable()
		);
		
		// 애플리케이션의 보안 설정에서 예외 처리를 구성하는 데 사용, 인증 및 권한 부여 예외를 처리 가능
		http.exceptionHandling(exceptionHandling -> exceptionHandling
				.accessDeniedPage("/accessDenied")		// 접근 거부 시 Redirect 할 페이지
		);
		
		http.logout(logout -> logout
				.invalidateHttpSession(true)		// 현재 브라우저와 연결된 세션 강제 종료
				.deleteCookies("JESSIONID")			// 세션 아이디가 저장된 쿠키 삭제
				.logoutSuccessUrl("/index")			// 로그아웃 후 이동할 URL 지정
		);
		
		
		return http.build();
	}
	
	 @Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

}
