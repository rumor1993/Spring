<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function id_check() {
		var id = $("#join_id").val();
		var check = /^[a-z1-9_]{4,12}$/;

		if (id == '') {
			alert("아이디를 입력하세요");
			$("#join_id").focus();
			$('#idcheck').text("");
		} else if (!check.test(id)) {
			alert('길이 1~12자/소문자/숫자/_만 허용');
			$("#join_id").focus();
			$('#join_id').val("");
			$('#idcheck').text("");
		} else {
			$.ajax({
				url : "./checkMemberId.nhn",
				type : "POST",
				data : {"id" : id},
				success : function(data) {
					if (data == 0) {
						$('#idcheck').text("가입 가능합니다.").css("color", "blue")
						checkconfirm = true;
						checkid = id
					} else {
						$("#idcheck").text("중복아이디 존재").css("color", "red")
					}
				}
			})
		}
	}

	function post_check() {
		window.open("zipcode_find.nhn", "우편번호검색", "width=420,height=500",
				"scrollbars=yes");

	}

	function post_search() {
		alert("우편번호 검색 버튼을 클릭하세요")
	}

	function domain_list() {
		/* 리스트에서 직접입력을 선택했을때 */
		if ($("#mail_list").val() == "0") // 직접입력
		{
			// @뒤에 도메인입력란을 공백처리
			$("#join_maildomain").val("");

			// @뒤의 도메인입력란을 쓰기가능
			$("#join_maildomain").attr("readOnly", false);

			// 도메인 입력란으로 입력대기상태
			$("#join_maildomain").focus();

		} else { // 리스트 목록을 선택했을때
			$("#join_maildomain").val($("#mail_list").val());
			$("#join_maildomain").attr("readOnly", true);
		}
	}

	function check() {
		if ($.trim($("#join_id").val()) == "") {
			alert("회원 아이디를 입력하세요!");
			$("#join_id").val("").focus();
			return false;
		}

/* 		if (checkconfirm == false || checkid != $("#join_id").val()) {
			alert("아이디 중복체크 하세요!");
			return false;
		} */

		if ($.trim($("#join_pwd1").val()) == "") {
			alert("비밀번호를 입력하세요!");
			$("#join_pwd1").val("").focus();
			return false;

		}
		if ($.trim($("#join_pwd2").val()) == "") {
			alert("비밀번호 확인란을 입력하세요!");
			$("#join_pwd2").val("").focus();
			return false;

		}
		if ($.trim($("#join_name").val()) == "") {
			alert("회원 이름을 입력하세요!");
			$("#join_name").val("").focus();
			return false;

		}
		if ($.trim($("#join_zip").val()) == "") {
			alert("우편번호를 입력하세요!");
			$("#join_zip").val("").focus();
			return false;

		}
		if ($.trim($("#join_addr1").val()) == "") {
			alert("주소를 입력하세요!");
			$("#join_zip").val("").focus();
			return false;

		}
		if ($.trim($("#join_addr2").val()) == "") {
			alert("나머지 주소를 입력하세요!");
			$("#join_zip").val("").focus();
			return false;

		}
		if ($.trim($("#join_tel2").val()) == "") {
			alert("전화번호 앞자리를 입력하세요!");
			$("#join_tel2").val("").focus();
			return false;

		}
		if ($.trim($("#join_tel3").val()) == "") {
			alert("전화번호 뒷자리를 입력하세요!");
			$("#join_tel2").val("").focus();
			return false;

		}
		if ($.trim($("#join_phone2").val()) == "") {
			alert("핸드폰 앞자리를 입력하세요!");
			$("#join_tel2").val("").focus();
			return false;

		}
		if ($.trim($("#join_phone3").val()) == "") {
			alert("핸드폰 뒷자리를 입력하세요!");
			$("#join_tel2").val("").focus();
			return false;

		}
		if ($.trim($("#join_mailid").val()) == "") {
			alert("이메일을 입력하세요!");
			$("#join_mailid").val("").focus();
			return false;

		}
		if ($.trim($("#join_maildomain").val()) == "") {
			alert("도메인을 입력하세요!");
			$("#join_maildomain").val("").focus();
			return false;

		}
		if ($.trim($("#join_mailid").val()) == "") {
			alert("이메일을 입력하세요!");
			$("#join_mailid").val("").focus();
			return false;

		}
	}
