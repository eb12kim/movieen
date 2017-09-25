<%@page import="net.movie.db.MovieBean"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>영화리스트</title>
</head>
<body>
	<%@ include file="/gnb.jsp" %>
	 <header class="w3-container w3-center w3-padding-40 w3-white">
    <h1 class="w3-xxxlarge">
    	<b>
			영화리스트(어드민)
     	</b>
    </h1>
    <h6>등록되어 있는 영화리스트입니다.(가나다 순)</h6>
  </header>		
	<div class="container" style="padding-top:20px;" id="movieList">
		<table class="table">
			<thead>
			<tr>
				<th>영화ID</th>
				<th>영화제목</th>
				<th>장르</th>
				<th>개봉년도</th>
				<th>기능</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${requestScope.movielist}">
					<tr>
						<td>${list.getMOVIE_ID()}</td>
						<td><a href="movieInfo.mo?movieid=${list.getMOVIE_ID()}">${list.getMOVIE_TITLE()}</a></td>
						<td>${list.getMOVIE_GENRE()}</td> 
						<td>${list.getMOVIE_PUB_YEAR()}</td>
						<td><a href="movieDelete.mo?movieid=${list.getMOVIE_ID()}">[삭제]</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div align="center" style="padding-bottom:100px;">
		<a href="movieAdd.mo" type="button" class="form-control btn-success">영화 등록</a>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>