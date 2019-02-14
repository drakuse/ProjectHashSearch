<%@page import="vo.Member"%>
<%@page import="java.util.ArrayList"%>
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
		String search = null;
		ArrayList<Member> memberList = (ArrayList<Member>) request.getAttribute("memberList");
	%>
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<a href="main.jsp"><img src="images/shap.png" title="#" /></a>
			</div>
			<div class="nav-icon">
				<span> </span>
			</div>
			<form action="memberListAction.me" method="get">
				<div class="top-searchbar">
					<ul>
						<li><input type="text" placeholder="검색" name="search"
							id="search" maxlength="15" /><input type="submit" value="🔍" /></li>
					</ul>					
				</div>
				<div class="post-info"></div>
			</form>
			<div class="userinfo">
				<div class="user">
					<%
						if (id == null) {
					%>
					<ul>
						<li><a href="loginForm.me">🔐 로그인 </a></li>
						<li><a href="joinForm.me">👤 회원가입</a></li>
					</ul>
					<%
						} else {
					%>
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
					<%
						}
					%>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>



	<div class="content">
		<div class="wrap">
			<div id="main" role="main">
				<ul id="tiles">
					<!-- These are our grid blocks -->
					<%
						if (request.getAttribute("memberList") != null) {
							ArrayList<Member> member = (ArrayList<Member>) request.getAttribute("memberList");
							for (int i = 0; i < member.size(); i++) {
								if (member.get(i).getMEMBER_ID().equals("ADMIN")) {
								} else {
					%>
					<li>
						<div class="post-info">
							<div class="post-basic-info">
								<h3>
									<a href="#"><%=member.get(i).getMEMBER_ID()%></a>
								</h3>
								<p><%=member.get(i).getMEMBER_NAME()%></p>
								<p>
									🚨 신고횟수 :
									<%=member.get(i).getMEMBER_REFUSE()%></p>
							</div>
							<%
								if (member.get(i).getMEMBER_REFUSE() > 2) {
							%>
							<div class="post-info-rate-share">
								<div class="post-share">
									<a
										href="memberAdminDelete.me?MEMBER_ID=<%=member.get(i).getMEMBER_ID()%>">🗑
										삭제</a>
								</div>
								<div class="clear"></div>
							</div>
							<%
								}
							%>
						</div>
					</li>
					<%
						}
							}
						}
					%>
				</ul>
			</div>
		</div>
	</div>


	<!---//End-content---->
	<!----wookmark-scripts---->
	<script src="js/jquery.imagesloaded.js"></script>
	<script src="js/jquery.wookmark.js"></script>
	<script type="text/javascript">
		(function($) {
			var $tiles = $('#tiles'), $handler = $('li', $tiles), $main = $('#main'), $window = $(window), $document = $(document), options = {
				autoResize : true, // This will auto-update the layout when the browser window is resized.
				container : $main, // Optional, used for some extra CSS styling
				offset : 20, // Optional, the distance between grid items
				itemWidth : 280
			// Optional, the width of a grid item
			};
			/**
			 * Reinitializes the wookmark handler after all images have loaded
			 */
			function applyLayout() {
				$tiles.imagesLoaded(function() {
					// Destroy the old handler
					if ($handler.wookmarkInstance) {
						$handler.wookmarkInstance.clear();
					}

					// Create a new layout handler.
					$handler = $('li', $tiles);
					$handler.wookmark(options);
				});
			}
			/**
			 * When scrolled all the way to the bottom, add more tiles
			 */
			function onScroll() {
				// Check if we're within 100 pixels of the bottom edge of the broser window.
				var winHeight = window.innerHeight ? window.innerHeight
						: $window.height(), // iphone fix
				closeToBottom = ($window.scrollTop() + winHeight > $document
						.height() - 100);

				if (closeToBottom) {
					// Get the first then items from the grid, clone them, and add them to the bottom of the grid
					var $items = $('li', $tiles), $firstTen = $items.slice(0,
							10);
					$tiles.append($firstTen.clone());

					applyLayout();
				}
			}
			;

			// Call the layout function for the first time
			applyLayout();

			// Capture scroll event.
			$window.bind('scroll.wookmark', onScroll);
		})(jQuery);
	</script>
	<!----//wookmark-scripts---->
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