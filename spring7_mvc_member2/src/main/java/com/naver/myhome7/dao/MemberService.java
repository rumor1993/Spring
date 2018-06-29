package com.naver.myhome7.dao;
import java.util.List;
import java.util.Map;

import com.naver.myhome7.model.MemberBean;
import com.naver.myhome7.model.ZipcodeBean2;
public interface MemberService {
	/*���̵� �ߺ� üũ */
	public int checkMemberId(String id) throws Exception;

	/*���� �˻�*/
	public List<ZipcodeBean2> findZipcode(String dong) throws Exception;
	
	/* ��� �˻� */
	public MemberBean findpwd(Map pm) throws Exception ;

	/*ȸ������*/
	public void insertMember(MemberBean m) throws Exception;

	/*�α��� ���� üũ */
	public MemberBean userCheck(String id) throws Exception;

	/*ȸ������*/
	public void deleteMember(MemberBean delm) throws Exception;

	 /*ȸ������*/
	public void updateMember(MemberBean member) throws Exception;

}
