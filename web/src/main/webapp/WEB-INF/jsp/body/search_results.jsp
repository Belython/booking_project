<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="loc" uri="http://booking.by/localizator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="selectedHotel" value="${order.hotel}"/>
<c:set var="selectedLocation" value="${selectedHotel.location}"/>
<c:set var="locationAlias" value="hotel.location"/>
<c:set var="selectedRoomType" value="${order.roomType}"/>
<c:set var="selectedFacilities" value="${order.facilityList}"/>
<c:set var="roomFacilities" value=""/>
<c:forEach var="selectedFacility" items="${selectedFacilities}">
    <c:set var="roomFacilities" value="${roomFacilities.concat(', ').concat(selectedFacility)}"/>
</c:forEach>
<c:set var="mainRef" value="${context}/search_results?${locationAlias}.country=${selectedLocation.country}&${locationAlias}.city=${selectedLocation.city}&hotel.hotelName=${selectedHotel.hotelName}&checkInDate=${order.checkInDate}&checkOutDate=${order.checkOutDate}&totalRooms=${order.totalRooms}&totalPersons=${order.totalPersons}"/>
<c:set var="sortRef" value="${mainRef}&roomFacilities=${roomFacilities}"/>

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

            <!--sidebar-->
            <%@include file="../commons/sidebar.jsp"%>
            <!--//sidebar-->

            <!--three-fourth content-->
            <section class="three-fourth">
                <div class="sort-by">
                    <h3><spring:message code="selectHotel.sortBy"/> </h3>
                    <ul class="sort">
                        <li>Popularity <a href="#" title="ascending" class="ascending">ascending</a><a href="#" title="descending" class="descending">descending</a></li>

                        <li><spring:message code="selectHotel.price"/>
                            <a href="${sortRef}&sortPrice=asc" title="ascending" class="ascending">ascending</a>
                            <a href="${sortRef}&sortPrice=desc" title="descending" class="descending">descending</a>
                        </li>
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
                                <a href="${context}/hotel?hotelId=${hotel.hotelId}" title="">
                                    <img src="${context}/assets/images/uploads/img.jpg" alt="" width="270" height="152" />
                                </a>
                            </figure>
                            <div class="details">
                                <h1>${hotel.hotelName}
                                    <span class="stars">
                                        <c:forEach var="star" begin="1" end="${hotel.stars}">
                                            <img src="${context}/assets/images/ico/star.png" alt="" />
                                        </c:forEach>
                                    </span>
                                </h1>
                                <span class="address">${hotel.location.city}  •  <a href="#">Show on map</a></span>
                                <span class="rating"> 8 /10</span>
                                <span class="maxPersons"><spring:message code="entityField.maxPersons"/>: ${roomType.maxPersons}</span>
                                <span class="price"><spring:message code="entityField.pricePerNight"/>
                                    <em>${loc:getMoney(roomType.pricePerNight)}</em>
                                </span>
                                <a href="${context}/hotel?hotelId=${hotel.hotelId}" title="Book now" class="gradient-button"><spring:message code="selectHotel.bookNow"/> </a>
                            </div>
                        </article>
                        <!--//deal-->
                    </c:forEach>

                    <!--bottom navigation-->
                    <%@ include file="/WEB-INF/jsp/commons/pagination.jsp"%>
                    <!--//bottom navigation-->
                </div>
            </section>
            <!--//three-fourth content-->
        </div>
        <!--//main content-->
    </div>
</div>
<!--//main-->


