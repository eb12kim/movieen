<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="id" value="${sessionScope.LoginId}" />

	

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-card-2" id="myNavbar">
    <a href="./main.jsp" class="w3-bar-item w3-button w3-wide">Movi'een</a>



    <!-- Right-sided navbar links -->
    <div class="w3-right w3-hide-small">
     <div class="w3-dropdown-hover w3-white">
    <button class="w3-button w3-white">by Movi'een</button> 
        <div class="w3-dropdown-content w3-bar-block w3-card-4">
	  <a href="postListByMovieAction.po" class="w3-bar-item w3-button">by Movie</a>
	  <a href="postListByNationAction.po" class="w3-bar-item w3-button">by Country</a>
	  <a href="main.jsp#googlemap" class="w3-bar-item w3-button">by Map</a>
	     </div>
	      </div> 
      <a href="postListAction.po?type=1" class="w3-bar-item w3-button">전체글보기</a>
      <a href="postListAction.po?type=2" class="w3-bar-item w3-button">Community</a>
      <c:choose>
        <c:when test="${id==null}"> 	
      		<a href="memberJoin.me" class="w3-bar-item w3-button"><i class="fa fa-user-plus"></i> Sign-Up</a>
      		<a href="memberLogin.me" class="w3-bar-item w3-button"><i class="fa fa-sign-in"></i> Login</a>
		</c:when>
		<c:when test="${id.equals('admin')}">
			<a href="movieList.mo" class="w3-bar-item w3-button"><i class="fa fa-film"></i> MovieList</a>
			<a href="memberList.me" class="w3-bar-item w3-button"><i class="fa fa-th"></i> MemberList</a>
			<a href="memberMyPageAction.me" class="w3-bar-item w3-button"><i class="fa fa-user"></i> My Page</a>
			<a href="memberLogout.me" class="w3-bar-item w3-button"><i class="fa fa-sign-out"></i> Logout</a>
		</c:when>
		<c:otherwise>
			<a href="memberMyPageAction.me" class="w3-bar-item w3-button"><i class="fa fa-user"></i> My Page</a>
			<a href="memberLogout.me" class="w3-bar-item w3-button"><i class="fa fa-sign-out"></i> Logout</a>
		    <a href="#" class="w3-bar-item w3-button w3-wide">${id}님, 환영합니다.</a>			
		</c:otherwise>
	</c:choose>
    </div>
  
    <!-- Hide right-floated links on small screens and replace them with a menu icon -->
    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open('main')">
      <i class="fa fa-bars"></i>
    </a>
  </div>
</div>

<!-- Sidebar on small screens when clicking the menu icon -->
<nav class="w3-sidebar w3-bar-block w3-black w3-card-2 w3-animate-left w3-hide-medium w3-hide-large" style="display:none" id="main">
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
  <a href="postListByMovieAction.po" onclick="w3_close()" class="w3-bar-item w3-button">by Movie</a>
  <a href="postListByNationAction.po" onclick="w3_close()" class="w3-bar-item w3-button">by Country</a>
  <a href="postByMapAction.po" onclick="w3_close()" class="w3-bar-item w3-button">by Map</a>
  <a href="postListAction.po?type=1" onclick="w3_close()" class="w3-bar-item w3-button">전체글보기</a>
  <a href="postListAction.po?type=2" onclick="w3_close()" class="w3-bar-item w3-button">Community</a>
 
    <c:choose>
		<c:when test="${id==null}"> 	
      		
      		<a href="memberJoin.me" onclick="w3_close()" class="w3-bar-item w3-button"><i class="fa fa-user-plus"></i> Sign-Up</a>
      		<a href="memberLogin.me" onclick="w3_close()" class="w3-bar-item w3-button"><i class="fa fa-sign-in"></i> Login</a>
		</c:when>
		<c:when test="${id.equals('admin')}">
			<a href="movieList.mo" onclick="w3_close()" class="w3-bar-item w3-button"><i class="fa fa-film"></i> MovieList</a>
			<a href="memberList.me" onclick="w3_close()" class="w3-bar-item w3-button"><i class="fa fa-th"></i> MemberList</a>
			<a href="memberMyPageAction.me" onclick="w3_close()" class="w3-bar-item w3-button"><i class="fa fa-user"></i> My Page</a>
			<a href="memberLogout.me" onclick="w3_close()" class="w3-bar-item w3-button"><i class="fa fa-sign-out"></i> Logout</a>
		</c:when>
		<c:otherwise>
			<a href="memberMyPageAction.me"  onclick="w3_close()" class="w3-bar-item w3-button"><i class="fa fa-user"></i> My Page</a>
			<a href="memberLogout.me" onclick="w3_close()" class="w3-bar-item w3-button"><i class="fa fa-user-plus"></i> Logout</a>
		    <a href="#" class="w3-bar-item w3-button w3-wide">${id}님, 환영합니다.</a>			
		</c:otherwise>
	</c:choose>
</nav>

<script>


function w3_open(id) {
/* 	var mySidebar = document.getElementById("mySidebar_main"); */
	var x = document.getElementById(id);
	
    if (x.style.display === 'block') {
        x.style.display = 'none';
    } else {
        x.style.display = 'block';
    }
}

// Close the sidebar with the close button
function w3_close(id) {
	var x = document.getElementById(id);
	x.style.display = "none";
}
</script>