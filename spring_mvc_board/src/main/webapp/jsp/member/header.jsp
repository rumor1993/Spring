<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<style>
   .header{text-align:right;background:linen}
</style>
<div class="header">
<c:choose>
   <c:when test="${sessionScope.id=='admin' }">
      <b>관리자 ${id} 님 환영합니다.</b>
      <a href="member_list.net">관리자 모드 접속(회원 목록 보기)</a>
         <a href="bbs_list.nhn">(게시판)</a>
      
   </c:when>
   <c:when test="${empty sessionScope.id }">
      <script>
         location.href="member_login.nhn"
      </script>
   </c:when>
   <c:otherwise>
      <b>일반회원 ${id} 님 환영합니다. </b>
   </c:otherwise>
</c:choose>
   
   
   
   
   
<a href="member_update.nhn">정보수정</a>
<a href="member_logout.nhn">로그아웃</a>
</div>