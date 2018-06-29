package com.naver.myhome7.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.myhome7.dao.MemberService;

@Controller
public class MemberAction {
	private MemberService memberService;
	// 배포시 필요한 경로
	// private String saveFolder="C:/Program Files/Apache Software Foundation/Tomcat 8.5 /......
	
	private String saveFolder="C:\\Users\\user1\\Desktop\\spring7_mvc_member\\src\\main\\webapp\\resources\\upload";
	
	/* 로그인 폼 뷰 */
	@RequestMapping(value="/member_login.nhn")
	public String member_login(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return "member/member_login";
		// member 폴더의 member_login.jsp 뷰 페이지 실행
	}
	
}