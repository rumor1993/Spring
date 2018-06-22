
package com.naver.myhome1.sample3_2;
/*
	���������� �̿��ϱ�
	Factory ������ �̿��ϴ� ������� Ŭ���̾�Ʈ���� ����� ��ü ������ ĸ��ȭ�Ͽ�
	�޼����� �Ű������� ���� ��ü�� �����մϴ�.
	==> ���� �ʿ��� ��ü�� ���� �������� �ʰ� ���� � ��ü�� �ʿ����� BeanFactory�� ��û�ϸ�
	BeanFactory�� �����Ͽ� �Ѱ��ݴϴ�.
*/

import org.springframework.context.support.ClassPathXmlApplicationContext;
// �������� �̿��Ͽ� Ŭ���� ������ ���� ���¸� �����ϰ� ����ϴ�.
public class HelloApp1 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/naver/myhome1/sample3_2/applicationContext.xml");
		
		/*
		 Spring �����̳� ����
		  ȯ�� ���� ������ applicationContext.xml�� �ε��Ͽ� ������ �����̳� �� �ϳ���
		 ClassPathXmlApplicationContext ��ü�� �����Ǿ� ������ �����̳ʰ� �����մϴ�.
		  ������ �����̳ʴ� applicationContext.xml�� ������ ���� �����մϴ�. 
		*/
		
		MessageBean bean1 = (MessageBean) ctx.getBean("m");
		MessageBean bean2 = (MessageBean) ctx.getBean("m");
		MessageBean bean3 = (MessageBean) ctx.getBean("m");
		MessageBean bean4 = (MessageBean) ctx.getBean("m");
		ctx.close();
	}
}
