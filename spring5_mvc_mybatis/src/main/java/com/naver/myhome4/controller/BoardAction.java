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

	// �Խ��� �۾��� ��
	@RequestMapping(value = "/board_write.nhn")
	public String board_write() {
		System.out.println("=====================");
		return "board/board_write";
	}

	// �Խ��� ����
	@RequestMapping(value = "/board_write_ok.nhn", method = RequestMethod.POST)
	public String board_write_ok(BoardBean board) throws Exception {
		boardService.insertBoard(board);
		System.out.println("��ϼ���");
		return "redirect:/board_list.nhn";

	}

	// �Խ��� ���
	@RequestMapping(value = "/board_list.nhn")
	public ModelAndView board_list(@RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
		System.out.println("board_list");

		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		int getListCount = boardService.getListCount();

		int limit = 10;
		// �� ȭ�鿡 ����� ���ڵ� ����

		int listcount = getListCount;
		// �� ����Ʈ ���� �޾ƿ�

		int maxpage = (listcount + limit - 1) / limit;
		// �� ������ ��

		int startpage = ((page - 1) / limit) * limit + 1;
		// ���� �������� ������ ���� ������ �� (1, 11, 21 ...)

		int endpage = startpage + limit - 1;
		// ���� �������� ������ ������ ������ �� (10, 20, 30, ...)

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

	// �Խ��� ���뺸�� , ���� , ���� , �亯
	@RequestMapping(value = "/board_cont.nhn")
	public ModelAndView board_cont(@RequestParam("board_num") int board_num, @RequestParam("page") int page,
			@RequestParam("state") String state) throws Exception {

		if (state.equals("cont")) { // ���뺸���϶���
			boardService.boardHit(board_num);
		}
		BoardBean board = boardService.getBoardCont(board_num);

		ModelAndView contM = new ModelAndView();
		contM.addObject("board", board);
		contM.addObject("page", page);

		if (state.equals("cont")) { // ���뺸���϶���
			contM.setViewName("board/board_cont"); // ���뺸�� ������ ����
		} else if (state.equals("edit")) { // ������
			contM.setViewName("board/board_edit");
		} else if (state.equals("del")) { // ������
			contM.setViewName("board/board_del");
		} else if (state.equals("reply")) { // �亯�ޱ� ��
			contM.setViewName("board/board_reply");
		}
		return contM;

	}

	// ���� �� ��й�ȣ üũ
	@RequestMapping(value = "/board_edit_ok.nhn", method = RequestMethod.POST)
	public String board_edit_ok(BoardBean b, @RequestParam("board_num") int board_num, @RequestParam("page") int page,
			HttpServletResponse response) throws Exception {

		BoardBean board = boardService.getBoardCont(board_num);
		response.setContentType("text/html; charset=UTF-8");
	

		if (board.getBoard_pass().equals(b.getBoard_pass())) { // ����� ���ٸ�
			boardService.boardEdit(b);
			return "redirect:/board_cont.nhn?board_num=" + b.getBoard_num() + "&page=" + page + "&state=cont";
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��й�ȣ�� �ٸ��ϴ�.')");
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
		if(board_pass.equals(board.getBoard_pass())) { //����� ���ٸ� 	
			boardService.boardDel(board_num);
			out.println("<script>");
			out.println("alert('������ ���� �߽��ϴ�.')");
			out.println("location.href = './board_list.nhn?page="+page+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('��й�ȣ�� �ٸ��ϴ�.')");
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