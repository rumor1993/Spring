<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE htm>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>Insert title here</title>
<style type="text/css">
@import
	url('https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css')
	;

/*Normalize Start*/
article, aside, details, figcaption, figure, footer, header, hgroup, nav,
	section, summary {
	display: block;
}

audio, canvas, video {
	display: inline-block;
}

audio






:not



 



(
[
controls
]



 



)
{
display






:



 



none






;
}
[hidden] {
	display: none;
}

html {
	font-size: 100%;
}

html, button, input, select, textarea {
	font-family: sans-serif;
}

body {
	margin: 0;
}

a:focus {
	outline: thin dotted;
}

a:hover, a:active {
	outline: 0 none;
}

h1 {
	font-size: 2em;
	margin: 0.67em 0;
}

h2 {
	font-size: 1.5em;
	margin: 0.83em 0;
}

h3 {
	font-size: 1.17em;
	margin: 1em 0;
}

h4 {
	font-size: 1em;
	margin: 1.33em 0;
}

h5 {
	font-size: 0.83em;
	margin: 1.67em 0;
}

h6 {
	font-size: 0.75em;
	margin: 2.33em 0;
}

abbr[title] {
	border-bottom: 1px dotted;
}

b, strong {
	font-weight: bold;
}

blockquote {
	margin: 1em 40px;
}

dfn {
	font-style: italic;
}

mark {
	background: none repeat scroll 0 0 #FFFF00;
	color: #000000;
}

p, pre {
	margin: 1em 0;
}

pre, code, kbd, samp {
	font-family: monospace, serif;
	font-size: 1em;
}

pre {
	white-space: pre-wrap;
	word-wrap: break-word;
}

q {
	quotes: none;
}

q:before, q:after {
	content: none;
}

small {
	font-size: 75%;
}

sub, sup {
	font-size: 75%;
	line-height: 0;
	position: relative;
	vertical-align: baseline;
}

sup {
	top: -0.5em;
}

sub {
	bottom: -0.25em;
}

dl, menu, ol, ul {
	margin: 1em 0;
}

dd {
	margin: 0 0 0 40px;
}

menu, ol, ul {
	padding: 0 0 0 40px;
}

nav ul, nav ol {
	list-style: none outside none;
}

img {
	border: 0 none;
}

a img {
	border: 0 none;
}

svg






:not



 



(
:root



 



)
{
overflow






:



 



hidden






;
}
figure {
	margin: 0;
}

form {
	margin: 0;
}

fieldset {
	border: 1px solid #C0C0C0;
	margin: 0 2px;
	padding: 0.35em 0.625em 0.75em;
}

legend {
	border: 0 none;
	padding: 0;
	white-space: normal;
}

button, input, select, textarea {
	font-size: 100%;
	margin: 0;
	vertical-align: baseline;
}

button, input {
	line-height: normal;
}

button, input[type="button"], input[type="reset"], input[type="submit"]
	{
	cursor: pointer;
}

button[disabled], input[disabled] {
	cursor: default;
}

input[type="checkbox"], input[type="radio"] {
	padding: 0;
}

input[type="search"] {
	-moz-box-sizing: content-box;
}

button::-moz-focus-inner, input::-moz-focus-inner {
	border: 0 none;
	padding: 0;
}

textarea {
	overflow: auto;
	vertical-align: top;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

/*Normalize END*/
body {
	font-family: Calibri;
	background: #f5f7f8;
	margin: 30px;
}

a:link, a:visited {
	text-decoration: none;
	color: #666;
}

a:hover {
	text-decoration: underline;
}

#f-accordion {
	position: relative;
}

#f-accordion h3 {
	margin: 5px 0 0;
	padding: 15px;
	font-size: 24px;
	font-weight: normal;
	color: #009C7C;
	position: relative;
	overflow: hidden;
	background: #f9f9f9;
	border: 1px solid #ddd;
	text-shadow: 0 1px 1px #fff;
	outline: 0;
	cursor: pointer;
}

#f-accordion h3:hover {
	background: #eee;
}

#f-accordion .ui-accordion-header-active span {
	background:
		url("http://www.owltemplates.com/demo/website/foscor/img/accordion-close.png")
		no-repeat scroll right center rgba(0, 0, 0, 0);
	display: inline-block;
	width: 39px;
	position: absolute;
	height: 80px;
	right: 0;
	top: -10px;
}

