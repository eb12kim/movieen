<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.movie.db.MovieBean"%>
<%@page import="net.movie.db.MovieDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="LoginId" value="${sessionScope.LoginId}" />

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
<!--  UploadCare -->
<script>
	UPLOADCARE_LOCALE = "ko";
	UPLOADCARE_TABS = "file url facebook instagram";
	UPLOADCARE_PUBLIC_KEY = "62a4d46e5b17aee8d761";
</script>
<script charset="utf-8"	src="//ucarecdn.com/libs/widget/3.1.1/uploadcare.full.min.js"></script>

<!-- 게시판에 따른 영역 show&hide / required 처리 -->
<script>
	$(document).ready(function() {
				var index = document.getElementById('selectType');
				index.selectedIndex = ${param.type}	-1;
				if (index.selectedIndex === 0) {
					$('.mandatory').prop("hidden", false);
				} else {
					$('#mandatory_pic').prop("required", false);
					$('.mandatory').prop("hidden", true);
					$('#mapReq').prop("required", false);
					$('#movieSelect').prop("required", false);
					$('#movieSelect1').prop("required", false);
				};

				$('#selectType').change(function() {
					if ($(this).val() == "1") {
						$('#mandatory_pic').prop("required", true);
						$('.mandatory').prop("hidden", false);
						;

					} else {
						$('#mandatory_pic').prop("required", false);
						$('.mandatory').prop("hidden", true);
					}
					;
				});

				var singleWidget1 = uploadcare
						.SingleWidget('[role=uploadcare-uploader1]');
				singleWidget1.onUploadComplete(function(info) {
					$("#description").attr("src", info.cdnUrl);
				});
				var singleWidget2 = uploadcare
						.SingleWidget('[role=uploadcare-uploader2]');
				singleWidget2.onUploadComplete(function(info) {
					$("#description2").attr("src", info.cdnUrl);
				});

				$('#button').click(	function() {
							if ($('#selectType').val() === "1"
									&& $('#mandatory_pic').val() === "") {
								alert("이미지를 등록해주세요.");
								return false;

							}
							;
						});
		});
	
	function selectmovie(){
		window.open("movieList.mo?write=true","width=500 height=500");
	}
		
</script>
<title>글 작성</title>

</head>
<body>
	<%@ include file="/gnb.jsp"%>
 <header class="w3-container w3-center w3-padding-40 w3-white">
    <h1 class="w3-xxxlarge">
    	<b>
			글 작성하기
     	</b>
    </h1>
    <h6>Welcome to <span class="w3-tag">Movi'een</span></h6>
  </header>	
	<div class="container" style="padding-top:5px;">
	
<!-- 	<div class="container col-sm-6 md-6" style="padding-top:5px;"> -->
 	<div class="w3-card-4" style="margin-bottom:50px;">
			<div class="panel-body">
				<form action="./postWriteAction.po" method="post" name="writeform" class="form-horizontal" >
						<label for="POST_TYPE">게시판 선택</label>
						<select class="form-control" name="POST_TYPE" id="selectType" required>
							<option value="1">메인게시판</option>
							<option value="2">자유게시판</option>
							<option value="3">QnA</option>
						</select>

					<hr />
					<input type="hidden" role="uploadcare-uploader1" name="POST_FILE" data-images-only="true" id="mandatory_pic"/>
					<label for="POST_FILE" class="mandatory"> 여행지 사진</label>
					<div>
						<img src="" id="description" class="img img-responsive" style="align:center;" />
					</div>
					
					<div class="mandatory">
					<hr />
					<input type="hidden" role="uploadcare-uploader2" name="POST_FILE_2" data-images-only="true"/>
					<label for="POST_FILE_2">영화 장면 캡처</label>
					<div>
						<img src="" id="description2" class="img img-responsive" style="align:center;"/>
					</div>
					</div>

					<hr />

					<label for="POST_SUBJECT">제목</label>
					<input class="form-control" type="text" name="POST_SUBJECT"	required /><br>
					
					<label for="POST_USER_ID">작성자</label>
					<input class="form-control" type="text" value="${LoginId}" readonly="readonly" disabled /><br />
					<input class="form-control" type="hidden" name="POST_USER_ID" value="${LoginId}" /><br />
	

					<textarea class="form-control" name="POST_TEXT" rows="3" required></textarea>
					<hr />
					
					<div class="mandatory" >
				
						<button class="form-control btn btn-success" onclick="javascript:selectmovie();">영화선택</button>
						
						<input class="form-control" type="text" id="movieSelect1" name="POST_MOVIE_TITLE" required/>
						<input class="form-control" type="hidden" id="movieSelect" name="POST_MOVIE" required/>
					
						<!--  Google Map -->
						<div id="map" style="height: 400px; width: 100%"></div>
						<hr />
						<div id="floating-panel" class="form-inline">
							<div class="input-group">
								<label for="POST_NATION" class="input-group-addon">국가</label> <input
									class="form-control" type="text" id="POST_NATION"
									name="POST_NATION" readonly="readonly" />
							</div>
							&nbsp;&nbsp;&nbsp;
							<div class="form-group">
								<input id="address" type="text" class="form-control" id="mapReq">
								<input id="geocode" type="button" value="위치검색" class="btn">&nbsp;&nbsp;&nbsp;
								<span id="selectedLocation"></span>
								<!-- <button onclick="goMyLocation()" class="form-control btn-info" align="right">내 위치로 이동</button> -->
								<input class="form-control" type="hidden" id="GPS_LAT"
									name="GPS_LAT" /><input class="form-control" type="hidden"
									id="GPS_LNG" name="GPS_LNG" />
							</div>
						</div>
						<!--  구글맵 끝 -->				

					</div>
					
					
					
					<hr />
					<input class="form-control btn btn-primary" type="submit" value="등록"  id="button" />
					
				</form>
				</div>
			</div>
					<div align="center">
			<a href="postListAction.po?type=${post.getPOST_TYPE()}">게시판으로 돌아가기</a>
			<hr />
			</div>	
		</div>

	<%@ include file="/footer.jsp"%>

<!-- Google -->
    <script>
      var map;
      var geoInfoWindow;
      var pos;
      var marker;
      var infowondow;

      

      //지도 초기화
      function initMap() {

        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 10,
          center: {
            lat: 37.555148,
            lng: 126.936887
          }
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
      
       /*  document.getElementById('geocode').addEventListener('keypress', function (e) {
            var key = e.which || e.keyCode;
            if (key === 13) { // 13 is enter
            	  geocodeAddress(geocoder, map);
            }
        }); */

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

        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };

            geoInfoWindow.setPosition(pos);
            geoInfoWindow.setContent('it\'s me :)');
            map.setCenter(pos);
          }, function() {
            handleLocationError(true, geoInfoWindow, map.getCenter());
          });
        } else {
          handleLocationError(false, geoInfoWindow, map.getCenter());
        }


        function handleLocationError(browserHasGeolocation, infoWindow, pos) {
          infoWindow.setPosition(pos);
          infowindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
        }

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
              var x= results[1].formatted_address;
              document.getElementById('selectedLocation').innerHTML = x;
             
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

      function goMyLocation() {
        map.setCenter(pos);
        geoInfoWindow.open(map);
      }
    </script>

    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBnBNqtyE8plQKCxFw7kdlpiFpYQw2Jqo&callback=initMap"></script>
    <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>	
</body>
</html>