package com.naver.myhome1.sample3_2;

public class MessageBeanEn implements MessageBean {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello!" + name);
	}

	public MessageBeanEn() {
		System.out.println("이곳은 MessageBeanKo 생성자 입니다.");
	}
}
