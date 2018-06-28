<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link href="./resources/css/costom.css" type="text/css" rel="stylesheet">
<link href="./resources/css/bootstrap.css" type="text/css"
	rel="stylesheet">

<script>
	function cambiar_login() {
		document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
		document.querySelector('.cont_form_login').style.display = "block";
		document.querySelector('.cont_form_sign_up').style.opacity = "0";

		setTimeout(function() {
			document.querySelector('.cont_form_login').style.opacity = "1";
		}, 400);

		setTimeout(
				function() {
					document.querySelector('.cont_form_sign_up').style.display = "none";
				}, 200);
	}

	function cambiar_sign_up(at) {

		document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
		document.querySelector('.cont_form_sign_up').style.display = "block";
		document.querySelector('.cont_form_login').style.opacity = "0";

		setTimeout(function() {
			document.querySelector('.cont_form_sign_up').style.opacity = "1";
		}, 100);

		setTimeout(function() {
			document.querySelector('.cont_form_login').style.display = "none";
		}, 400);

	}

	function ocultar_login_sign_up() {

		document.querySelector('.cont_forms').className = "cont_forms";
		document.querySelector('.cont_form_sign_up').style.opacity = "0";
		document.querySelector('.cont_form_login').style.opacity = "0";

		setTimeout(
				function() {
					document.querySelector('.cont_form_sign_up').style.display = "none";
					document.querySelector('.cont_form_login').style.display = "none";
				}, 500);

	}
	
	function login_ok(){
		if ($("#id").val() == "") {
			alert('아이디를 입력해주세요.');
			$("#id").focus()
			return false;
		}
		if ($("#pass").val() == "") {
			alert('비밀번호를 입력해주세요.');
			$("#pass").focus()
			return false;
		}
	}

	function sign_up_ok() {
		if ($("#member_email").val() == "") {
			alert('이메일을 입력해주세요.');
			$("#member_email").focus()
			return false;
		}
		if ($("#member_id").val() == "") {
			alert('아이디를 입력해주세요.');

			$("#member_id").focus()
			return false;
		}
		if ($("#member_pass").val() == "") {
			alert('비밀번호를 입력해주세요.');
			$("#member_pass").focus()
			return false;
		}
		if ($("#confirm_pass").val() == "") {
			alert('비밀번호 확인란을 입력해주세요.');
			$("#confirm_pass").focus()
			return false;
		}
		if ($("#member_pass").val() != $("#confirm_pass").val()) {
			alert('비밀번호가 일치하지 않습니다.')
			$("#member_pass").val("")
			$("#confirm_pass").val("")
			$("#member_pass").focus()
			return false;
		}

	}

	
	$(function() {
		if ($("#checkX").val() == "성공 메세지") {
			$('.modal-title').text("성공 메세지.")
			$('.modal-body p').text($("#checkO").val())
			$('div.modal').modal();
		} else if ($("#checkX").val() == "오류 메세지") {
			$('.modal-title').text("오류 메세지.")
			$('.modal-body p').text($("#checkO").val())
			$('div.modal').modal();
		}
	})
</script>
<title>Insert title here</title>
</head>
<body>

	<input id="checkO" type="hidden" value="${modal }">
	<input id="checkX" type="hidden" value="${message }">
	<div class="cotn_principal">
		<div class="cont_centrar">
			<div class="cont_login">
				<div class="cont_info_log_sign_up">
					<div class="col_md_login">
						<div class="cont_ba_opcitiy">

							<h2>LOGIN</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
							<button class="btn_login" onclick="cambiar_login()">LOGIN</button>
						</div>
					</div>
					<div class="col_md_sign_up">
						<div class="cont_ba_opcitiy">
							<h2>SIGN UP</h2>


							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>

							<button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN
								UP</button>
						</div>
					</div>
				</div>


				<div class="cont_back_info">
					<div class="cont_img_back_grey">
						<img
							src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
							alt="" />
					</div>

				</div>
				<div class="cont_forms">
					<div class="cont_img_back_">
						<img
							src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d"
							alt="" />
					</div>
					<form action="member_login.nhn" method="POST" onsubmit="return login_ok()" method="POST">
						<div class="cont_form_login">
							<a href="#" onclick="ocultar_login_sign_up()"><i
								class="material-icons">&#xE5C4;</i></a>
							<h2>LOGIN</h2>
							<input id="id" name="member_email" type="text" placeholder="Email" />
							 <input id="pass" name="member_pass" type="password" placeholder="Password" />
							<button class="btn_login" onclick="cambiar_login()">LOGIN</button>
						</div>
					</form>

					<form id="signup" action="member_sign_up_ok.nhn"
						onsubmit="return sign_up_ok()" method="POST">
						<div class="cont_form_sign_up">
							<a href="bbs_sign_up.nhn" onclick="ocultar_login_sign_up()"><i
								class="material-icons">&#xE5C4;</i></a>
							<h2>SIGN UP</h2>
							<input type="text" id="member_email" name="member_email"
								placeholder="Email" /> <input type="text" id="member_id"
								name="member_id" placeholder="User" /> <input type="password"
								id="member_pass" name="member_pass" placeholder="Password" /> <input
								type="password" id="confirm_pass" name="confirm_pass"
								placeholder="Confirm Password" />
							<button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN
								UP</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- 모달 부분 -->
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>