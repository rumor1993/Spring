package com.naver.myhome3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.myhome3.model.BbsBean;

//@Controller
public class BoardAction2_���� {

	@RequestMapping(value="/login_ok2.do",method=RequestMethod.POST)
	public String Board_write(BbsBean bbs) throws Exception {
			
		/*  
	 	commend ��ü��?
	 	�������� ��û �Ķ������ ���� command ��ü�� ����ִ� ����� �����մϴ�.
	 	HTTP ��û �Ķ���� ���� ���� ���� �� ���Ǵ� ��ü�Դϴ�.
	 	command ��ü�� ���� Ŭ������ ������ ������ �ڹٺ� ��Ģ�� ���� �˸��� setter �޼��常 �����ϸ� �˴ϴ�.
	 	=> �Ķ������ �̸��� property�� �̸��� ������ �˴ϴ�.
	 	���� loginForm.jsp ���� <input type=text name=id> ���� �Է��� ���� �����ϱ� ���� 
	 	setID() �޼��尡 �����ϸ� �˴ϴ�.
	 	
	 	 String id = request.getParameter("id");
	 	 String pass = request.getParameter("pass");
	 	 
	 	BbsBean bbs = new BbsBean();
		bbs.setId(id);
		bbs.setPass(pass);
		
		command ��ü�� ����ϸ� ������ ���Ǿ��� ���� �ڵ�� ����� �ϰ� �Ǿ� �ʿ� ���� �˴ϴ�.
		
		command ��ü�� ����� ���� JSP���� ����ϱ� ���ؼ��� 
		${bbsBean.id} �Ǵ� ${bbsBean.pass}�� ����մϴ�.
		��, list2.jsp ���� 
		command ��ü�� Ŭ���� �̸� (ù ���ڸ� �ҹ��ڷ� �ٲߴϴ�.) �� 
		������ �Ӽ��̸��� ����ؼ� command ��ü�� �信 �����մϴ�.
		���� command ��ü�� Ŭ���� �̸��� BbsBean�� ��� JSP �ڵ忡���� 
		bbsBean�̶�� �̸��� ����ؼ� command ��ü�� ������ �� �ְ� �˴ϴ�.
	*/
		
		return "list2";
		
		
	}
}
