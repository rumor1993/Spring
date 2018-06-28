package com.naver.myhome6.controller;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardOperatorOverloader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome6.dao.BbsDAOImpl;
import com.naver.myhome6.dao.BbsService;
import com.naver.myhome6.model.BbsBean;
import com.naver.myhome6.model.MemberBean;

@Controller
public class BbsAction2 {

	@Autowired
	private BbsService bbsService;
	private String saveFolder = "C:\\Users\\user1\\Documents\\workspace-sts-3.9.4.RELEASE\\spring_mvc_bbs\\src\\main\\webapp\\resources\\upload";

	/* 자료실 입력 폼 */
	@RequestMapping(value = "/bbs_write.nhn")
	public String bbs_write() {

		return "bbs/bbs_write"; // bbs 폴더의 bbs_write.jsp 뷰 페이지가 실행

		/*
		 * 스프링 컨테이너는 매개변수 BbsBean 객체를 생성하고 BbsBean 객체의 setter 메서드들을 호출하여 사용자 입력값을 설정합니다.
		 * 매개변수의 이름과 setter의 property가 일치하면 됩니다.
		 * 
		 * 자료실 저장 첨부 파일 클릭해서 이미지 보고자 할 경우 : 자동 새로 고침 설정 (window -> Preferencs ->
		 * workspace -> Refresh using native hooks or polling 체크) 하고 5초 정도 지난 뒤 확인하세요
		 * 
		 * CommonsMultiartResolver 역할 : 파일 업로드 기능을 구현해 놓은 클래스 입니다.
		 * 
		 * 스프링 컨테이너는 매개변수 BbsBean 객체를 생성하고 BbsBean 객체의 setter 메서드들을 호출하여 사용자 입력값을 설정합니다.
		 * 매개변수의 이름과 setter의 property가 일치하면 됩니다.
		 */
	}

	// 게시판 저장
	@RequestMapping(value = "/bbs_write_ok.nhn", method = RequestMethod.POST)
	public String bbs_write_ok(BbsBean bbsbean) throws Exception {

		MultipartFile uploadfile = bbsbean.getUploadfile();

		if (!uploadfile.isEmpty()) {
			// 원래 파일명 구해오기
			String fileName = uploadfile.getOriginalFilename();

			// 원래 파일명 저장
			bbsbean.setBbs_original(fileName);

			// 생성할 폴더 이름 : 오늘 년+월+일
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); // 오늘 년도를 구합니다.
			int month = c.get(Calendar.MONTH) + 1; // 오늘 월을 구합니다.
			int date = c.get(Calendar.DATE); // 오늘 일을 구합니다.
			String homedir = saveFolder + "/" + year + "-" + month + "-" + date;

			// 파일 객체 생성합니다.
			File path1 = new File(homedir);

			// 폴더가 존재하는지 확인합니다.
			if (!(path1.exists())) {
				System.out.println("폴더 만들어요");
				path1.mkdirs(); // 새로운 폴더를 생성
			}

			// 년수를 구합니다.
			Random r = new Random();
			int random = r.nextInt(100000000);

			// 확장자 구하기 시작
			int index = fileName.lastIndexOf(".");
			// 문자열에서 특정 문자열의 위치 값(index)를 반환합니다.
			// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			// lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
			// (파일명에 점이 여러개 있을 경우 맨 마지막에 발견디는 문자열의 위치를 리턴합니다.)
			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);
			// 확장자 구하기 끝

			// 새로운 파일명을 저장
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;
			System.out.println(refileName);

