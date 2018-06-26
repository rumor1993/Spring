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
	
	/* 자료실 입력 폼 */
	@RequestMapping(value="/bbs_write.nhn")
	public String bbs_write() {
		
		return "bbs/bbs_write"; // bbs 폴더의 bbs_write.jsp 뷰 페이지가 실행
		
		/*
		 * 스프링 컨테이너는 매개변수 BbsBean 객체를 생성하고
		 * BbsBean 객체의 setter 메서드들을 호출하여 사용자 입력값을 설정합니다.
		 * 매개변수의 이름과 setter의 property가 일치하면 됩니다.
		 */
	}
	
	@RequestMapping(value="/bbs_write_ok.nhn", method = RequestMethod.POST)
	public String bbs_write_ok(BbsBean bbsbean) throws Exception {
		
		MultipartFile uploadfile = bbsbean.getUploadfile();
		
		if(!uploadfile.isEmpty()) {
			// 원래 파일명 구해오기
			String fileName = uploadfile.getOriginalFilename();
			
			// 원래 파일명 저장
			bbsbean.setBbs_original(fileName);
			
			// 생성할 폴더 이름 : 오늘 년+월+일
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);	  // 오늘 년도를 구합니다.
			int month = c.get(Calendar.MONTH)+1; // 오늘 월을 구합니다.
			int date = c.get(Calendar.DATE);	// 오늘 일을 구합니다.
			String homedir = saveFolder+"/"+year+"-"+ month + "-" + date;
			
			// 파일 객체 생성합니다.
			File path1 = new File(homedir);
			
			// 폴더가 존재하는지 확인합니다.
			if(!(path1.exists())) {
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
			String refileName = "bbs"+year+month+date+random+"."+fileExtension;
			System.out.println(refileName);
			
			// 오라클 디비에 저장될 값
			String fileDBName = "/"+year+"-"+month+"-"+date+"/"+refileName;
			System.out.println("fileDBName = " + fileDBName);
			
			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(saveFolder + fileDBName));
			// 바뀐 파일명으로 저장
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
