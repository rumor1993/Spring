package com.naver.myhome6.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome6.model.BbsBean;

/*
 @Component�� �̿��ؼ� ������ ���ͳ��̳ʰ� �ش� Ŭ���� ��ü�� �����ϵ��� ������ �� ������
   ��� Ŭ������ @Component�� �Ҵ��ϸ� � Ŭ������ � ������ �����ϴ��� �ľ��ϱ�
   ��ƽ��ϴ�. ������ �����ӿ�ũ������ �̷� Ŭ�������� �з��ϱ� ���ؼ� @Component�� ����Ͽ�
   ������ ���� �� ���� �ֳ����̼��� �����մϴ�.
   
   1. @Controller - ������� ��û�� �����ϴ� Controller Ŭ����
   2. @Repository - ������ ���̽� ������ ó���ϴ� DAO Ŭ����
   3. @Service - �����Ͻ� ������ ó���ϴ� Service Ŭ����
 */

@Repository
public class BbsDAOImpl  {
		
		@Autowired
		private SqlSessionTemplate sqlsession;
		
		/* �ڷ�� ���� */
		public void insertBbs(BbsBean bbsbean) throws Exception{
			sqlsession.insert("bbs.insert", bbsbean);
		}
		
}
