package com.naver.myhome6.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor_pre extends HandlerInterceptorAdapter {

	/*
	 * ��û URL�� ������ ���� ��� ��û�� ó���ϱ� ���� �Ʒ��� �޼��带 �����մϴ�.
	 * 
	 * 
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			System.out.println("preHandle(): " + request.getRequestURI() + "��û��");
			// id��� �Ӽ��� ���� ���� null�� ��� �α��� ������ �̵�

			if (request.getSession().getAttribute("id") == null) {
				response.sendRedirect("member_login.nhn");
				return false; // ó���� ���� - ��Ʈ�ѷ� ��û�� ���� �ʽ��ϴ�.

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// null �ƴϸ�
		return true;

	}
}
