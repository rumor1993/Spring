package com.naver.myhome6.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome6.dao.MemberService;
import com.naver.myhome6.model.MemberBean;
import com.naver.myhome6.model.ZipcodeBean;
import com.naver.myhome6.model.ZipcodeBean2;

@Controller
public class MemberAction {
	@Autowired
	private MemberService memberService;
	// 배포시 필요한 경로
	// private String saveFolder="C:/Program Files/Apache Software Foundation/Tomcat
	// 8.5 /......

	private String saveFolder = "C:\\Users\\user1\\git\\Spring2\\spring_mvc_member\\src\\main\\webapp\\resources\\upload";

	/* 로그인 폼 뷰 */
	@RequestMapping(value = "/member_login.nhn")
	public String member_login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/loginForm";
		// member 폴더의 member_login.jsp 뷰 페이지 실행
	}

	/* 우편검색 폼 */
	@RequestMapping(value = "/zipcode_find.nhn")
	public String zipcode_find() {
		return "member/zipcode_find";
		// member 폴더의 zipcode_find.jsp 뷰 페이지 실행
	}

	/* 로그인 인증 */
	@RequestMapping(value = "/member_login_ok.nhn", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView member_login_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// HttpSession 클래스는 세션 객체를 생성해서 로그인 인증 처리를 하기 위해서 입니다.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // 출력 스트림 객체 생성
		HttpSession session = request.getSession(); // 세션 객체 생성

		String id = request.getParameter("id").trim();
		String pwd = request.getParameter("pwd").trim();

		MemberBean m = this.memberService.userCheck(id);

		if (m == null) { // 등록 되지 않은 회원일때
			out.println("<script>");
			out.println("alert('등록된 아이디가 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			if (m.getJoin_pwd().equals(pwd)) { // 비번이 같을 경우
				session.setAttribute("id", id);

				String join_name = m.getJoin_name();
				String join_file = m.getJoin_file();
				session.setAttribute("join_name", join_name);
				session.setAttribute("join_file", join_file);

				// jsp 폴더의 view.jsp로 이동
				ModelAndView mv = new ModelAndView("view");
				return mv;
			} else { // 비번이 다를경우
				out.println("<script>");
				out.println("alert('비번이 다릅니다..')");
				out.println("history.go(-1)");
				out.println("</script>");

			}
		}
		return null;

	}

	/* 회원가입 폼 보기 */
	@RequestMapping(value = "/member_join.nhn")
	public String member_join() throws Exception {
		return "member/member_join";
	}

	@RequestMapping(value = "/member_join_ok.nhn", method = RequestMethod.POST)
	public void member_join_ok(MemberBean member, HttpServletResponse response) throws Exception {

		System.out.println("응 넘어옴?");
		String join_tel = member.getJoin_tel1() + "-" + member.getJoin_tel2() + "-" + member.getJoin_tel3();
		String join_phone = member.getJoin_phone1() + "-" + member.getJoin_phone2() + "-" + member.getJoin_phone3();

		// 메일아이디 : member.getJoin_mailid()
		// 메일도메인 : member. getJoin_maildomain()

		String join_email = member.getJoin_mailid() + "@" + member.getJoin_maildomain(); // 메일 주소
		member.setJoin_tel(join_tel);
		member.setJoin_phone(join_phone);
		member.setJoin_email(join_email);

		MultipartFile UpFile = member.getJoin_profile();

		if (!UpFile.isEmpty()) { // 첨부한 이전 파일이 있다면
			String fileName = UpFile.getOriginalFilename(); // 이진 파일명 저장

			member.setJoin_original(fileName);
			System.out.println(member.getJoin_original());

			Calendar c = Calendar.getInstance(); // 추상클래스로서 년월일 시분초 반환
			int year = c.get(Calendar.YEAR); // 년도값
			int month = c.get(Calendar.MONTH) + 1; // 월 값 . +1일 한 이유는 1월이 0 이라서
			int date = c.get(Calendar.DATE);

			String homedir = saveFolder + "/" + year + "-" + month + "-" + date; // 새로운 폴더 저장
			File path1 = new File(homedir);
			if (!path1.exists()) { // 새로운 폴더가 존재 하지 않으면
				path1.mkdir();// 새로운 폴더를 생성
			}

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
			String refileName = "member" + year + month + date + random + "." + fileExtension;
			System.out.println(refileName);

			// 오라클 디비에 저장될 값
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			UpFile.transferTo(new File(saveFolder + fileDBName));
			// 바뀐 파일명으로 저장
			member.setJoin_file(fileDBName);
		}
		memberService.insertMember(member);

		response.sendRedirect("member_login.nhn");
	}

	/* 아이디 중복 체크 */
	@RequestMapping(value = "/checkMemberId.nhn")
	public void id_check(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberService.checkMemberId(id);
		PrintWriter out = response.getWriter();

		out.println(result);
	}

	/* 우편번호 DB로 부터 검색 */
	@RequestMapping(value = "/zipcode_find_ok.nhn", method = RequestMethod.POST)
	public ModelAndView zipcode_find_ok(@RequestParam String dong, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(dong);

		List<ZipcodeBean2> zipcodeList = new ArrayList<ZipcodeBean2>();

		// 도을 기준으로 주소를 검색해서 컬렉션에 저장합니다.
		zipcodeList = this.memberService.findZipcode("%" + dong + "%");

		List<ZipcodeBean> zipcodeList2 = new ArrayList<ZipcodeBean>();

		for (int i = 0; i < zipcodeList.size(); i++) {
			ZipcodeBean2 zipcode_addr = zipcodeList.get(i);

			String zipcode = zipcode_addr.getZipcode(); // 우편번호 저장
			String sido = zipcode_addr.getSido(); // 서울시, 경기도 저장
			String gugun = zipcode_addr.getSigungu(); // 구, 군
			String doro = zipcode_addr.getDoro(); // 도로
			String addr = sido + " " + gugun + " " + doro; // 서울시 강남구 강남 대로

			System.out.println(addr);
			ZipcodeBean zip = new ZipcodeBean();
			zip.setZipcode(zipcode);
			zip.setAddr(addr);

			System.out.println(zip.getAddr());
			// 컬렉션에 주소를 저장합니다.
			zipcodeList2.add(zip);

		}

		ModelAndView zipcodeM = new ModelAndView("member/zipcode_find");
		zipcodeM.addObject("zipcodeList", zipcodeList2);
		zipcodeM.addObject("dong", dong);

		return zipcodeM;

	}

	/* 회원정보 수정 */
	@RequestMapping(value = "/member_edit.nhn", method = RequestMethod.GET)
	public ModelAndView member_edit(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		MemberBean m = this.memberService.userCheck(id);

		String join_tel = m.getJoin_tel();
		// java.util 패키지의 StringTokenizer 클래스는 첫번째 전달인자를
		// 두번째 -를 기준으로 문자열을 파싱해 줍니다.
		// 집 전화번호 저장

		StringTokenizer st01 = new StringTokenizer(join_tel, "-");
		String join_tel1 = st01.nextToken(); // 첫번째 (국번 집전화번호 저장)
		String join_tel2 = st01.nextToken(); // 두번째 (가운데 자리)
		String join_tel3 = st01.nextToken(); // 세번째 (뒷 자리)

		String join_phone = m.getJoin_phone();
		// java.util 패키지의 StringTokenizer 클래스는 첫번째 전달인자를
		// 두번째 -를 기준으로 문자열을 파싱해줍니다.
		// 휴대폰 번호 저장

		StringTokenizer st02 = new StringTokenizer(join_phone, "-");
		String join_phone1 = st02.nextToken(); // 첫번째 자리
		String join_phone2 = st02.nextToken(); // 두번째 자리
		String join_phone3 = st02.nextToken(); // 세번째 자리

		String join_email = m.getJoin_email();
		// java.util 패키지의 StringTokenizer 클래스는 첫번째 전달인자를
		// 두번째 @를 기준으로 문자열을 파싱해 줍니다.

		StringTokenizer st03 = new StringTokenizer(join_email, "@");
		String join_mailid = st03.nextToken();
		String join_maildomain = st03.nextToken();

		ModelAndView mv = new ModelAndView("member/member_edit");
		mv.addObject("m", m);
		mv.addObject("join_tel1", join_tel1);
		mv.addObject("join_tel2", join_tel2);
		mv.addObject("join_tel3", join_tel3);

		mv.addObject("join_phone1", join_phone1);
		mv.addObject("join_phone2", join_phone2);
		mv.addObject("join_phone3", join_phone3);

		mv.addObject("join_mailid", join_mailid);
		mv.addObject("join_maildomain", join_maildomain);

		return mv;

	}

	@RequestMapping(value = "/member_edit_ok.nhn", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView member_edit_ok(HttpServletRequest request, MemberBean member) throws Exception {
		HttpSession session = request.getSession();
		// 세션 객체를 받음
		String id = (String) session.getAttribute("id");

		String join_tel = member.getJoin_tel1() + "-" + member.getJoin_tel2() + "-" + member.getJoin_tel3();
		String join_phone = member.getJoin_phone1() + "-" + member.getJoin_phone2() + "-" + member.getJoin_phone3();

		// 메일아이디 : member.getJoin_mailid()
		// 메일도메인 : member. getJoin_maildomain()

		String join_email = member.getJoin_mailid() + "@" + member.getJoin_maildomain(); // 메일 주소
		member.setJoin_tel(join_tel);
		member.setJoin_phone(join_phone);
		member.setJoin_email(join_email);

		MultipartFile UpFile = member.getJoin_profile();

		if (!UpFile.isEmpty()) { // 첨부한 이전 파일이 있다면
			File DelFile = new File(saveFolder + member.getJoin_file());
			if (DelFile.exists()) { // 기존 이진 파일이 존재하면
				DelFile.delete(); // 기존 이진 파일명을 삭제
			}

			String fileName = UpFile.getOriginalFilename(); // 이진 파일명 저장

			member.setJoin_original(fileName);
			System.out.println(member.getJoin_original());

			Calendar c = Calendar.getInstance(); // 추상클래스로서 년월일 시분초 반환
			int year = c.get(Calendar.YEAR); // 년도값
			int month = c.get(Calendar.MONTH) + 1; // 월 값 . +1일 한 이유는 1월이 0 이라서
			int date = c.get(Calendar.DATE);

			String homedir = saveFolder + "/" + year + "-" + month + "-" + date; // 새로운 폴더 저장
			File path1 = new File(homedir);
			if (!path1.exists()) { // 새로운 폴더가 존재 하지 않으면
				path1.mkdir();// 새로운 폴더를 생성
			}

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
			String refileName = "member" + year + month + date + random + "." + fileExtension;
			System.out.println(refileName);

			// 오라클 디비에 저장될 값
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			UpFile.transferTo(new File(saveFolder + fileDBName));
			// 바뀐 파일명으로 저장
			member.setJoin_file(fileDBName);
		}
		memberService.updateMember(member);
		// 수정 메서드 호출

		// 이미지 폴더가 바로 적용 안됩니다.
		ModelAndView mv = new ModelAndView("view");
		mv.addObject("join_name", member.getJoin_name());
		mv.addObject("join_file", member.getJoin_file());
		mv.addObject("state", "edit");

		return mv;
	}

	/* 회원정보 삭제 폼 */
	@RequestMapping(value = "/member_del.nhn")
	public ModelAndView member_del(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberBean deleteM = this.memberService.userCheck(id);
		
		ModelAndView dm = new ModelAndView("member/member_del");
		dm.addObject("d_id",id);
		dm.addObject("d_name",deleteM.getJoin_name());
		return dm;

	}

	/* 로그아웃 폼 */
	@RequestMapping(value = "/member_logout.nhn")
	public String member_logout() {
		return "member/member_logout";
		// member 폴더의 member_logout.jsp 뷰 페이지 실행
	}

	/*
	 * servlet-context.xml에서 지정했기 때문에 아래의 문장을 생략 가능합니다. <view-controller path =
	 * "/pwd_find.nhn" view-name="member/pwd_find"/> 비밀번호찾기 폼
	 * 
	 * @RequestMapping(value="/pwd_find.nhn") public String pwd_find(){ return
	 * "member/pwd_find"; // member 폴더의 pwd_find.jsp 뷰 페이지 실행
	 * 
	 */

	@RequestMapping(value = "pwd_find_ok.nhn", method = RequestMethod.POST)
	public ModelAndView pwd_find_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();

		Map pm = new HashMap();
		// 컬렉션 Map에 키와 값 저장합니다.
		pm.put("id", id);
		pm.put("name", name);

		MemberBean member = this.memberService.findpwd(pm);
		

		if (member == null) { // 회원 아이디와 이름이 맞지 않는 경우
			out.println("<script>");
			out.println("alert('회원아이디와 이름이 맞지 않습니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
		} else {
			ModelAndView pwdM = new ModelAndView("member/pwd_find");
			pwdM.addObject("pwdok", member.getJoin_pwd());
			return pwdM;
		}
		return null;

	}

}