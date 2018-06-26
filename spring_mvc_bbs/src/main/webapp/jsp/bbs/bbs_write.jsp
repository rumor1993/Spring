<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/bbs.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function() {
		if ($("#upfile").val() == "") {
			$("#close").hide();
		}
		$("#upfile").change(function() {
			var a = $("#upfile").val().split('\\');
			$("#filevalue").text(a[a.length-1]);
			$("#close").show();
		})

		$("#close").click(function() {
			$("#close").hide();
			$("#filevalue").text("")
		})
	})

	function check() {
		if ($("#bbs_pass").val() == "") {
			alert("비밀번호를 입력하세요")
			$("#bbs_pass").focus();
			return false;
		}
		if ($("#bbs_subject").val() == "") {
			alert("제목을 입력하세요.");
			$("#bbs_subject").focus();
			return false;
		}
		if ($("#bbs_content").val() == "") {
			alert("내용을 입력하세요")
			$("#bbs_content").focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">자료실 입력폼</h2>
		<form method="post" action="./bbs_write_ok.nhn"
			onsubmit="return check()" enctype="multipart/form-data">
			<table id="bbswrite_t">
				<tr>
					<th>글쓴이</th>
					<td><input name="bbs_name" id="bbs_name" size="14"
						class="input_box"></td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="bbs_pass" name="bbs_pass"
						size="14" class="input_box"></td>
				</tr>

				<tr>
					<th>글제목</th>
					<td><input name="bbs_subject" id="bbs_subject" size="40"
						class="input_box"></td>
				<tr>
					<th>글내용</th>
					<td><textarea rows="8" cols="50" name="bbs_content"
							id="bbs_content" class="input_box"></textarea></td>
				</tr>

				<tr>
					<th>파일첨부</th>
					<td>
						<!-- <input type="file" name="uploadfile" > --> <label
						for="upfile"><img src="resources/images/file_open.png"
							alt="파일열기"></label> <input type="file" id="upfile"
						name="uploadfile"> <span id="filevalue"></span>&nbsp; <span
						id="close"><img src="resources/images/cancel.png"></span>
					</td>
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