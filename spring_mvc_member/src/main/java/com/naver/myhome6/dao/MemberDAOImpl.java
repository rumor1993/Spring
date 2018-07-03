package com.naver.myhome6.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome6.model.MemberBean;
import com.naver.myhome6.model.ZipcodeBean2;

@Repository
public class MemberDAOImpl {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	/* 우편 검색 */
	public List<ZipcodeBean2> findZipcode(String dong) throws Exception{
		List<ZipcodeBean2> list = sqlsession.selectList("Test.zipcodelist",dong);
		return list;
	}	
	
	public void insertMember(MemberBean m) throws Exception {
		sqlsession.insert("Test.join",m);
		
	}
	public int checkMemberId(String id) throws Exception {
		return sqlsession.selectOne("Test.check_id",id);
	}
	public MemberBean userCheck(String id) throws Exception {
		return sqlsession.selectOne("Test.select_member",id);
	}

	public void updateMember(MemberBean member) {
		sqlsession.update("Test.update",member);
		
	}

	public MemberBean findpwd(Map pm) {
		return sqlsession.selectOne("member_find",pm);
	}
}
