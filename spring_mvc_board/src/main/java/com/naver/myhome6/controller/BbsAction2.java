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

	/* �ڷ�� �Է� �� */
	@RequestMapping(value = "/bbs_write.nhn")
	public String bbs_write() {

		return "bbs/bbs_write"; // bbs ������ bbs_write.jsp �� �������� ����

		/*
		 * ������ �����̳ʴ� �Ű����� BbsBean ��ü�� �����ϰ� BbsBean ��ü�� setter �޼������ ȣ���Ͽ� ����� �Է°��� �����մϴ�.
		 * �Ű������� �̸��� setter�� property�� ��ġ�ϸ� �˴ϴ�.
		 * 
		 * �ڷ�� ���� ÷�� ���� Ŭ���ؼ� �̹��� ������ �� ��� : �ڵ� ���� ��ħ ���� (window -> Preferencs ->
		 * workspace -> Refresh using native hooks or polling üũ) �ϰ� 5�� ���� ���� �� Ȯ���ϼ���
		 * 
		 * CommonsMultiartResolver ���� : ���� ���ε� ����� ������ ���� Ŭ���� �Դϴ�.
		 * 
		 * ������ �����̳ʴ� �Ű����� BbsBean ��ü�� �����ϰ� BbsBean ��ü�� setter �޼������ ȣ���Ͽ� ����� �Է°��� �����մϴ�.
		 * �Ű������� �̸��� setter�� property�� ��ġ�ϸ� �˴ϴ�.
		 */
	}

	// �Խ��� ����
	@RequestMapping(value = "/bbs_write_ok.nhn", method = RequestMethod.POST)
	public String bbs_write_ok(BbsBean bbsbean) throws Exception {

		MultipartFile uploadfile = bbsbean.getUploadfile();

		if (!uploadfile.isEmpty()) {
			// ���� ���ϸ� ���ؿ���
			String fileName = uploadfile.getOriginalFilename();

			// ���� ���ϸ� ����
			bbsbean.setBbs_original(fileName);

			// ������ ���� �̸� : ���� ��+��+��
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); // ���� �⵵�� ���մϴ�.
			int month = c.get(Calendar.MONTH) + 1; // ���� ���� ���մϴ�.
			int date = c.get(Calendar.DATE); // ���� ���� ���մϴ�.
			String homedir = saveFolder + "/" + year + "-" + month + "-" + date;

			// ���� ��ü �����մϴ�.
			File path1 = new File(homedir);

			// ������ �����ϴ��� Ȯ���մϴ�.
			if (!(path1.exists())) {
				System.out.println("���� ������");
				path1.mkdirs(); // ���ο� ������ ����
			}

			// ����� ���մϴ�.
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
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;
			System.out.println(refileName);

			// ����Ŭ ��� ����� ��
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			// transferTo(File path) : ���ε��� ������ �Ű������� ��ο� �����մϴ�.
			uploadfile.transferTo(new File(saveFolder + fileDBName));
			// �ٲ� ���ϸ����� ����
			bbsbean.setBbs_file(fileDBName);
		}

		this.bbsService.insertBbs(bbsbean);

		return "redirect:bbs_list.nhn";

	}

	// �Խ��� ���
	@RequestMapping(value = "/bbs_list.nhn")
	public ModelAndView bbs_list(
			HttpServletRequest request
			)throws Exception {
			
		int page=1;
		int limit=10; //��� �ʱⰪ
		HttpSession session = request.getSession();
		
		if(request.getParameter("page")!= null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		// �ڷ�� ���� ���� �� ����� ���� ���� �� limit���� ���� �մϴ�.
		// �Ʒ��κ��� �ּ��� �ް��  limit=3���� �����˴ϴ�.
		// ������ ������ limit�� �ִ��� üũ
		
		if(session.getAttribute("limit")!=null) {
			limit=(Integer)session.getAttribute("limit");
		}
		
		// ����� limit�� �ִ��� üũ
		if(request.getParameter("limit")!=null) {
			limit=Integer.parseInt(request.getParameter("limit"));
			session.setAttribute("limit", limit);
		}
		
		System.out.println("limit="+limit);
		System.out.println("page="+page);
		
		
		// �� ����Ʈ�� ����
		int listcount = bbsService.getListCount();

		// �Խñ��� ���� ����
		List<BbsBean> bbslist = new ArrayList<BbsBean>();
		
		System.out.println("limit : " + limit);
		
		int maxpage = (listcount + limit - 1) / limit; // �� ������ ��
		int startpage = ((page - 1) / 5) * 5 + 1; // ���� �������� ǥ���� ����������
		int endpage = startpage + 5 - 1; // ���� �������� ǥ���� ������ ������ ��

		if (endpage > maxpage) endpage = maxpage;
		if (endpage < page) page = endpage;
		// ������ �������� ��� limit �������� �þ�� ������ ���������� ������ ũ�� �̸� �����ش�
		
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

	// �Խ��� ���뺸�� , ���� , ���� , �亯
	@RequestMapping(value = "/bbs_cont.nhn")
	public ModelAndView bbs_cont(@RequestParam(value = "bbs_num") int bbs_num, @RequestParam(value = "page") int page,
			@RequestParam(value = "state") String state) throws Exception {

		if (state.equals("cont")) { // ���뺸�� �϶�!
			// ��ȸ�� �÷��ֱ� ���� �޼���
			bbsService.bbsHit(bbs_num);
		}
		// Ŭ���� �Խñۿ� ���� ���� �������� ���� �޼���
		BbsBean bbs = bbsService.getBbsCont(bbs_num);

		ModelAndView mv = new ModelAndView();
		mv.addObject("bbs", bbs);
		mv.addObject("page", page);

		// ���뺸�� �ȿ��� ����,����,�亯�� �������� �̵��ϱ� ���ؼ�
		if (state.equals("cont")) { // ���뺸��
			mv.setViewName("bbs/bbs_cont");
		} else if (state.equals("edit")) { // ������
			mv.setViewName("bbs/bbs_edit");
		} else if (state.equals("del")) { // ������
			mv.setViewName("bbs/bbs_del");
		} else if (state.equals("reply")) { // �亯��
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

			// �⺻ ������ ����
			File DelFile = new File(saveFolder + bbs2.getBbs_file());
			if (DelFile.exists()) {
				DelFile.delete(); // ���� ���� ������ �����Ѵ�.
			}

			if (!uploadfile.isEmpty()) {

				// ���� ���ϸ� ���ؿ���
				String fileName = uploadfile.getOriginalFilename();

				// ���� ���ϸ� ����
				bbs.setBbs_original(fileName);

				// ������ ���� �̸� : ���� ��+��+��
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR); // ���� �⵵�� ���մϴ�.
				int month = c.get(Calendar.MONTH) + 1; // ���� ���� ���մϴ�.
				int date = c.get(Calendar.DATE); // ���� ���� ���մϴ�.
				String homedir = saveFolder + "/" + year + "-" + month + "-" + date;

				// ���� ��ü �����մϴ�.
				File path1 = new File(homedir);

				// ������ �����ϴ��� Ȯ���մϴ�.
				if (!(path1.exists())) {
					System.out.println("���� ������");
					path1.mkdirs(); // ���ο� ������ ����
				}

				// ����� ���մϴ�.
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
				String refileName = "bbs" + year + month + date + random + "." + fileExtension;
				System.out.println(refileName);

				// ����Ŭ ��� ����� ��
				String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
				System.out.println("fileDBName = " + fileDBName);

				// transferTo(File path) : ���ε��� ������ �Ű������� ��ο� �����մϴ�.
				uploadfile.transferTo(new File(saveFolder + fileDBName));
				// �ٲ� ���ϸ����� ����
				bbs.setBbs_file(fileDBName);
			}

			out.println("<script>");
			out.println("alert('������ ���� �߽��ϴ�.')");
			out.println("location.href = './bbs_cont.nhn?page=" + page + "&bbs_num=" + bbs_num + "&state=cont'");
			out.println("</script>");
			out.close();

			bbsService.editBbs(bbs);
		} else {
			out.println("<script>");
			out.println("alert('��й�ȣ�� �ٸ��ϴ�.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		return null;
	}

	// ���� �ٿ�ε�
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
				DelFile.delete(); // ���� ���� ������ �����Ѵ�.
			}
			out.println("<script>");
			out.println("alert('���� �Ǿ����ϴ�')");
			out.println("location.href = './bbs_list.nhn?page=" + page + "'");
			out.println("</script>");
			out.close();
			bbsService.deleteBbs(num);
		} else {
			out.println("<script>");
			out.println("alert('��й�ȣ�� �ٸ��ϴ�.')");
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
	
	// �ڷ� �˻� ���
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
			// �������� ��
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
