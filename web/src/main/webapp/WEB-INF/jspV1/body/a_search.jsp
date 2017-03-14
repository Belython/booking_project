<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<!--search-->
<div class="main-search" ng-app="hotelsSearch" ng-controller="destinationsCtrl as ct">
    <form id="main-search" method="POST" action="search_results">

        <div class="forms">
            <!--form hotel-->
            <div class="form" id="form1">
                <!--column-->
                <div class="column">
                    <h4><span>02</span><spring:message code="search.where"/></h4>
                    <div class="f-item">
                        <label for="destination"><spring:message code="search.yourDestination"/></label>
                        <input type="text" list="destinations"
                               placeholder="<spring:message code='search.destinationValue'/>"
                               id="destination" name="destination"
                               ng-model="ct.destination"
                               ng-change="ct.getDestinations()"/>
                        <datalist id="destinations">
                            <option ng-repeat="dest in ct.possibleDestinations"
                                        value="{{dest.location.country}}, {{dest.location.city}}{{(dest.hotelName != 'any') ? (',' + dest.hotelName) : ''}}">
                            </option>
                        </datalist>
                    </div>
                </div>
                <!--//column-->

                <!--column-->
                <div class="column twins">
                    <h4><span>03</span><spring:message code="search.when"/></h4>
                    <div class="f-item datepicker">
                        <label for="checkInDate"><spring:message code="search.checkInDate"/></label>
                        <div class="">
                            <input class="tcal" type="text" id="checkInDate" name="checkInDate" ng-model="ct.checkInDate"/>
                        </div>
                    </div>
                    <div class="f-item datepicker">
                        <label for="checkOutDate"><spring:message code="search.checkOutDate"/></label>
                        <div class="">
                            <input class="tcal" type="text" id="checkOutDate" name="checkOutDate" ng-model="ct.checkOutDate"/>
                        </div>
                    </div>
                </div>
                <!--//column-->

                <!--column-->
                <div class="column twins">
                    <h4><span>04</span><spring:message code="search.who"/></h4>
                    <div class="f-item spinner">
                        <label for="totalRooms"><spring:message code="search.totalRooms"/></label>
                        <input type="text" placeholder="" id="totalRooms" name="totalRooms" ng-model="ct.totalRooms"/>
                    </div>
                    <div class="f-item spinner">
                        <label for="totalPersons"><spring:message code="search.totalPersons"/></label>
                        <input type="text" placeholder="" id="totalPersons" name="totalPersons" ng-model="ct.totalPersons"/>
                    </div>
                </div>
                <!--//column-->
            </div>
            <!--//form hotel-->
        </div>
        <input type="button" value="<spring:message code='search.search'/>" class="search-submit" id="search-submit" ng-click="ct.makeOrder()">
    </form>
</div>
<!--//search-->