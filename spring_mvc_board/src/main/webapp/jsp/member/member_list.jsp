<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<%@ page import = "javax.naming.*" %>

<%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core" %>
	
<%
	request.setCharacterEncoding("UTF-8");
%>
	
	

<!DOCTYPE html>
<html>
<head>
	
	<style>
		table{width:200px; margin: 0 auto}
		td{text-align:center; }
		tr:nth-child(1) {background:pink}
	</style>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<table border=1>
		<tr>
		<td colspan=3>ȸ�� ���</td>
		</tr>
		<tr>
			<td>���̵�</td><td>�̸�</td><td>����</td>
		</tr>
		
		<c:forEach var="m" items="${totallist }">
			<tr>
			<td>
				<a href ='member_info.nhn?id=${m.member_id }'> ${m.member_id }</a>
			</td>
			<td>
			${m.member_email}
			</td>
			<td>
				<a href ='member_delete.nhn?id=${m.member_id }'>����</a>
			</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan=3>
			<a href='bbs_list.nhn'> �������� ���ư��� </a>
		</td>
		</tr>
		</table>

</body>
</html>