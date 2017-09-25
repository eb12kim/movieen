<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="movie" value="${requestScope.movie}"/>

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

<title>영화 상세정보</title>
</head>
<body>
	<%@ include file="/gnb.jsp" %>
	 <header class="w3-container w3-center w3-padding-40 w3-white">
    <h1 class="w3-xxxlarge">
    	<b>
			영화정보  
     	</b>
    </h1>
    <h6>등록되어 있는 영화상세정보입니다.</h6>
  </header>		
	<div class="container" style="padding-top:20px;">
	id : ${movie.getMOVIE_ID()}<br>
	제목 : ${movie.getMOVIE_TITLE()}<br>
	장르 : ${movie.getMOVIE_GENRE()}<br>
	개봉년도 :${movie.getMOVIE_PUB_YEAR()}<br>
	
	<a href="movieModify.mo?movieid=${movie.getMOVIE_ID()}">[수정]</a>
	<a href="movieDelete.mo?movieid=${movie.getMOVIE_ID()}">[삭제]</a><br>
	<a href="movieList.mo">[리스트로 돌아가기]</a><br>
	<a href="main.jsp">[메인으로 돌아가기]</a>
	</div>	
	<%@ include file="/footer.jsp" %>	
</body>
</html>