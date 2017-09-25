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

<!--  Customized CSS&JSfor movieen -->
<link href="css/common.css" rel="stylesheet">
<script src=js/js_like.js></script>
<script>

    var movie_like = ["${member.getMEMBER_MOVIE_LIKE()[0]}", "${member.getMEMBER_MOVIE_LIKE()[1]}", "${member.getMEMBER_MOVIE_LIKE()[2]}"];
    
    $(document).ready(function() {
        $('input:checkbox[name="MEMBER_MOVIE_LIKE"]').each(function() {
            for(var i=0; i<movie_like.length; i++) {
                if(this.value === movie_like[i]){ //값 비교
                       this.checked = true; //checked 처리
                     }
            }
        });
    });
     
    var trip_like = ["${member.getMEMBER_TRIP_LIKE()[0]}", "${member.getMEMBER_TRIP_LIKE()[1]}", "${member.getMEMBER_TRIP_LIKE()[2]}"];
    
    $(document).ready(function() {
        $('input:checkbox[name="MEMBER_TRIP_LIKE"]').each(function() {
            for(var i=0; i<trip_like.length; i++) {
                if(this.value === trip_like[i]){ //값 비교
                       this.checked = true; //checked 처리
                     }
            }
        });
    });

	function aa(){ 
		   var getPwd = document.getElementById("pwd").value; 
		   var getPwdCh = document.getElementById("pwdCh").value; 
		   console.log("getPwd : " + getPwd); 
		   console.log("getPwdCh : " + getPwdCh); 
		   if(getPwd!=getPwdCh){ 
		      document.getElementById("txt").innerHTML = "<font color='Red'>비밀번호가 일치하지 않습니다</font>"; 

		      }else{ 
		      document.getElementById("txt").innerHTML = "비밀번호가 일치합니다"; 
		   } 
		}
	function chkForm(){

		var getPwd = document.getElementById("pwd").value; 
		var getPwdCh = document.getElementById("pwdCh").value;

		if(getPwd!=getPwdCh){
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
		 return false;
	   }
		alert("회원정보수정이 완료되었습니다.");
		return true;
		
		}
</script>

