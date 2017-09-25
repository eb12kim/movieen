<%@page import="net.movie.db.MovieBean"%>
<%@page import="net.post.db.PostDAO"%>
<%@page import="net.post.db.PostBean"%>

<%@page import="net.movie.db.MovieDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="post" value="${requestScope.post}" />
<%
PostBean post= (PostBean)request.getAttribute("post");
MovieDAO movieDao = new MovieDAO();
MovieBean movie= movieDao.getDetailMovie(post.getPOST_MOVIE_ID());
%>

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

<!--  UploadCare -->
<script>
	UPLOADCARE_LOCALE = "ko";
	UPLOADCARE_TABS = "file url facebook instagram";
	UPLOADCARE_PUBLIC_KEY = "62a4d46e5b17aee8d761";
</script>
<script charset="utf-8"
	src="//ucarecdn.com/libs/widget/3.1.1/uploadcare.full.min.js"></script>
<!-- 게시판에 따른 영역 show&hide / required 처리 -->
<script>
$(document).ready(function() {
		if (${post.getPOST_TYPE()==1}) {
			$('#mandatory_pic').required = true;
			$('.mandatory').prop("hidden", false);
			$('#movieSelect').prop("required", true);
		} else {
			$('#mandatory_pic').required = false;
			$('.mandatory').prop("hidden", true);
			$('.mandatory').disabled=true;
			$('#movieSelect').prop("required", false);
		};
		
		if(${post.getPOST_FILE_2()==null}){
			$("#description2").prop("hidden", true);
		};
		
		$("#btn_update").click(function() {
			var x = confirm("수정내용이 사라집니다. 글목록으로 돌아가시겠습니까?");
			if (x == true) {
				window.location = "memberView.me?id=${member.getMEMBER_ID()}"
			} else {
				return false;
			}

		});
		
		var singleWidget1 = uploadcare.SingleWidget('[role=uploadcare-uploader1]');
		
		singleWidget1.onUploadComplete(function(info) {
			$("#description").attr("src",info.cdnUrl);
			$("#description").prop("hidden", false);
					
		});
		var singleWidget2 = uploadcare.SingleWidget('[role=uploadcare-uploader2]');

		singleWidget2.onUploadComplete(function(info) {
			$("#description2").attr("src",info.cdnUrl);
			$("#description2").prop("hidden", false);
		});
	
	});

