<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h2 class="boarddel_title">게시물 삭제</h2>
	<form method="post" action="board_del_ok.nhn">
		<input type="hidden" name="board_num" value="${board.board_num }">
		<input type="hidden" name="page" value="${page }">
		<table id="boarddel_t">
			<tr>
				<th>글쓴이</th>
				<td><input name="board_name" id="board_name" size="14"
					class="input_box" value="${board.board_name }" readonly></td>
			</tr>

			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd" size="14"
					class="input_box"></td>
			</tr>
		</table>
		<div id="bbswrite_menu">
			<input type="submit" value="삭제" class="input_button"> <input
				type="reset" value="취소" class="input_button"
				onclick="$('#board_name').focus();">
		</div>
	</form>


</body>
</html>