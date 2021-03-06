package com.naver.myhome1.sample2;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter implements Outputter {
	
	String filePath; // 출력파일 경로와 파일이름을 저장할 변수
	
	public FileOutputter() {
		System.out.println("2. 여기가 FileOutputter 생성자입니다.");
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		System.out.println("3. 여기는 FileOutoutter.java의 setFilePath() 입니다.");
	}// 스프링에서 setter 메서드를 활용한 setter DI 설정

	@Override
	public void output(String message) throws IOException {
		System.out.println("7. 여기는 FileOutputter.java의 output() 입니다.");
		FileWriter out = new FileWriter(filePath);
		out.write(message); //메세지 기록함
		out.close(); // 출력 객체를 닫음
	}
	
	
}
