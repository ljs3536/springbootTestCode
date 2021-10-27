package ljs.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ljs.spring.test.webservice.springboot.web.HelloController;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
/*
 * 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다. 여기서는 SpringRunner라는 스프링 실행자 사용,
 * 스프링 부트테스트와 JUnit 사이에 연결자 역할을 한다.
 */
@WebMvcTest(controllers = HelloController.class)
/*
 * 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
 * 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
 * 단, @Service, @Component, @Repository 등은 사용할 수 없다.
 * 여기서는 컨트롤러만 사용
 */
class SpringbootTestApplicationTests {

	@Autowired //스프링이 관리하는 빈 주입
	private MockMvc mvc;
	/* - MockMvc 
	 * 웹 API를 테스트할 때 사용 
	 * 스프링 MVC 테스트의 시작점
	 * 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.
	 * */
	
	@Test
	public void helloReturn() throws Exception {
		String hello = "hello";

		mvc.perform(get("/hello"))	// MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
			.andExpect(status().isOk())	// mvc.perform의 결과 검증, HTTP Header의 Stautus검증
			.andExpect(content().string(hello));	// mvc.perform의 결과 검증, 응답 본문의 내용을 검증
	}
	
	@Test
	public void helloReturnDto() throws Exception{
		String name = "hello";
		int amount = 1000;
		
		mvc.perform(
				get("/hello/dto")
					/* param
					 * API를 테스트할 때 사용될 요청 파라미터를 설정 / 단, 값은 String만 허용
					 * 숫자/날짜 등의 데이터도 작성할 대는 문자열로 변경해야 가능
					 * */
					.param("name", name)						
					.param("amount", String.valueOf(amount)))
				.andExpect(status().isOk())
					/*	jsonPath
					 * 	JSON 응답값을 필드별로 검증할 수 있는 메소드
					 * 	$를 기준으로 필드명을 명시한다.
					 * */
				.andExpect(jsonPath("$.name", is(name)))
				.andExpect(jsonPath("$.amount", is(amount)));
	}

}
