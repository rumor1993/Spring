package com.naver.myhome6.dao;

import java.util.List;

import com.naver.myhome6.model.MemberBean;

public interface MemberService {
	
	// 회원 가입 
	public void insertMember(MemberBean member) throws Exception;
	
	// 로그인 중복체크
	public MemberBean loginCheck(MemberBean member) throws Exception;
	
	// 멤버 리스트 보여주기
	public List<MemberBean> getmember_list() throws Exception;
	
	
}
