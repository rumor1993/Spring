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

		// 회원가입 중복체크
		MemberBean check = memberService.loginCheck(member);

		if (check != null) {
			if (member.getMember_email().equals(check.getMember_email())) {
				mv.addObject("modal", "중복된 이메일 입니다 다른 이메일로 다시 가입해주세요.");
				mv.addObject("message", "오류 메세지");
				System.out.println("이메일 중복");
			} else if (member.getMember_id().equals(check.getMember_id())) {
				mv.addObject("modal", "중복된 아이디 입니다 다른 아이디로 다시 가입해주세요.");
				mv.addObject("message", "오류 메세지");
				System.out.println("아이디 중복");
			}
		} else {
			mv.addObject("modal", "환영 합니다. 회원가입에 성공했습니다. 로그인을 해주세요.");
			mv.addObject("message", "성공 메세지");
			memberService.insertMember(member);
		}
		return mv;
	}	
	@RequestMapping(value = "member_login.nhn", method = RequestMethod.POST)
	public ModelAndView member_login_ok(MemberBean member,
			HttpServletRequest request
			) throws Exception {
		
		// DB에 데이터가 있다면 이미 회원이므로 로그인 처리
		MemberBean check = memberService.loginCheck(member);
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(check != null) {
				if(check.getMember_pass() != member.getMember_pass()){
				mv.addObject("modal","비밀 번호가 다릅니다. 다시 시도 해주세요");
				mv.addObject("message", "오류 메세지");
				System.out.println("로그인실패 (비밀번호)");
				mv.setViewName("bbs/bbs_login");
			}else{
			System.out.println("로그인성공");
			mv.setViewName("redirect:bbs_list.nhn");
			session.setAttribute("id", check.getMember_id());
			}
		}else {
				mv.addObject("modal","이메일이 존재하지 않습니다. 다시 시도 해주세요");
				mv.addObject("message", "오류 메세지");
				System.out.println("로그인실패 (아이디)");
				mv.setViewName("bbs/bbs_login");
			
		}
		return mv;
	}
}
