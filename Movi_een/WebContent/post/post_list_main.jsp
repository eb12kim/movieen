<%@page import="net.post.db.PostDAO"%>
<%@page import="java.util.List"%>
<%@page import="net.movie.db.MovieBean"%>
<%@page import="net.movie.db.MovieDAO"%>
<%@page import="net.post.db.PostBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int prevPage = Integer.parseInt(request.getParameter("type"));
	session.setAttribute("prevPage", prevPage);
	session.setAttribute("Page", "list");
%>

<c:set var="type" value="${requestScope.type}" />
<c:set var="listcount" value="${requestScope.listcount}" />
<c:set var="nowpage" value="${requestScope.page}" />
<c:set var="maxpage" value="${requestScope.maxpage}" />
<c:set var="startpage" value="${requestScope.startpage}" />
<c:set var="endpage" value="${requestScope.endpage}" />
<c:set var="sort" value="${requestScope.sort}" />
<!-- 170831 은별 -->
<c:set var="search" value="${requestScope.search}" />

<!DOCTYPE>
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

<title>메인게시판</title>
<script>
$(document).ready(function(){
		if(${param.sort=="readcount"}){
			$('#view').addClass('w3-light-grey')
		} else if(${param.sort=="likecount"}){
			$('#like').addClass('w3-light-grey')
		} else {
			$('#recent').addClass('w3-light-grey')
		}
});
</script>
</head>
<body>

	<%@ include file="/gnb.jsp"%>
	<header class="w3-container w3-center w3-padding-40 w3-white">
		<h1 class="w3-xxxlarge">
			<b>전체글보기</b>
		</h1>
		<h6>
			자신만의 <span class="w3-tag">Movi'een Moment</span> 를 공유하세요!
		</h6>
		<div class="w3-padding-32">
		<!--  은별 수정 170831 -->
			<c:choose>
				<c:when test="${search==null || search.equals('')}">		
					<div class="w3-bar w3-border">
						<a href="./postListAction.po?type=${type}&sort=" class="w3-bar-item w3-button" id="recent">최신게시글</a>
						<a href="./postListAction.po?type=${type}&sort=readcount" class="w3-bar-item w3-button" id="view">조회수많은순</a> 
						<a href="./postListAction.po?type=${type}&sort=likecount" class="w3-bar-item w3-button" id="like">좋아요많은순</a>
					</div>
				</c:when>
				<c:otherwise>
					<h3>검색어 <strong>"${search}"</strong>로 검색한 결과입니다.</h3>
				</c:otherwise>
			</c:choose>
		
		</div>
			<h4><a href="postWrite.po?type=${type}" role="button" class="w3-button"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> 글쓰기</a></h4>
	</header>

	<div class=container style="padding-top: 30px;">

		<section id="main">
			<!-- Photo Grid -->
			<div class="w3-row-padding" style="margin-bottom: 50px">
				<div class="w3-half">
				<% List<PostBean> postList = (List)request.getAttribute("postList");
				MovieDAO movieDAO = new MovieDAO(); %>
					<% for(int i=0; i<postList.size(); i+=2) {
						PostBean post = postList.get(i);
						MovieBean movie = movieDAO.getDetailMovie(post.getPOST_MOVIE_ID()); 
						PostDAO postDAO = new PostDAO();
						int replyCount = postDAO.getReplyListCount(post.getPOST_NO());
						%>
								<h3><%= post.getPOST_SUBJECT() %>
														<small>	<% if(post.getPOST_TYPE()==1) { %>

								<i class="fa fa-film" aria-hidden="true"></i> <%= movie.getMOVIE_TITLE() %> &nbsp;
								<i class="fa fa-map-marker" aria-hidden="true"></i> <%= post.getPOST_NATION() %>

							<% } %>
							
							</small>	</h3>						
						<a href="./postDetailAction.po?type=${type}&postno=<%= post.getPOST_NO() %>"
							class="w3-hover-opacity"> <img src="<%= post.getPOST_FILE() %>"
							style="width: 100%" />
						</a>

						<div class="w3-container w3-white" style="padding-left: 1px; padding-top: 5px;">
							<div align="right">
							<span class="label label-default">VIEW <%= post.getPOST_READCOUNT() %></span>
							<a href="./postLikeAction.po?type=<%=post.getPOST_TYPE()%>&post_no=<%=post.getPOST_NO()%>&sort=${sort}">
								<span class="label label-danger">LIKE <%= post.getPOST_LIKECOUNT() %></span></a>
								<a href="./postDetailAction.po?type=${type}&postno=<%= post.getPOST_NO() %>"><span class="label label-info">COMMENT <%= replyCount %></span></a>
							</div>

						<hr />
						</div>
					<%}%>
				</div>

				<div class="w3-half">
					<% for(int i=1; i<postList.size(); i+=2) {
						PostBean post = postList.get(i);
						MovieBean movie = movieDAO.getDetailMovie(post.getPOST_MOVIE_ID()); 
						PostDAO postDAO = new PostDAO();
						int replyCount = postDAO.getReplyListCount(post.getPOST_NO());
						%>
								<h3><%= post.getPOST_SUBJECT() %>
														<small>	<% if(post.getPOST_TYPE()==1) { %>

								<i class="fa fa-film" aria-hidden="true"></i> <%= movie.getMOVIE_TITLE() %> &nbsp;
								<i class="fa fa-map-marker" aria-hidden="true"></i> <%= post.getPOST_NATION() %>

							<% } %>
							
							</small>	</h3>						
						<a href="./postDetailAction.po?type=${type}&postno=<%= post.getPOST_NO() %>"
							class="w3-hover-opacity"> <img src="<%= post.getPOST_FILE() %>"
							style="width: 100%" />
						</a>

						<div class="w3-container w3-white" style="padding-left: 1px; padding-top: 5px;">
							<div align="right">
							<span class="label label-default">VIEW <%= post.getPOST_READCOUNT() %></span>
							<a href="./postLikeAction.po?type=<%=post.getPOST_TYPE()%>&post_no=<%=post.getPOST_NO()%>&sort=${sort}">
									<span class="label label-danger">LIKE <%= post.getPOST_LIKECOUNT() %></span></a>
								<a href="./postDetailAction.po?type=${type}&postno=<%= post.getPOST_NO() %>"><span class="label label-info">COMMENT <%= replyCount %></span></a>
							</div>


						<hr />
						</div>
					<%}%>
				</div>
			</div>
			<div class="form-group" align="right">
			<h4><a href="postWrite.po?type=${type}" role="button" class="w3-button"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> 글쓰기</a></h4>
			</div>
		<hr />
		<div align="center">
	  <form action="postSearchAction.po" class="form-inline">
		&nbsp;
	  <input type="text" class="form-control" name="search" required
	  			placeholder="영화제목 or 나라이름"/>
	  
	  	<input type="hidden" name="type" value="1" required/>
		<input type="submit" class="form-control btn-default" value="검색"></input>

  </form>	</div>

			<div class="w3-bar" align="center">
				<!-- 게시판 페이지 처리 -->
				<c:choose>
					<c:when test="${nowpage<=1}">
						<span class="w3-button">&laquo;</span>
					</c:when>
					<c:otherwise>
						<a
							href="./postListAction.po?type=${type}&page=${nowpage-1}&sort=${sort}"
							class="w3-button">&laquo;</a>
					</c:otherwise>
				</c:choose>

				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:choose>
						<c:when test="${a==nowpage}">
							<span class="w3-button w3-gray">${a}</span>
						</c:when>
						<c:otherwise>
							<a href="./postListAction.po?type=${type}&page=${a}&sort=${sort}"
								class="w3-button">${a}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${nowpage>=maxpage}">
						<span class="w3-button">&raquo;</span>
					</c:when>
					<c:otherwise>
						<a
							href="./postListAction.po?type=${type}&page=${nowpage+1}&sort=${sort}"
							class="w3-button">&raquo;</a>
					</c:otherwise>
				</c:choose>

			</div>
			<hr />
		</section>
	</div>

	<%@ include file="/footer.jsp"%>
</body>
</html>