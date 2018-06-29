package com.naver.myhome6.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.myhome6.dao.MemberService;

@Controller
public class MemberAction {
	@Autowired
	private MemberService memberService;
	// ������ �ʿ��� ���
	// private String saveFolder="C:/Program Files/Apache Software Foundation/Tomcat 8.5 /......
	
	private String saveFolder="C:\\Users\\user1\\Desktop\\spring7_mvc_member\\src\\main\\webapp\\resources\\upload";
	
	/* �α��� �� �� */
	@RequestMapping(value="/member_login.nhn")
	public String member_login(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return "member/loginForm";
		// member ������ member_login.jsp �� ������ ����
	}
	
}