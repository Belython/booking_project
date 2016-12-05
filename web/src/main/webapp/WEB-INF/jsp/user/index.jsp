<!DOCTYPE html>
<%@ page language="java"
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>${index_booking}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="HandheldFriendly" content="True">
	<%@include file="/WEB-INF/jsp/user/includes/standardScripts.jsp"%>

	<c:choose>
		<c:when test="${currentLocale eq 'ru_RU'}">
			<script type="text/javascript" src="${context}/assets/plugins/calendar/tcal_ru.js"></script>
		</c:when>
		<c:when test="${currentLocale eq 'en_US'}">
			<script type="text/javascript" src="${context}/assets/plugins/calendar/tcal_en.js"></script>
		</c:when>
	</c:choose>

	<script type="text/javascript">
		$(document).ready(function(){
			$(".form").hide();
			$(".form:first").show();
			$(".f-item:first").addClass("active");
			$(".f-item:first span").addClass("checked");
		});
	</script>
</head>
<body>
	<!--header-->
	<%@include file="/WEB-INF/jsp/user/includes/header.jsp"%>
	<!--//header-->

	<!--slider-->
	<section class="slider clearfix">
		<div id="sequence">
			<ul>
				<li>
					<div class="info animate-in">
						<h2>Last minute Winter escapes</h2><br />
						<p>January 2013 holidays 40% off! An unique opportunity to realize your dreams</p>
					</div>
					<img class="main-image animate-in" src="" alt="" />
				</li>
				<li>
					<div class="info animate-in">
						<h2>Check out our top weekly deals</h2><br />
						<p>Save Now. Book Later.</p>
					</div>
					<img class="main-image animate-in" src="${context}/assets/images/slider/img.jpg" alt="" />
				</li>
				<li>
					<div class="info animate-in">
						<h2>Check out last minute flight, hotel &amp; vacation offers!</h2><br />
						<p>Save up to 50%!</p>
					</div>
					<img class="main-image animate-in" src="${context}/assets/images/slider/img.jpg" alt="" />
				</li>
			</ul>
		</div>
	</section>
	<!--//slider-->

	<!--search-->
	<%@include file="/WEB-INF/jsp/user/includes/search.jsp"%>
	<!--//search-->

	<!--footer-->
	<%@include file="/WEB-INF/jsp/user/includes/footer.jsp"%>
	<!--//footer-->
	
	<script>
		// Initiate selectnav function
		selectnav();
	</script>
</body>
</html>