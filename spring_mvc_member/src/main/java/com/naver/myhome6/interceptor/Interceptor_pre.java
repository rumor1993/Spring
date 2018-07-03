package com.naver.myhome6.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor_pre extends HandlerInterceptorAdapter {

	/*
	 * 요청 URL이 다음과 같은 경우 요청을 처리하기 전에 아래의 메서드를 수행합니다.
	 * 
	 * 
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			System.out.println("preHandle(): " + request.getRequestURI() + "요청중");
			// id라는 속성을 가진 값이 null일 경우 로그인 페이지 이동

			if (request.getSession().getAttribute("id") == null) {
				response.sendRedirect("member_login.nhn");
				return false; // 처리를 끝냄 - 컨트롤로 요청이 가지 않습니다.

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// null 아니면
		return true;

	}
}
