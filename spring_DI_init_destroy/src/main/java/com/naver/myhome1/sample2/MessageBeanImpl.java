package com.naver.myhome1.sample2;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("m")
public class MessageBeanImpl implements MessageBean {
	
	private String name;
	private String greeting;
	@Autowired
	private Outputter outputter;
	
	
	@PostConstruct // 객체를 생성한 후에 init-method 속성으로 지저된 이메서드를 호출합니다. 
					//init-method="initMethod"에 해당하는 annotation
	public void initMethod() {
		System.out.println(" MessageBeanImpl의 init-method()를 실행합니다. ");
	}
	// 생성자 호출을 통하여 name="Spring" 할당합니다.
	public MessageBeanImpl() {
		this.name = "홍길동";
		System.out.println("1. MessageBeanImpl 생성자 입니다.");
	}// 생성자 정의
	@Override
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
	
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
		System.out.println("5. MessageBeanImpl의 setOutputter()에서 값 저장");
	}	//setter DI 설정 (스프링용어)
	
	
	

}
