package com.naver.myhome6.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome4.model.BoardBean;

/*
 
  @Component를 이용해서 스프링 컨터네이너가 해당 클래스 객체를 생성하도록 설정할 수 있지만
   모든 클래스에 @Component를 할당하면 어떤 클래스가 어떤 역할을 수행하는지 파악하기
   어렵습니다. 스프링 프레임워크에서는 이런 클래스들을 분류하기 위해서 @Component를 상속하여
   다음과 같은 세 개의 애노테이션을 제공합니다.
   
   1. @Controller - 사용자의 요청을 제어하는 Controller 클래스
   2. @Repository - 데이터 베이스 연동을 처리하는 DAO 클래스
   3. @Service - 비지니스 로직을 처리하는 Service 클래스
   
*/

@Repository
public class BoardDAOImpl {
	@Autowired
	private SqlSession sqlsession;
	
	// @Autowired 
	// public void setSqlSession(SqlSession Sqlsession){
	//		this.sqlSession = SqlSession;
	// setter DI 설정

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
	// 게시물 조회수 증가
	public void boardHit(int board_num) {
		sqlsession.update("board.hit",board_num);
	}
	// 게시물 내용 보기
	public BoardBean getBoardCont(int board_num) {
		BoardBean boardcont = sqlsession.selectOne("board.cont", board_num);
		return boardcont;
	}
	// 게시물 수정
	public void boardEdit(BoardBean b) throws Exception {
		sqlsession.update("board.edit",b);
	}
	// 게시물 삭제
	public void boardDel(int board_num) throws Exception {
		sqlsession.delete("board.del",board_num);
	}
	// 답변글 레벨 증가
	public void refEdit(BoardBean b) throws Exception {
		sqlsession.update("board.level",b);
	}
	// 답변글 저장
	public void insertBoardReply(BoardBean b)throws Exception {
		sqlsession.insert("board.reply",b);
	}
	
}