</script>
</head>
<body>
	<div id="join_wrap">
		<h2 class="join_title">회원가입</h2>
		<form name="f" method="post" action="member_join_ok.nhn"
			onsubmit="return check()" enctype="multipart/form-data">
			<table id="join_t">
				<tr>
					<th>회원아이디</th>
					<td><input name="join_id" id="join_id" size="14"
						class="input_box"> <input type="button" value="아이디 중복체크"
						class="input_button" onclick="id_check()">
						<div id="idcheck"></div></td>
				</tr>

				<tr>
					<th>회원비번</th>
					<td><input type="password" name="join_pwd" id="join_pwd1"
						size="14" class="input_box"></td>
				</tr>

				<tr>
					<th>회원비번확인</th>
					<td><input type="password" name="join_pwd2" id="join_pwd2"
						size="14" class="input_box"></td>
				</tr>

				<tr>
					<th>회원이름</th>
					<td><input name="join_name" id="join_name" size="14"
						class="input_box"></td>
				</tr>

				<tr>
					<th>우편번호</th>
					<td><input name="join_zip" id="join_zip" size="3"
						class="input_box" readonly onclick="post_search()"> <input
						type="button" value="우편번호검색" class="input_button"
						onclick="post_check()"></td>
				</tr>

				<tr>
					<th>주소</th>
					<td><input name="join_addr1" id="join_addr1" size="50"
						class="input_box" readonly onclick="post_search()"></td>
				</tr>

				<tr>
					<th>나머지 주소</th>
					<td><input name="join_addr2" id="join_addr2" size="37"
						class="input_box"></td>
				</tr>

				<tr>
					<th>집전화번호</th>
					<td><%@ include file="/jsp/include/tel_number.jsp"%>
						<select name="join_tel1">
							<c:forEach var="t" items="${tel}">
								<option value="${t}">${t}</option>
							</c:forEach>
					</select> - <input name="join_tel2" id="join_tel2" size="4" maxlength="4"
						class="input_box"> - <input name="join_tel3"
						id="join_tel3" size="4" maxlength="4" class="input_box"></td>
				</tr>

				<tr>
					<th>휴대전화번호</th>
					<td><%@ include file="/jsp/include/phone_number.jsp"%>
						<select name="join_phone1">
							<c:forEach var="p" items="${phone}">
								<option value="${p}">${p}</option>
							</c:forEach>
					</select> - <input name="join_phone2" id="join_phone2" size="4"
						maxlength="4" class="input_box"> - <input
						name="join_phone3" id="join_phone3" size="4" maxlength="4"
						class="input_box"></td>
				</tr>

				<tr>
					<th>전자우편</th>
					<td><input name="join_mailid" id="join_mailid" size="10"
						class="input_box"> @ <input name="join_maildomain"
						id="join_maildomain" size="20" class="input_box" readonly>
						<select name="mail_list" id="mail_list" onchange="domain_list()">
							<option value="">- 이메일 선택 -</option>
							<option value="daum.net">daum.net</option>
							<option value="nate.com">nate.com</option>
							<option value="naver.com">naver.com</option>
							<option value="hotmail.com">hotmail.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="0">직접입력</option>
					</select></td>
				</tr>

				<tr>
					<th>프로필사진</th>
					<td><input type="file" name="join_profile">
					</td>
				</tr>
			</table>
			<div id="join_menu">
				<input type="submit" value="Sign Up" class="input_button"> <input
					type="reset" value="Cancel" class="input_button"
					onclick="$('#join_id').focus();">
			</div>
		</form>
	</div>
</body>
</html>