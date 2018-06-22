<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">게시판 입력폼</h2>
		<form action="post" action="./board_write_ok.nhn">
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
							id="board_content" class="input_box"></textarea></td>
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