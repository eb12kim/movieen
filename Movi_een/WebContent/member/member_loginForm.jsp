<%@ page contentType="text/html; charset=UTF-8"%>

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

<title>로그인</title>
</head>
<body>
	<%@ include file="/gnb.jsp"%>
	 <header class="w3-container w3-center w3-padding-40 w3-white">
    <h1 class="w3-xxxlarge">
    	<b>
			로그인  
     	</b>
    </h1>
    <h6>로그인하고, 자신만의 <span class="w3-tag">Movi'een</span> 을 공유하세요!</h6>
  </header>			
	<div class="container">
		<div class="w3-card-4" style="padding-top:5px;">

			<div class="panel-body">
				<div class="form-box">

						<form role="form" action="memberLoginAction.me" method="post" class="form-horizontal">
							<div class="form-group">
								<label for="MEMBER_ID" class="col-sm-3 control-label">아이디</label>
								<div class="col-sm-9">
								<input
									type="text" name="MEMBER_ID" placeholder="아이디를 입력하세요."
									class="form-username form-control" id="form-username">
								</div>
							</div>
			
							<div class="form-group">
								<label for="MEMBER_PW" class="col-sm-3 control-label">비밀번호</label>
								<div class="col-sm-9">
								<input
									type="password" name="MEMBER_PW" placeholder="비밀번호를 입력하세요."
									class="form-password form-control" id="form-password">
								</div>
							</div>
							<div align="center">
								<button type="submit" class="btn">로그인</button> &nbsp;&nbsp;
								<a href="memberJoin.me"><i class="fa fa-user-plus">회원가입</i></a>
							</div>
	
	
						</form>
	
				</div>
			</div>
		</div>
	</div>



	<%@ include file="/footer.jsp"%>
</body>
</html>