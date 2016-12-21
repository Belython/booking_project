<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="display" value="none"/>
<c:if test="${not empty operationMessage}">
    <c:set var="display" value="block"/>
</c:if>
<div id="messageBox" class="lightbox" style="display:${display};">
    <div class="lb-wrap">
        <a href="#" class="close">x</a>
        <div class="lb-content">
            <h1>${operationMessage}</h1>
        </div>
    </div>
</div>

