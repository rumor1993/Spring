<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head><link rel="stylesheet" href="./resources/css/bbs.css">
<style type="text/css">
#button {
  display: inline-block;
  text-decoration: none;
  color: white;
  height: 48px;
  line-height: 48px;
  padding: 0 20px;
  border-radius: 24px;
  border: 1px solid #d5e0cc;
  background: #d5e0cc;
  box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), inset 0 1px 3px rgba(255, 255, 255, 0.2);
  transition: all 0.1s;
}
#button:hover {
  background: #4f8aba;
  box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), inset 0 0 10px rgba(255, 255, 255, 0.1);
}
#button:active {
  color: #294d6b;
  background: #427aa9;
  box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), inset 0 0 5px rgba(0, 0, 0, 0.2);
}
#button:focus {
  outline: none;
}
a{
  color: #739931;
}
.page{
  max-width: 60em;
  margin: 0 auto;
}
table th,
table td{
  text-align: left;
}
table{
  width: 50%;
  border-collapse: collapse;
}
table{
  margin: 1em 0;
}
table th,
table td{
  border: 1px solid #B3BFAA;
  padding: .5em 1em;
}

table th{ background: #D5E0CC; }
table td{ background: #fff; }

table{
  box-shadow: 0 1px 10px rgba(0, 0, 0, 0.2);
}

@media (max-width: 30em){
    table{
      box-shadow: none;  
    }
    table thead{
      display: none; 
    }
  table th,
  table td{
    padding: .5em;
  }
  
  

</style>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>Insert title here</title>
</head>
<body>
	<table id=bbscont_t>
		<c:set var="d" value="${bbs }" />
		<tr>
			<th colspan=2>MVC 게시판</th>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${d.bbs_name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${d.bbs_subject }</td>
		</tr>
		<tr>
			<td>내용</td>
			<!-- <pre> 태그로 입력한 그래도 출력되게 합니다. 엔터도 잘나옵니다. -->
			<!-- <pre> ${bcont.bbs_content} </pre> -->
			<td>${d.bbs_content }</td>
		</tr>
		
		<c:if test="${!empty bbs.bbs_file }">
		<tr>
			<td>첨부파일명</td>
			<td><a href="./download.file?path=${bbs.bbs_file}&original=${bbs.bbs_original }">${bbs.bbs_original }</a></td>
			
		</tr>
		<tr>
			<td>조회수</td>
			<td>${bbs.bbs_readcount }</td>
		</tr>
		</c:if>
	</table>
	
	<div id="bbscont_menu" style="margin-left: 200px">
		<input id=button type="button" value="수정" class="input_button"
			onclick="location='bbs_cont.nhn?bbs_num=${d.bbs_num}&page=${page }&state=edit'">
		<input id=button type="button" value="삭제" class="input_button"
			onclick="location='bbs_cont.nhn?bbs_num=${d.bbs_num}&page=${page }&state=del'">
		<input id=button type="button" value="답변" class="input_button"
			onclick="location='bbs_cont.nhn?bbs_num=${d.bbs_num}&page=${page }&state=reply'">
		<input id=button type="button" value="목록" class="input_button"
			onclick="location='bbs_list.nhn?page=${page }'">

	</div>
</body>
</html>