#f-accordion h3 span {
	background:
		url("http://www.owltemplates.com/demo/website/foscor/img/accordion-open.png")
		no-repeat scroll right center rgba(0, 0, 0, 0);
	display: inline-block;
	width: 39px;
	position: absolute;
	height: 80px;
	right: 0;
	top: -10px;
}

#f-accordion div {
	position: relative;
	margin: -1px 0 0;
	padding: 15px;
	color: #777;
	background: #fff;
	border: 1px solid #ddd;
}

#f-accordion div:after {
	position: absolute;
	top: -14px;
	left: 25px;
	display: block;
	width: 0;
	height: 0;
	margin-left: -7px;
	border-width: 7px;
	border-style: solid;
	border-color: transparent transparent #fff transparent;
	content: '';
}

#f-accordion div:before {
	position: absolute;
	top: -14px;
	left: 25px;
	display: block;
	width: 0;
	height: 0;
	margin-left: -7px;
	border-width: 7px;
	border-style: solid;
	border-color: transparent transparent #777 transparent;
	content: '';
}

.table-box {
	overflow: hidden;
	margin: 0 auto;
	border: 1px solid #ddd;
}

.table-box table {
	text-align: left;
	width: 100%;
}

.table-box td, .table-box th {
	padding: 10px;
}

.table-box td:first-child, .table-box th:first-child {
	padding-left: 20px;
}

.table-box td:last-child, .table-box th:last-child {
	padding-right: 20px;
}

.table-box th {
	border-bottom: 1px solid #ddd;
	position: relative;
}

input[type="search"] {
	margin: 0 0 12px 0;
	width: 50%;
	border: 1px solid #bababa;
}

input[type="search"]:focus {
	border-color: #009C7C;
	background: #fff;
	color: #5d5d5d;
	box-shadow: 0 1px 1px rgba(60, 140, 210, 0.075) inset, 0 0 8px
		rgba(82, 168, 236, 0.6)
}

.input-group {
	border-collapse: separate;
	display: table;
	position: relative;
}

.input-group-addon, .input-group-btn, .input-group .form-control {
	display: table-cell;
}

.input-group-addon






:not



 



(
:first-child



 



)
:not



 



(
:last-child



 



),
.input-group-btn






:not



 



(
:first-child



 



)
:not



 



(
:last-child



 



),
.input-group



 



.form-control






:not




	



(
:first-child



 



)
:not



 



(
:last-child



 



)
{
border-radius






:



 



0;
}
.input-group-addon, .input-group-btn {
	vertical-align: middle;
	white-space: nowrap;
	width: 1%;
}

.input-group-addon {
	background-color: #EEEEEE;
	border: 1px solid #CCCCCC;
	border-radius: 4px;
	font-size: 14px;
	font-weight: normal;
	line-height: 1;
	padding: 6px 12px;
	text-align: center;
}

.input-group-addon.input-sm {
	border-radius: 3px;
	font-size: 12px;
	padding: 5px 10px;
}

.input-group-addon.input-lg {
	border-radius: 6px;
	font-size: 18px;
	padding: 10px 16px;
}

.input-group-addon input[type="radio"], .input-group-addon input[type="checkbox"]
	{
	margin-top: 0;
}

.input-group



 



.form-control






:first-child
,
.input-group-addon






:first-child
,
.input-group-btn






:first-child
>
.btn
,
.input-group-btn






:first-child
>
.dropdown-toggle
,
.input-group-btn






:last-child
>
.btn






:not



 



(
:last-child



 



)
:not



 



(
.dropdown-toggle




	



)
{
border-bottom-right-radius






:



 



0;
border-top-right-radius






:



 



0;
}
.input-group-addon:first-child {
	border-right: 0 none;
}

.input-group



 



.form-control






:last-child
,
.input-group-addon






:last-child
,
.input-group-btn






:last-child
>
.btn
,
.input-group-btn






:last-child
>
.dropdown-toggle
,
.input-group-btn






:first-child
>
.btn






:not



 



(
:first-child



 



)
{
border-bottom-left-radius






:



 



0;
border-top-left-radius






:



 



0;
}
.input-group-addon:last-child {
	border-left: 0 none;
}

.input-group-btn {
	position: relative;
	white-space: nowrap;
}

.input-group-btn>.btn {
	position: relative;
}

.input-group-btn>.btn+.btn {
	margin-left: -4px;
}

.input-group



 



.form-control






:last-child
,
.input-group-addon






:last-child
,
.input-group-btn






