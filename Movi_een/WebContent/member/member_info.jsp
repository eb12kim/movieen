<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="member" value="${requestScope.member}" />

<!DOCTYPE html>
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

<script>
	$(document).ready(function() {
		$("#btn_del").click(function() {
			var x = confirm("정말 탈퇴하시겠습니까? 작성하신 게시글은 삭제되지 않습니다.");
			if (x == true) {
				window.location = "memberDelete.me?id=${member.getMEMBER_ID()}"
			} else {
				return false;
			}

		});
	});
</script>

<title>회원 정보</title>
</head>
<body>
	<%@ include file="/gnb.jsp"%>
	 <header class="w3-container w3-center w3-padding-40 w3-white">
    <h1 class="w3-xxxlarge">
    	<b>
			회원정보  
     	</b>
    </h1>
    <h6>${member.getMEMBER_NAME()}님의 개인정보입니다.</h6>
  </header>		
	<div class="container" style="padding-top:20px;">
			<div class="w3-card-4">

				<div class="panel-body">
					<table class="table">

						<tbody>
							<tr>
								<th scope="row">아이디</th>
								<td>${member.getMEMBER_ID()}</td>
							</tr>
							<tr>
								<th scope="row">이름</th>
								<td>${member.getMEMBER_NAME()}</td>
							</tr>
							<tr>
								<th scope="row">전화번호</th>
								<td>${member.getMEMBER_PHONE()}</td>
							</tr>
							<tr>
								<th scope="row">출생년도</th>
								<td>${member.getMEMBER_AGE()}</td>
							</tr>
							<tr>
								<th scope="row">성별</th>
								<td><c:if test="${member.getMEMBER_GENDER()==1}">남</c:if> 
									<c:if test="${member.getMEMBER_GENDER()==2}">여</c:if></td>
							</tr>
							<tr>
								<th scope="row">국적</th>
								<td>${member.getMEMBER_NATIONALITY()}</td>
							</tr>
							<tr>
								<th scope="row">선호영화장르</th>
								<td><c:if test="${member.getMEMBER_MOVIE_LIKE()!=null}">${member.printMEMBER_MOVIE_LIKE()}</c:if></td>
							</tr>
							
							<tr>
								<th scope="row">여행희망지역</th>
								<td><c:if test="${member.getMEMBER_TRIP_LIKE()!=null}">${member.printMEMBER_TRIP_LIKE()}</c:if></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<button type="button" class="btn"
										onclick="location.href='memberUpdate.me?id=${member.getMEMBER_ID()}'">정보수정</button>
									<a href="memberDelete.me?id=${member.getMEMBER_ID()}"
									class="btn btn-secondary" id="btn_del">회원탈퇴</a>
								</td>



							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>


	<%@ include file="/footer.jsp"%>
</body>
</html>