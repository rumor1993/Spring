package com.naver.myhome1.sample2;

public class MessageBeanEn implements MessageBean{
	@Override
	public void sayHello(String name) {
		System.out.println("Hello!" + name );
	}
}