:last-child
>
.btn
,
.input-group-btn






:last-child
>
.dropdown-toggle
,
.input-group-btn






:first-child
>
.btn






:not



 



(
:first-child



 



)
{
border-bottom-left-radius






:



 



0;
border-top-left-radius






:



 



0;
}
.input-group-addon, .input-group-btn, .input-group .form-control {
	display: table-cell;
}

.input-group .form-control {
	margin-bottom: 0;
	width: 100%;
}

/ /*Alert*/
/
.container {
	width: 85%;
	margin: 0 auto;
	position: relative;
}

.alert {
	display: none;
	box-shadow: inset 0px 0px 10px -2px rgba(0, 0, 0, 0.75);
}

.alert p {
	padding: 0 1em;
}

.alert i.close {
	color: #eee;
	float: right;
	font-size: 130%;
	position: relative;
}

.alert i.close:hover {
	color: #fa4242;
	cursor: pointer;
}

.alert i.icon {
	margin-right: .5em;
	font-size: 1.5em;
	position: relative;
	top: .12em;
}

.alert.success {
	background: #53ca74;
	border-radius: 3px;
	color: #fff;
	padding: 1em;
	margin: 0 0 12px;
}

.alert.error {
	background: #e74c3c;
	border-radius: 3px;
	color: #fff;
	padding: 1em;
	margin: 0 0 12px;
}

.instruction {
	width: 420px;
	margin: 1em auto;
	text-align: center;
	color: #999;
	line-height: 1.5em;
}

.instruction a.button {
	display: block;
	clear: both;
	width: 65%;
	padding: 1em;
	text-align: center;
	border: 1px solid #ccc;
	margin: 1em auto;
	border-radius: .5em;
}

.instruction a.button:hover {
	background: #eaeaea;
	border: 1px solid #c4c4c4;
}

.instruction a.button:active {
	background: #dddddd;
}

.button {
	float: right;
	padding: 4px;
	cursor: pointer;
	line-height: 1;
}

@media all and (max-width: 768px) {
	body {
		margin: 0;
	}
	.table-box td:first-child, .table-box th:first-child {
		padding-left: 10px;
	}
	table, thead, tbody, th, td, tr {
		display: block;
	}
	/*
th {
	color: #fff;
	background: #009C7C;
}*/
	table {
		position: relative;
		padding-bottom: 0;
		border: none;
		box-shadow: 0 0 10px rgba(0, 0, 0, .2);
	}
	.table-box th {
		border-right: 1px solid #ddd;
		position: relative;
	}
	thead {
		float: left;
		white-space: nowrap;
	}
	tbody {
		overflow-x: auto;
		overflow-y: hidden;
		position: relative;
		white-space: nowrap;
	}
	tr {
		display: inline-block;
		vertical-align: top;
	}
	th {
		border-bottom: 1px solid #a39485;
	}
	td {
		border-bottom: 1px solid #e5e5e5;
	}
}
</style>

<script>
	function find_check() {
		if ($.trim($("#find_name").val()) == "") {
			alert("검색어를 입력하세요!");
			$("#find_name").val("").focus();
			return flase;
		}
	}

	$(document).ready(function() {
		$("#view").change(function() {
			var view = $("#view").val()
			$.ajax({
				type : "POST",
				url : "bbs_list.nhn",
				data : {
					"limit" : view,
					"state" : "ajax"
				},
				success : function(data) {
					alert("성공");
					$("body").html(data)

				}

			})
		})

		$("#view").val('${limit}');

	})
	$(function() {
		$("#f-accordion").accordion({
			collapsible : true,
			heightStyle : "content"
		});
	});

	//Alert button
	$('.button').click(function() {
		$('.alert').slideToggle();
	});

	$('i.close').click(function() {
		$('.alert').slideToggle();
	});

	//JS table filter
	(function(document) {
		'use strict';

		var LightTableFilter = (function(Arr) {

			var _input;

			function _onInputEvent(e) {
				_input = e.target;
				var tables = document.getElementsByClassName(_input
						.getAttribute('data-table'));
				Arr.forEach.call(tables, function(table) {
					Arr.forEach.call(table.tBodies, function(tbody) {
						Arr.forEach.call(tbody.rows, _filter);
					});
				});
			}

			function _filter(row) {
				var text = row.textContent.toLowerCase(), val = _input.value
						.toLowerCase();
				row.style.display = text.indexOf(val) === -1 ? 'none'
						: 'table-row';
			}

			return {
				init : function() {
					var inputs = document
							.getElementsByClassName('light-table-filter');
					Arr.forEach.call(inputs, function(input) {
						input.oninput = _onInputEvent;
					});
				}
			};
		})(Array.prototype);

		document.addEventListener('readystatechange', function() {
			if (document.readyState === 'complete') {
				LightTableFilter.init();
			}
		});

	})(document);
	//Filter Table
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="f-accordion">
		<h3>
			<i class="fa fa-tasks"></i> MVC 게시판
		</h3>
		<div>

			<jsp:include page="header.jsp"></jsp:include>

			<aside class="alert success">
				<p>
					<i class="icon fa fa-envelope-o"></i> Roger Roger, Message
					Received. <i class="close fa fa-times"></i>
				</p>
			</aside>
			<!-- end alert -->

			<!---
