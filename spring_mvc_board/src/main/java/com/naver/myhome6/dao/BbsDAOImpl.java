package com.naver.myhome6.dao;

import java.util.List;
import java.util.Map;

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
		public int getListCount() throws Exception{
			return sqlsession.selectOne("bbs.count");
		}
		public List<BbsBean> getBbsList(int page) throws Exception {
			return sqlsession.selectList("bbs.list",page);
		}
		public BbsBean getBbsCont(int num) throws Exception {
			return sqlsession.selectOne("bbs.cont",num);
		}
		public void bbsHit(int num) throws Exception {
			sqlsession.update("bbs.hit",num);
		}
		public void editBbs(BbsBean bbsbean) throws Exception {
			sqlsession.update("bbs.edit",bbsbean);
		}
		public void deleteBbs(int bbs_num) throws Exception {	
			sqlsession.delete("bbs.del",bbs_num);
		}
		public int getListCount3(Map m) throws Exception {
			return sqlsession.selectOne("bbs.findcount",m);
		}
		public List<BbsBean> getBbsList3(Map m) throws Exception {
			return sqlsession.selectList("bbs.find", m);
		}
		public void refEdit(BbsBean bbsbean) throws Exception {
		
		}
		public void bbsReplyOk(BbsBean bbsbean) throws Exception {
			sqlsession.insert("bbs.reply",bbsbean);
			
		}
		public List<BbsBean> getBbsListview(Map m) throws Exception {
			return sqlsession.selectList("bbs.listview",m);
	}
}
