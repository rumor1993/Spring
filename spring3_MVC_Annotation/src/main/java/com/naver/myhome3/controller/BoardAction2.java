package com.naver.myhome3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.myhome3.model.BbsBean;

@Controller
public class BoardAction2 {

	@RequestMapping(value="/login_ok2.do",method=RequestMethod.POST)
	public String Board_write(
			BbsBean bbs // commend ��ü
			) throws Exception {
			
		
		return "list2";
		
		
	}
}
