package com.naver.myhome6.dao;

import com.naver.myhome6.model.MemberBean;

public interface MemberService {
	
	// 회원 가입 
	public void insertMember(MemberBean member) throws Exception;
	
	// 로그인 중복체크
	public MemberBean loginCheck(MemberBean member) throws Exception;
}
