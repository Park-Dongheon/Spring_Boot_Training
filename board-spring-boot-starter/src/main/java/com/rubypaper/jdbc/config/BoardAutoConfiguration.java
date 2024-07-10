package com.rubypaper.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rubypaper.jdbc.util.JDBCConnectionManager;


@Configuration	// BoardAutoConfiguration 클래스가 스프링의 Java 기반 구성 클래스임을 나타냄
public class BoardAutoConfiguration {
	
	@Bean	// manager 인스턴스 객체 리턴 - 자동으로 빈으로 등록, getJDBCConnectionManager() 메서드는 스프링 빈으로 등록
	@ConditionalOnMissingBean	// 해당 빈이 이미 정의되어 있지 않은 경우에만 이 메서드를 통해 빈을 등록
	JDBCConnectionManager getJDBCConnectionManager() {	// 메스드 내에서 JDBCConnectionManager 객체를 생성하고 초기화
		JDBCConnectionManager manager = new JDBCConnectionManager();
		// setUrl(), setUsername(), setPassword() 메서드를 호출하여 데이터베이스 연결에 필요한 정보를 설정
		manager.setUrl("jdbc:mysql://localhost:3306/musthave");
		manager.setUsername("scott");
		manager.setPassword("tiger");
		return manager;	// 설정된 JDBCConnectionManager 객체를 반환하여 스프링 컨테이너에 등록
	}
	
    @Bean
    Connection getDataConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/musthave";
        String username = "scott";
        String password = "tiger";
        return DriverManager.getConnection(url, username, password);
    }
    
}
