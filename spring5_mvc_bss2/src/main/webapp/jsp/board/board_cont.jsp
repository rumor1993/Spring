<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
#boardcont_wrap{
	width:400px; padding:25px;
	border:1px solid blue; border-radius:20px; 
	box-shadow:15px 15px 15px red; 
	margin-top:70px; margin-left:auto; margin-right:auto;
}
#boardcont_wrap .boardcont_title{
	margin:10px 0 0 120px; width:170px; font-size:20px;
	background:skyblue; border-radius:20px;
	text-align:center; padding:3px;
}
#boardcont_t{
	margin:15px 0 0 70px;
}
#boardcont_t th{
	background:skyblue; border-radius:15px;
}
#boardcont_menu{
	margin:15px 0 0 100px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<table id=boardcont_t>
		<c:set var="d" value="${board }" />
		<tr>
			<th colspan=2>MVC 게시판</th>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${d.board_name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${d.board_subject }</td>
		</tr>
		<tr>
			<td>내용</td>
			<!-- <pre> 태그로 입력한 그래도 출력되게 합니다. 엔터도 잘나옵니다. -->
			<!-- <pre> ${bcont.board_content} </pre> -->
			<td><textarea rows="8" cols="50">${d.board_content }</textarea></td>
		</tr>
	</table>
	
	<div id="boardcont_menu" style="margin-left: 200px">
		<input type="button" value="수정" class="input_button"
			onclick="location='board_cont.nhn?board_num=${d.board_num}&page=${page }&state=edit'">
		<input type="button" value="삭제" class="input_button"
			onclick="location='board_cont.nhn?board_num=${d.board_num}&page=${page }&state=del'">
		<input type="button" value="답변" class="input_button"
			onclick="location='board_cont.nhn?board_num=${d.board_num}&page=${page }&state=reply'">
		<input type="button" value="목록" class="input_button"
			onclick="location='board_list.nhn?page=${page }'">

	</div>
</body>
</html>