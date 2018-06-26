<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
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
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">게시판 입력폼</h2>
		<form method="post" action="./board_write_ok.nhn">
			<table id="bbswrite_t">
				<tr>
					<th>글쓴이</th>
					<td><input name="board_name" id="board_name" size="14"
						class="input_box"></td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="board_pass" size="14"
						class="input_box"></td>
				</tr>

				<tr>
					<th>글제목</th>
					<td><input name="board_subject" id="board_subject" size="40"
						class="input_box"></td>
				<tr>
					<th>글내용</th>
					<td><textarea rows="8" cols="50" name="board_content"
							id="board_content" class="input_box" ></textarea></td>
				</tr>
			</table>

			<div id="bbswrite_menu">
				<input type="submit" value="등록" class="input_button"> <input
					type="reset" value="취소" class="input_button"
					onclick="$('#board_name').focus();">
			</div>
		</form>
	</div>
</body>
</html>