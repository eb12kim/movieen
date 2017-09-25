<%@page import="net.post.db.ReplyBean"%>
<%@page import="java.util.List"%>
<%@page import="net.movie.db.MovieDAO"%>
<%@page import="net.movie.db.MovieBean"%>
<%@page import="net.post.db.PostBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="post" value="${requestScope.postdata}" />
<c:set var="loginId" value="${sessionScope.LoginId }" />
<c:set var="listcount" value="${requestScope.reply_listcount}" />
<c:set var="nowpage" value="${requestScope.reply_page}" />
<c:set var="maxpage" value="${requestScope.reply_maxpage}" />
<c:set var="startpage" value="${requestScope.reply_startpage}" />
<c:set var="endpage" value="${requestScope.reply_endpage}" />

<%
	PostBean post = (PostBean) request.getAttribute("postdata");
	int type = post.getPOST_TYPE();

	MovieDAO movieDAO = new MovieDAO();
	MovieBean movie = movieDAO.getDetailMovie(post.getPOST_MOVIE_ID());
	
	session.setAttribute("Page", "detail");
	session.setAttribute("detail_type", type);
	session.setAttribute("detail_no", post.getPOST_NO());
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
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="jquery/3.2.1/jquery-3.2.1.min.js"></script>
<script src="bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!--  Customized CSS for movieen -->
<link href="css/common.css" rel="stylesheet">

