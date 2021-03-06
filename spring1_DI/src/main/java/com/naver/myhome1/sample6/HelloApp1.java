
package com.naver.myhome1.sample6;
/*
	디자인패턴 이용하기
	Factory 패턴을 이용하는 방법으로 클라이언트에서 사용할 객체 생성을 캡슐화하여
	메서드의 매개변수에 따라 객체를 생성합니다.
	==> 내가 필요한 객체를 직접 생성하지 않고 단지 어떤 객체가 필요한지 BeanFactory에 요청하면
	BeanFactory가 생성하여 넘겨줍니다.
*/

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// 스프링을 이용하여 클래스 사이의 결합 상태를 느슨하게 만듭니다.
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
		
		// Properties에서 키값을 가져옵니다.
		// Set<String> Keys = addressList.stringPropertyNames();
		ctx.close();
		
		
		
		
	
		

			
			
		
	}
}
