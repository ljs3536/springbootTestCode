package ljs.spring.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ljs.spring.test.webservice.springboot.dto.HelloResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
public class HelloResponseDtoTest {

	@Test
	void lombokTest() {
		
		String name = "test";
		int amount = 1000;
		
		HelloResponseDto dto = new HelloResponseDto(name,amount);
		
		assertThat(dto.getName()).isEqualTo(name);
		assertThat(dto.getAmount()).isEqualTo(amount);
		/*	assertThat : assertj라는 테스트 검증 라이브러리의 검증 메소드
		 * 
		 * 	isEqualTo : 검증하고 싶은 대상을 메소드 인자로 받는다.
		 * 				메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.
		 * */
	}

}
