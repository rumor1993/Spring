package com.naver.myhome6.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.naver.myhome6.model.BbsBean;

@Service("bbsService")
public class BbsServiceImpl implements BbsService {

	/*
	 * 3개의 DAO 파일을 작성 하였습니다.
	 * 만약 BbsDAOImpl_old를 사용하고 싶다면 @autowired할 클래스 이름만 바꾸어 주고 
	 * BbsDAOImpl_old.java 파일은 @Repositrory를 붙여주면 됩니다.
	 * 
	 *  이 처럼 DAO 파일이 바뀌어도 이곳에서만 클래스 명만 바꾸어 주면 됩니다.
	 *  DAO를 간접 사용하고 있는 BbsAction2.java는 수정할 필요가 없습니다.
	*/
	
	@Autowired
	private BbsDAOImpl bbsDAO;
	
	@Override
	public void insertBbs(BbsBean bbsbean) throws Exception {
		bbsDAO.insertBbs(bbsbean);
	}

	@Override
	public int getListCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BbsBean> getBbsList(int page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BbsBean getBbsCont(int num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bbsHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editBbs(BbsBean bbsbean) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBbs(int bbs_num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getListCount3(Map m) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BbsBean> getBbsList3(Map m) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refEdit(BbsBean bbsbean) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bbsReplyOk(BbsBean bbsbean) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
