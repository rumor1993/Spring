<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE htm>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
table{margin:0 auto; width:800px}
th{height:30px;background:#f1f1f1;}
td{font-family:µ¸À½; font-size:14; height:36px;}
td:nth-child(2n+1), .center{text-align:center}	  
.h30{height:30px}
.lime{background-color: #f1f1f1;} 
tr:hover{background-color:#F8F8F8} 	
a{text-decoration:none}
.btn {
    border: none;
    outline: none;
    padding: 10px 18px;
    background-color: #f1f1f1;
    cursor: pointer;
    font-size: 18px;
    word-spacing:5px;
}


.active, .btn a:hover {
    background-color: #666;
    color: white;
}
</style>
</head>
<body>
<table border=1>
		<!-- 레코드가 있으면 -->
		<c:if test="${listcount > 0 }">
			<tr>
				<th colspan="4">MVC 게시판 - list</th>
				<th colspan="2">
				<font size=2>글 개수 : ${listcount }</font>
				</th>
			</tr>
	
	
			<tr>
				<th width="8%"><div>번호</div>  </th>
				<th width="50%"><div>제목</div></th>
				<th width="14%"><div>작성자</div></th>
				<th width="17%" ><div>날짜</div></th>
				<th width="11%"><div>조회수</div></th>
			</tr>
			<c:set var="num" value="${listcount-(page-1)*10 }"/>
		<c:forEach var="list" items="${list }">	
			<tr>
				<td>
					<c:out value="${num }"/> <%-- num 출력 --%>
					<c:set var="num" value="${num-1 }"/> <%-- num = num -1 --%>
				</td>
             
             
             <td>
             	<div>
             	  <%--답변글 제목앞에 여백 처리 부분 board_re_lev, board_re_lev, board_num, board_subject, board_name, board_date, board_readcount : property 이름 --%>
             <c:if test="${list.board_re_lev != 0 }"> <!-- 답글인 경우 -->
               <c:forEach var="a" begin="0"
                        end="${list.board_re_lev*2 }" step="1">&nbsp;
               </c:forEach>
             <img src="./resources/images/AnswerLine.gif">  
              </c:if>
              
              <c:if test="${list.board_re_lev == 0  }"> <!-- 원문인 경우 -->
              &nbsp;
              </c:if>
                    <a href="./board_cont.nhn?board_num=${list.board_num}&page=${page}&state=cont">
               ${list.board_subject}            
            </a>
         </div>
      </td>
      
      <td>
         <div>${list.board_name }</div>
      </td>
      
      <td>
         <div>${list.board_date }</div>
      </td>
      
      <td>
         <div>${list.board_readcount }</div>
      </td>
</tr>
		</c:forEach>
		</c:if>
			
			<tr class="h30 lime center btn">
				<td colspan=5>
					<c:if test="${page <= 1 }">
					 이전&nbsp;
					 </c:if>
				<c:if test="${page > 1 }">
					<a href="./board_list.nhn?page=${page-1 }">이전</a>
				</c:if>
				
				<c:forEach var="a" begin="${startpage }" end="${endpage }">
					<c:if test="${a==page }">
						${a } 
					</c:if>
					
					<c:if test="${a!=page }">
						<a href="./board_list.nhn?page=${a }">${a }</a>
					</c:if>
				</c:forEach>
				<c:if test="${page >= maxpage }">
					&nbsp;다음
				</c:if>
				
				<c:if test="${page < maxpage }">
					<a href="./board_list.nhn?page=${page+1 }">다음</a>
				</c:if>
				
				
			<tr>
				<td colspan="5" style="text-align:right">
					<a href="./BoardWrite.bo">[글쓰기]</a>
				</td>
			</tr>

</table>
<!--  게시판 리스트 -->
<table border=1>
		<!-- 레코드가 없으면 -->
		<c:if test="${listcount==0 }">
			<tr>
				<td colspan="4">MVC 게시판</td>
				<td style="text-align:right">
					<font size=2>등록된 글이 없습니다.</font>
				</td>
			</tr>
		</c:if>

</table>
	<%-- <table border=1>
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

			<c:forEach var="b" items="${list }">
				<tr>
					<td>${b.board_num }</td>
					<td>${b.board_subject }</td>
					<td>${b.board_name }</td>
					<td>${b.board_date } 
					<td>${b.board_readcount }</td> 
				</tr>
			</c:forEach>
	</table>
			<c:forEach var="page" begin="${startpage }" end="${endpage }">
				<td><a href="board_list.nhn?page=${page }"> ${page } </a></td>
			</c:forEach> --%>
	
</body>
</html>