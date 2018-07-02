package com.naver.myhome6.controller;


import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
   // private String saveFolder="C:/Program Files/Apache Software Foundation/Tomcat 8.5 /......
   
   private String saveFolder="C:\\Users\\user1\\Desktop\\spring7_mvc_member\\src\\main\\webapp\\resources\\upload";
   
   /* 로그인 폼 뷰 */
   @RequestMapping(value="/member_login.nhn")
   public String member_login(HttpServletRequest request,
         HttpServletResponse response) throws Exception{
      return "member/loginForm";
      // member 폴더의 member_login.jsp 뷰 페이지 실행
   }
   

   
   /* 우편검색 폼 */
   @RequestMapping(value="/zipcode_find.nhn")
   public String zipcode_find() {
      return "member/zipcode_find";
      // member 폴더의 zipcode_find.jsp 뷰 페이지 실행
   }
   
	/*로그인 process*/
	@RequestMapping(value="/member_login_ok.nhn")
	public String login_process(MemberBean member,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		MemberBean getMember = memberService.userCheck(member.getJoin_id());
		PrintWriter out = response.getWriter();
		if(getMember==null) {
			out.println("<script>");
			out.println("alert('가입되어 있지 않아요.')");
			out.println("history.go(-1)");
			out.println("<script>");
			return null;
		}else if(member.getJoin_pwd().equals(getMember.getJoin_pwd())) {
			session.setAttribute("id", member.getJoin_id());
			return "redirect:";
		}else {
			out.println("<script>");
			out.println("alert('비밀번호가 달라요.')");
			out.println("history.go(-1)");
			out.println("<script>");
			return null;
		}
		
		
	}
	/*회원가입 폼 보기*/
	@RequestMapping(value="/member_join.nhn")
	public String member_join() throws Exception{
		return "member/member_join";
	}
	
	@RequestMapping(value="/member_join_ok.nhn", method=RequestMethod.POST)
	public void member_join_ok(
			MemberBean member,
			HttpServletResponse response) throws Exception{
		
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
		
		if(!UpFile.isEmpty()) { //첨부한 이전 파일이 있다면
			String fileName = UpFile.getOriginalFilename(); //이진 파일명 저장
			Calendar c = Calendar.getInstance(); // 추상클래스로서 년월일 시분초 반환
			int year = c.get(Calendar.YEAR); // 년도값
			int month = c.get(Calendar.MONTH)+1; // 월 값 . +1일 한 이유는 1월이 0 이라서
			int date = c.get(Calendar.DATE);
			
			String homedir = saveFolder + "/" + year + "-" + month + "-" + date; // 새로운 폴더 저장
			File path1 = new File(homedir);
			if(!path1.exists()) { // 새로운 폴더가 존재 하지 않으면
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
	
	/*아이디 중복 체크*/
	@RequestMapping(value="/checkMemberId.nhn")
	public void id_check(
			@RequestParam("id")String id,
			HttpServletResponse response)throws Exception{		
		int result = memberService.checkMemberId(id);
		PrintWriter out = response.getWriter();
		
		out.println(result);
	}
   
   /* 우편번호 DB로 부터 검색 */
   @RequestMapping(value = "/zipcode_find_ok.nhn", method=RequestMethod.POST)
   public ModelAndView zipcode_find_ok(
         @RequestParam String dong,
         HttpServletRequest request,
         HttpServletResponse response) throws Exception{
         
      System.out.println(dong);
      
      List<ZipcodeBean2> zipcodeList = new ArrayList<ZipcodeBean2>();
      
      
      // 도을 기준으로 주소를 검색해서 컬렉션에 저장합니다.
      zipcodeList = this.memberService.findZipcode("%"+dong+"%");

      
      List<ZipcodeBean> zipcodeList2 = new ArrayList<ZipcodeBean>();
      
      for(int i = 0; i<zipcodeList.size(); i++) {
    	  ZipcodeBean2 zipcode_addr = zipcodeList.get(i);
    	  
    	  String zipcode = zipcode_addr.getZipcode(); // 우편번호 저장
    	  String sido = zipcode_addr.getSido(); // 서울시, 경기도 저장
    	  String gugun = zipcode_addr.getSigungu(); // 구, 군
    	  String doro = zipcode_addr.getDoro(); // 도로
    	  String addr = sido + " " +gugun + " " + doro; // 서울시 강남구 강남 대로
    	  
    	  System.out.println(addr);
    	  ZipcodeBean zip = new ZipcodeBean();
    	  zip.setZipcode(zipcode);
    	  zip.setAddr(addr);
    	  
    	  System.out.println(zip.getAddr());
    	  // 컬렉션에 주소를 저장합니다.
    	  zipcodeList2.add(zip);
    	  
      }
      
      ModelAndView zipcodeM = new ModelAndView("member/zipcode_find");
      zipcodeM.addObject("zipcodeList",zipcodeList2);
      zipcodeM.addObject("dong", dong);
      
      
      return zipcodeM;
      
   }
   
}