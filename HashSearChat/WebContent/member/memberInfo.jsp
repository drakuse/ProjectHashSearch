<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=devicd-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>회원정보</title>
</head>
<script>
function passCheck() {
	  var N = prompt("패스워드를 입력하세요","")
	}
</script>
<body>
	<%
		Member member = (Member) request.getAttribute("memberInfo");
	%>

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="main.jsp" style="font-size: 20pt"><img src="images/shap.png" title="#" /></a>
		</div>		
	</nav>

	<div class="container">
		<div class="row">
		<form method="post" action="./memberManager.me">
			<table class="table table-striped">
				<thead>
					<tr>
						<th colspan="2"
							style="background-color: #eeeeee; text-align: center;">회원정보
						</th>
					</tr>
				</thead>
				<tbody style="text-align: center; font-size: 12pt;">
					<tr>
						<td>아이디&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;<%=member.getMEMBER_ID()%></td>
					</tr>
					<tr>
						<td>이름&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;<%=member.getMEMBER_NAME()%></td>
					</tr>
					<tr>
						<td>신고횟수&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;<%=member.getMEMBER_REFUSE()%></td>
					</tr>
					
					<tr>
						<td>비밀번호&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;<input type="password" name="password"></td>
					</tr>
					<tr>
						<td>새 비밀번호&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;<input type="password" name="new_password"></td>
					</tr>
					<tr style="border-bottom: 1px solid white">
						<td><input type="submit" name="select" value="정보수정">
						<input type="submit" name="select" value="회원탈퇴"></td>
					</tr>					
				</tbody>
			</table>
			</form>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>