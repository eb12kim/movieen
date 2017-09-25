<%@page import="net.movie.db.MovieBean"%>
<%@page import="net.movie.db.MovieDAO"%>
<%@page import="net.post.db.PostBean"%>
<%@page import="java.util.List"%>
<%@page import="net.post.db.PostDAO"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="listcount" value="${requestScope.listcount}" />
<c:set var="nowpage" value="${requestScope.page}" />
<c:set var="maxpage" value="${requestScope.maxpage}" />
<c:set var="startpage" value="${requestScope.startpage}" />
<c:set var="endpage" value="${requestScope.endpage}" />
<c:set var="genre" value="${requestScope.genre}" />
<c:set var="movieId" value="${requestScope.movieId}" />
<%
	PostDAO postDAO = new PostDAO();
	MovieDAO movieDAO = new MovieDAO();
%>

<!DOCTYPE>
<html>
<head>

<!--  부트스트랩 & W3 CSS core -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="jquery/3.2.1/jquery-3.2.1.min.js"></script>
<script src="bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!--  Customized CSS for movieen -->
<!-- <link href="css/common.css" rel="stylesheet"> -->
<style>
html {
  /* position: relative; */
  height: 100%;
  line-height: 1.8;
}

body{
	height: 100%;
	line-height: 1.8;
	
}

body>.container{
	padding-top:60px;
	padding-bottom:60px;
	min-height:80%;
}

.w3-bar .w3-button {
    padding: 16px;
}

footer {
/*   position: absolute; */
  bottom: 0;
  width: 100%;

  
  /* Set the fixed height of the footer here */
  height: 240px;
  background-color: white;
}



body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
};

</style>
<title>by Movie</title>
</head>
<body style="padding-top:50px;">
<%@ include file="/gnb.jsp"%>
<!-- 네비게이션 -->
<div>
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-padding-60 w3-animate-left" style="z-index:0;width:250px;margin-top:65px;padding-bottom:60px;" id="mySidebar_movie">
	<div class="w3-container w3-display-container w3-padding-16">
      <i onclick="w3_close('mySidebar_movie')" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
          <h3 class="w3-wide"><b>by Movie</b></h3>
	</div>
	<div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
		<% List<String> genreList = (List)request.getAttribute("genreList");
		
		for(int i=0; i<genreList.size(); i++) {
			String genre = genreList.get(i);
		%>
		<div>
		   <a onclick="myAccFunc('Acc<%=i%>')" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="myBtn">
     			<%=genre%> [<%=postDAO.getPostByGenreCount(genre)%>]<i class="fa fa-caret-down"></i>
			</a>
 		   <div id="Acc<%=i%>" class="w3-bar-block w3-hide w3-padding-large w3-medium">
			<a href="postListByMovieAction.po?genre=<%=genre%>" class="w3-bar-item w3-button">전체글보기 [<%=postDAO.getPostByGenreCount(genre)%>]</a>
			
 				<% List<Integer> movieList = postDAO.getMovieByGenre(genre);
 				for(int j=0; j<movieList.size(); j++) {
 					int movie_id = movieList.get(j); 
 					MovieBean movie = movieDAO.getDetailMovie(movie_id); %>
					<a href="postListByMovieAction.po?movieId=<%=movie_id%>" class="w3-bar-item w3-button">
						<%=movie.getMOVIE_TITLE()%> [<%=postDAO.getPostByMovieCount(movie_id)%>]
					</a>

				<% } %>
			</div>
		</div>
		<% } %>
		<hr />
	<div style="padding-left:10px;">		
	  <form action="postSearchAction.po" class="form-inline">

	  <input type="text" class="form-control" name="search" required/>
	  
	  	<input type="hidden" name="type" value="1" required/>
		<input type="submit" class="form-control" value="검색"></input>

  </form>	
  </div>
	</div>
	</nav>
</div>
	<!-- 네비게이션 끝 -->
	
	<!--  Small Screen -->
 	<!-- Top menu on small screens -->
<header class="w3-bar w3-hide-large w3-black" style="position:fixed">
  		<div class="w3-bar-item w3-padding-24 w3-wide">by Movie</div>
	  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open('mySidebar_movie')"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close('mySidebar_movie')" style="cursor:pointer" title="close side menu" id="myOverlay_movie"></div>


