package com.naver.myhome6.dao;

import com.naver.myhome6.model.MemberBean;

public interface MemberService {
	
	// ȸ�� ���� 
	public void insertMember(MemberBean member) throws Exception;
	
	// �α��� �ߺ�üũ
	public MemberBean loginCheck(MemberBean member) throws Exception;
}
