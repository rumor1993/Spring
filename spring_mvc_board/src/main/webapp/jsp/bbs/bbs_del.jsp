<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h2 class="bbsdel_title">게시물 삭제</h2>
	<form method="post" action="bbs_del_ok.nhn">
		<input type="hidden" name="bbs_num" value="${bbs.bbs_num }">
		<input type="hidden" name="page" value="${page }">
		<table id="bbsdel_t">
			<tr>
				<th>글쓴이</th>
				<td><input name="bbs_name" id="bbs_name" size="14"
					class="input_box" value="${bbs.bbs_name }" readonly></td>
			</tr>

			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="bbs_pass" size="14"
					class="input_box"></td>
			</tr>
		</table>
		<div id="bbswrite_menu">
			<input type="submit" value="삭제" class="input_button"> <input
				type="reset" value="취소" class="input_button"
				onclick="$('#bbs_name').focus();">
		</div>
	</form>


</body>
</html>