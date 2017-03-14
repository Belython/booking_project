<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="display" value="none"/>
<c:if test="${not empty restoreMessage}">
    <c:set var="display" value="block"/>
</c:if>
<div id="remindPasswordBlock" class="lightbox" style="display:${display};">
    <div class="lb-wrap">
        <a href="#" class="close">x</a>
        <div class="lb-content">
            <form id="remindPasswordForm" name="remindPasswordForm" method="POST" action="remindPassword">
                <h1><spring:message code="remindPassword.remindPassword"/></h1>
                <div class="f-item">
                    <label for="email"><spring:message code="user.email"/></label>
                    <input type="text" id="email" name="email"/>
                </div>
                <h4>${restoreMessage}</h4>
                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                <input type="submit" id="signIn" value="<spring:message code="header.submit"/>" class="gradient-button"/>
            </form>
        </div>
    </div>
</div>
