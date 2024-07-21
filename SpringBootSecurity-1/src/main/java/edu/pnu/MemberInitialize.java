package edu.pnu;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class MemberInitialize implements ApplicationRunner {
	
	// memberRepo 는 Member Entity 와 관련된 JpaRepository or CrudRepository 인터페이스의 인스턴스  
	private final MemberRepository memberRepo;
	// PasswordEncoder 인터페이스를 구현한 객체
	private final PasswordEncoder encoder;
	
	/* Spring boot Application 에서 CommandLineRunner 인터페이스를 구현할 때 사용,
	 * Application 이 시작될 때 자동으로 실행
	 * ApplicationArguments 는 Application 이 시작될 때 전달된 인수(arguments)를 포함하는 객체
	 * Application 이 초기화된 후 추가적인 설정이나 작업을 수행하기 위해 사용
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		// Spring Data JPA를 사용하여 데이터베이스에 새로운 Entity 를 저장
		// save 메서드는 주어진 Entity 객체를 데이터베이스에 저장하거나, 이미 존재하는 경우 업데이트
		memberRepo.save(Member.builder()	// Member 클래스가 빌더 패턴을 사용하여 객체를 생성하도록 설정된 경우, builder() 메서드는 새로운 빌더 객체를 반환
											// 빌더 패턴은 객체의 생성 과정을 유연하게 해주며, 특히 복잡한 객체를 생성할 때 유
				.username("member")
				.password(encoder.encode("abcd"))		// encoder.encode("abcd")는 비밀번호를 해시 처리
														// BCryptPasswordEncoder 와 같은 구현체를 사용하여 비밀번호를 안전하게 암호화
				.role(Role.ROLE_MEMBER).enabled(true)	// 사용자가 활성화되어 있는지 여부를 나타냄, 비활성화된 사용자는 로그인을 할 수 없슴
				.build()								// 빌더 패턴을 사용하여 Member 객체를 생성, 모든 설정된 필드를 포함한 최종 Member 객체가 반환됨
				);
		
		memberRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_MANAGER).enabled(true)
				.build()
				);
		
		memberRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_ADMIN).enabled(true)
				.build()
				);
		
	}
	
}
