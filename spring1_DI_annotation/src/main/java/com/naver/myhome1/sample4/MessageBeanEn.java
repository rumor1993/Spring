package com.naver.myhome1.sample4;

public class MessageBeanEn implements MessageBean {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello!" + name);
	}

	public MessageBeanEn() {
		System.out.println("이곳은 MessageBeanEn 생성자 입니다.");
	}
}