<title>회원 정보 수정</title>
</head>
<body>
	<%@ include file="/gnb.jsp"%>
	 <header class="w3-container w3-center w3-padding-40 w3-white">
    <h1 class="w3-xxxlarge">
    	<b>
			회원정보 수정 
     	</b>
    </h1>
    <h6> ${member.getMEMBER_NAME()} 님의 정보를 수정합니다.</h6>
  </header>	
  	<div class="container">
		<div class="w3-card-4" style="padding-top:5px;">

			<div class="panel-body">
				<form action="./memberUpdateAction.me" method="post" class="form-horizontal" onSubmit="return chkForm();">
					<div class="form-group">
						<label for="id" class="col-sm-3 control-label">아이디</label>
						<div class="col-sm-9">
							<input type="text" name="id" class="form-control"
								value="${member.getMEMBER_ID()}" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label for="MEMBER_PW" class="col-sm-3 control-label">비밀번호</label>
						<div class="col-sm-9">
							<input type="password" name="MEMBER_PW" class="form-control"
								 required="required"  id="pwd"/>
						</div>
					</div>
					<div class="form-group">
						<label for="MEMBER_PW" class="col-sm-3 control-label">비밀번호 확인</label>
						<div class="col-sm-9">
							<input type="password" name="MEMBER_PW" class="form-control" id="pwdCh" onkeyup="aa();"
								 required="required" />
							<span id="txt"></span> 
						</div>
					</div>
					<div class="form-group">
						<label for="MEMBER_NAME" class="col-sm-3 control-label">이름</label>
						<div class="col-sm-9">
							<input type="text" name="MEMBER_NAME" class="form-control"
								value="${member.getMEMBER_NAME()}"
								placeholder="${member.getMEMBER_NAME()}" required="required" />
						</div>
					</div>
					<div class="form-group">
						<label for="MEMBER_AGE" class="col-sm-3 control-label">생년월일</label>
						<div class="col-sm-9">
							<input type="text" name="" class="form-control"
								value="${member.getMEMBER_AGE()}" readonly="readonly" />
						</div>
					</div>	
					<hr />			
					<div class="form-group">
						<label for="MEMBER_PHONE" class="col-sm-3 control-label">전화번호</label>
						<div class="col-sm-9">
							<input type="text" name="MEMBER_PHONE" class="form-control"
								value="${member.getMEMBER_PHONE()}"
								placeholder="${member.getMEMBER_PHONE()}" />
						</div>
					</div>
					<div class="form-group">
						<label for="MEMBER_NATIONALITY" class="col-sm-3 control-label">변경 전 국적</label>
						<div class="col-sm-9">
								<input type="text" name="${member.getMEMBER_NATIONALITY()}" class="form-control"
								value="${member.getMEMBER_NATIONALITY()}" readonly="readonly" />
						</div>
					</div>			
					<div class="form-group">
						<label for="MEMBER_NATIONALITY" class="col-sm-3 control-label">변경 후 국적</label>
						<div class="col-sm-9">
							
							<select	class="form-control" name="MEMBER_NATIONALITY">
							<option selected="selected" hidden="">${member.getMEMBER_NATIONALITY()}</option>
								<optgroup label="아시아">
									<option value="한국">대한민국</option>
									<option value="대만">대만</option>
									<option value="베트남">베트남</option>
									<option value="일본">일본</option>
									<option value="중국">중국</option>
									<option value="태국">태국</option>
									<option value="홍콩">홍콩</option>
								</optgroup>

								<optgroup label="미주대륙">
									<option value="미국">미국</option>
									<option value="캐나다">캐나다</option>
								</optgroup>

								<optgroup label="유럽">
									<option value="독일">독일</option>
									<option value="스위스">스위스</option>
									<option value="스페인">스페인</option>
									<option value="영국">영국</option>
									<option value="오스트리아">오스트리아</option>
									<option value="이탈리아">이탈리아</option>
									<option value="크로아티아">크로아티아</option>
									<option value="프랑스">프랑스</option>
								</optgroup>
							</select>
						</div>
					</div>
							<div class="form-group">
			<label for="MEMBER_MOVIE_LIKE" class="col-sm-3 control-label">선호영화 장르(최대 3개)</label>
			<div class="col-sm-9">
 			<table>
          		<tbody>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="SF">SF</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="가족">가족</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="공연">공연</td>
						<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="공포(호러)">공포(호러)</td>
           			</tr>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="다큐멘터리">다큐멘터리</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="드라마">드라마</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="멜로/로맨스">멜로/로맨스</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="뮤지컬">뮤지컬</td>
          			</tr>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="미스터리">미스터리</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="범죄">범죄</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="사극">사극</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="애니메이션">애니메이션</td>
          			</tr>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="액션">액션</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="어드벤처">어드벤처</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="전쟁">전쟁</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="코미디">코미디</td>
          			</tr>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="판타지">판타지</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_MOVIE_LIKE" value="기타">기타</td>
          				<td></td>
          				<td></td>
          			</tr>
           		</tbody>
        	</table>			
		  </div>
        </div>
          	<div class="form-group">
			<label for="MEMBER_MOVIE_LIKE" class="col-sm-3 control-label">여행 희망 지역(최대 3개)</label>
			<div class="col-sm-9">
			<table>
				<thead>
					<tr><th>아시아</th></tr>
				</thead>
          		<tbody>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="대한민국">대한민국</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="대만">대만 </td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="베트남">베트남</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="일본">일본</td>
          			</tr>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="중국">중국</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="태국">태국</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="홍콩">홍콩</td>
          				<td></td>
          			</tr>
           		</tbody>
        	</table>
			<table>
				<thead>
					<tr><th>미주대륙</th></tr>
				</thead>
          		<tbody>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="미국">미국</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="캐나다">캐나다 </td>
           				<td></td>
          				<td></td>           				         				
          			</tr>
           		</tbody>
        	</table>
 			<table>
				<thead>
					<tr><th>유럽</th></tr>
				</thead>
          		<tbody>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="독일">독일</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="스위스">스위스</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="영국">영국</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="오스트리아">오스트리아</td>
          			</tr>
          			<tr>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="이탈리아">이탈리아</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="크로아티아">크로아티아</td>
          				<td><input class="single-checkbox" type="checkbox" name="MEMBER_TRIP_LIKE" value="프랑스">프랑스</td>
          				<td></td>
          			</tr>
           		</tbody>
        	</table>
		</div>
        </div>
					
					
					<div class="form-group">
						<div align="center">
							<button type="submit" class="btn">수정하기</button>
							<button type="reset" class="btn">다시작성</button>
							<br />
							<a href="memberView.me?id=${member.getMEMBER_ID()}" class="btn btn-secondary" id="btn_update">[회원 정보로 돌아가기]</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="/footer.jsp"%>
</body>
</html>