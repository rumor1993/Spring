package com.naver.myhome1.sample1;
/*
	�� ���α׷��� �ڹٿ��� �Ϲ������� �� �� �ִ� �ڵ��Դϴ�.
	HelloApp Ŭ�������� MessageBeanEn Ŭ������ ���� �ν��Ͻ��� �����Ͽ�
	say �޼��带 ȣ���ϰ� �ֽ��ϴ�.
	�̷� ��츦 HelloApp Ŭ������ MessageBeanEn Ŭ������ ���ϰ� �����ϰ� �ִٰ� �մϴ�.
	==> �� Ŭ������ �ٸ� Ŭ������ �޼��带 ������ ���� �����Ѵٶ�� �մϴ�.
	��, Ŭ�������� ��������, ���ջ��°� ���ϴٰ� �� �� �ֽ��ϴ�.


	���� HelloApp Ŭ�������� MessageBeanEn Ŭ���� ��� �ٸ� Ŭ���� (MessageBeanKo)�� 
	����� ��쿡 (MessageBeanKo Ŭ������ MessageBeanEn Ŭ������ ���� ����� �����ϴ� �޼��尡 �����մϴ�.
	�� Ŭ������ �޼���� MessageBeanEn Ŭ���� �޼���� �ٸ� �̸����� ���ǰ� �ֽ��ϴ�.
	�׷��� MessageBeanKo Ŭ������ �����ϱ� ���ؼ��� �Ʒ��� 26-27���� ó�� �ڵ带 �����ؾ� �մϴ�.
	�̸� �ذ��ϱ� ���� �켱 �̸��� ���� �޼��带 ����ϴ� ��� (������ �̿�) ���� ����� ���ڽ��ϴ�.
*/

public class HelloApp {
	public static void main(String[] args) {
		MessageBeanEn en = new MessageBeanEn();
		MessageBeanKo ko = new MessageBeanKo();
		
		String name = "Spring";
		
		en.say(name);
		ko.sayHello(name);
	}
}
