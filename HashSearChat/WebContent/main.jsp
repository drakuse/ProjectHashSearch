<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=devicd-width" initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>메인</title>
</head>
<body>
	<%
		String id = null;
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
		}
	%>

	<nav class="navbar navbar-default">
		<!-- 메인페이지로이동하는 이미지  -->
		<div class="navbar-header">
			<a class="navbar-brand" href="main.jsp"><img
				src="images/shap.png" title="#" /></a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<%
				if (id == null) {
			%>
			<!-- 세션영역에 id가 저장 안되었을때  -->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="loginForm.me" style="font-size: 8pt;">로그인</a></li>
				<li><a href="joinForm.me" style="font-size: 8pt;">회원가입</a></li>
			</ul>
			<%
				} else {
			%>
			<%
				if (id.equals("ADMIN")) {
			%>
			<!-- 세션영역에 id가 ADMIN 일때  -->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="memberListAction.me?search="
					style="font-size: 8pt;">관리자모드</a></li>
				<li><a href="memberLogout.me" style="font-size: 8pt;">로그아웃</a></li>
			</ul>
			<%
				} else {
			%>
			<!-- 세션영역에 id가 있을때 -->
			<ul class="nav navbar-nav navbar-right">

				<li><a href="memberInfo.me" style="font-size: 8pt;">내정보</a></li>
				<li><a href="memberLogout.me" style="font-size: 8pt;">로그아웃</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a>👤 <%=id%> 님
				</a></li>
			</ul>
			<%
				}
				}
			%>

		</div>
	</nav>

	<div class="container">
		<!-- 채팅방 페이지로 이동  -->
		<div class="jumbotron h1">
			<p style="font-size: 35px; margin-top: 10px">
				채팅방<a href="chatListAction.ch?search="><button type="button"
						class="btn btn-warning btn-lg pull-right">go</button></a>
			</p>
		</div>
		<!-- 게시판 페이지로 이동  -->
		<div class="jumbotron h1">
			<p style="font-size: 35px; margin-top: 10px">
				게시판<a href="search.fe?search=&sort=time"><button type="button"
						class="btn btn-success btn-lg pull-right">go</button></a>
			</p>
		</div>
	</div>



	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>