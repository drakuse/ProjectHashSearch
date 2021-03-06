<%@page import="vo.Chat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>#HASHSEARCHAT</title>
<link href="css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="images/fav-icon.png" />
<script type="application/x-javascript">
			
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<!----webfonts---->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<!----//webfonts---->
<!-- Global CSS for the page and tiles -->
<link rel="stylesheet" href="css/main.css">
<!-- //Global CSS for the page and tiles -->
<!---start-click-drop-down-menu----->
<script src="js/jquery.min.js"></script>
<!----start-dropdown--->
<script type="text/javascript">
	var $ = jQuery.noConflict();
	$(function() {
		$('#activator').click(function() {
			$('#box').animate({
				'top' : '0px'
			}, 500);
		});
		$('#boxclose').click(function() {
			$('#box').animate({
				'top' : '-700px'
			}, 500);
		});
	});
	$(document).ready(function() {
		//Hide (Collapse) the toggle containers on load
		$(".toggle_container").hide();
		//Switch the "Open" and "Close" state per click then slide up/down (depending on open/close state)
		$(".trigger").click(function() {
			$(this).toggleClass("active").next().slideToggle("slow");
			return false; //Prevent the browser jump to the link anchor
		});

	});
</script>
</head>
<body>
	<%
		String id = null;
		id = (String) session.getAttribute("id");
		String num = request.getParameter("chat_num");
	%>
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<a href="main.jsp"><img src="images/shap.png" title="#" /></a>
			</div>
			<div class="nav-icon">
				<span> </span>
			</div>
			<form action="search.fe" method="get">
				<div class="top-searchbar">
					<ul>
						<li><input type="text" placeholder="검색" name="search"
							id="search" maxlength="15" /><input type="submit" value="🔍" /></li>
					</ul>
					<ul>
						<li><input type="radio" name="sort" id="sort" value="time"
							checked>최신순 <input type="radio" name="sort" id="sort"
							value="like">추천순</li>
					</ul>
				</div>
				<div class="post-info"></div>
			</form>
			<div class="userinfo">
				<div class="user">
					<ul>
						<li>👤 <%=id%> 님
						</li>
					</ul>
					<ul>
						<li><a href="memberInfo.me">👤 내 정보</a></li>
						<li><a href="memberLogout.me">🔓 로그아웃</a></li>
					</ul>
					<ul>
						<li></li>
						<li></li>
					</ul>
					<ul>
						<li><a href="chatWriteForm.ch">📝 방파기</a></li>
						<li><a href="chatMemberAction.ch?member_id=<%=id%>">📖 내
								방목록</a></li>
					</ul>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>


	<div class="content">
		<div class="wrap">
			<div class="contact-info">
				<div class="contact-grids">
					<div class="clear"></div>
				</div>
				<form method="get" action="chatModifyAction.ch">
					<div class="contact-form">
						<input type="hidden" name=chat_num value=<%=num%>>
						<div class="contact-to">
							<span>Password : </span><input type="password" name="password"
								class="text">
						</div><br>
						<div class="text2">
							<input type ="text" name="chat_subject" id="chat_subject" placeholder="변경 할 방제"></textarea>
						</div>
						<span><input type="submit" class="" value="Submit"></span>
						<div class="clear"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!----start-footer--->
	<div class="footer">
		<p>
			Design by <a href="http://w3layouts.com/">W3layouts</a>
		</p>
	</div>
	<!----//End-footer--->
	<!---//End-wrap---->
</body>
</html>