<div class="input-group">
  <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
  <input class="form-control" type="password" placeholder="Password">
</div>
---->
			<input type="search" class="light-table-filter"
				data-table="order-table" placeholder="Filtrer" /> <a class="button"><i
				class="fa fa-exclamation-circle"></i> Report Error</a>
			<section class="table-box">
				<table border=1 id="abc">
					<c:if test="${listcount > 0 }">
						<thead>
							<!-- 레코드가 있으면 -->

							<tr>
								<th colspan="4">MVC 게시판 - list</th>
								<th colspan="2"><font size=2>글 개수 : ${listcount }</font></th>
							</tr>


							<tr>
								<th width="8%"><div>번호</div></th>
								<th width="50%"><div>제목</div></th>
								<th width="14%"><div>작성자</div></th>
								<th width="17%"><div>날짜</div></th>
								<th width="11%"><div>조회수</div></th>
							</tr>
						</thead>

						<tbody id="b">
							<c:set var="num" value="${listcount-(page-1)*limit }" />
							<c:forEach var="list" items="${list }">
								<tr>
									<td><c:out value="${num }" /> <%-- num 출력 --%> <c:set
											var="num" value="${num-1 }" /> <%-- num = num -1 --%></td>


									<td>
										<div>
											<%--답변글 제목앞에 여백 처리 부분 bbs_re_lev, bbs_re_lev, bbs_num, bbs_subject, bbs_name, bbs_date, bbs_readcount : property 이름 --%>
											<c:if test="${list.bbs_re_lev != 0 }">
												<!-- 답글인 경우 -->
												<c:forEach var="a" begin="0" end="${list.bbs_re_lev*2 }"
													step="1">&nbsp;
               </c:forEach>
												<img src="./resources/images/AnswerLine.gif">
											</c:if>

											<c:if test="${list.bbs_re_lev == 0  }">
												<!-- 원문인 경우 -->
              &nbsp;
              </c:if>
											<a
												href="./bbs_cont.nhn?bbs_num=${list.bbs_num}&page=${page}&state=cont">
												${list.bbs_subject} </a>
										</div>
									</td>

									<td>
										<div>${list.bbs_name }</div>
									</td>

									<td>
										<div>${list.bbs_date }</div>
									</td>

									<td>
										<div>${list.bbs_readcount }</div>
									</td>
								</tr>
							</c:forEach>
					</c:if>
					<tr class="h30 lime center btn">
						<td colspan=5><c:if test="${page <= 1 }">
					 이전&nbsp;
					 </c:if> <c:if test="${page > 1 }">
								<a href="./bbs_list.nhn?page=${page-1 }">이전</a>
							</c:if> <c:forEach var="a" begin="${startpage }" end="${endpage }">
								<c:if test="${a==page }">
						${a } 
					</c:if>

								<c:if test="${a!=page }">
									<a href="./bbs_list.nhn?page=${a }">${a }</a>
								</c:if>
							</c:forEach> <c:if test="${page >= maxpage }">
					&nbsp;다음
				</c:if> <c:if test="${page < maxpage }">
								<a href="./bbs_list.nhn?page=${page+1 }">다음</a>
							</c:if>
						
					<tr>
						<form method="get" action="bbs_find_ok.nhn" onsubmit="return find_check()">
					
						<td><select name="find_field">
								<option value="bbs_name">작성자</option>
								<option value="bbs_subject">제목</option>
								<option value="bbs_content">내용</option>
						</select></td>

						<td><input name="find_name" id="find_name" size="18"
							class="input_box"> <input type="submit" value="검색"
							class="input_button"></td>

						<td><select id="view" name="view">
								<option value="3">3줄 보기</option>
								<option value="5">5줄 보기</option>
								<option value="7">7줄 보기</option>
						</select></td>
						<td colspan="5" style="text-align: right"><a
							href="./bbs_write.nhn">[글쓰기]</a></td>
						</form>
					</tr>
					</tbody>

				</table>


			</section>

		</div>
		<h3>Section 2</h3>
		<div>

			<aside class="alert success">
				<p>
					<i class="icon fa fa-envelope-o"></i> Roger Roger, Message
					Received. <i class="close fa fa-times"></i>
				</p>
			</aside>
			<!-- end alert -->

			<input type="search" class="light-table-filter"
				data-table="order-table" placeholder="Filtrer" /> <a class="button"><i
				class="fa fa-exclamation-circle"></i> Report Error</a>
			<section class="table-box">
				<table class="order-table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>John Doe</td>
							<td>john.doe@gmail.com</td>
							<td>0123456789</td>
							<td>99</td>
						</tr>
						<tr>
							<td>Jane Vanda</td>
							<td>jane@vanda.org</td>
							<td>9876543210</td>
							<td>349</td>
						</tr>
						<tr>
							<td>Alferd Penyworth</td>
							<td>alfred@batman.com</td>
							<td>7542890</td>
							<td>199</td>
						</tr>
						<tr>
							<td>Roger Thomas Alder</td>
							<td>roger@gmail.com</td>
							<td>2345689</td>
							<td>21399</td>
						</tr>
						<tr>
							<td>Vince Allen</td>
							<td>vince@vanda.org</td>
							<td>763210</td>
							<td>349</td>
						</tr>
						<tr>
							<td>Johnny Cashmere</td>
							<td>alfred@batman.com</td>
							<td>6754328901</td>
							<td>199S356</td>
						</tr>
					</tbody>
				</table>
			</section>

		</div>
		<h3>
			<i class="fa fa-tasks"></i> Section 3
		</h3>
		<div>

			<aside class="alert error">
				<p>
					<i class="icon fa  fa-exclamation-triangle"></i> Error! <i
						class="close fa fa-times"></i>
				</p>
			</aside>
			<aside class="alert success">
				<p>
					<i class="icon fa fa-envelope-o"></i> Roger Roger, Message
					Received. <i class="close fa fa-times"></i>
				</p>
			</aside>

			<input type="search" class="light-table-filter"
				data-table="order-table" placeholder="Filtrer" /> <a class="button"><i
				class="fa fa-exclamation-circle"></i> Report Error</a>
			<section class="table-box">
				<table class="order-table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>John Doe</td>
							<td>john.doe@gmail.com</td>
							<td>0123456789</td>
							<td>99</td>
						</tr>
						<tr>
							<td>Jane Vanda</td>
							<td>jane@vanda.org</td>
							<td>9876543210</td>
							<td>349</td>
						</tr>
						<tr>
							<td>Alferd Penyworth</td>
							<td>alfred@batman.com</td>
							<td>7542890</td>
							<td>199</td>
						</tr>
						<tr>
							<td>Roger Thomas Alder</td>
							<td>roger@gmail.com</td>
							<td>2345689</td>
							<td>21399</td>
						</tr>
						<tr>
							<td>Vince Allen</td>
							<td>vince@vanda.org</td>
							<td>763210</td>
							<td>349</td>
						</tr>
						<tr>
							<td>Johnny Cashmere</td>
							<td>alfred@batman.com</td>
							<td>6754328901</td>
							<td>199S356</td>
						</tr>
					</tbody>
				</table>
			</section>

		</div>
		<h3>
			<i class="fa fa-spinner fa-spin"></i> Section 4 (Loading)
		</h3>
		<div>
			<p>Just an example of a loading icon using icon fonts and css
				animations. That or use css cusor properity.</p>
			<p>Cras dictum. Pellentesque habitant morbi tristique senectus et
				netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum
				primis in faucibus orci luctus et ultrices posuere cubilia Curae;
				Aenean lacinia mauris vel est.</p>
			<p>Suspendisse eu nisl. Nullam ut libero. Integer dignissim
				consequat lectus. Class aptent taciti sociosqu ad litora torquent
				per conubia nostra, per inceptos himenaeos.</p>
		</div>
	</div>

	<!-- jQuery via Google's CDN -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</body>
</html>