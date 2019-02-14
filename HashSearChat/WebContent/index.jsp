<%@page import="vo.Feed"%>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
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
	%>
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<a href="main.jsp"><img src="images/shap.png" title="#" /></a>
			</div>
			<div class="nav-icon">
				<span> </span>
			</div>
			<!-- 검색장 검색내용과 정렬순을 get방식으로 전달  -->
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
						<li><a href="feedForm.fe">📝 글 쓰기</a></li>
						<li><a href="feedMemberAction.fe?member_id=<%=id%>">📖
								글목록</a></li>
					</ul>
					<%
						}
					%>
				</div>
			</div>
			<div class="clear"></div>
		</div>

		<div class="navbar navbar-inverse navbar-fixed-left">
			<div class="container">
				<div class="navbar-header">
					<!-- 브라우저가 좁아졋을때 나오는 버튼(클릭시 메뉴출력) -->
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">카테고리</a>
				</div>
				<!-- 카테고리별 검색결과  -->
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="feedCategoryAction.fe?category=1">유머</a></li>
						<li><a href="feedCategoryAction.fe?category=2">스포츠</a></li>
						<li><a href="feedCategoryAction.fe?category=3">게임</a></li>
						<li><a href="feedCategoryAction.fe?category=4">영화</a></li>
						<li><a href="feedCategoryAction.fe?category=5">자유</a></li>
					</ul>
				</div>
			</div>
		</div>
	<div class="clear"></div>
	</div>



	<!-- 게시판의 내용들 출력 -->
	<div class="content">
		<div class="wrap">
			<div id="main" role="main" style="margin-top: 20em;">
				<ul id="tiles">
					<%
						if (request.getAttribute("feedList") != null) {
							ArrayList<Feed> feed = (ArrayList<Feed>) request.getAttribute("feedList");
							String category;
							for (int i = 0; i < feed.size(); i++) {
								if (feed.get(i).getFeed_category() == 1) {
									category = "유머";
								} else if (feed.get(i).getFeed_category() == 2) {
									category = "스포츠";
								} else if (feed.get(i).getFeed_category() == 3) {
									category = "게임";
								} else if (feed.get(i).getFeed_category() == 4) {
									category = "영화";
								} else {
									category = "자유";
								}
					%>
					<li>
						<div class="post-info">
							<div class="post-basic-info">
								<p><%=category%></p>
								<h3><%=feed.get(i).getFeed_content()%></h3>
								<h6 style="text-align: right">
									<a href="#"><%=feed.get(i).getFeed_id()%></a>
								</h6>
							</div>
							<%
								if (id != null) {
							%>
							<div class="post-info-rate-share">
								<div class="rateit">
									<a
										href="likeCount.fe?feed_num=<%=feed.get(i).getFeed_num()%>&feed_like=<%=feed.get(i).getFeed_like()%>">👍
										좋아요 </a><%=feed.get(i).getFeed_like()%>
								</div>
								<div class="post-share">
									<a
										href="refuseCount.fe?feed_num=<%=feed.get(i).getFeed_num()%>&feed_refuse=<%=feed.get(i).getFeed_refuse()%>">🚨
										신고하기</a>
								</div>
								<div class="clear"></div>
							</div>
							<%
								if (id.equals(feed.get(i).getFeed_id())) {
							%>
							<div class="post-info-rate-share">
								<div class="rateit">
									<a
										href="feedModify.fe?feed_num=<%=feed.get(i).getFeed_num()%>&content=<%=feed.get(i).getFeed_content()%>">🛠
										수정</a>
								</div>
								<div class="post-share">
									<a
										href="feedDelete.fe?feed_num=<%=feed.get(i).getFeed_num()%>&content=<%=feed.get(i).getFeed_content()%>">🗑
										삭제</a>
								</div>
								<div class="clear"></div>
							</div>
							<%
								} else {
							%>
							<div class="post-info-rate-share">
								<div class="rateit"></div>
								<div class="post-share">
									<a
										href="feedMemberAction.fe?member_id=<%=feed.get(i).getFeed_id()%>">📖
										작성자 글보기</a>
								</div>
								<div class="clear"></div>
							</div>
							<%
								}
										}
							%>
						</div>
					</li>
					<%
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