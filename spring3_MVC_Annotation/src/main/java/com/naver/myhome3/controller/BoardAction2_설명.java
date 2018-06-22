package com.naver.myhome3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.myhome3.model.BbsBean;

//@Controller
public class BoardAction2_설명 {

	@RequestMapping(value="/login_ok2.do",method=RequestMethod.POST)
	public String Board_write(BbsBean bbs) throws Exception {
			
		/*  
	 	commend 객체란?
	 	스프링은 요청 파라미터의 값을 command 객체에 담아주는 기능을 제공합니다.
	 	HTTP 요청 파라미터 값을 전달 받을 때 사용되는 객체입니다.
	 	command 객체로 사용될 클래스에 제한은 없으며 자바빈 규칙에 맞춰 알맞은 setter 메서드만 제공하면 됩니다.
	 	=> 파라미터의 이름과 property의 이름이 같으면 됩니다.
	 	예로 loginForm.jsp 에서 <input type=text name=id> 에서 입력한 값을 저장하기 위해 
	 	setID() 메서드가 존재하면 됩니다.
	 	
	 	 String id = request.getParameter("id");
	 	 String pass = request.getParameter("pass");
	 	 
	 	BbsBean bbs = new BbsBean();
		bbs.setId(id);
		bbs.setPass(pass);
		
		command 객체를 사용하면 이전에 사용되었던 위의 코드들 기능을 하게 되어 필요 없게 됩니다.
		
		command 객체에 저장된 값을 JSP에서 사용하기 위해서는 
		${bbsBean.id} 또는 ${bbsBean.pass}로 사용합니다.
		즉, list2.jsp 에서 
		command 객체의 클래스 이름 (첫 글자를 소문자로 바꿉니다.) 과 
		동일한 속성이름을 사용해서 command 객체를 뷰에 전달합니다.
		예로 command 객체의 클래스 이름이 BbsBean인 경우 JSP 코드에서는 
		bbsBean이라는 이름을 사용해서 command 객체에 접근할 수 있게 됩니다.
	*/
		
		return "list2";
		
		
	}
}
