<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!--  부트스트랩 & W3 CSS core -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="jquery/3.2.1/jquery-3.2.1.min.js"></script>
<script src="bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!--  Customized CSS for movieen -->
<link href="css/common.css" rel="stylesheet">

<title>회원 목록</title>
</head>
<body>
	<%@ include file="/gnb.jsp"%>
 <header class="w3-container w3-center w3-padding-40 w3-white">
    <h1 class="w3-xxxlarge">
    	<b>
			회원리스트(어드민)  
     	</b>
    </h1>
    <h6><span class="w3-tag">Movi'een</span> 가입된 회원 리스트를 조회합니다.</h6>
  </header>			
	<div class="container" style="padding-top:20px;">
		<h3>등록된 회원 리스트</h3>
		<table class="table">
		<thead>
			<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>생년월일</th>
			<th>성별</th>
			<th>국적</th>
			<th>선호영화장르</th>
			<th>여행희망지역</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${requestScope.memberlist}">
			<tr>
			
			<td><a href="memberView.me?id=${list.getMEMBER_ID()}">${list.getMEMBER_ID()}</a></td>
			<td>${list.getMEMBER_NAME()}</td>
			<td>${list.getMEMBER_PHONE()}</td>
			<td>${list.getMEMBER_AGE()}</td>
			<td>
				<c:if test="${list.getMEMBER_GENDER()==1}"> 남	</c:if>
				<c:if test="${list.getMEMBER_GENDER()==2}"> 여	</c:if>
			</td>	
				
			<td>${list.getMEMBER_NATIONALITY()}</td>
			
			<td>
			<c:if test="${list.getMEMBER_MOVIE_LIKE()!=null}">
			${list.printMEMBER_MOVIE_LIKE()}
			</c:if>
			</td>
		
			<td>
			<c:if test="${list.getMEMBER_TRIP_LIKE()!=null}">
			${list.printMEMBER_TRIP_LIKE()}
			</c:if>
			</td>
		
			</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
	<%@ include file="/footer.jsp" %>
</body>
</html>