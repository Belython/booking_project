<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="possibleDestination" items="${possibleDestinations}">
    <c:choose>
        <c:when test="${possibleDestination.hotelName eq 'any'}">
            <c:set var="location" value="${possibleDestination.location}"/>
            <option value="${location.country}, ${location.city}"/>
        </c:when>
        <c:otherwise>
            <c:set var="location" value="${possibleDestination.location}"/>
            <option value="${location.country}, ${location.city}, ${possibleDestination.hotelName}"/>
        </c:otherwise>
    </c:choose>
</c:forEach>
