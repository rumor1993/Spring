package com.naver.myhome6.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome4.model.BoardBean;

/*
 
  @Component�� �̿��ؼ� ������ ���ͳ��̳ʰ� �ش� Ŭ���� ��ü�� �����ϵ��� ������ �� ������
   ��� Ŭ������ @Component�� �Ҵ��ϸ� � Ŭ������ � ������ �����ϴ��� �ľ��ϱ�
   ��ƽ��ϴ�. ������ �����ӿ�ũ������ �̷� Ŭ�������� �з��ϱ� ���ؼ� @Component�� ����Ͽ�
   ������ ���� �� ���� �ֳ����̼��� �����մϴ�.
   
   1. @Controller - ������� ��û�� �����ϴ� Controller Ŭ����
   2. @Repository - ������ ���̽� ������ ó���ϴ� DAO Ŭ����
   3. @Service - �����Ͻ� ������ ó���ϴ� Service Ŭ����
   
*/

@Repository
public class BoardDAOImpl {
	@Autowired
	private SqlSession sqlsession;
	
	// @Autowired 
	// public void setSqlSession(SqlSession Sqlsession){
	//		this.sqlSession = SqlSession;
	// setter DI ����

	public void insertBoard(BoardBean b)throws Exception {
		sqlsession.insert("board.add",b);
	}
	public int getListCount() {
		int count = 0;
		count = ((Integer) sqlsession.selectOne("board.count")).intValue();;
		return count;
	}
	public List<BoardBean> GetBoardList(int page){
		List<BoardBean> list = sqlsession.selectList("board.list",page);
		return list;
		
	}
	// �Խù� ��ȸ�� ����
	public void boardHit(int board_num) {
		sqlsession.update("board.hit",board_num);
	}
	// �Խù� ���� ����
	public BoardBean getBoardCont(int board_num) {
		BoardBean boardcont = sqlsession.selectOne("board.cont", board_num);
		return boardcont;
	}
	// �Խù� ����
	public void boardEdit(BoardBean b) throws Exception {
		sqlsession.update("board.edit",b);
	}
	// �Խù� ����
	public void boardDel(int board_num) throws Exception {
		sqlsession.delete("board.del",board_num);
	}
	// �亯�� ���� ����
	public void refEdit(BoardBean b) throws Exception {
		sqlsession.update("board.level",b);
	}
	// �亯�� ����
	public void insertBoardReply(BoardBean b)throws Exception {
		sqlsession.insert("board.reply",b);
	}
	
}

