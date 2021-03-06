package com.naver.myhome1.sample5;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 추가합니다. - "m3" 는 빈의 이름입니다.
// 즉, @Component에 들어가는 속성값은 빈의 이름입니다.
// getBean을 호출할 때 이 이름을 사용합니다.

@Component("m3")
public class MessageBeanImpl implements MessageBean {
	
	private String name;
	private String greeting;
	private Outputter outputter;
	
	// 생성자 호출을 통하여 name="Spring" 할당합니다.
	public MessageBeanImpl() {
		name = "홍길동";
		System.out.println("1. MessageBeanImpl 생성자 입니다.");
	}// 생성자 정의


	public void sayHello() {
		String message = greeting + name + "!";
		System.out.println("6. MessageBeanImpl의 sayHello() 호출 : " + message);
		
		try {
			outputter.output(message);
		}catch(IOException ie) {
			ie.printStackTrace();
		}
	}

	public String getGreeting() {
		return greeting;
	}
	
	//setGreeting 메서드 호출을 통하여 greeting = 안녕하세요! 할당합니다.
		
	

	public void setGreeting(String greetign) {
		this.greeting = greetign;
		System.out.println("4. MessageBeanImpl의 setGreeting()에서 값 저장");
	}	// setter DI 설정 (스프링용어)
	
				
				// 해당 타입에 할당할 수 있는 빈 객체를 찾아서 자동 주입됩니다.
	@Autowired  // 반드시 주입할 의존 객체가 존재해야 합니다.
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
		System.out.println("5. MessageBeanImpl의 setOutputter()에서 값 저장");
	}	//setter DI 설정 (스프링용어)
	
	

}