<!--  본문영역 시작 -->



	<!-- 리스트 출력 -->
<div class="w3-main" style="margin-left:250px">	
<!-- Push down content on small screens -->
<div class="w3-hide-large" style="margin-top:83px"></div>
<header class="w3-container w3-center w3-padding-40 w3-white" style="padding-top:60px">
    <h1 class="w3-xxxlarge">
    	<b>영화 Movi'een</b>
    </h1>
    <h6>영화 속에 나온 그 곳을 찾아가 Movi'een Moment를 느껴보세요!</h6>
    <h4><a href="postWrite.po?type=${type}" role="button" class="w3-button"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> 글쓰기</a></h4>
</header>	
  <div class=container style="padding-top: 30px;">		
  	<div class="w3-row-padding ">
		<c:if test="${listcount!=0}">
			<% List<PostBean> postList = (List) request.getAttribute("postList");
			for (int i=0; i<postList.size(); i++) {
				PostBean post = postList.get(i);
				MovieBean movie = movieDAO.getDetailMovie(post.getPOST_MOVIE_ID());
				int replyCount = postDAO.getReplyListCount(post.getPOST_NO()); %>
				<div class="w3-third w3-container w3-margin-bottom" >
					<div>
						<i class="fa fa-film test" aria-hidden="true"><%=movie.getMOVIE_TITLE()%></i>
						<i class="fa fa-map-marker" aria-hidden="true"></i><%=post.getPOST_NATION()%>
					</div>
					<div>
						<a href="./postDetailAction.po?type=<%=post.getPOST_TYPE()%>&postno=<%=post.getPOST_NO()%>"	class="w3-hover-opacity">
							<img src="<%=post.getPOST_FILE()%>" style="width:100%;height:250px;" class="w3-hover-opacity" />
						</a>
					</div>
					<div class="w3-container w3-white">
						<div align="right" style="padding-top:5px;">
							<span class="label label-default">VIEW <%=post.getPOST_READCOUNT()%></span>
							<span class="label label-danger">LIKE <%=post.getPOST_LIKECOUNT()%></span>
							
						</div>
					</div>
				</div>
			<% } %>
		</c:if>
		<c:if test="${listcount==0}">
			해당 게시물이 없습니다.
		</c:if>
	</div>
</div>	
	<!-- 리스트 출력 끝 -->
				<div class="form-group" align="right">
			<h4><a href="postWrite.po?type=${type}" role="button" class="w3-button"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> 글쓰기</a></h4>
			</div>
	<!-- 게시판 페이지 처리 -->
	<div class="w3-bar" align="center">
		<c:choose>
			<c:when test="${nowpage<=1}">
				<span class="w3-button">&laquo;</span>
			</c:when>
			<c:otherwise>
				<a href="./postListByMovieAction.po?genre=${genre}&movieId=${movieId}&page=${nowpage-1}" class="w3-button">&laquo;</a>
			</c:otherwise>
		</c:choose>

		<c:forEach var="a" begin="${startpage}" end="${endpage}">
			<c:choose>
				<c:when test="${a==nowpage}">
					<span class="w3-button w3-gray">${a}</span>
				</c:when>
				<c:otherwise>
					<a href="./postListByMovieAction.po?genre=${genre}&movieId=${movieId}&page=${a}" class="w3-button">${a}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${nowpage>=maxpage}">
				<span class="w3-button">&raquo;</span>
			</c:when>
			<c:otherwise>
				<a href="./postListByMovieAction.po?genre=${genre}&movieId=${movieId}&page=${nowpage+1}" class="w3-button">&raquo;</a>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- 게시판 페이지 처리 -->
</div>

<%-- <%@ include file="/footer.jsp" %> --%>
	
<!--  사이드바 처리를 위한 스크립트 -->
<script>
// Accordion 
function myAccFunc(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}

// Click on the "Jeans" link on page load to open the accordion for demo purposes
/* document.getElementById("myBtn").click(); */


// Script to open and close sidebar
/* function w3_open() {
    document.getElementById("mySidebar_movie").style.display = "block";
    document.getElementById("myOverlay_movie").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar_movie").style.display = "none";
    document.getElementById("myOverlay_movie").style.display = "none";
} */
</script>
	
</body>
</html>