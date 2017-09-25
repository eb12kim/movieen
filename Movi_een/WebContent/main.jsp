<%@page import="net.post.db.MapBean"%>
<%@page import="java.util.List"%>
<%@page import="net.post.db.PostDAO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    int prevPage = 100;
    session.setAttribute("prevPage", prevPage);
    session.setAttribute("Page", "");
	PostDAO postDAO = new PostDAO();
	List<MapBean> mapList = postDAO.initMap();
%>
<c:set var="id" value="${sessionScope.LoginId}" />

<!DOCTYPE HTML>
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
<!-- <link href="css/common.css" rel="stylesheet"> -->
<style>
body, html {
    height: 100%;
    line-height: 1.8;
}
/* Full height image header */
.bgimg-1 {
    background-position: center;
    background-size: cover;
    background-image: url("main.jpg");
    min-height: 100%;
    
}
.w3-bar .w3-button {
    padding: 16px;
}
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
body, html {
    height: 100%;
    line-height: 1.8;
}
</style>


<title>메인 화면</title>
</head>
<body>
	<%@ include file="/gnb.jsp" %>
	
<!-- Header with full-height image -->
<header class="bgimg-1 w3-display-container w3-grayscale-min" id="home">
  <div class="w3-display-left w3-text-black" style="padding:48px">
    <span class="w3-jumbo w3-hide-small"><b>Share your Movi'een Moment!</b></span><br>
    <span class="w3-xxlarge w3-hide-large w3-hide-medium">Share your Movi'een Moment!</span><br>
    <span class="w3-large">다른 여행자들의 Movi'een Moment을 느껴보세요.</span>
  
  <!-- 영화검색 or 여행지 검색 -->
  <form action="postSearchAction.po">

	  <input type="text" class="w3-input w3-border-0 w3-third w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off" name="search" required
	  			placeholder="영화제목 or 나라이름"/>
	  <input type="hidden" name="type" value="1" required/>
	  <input type="submit" class="w3-button w3-black w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off" value="검색"></input>

  </form>
  </div> 

</header>
<div class="w3-container w3-light-grey" style="padding:50px 0px;padding-bottom:0px;" id="googlemap">
  <h2 class="w3-center"><strong>Movi'een in the world</strong></h2>
    <p class="w3-center w3-large">마커를 클릭하면 자세히 볼 수 있습니다.</p>
  <br />
<div id="map" style="width:100%;height:480px;"></div>	
</div>
 <%@ include file="/footer.jsp" %>
 <script>
    var map;
    var geoInfoWindow;
    var pos;
    var markers = [];
    var locations=[];
    var postInfos=[];


    //지도 초기화
    function initMap() {
 

      map = new google.maps.Map(document.getElementById('map'), {
        zoom: 2,
        center: {
          lat: 37.555148,
          lng: 126.936887
        }
      });

      //DB정보 예시
      locations = [<%for (int i = 0; i < mapList.size(); i++) {%>
      	  {latlng:new google.maps.LatLng(<%=mapList.get(i).getPOST_GPS_LAT()%>,
      	  <%=mapList.get(i).getPOST_GPS_LNG()%>)},
      		<%}%>
      ];
      
     postInfos = [<%for (int i = 0; i < mapList.size(); i++) {%>
    	  {postinfo:'<%=mapList.get(i).getPOST_NO()%>.<%=mapList.get(i).getPOST_SUBJECT()%><br><%=mapList.get(i).getMOVIE_TITLE()%>'},
        <%}%>
     ];
	
      for (var i = 0; i < locations.length; i++) {
        // 마커를 생성합니다
        var marker = new google.maps.Marker({
          position: locations[i].latlng, // 마커의 위치
          map: map // 마커를 표시할 지도
        });

        // 마커에 표시할 인포윈도우를 생성
        var infowindow = new google.maps.InfoWindow({
          content: postInfos[i].postinfo // 인포윈도우에 표시할 내용
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록
        // 이벤트 리스너로는 클로저를 만들어 등록
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록
        google.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
        google.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
        google.maps.event.addListener(marker, 'click', clickListener(map, marker, infowindow));


        markers.push(marker);
      }

      // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
      function makeOverListener(map, marker, infowindow) {
        return function() {
          infowindow.open(map, marker);
        };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다
      function makeOutListener(infowindow) {
        return function() {
          infowindow.close();
        };
      }

      // 마커 클릭시 중앙으로 이동 & 포스트 정보 띄우기
      function clickListener(map, marker, infowindow){
        return function(){
        	var type = 1;
        	var info = infowindow.content.split(".");
        	var postno = info[0];
        	var x = '/Movi_een/postDetailAction.po?type='+type+'&postno='+postno;
        	location.href = x;
        	

        }
      }
     	//마커클러스터링
      var markerCluster = new MarkerClusterer(map, markers, {imagePath:'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
      
  } //end initMap
	

</script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBnBNqtyE8plQKCxFw7kdlpiFpYQw2Jqo&callback=initMap"></script>
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
</body>
</html>