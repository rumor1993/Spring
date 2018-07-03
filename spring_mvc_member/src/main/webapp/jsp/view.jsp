<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src ="http://code.jquery.com/jquery-latest.js"></script>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sessionScope.id }">
		<script>
			alert("다시 로그인 해주세요!");
			location = "../member_login.nhn";
		</script>
	</c:if>
	
	<c:if test="${state == 'edit' }">
		<script>
			alert('수정완료 되었습니다');
		</script>
	</c:if>
	<div id="main_wrap">
		<h2 class="main_title">사용자 메인화면</h2>
		<form method="post" action="member_logout.nhn">
		<table id="main_t">
			<tbody>
				<tr>
					<th colspan="2"> 
						<input type="button" value="정보수정" class="input_button" onclick="location='member_edit.nhn'">
						<input type="button" value="회원탈퇴" class="input_button" onclick="location='member_del.nhn'">
						<input type="button" value="로그아웃" class="input_button" onclick="location='member_logout.nhn'">
					</th>
				</tr>		
				
				<tr>
						<th> 회원이름 </th> 
						<td> ${join_names }님 로그인을 환영합니다.<td>
				</tr>	
				<tr>
						<th>프로필 사진</th>
						<td>
							<c:if test="${empty join_file }">
								&nbsp;
							</c:if>
							
							<c:if test="${!empty join_file }">
								<img alt="프로필사진" src="./resources/upload${join_file }" height="100" width="100">
							</c:if>
						</td>
				</tr>
			</tbody>
		</table>
		
		</form>
	</div>
</body>
</html>