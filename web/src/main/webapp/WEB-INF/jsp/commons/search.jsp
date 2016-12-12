<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--search-->
<div class="main-search">
    <form id="main-search" method="post" action="searchHotels">
        <input type="hidden" name="command" value="searchHotel"/>
        <!--column-->
        <div class="column radios">
            <h4><span>01</span><spring:message code="search.what"/></h4>
            <div class="f-item" >
                <input type="radio" name="radio" id="hotel" value="form1" />
                <label for="hotel">Hotel</label>
            </div>
            <div class="f-item">
                <input type="radio" name="radio" id="self_catering" value="form2" />
                <label for="self_catering">Self Catering</label>
            </div>
            <div class="f-item">
                <input type="radio" name="radio" id="flight" value="form3" />
                <label for="flight">Flight</label>
            </div>
            <div class="f-item" >
                <input type="radio" name="radio" id="cruise" value="form4" />
                <label for="cruise">Cruise</label>
            </div>
            <div class="f-item" >
                <input type="radio" name="radio" id="flight_and_hotel" value="form5" />
                <label for="flight_and_hotel">Flight &amp; Hotel</label>
            </div>
            <div class="f-item">
                <input type="radio" name="radio" id="rent_a_car" value="form6" />
                <label for="rent_a_car">Rent a Car</label>
            </div>
        </div>
        <!--//column-->

        <div class="forms">
            <!--form hotel-->
            <div class="form" id="form1">
                <!--column-->
                <div class="column">
                    <h4><span>02</span><spring:message code="search.where"/></h4>
                    <div class="f-item">
                        <label for="destination">Your destination</label>
                        <input type="text" list="destinations"
                               placeholder="<spring:message code='search.destinationValue'/>"
                               id="destination" name="destination"/>
                        <datalist id="destinations">
                            <%--HERE IS SOME AJAX (see destinations.jsp)--%>
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
                            <input class="tcal" type="text" id="checkInDate" name="checkInDate"/>
                        </div>
                    </div>
                    <div class="f-item datepicker">
                        <label for="checkOutDate"><spring:message code="search.checkOutDate"/></label>
                        <div class="">
                            <input class="tcal" type="text" id="checkOutDate" name="checkOutDate"/>
                        </div>
                    </div>
                </div>
                <!--//column-->

                <!--column-->
                <div class="column twins">
                    <h4><span>04</span><spring:message code="search.who"/></h4>
                    <div class="f-item spinner">
                        <label for="spinner1"><spring:message code="search.totalRooms"/></label>
                        <input type="text" placeholder="" id="spinner1" name="totalRooms" />
                    </div>
                    <div class="f-item spinner">
                        <label for="spinner2"><spring:message code="search.totalPersons"/></label>
                        <input type="text" placeholder="" id="spinner2" name="totalPersons" />
                    </div>
                </div>
                <!--//column-->
            </div>
            <!--//form hotel-->
        </div>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <input type="submit" value="<spring:message code='search.search'/>" class="search-submit" id="search-submit">
    </form>
</div>
<!--//search-->
