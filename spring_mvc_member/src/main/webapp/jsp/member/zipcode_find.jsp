<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function(){
		$("#post_list").change(function(){
			var a = $(this).val().substring(0,5);
			var b = $(this).val().substring(5);
			
			
			// id 뒤에 opener.document 또는 parent.document 를 사용하여 부모창의 문서 객체를 제어합니다.
			/*
				$("#join_zip",opener.document).val(zip1);
				$("#join_addr1",opener.document).val(addr2);
			*/
			opener.$("#join_zip").val(a);
			opener.$("#join_addr1").val(b);
			
			window.self.close();
			
		})
	})
</script>
</head>
<body>
	<form method="post" action="zipcode_find_ok.nhn" id="zipform">
		<table class="bg">
			<tr>
				<td colspan=2 class="center">
					<input type="image" src="./resources/images/ZipCode_img01.gif" width="413" height="58">
				</td>
			</tr>
			
			<tr>
				<td colspan=2 class="center"> 
					<strong>
						<font color="#466d1b">
							<span class="style1">[거주지의 대로명을 입력하고 '찾기' 버튼을 누르세요 !!]</span>
						</font>
					</strong>
				</td>
			</tr>
			
			<tr height="30">
				<td class="right">
					<input type="text" name="dong" id="inputdong" size="10" maxlength="10" height="19">&nbsp;
				</td>
				<td class="image02">
					<input type="image" src="./resources/images/m-i02.gif" width="69" height="19">
				</td>
			</tr>
			
			
			<!-- 동을입력했다면 실행되는 JSTL if 문 -->
			<c:if test="${!empty dong }">
			
			<!-- 검색결과 주소값이 있을 경우만 실행되는 JSTL if 문 -->
			<c:if test="${!empty zipcodeList }">
			<tr>
				<td colspan=2 class="center30">
					<SELECT name="post_list" id=post_list>
						<option value="">-----주소를 선택하세요------</option>
					
					
					<!-- items 속성에는 검색결과의 주소값을 담고 있는 키값을 적습니다.
						 addr2 변수에는 주소값을 받아서 저장합니다. -->
					<c:forEach var="addr2" items="${zipcodeList}">
					<!-- addr2.zipcode에는 zipcodeBean 클래스의 getZipcode() 메서드에서 구해온 우편번호, 
						addr2.addr에는 ZipcodeBean 클래스의 getAddr() 메서드를 가져와 시도 구군 도로 합친 totaladdr에 저장합니다. -->
						<c:set var="totaladdr" value="${addr2.zipcode }${addr2.addr }"></c:set>
						
						<option value="${totaladdr }">{${addr2.zipcode }]&nbsp;${addr2.addr }</option>
					</c:forEach>
					</SELECT>
				</td>
			</tr>
			</c:if>
			
			<!-- JSTL 에서 검색 주소값이 없을 경우 실행되는 If 문. -->
			<c:if test="${empty zipcodeList }">
				<tr>
					<td colspan=2 class="center30">
						<font color="#466d1b"><span class="style1">검색 결과가 없습니다.</span></font>
					</td>
				</tr>
			</c:if>
			</c:if>
			<tr>
				<td class="bar" colspan="2" height="3">
			</tr>
		</table>
	</form>
</body>
</html>