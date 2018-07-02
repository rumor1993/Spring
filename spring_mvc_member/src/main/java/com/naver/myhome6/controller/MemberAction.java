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
   // ������ �ʿ��� ���
   // private String saveFolder="C:/Program Files/Apache Software Foundation/Tomcat 8.5 /......
   
   private String saveFolder="C:\\Users\\user1\\Desktop\\spring7_mvc_member\\src\\main\\webapp\\resources\\upload";
   
   /* �α��� �� �� */
   @RequestMapping(value="/member_login.nhn")
   public String member_login(HttpServletRequest request,
         HttpServletResponse response) throws Exception{
      return "member/loginForm";
      // member ������ member_login.jsp �� ������ ����
   }
   

   
   /* ����˻� �� */
   @RequestMapping(value="/zipcode_find.nhn")
   public String zipcode_find() {
      return "member/zipcode_find";
      // member ������ zipcode_find.jsp �� ������ ����
   }
   
	/*�α��� process*/
	@RequestMapping(value="/member_login_ok.nhn")
	public String login_process(MemberBean member,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		MemberBean getMember = memberService.userCheck(member.getJoin_id());
		PrintWriter out = response.getWriter();
		if(getMember==null) {
			out.println("<script>");
			out.println("alert('���ԵǾ� ���� �ʾƿ�.')");
			out.println("history.go(-1)");
			out.println("<script>");
			return null;
		}else if(member.getJoin_pwd().equals(getMember.getJoin_pwd())) {
			session.setAttribute("id", member.getJoin_id());
			return "redirect:";
		}else {
			out.println("<script>");
			out.println("alert('��й�ȣ�� �޶��.')");
			out.println("history.go(-1)");
			out.println("<script>");
			return null;
		}
		
		
	}
	/*ȸ������ �� ����*/
	@RequestMapping(value="/member_join.nhn")
	public String member_join() throws Exception{
		return "member/member_join";
	}
	
	@RequestMapping(value="/member_join_ok.nhn", method=RequestMethod.POST)
	public void member_join_ok(
			MemberBean member,
			HttpServletResponse response) throws Exception{
		
		System.out.println("�� �Ѿ��?");
		String join_tel = member.getJoin_tel1() + "-" + member.getJoin_tel2() + "-" + member.getJoin_tel3();
		String join_phone = member.getJoin_phone1() + "-" + member.getJoin_phone2() + "-" + member.getJoin_phone3();
		
		// ���Ͼ��̵� : member.getJoin_mailid()
		// ���ϵ����� : member. getJoin_maildomain()
		
		String join_email = member.getJoin_mailid() + "@" + member.getJoin_maildomain(); // ���� �ּ�
		member.setJoin_tel(join_tel);
		member.setJoin_phone(join_phone);
		member.setJoin_email(join_email);
		
		MultipartFile UpFile = member.getJoin_profile();
		
		if(!UpFile.isEmpty()) { //÷���� ���� ������ �ִٸ�
			String fileName = UpFile.getOriginalFilename(); //���� ���ϸ� ����
			Calendar c = Calendar.getInstance(); // �߻�Ŭ�����μ� ����� �ú��� ��ȯ
			int year = c.get(Calendar.YEAR); // �⵵��
			int month = c.get(Calendar.MONTH)+1; // �� �� . +1�� �� ������ 1���� 0 �̶�
			int date = c.get(Calendar.DATE);
			
			String homedir = saveFolder + "/" + year + "-" + month + "-" + date; // ���ο� ���� ����
			File path1 = new File(homedir);
			if(!path1.exists()) { // ���ο� ������ ���� ���� ������
				path1.mkdir();// ���ο� ������ ����
			}
			
			Random r = new Random();
			int random = r.nextInt(100000000);

			// Ȯ���� ���ϱ� ����
			int index = fileName.lastIndexOf(".");
			// ���ڿ����� Ư�� ���ڿ��� ��ġ ��(index)�� ��ȯ�մϴ�.
			// indexOf�� ó�� �߰ߵǴ� ���ڿ��� ���� index�� ��ȯ�ϴ� �ݸ�,
			// lastIndexOf�� ���������� �߰ߵǴ� ���ڿ��� index�� ��ȯ�մϴ�.
			// (���ϸ� ���� ������ ���� ��� �� �������� �߰ߵ�� ���ڿ��� ��ġ�� �����մϴ�.)
			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);
			// Ȯ���� ���ϱ� ��

			// ���ο� ���ϸ��� ����
			String refileName = "member" + year + month + date + random + "." + fileExtension;
			System.out.println(refileName);

			// ����Ŭ ��� ����� ��
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			// transferTo(File path) : ���ε��� ������ �Ű������� ��ο� �����մϴ�.
			UpFile.transferTo(new File(saveFolder + fileDBName));
			// �ٲ� ���ϸ����� ����
			member.setJoin_file(fileDBName);
		}
		memberService.insertMember(member);
		
		response.sendRedirect("member_login.nhn");
	}
	
	/*���̵� �ߺ� üũ*/
	@RequestMapping(value="/checkMemberId.nhn")
	public void id_check(
			@RequestParam("id")String id,
			HttpServletResponse response)throws Exception{		
		int result = memberService.checkMemberId(id);
		PrintWriter out = response.getWriter();
		
		out.println(result);
	}
   
   /* �����ȣ DB�� ���� �˻� */
   @RequestMapping(value = "/zipcode_find_ok.nhn", method=RequestMethod.POST)
   public ModelAndView zipcode_find_ok(
         @RequestParam String dong,
         HttpServletRequest request,
         HttpServletResponse response) throws Exception{
         
      System.out.println(dong);
      
      List<ZipcodeBean2> zipcodeList = new ArrayList<ZipcodeBean2>();
      
      
      // ���� �������� �ּҸ� �˻��ؼ� �÷��ǿ� �����մϴ�.
      zipcodeList = this.memberService.findZipcode("%"+dong+"%");

      
      List<ZipcodeBean> zipcodeList2 = new ArrayList<ZipcodeBean>();
      
      for(int i = 0; i<zipcodeList.size(); i++) {
    	  ZipcodeBean2 zipcode_addr = zipcodeList.get(i);
    	  
    	  String zipcode = zipcode_addr.getZipcode(); // �����ȣ ����
    	  String sido = zipcode_addr.getSido(); // �����, ��⵵ ����
    	  String gugun = zipcode_addr.getSigungu(); // ��, ��
    	  String doro = zipcode_addr.getDoro(); // ����
    	  String addr = sido + " " +gugun + " " + doro; // ����� ������ ���� ���
    	  
    	  System.out.println(addr);
    	  ZipcodeBean zip = new ZipcodeBean();
    	  zip.setZipcode(zipcode);
    	  zip.setAddr(addr);
    	  
    	  System.out.println(zip.getAddr());
    	  // �÷��ǿ� �ּҸ� �����մϴ�.
    	  zipcodeList2.add(zip);
    	  
      }
      
      ModelAndView zipcodeM = new ModelAndView("member/zipcode_find");
      zipcodeM.addObject("zipcodeList",zipcodeList2);
      zipcodeM.addObject("dong", dong);
      
      
      return zipcodeM;
      
   }
   
}