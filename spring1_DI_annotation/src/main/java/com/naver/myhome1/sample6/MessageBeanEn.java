package com.naver.myhome1.sample6;

import org.springframework.stereotype.Component;

@Component("en")
public class MessageBeanEn implements MessageBean {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello!" + name);
	}

	public MessageBeanEn() {
		System.out.println("�̰��� MessageBeanEn ������ �Դϴ�.");
	}
}
