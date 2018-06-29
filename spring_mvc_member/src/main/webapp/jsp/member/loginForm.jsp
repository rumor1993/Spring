<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,700">
<link rel="stylesheet" href="./resources/css/loginForm.css">
</head>
<!-- 비밀번호찾기 팝업창 -->
<script>
	function pwd_find(){
		window.open("pwd_find.nhn","비밀번호찾기","width=400","height=300");
		// 자바스크립트 window 객체의 open( 팝업창 경로와 , 팝업창이름, 팝업창 속성)
		// 메서드로 새로운 팝압창을 만듬, 폭이 300, 높이가 300인 새로운 팝업창으로 만듬, 단위는 픽셀
	}
</script>
<body>

	<div id="login">
		<form name='form-login'>
			<span class="fontawesome-user"></span> <input type="text" id="user"
				placeholder="Username"> <span class="fontawesome-lock"></span>
			<input type="password" id="pass" placeholder="Password">
			 <input type="submit" value="로그인" class="input_button">
			 <input type="reset" value="취소" class="input_button" onclick="$('id').focus();">
			 <input type="button" value="회원가입" class="input_button" onclick="location='member_join.nhn'">
			 <input type="button" value="비번찾기"class="input_button" onclick="pwd_find()">
		</form>
	</div>
</body>
</html>