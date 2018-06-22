package com.naver.myhome4.controller;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome4.model.BoardBean;

@Controller
public class BoardAction {
	
	// 게시판 글쓰기 폼
	@RequestMapping(value = "/board_write.nhn")
	public String board_write() {
		System.out.println("=====================");
		return "board/board_write";
	}
	
	// 게시판 저장
	@RequestMapping(value = "/board_write_ok.nhn", method = RequestMethod.POST)
	public String board_write_ok(BoardBean board) {
		System.out.println("=====================");
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("WEB-INF/info-servlet.xml");
		board = ctx.getBean("sqlSession",BoardBean.class);
		
	
		ctx.close();
		
	return null;
		
	}
	
}