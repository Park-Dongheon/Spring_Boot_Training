package com.rubypaper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BoardControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHello() throws Exception {
		mockMvc.perform(get("/hello").param("name", "둘리"))		// HTTP 요청을 모의(Mock)하여 실행하는 데 사용, 컨트롤러의 특정 엔드포인트에 대한 테스트를 수행 가능
		.andExpect(status().isOk())		// HTTP 상태 코드 200(OK) 기대
		.andExpect(content().string("Hello : 둘리"))		// andExpect() : Spring MVC Test 프레임워크에서 HTTP 요청의 결과를 검증하는 데 사용
		.andDo(print());	// 요청에 대한 추가 작업을 수행하는 데 사용, 요청 및 응답의 정보를 로깅하거나, 특정 작업을 수행하도록 가능, 요청 및 응답 정보를 콘솔에 출력
	}
	
//	@Autowired
//	private TestRestTemplate restTemplate;	// 특정 URL로 서버에 요청을 전달 가능, 응답 결과도 검증 가능
//	
//	@Test
//	public void testHello() throws Exception {
//		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
//		assertEquals("Hello : 둘리", result);		// JUnit 테스트 프레임워크에서 제공하는 메서드, 두 값이 동일한지 비교하여 테스트를 검증
//	}
//	
//	@Test
//	public void testGetBoard() throws Exception {
//		BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
//		assertEquals("테스터", board.getWriter());
//	}
	
}
