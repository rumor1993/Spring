package com.naver.myhome1.sample4;

public class MessageBeanKo implements MessageBean{
	@Override
	public void sayHello(String name) {
		System.out.println("�Ʒ��ϼ���!" + name );
	}
	
	public MessageBeanKo() {
		System.out.println("�̰��� MessageBeanEn ������ �Դϴ�.");
	}
}
