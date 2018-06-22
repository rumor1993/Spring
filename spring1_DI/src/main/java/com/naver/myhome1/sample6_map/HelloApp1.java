
package com.naver.myhome1.sample6_map;
/*
	���������� �̿��ϱ�
	Factory ������ �̿��ϴ� ������� Ŭ���̾�Ʈ���� ����� ��ü ������ ĸ��ȭ�Ͽ�
	�޼����� �Ű������� ���� ��ü�� �����մϴ�.
	==> ���� �ʿ��� ��ü�� ���� �������� �ʰ� ���� � ��ü�� �ʿ����� BeanFactory�� ��û�ϸ�
	BeanFactory�� �����Ͽ� �Ѱ��ݴϴ�.
*/

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// �������� �̿��Ͽ� Ŭ���� ������ ���� ���¸� �����ϰ� ����ϴ�.
public class HelloApp1 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/naver/myhome1/sample6_map/applicationContext.xml");
		
		CollectionBean bean = ctx.getBean("collectionBean",CollectionBean.class);
		Map<String,Object> map = bean.getAddressList();
		
		Collection<String> set = map.keySet();
		// Key�� �ߺ��� ���X �׷��� set�� ����Ѵ�.
		Iterator<String> it = set.iterator();
		
		Collection<Object> list =  map.values();
		// Value�� �ߺ��� ���O �׷��� list�� ����Ѵ�.
		Iterator<Object> it2 = list.iterator();
		
		// ������ Collection�� List�� Set ��θ� �����ϰ� �ֱ� ������ ��� �����ϴ�.
		
		while(it.hasNext()) {
			System.out.println(it.next() + " : " + it2.next());
		}
		ctx.close();
		
		
		
		
	
		

			
			
		
	}
}
