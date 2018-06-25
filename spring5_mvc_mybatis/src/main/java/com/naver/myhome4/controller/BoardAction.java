package com.naver.myhome4.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome4.dao.BoardDAOImpl;
import com.naver.myhome4.model.BoardBean;

@Controller
public class BoardAction {

	@Autowired
	private BoardDAOImpl boardService;

	// 게시판 글쓰기 폼
	@RequestMapping(value = "/board_write.nhn")
	public String board_write() {
		System.out.println("=====================");
		return "board/board_write";
	}

	// 게시판 저장
	@RequestMapping(value = "/board_write_ok.nhn", method = RequestMethod.POST)
	public String board_write_ok(BoardBean board) throws Exception {
		boardService.insertBoard(board);
		System.out.println("등록성공");
		return "redirect:/board_list.nhn";

	}

	// 게시판 목록
	@RequestMapping(value = "/board_list.nhn")
	public ModelAndView board_list(@RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
		System.out.println("board_list");

		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		int getListCount = boardService.getListCount();

		int limit = 10;
		// 한 화면에 출력할 레코드 갯수

		int listcount = getListCount;
		// 총 리스트 수를 받아옴

		int maxpage = (listcount + limit - 1) / limit;
		// 총 페이지 수

		int startpage = ((page - 1) / limit) * limit + 1;
		// 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 ...)

		int endpage = startpage + limit - 1;
		// 현제 페이지에 보여줄 마지막 페이지 수 (10, 20, 30, ...)

		if (endpage > maxpage)
			endpage = maxpage;

		boardlist = boardService.GetBoardList(page);
		ModelAndView mv = new ModelAndView("/board/board_list");
		mv.addObject("list", boardlist);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		return mv;
	}

	// 게시판 내용보기 , 삭제 , 수정 , 답변
	@RequestMapping(value = "/board_cont.nhn")
	public ModelAndView board_cont(@RequestParam("board_num") int board_num, @RequestParam("page") int page,
			@RequestParam("state") String state) throws Exception {

		if (state.equals("cont")) { // 내용보기일때만
			boardService.boardHit(board_num);
		}
		BoardBean board = boardService.getBoardCont(board_num);

		ModelAndView contM = new ModelAndView();
		contM.addObject("board", board);
		contM.addObject("page", page);

		if (state.equals("cont")) { // 내용보기일때만
			contM.setViewName("board/board_cont"); // 내용보기 페이지 설정
		} else if (state.equals("edit")) { // 수정폼
			contM.setViewName("board/board_edit");
		} else if (state.equals("del")) { // 삭제폼
			contM.setViewName("board/board_del");
		} else if (state.equals("reply")) { // 답변달기 폼
			contM.setViewName("board/board_reply");
		}
		return contM;

	}

	// 수정 시 비밀번호 체크
	@RequestMapping(value = "/board_edit_ok.nhn", method = RequestMethod.POST)
	public String board_edit_ok(BoardBean b, @RequestParam("board_num") int board_num, @RequestParam("page") int page,
			HttpServletResponse response) throws Exception {

		BoardBean board = boardService.getBoardCont(board_num);
		response.setContentType("text/html; charset=UTF-8");
	

		if (board.getBoard_pass().equals(b.getBoard_pass())) { // 비번이 같다면
			boardService.boardEdit(b);
			return "redirect:/board_cont.nhn?board_num=" + b.getBoard_num() + "&page=" + page + "&state=cont";
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.')");
			out.println("history.back()");
			out.println("</script>");
		}

		return null;

	}

	@RequestMapping(value = "/board_del_ok.nhn", method = RequestMethod.POST)
	public String board_del_ok(
			@RequestParam("board_num")int board_num,
			@RequestParam("page") int page,
			@RequestParam("pwd") String board_pass,
			HttpServletResponse response
			) throws Exception {
			
		BoardBean board = boardService.getBoardCont(board_num);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(board_pass.equals(board.getBoard_pass())) { //비번이 같다면 	
			boardService.boardDel(board_num);
			out.println("<script>");
			out.println("alert('삭제에 성공 했습니다.')");
			out.println("location.href = './board_list.nhn?page="+page+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
		
	}

	@RequestMapping(value = "/board_reply_ok.nhn" , method = RequestMethod.POST)
	public String board_reply_ok(
			BoardBean board,
			@RequestParam("page") int page,
			HttpServletResponse response
			) throws Exception {
			
			boardService.refEdit(board);
		
			board.setBoard_re_lev(board.getBoard_re_lev()+1);
			board.setBoard_re_seq(board.getBoard_re_seq() +1);
			boardService.insertBoardReply(board);
			
			
			return "redirect:/board_list.nhn";
		
	}

}