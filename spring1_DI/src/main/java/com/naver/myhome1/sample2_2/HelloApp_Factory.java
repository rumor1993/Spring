package com.naver.myhome1.sample2_2;
/*
	���������� �̿��ϱ�
	Factory ������ �̿��ϴ� ������� Ŭ���̾�Ʈ���� ����� ��ü ������ ĸ��ȭ�Ͽ�
	�޼����� �Ű������� ���� ��ü�� �����մϴ�.
	==> ���� �ʿ��� ��ü�� ���� �������� �ʰ� ���� � ��ü�� �ʿ����� BeanFactory�� ��û�ϸ�
	BeanFactory�� �����Ͽ� �Ѱ��ݴϴ�.
*/

public class HelloApp_Factory {
	public static void main(String[] args) {
		BeanFactory factory = new BeanFactory();
		MessageBean bean = (MessageBean) factory.getBean("MessageBeanEn");
		bean.sayHello("spring~");
		
	}
}
