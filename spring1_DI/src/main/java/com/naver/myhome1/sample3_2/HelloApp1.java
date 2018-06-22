
package com.naver.myhome1.sample3_2;
/*
	디자인패턴 이용하기
	Factory 패턴을 이용하는 방법으로 클라이언트에서 사용할 객체 생성을 캡슐화하여
	메서드의 매개변수에 따라 객체를 생성합니다.
	==> 내가 필요한 객체를 직접 생성하지 않고 단지 어떤 객체가 필요한지 BeanFactory에 요청하면
	BeanFactory가 생성하여 넘겨줍니다.
*/

import org.springframework.context.support.ClassPathXmlApplicationContext;
// 스프링을 이용하여 클래스 사이의 결합 상태를 느슨하게 만듭니다.
public class HelloApp1 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("com/naver/myhome1/sample3_2/applicationContext.xml");
		
		/*
		 Spring 컨테이너 구동
		  환경 설정 파일인 applicationContext.xml을 로딩하여 스프링 컨테이너 중 하나인
		 ClassPathXmlApplicationContext 객체가 생성되어 스프링 컨테이너가 구동합니다.
		  스프링 컨테이너는 applicationContext.xml에 설정된 빈을 생성합니다. 
		*/
		
		MessageBean bean1 = (MessageBean) ctx.getBean("m");
		MessageBean bean2 = (MessageBean) ctx.getBean("m");
		MessageBean bean3 = (MessageBean) ctx.getBean("m");
		MessageBean bean4 = (MessageBean) ctx.getBean("m");
		ctx.close();
	}
}
