package com.naver.myhome3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.naver.myhome3.model.BbsBean;
 // Controller ����
	/* 
	 * 1. Controller (interface)
	 * 2. AbstractController
	 * 3. AbstractCommandController
	 * 4. MultiActionController
	 * 		���� interface / class�� ����Ͽ� Controller�� �ۼ��մϴ�.
	 * 
	 * ���� �⺻�� �Ǵ� Controller �ۼ��մϴ�.
	 * AbstractController�� ��ӹް� �������̵� �ؼ� �ڵ带 �����մϴ�.
	 * ����� �ڷ����� ModelAndView�� view���� ����� ��ü�� view�� ���� id�� ��й�ȣ�� ������ �� return �մϴ�.
	 */

public class BbsWriteController extends AbstractController{
	
	private BbsBean bbsBean;
	
	public BbsBean getBbsBean() {
		return bbsBean;
	}

	public void setBbsBean(BbsBean bean) {
		this.bbsBean = bean;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ModelAndView : Controller ó�� ��� �� ������ view�� view�� ������ ���� �����մϴ�.
		
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		String pass =request.getParameter("pass");
		
		bbsBean.setId(id);
		bbsBean.setPass(pass);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("list");
		// �� ����� ���� �ǹ̷� ModelAndView mv = new ModelAndView ("list");
		// ViewResolver�� ���� webapp/view/list.jsp�� ã�ư��� �˴ϴ�.
		
		mv.addObject("bkey",bbsBean);
		// addObject�� ù ��° �Ű������� Ű(key), �� ��° �Ű������� ��(value)�� �ǹ��մϴ�
		// bbsBean�� id�� ��й�ȣ�� ����Ǿ� �ִ� DTO ��ü�Դϴ�.
		// ModelAndView�� ���� ������ ���� webapp/view/list.jsp���� EL�� ��Ÿ�� �� �ֽ��ϴ�.
		// ID : #{bkey.id} <br>
		// ��й�ȣ : ${bkey.pass} <br>
		
		return mv;
	}
}
