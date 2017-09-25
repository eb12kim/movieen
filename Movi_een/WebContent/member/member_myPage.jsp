<%@page import="net.movie.db.MovieBean"%>
<%@page import="net.movie.db.MovieDAO"%>
<%@page import="net.post.db.PostBean"%>
<%@page import="net.post.db.PostDAO"%>
<%@page import="net.post.db.ReplyBean"%>
<%@page import="java.util.List"%>
<%@page import="net.member.db.MemberDAO"%>
<%@page import="net.member.db.MemberBean"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="member" value="${requestScope.member}" />
<c:set var="myPostListCount" value="${requestScope.myPostListCount}" />
<c:set var="myReplyListCount" value="${requestScope.myReplyListCount}" />
<c:set var="myLikeListCount" value="${requestScope.myLikeListCount}" />
<c:set var="myGetLikeCount" value="${requestScope.myGetLikeCount}" />
<c:set var="movieLikeListCount" value="${requestScope.movieLikeListCount}" />
<c:set var="tripLikeListCount" value="${requestScope.tripLikeListCount}" />

<!DOCTYPE>
<html>
<head>
<!--  부트스트랩 & W3 CSS core -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="jquery/3.2.1/jquery-3.2.1.min.js"></script>
<script src="bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!--  Customized CSS for movieen -->
<link href="css/common.css" rel="stylesheet">
<script>
	function myFunction(id,id2) {
		var x = document.getElementById(id);
		var y = document.getElementById(id2);
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
			y.className = y.className.replace("fa fa-caret-down", "fa fa-caret-up");
		} else {
			x.className = x.className.replace(" w3-show", "");
			y.className = y.className.replace("fa fa-caret-up", "fa fa-caret-down");
		}
	}
	
	function myFunction2(id, id2) {
		var x = document.getElementById(id);
		var y = document.getElementById(id2);
		if (x.className.indexOf("w3-hide") == -1) {
			x.className += " w3-hide";
			y.className = y.className.replace("fa fa-caret-up", "fa fa-caret-down");
		} else {
			x.className = x.className.replace(" w3-hide", "");
			y.className = y.className.replace("fa fa-caret-down", "fa fa-caret-up");
		}
	}
	

</script>
<title>MyPage</title>
<style>
.test {
  width        : 100%;     /* 너비는 변경될수 있습니다. */
  text-overflow: ellipsis;  /* 위에 설정한 100px 보다 길면 말줄임표처럼 표시합니다. */
  white-space  : nowrap;    /* 줄바꿈을 하지 않습니다. */
  overflow     : hidden;    /* 내용이 길면 감춤니다 */
  display      : block;     /* ie6이상 현재요소를 블럭처리합니다. */
}

</style>

