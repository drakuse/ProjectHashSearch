<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=devicd-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>회원가입</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="main.jsp"><img src="images/shap.png" title="#" /></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav navbar-right">
				<li><a href="loginForm.me" style="font-size: 8pt;">로그인</a></li>				
			</ul>

		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="./memberJoinAction.me">
					<h3 style="text-align: center;">회원가입 화면</h3>
					<br>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디"
							name="MEMBER_ID" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							name="MEMBER_PASSWORD" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름"
							name="MEMBER_NAME" maxlength="20">
					</div>
					<input type="submit" class="btn btn-success form-control"
						value="회원가입">

				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>