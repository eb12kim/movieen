<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE>
<html>
<head>
<style>
#map {
	height: 400px;
	width: 100%;
}
</style>
</head>
<body>
  <div id="map"></div>
  <button onclick="getInfo()">위치정보 보기</button>
  <p id="infoDiv"></p>
  <button onclick="goMyLocation()">내 위치로 이동</button>
  <div id="floating-panel">
  <input id="address" type="textbox" value="신촌역">
  <input id="geocode" type="button" value="위치검색">
</div>
  <p id="clickLatlng"></p>
  <form action="#">
  	위도:<input type="text" id="lat" value=""/>
  	경도:<input type="text" id="lng" value=""/><br>
  	내용:<input type="text" id="txt" /><br>
  	<input type="submit" value="등록"/>
  </form>
  
  
  
  <script>
    var map;
    var geoInfoWindow;
    var pos;
    var markers = [];


    //지도 초기화
    function initMap() {

      map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        center: {
          lat: 37.555148,
          lng: 126.936887
        }
      });

      //DB정보 예시
      var locations = [{
          latlng : new google.maps.LatLng(37.556795, 126.938952),
          content: '포스트제목1'
        },
        {
          latlng : new google.maps.LatLng(37.556531, 126.937740),
          content:'포스트제목2'
        },
        {
          latlng : new google.maps.LatLng(37.556352, 126.938781),
          content: '포스트제목3'
        }
      ];
      var postInfos = [{
          postinfo: '포스트제목1 <br> <a href="post주소">post1로 연결</a> <br>post_date: 1234'
        },
        {
          postinfo: '포스트제목2 <br> <a href="post주소">post2로 연결</a> <br>post_date: 1234'
        },
        {
          postinfo: '포스트제목3 <br> <a href="post주소">post3로 연결</a> <br>post_date: 1234'
        }
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
          map.setCenter(marker.getPosition());
          alert('post정보');
        }
      }

      //마커클러스터링
      var markerCluster = new MarkerClusterer(map, markers, {
        imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
      });

      //지도에 마커 등록
      // 지도에 클릭 이벤트를 등록합니다
      // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
      google.maps.event.addListener(map, 'rightclick', function(mouseEvent) {
    	  
    	  var register = confirm('위치를 등록하시겠습니까?');
		if(register){	
          // 클릭한 위도, 경도 정보를 가져옵니다
          var latlng = mouseEvent.latLng;
          // 마커 위치를 클릭한 위치로 옮깁니다
          var marker = new google.maps.Marker({
            position:latlng,
            map:map
          });

			
          document.getElementById('lat').value = latlng.lat();
          document.getElementById('lng').value = latlng.lng();
          
		}

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
          geoInfoWindow.setContent('나의 현재 위치');
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
    
    //geocoding
    var geocoder = new google.maps.Geocoder();
    
    document.getElementById('geocode').addEventListener('click', function() {
        geocodeAddress(geocoder, map);
      });

  } //end initMap
	
  function geocodeAddress(geocoder, resultsMap) {
	  var address = document.getElementById('address').value;
	  geocoder.geocode({'address': address}, function(results, status) {
	    if (status === 'OK') {
	      resultsMap.setCenter(results[0].geometry.location);
	      resultsMap.setZoom(15);
	      var infowindow = new google.maps.InfoWindow({
	    	  content:address,
	      })
	      var marker = new google.maps.Marker({
	        map: resultsMap,
	        position: results[0].geometry.location
	      });
	      infowindow.open(resultsMap, marker);
	    } else {
	      alert('Geocode was not successful for the following reason: ' + status);
	    }
	  });
	}

    function getInfo() {
      var center = map.getCenter();
      var zoom = map.getZoom();
      var MapTypeId = map.getMapTypeId();
      var message = '지도 중심좌표의 <br>위도 = ' + center.lat() + ',<br>';
      message += '경도=' + center.lng() + '<br>';
      message += '줌은 ' + zoom + '<br>';
      message += '지도타입은 ' + MapTypeId;

      var infoDiv = document.getElementById('infoDiv');
      infoDiv.innerHTML = message;
    }


    function goMyLocation() {
      map.setCenter(pos);
    }



  </script>

  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBnBNqtyE8plQKCxFw7kdlpiFpYQw2Jqo&callback=initMap"></script>
  <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>

</body>

</html>