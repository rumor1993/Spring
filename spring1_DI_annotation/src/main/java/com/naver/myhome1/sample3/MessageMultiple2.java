package com.naver.myhome1.sample3;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageMultiple2{
	
	@Autowired
	// 해당 타입에 할당할 수  있는 빈 객체를 찾아서 자동 주입 됩니다.
	// 반드시 주입할 의존 객체가 존재해야 합니다.
	private MessageBean messagebean;
	
	public MessageMultiple2() {
		System.out.println("MessageMultiple 생성자 입니다.");
	}
	
	public void print() {
		messagebean.sayHello("Spring");
	}
}
