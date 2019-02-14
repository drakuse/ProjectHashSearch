<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=devicd-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>채팅방개설</title>
</head>
<%
	String id = null;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	}
%>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="main.jsp" style="font-size: 20pt"><img
				src="images/shap.png" title="#" /></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<%
				if (id.equals("ADMIN")) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="memberListAction.me" style="font-size: 8pt;">관리자모드</a></li>
				<li><a href="memberLogout.me" style="font-size: 8pt;">로그아웃</a></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="memberInfoAction.me" style="font-size: 8pt;">내정보</a></li>
				<li><a href="memberLogout.me" style="font-size: 8pt;">로그아웃</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a>👤 <%=id%> 님
				</a></li>
			</ul>
			<%
				}
			%>

		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="chatWriteAction.ch">
					<h3 style="text-align: center;">채팅방개설</h3>
					<br> <input type="hidden" name="CHAT_ID" value="<%=id%>" />
					<div class="form-group">
						<label for="exampleFormControlSelect1">방 제목</label> <input
							type="text" class="form-control" placeholder="채팅방제목"
							name="CHAT_SUBJECT" maxlength="20">
					</div>
					<div class="form-group">
						<label for="exampleFormControlSelect1">CATEGORY</label> <select
							name="CHAT_CATEGORY" class="form-control"
							id="exampleFormControlSelect1">
							<option value=1>유머</option>
							<option value=2>스포츠</option>
							<option value=3>게임</option>
							<option value=4>영화</option>
							<option value=5>자유</option>
						</select>
					</div>
					<br> <input type="submit" class="btn btn-warning form-control"
						value="채팅방개설">
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>