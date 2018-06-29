package com.naver.myhome6.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	
}
