<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="net.movie.db.MovieBean"%>
<%@page import="net.movie.db.MovieDAO"%>
<%@page import="net.post.db.PostBean"%>
<%@page import="net.post.db.PostDAO"%>
<%@page import="net.post.db.MapBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	PostDAO postDAO = new PostDAO();
	List<MapBean> mapList = postDAO.initMap();
%>
<%-- <c:set var="maplist" value="${requestScope.mapList }" />	 --%>
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

<style>
#map {
	height: 400px;
	width: 100%;
}
</style>
</head>
<body>
	<%@ include file="/gnb.jsp"%>
	<header class="w3-container w3-center w3-padding-40 w3-white">
		<h1 class="w3-xxxlarge">
			<b>지도로 Movi'een 찾기</b>
		</h1>
		<h6>구글지도의 마커를 찍어보세요.</h6>
	</header>

<div class="container">
  <div id="map"></div>	
  <iframe id="map_detail" src="" style="border:0; width:100%; height:40%;"></iframe>
  
  <a id="go_detail" href="">크게 보기</a>
</div>

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
        	var x = '/Movi_een/postDetailAction.po?isMap=true&type='+type+'&postno='+postno;
        	document.getElementById('map_detail').src = x;
        	
        	var y = '/Movi_een/postDetailAction.po?type='+type+'&postno='+postno;
        	document.getElementById('go_detail').href = y;
        }
      }
     	//마커클러스터링
      var markerCluster = new MarkerClusterer(map, markers, {imagePath:'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
      
  } //end initMap
	

</script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBnBNqtyE8plQKCxFw7kdlpiFpYQw2Jqo&callback=initMap"></script>
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>

	<%@ include file="/footer.jsp"%>
</body>

</html>