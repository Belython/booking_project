<!DOCTYPE html>
<%@ page language="java"
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="HandheldFriendly" content="True">
	<title>${index_booking}</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css" media="screen,projection,print" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/prettyPhoto.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/calendar/tcal.css" type="text/css" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" />
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/css3-mediaqueries.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/sequence.jquery-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.uniform.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.prettyPhoto.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/sequence.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/selectnav.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>

	<c:choose>
		<c:when test="${currentLocale eq 'ru_RU'}">
			<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/calendar/tcal_ru.js"></script>
		</c:when>
		<c:when test="${currentLocale eq 'en_US'}">
			<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/calendar/tcal_en.js"></script>
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
	<%@include file="header.jsp"%>
	<!--//header-->

	<!--slider-->
	<section class="slider clearfix">
		<div id="sequence">
			<%--<ul>--%>
				<%--<li>--%>
					<%--<div class="info animate-in">--%>
						<%--<h2>Last minute Winter escapes</h2><br />--%>
						<%--<p>January 2013 holidays 40% off! An unique opportunity to realize your dreams</p>--%>
					<%--</div>--%>
					<%--<img class="main-image animate-in" src="" alt="" />--%>
				<%--</li>--%>
				<%--<li>--%>
					<%--<div class="info animate-in">--%>
						<%--<h2>Check out our top weekly deals</h2><br />--%>
						<%--<p>Save Now. Book Later.</p>--%>
					<%--</div>--%>
					<%--<img class="main-image animate-in" src="${pageContext.request.contextPath}/assets/images/slider/img.jpg" alt="" />--%>
				<%--</li>--%>
				<%--<li>--%>
					<%--<div class="info animate-in">--%>
						<%--<h2>Check out last minute flight, hotel &amp; vacation offers!</h2><br />--%>
						<%--<p>Save up to 50%!</p>--%>
					<%--</div>--%>
					<%--<img class="main-image animate-in" src="${pageContext.request.contextPath}/assets/images/slider/img.jpg" alt="" />--%>
				<%--</li>--%>
			<%--</ul>--%>
		</div>
	</section>
	<!--//slider-->

	<!--search-->
	<%@include file="search.jsp"%>
	<!--//search-->
	
	<!--main-->
	<div class="main" role="main">		
		<div class="wrap clearfix">
			<!--deals-->
			<section class="full">
				<h1>Most popular Hotels</h1>
				<div class="deals clearfix">
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Best ipsum hotel 
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 8 /10</span>
							<span class="price">Price per room per night from  <em>$ 50</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Tropicana hotel
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 9 /10</span>
							<span class="price">Price per room per night from  <em>$ 80</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Spa Resort hotel
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 8 /10</span>
							<span class="price">Price per room per night from  <em>$ 70</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->

					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Best ipsum hotel 
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 8 /10</span>
							<span class="price">Price per room per night from  <em>$ 50</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Spa Resort hotel
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 8 /10</span>
							<span class="price">Price per room per night from  <em>$ 70</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Tropicana hotel
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 9 /10</span>
							<span class="price">Price per room per night from  <em>$ 80</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Best ipsum hotel 
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 8 /10</span>
							<span class="price">Price per room per night from  <em>$ 50</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Tropicana hotel
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 9 /10</span>
							<span class="price">Price per room per night from  <em>$ 80</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Spa Resort hotel
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 8 /10</span>
							<span class="price">Price per room per night from  <em>$ 70</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Best ipsum hotel 
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 8 /10</span>
							<span class="price">Price per room per night from  <em>$ 50</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Spa Resort hotel
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 8 /10</span>
							<span class="price">Price per room per night from  <em>$ 50</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
					
					<!--deal-->
					<article class="one-fourth">
						<figure><a href="../../HTML/hotel.html" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
						<div class="details">
							<h1>Tropicana hotel
								<span class="stars">
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
									<img src="../../HTML/images/ico/star.png" alt="" />
								</span>
							</h1>
							<span class="address">London  •  <a href="#">Show on map</a></span>
							<span class="rating"> 9 /10</span>
							<span class="price">Price per room per night from  <em>$ 80</em> </span>
							<div class="description">
								<p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="../../HTML/hotel.html">More info</a></p>
							</div>
							<a href="../../HTML/hotel.html" title="Book now" class="gradient-button">Book now</a>
						</div>
					</article>
					<!--//deal-->
				</div>
			</section>	
			<!--//deals-->
			
			<!--top destinations-->
			<section class="destinations clearfix last">
				<h1>Top destinations around the world</h1>
				
				<!--column-->
				<article class="one-fourth">
					<figure><a href="#" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
					<div class="details">
						<a href="#" title="View all" class="gradient-button">View all</a>
						<h5>Paris</h5>
						<span class="count">1529 Hotels</span>
						<div class="ribbon">
							<div class="half hotel">
								<a href="hotels.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 70</span>
								</a>
							</div>
							<div class="half flight">
								<a href="flights.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 150</span>
								</a>
							</div>
						</div>
					</div>
				</article>
				<!--//column-->
				
				<!--column-->
				<article class="one-fourth">
					<figure><a href="#" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
					<div class="details">
						<a href="#" title="View all" class="gradient-button">View all</a>
						<h5>Amsterdam</h5>
						<span class="count">929 Hotels</span>
						<div class="ribbon">
							<div class="half hotel">
								<a href="hotels.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 70</span>
								</a>
							</div>
							<div class="half flight">
								<a href="flights.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 150</span>
								</a>
							</div>
						</div>
					</div>
				</article>
				<!--//column-->
				
				<!--column-->
				<article class="one-fourth">
					<figure><a href="#" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
					<div class="details">
						<a href="#" title="View all" class="gradient-button">View all</a>
						<h5>Saint Petersburg</h5>
						<span class="count">658 Hotels</span>
						<div class="ribbon">
							<div class="half hotel">
								<a href="hotels.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 70</span>
								</a>
							</div>
							<div class="half flight">
								<a href="flights.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 150</span>
								</a>
							</div>
						</div>
					</div>
				</article>
				<!--//column-->
				
				<!--column-->
				<article class="one-fourth last">
					<figure><a href="#" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
					<div class="details">
						<a href="#" title="View all" class="gradient-button">View all</a>
						<h5>Prague</h5>
						<span class="count">829 Hotels</span>
						<div class="ribbon">
							<div class="half hotel">
								<a href="hotels.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 70</span>
								</a>
							</div>
							<div class="half flight">
								<a href="flights.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 150</span>
								</a>
							</div>
						</div>
					</div>
				</article>
				<!--//column-->
				<!--column-->
				<article class="one-fourth">
					<figure><a href="#" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
					<div class="details">
						<a href="#" title="View all" class="gradient-button">View all</a>
						<h5>Prague</h5>
						<span class="count">829 Hotels</span>
						<div class="ribbon">
							<div class="half hotel">
								<a href="hotels.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 70</span>
								</a>
							</div>
							<div class="half flight">
								<a href="flights.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 150</span>
								</a>
							</div>
						</div>
					</div>
				</article>
				<!--//column-->
				
				<!--column-->
				<article class="one-fourth promo">
					<div class="ribbon-small">- 20%</div>
					<figure><a href="#" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
					<div class="details">
						<a href="#" title="View all" class="gradient-button">View all</a>
						<h5>Paris</h5>
						<span class="count">1529 Hotels</span>
						<div class="ribbon">
							<div class="half hotel">
								<a href="hotels.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 70</span>
								</a>
							</div>
							<div class="half flight">
								<a href="flights.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 150</span>
								</a>
							</div>
						</div>
					</div>
				</article>
				<!--//column-->
				
				<!--column-->
				<article class="one-fourth">
					<figure><a href="#" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
					<div class="details">
						<a href="#" title="View all" class="gradient-button">View all</a>
						<h5>Amsterdam</h5>
						<span class="count">929 Hotels</span>
						<div class="ribbon">
							<div class="half hotel">
								<a href="hotels.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 70</span>
								</a>
							</div>
							<div class="half flight">
								<a href="flights.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 150</span>
								</a>
							</div>
						</div>
					</div>
				</article>
				<!--//column-->
				
				<!--column-->
				<article class="one-fourth last">
					<figure><a href="#" title=""><img src="../../HTML/images/uploads/img.jpg" alt="" width="270" height="152" /></a></figure>
					<div class="details">
						<a href="#" title="View all" class="gradient-button">View all</a>
						<h5>Saint Petersburg</h5>
						<span class="count">658 Hotels</span>
						<div class="ribbon">
							<div class="half hotel">
								<a href="hotels.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 70</span>
								</a>
							</div>
							<div class="half flight">
								<a href="flights.html" title="View all">
									<span class="small">from</span>
									<span class="price">&#36; 150</span>
								</a>
							</div>
						</div>
					</div>
				</article>
				<!--//column-->
			</section>
			<!--//top destinations-->
		</div>
	</div>
	<!--//main-->
	
	<!--footer-->
	<footer>
		<div class="wrap clearfix">
			<!--column-->
			<article class="one-fourth">
				<h3>Book Your Travel</h3>
				<p>1400 Pennsylvania Ave. Washington, DC</p>
				<p><em>P:</em> 24/7 customer support: 1-555-555-5555</p>
				<p><em>E:</em> <a href="#" title="booking@mail.com">booking@mail.com</a></p>
			</article>
			<!--//column-->
			
			<!--column-->
			<article class="one-fourth">
				<h3>Customer support</h3>
				<ul>
					<li><a href="#" title="Faq">Faq</a></li>
					<li><a href="#" title="How do I make a reservation?">How do I make a reservation?</a></li>
					<li><a href="#" title="Payment options">Payment options</a></li>
					<li><a href="#" title="Booking tips">Booking tips</a></li>
				</ul>
			</article>
			<!--//column-->
			
			<!--column-->
			<article class="one-fourth">
				<h3>Follow us</h3>
				<ul class="social">
					<li class="facebook"><a href="#" title="facebook">facebook</a></li>
					<li class="youtube"><a href="#" title="youtube">youtube</a></li>
					<li class="rss"><a href="#" title="rss">rss</a></li>
					<li class="linkedin"><a href="#" title="linkedin">linkedin</a></li>
					<li class="googleplus"><a href="#" title="googleplus">googleplus</a></li>
					<li class="twitter"><a href="#" title="twitter">twitter</a></li>
					<li class="vimeo"><a href="#" title="vimeo">vimeo</a></li>
					<li class="pinterest"><a href="#" title="pinterest">pinterest</a></li>
				</ul>
			</article>
			<!--//column-->
			
			<!--column-->
			<article class="one-fourth last">
				<h3>Don’t miss our exclusive offers</h3>
				<form id="newsletter" action="../../HTML/newsletter.php" method="post">
					<fieldset>
						<input type="email" id="newsletter_signup" name="newsletter_signup" placeholder="Enter your email here" />
						<input type="submit" id="newsletter_submit" name="newsletter_submit" value="Signup" class="gradient-button" />
					</fieldset>
				</form>
			</article>
			<!--//column-->
			
			<section class="bottom">
				<p class="copy">Copyright 2012 Book your travel ltd. All rights reserved</p>
				<nav>
					<ul>
						<li><a href="#" title="About us">About us</a></li>
						<li><a href="../../HTML/contact.html" title="Contact">Contact</a></li>
						<li><a href="#" title="Partners">Partners</a></li>
						<li><a href="#" title="Customer service">Customer service</a></li>
						<li><a href="#" title="FAQ">FAQ</a></li>
						<li><a href="#" title="Careers">Careers</a></li>
						<li><a href="#" title="Terms & Conditions">Terms &amp; Conditions</a></li>
						<li><a href="#" title="Privacy statement">Privacy statement</a></li>
					</ul>
				</nav>
			</section>
		</div>
	</footer>
	<!--//footer-->
	<script>
		// Initiate selectnav function
		selectnav();
	</script>
</body>
</html>