<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/WEB-INF/jsp/commons/slider.jsp"%>

<div class="main-search">
    <form name="adminMainForm" method="POST" action="users_redactor">
        <input type="radio" name="command" value="goToHotelsRedactor"> <spring:message code="tableRedactor.hotelsRedactor"/>
        <br/>
        <input type="radio" name="command" value="goToLocationsRedactor" > ${sideBar_redactLocations}
        <br/>
        <input type="radio" name="command" value="goToUsersRedactor" > <spring:message code="tableRedactor.usersRedactor"/>
        <br/>
        <input type="radio" name="command" value="goToRoomTypesRedactor" > ${sideBar_redactRoomTypes}
        <br/>
        <input type="radio" name="command" value="goToRoomsRedactor" > ${sideBar_redactRooms}
        <br/>
        <input type="radio" name="command" value="goToBillsRedactor" > ${sideBar_redactBills}
        <br/>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <input type="submit" value="ssss">
    </form>
</div>
