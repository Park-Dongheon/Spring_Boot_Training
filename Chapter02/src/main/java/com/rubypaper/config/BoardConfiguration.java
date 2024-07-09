package com.rubypaper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rubypaper.jdbc.util.JDBCConnectionManager;

@Configuration // 사용자 정의 configuration boot 뒤에 생성 됨
public class BoardConfiguration {
	
	@Bean	// manager 인스턴스 객체 리턴 - 자동으로 빈으로 등록
	JDBCConnectionManager getJDBCConnectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setUrl("jdbc:mysql://localhost:3306/musthave");
		manager.setUsername("scott");
		manager.setPassword("tiger");
		return manager;
	}
}