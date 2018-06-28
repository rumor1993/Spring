package com.naver.myhome6.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome6.dao.MemberService;
import com.naver.myhome6.model.MemberBean;

@Controller
public class MemberAction2 {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "member_login.nhn")
	public String member_login() throws Exception {
		return "bbs/bbs_login";

	}

	@RequestMapping(value = "member_sign_up_ok.nhn", method = RequestMethod.POST)
	public ModelAndView member_sign_up(MemberBean member, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("bbs/bbs_login");

		// ȸ������ �ߺ�üũ
		MemberBean check = memberService.loginCheck(member);

		if (check != null) {
			if (member.getMember_email().equals(check.getMember_email())) {
				mv.addObject("modal", "�ߺ��� �̸��� �Դϴ� �ٸ� �̸��Ϸ� �ٽ� �������ּ���.");
				mv.addObject("message", "���� �޼���");
				System.out.println("�̸��� �ߺ�");
			} else if (member.getMember_id().equals(check.getMember_id())) {
				mv.addObject("modal", "�ߺ��� ���̵� �Դϴ� �ٸ� ���̵�� �ٽ� �������ּ���.");
				mv.addObject("message", "���� �޼���");
				System.out.println("���̵� �ߺ�");
			}
		} else {
			mv.addObject("modal", "ȯ�� �մϴ�. ȸ�����Կ� �����߽��ϴ�. �α����� ���ּ���.");
			mv.addObject("message", "���� �޼���");
			memberService.insertMember(member);
		}
		return mv;
	}	
	@RequestMapping(value = "member_login.nhn", method = RequestMethod.POST)
	public ModelAndView member_login_ok(MemberBean member,
			HttpServletRequest request
			) throws Exception {
		
		// DB�� �����Ͱ� �ִٸ� �̹� ȸ���̹Ƿ� �α��� ó��
		MemberBean check = memberService.loginCheck(member);
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(check != null) {
				if(check.getMember_pass() != member.getMember_pass()){
				mv.addObject("modal","��� ��ȣ�� �ٸ��ϴ�. �ٽ� �õ� ���ּ���");
				mv.addObject("message", "���� �޼���");
				System.out.println("�α��ν��� (��й�ȣ)");
				mv.setViewName("bbs/bbs_login");
			}else{
			System.out.println("�α��μ���");
			mv.setViewName("redirect:bbs_list.nhn");
			session.setAttribute("id", check.getMember_id());
			}
		}else {
				mv.addObject("modal","�̸����� �������� �ʽ��ϴ�. �ٽ� �õ� ���ּ���");
				mv.addObject("message", "���� �޼���");
				System.out.println("�α��ν��� (���̵�)");
				mv.setViewName("bbs/bbs_login");
			
		}
		return mv;
	}
}