<title>${post.getPOST_SUBJECT()}</title>
<script>
function chckLogin(){
	if(${loginId==null}){
		alert("로그인 후 작성할 수 있습니다.");
	}
}
</script>
<style>
pre{
	border:0px;
	white-space:pre-wrap;
}
</style>
</head>
<body>

	<c:if test="${(param.isMap)==null || !(param.isMap).equals('true')}">
	<%@ include file="/gnb.jsp"%>

	<header class="w3-container w3-center w3-padding-40 w3-white">
		<h1 class="w3-xxxlarge">
			<b> <c:choose>
					<c:when test="${post.getPOST_TYPE()==1}">Movi'een</c:when>
					<c:when test="${post.getPOST_TYPE()==2}">Community</c:when>
					<c:when test="${post.getPOST_TYPE()==3}">QnA게시판</c:when>
				</c:choose>
			</b>
		</h1>
		<h6>
			Welcome to <span class="w3-tag">Movi'een</span>
		</h6>
		
	</header>
	</c:if>

	<div class="container" style="padding-top: 20px;">
		<!-- Blog entry -->
		<div class="w3-card-4">
			<!--  이미지 출력  -->

			<div id="myCarousel" class="carousel slide" data-ride="carousel">

				<!--  페이지를 가리키는  -->
				<ol class="carousel-indicators">
					<c:if test="${post.getPOST_FILE()!=null}">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					</c:if>
					<c:if test="${post.getPOST_FILE_2()!=null}">
						<li data-target="#myCarousel" data-slide-to="1"></li>
					</c:if>
				</ol>

				<!--  슬라이드할 대상을 wrapping하기 -->
				<div class="carousel-inner" align="center">
					<c:if test="${post.getPOST_FILE()!=null}">
						<div class="item active"">
							<img src="${post.getPOST_FILE()}" alt="${post.getPOST_FILE()}" style="width:100%;" />
						</div>
					</c:if>
					<c:if test="${post.getPOST_FILE_2()!=null}">
						<div class="item">
							<img src="${post.getPOST_FILE_2()}"	alt="${post.getPOST_FILE_2()}" style="width: 100%" />
						</div>
					</c:if>
				</div>

				<!--  왼쪽/오른쪽 컨트롤러 -->
				<c:if test="${post.getPOST_FILE_2()!=null}">
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span> <span
						class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span> <span
						class="sr-only">Next</span>
					</a>
				</c:if>
			</div>


			<div class="w3-container">
				<h3>
					<strong>${post.getPOST_SUBJECT()}</strong> <small> by
						${post.getPOST_USER_ID()}  &nbsp;&nbsp;<i class="fa fa-calendar" aria-hidden="true"></i>${post.getPOST_DATE()}
					</small>
				</h3>
				<h5>
					<c:if test="${post.getPOST_TYPE()==1}">
						<span class="glyphicon glyphicon-film"></span> <%=movie.getMOVIE_TITLE()%>
						 &nbsp;&nbsp;
				<i class="fa fa-map-marker" aria-hidden="true"></i><%=post.getPOST_NATION()%>
					</c:if>
				</h5>
			</div>

			<div class="w3-container">
				<div class="w3-row">
					<div class="w3-col m8 s12">
						<p>
							<span class="label label-default">VIEW
								${post.getPOST_READCOUNT()}</span>
								<c:if test="${(param.isMap)==null || !(param.isMap).equals('true')}">
								<a href="./postLikeAction.po?type=${post.getPOST_TYPE()}&post_no=${post.getPOST_NO()}">
									<span class="label label-danger">LIKE ${post.getPOST_LIKECOUNT()}</span> &nbsp;&nbsp;<i class="fa fa-heart" aria-hidden="true"></i>
								</a>
								</c:if>
								<c:if test="${(param.isMap)!=null && (param.isMap).equals('true')}">
									<span class="label label-danger">LIKE ${post.getPOST_LIKECOUNT()}</span>
								</c:if>
						</p>
					</div>

				</div>
				<pre>${post.getPOST_TEXT()}</pre>

			</div>
			
		<c:if test="${(param.isMap)==null || !(param.isMap).equals('true')}">
			<!-- google map -->
			<c:if test="${post.getPOST_TYPE()==1}">
				<div id="map" style="height:400px; width:100%;"></div>
			</c:if>
			<!-- the comment box -->
                <div class="well">
                	<div>
						<c:if test="${listcount!=0}">
						<%List<ReplyBean> replyList = (List)request.getAttribute("replyList");
						for(int i=0; i<replyList.size(); i++) {
							ReplyBean reply = replyList.get(i); %>
							<%= reply.getREPLY_USER_ID() %>&nbsp;
							<%= reply.getREPLY_DATE() %>&nbsp;
					<% if(session.getAttribute("LoginId")!=null) {
								if(reply.getREPLY_USER_ID().equals(session.getAttribute("LoginId")) || session.getAttribute("LoginId").equals("admin")) { %>
									<a href="postReplyDeleteAction.po?replyno=<%=reply.getREPLY_NO()%>&id=<%=reply.getREPLY_USER_ID()%>&type=<%=type%>&postno=<%=reply.getREPLY_POST_NO()%>"><i class="fa fa-times"></i></a>
								<% } %>
							<% } %>
							<pre><%= reply.getREPLY_TEXT() %></pre>
		

						<hr>
						<% } %>
					
						<div class="w3-bar" align="center">
				<!-- 댓글 페이지 처리 -->
				<c:choose>
					<c:when test="${nowpage<=1}">
						<span class="w3-button">&laquo;</span>
					</c:when>
					<c:otherwise>
						<a href="./postDetailAction.po?type=<%=type%>&postno=${post.getPOST_NO()}&reply_page=${nowpage-1}" class="w3-button">&laquo;</a>
					</c:otherwise>
				</c:choose>

				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:choose>
						<c:when test="${a==nowpage}">
							<span class="w3-button w3-gray">${a}</span>
						</c:when>
						<c:otherwise>
							<a href="./postDetailAction.po?type=<%=type%>&postno=${post.getPOST_NO()}&reply_page=${a}" class="w3-button">${a}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${nowpage>=maxpage}">
						<span class="w3-button">&raquo;</span>
					</c:when>
					<c:otherwise>
						<a href="./postDetailAction.po?type=<%=type%>&postno=${post.getPOST_NO()}&reply_page=${nowpage+1}" class="w3-button">&raquo;</a>
					</c:otherwise>
				</c:choose>
						</div>
					</c:if>
					
					</div>
					
                    <h4>Leave a Comment:</h4>
                    <form action="./postReplyAction.po?type=${post.getPOST_TYPE()}&post_no=${post.getPOST_NO()}" method="post">
                        <div class="form-group">
                            <textarea class="form-control" rows="3" name="REPLY_TEXT" required="required"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary" onclick="javascript:chckLogin();">댓글 등록</button>
                    </form>
                </div>
           	<div align="center">
                <c:if test="${loginId!=null&&loginId.equals('admin') || loginId!=null&&(post.getPOST_USER_ID()).equals(loginId)}">
				<a href="postModifyView.po?type=${post.getPOST_TYPE()}&postno=${post.getPOST_NO() }&id=${post.getPOST_USER_ID()}">
					<span class="w3-button">수정</span>
					</a>
				<a href="postDeleteAction.po?type=${post.getPOST_TYPE()}&postno=${post.getPOST_NO() }&id=${post.getPOST_USER_ID()}">
					<span class="w3-button">삭제</span>
					</a>
				</c:if>
			</div>
			</c:if>
		</div>
		<!-- END BLOG ENTRIES -->

	<c:if test="${(param.isMap)==null || !(param.isMap).equals('true')}">
		<hr />
		<div align="center">
	
			<a href="./postDetailAction.po?type=${post.getPOST_TYPE()}&postno=${post.getPOST_NO()}&move=prev"><span class="w3-button">&laquo; 이전글</span></a>
			<a href="./postDetailAction.po?type=${post.getPOST_TYPE()}&postno=${post.getPOST_NO()}&move=next"><span class="w3-button">다음글 &raquo;</span></a>
			<br />
			<a href="postListAction.po?type=${post.getPOST_TYPE()}">게시판으로 돌아가기</a>
		</div>
	</c:if>
		<hr />
	</div>
	<c:if test="${(param.isMap)==null || !(param.isMap).equals('true')}">
		<%@ include file="/footer.jsp"%>
	</c:if>

	<!--  Google Map Script -->
  <script>
    var lat = <%=post.getPOST_GPS_LAT()%>;
    var lng = <%=post.getPOST_GPS_LNG()%>;


    //지도 초기화
    function initMap() {
    	var latlng = new google.maps.LatLng(lat, lng);

      var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: latlng
      });

      //DB정보 예시

      var postInfo = '<%=post.getPOST_NO()%>:<%=post.getPOST_SUBJECT()%>';


        // 마커를 생성합니다
        var marker = new google.maps.Marker({
          position: latlng, // 마커의 위치
          map: map // 마커를 표시할 지도
        });

        //reverse geocoding

        var geocoder = new google.maps.Geocoder();

        var revgeoinfowindow = new google.maps.InfoWindow;

        geocodeLatLng(geocoder, map, revgeoinfowindow, latlng);


    } //end initMap

    function geocodeLatLng(geocoder, map, infowindow, latlng) {

        geocoder.geocode({
          'location': latlng
        }, function(results, status) {
          if (status === 'OK') {
            if (results[1]) {
              var marker = new google.maps.Marker({
                position: latlng,
                map: map
              });
              infowindow.setContent(results[1].formatted_address);
              infowindow.open(map, marker);
              google.maps.event.addListener(marker, 'click', clickListener(map, marker, infowindow));
            } else {
              window.alert('No results found');
            }
          } else {
            window.alert('Geocoder failed due to: ' + status);
          }
        });
      }

      function clickListener(map, marker, infowindow) {
        return function() {
          infowindow.open(map, marker);
        }
      }

  </script>

  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBnBNqtyE8plQKCxFw7kdlpiFpYQw2Jqo&callback=initMap"></script>
  <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>

  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBnBNqtyE8plQKCxFw7kdlpiFpYQw2Jqo&callback=initMap"></script>
  <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
</body>
</html>