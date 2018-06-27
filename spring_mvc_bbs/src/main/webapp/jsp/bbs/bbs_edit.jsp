<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/bbs.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>

function fileshow(){
   if($("#filevalue").text()==""){
      $("#close").css("display","none");
   }else{
      $("#close").css("display","inline");
   }
}
      
$(function(){
   fileshow();
   $("#upfile").change(function(){
      $("#filevalue").text('');
      //$(this).val()=> c:\fakepath\image1.png
      var inputfile=$(this).val().split('\\');//c:fakepath,image1.png
      $('#filevalue').text(inputfile[inputfile.length-1]);
      fileshow();
   })
   
   $("#close").click(function(){
      $("#filevalue").text('');
      $(this).css("display",'none');
   })
})
</script>
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">자료실 수정폼</h2>
		<form method="post" action="./bbs_edit_ok.nhn"
			onsubmit="return check()" enctype="multipart/form-data">
			
			<input type="hidden" name="bbs_num" value="${bbs.bbs_num }">
			<input type="hidden" name="page" value="${page }">
			
			<table id="bbswrite_t">
				<tr>
					<th>글쓴이</th>
					<td><input name="bbs_name" id="bbs_name" size="14"
						class="input_box" readonly value="${bbs.bbs_name }"></td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="bbs_pass" name="bbs_pass"
						size="14" class="input_box"></td>
				</tr>

				<tr>
					<th>글제목</th>
					<td><input name="bbs_subject" id="bbs_subject" size="40"
						class="input_box" readonly value="${bbs.bbs_subject }"></td>
				<tr>
					<th>글내용</th>
					<td><textarea rows="8" cols="50" name="bbs_content"
							id="bbs_content" class="input_box" >${bbs.bbs_content }</textarea></td>
				</tr>

				<tr>
					<th>파일첨부</th>
					<td>
						<!-- <input type="file" name="uploadfile" > --> <label
						for="upfile"><img src="resources/images/file_open.png"
							alt="파일열기"></label> <input type="file" id="upfile"
						name="uploadfile"> <span id="filevalue">${bbs.bbs_file }</span>&nbsp; <span
						id="close"><img src="resources/images/cancel.png"></span>
					</td>
				</tr>
			</table>

			<div id="bbswrite_menu">
				<input type="submit" value="수정" class="input_button"> <input
					type="reset" value="취소" class="input_button"
					onclick="$('#bbs_name').focus();">
			</div>
		</form>
	</div>
</body>
</html>