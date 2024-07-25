package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final OAuth2SuccessHandler successHandler;
	private final MemberRepository memberRepo;
	private final AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());

		http.httpBasic(basic -> basic.disable());
		
		http.sessionManagement(ssm -> ssm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		// 스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터를 추가
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
//		http.cors(cors -> cors.configurationSource(corsSource()));
		
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepo), AuthorizationFilter.class);
		
		http.oauth2Login(oauth2 -> oauth2.successHandler(successHandler));

		return http.build();
	}
	
//	private CorsConfigurationSource corsSource() {
//		CorsConfiguration config = new CorsConfiguration();
//		
//		config.addAllowedOriginPattern(CorsConfiguration.ALL);		// 요청을 허용할 서버
//		config.addAllowedMethod(CorsConfiguration.ALL);				// 요청을 허용할 Method
//		config.addAllowedHeader(CorsConfiguration.ALL);				// 요청을 허용할 Header
//		config.setAllowCredentials(true);							// 요청/응답에 자격증명정보 포함을 허용
//																	// true 인 경우 addAllowedOrigin("*")는 사용 불가 -> Pattern 으로 변경
//		config.addExposedHeader(CorsConfiguration.ALL);				// Header 에 Authorization 을 추가하기 위해서는 필요
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", config);			// 위 설정을 적용할 Rest 서버의 URL 패턴 설정
//		return source;
//	}
}
