package com.naver.myhome3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome3.model.BbsBean;

//@Controller
public class BoardAction3_���� {

	@RequestMapping(value = "/login_ok3.do", method = RequestMethod.POST)
	public ModelAndView Board_write(@ModelAttribute("hoho") BbsBean bbs // commend ��ü
	) throws Exception {
		ModelAndView mv = new ModelAndView("list3");

		return mv;

		/*
		 * String id = request.getParameter("id"); String pass =
		 * request.getParameter("pass");
		 * 
		 * BbsBean bbs = new BbsBean(); bbs.setId(id); bbs.setPass(pass);
		 * 
		 * command ��ü�� ����ϸ� ������ ���Ǿ��� ���� �ڵ�� ����� �ϰ� �Ǿ� �ʿ� ���� �˴ϴ�.
		 * 
		 * @ModelAttribute�� ����մϴ�.
		 * ��) @ModelAttribute("hoho") BbsBean bbs
		 * view ���������� "hoho" ��� �̸����� ����ϸ� �˴ϴ�.
		 * 
		 *  <th>ID</th><td>${hoho.id}</td>
		 * 
		 */

	}
}
