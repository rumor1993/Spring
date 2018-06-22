
package com.naver.myhome1.sample6;
/*
	���������� �̿��ϱ�
	Factory ������ �̿��ϴ� ������� Ŭ���̾�Ʈ���� ����� ��ü ������ ĸ��ȭ�Ͽ�
	�޼����� �Ű������� ���� ��ü�� �����մϴ�.
	==> ���� �ʿ��� ��ü�� ���� �������� �ʰ� ���� � ��ü�� �ʿ����� BeanFactory�� ��û�ϸ�
	BeanFactory�� �����Ͽ� �Ѱ��ݴϴ�.
*/

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// �������� �̿��Ͽ� Ŭ���� ������ ���� ���¸� �����ϰ� ����ϴ�.
public class HelloApp1 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/naver/myhome1/sample6/applicationContext.xml");
		
		CollectionBean bean = ctx.getBean("collectionBean",CollectionBean.class);
		Properties prop = bean.getAddressList();
		
		Set<Object> set = prop.keySet();
		Iterator<Object> it = set.iterator();
		
		while(it.hasNext()) {
			String i = (String) it.next();
			System.out.println(i + ":" + prop.getProperty(i));
		}
		
		// Properties���� Ű���� �����ɴϴ�.
		// Set<String> Keys = addressList.stringPropertyNames();
		ctx.close();
		
		
		
		
	
		

			
			
		
	}
}
