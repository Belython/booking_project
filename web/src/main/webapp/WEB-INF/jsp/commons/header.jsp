<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>

<header>
    <div class="wrap clearfix">
        <!--logo-->
        <h1 class="logo">
            <a href="${context}/index" title="Book Your Travel - home">
                <img src="${context}/assets/images/txt/logo.png" alt="Book Your Travel" />
            </a>
        </h1>
        <!--//logo-->

        <!--ribbon-->
        <div class="ribbon">
            <nav>
                <ul class="profile-nav">
                    <li class="active">
                        <a href="#" title="My Account">My Account</a>
                        <spring:message code="hello"/>
                    </li>
                    <c:choose>
                        <c:when test="${empty user}">
                            <li>
                                <a id="loginRef" href="#" title="Login">Login</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${user.role eq 'admin'}">
                                <li>
                                    <p>${header_welcome} ${user.firstName}${header_administrator}</p>
                                    <a href="controller?command=goToAdminPage">${header_goToAdminPage}</a>
                                </li>
                            </c:if>
                            <li>
                                <a href="controller?command=goToAccount" title="Settings">Settings</a>
                            </li>
                            <li>
                                <a href="${context}/logout">${header_signOut}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>

                <ul class="lang-nav">
                    <c:set var="localeSet" value="${localeMap.keySet()}"/>
                    <c:forEach var="locale" items="${localeSet}">
                        <c:set var="displayLanguage" value="${localeMap.get(locale)}"/>
                        <c:choose>
                            <c:when test="${currentLocale eq locale}">
                                <li class="active">
                                    <a href="#" title=${displayLanguage}>${displayLanguage}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${context}/set_locale?locale=${locale}" title=${displayLanguage}>${displayLanguage}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>

                <ul class="currency-nav">
                    <c:set var="currencySet" value="${currencyMap.keySet()}"/>
                    <c:forEach var="currency" items="${currencySet}">
                        <c:set var="displayCurrency" value="${currencyMap.get(currency)}"/>
                        <c:choose>
                            <c:when test="${currentCurrency eq currency}">
                                <li class="active">
                                    <a href="#" title="${displayCurrency}">${displayCurrency}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="controller?command=setCurrency&currentCurrency=${currency}" title="${displayCurrency}">${displayCurrency}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </nav>
        </div>
        <!--//ribbon-->

        <!--search-->
        <div class="search">
            <form id="search-form" method="get" action="search-form">
                <input type="search" placeholder="Search entire site here" name="site_search" id="site_search" />
                <input type="submit" id="submit-site-search" value="submit-site-search" name="submit-site-search"/>
            </form>
        </div>
        <!--//search-->

        <!--contact-->
        <div class="contact">
            <span>24/7 Support number</span>
            <span class="number">1- 555 - 555 - 555</span>
        </div>
        <!--//contact-->

        <!--login-->
        <%@include file="login.jsp"%>
        <!--//login-->

        <!--registration-->
        <%@include file="regisration.jsp"%>
        <!--//registration-->

    </div>

    <!--main navigation-->
    <nav class="main-nav" role="navigation" id="nav">
        <ul class="wrap">
            <li class="active"><a href="hotels.html" title="Hotels">Hotels</a>
                <ul>
                    <li><a href="#">Secondary navigation</a></li>
                    <li><a href="#">example links</a></li>
                    <li><a href="error.html">Error page</a></li>
                    <li><a href="loading.html">Loading page</a></li>
                </ul>
            </li>
            <li><a href="flights.html" title="Flights">Flights</a></li>
            <li><a href="flight_and_hotels.html" title="Flight + Hotel">Flight + Hotel</a></li>
            <li><a href="self_catering.html" title="Self catering">Self catering</a></li>
            <li><a href="cruise.html" title="Cruises">Cruises</a></li>
            <li><a href="car_rental.html" title="Car rental">Car rental</a></li>
            <li><a href="hot_deals.html" title="Hot deals">Hot deals</a></li>
            <li><a href="#" title="Vacations">Vacations</a></li>
            <li><a href="#" title="Business travel">Business travel</a></li>
            <li><a href="#" title="Group travel">Group travel</a></li>
            <li><a href="get_inspired.html" title="Get inspired">Get inspired</a></li>
            <li><a href="#" title="Travel guides">Travel guides</a>
                <ul>
                    <li><a href="location.html">Location Details</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <!--//main navigation-->

    <script>
        // Initiate selectnav function
        selectnav();
    </script>

</header>