			// 오라클 디비에 저장될 값
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(saveFolder + fileDBName));
			// 바뀐 파일명으로 저장
			bbsbean.setBbs_file(fileDBName);
		}

		this.bbsService.insertBbs(bbsbean);

		return "redirect:bbs_list.nhn";

	}

	// 게시판 목록
	@RequestMapping(value = "/bbs_list.nhn")
	public ModelAndView bbs_list(
			HttpServletRequest request
			)throws Exception {
			
		int page=1;
		int limit=10; //목록 초기값
		HttpSession session = request.getSession();
		
		if(request.getParameter("page")!= null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		// 자료실 내용 보기 후 목록을 선택 했을 때 limit값을 유지 합니다.
		// 아래부분을 주석을 달경우  limit=3으로 설정됩니다.
		// 이전에 설정된 limit가 있는지 체크
		
		if(session.getAttribute("limit")!=null) {
			limit=(Integer)session.getAttribute("limit");
		}
		
		// 변경된 limit가 있는지 체크
		if(request.getParameter("limit")!=null) {
			limit=Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit", limit);
		}
		
		System.out.println("limit="+limit);
		System.out.println("page="+page);
		
		
		// 총 리스트의 갯수
		int listcount = bbsService.getListCount();

		// 게시글을 담을 변수
		List<BbsBean> bbslist = new ArrayList<BbsBean>();
		
		System.out.println("limit : " + limit);
		
		int maxpage = (listcount + limit - 1) / limit; // 총 페이지 수
		int startpage = ((page - 1) / 5) * 5 + 1; // 현재 페이지에 표시할 시작페이지
		int endpage = startpage + 5 - 1; // 현재 페이지에 표시할 마지막 페이지 수

		if (endpage > maxpage) endpage = maxpage;
		if (endpage < page) page = endpage;
		// 마지막 페이지의 경우 limit 기준으로 늘어나기 때문에 총페이지의 수보다 크면 이를 맞춰준다
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		

		Map m = new HashMap();
		m.put("limit", limit);
		m.put("page", page);
		bbslist = bbsService.getBbsListview(m);
		mv.addObject("list", bbslist);
		mv.setViewName("bbs/bbs_list");
		
		return mv;
	}

	// 게시판 내용보기 , 삭제 , 수정 , 답변
	@RequestMapping(value = "/bbs_cont.nhn")
	public ModelAndView bbs_cont(@RequestParam(value = "bbs_num") int bbs_num, @RequestParam(value = "page") int page,
			@RequestParam(value = "state") String state) throws Exception {

		if (state.equals("cont")) { // 내용보기 일때!
			// 조회수 올려주기 위한 메서드
			bbsService.bbsHit(bbs_num);
		}
		// 클릭한 게시글에 대한 내용 가져오기 위한 메서드
		BbsBean bbs = bbsService.getBbsCont(bbs_num);

		ModelAndView mv = new ModelAndView();
		mv.addObject("bbs", bbs);
		mv.addObject("page", page);

		// 내용보기 안에서 수정,삭제,답변을 눌렀을때 이동하기 위해서
		if (state.equals("cont")) { // 내용보기
			mv.setViewName("bbs/bbs_cont");
		} else if (state.equals("edit")) { // 수정폼
			mv.setViewName("bbs/bbs_edit");
		} else if (state.equals("del")) { // 삭제폼
			mv.setViewName("bbs/bbs_del");
		} else if (state.equals("reply")) { // 답변폼
			mv.setViewName("bbs/bbs_reply");
		}
		return mv;
	}

	@RequestMapping(value = "/bbs_edit_ok.nhn")
	public String bbs_edit_ok(BbsBean bbs, @RequestParam(value = "bbs_num") int bbs_num,
			@RequestParam(value = "bbs_pass") String bbs_pass, @RequestParam(value = "page") int page,
			HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf8");
		BbsBean bbs2 = bbsService.getBbsCont(bbs_num);

		if (bbs2.getBbs_pass().equals(bbs_pass)) {

			MultipartFile uploadfile = bbs.getUploadfile();

			// 기본 파일을 삭제
			File DelFile = new File(saveFolder + bbs2.getBbs_file());
			if (DelFile.exists()) {
				DelFile.delete(); // 기존 이진 파일을 삭제한다.
			}

			if (!uploadfile.isEmpty()) {

				// 원래 파일명 구해오기
				String fileName = uploadfile.getOriginalFilename();

				// 원래 파일명 저장
				bbs.setBbs_original(fileName);

				// 생성할 폴더 이름 : 오늘 년+월+일
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR); // 오늘 년도를 구합니다.
				int month = c.get(Calendar.MONTH) + 1; // 오늘 월을 구합니다.
				int date = c.get(Calendar.DATE); // 오늘 일을 구합니다.
				String homedir = saveFolder + "/" + year + "-" + month + "-" + date;

				// 파일 객체 생성합니다.
				File path1 = new File(homedir);

				// 폴더가 존재하는지 확인합니다.
				if (!(path1.exists())) {
					System.out.println("폴더 만들어요");
					path1.mkdirs(); // 새로운 폴더를 생성
				}

				// 년수를 구합니다.
				Random r = new Random();
				int random = r.nextInt(100000000);

				// 확장자 구하기 시작
				int index = fileName.lastIndexOf(".");
				// 문자열에서 특정 문자열의 위치 값(index)를 반환합니다.
				// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
				// lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
				// (파일명에 점이 여러개 있을 경우 맨 마지막에 발견디는 문자열의 위치를 리턴합니다.)
				System.out.println("index = " + index);

				String fileExtension = fileName.substring(index + 1);
				System.out.println("fileExtension = " + fileExtension);
				// 확장자 구하기 끝

				// 새로운 파일명을 저장
				String refileName = "bbs" + year + month + date + random + "." + fileExtension;
				System.out.println(refileName);

				// 오라클 디비에 저장될 값
				String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
				System.out.println("fileDBName = " + fileDBName);

				// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
				uploadfile.transferTo(new File(saveFolder + fileDBName));
				// 바뀐 파일명으로 저장
				bbs.setBbs_file(fileDBName);
			}

			out.println("<script>");
			out.println("alert('수정에 성공 했습니다.')");
			out.println("location.href = './bbs_cont.nhn?page=" + page + "&bbs_num=" + bbs_num + "&state=cont'");
			out.println("</script>");
			out.close();

			bbsService.editBbs(bbs);
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		return null;
	}

	// 파일 다운로드
	@RequestMapping(value = "/download.file", method = { RequestMethod.GET })
	public void downloadFile(HttpServletResponse response, @RequestParam("path") String storedFileName,
			@RequestParam("original") String originalFileName, HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("utf-8");
		System.out.println("original = " + originalFileName);

		byte fileByte[] = FileUtils.readFileToByteArray(new File(saveFolder + storedFileName));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	@RequestMapping(value = "/bbs_del_ok.nhn", method = RequestMethod.POST)
	public String bbs_del_ok(HttpServletResponse response, @RequestParam(value = "bbs_num") int num,
			@RequestParam(value = "page") int page, @RequestParam(value = "bbs_pass") String bbs_pass)
			throws Exception {

		BbsBean bbs = bbsService.getBbsCont(num);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf8");

		if (bbs.getBbs_pass().equals(bbs_pass)) {
			File DelFile = new File(saveFolder + bbs.getBbs_file());
			if (DelFile.exists()) {
				DelFile.delete(); // 기존 이진 파일을 삭제한다.
			}
			out.println("<script>");
			out.println("alert('삭제 되었습니다')");
			out.println("location.href = './bbs_list.nhn?page=" + page + "'");
			out.println("</script>");
			out.close();
			bbsService.deleteBbs(num);
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "bbs_reply_ok.nhn", method = RequestMethod.POST)
	public String bbs_reply_ok(
			BbsBean bbs, 
			@RequestParam("page") int page
			) throws Exception {
		
		
		bbsService.bbsReplyOk(bbs);
		return "redirect:/bbs_list.nhn";
		
	}
	
	// 자료 검색 목록
	@RequestMapping(value="/bbs_find_ok.nhn", method=RequestMethod.GET)
	public ModelAndView bbs_find_ok(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam("find_name") String find_name,
			@RequestParam("find_field") String find_field) throws Exception{
				
			int limit=10;
			
			Map m = new HashMap<>();
			m.put("page", page);
			m.put("find_field",find_field);
			m.put("find_name", "%"+find_name+"%");
			int listcount = this.bbsService.getListCount3(m);
			// 총페이지 수
			int maxpage = (listcount+limit-1) / limit;
			
			int startpage = ((page -1) / limit) * limit + 1;
			int endpage = startpage + limit -1;
			
			if(endpage > maxpage) endpage = maxpage;
			
			List<BbsBean> bbslist = this.bbsService.getBbsList3(m);
			
			ModelAndView mv = new ModelAndView("bbs/bbs_find");
			
			mv.addObject("find_field",find_field);
			mv.addObject("find_name",find_name);
			mv.addObject("list", bbslist);
			mv.addObject("startpage", startpage);
			mv.addObject("endpage", endpage);
			mv.addObject("listcount", listcount);
			mv.addObject("page", page);
			mv.addObject("maxpage", maxpage);
			
		return mv;
		
		
	}

			
}
