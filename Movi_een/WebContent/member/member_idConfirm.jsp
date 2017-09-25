<%@page import="net.member.db.MemberDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id=request.getParameter("id");
	MemberDAO member=new MemberDAO();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="../jquery/3.2.1/jquery-3.2.1.min.js"></script>
<script src="../bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>아이디중복체크</title>
<script>
	function checkIdClose(id){
		opener.memberjoin.checkid.value=1;
		opener.memberjoin.MEMBER_ID.value=id;
		window.close();
		opener.memberjoin.MEMBER_PW.focus();
	}
</script>
</head>
<body>
<div class="container">
<h3>아이디 중복체크</h3>
<hr />
<form class="form-inline" action="member_idConfirm.jsp">
	<%
		if(member.isId(id)==1){
	%>
		
		<h4><strong>입력하신 아이디 <%=id%> 는 사용이 불가능합니다.</strong></h4>
		<div class="form-group">
		<label for="id">아이디</label>
		<input class="form-control" type="text" name="id" id="id"/>
		</div>
		<input class="form-control" type="submit" value="중복체크" />
	<%		
		} else {
	%>
		
		<h4><strong>입력하신 아이디 <%=id%> 는 사용이 가능합니다.</strong></h4>
		<div class="form-group">
		<a href="member_idConfirm.jsp">다른아이디 고르기</a>
		</div>
		<input class="form-control" type="button" value="현재 아이디 선택" onClick="javascript:checkIdClose('<%=id%>')" />		
	<%
		}
	%>

</form>
</div>
</body>
</html>


