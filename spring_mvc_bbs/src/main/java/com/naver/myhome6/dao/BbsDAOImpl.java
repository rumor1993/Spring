package com.naver.myhome6.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome6.model.BbsBean;

/*
 @Component를 이용해서 스프링 컨터네이너가 해당 클래스 객체를 생성하도록 설정할 수 있지만
   모든 클래스에 @Component를 할당하면 어떤 클래스가 어떤 역할을 수행하는지 파악하기
   어렵습니다. 스프링 프레임워크에서는 이런 클래스들을 분류하기 위해서 @Component를 상속하여
   다음과 같은 세 개의 애노테이션을 제공합니다.
   
   1. @Controller - 사용자의 요청을 제어하는 Controller 클래스
   2. @Repository - 데이터 베이스 연동을 처리하는 DAO 클래스
   3. @Service - 비지니스 로직을 처리하는 Service 클래스
 */

@Repository
public class BbsDAOImpl  {
		
		@Autowired
		private SqlSessionTemplate sqlsession;
		
		/* 자료실 저장 */
		public void insertBbs(BbsBean bbsbean) throws Exception{
			sqlsession.insert("bbs.insert", bbsbean);
		}
		
}