</script>
<title>게시글 수정</title>
</head>
<body>
	<%@ include file="/gnb.jsp"%>
	<header class="w3-container w3-center w3-padding-40 w3-white">
		<h1 class="w3-xxxlarge">
			<b>글 수정하기</b>
		</h1>
		<h6>등록한 글을 수정합니다.</h6>
	</header>

		<div class="container" style="padding-top: 5px;">
		<div class="w3-card-4" style="margin-bottom: 50px;">

			<div class="panel-body">
				<form action="./postModifyAction.po" method="post" name="modifyform"
					class="form-horizontal">
					<input type="hidden" name="POST_NO" value="${post.getPOST_NO()}" />
					<input type="hidden" name="POST_TYPE"
						value="${post.getPOST_TYPE()}" />
					<h3>
						<a href="postListAction.po?type=${post.getPOST_TYPE()}"
							id="btn_update"> <span class="label label-default"> <c:choose>
									<c:when test="${post.getPOST_TYPE()==1}">메인게시판</c:when>
									<c:when test="${post.getPOST_TYPE()==2}">자유게시판</c:when>
									<c:when test="${post.getPOST_TYPE()==3}">QnA게시판</c:when>
								</c:choose>
						</span>
						</a>
					</h3>
					<div>
						<c:if test="${post.getPOST_FILE()!=null}">
							<p>
								<img src="${post.getPOST_FILE()}" alt="${post.getPOST_FILE()}"
									style="width: 100%;" id="description" /> <br />
							<p>
								<input type="hidden" role="uploadcare-uploader1"
									name="POST_FILE" data-crop="disabled" data-images-only="true"
									value="${post.getPOST_FILE()}">
							</p>
							</p>
						</c:if>
						<!-- 메인게시판일 때만 보여지는 부분 -->
						<div class="mandatory">
							<p>

								<img src="${post.getPOST_FILE_2()}"
									alt="${post.getPOST_FILE_2()}" style="width: 100%;"
									id="description2" /> <br /> <input type="hidden"
									role="uploadcare-uploader2" name="POST_FILE_2"
									data-crop="disabled" data-images-only="true"
									value="${post.getPOST_FILE_2()}" />
							</p>
						</div>

						<!-- 사진이 하나도 없으면 -->
						<c:if
							test="${post.getPOST_FILE()==null && post.getPOST_FILE_2()==null}">
							<hr />
							<img src="${post.getPOST_FILE()}" style="width: 100%;"
								id="description" hidden="true" />
							<input type="hidden" role="uploadcare-uploader1" name="POST_FILE"
								data-crop="disabled" data-images-only="true" /> 선택사항입니다.
					</c:if>

						<hr />
					</div>

					<label for="POST_SUBJECT">제목</label> <input class="form-control"
						type="text" name="POST_SUBJECT" value="${post.getPOST_SUBJECT()}" /><br>
					<label for="POST_USER_ID">작성자</label> <input class="form-control"
						type="text" name="POST_USER_ID" value="${LoginId}"
						readonly="readonly" /><br>

					<textarea class="form-control" name="POST_TEXT" rows="5">${post.getPOST_TEXT()}</textarea>

					<c:if test="${post.getPOST_TYPE()==1}">
					<hr />
					<div class="mandatory">
						<label for="POST_MOVIE">선택된 영화는 수정할 수 없습니다.</label>
						<c:if test="<%=movie.getMOVIE_TITLE()!=null%>">
						<input class="form-control"	type="text" value="<%=movie.getMOVIE_TITLE()%>" readonly="readonly"/>
						</c:if>
						<c:if test="<%=movie.getMOVIE_TITLE()==null%>">
						<input class="form-control"	type="text" disabled/>
						</c:if>
						
						
						<input type="hidden" name="POST_MOVIE" id="movieSelect" value="${post.getPOST_MOVIE_ID()}" readonly="readonly"  />
					<hr />		
						<!--  Google Map -->
						<div id="map" style="height: 400px; width: 100%"></div>
						<hr />
						<div id="floating-panel" class="form-inline">
							<div class="input-group">
								<label for="POST_NATION" class="input-group-addon">국가</label>
								<input class="form-control" type="text" id="POST_NATION" name="POST_NATION" readonly="readonly" value="${post.getPOST_NATION()}"/>
							</div>
							&nbsp;&nbsp;&nbsp;
							<div class="form-group">
								<input id="address" type="text" class="form-control" id="selectedLocation_tag">
								<input id="geocode" type="button" value="위치검색" class="btn">&nbsp;&nbsp;&nbsp;
								<span id="selectedLocation"></span>
								<input class="form-control" type="hidden" id="GPS_LAT" name="GPS_LAT" value="${post.getPOST_GPS_LAT()}"/><input class="form-control" type="hidden" id="GPS_LNG" name="GPS_LNG" value="${post.getPOST_GPS_LNG()}" />
							</div>
						</div>
						<!--  구글맵 끝 -->			
<%-- 						<label for="POST_NATION">국가입력(지도api)</label> <input class="form-control"
							type="text" name="POST_NATION" value="${post.getPOST_NATION()}" />
						<label>위도경도입력(지도api)</label> <input
							class="form-control" type="text" name="GPS_LAT"
							value="${post.getPOST_GPS_LAT()}"/><input
							class="form-control" type="text" name="GPS_LNG"
							value="${post.getPOST_GPS_LNG()}"/>
							--%>
						<hr /> 
					</div>
					</c:if>
					<input type="hidden" name="POST_DATE"
						value="${post.getPOST_DATE()}" /> <input type="hidden"
						name="POST_READCOUNT" value="${post.getPOST_READCOUNT()}" /> <input
						type="hidden" name="POST_LIKECOUNT"
						value="${post.getPOST_LIKECOUNT()}" />

					<div align="center">
						<button class="btn" type="submit">수정하기</button>

					</div>
				</form>

			</div>
		</div>
		<div align="center">
			<a href="postListAction.po?type=${post.getPOST_TYPE()}">게시판으로
				돌아가기</a>
			<hr />
		</div>
	</div>

	<hr />
	<%@ include file="/footer.jsp"%>
