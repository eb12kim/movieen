<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    int prevPage = Integer.parseInt(request.getParameter("type"));
    session.setAttribute("prevPage", prevPage);
%>
<c:set var="type" value="${requestScope.type}" />
<c:set var="listcount" value="${requestScope.listcount}" />
<c:set var="nowpage" value="${requestScope.page}" />
<c:set var="maxpage" value="${requestScope.maxpage}" />
<c:set var="startpage" value="${requestScope.startpage}" />
<c:set var="endpage" value="${requestScope.endpage}" />

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
<title>QnA 게시판</title>
</head>
<body>
	<%@ include file="/gnb.jsp"%>
	<header class="w3-container w3-center w3-padding-40 w3-white">
    <h1 class="w3-xxxlarge">
    	<b>Q&A게시판</b>
    </h1>
    <h6>무엇이든 물어보세요!</h6>
</header>
	
	<div class=container >

		<table class="table">
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>등록일자</th>
					<th>조회수</th>
					<th>좋아요</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${requestScope.postList }">
				<tr>
					
					<td>${post.getPOST_NO()}</td>
					<td><a href="./postDetailAction.po?type=${type}&postno=${post.getPOST_NO()}">${post.getPOST_SUBJECT()}</a></td>
					<td>${post.getPOST_USER_ID()}</td>
					<td>${post.getPOST_DATE()}</td>
					<td>${post.getPOST_READCOUNT()}</td>
					<td>${post.getPOST_LIKECOUNT()}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
			<div class="form-group"  align="right">
			<a href="postWrite.po?type=${type}" role="button" class="btn btn-primary" >글쓰기</a>
			</div>


	<div class="container" align="center">
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
	</div>	
	<%@ include file="/footer.jsp"%>

</body>
</html>