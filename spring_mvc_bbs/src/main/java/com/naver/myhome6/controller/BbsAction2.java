package com.naver.myhome6.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.naver.myhome6.dao.BbsService;
import com.naver.myhome6.model.BbsBean;


@Controller
public class BbsAction2 {
	
	@Autowired
	private BbsService bbsService;
	private String saveFolder = "C:\\Users\\user1\\Documents\\workspace-sts-3.9.4.RELEASE\\spring_mvc_bbs\\src\\main\\webapp\\resources\\upload";  
	
	/* �ڷ�� �Է� �� */
	@RequestMapping(value="/bbs_write.nhn")
	public String bbs_write() {
		
		return "bbs/bbs_write"; // bbs ������ bbs_write.jsp �� �������� ����
		
		/*
		 * ������ �����̳ʴ� �Ű����� BbsBean ��ü�� �����ϰ�
		 * BbsBean ��ü�� setter �޼������ ȣ���Ͽ� ����� �Է°��� �����մϴ�.
		 * �Ű������� �̸��� setter�� property�� ��ġ�ϸ� �˴ϴ�.
		 */
	}
	
	@RequestMapping(value="/bbs_write_ok.nhn", method = RequestMethod.POST)
	public String bbs_write_ok(BbsBean bbsbean) throws Exception {
		
		MultipartFile uploadfile = bbsbean.getUploadfile();
		
		if(!uploadfile.isEmpty()) {
			// ���� ���ϸ� ���ؿ���
			String fileName = uploadfile.getOriginalFilename();
			
			// ���� ���ϸ� ����
			bbsbean.setBbs_original(fileName);
			
			// ������ ���� �̸� : ���� ��+��+��
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);	  // ���� �⵵�� ���մϴ�.
			int month = c.get(Calendar.MONTH)+1; // ���� ���� ���մϴ�.
			int date = c.get(Calendar.DATE);	// ���� ���� ���մϴ�.
			String homedir = saveFolder+"/"+year+"-"+ month + "-" + date;
			
			// ���� ��ü �����մϴ�.
			File path1 = new File(homedir);
			
			// ������ �����ϴ��� Ȯ���մϴ�.
			if(!(path1.exists())) {
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
			String refileName = "bbs"+year+month+date+random+"."+fileExtension;
			System.out.println(refileName);
			
			// ����Ŭ ��� ����� ��
			String fileDBName = "/"+year+"-"+month+"-"+date+"/"+refileName;
			System.out.println("fileDBName = " + fileDBName);
			
			// transferTo(File path) : ���ε��� ������ �Ű������� ��ο� �����մϴ�.
			uploadfile.transferTo(new File(saveFolder + fileDBName));
			// �ٲ� ���ϸ����� ����
			bbsbean.setBbs_file(fileDBName);
		}
		
		this.bbsService.insertBbs(bbsbean);
		
		
		return "redirect:bbs_list.nhn";
		
		
	}
	
	@RequestMapping(value="/bbs_list.nhn")
	public String bbs_list(BbsBean bbsbean) throws Exception {
		
		return "redirect:bbs_list.jsp";
		
	}
}