<!-- Google -->
 
<script>
      var geoInfoWindow;
      var pos;
      var marker;
      var infowondow;
      
      var lat = ${post.getPOST_GPS_LAT()};
      var lng = ${post.getPOST_GPS_LNG()};  
      
      //지도 초기화
      function initMap() {
    	  
    	  var latlng = new google.maps.LatLng(lat,lng);

        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 10,
          center: latlng
        });

        var geocoder = new google.maps.Geocoder();

        marker = new google.maps.Marker({
          position:map.getCenter(),
          map:map,
          draggable:true
        })
        
        google.maps.event.addListener(marker, 'dragend', function(e){
          var latlng = e.latLng;
          infowindow.open(map, marker);

          geocodeLatLng(geocoder, map, infowindow, latlng);
          

          var markerlat = marker.getPosition().lat().toString();
          var markerlng = marker.getPosition().lng().toString();
          document.getElementById('GPS_LAT').value = markerlat;
          document.getElementById('GPS_LNG').value = markerlng;
        });

        infowindow = new google.maps.InfoWindow({
          content:""
        })
        
        //geocoding
        var geocoder = new google.maps.Geocoder();

        document.getElementById('geocode').addEventListener('click', function() {
          geocodeAddress(geocoder, map);
        });

        geocodeLatLng(geocoder, map, infowindow, latlng);
        
        //지도에 마커 등록
        // 지도에 클릭 이벤트를 등록합니다
        // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
        google.maps.event.addListener(map, 'click', function(mouseEvent) {
          // 클릭한 위도, 경도 정보를 가져옵니다
          var latlng = mouseEvent.latLng;

          //reverse geocoding
          geocodeLatLng(geocoder, map, infowindow, latlng);

          document.getElementById('GPS_LAT').value = latlng.lat();
          document.getElementById('GPS_LNG').value = latlng.lng();
        });


        //geolocation
        geoInfoWindow = new google.maps.InfoWindow({
          map: map
        });

      } //end initMap

      function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('address').value;
        geocoder.geocode({
          'address': address
        }, function(results, status) {
          if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            resultsMap.setZoom(15);
            infowindow.setContent(results[0].formatted_address);
            marker.setPosition(results[0].geometry.location);
            var latlng = results[0].geometry.location
            var lat = latlng.lat().toString();
            var lng = latlng.lng().toString();
            for (var i = 0; i < results[0].address_components.length; i++) {
              if (results[0].address_components[i].types[0] == "country") {
                var nationName = "" + results[0].address_components[i].long_name;
              }
            }
            document.getElementById('GPS_LAT').value = lat;
            document.getElementById('GPS_LNG').value = lng;
            document.getElementById('POST_NATION').value = nationName;
            infowindow.open(resultsMap, marker);
          } else {
            alert('Geocode was not successful for the following reason: ' + status);
          }
        });
      }

      //reverse geocode
      function geocodeLatLng(geocoder, map, infowindow, latlng) {

        geocoder.geocode({
          'location': latlng
        }, function(results, status) {
          if (status === 'OK') {
            if (results[1]) {
              marker.setPosition(latlng);
              infowindow.setContent(results[1].formatted_address);
              infowindow.open(map, marker);

              //국가 찾기
              for (var i = 0; i < results[1].address_components.length; i++) {
                if (results[1].address_components[i].types[0] == "country") {
                  var nationCode = "" + results[1].address_components[i].long_name;
                }
              }
              document.getElementById('POST_NATION').value = nationCode;

            } else {
              window.alert('No results found');
            }
          } else {
            window.alert('Geocoder failed due to: ' + status);
          }
        });
      }

    </script>

    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBnBNqtyE8plQKCxFw7kdlpiFpYQw2Jqo&callback=initMap"></script>
    <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
</body>
</html>