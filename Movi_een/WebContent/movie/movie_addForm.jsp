<!DOCTYPE html>
<%@page import="net.movie.db.MovieDAO"%>
<%@page import="net.movie.db.MovieBean"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page	import="kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService"%>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Collection"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.util.JSONBuilder"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
------------------------------------------------------------
* @설명 : 영화코드 조회 REST 호출 - 서버측에서 호출하는 방식 예제
------------------------------------------------------------
-->
<% String write=request.getParameter("write"); %>
<%-- <c:set var="write" value="${requestScope.write}" /> --%>
<%
	// 파라메터 설정

	String curPage = request.getParameter("curPage") == null ? "1" : request.getParameter("curPage"); //현재페이지
	String itemPerPage = request.getParameter("itemPerPage") == null ? "20"
			: request.getParameter("itemPerPage"); //결과row수
	String movieNm = request.getParameter("movieNm") == null ? "" : request.getParameter("movieNm"); //영화명
	String directorNm = request.getParameter("directorNm") == null ? "" : request.getParameter("directorNm"); //감독명
	String openStartDt = request.getParameter("openStartDt") == null ? "" : request.getParameter("openStartDt"); //개봉연도 시작조건 ( YYYY )
	String openEndDt = request.getParameter("openEndDt") == null ? "" : request.getParameter("openEndDt"); //개봉연도 끝조건 ( YYYY )	
	String prdtStartYear = request.getParameter("prdtStartYear") == null ? ""
			: request.getParameter("prdtStartYear"); //제작연도 시작조건 ( YYYY )
	String prdtEndYear = request.getParameter("prdtEndYear") == null ? "" : request.getParameter("prdtEndYear"); //제작연도 끝조건    ( YYYY )
	String repNationCd = request.getParameter("repNationCd") == null ? "" : request.getParameter("repNationCd"); //대표국적코드 (공통코드서비스에서 '2204'로 조회된 국가코드)
	String[] movieTypeCdArr = request.getParameterValues("movieTypeCdArr") == null ? null
			: request.getParameterValues("movieTypeCdArr"); //영화형태코드 배열 (공통코드서비스에서 '2201'로 조회된 영화형태코드)

	// 발급키
	String key = "b62a74c41c28bf6e3816ac43dfea6432";
			
	// KOBIS 오픈 API Rest Client를 통해 호출
	KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

	// 영화코드조회 서비스 호출 (boolean isJson, String curPage, String itemPerPage,String directorNm, String movieCd, String movieNm, String openStartDt,String openEndDt, String ordering, String prdtEndYear, String prdtStartYear, String repNationCd, String[] movieTypeCdArr)
	String movieCdResponse = service.getMovieList(true, curPage, itemPerPage, movieNm, directorNm, openStartDt,
			openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr);
	
	// Json 라이브러리를 통해 Handling
	ObjectMapper mapper = new ObjectMapper();
	HashMap <String, Object> result = mapper.readValue(movieCdResponse, HashMap.class);

	request.setAttribute("result", result);

%>

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

<script>
	function searchMovie() {
		document.getElementById("search").style.display="block";
	};
</script>

<title>영화 추가</title>
</head>
<body>
	<%@ include file="/gnb.jsp" %>
	<div class="container">
	<form class="form-inline" action="">
	<input type="hidden" name="write" value=<%= write %> />
	<div class="input-group">
	<label for="movieNm" class="input-group-addon">영화명</label><input type="text" name="movieNm" value="<%=movieNm%>" class="form-control">
	</div>
	<input type="submit" value="조회" onclick="javascript:searchMovie()" class="form-control">
	</form>
	<div >
	<div>
	<strong>총 검색결과 : <c:out value="${result.movieListResult.totCnt}" />건</strong> &nbsp;&nbsp;&nbsp;
	<a href="javascript:searchMovie()" ><u>결과보기</u></a>
	</div>
	<br />

	<div id="search" style="display:none;">
		<c:if test="${not empty result.movieListResult.movieList}">
			<table class="table">
				<thead>
				<tr>
				<th>영화코드</th>
				<th>영화명</th>
				<th>영화명(영문)</th>
				<th>개봉연도</th>
				<th>대표장르</th>
				<th>비고</th>
				</tr>
					
				</thead>
				<tbody>
			<c:forEach items="${result.movieListResult.movieList}" var="movie">
			<tr>
				<td><c:out value="${movie.movieCd}"/></td>
				<td><c:out value="${movie.movieNm}"/></td>
				<td><c:out value="${movie.movieNmEn}"/></td>
				<td><c:out value="${movie.prdtYear}"/></td>
				<td><c:out value="${movie.repGenreNm}"/></td>
				<td>
				<form action="./movieAddAction.mo" class="form">
					<input type="hidden" name="write" value="<%= request.getParameter("write") %>" />
				
					<input type="hidden" name="MOVIE_ID" value="${movie.movieCd}" />
					<input type="hidden" name="MOVIE_TITLE" value="${movie.movieNm}" />
					<input type="hidden" name="MOVIE_TITLE_EN" value="${movie.movieNmEn}" />
					
					<input type="hidden" name="MOVIE_GENRE" value="${movie.repGenreNm}" />
					<input type="hidden" name="MOVIE_PUB_YEAR" value="${movie.prdtYear}" />
					<input type="submit" value="선택" class=form-control/>
				</form>
				</td>
			</tr>	
			</c:forEach>
			</tbody>
			</table>
		</c:if>
	</div>
	</div>
	<hr>
	
	</div>
	<%@ include file="/footer.jsp" %>
</body>
</html>