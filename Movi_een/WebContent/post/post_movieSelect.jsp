<%@page import="net.movie.db.MovieBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.movie.db.MovieDAO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="jquery/3.2.1/jquery-3.2.1.min.js"></script>
<script src="bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>영화선택</title>
<script>
	function selectMovieClose(movieId, movieTitle){
		opener.writeform.POST_MOVIE_TITLE.value=movieTitle;
		opener.writeform.POST_MOVIE.value=movieId;
		window.close();
	}
	</script>
</head>
<body>

<div class="container" id="movieList" style="padding:50px;">
	<h3>영화를 선택하세요. (가나다 순)</h3>
	<hr />
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
						<td>${list.getMOVIE_TITLE()}</td>
						<td>${list.getMOVIE_GENRE()}</td> 
						<td>${list.getMOVIE_PUB_YEAR()}</td>
						
						<td><input class="form-control btn-info" type="button" value="선택" onClick="javascript:selectMovieClose('${list.getMOVIE_ID()}','${list.getMOVIE_TITLE()}')" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div align="center">
		<p>원하는 영화가 없다면?</p>
		<a href="movieAdd.mo?write=true" type="button" class="form-control btn-success">영화 등록</a>
		</div>
</div >
	
</body>
</html>