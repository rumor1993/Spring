<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">게시판 답글폼</h2>
		<form method="post" action="./board_reply_ok.nhn">

			<input type="hidden" name="board_num" value="${board.board_num }">
			<input type="hidden" name="board_re_ref" value="${board.board_re_ref }"> 
			<!-- 원본 글번호를 참조해서 그 아래에 위치하도록 한다. -->
			<input type="hidden" name="board_re_lev" value="${board.board_re_lev+1 }">
			<!-- 답글의 레벨을 통해 답글간의 구별을 해준다 -->
			<input type="hidden" name="board_re_seq" value="${board.board_re_seq+1 }">
			<!-- 답글들간의 순서를 정해준다. -->
			<input type="hidden" name="page" value="${page }">
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
						class="input_box" value="${board.board_subject }"></td>
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