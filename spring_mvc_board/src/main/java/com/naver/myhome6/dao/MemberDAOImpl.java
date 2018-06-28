package com.naver.myhome6.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome6.model.MemberBean;

@Repository
public class MemberDAOImpl {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public void insertMember(MemberBean member) throws Exception  {
		sqlsession.insert("member.signup",member);
	}
	public MemberBean loginCheck(MemberBean member) throws Exception {
		return sqlsession.selectOne("member.loginCheck", member);
	}

}
