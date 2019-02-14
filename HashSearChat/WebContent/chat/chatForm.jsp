<%@page import="vo.Chat"%>
<%@page import="vo.Message"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>#HASHSEARCHAT</title>
<style type="text/css">
#tr_right {
	text-align: right;
	font-size: 12pt;
}

#tr_left {
	text-align: left;
	font-size: 12pt;
}

#tr_right_id {
	text-align: right;
	font-size: 8pt;
}

#tr_left_id {
	text-align: left;
	font-size: 8pt;
}

#in1 {
	width: 100%;
	height: 43px;
}

#btn1 {
	padding: 5px 50px;
}
</style>
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
<link rel="stylesheet" href="css/bootstrap.css">
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
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
		}
		ArrayList<Chat> chatList = (ArrayList<Chat>) request.getAttribute("chatList");
		Chat chatDetail = (Chat) request.getAttribute("chatDetail");
		ArrayList<Message> msgList = (ArrayList<Message>) request.getAttribute("msgList");
	%>
	<div class="header">
		<div class="wrap">
			<div class="logo" style="margin-top: 22px">
				<a href="main.jsp"><img src="images/shap.png" title="#" /></a>
			</div>
			<div class="nav-icon">
				<span> </span>
			</div>
			<form action="chatSearch.ch" method="get">
				<div class="top-searchbar" style="margin-top: 22px">
					<ul>
						<li><input type="text" placeholder="검색" name="search"
							id="search" maxlength="15" /><input type="submit" value="🔍" /></li>
					</ul>

				</div>
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
					<ul>
						<li><a href="chatWriteForm.ch">💬 방 파기</a></li>
						<li><a href="chatMemberAction.ch?member_id=<%=id%>">📝 내
								방목록</a></li>
					</ul>
					<%
						}
					%>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="container" style="margin-top: 100px">
		<div class="jumbotron"
			style="text-align: center; float: right; width: 35%; padding: 10px; height: 50px; border: 1px solid gray; margin-right: 30px; background-color: white; position: fixed; right: 30px; top: 225px;">
			<p><%=chatDetail.getCHAT_SUBJECT()%></p>
		</div>
		<div id="load_tweets"
			style="float: right; width: 35%; height: 500px; padding: 10px; overflow: auto; border-bottom: 1px solid gray; border-right: 1px solid gray; border-left: 1px solid gray; margin-right: 30px; background-color: white; position: fixed; right: 30px; top: 265px;">

			<%
				for (int i = msgList.size()-1; i >= 0; i--) {
					if (msgList.get(i).getCHAT_ID().equals(id)) {
			%>
			<table width="100%">
				<tr id="tr_right_id">
					<td><b><%=msgList.get(i).getCHAT_ID()%></b></td>
				</tr>
				<tr id="tr_right">
					<td><%=msgList.get(i).getCHAT_MESSAGE()%></td>
				</tr>
			</table>
			<br>
			<%
				} else {
			%>
			<table width="100%">
				<tr id="tr_left_id">
					<td><b><%=msgList.get(i).getCHAT_ID()%></b></td>
				</tr>
				<tr id="tr_left">
					<td><%=msgList.get(i).getCHAT_MESSAGE()%></td>
				</tr>
			</table>
			<br>
			<%
				}
				}
			%>

		</div>
		<script>
		function autoRefresh_sample_div() {
			var currentLocation = window.location;
			$("#load_tweets").load(currentLocation + ' #load_tweets');
		}
		setInterval('autoRefresh_sample_div()', 3000); //30초 후 새로고침
		
		</script>
		<form method="post" action="chatInfoAction.ch?search=&CHAT_NUM=<%=chatDetail.getCHAT_NUM()%>">
			<div class="form-group"
				style="float: right; width: 35%; padding: 1px; height: 50px; margin-right: 30px; background-color: white; position: fixed; right: 30px; top: 780px;">
				<input id="in1" type="text" class="form-control" name="CHAT_MESSAGE"
					autofocus required /> <input type="hidden" name="CHAT_NUM"
					value="<%=chatDetail.getCHAT_NUM()%>" /><br> <input
					type="hidden" name="CHAT_ID" value="<%=id%>" /> <input
					style="position: fixed; right: 57px; top: 785px;" type="submit"
					class="btn btn-link" value="전송" /> <a
					href="chatListAction.ch?search=" id="btn1"
					class="btn btn-warning pull-right">나가기</a>
			</div>
		</form>
		
		<div id="main" role="main" style="width: 60%;">
			<ul id="tiles">
				<!-- These are our grid blocks -->
				<%
					for (int i = 0; i < chatList.size(); i++) {
				%>
				<li>
					<div class="post-info">
						<div class="post-basic-info">
							<h3>
								<a href="#"><%=chatList.get(i).getCHAT_SUBJECT()%></a>
							</h3>
							<p><%=chatList.get(i).getCHAT_ID()%></p>
						</div>
						<%
							if (id != null) {
						%>
						<div class="post-info-rate-share">
							<div class="post-share">
								<a
									href="chatInfoAction.ch?search=&CHAT_NUM=<%=chatList.get(i).getCHAT_NUM()%>">🚪
									입장</a>
							</div>
							<div class="clear"></div>
						</div>
						<%
							if (id.equals(chatList.get(i).getCHAT_ID())) {
						%>
						<div class="post-info-rate-share">
							<div class="rateit">
								<a
									href="chatModify.ch?chat_num=<%=chatList.get(i).getCHAT_NUM()%>">🛠
									수정</a>
							</div>
							<div class="post-share">
								<a
									href="chatDelete.ch?chat_num=<%=chatList.get(i).getCHAT_NUM()%>">🗑
									삭제</a>
							</div>
							<div class="clear"></div>
						</div>

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