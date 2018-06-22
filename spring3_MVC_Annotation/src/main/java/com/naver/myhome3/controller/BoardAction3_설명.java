package com.naver.myhome3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome3.model.BbsBean;

//@Controller
public class BoardAction3_설명 {

	@RequestMapping(value = "/login_ok3.do", method = RequestMethod.POST)
	public ModelAndView Board_write(@ModelAttribute("hoho") BbsBean bbs // commend 객체
	) throws Exception {
		ModelAndView mv = new ModelAndView("list3");

		return mv;

		/*
		 * String id = request.getParameter("id"); String pass =
		 * request.getParameter("pass");
		 * 
		 * BbsBean bbs = new BbsBean(); bbs.setId(id); bbs.setPass(pass);
		 * 
		 * command 객체를 사용하면 이전에 사용되었던 위의 코드들 기능을 하게 되어 필요 없게 됩니다.
		 * 
		 * @ModelAttribute를 사용합니다.
		 * 예) @ModelAttribute("hoho") BbsBean bbs
		 * view 페이지에서 "hoho" 라는 이름으로 사용하면 됩니다.
		 * 
		 *  <th>ID</th><td>${hoho.id}</td>
		 * 
		 */

	}
}
