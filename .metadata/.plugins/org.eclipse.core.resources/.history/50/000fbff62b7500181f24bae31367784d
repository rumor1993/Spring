
package com.naver.myhome1.sample6_map;
/*
	디자인패턴 이용하기
	Factory 패턴을 이용하는 방법으로 클라이언트에서 사용할 객체 생성을 캡슐화하여
	메서드의 매개변수에 따라 객체를 생성합니다.
	==> 내가 필요한 객체를 직접 생성하지 않고 단지 어떤 객체가 필요한지 BeanFactory에 요청하면
	BeanFactory가 생성하여 넘겨줍니다.
*/

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// 스프링을 이용하여 클래스 사이의 결합 상태를 느슨하게 만듭니다.
public class HelloApp1 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/naver/myhome1/sample6_map/applicationContext.xml");
		
		CollectionBean bean = ctx.getBean("collectionBean",CollectionBean.class);
		Map<String,Object> map = bean.getAddressList();
		
		Collection<String> set = map.keySet();
		// Key는 중복을 허용X 그래서 set을 사용한다.
		Iterator<String> it = set.iterator();
		
		Collection<Object> list =  map.values();
		// Value는 중복을 허용O 그래서 list를 사용한다.
		Iterator<Object> it2 = list.iterator();
		
		// 하지만 Collection은 List와 Set 모두를 포함하고 있기 때문에 사용 가능하다.
		
		while(it.hasNext()) {
			System.out.println(it.next() + " : " + it2.next());
		}
		ctx.close();
		
		
		
		
	
		

			
			
		
	}
}
