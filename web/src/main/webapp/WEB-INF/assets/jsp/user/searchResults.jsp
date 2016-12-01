<!DOCTYPE html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="loc" uri="http://booking.by/localizator" %>
<!--[if IE 7 ]>    <html class="ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8 oldie" lang="en"> <![endif]-->
<!--[if IE 	 ]>    <html class="ie" lang="en"> <![endif]-->
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="HandheldFriendly" content="True">
    <title>Book Your Travel - Search results</title>
    <link rel="stylesheet" href="../../../assets/css/style.css" type="text/css" media="screen,projection,print" />
    <link rel="stylesheet" href="../../../assets/css/prettyPhoto.css" type="text/css" media="screen" />
    <link rel="shortcut icon" href="images/favicon.ico" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/css3-mediaqueries.js"></script>
    <script type="text/javascript" src="js/jquery.uniform.min.js"></script>
    <script type="text/javascript" src="js/jquery.raty.min.js"></script>
    <script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
    <script type="text/javascript" src="js/selectnav.js"></script>
    <script type="text/javascript" src="js/scripts.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('dt').each(function() {
                var tis = $(this), state = false, answer = tis.next('dd').hide().css('height','auto').slideUp();
                tis.click(function() {
                    state = !state;
                    answer.slideToggle(state);
                    tis.toggleClass('active',state);
                });
            });

            $('.view-type li:first-child').addClass('active');

            $('#star').raty({
                score    : 3,
                click: function(score, evt) {
                    alert('ID: ' + $(this).attr('id') + '\nscore: ' + score + '\nevent: ' + evt);
                }
            });


        });

        $(window).load(function () {
            var maxHeight = 0;

            $(".three-fourth .one-fourth").each(function(){
                if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
            });
            $(".three-fourth .one-fourth").height(maxHeight);
        });

    </script>
</head>
<body>


<!--main-->
<div class="main" role="main">
    <div class="wrap clearfix">
        <!--main content-->
        <div class="content clearfix">
            <!--breadcrumbs-->
            <nav role="navigation" class="breadcrumbs clearfix">
                <!--crumbs-->
                <ul class="crumbs">
                    <li><a href="#" title="Home">Home</a></li>
                    <li><a href="#" title="Hotels">Hotels</a></li>
                    <li><a href="#" title="United Kingdom">United Kingdom</a></li>
                    <li><a href="#" title="London">London</a></li>
                    <li>Search results</li>
                </ul>
                <!--//crumbs-->

                <!--top right navigation-->
                <ul class="top-right-nav">
                    <li><a href="#" title="Back to results">Back to results</a></li>
                    <li><a href="#" title="Change search">Change search</a></li>
                </ul>
                <!--//top right navigation-->
            </nav>
            <!--//breadcrumbs-->

            <!--three-fourth content-->
            <section class="three-fourth">
                <div class="sort-by">
                    <h3>Sort by</h3>
                    <ul class="sort">
                        <li>Popularity <a href="#" title="ascending" class="ascending">ascending</a><a href="#" title="descending" class="descending">descending</a></li>
                        <li>Price <a href="#" title="ascending" class="ascending">ascending</a><a href="#" title="descending" class="descending">descending</a></li>
                        <li>Stars <a href="#" title="ascending" class="ascending">ascending</a><a href="#" title="descending" class="descending">descending</a></li>
                        <li>Rating <a href="#" title="ascending" class="ascending">ascending</a><a href="#" title="descending" class="descending">descending</a></li>
                    </ul>

                    <ul class="view-type">
                        <li class="grid-view"><a href="#" title="grid view">grid view</a></li>
                        <li class="list-view"><a href="#" title="list view">list view</a></li>
                        <li class="location-view"><a href="#" title="location view">location view</a></li>
                    </ul>
                </div>

                <div class="deals clearfix">
                    <c:forEach var="hotel" items="${userHotelList}">
                        <c:set var="roomType" value="${hotel.suitableRoomType}"/>
                        <!--deal-->
                        <article class="one-fourth">
                            <figure>
                                <a href="controller?command=goToHotel" title="">
                                    <img src="images/uploads/img.jpg" alt="" width="270" height="152" />
                                </a>
                            </figure>
                            <div class="details">
                                <h1>${hotel.hotelName}
                                    <span class="stars">
                                        <c:forEach var="star" begin="1" end="${hotel.stars}">
                                            <img src="images/ico/star.png" alt="" />
                                        </c:forEach>
                                    </span>
                                </h1>
                                <span class="address">${hotel.location.city}  •  <a href="#">Show on map</a></span>
                                <span class="rating"> 8 /10</span>
                                <span class="maxPersons">Max persons: ${roomType.maxPersons}</span>
                                <span class="price">Price per room per night from
                                    <em>${loc:getMoney(roomType.pricePerNight)}</em>
                                </span>
                                <div class="description">
                                    <p>Overlooking the Aqueduct and Nature Park, Lorem Ipsum Hotel is situated 5 minutes’ walk from London’s Zoological Gardens and a metro station. <a href="hotel.html">More info</a></p>
                                </div>
                                <a href="controller?command=goToHotel" title="Book now" class="gradient-button">Book now</a>
                            </div>
                        </article>
                        <!--//deal-->
                    </c:forEach>

                    <!--bottom navigation-->
                    <div class="bottom-nav">
                        <!--back up button-->
                        <a href="#" class="scroll-to-top" title="Back up">Back up</a>
                        <!--//back up button-->

                        <!--pager-->
                        <div class="pager">
                            <span><a href="#">First page</a></span>
                            <span><a href="#">&lt;</a></span>
                            <span class="current">1</span>
                            <span><a href="#">2</a></span>
                            <span><a href="#">3</a></span>
                            <span><a href="#">4</a></span>
                            <span><a href="#">5</a></span>
                            <span><a href="#">6</a></span>
                            <span><a href="#">7</a></span>
                            <span><a href="#">8</a></span>
                            <span><a href="#">&gt;</a></span>
                            <span><a href="#">Last page</a></span>
                        </div>
                        <!--//pager-->
                    </div>
                    <!--//bottom navigation-->
                </div>
            </section>
            <!--//three-fourth content-->
        </div>
        <!--//main content-->
    </div>
</div>
<!--//main-->


<script>
    // Initiate selectnav function
    selectnav();
</script>
</body>
</html>
