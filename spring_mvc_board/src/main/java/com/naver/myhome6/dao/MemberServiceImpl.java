package com.naver.myhome6.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome6.model.MemberBean;

@Service("MemberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAOImpl memberDAO;
	
	
	@Override
	public void insertMember(MemberBean member) throws Exception {
		memberDAO.insertMember(member);
	}


	@Override
	public MemberBean loginCheck(MemberBean member) throws Exception {
		return memberDAO.loginCheck(member);
	}
}