</head>
<body>

	<%@ include file="/gnb.jsp"%>

	<header class="w3-container w3-center w3-padding-40 w3-white">
		<h1 class="w3-xxxlarge">
			<b>${member.getMEMBER_NAME()}님의 My Page </b>
		</h1>
		<h6>${member.getMEMBER_NAME()}님환영합니다.</h6>
	</header>

	<div class="container">
		<div class="container" align="center">
			<div class="col-sm-4 w3-card-2 w3-round w3-white">
				<div class="w3-container">
					<h3>${member.getMEMBER_NAME()}님의프로필</h3>
					<hr />
					<p>
						<i class="fa fa-id-card fa-fw w3-margin-right w3-text-theme"></i>
						${member.getMEMBER_ID()}
					</p>
					<p>
						<i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i>
						${member.getMEMBER_AGE()}
					</p>
					<p>
						<i class="fa fa-globe fa-fw w3-margin-right w3-text-theme"></i>
						${member.getMEMBER_NATIONALITY()}
					</p>
					<p>
						<i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i><a
							href="memberView.me">회원정보 수정 및 탈퇴하기>></a>
					</p>
				</div>
			</div>
			<div class="col-sm-8">

				<br />
				<h3>
					<i class="fa fa-clipboard" aria-hidden="true">
						${myPostListCount} 개의 글을 작성하셨습니다.</i>
				</h3>
				<h3>
					<i class="fa fa-thumbs-up" aria-hidden="true">
						${myGetLikeCount} 개의 공감을 받았습니다.</i>
				</h3>
				<h3>
					<i class="fa fa-reply" aria-hidden="true"> ${myReplyListCount}
						개의 댓글을 달았습니다.</i>
				</h3>
				 <a href="postListAction.po?type=3" class="w3-bar-item w3-button">질문 및 건의하러 가기 <i class="fa fa-caret-right"></i></a>

			</div>

		</div>

		<hr>
		
		
		
		<!-- 컨텐츠 영역 -->
	<div class="container">
		<!--  영화 선호  -->
		<div class="w3-container">
			<button onclick="myFunction2('Demo4','Demo4_arrow')"
				class="w3-button w3-block w3-left-align">
				<h3> 영화맞춤 Movi'een <i id="Demo4_arrow" class="fa fa-caret-up"	aria-hidden="true"></i>     &nbsp;&nbsp;<small><a href="memberView.me">영화선호장르 수정하러 가기>></a></small>
				</h3>
			</button>
			<div id="Demo4" class="w3-container">
				<h5><i class="fa fa-heart" aria-hidden="true"></i>  ${member.getMEMBER_NAME()}님께서 좋아하는 영화장르는 ${member.printMEMBER_MOVIE_LIKE()}</h5>
				<hr />
				<div class="w3-row-padding">
						<c:if test="${movieLikeListCount!=0}">
							<%
								List<PostBean> movieLikeList = (List) request.getAttribute("movieLikeList");
									MovieDAO movieDAO = new MovieDAO();
									for (int i = 0; i < movieLikeList.size(); i++) {
										PostBean post = movieLikeList.get(i);
										MovieBean movie = movieDAO.getDetailMovie(post.getPOST_MOVIE_ID());
										PostDAO postDAO = new PostDAO();
										int replyCount = postDAO.getReplyListCount(post.getPOST_NO());
							%>
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
							<%
								}
							%>
						</c:if>
						<c:if test="${movieLikeListCount==0}">
							해당 게시물이 없습니다.
						</c:if>
					</div>
				</div>
			</div>
		<hr>
		
		
		
			<!--  여행선호 -->
			<div class="w3-container">
			<button onclick="myFunction2('Demo5', 'Demo5_arrow')"
				class="w3-button w3-block w3-left-align">
				<h3> 여행맞춤 Movi'een <i class="fa fa-caret-up"	aria-hidden="true"></i>     &nbsp;&nbsp;<small><a href="memberView.me">여행지역 수정하러 가기>></a></small>
						
				</h3>
			</button>
			<div id="Demo5" class="w3-container">
				<h5><i class="fa fa-heart" aria-hidden="true"></i>  ${member.getMEMBER_NAME()}님께서 여행하고 싶은 나라는 ${member.printMEMBER_TRIP_LIKE()}</h5>
				<hr />
				<div class="w3-row-padding">
						<c:if test="${tripLikeListCount!=0}">
							<%
								List<PostBean> tripLikeList = (List) request.getAttribute("tripLikeList");
									MovieDAO movieDAO = new MovieDAO();
									for (int i = 0; i < tripLikeList.size(); i++) {
										PostBean post = tripLikeList.get(i);
										MovieBean movie = movieDAO.getDetailMovie(post.getPOST_MOVIE_ID());
										PostDAO postDAO = new PostDAO();
										int replyCount = postDAO.getReplyListCount(post.getPOST_NO());
							%>
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
							<%
								}
							%>
						</c:if>
						<c:if test="${tripLikeListCount==0}">
							해당 게시물이 없습니다.
						</c:if>
					</div>
				</div>
			</div>


		<!-- 작성한 글  -->

		<div class="w3-container">
			<hr />
			<button onclick="myFunction('Demo1','Demo1_arrow')"
				class="w3-button w3-block w3-left-align">
				<h3>
					내가 작성한 글 <i id="Demo1_arrow" class="fa fa-caret-down" aria-hidden="true"></i>
				</h3>
			</button>

			<div id="Demo1" class="w3-container w3-hide">
				<c:if test="${myPostListCount!=0}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>게시판</th>
								<th>글번호</th>
								<th>글제목</th>
								<th>등록일자</th>
								<th>조회수</th>
								<th>좋아요</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="post" items="${requestScope.myPostList}">
								<tr>
									<c:if test="${post.getPOST_TYPE()==1}">
										<td>Movi'een</td>
									</c:if>
									<c:if test="${post.getPOST_TYPE()==2}">
										<td>Community</td>
									</c:if>
									<c:if test="${post.getPOST_TYPE()==3}">
										<td>QnA</td>
									</c:if>
									<td>${post.getPOST_NO()}</td>
									<td><a
										href="./postDetailAction.po?type=${post.getPOST_TYPE()}&postno=${post.getPOST_NO()}">${post.getPOST_SUBJECT()}</a></td>
									<td>${post.getPOST_DATE()}</td>
									<td>${post.getPOST_READCOUNT()}</td>
									<td>${post.getPOST_LIKECOUNT()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${myPostListCount==0}">
				작성한 글이 없습니다.
			</c:if>
			</div>
		</div>
		<hr />

		<!--  댓글 -->
		<div class="w3-container">
			<button onclick="myFunction('Demo2','Demo2_arrow')"
				class="w3-button w3-block w3-left-align">
				<h3>
					내가 단 댓글 <i id="Demo2_arrow" class="fa fa-caret-down" aria-hidden="true"></i>
				</h3>
			</button>
			<div id="Demo2" class="w3-container w3-hide">
				<c:if test="${myReplyListCount!=0}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th class="col-sm-2">등록일자</th>
								<th>댓글내용</th>

							</tr>
						</thead>
						<tbody>
							<%
								List<ReplyBean> myReplyList = (List) request.getAttribute("myReplyList");
									for (int i = 0; i < myReplyList.size(); i++) {
										ReplyBean reply = myReplyList.get(i);
										PostDAO postDAO = new PostDAO();
										PostBean post = postDAO.getDetailPost(reply.getREPLY_POST_NO());
							%>
							<tr>
								<td class="col-sm-2"><%=reply.getREPLY_DATE()%></td>
								<td><a
									href="./postDetailAction.po?type=<%=post.getPOST_TYPE()%>&postno=<%=post.getPOST_NO()%>"><%=reply.getREPLY_TEXT()%></a></td>

							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</c:if>
				<c:if test="${myReplyListCount==0}">
						단 댓글이 없습니다.
					</c:if>
			</div>

		</div>
		<hr>
		<!--  좋아요한 글 -->
		<div class="w3-container">
			<button onclick="myFunction('Demo3','Demo3_arrow')"
				class="w3-button w3-block w3-left-align">
				<h3>
					내가 'LIKE'한 글 <i id="Demo3_arrow" class="fa fa-caret-down" aria-hidden="true"></i>
				</h3>
			</button>
			<div id="Demo3" class="w3-container w3-hide">
				<c:if test="${myLikeListCount!=0}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>게시판</th>
								<th>글번호</th>
								<th>글제목</th>
								<th>등록일자</th>
								<th>조회수</th>
								<th>좋아요</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="post" items="${requestScope.myLikeList}">
								<tr>
									<c:if test="${post.getPOST_TYPE()==1}">
										<td>Movi'een</td>
									</c:if>
									<c:if test="${post.getPOST_TYPE()==2}">
										<td>Community</td>
									</c:if>
									<c:if test="${post.getPOST_TYPE()==3}">
										<td>QnA</td>
									</c:if>
									<td>${post.getPOST_NO()}</td>
									<td><a
										href="./postDetailAction.po?type=${post.getPOST_TYPE()}&postno=${post.getPOST_NO()}">${post.getPOST_SUBJECT()}</a></td>
									<td>${post.getPOST_DATE()}</td>
									<td>${post.getPOST_READCOUNT()}</td>
									<td>${post.getPOST_LIKECOUNT()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${myLikeListCount==0}">
						'LIKE'한 글이 없습니다.
					</c:if>
			</div>
		</div>

</div>
	
</div>

	<%@ include file="/footer.jsp"%>
</body>
</html>