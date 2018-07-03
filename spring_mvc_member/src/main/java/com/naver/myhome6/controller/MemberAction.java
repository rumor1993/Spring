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
	// ������ �ʿ��� ���
	// private String saveFolder="C:/Program Files/Apache Software Foundation/Tomcat
	// 8.5 /......

	private String saveFolder = "C:\\Users\\user1\\git\\Spring2\\spring_mvc_member\\src\\main\\webapp\\resources\\upload";

	/* �α��� �� �� */
	@RequestMapping(value = "/member_login.nhn")
	public String member_login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/loginForm";
		// member ������ member_login.jsp �� ������ ����
	}

	/* ����˻� �� */
	@RequestMapping(value = "/zipcode_find.nhn")
	public String zipcode_find() {
		return "member/zipcode_find";
		// member ������ zipcode_find.jsp �� ������ ����
	}

	/* �α��� ���� */
	@RequestMapping(value = "/member_login_ok.nhn", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView member_login_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// HttpSession Ŭ������ ���� ��ü�� �����ؼ� �α��� ���� ó���� �ϱ� ���ؼ� �Դϴ�.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // ��� ��Ʈ�� ��ü ����
		HttpSession session = request.getSession(); // ���� ��ü ����

		String id = request.getParameter("id").trim();
		String pwd = request.getParameter("pwd").trim();

		MemberBean m = this.memberService.userCheck(id);

		if (m == null) { // ��� ���� ���� ȸ���϶�
			out.println("<script>");
			out.println("alert('��ϵ� ���̵� �����ϴ�.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			if (m.getJoin_pwd().equals(pwd)) { // ����� ���� ���
				session.setAttribute("id", id);

				String join_name = m.getJoin_name();
				String join_file = m.getJoin_file();
				session.setAttribute("join_name", join_name);
				session.setAttribute("join_file", join_file);

				// jsp ������ view.jsp�� �̵�
				ModelAndView mv = new ModelAndView("view");
				return mv;
			} else { // ����� �ٸ����
				out.println("<script>");
				out.println("alert('����� �ٸ��ϴ�..')");
				out.println("history.go(-1)");
				out.println("</script>");

			}
		}
		return null;

	}

	/* ȸ������ �� ���� */
	@RequestMapping(value = "/member_join.nhn")
	public String member_join() throws Exception {
		return "member/member_join";
	}

	@RequestMapping(value = "/member_join_ok.nhn", method = RequestMethod.POST)
	public void member_join_ok(MemberBean member, HttpServletResponse response) throws Exception {

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

		if (!UpFile.isEmpty()) { // ÷���� ���� ������ �ִٸ�
			String fileName = UpFile.getOriginalFilename(); // ���� ���ϸ� ����

			member.setJoin_original(fileName);
			System.out.println(member.getJoin_original());

			Calendar c = Calendar.getInstance(); // �߻�Ŭ�����μ� ����� �ú��� ��ȯ
			int year = c.get(Calendar.YEAR); // �⵵��
			int month = c.get(Calendar.MONTH) + 1; // �� �� . +1�� �� ������ 1���� 0 �̶�
			int date = c.get(Calendar.DATE);

			String homedir = saveFolder + "/" + year + "-" + month + "-" + date; // ���ο� ���� ����
			File path1 = new File(homedir);
			if (!path1.exists()) { // ���ο� ������ ���� ���� ������
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

	/* ���̵� �ߺ� üũ */
	@RequestMapping(value = "/checkMemberId.nhn")
	public void id_check(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberService.checkMemberId(id);
		PrintWriter out = response.getWriter();

		out.println(result);
	}

	/* �����ȣ DB�� ���� �˻� */
	@RequestMapping(value = "/zipcode_find_ok.nhn", method = RequestMethod.POST)
	public ModelAndView zipcode_find_ok(@RequestParam String dong, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(dong);

		List<ZipcodeBean2> zipcodeList = new ArrayList<ZipcodeBean2>();

		// ���� �������� �ּҸ� �˻��ؼ� �÷��ǿ� �����մϴ�.
		zipcodeList = this.memberService.findZipcode("%" + dong + "%");

		List<ZipcodeBean> zipcodeList2 = new ArrayList<ZipcodeBean>();

		for (int i = 0; i < zipcodeList.size(); i++) {
			ZipcodeBean2 zipcode_addr = zipcodeList.get(i);

			String zipcode = zipcode_addr.getZipcode(); // �����ȣ ����
			String sido = zipcode_addr.getSido(); // �����, ��⵵ ����
			String gugun = zipcode_addr.getSigungu(); // ��, ��
			String doro = zipcode_addr.getDoro(); // ����
			String addr = sido + " " + gugun + " " + doro; // ����� ������ ���� ���

			System.out.println(addr);
			ZipcodeBean zip = new ZipcodeBean();
			zip.setZipcode(zipcode);
			zip.setAddr(addr);

			System.out.println(zip.getAddr());
			// �÷��ǿ� �ּҸ� �����մϴ�.
			zipcodeList2.add(zip);

		}

		ModelAndView zipcodeM = new ModelAndView("member/zipcode_find");
		zipcodeM.addObject("zipcodeList", zipcodeList2);
		zipcodeM.addObject("dong", dong);

		return zipcodeM;

	}

	/* ȸ������ ���� */
	@RequestMapping(value = "/member_edit.nhn", method = RequestMethod.GET)
	public ModelAndView member_edit(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		MemberBean m = this.memberService.userCheck(id);

		String join_tel = m.getJoin_tel();
		// java.util ��Ű���� StringTokenizer Ŭ������ ù��° �������ڸ�
		// �ι�° -�� �������� ���ڿ��� �Ľ��� �ݴϴ�.
		// �� ��ȭ��ȣ ����

		StringTokenizer st01 = new StringTokenizer(join_tel, "-");
		String join_tel1 = st01.nextToken(); // ù��° (���� ����ȭ��ȣ ����)
		String join_tel2 = st01.nextToken(); // �ι�° (��� �ڸ�)
		String join_tel3 = st01.nextToken(); // ����° (�� �ڸ�)

		String join_phone = m.getJoin_phone();
		// java.util ��Ű���� StringTokenizer Ŭ������ ù��° �������ڸ�
		// �ι�° -�� �������� ���ڿ��� �Ľ����ݴϴ�.
		// �޴��� ��ȣ ����

		StringTokenizer st02 = new StringTokenizer(join_phone, "-");
		String join_phone1 = st02.nextToken(); // ù��° �ڸ�
		String join_phone2 = st02.nextToken(); // �ι�° �ڸ�
		String join_phone3 = st02.nextToken(); // ����° �ڸ�

		String join_email = m.getJoin_email();
		// java.util ��Ű���� StringTokenizer Ŭ������ ù��° �������ڸ�
		// �ι�° @�� �������� ���ڿ��� �Ľ��� �ݴϴ�.

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
		// ���� ��ü�� ����
		String id = (String) session.getAttribute("id");

		String join_tel = member.getJoin_tel1() + "-" + member.getJoin_tel2() + "-" + member.getJoin_tel3();
		String join_phone = member.getJoin_phone1() + "-" + member.getJoin_phone2() + "-" + member.getJoin_phone3();

		// ���Ͼ��̵� : member.getJoin_mailid()
		// ���ϵ����� : member. getJoin_maildomain()

		String join_email = member.getJoin_mailid() + "@" + member.getJoin_maildomain(); // ���� �ּ�
		member.setJoin_tel(join_tel);
		member.setJoin_phone(join_phone);
		member.setJoin_email(join_email);

		MultipartFile UpFile = member.getJoin_profile();

		if (!UpFile.isEmpty()) { // ÷���� ���� ������ �ִٸ�
			File DelFile = new File(saveFolder + member.getJoin_file());
			if (DelFile.exists()) { // ���� ���� ������ �����ϸ�
				DelFile.delete(); // ���� ���� ���ϸ��� ����
			}

			String fileName = UpFile.getOriginalFilename(); // ���� ���ϸ� ����

			member.setJoin_original(fileName);
			System.out.println(member.getJoin_original());

			Calendar c = Calendar.getInstance(); // �߻�Ŭ�����μ� ����� �ú��� ��ȯ
			int year = c.get(Calendar.YEAR); // �⵵��
			int month = c.get(Calendar.MONTH) + 1; // �� �� . +1�� �� ������ 1���� 0 �̶�
			int date = c.get(Calendar.DATE);

			String homedir = saveFolder + "/" + year + "-" + month + "-" + date; // ���ο� ���� ����
			File path1 = new File(homedir);
			if (!path1.exists()) { // ���ο� ������ ���� ���� ������
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
		memberService.updateMember(member);
		// ���� �޼��� ȣ��

		// �̹��� ������ �ٷ� ���� �ȵ˴ϴ�.
		ModelAndView mv = new ModelAndView("view");
		mv.addObject("join_name", member.getJoin_name());
		mv.addObject("join_file", member.getJoin_file());
		mv.addObject("state", "edit");

		return mv;
	}

	/* ȸ������ ���� �� */
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

	/* �α׾ƿ� �� */
	@RequestMapping(value = "/member_logout.nhn")
	public String member_logout() {
		return "member/member_logout";
		// member ������ member_logout.jsp �� ������ ����
	}

	/*
	 * servlet-context.xml���� �����߱� ������ �Ʒ��� ������ ���� �����մϴ�. <view-controller path =
	 * "/pwd_find.nhn" view-name="member/pwd_find"/> ��й�ȣã�� ��
	 * 
	 * @RequestMapping(value="/pwd_find.nhn") public String pwd_find(){ return
	 * "member/pwd_find"; // member ������ pwd_find.jsp �� ������ ����
	 * 
	 */

	@RequestMapping(value = "pwd_find_ok.nhn", method = RequestMethod.POST)
	public ModelAndView pwd_find_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();

		Map pm = new HashMap();
		// �÷��� Map�� Ű�� �� �����մϴ�.
		pm.put("id", id);
		pm.put("name", name);

		MemberBean member = this.memberService.findpwd(pm);
		

		if (member == null) { // ȸ�� ���̵�� �̸��� ���� �ʴ� ���
			out.println("<script>");
			out.println("alert('ȸ�����̵�� �̸��� ���� �ʽ��ϴ�!')");
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