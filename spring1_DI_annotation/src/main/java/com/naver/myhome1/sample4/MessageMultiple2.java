package com.naver.myhome1.sample4;

import javax.annotation.Resource;

public class MessageMultiple2{
	@Resource(name="m3")
	// 해당 타입에 할당할 수  있는 빈 객체를 찾아서 자동 주입 됩니다.
	// 반드시 주입할 의존 객체가 존재해야 합니다.
	private MessageBean messagebean;
	// MessageBean 타입으로 messagebean을 선언하면 null 값이라서 
	// new MessageBean(); 을 따로 해주는게 아니라 @Autowired를 이용해서 인스턴스화 해준다.
	
	public MessageMultiple2() {
		System.out.println("MessageMultiple 생성자 입니다.");
	}
	
	public void print() {
		messagebean.sayHello("Spring");
	}
}
