<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="display" value="none"/>
<c:if test="${not empty loginOperationMessage}">
    <c:set var="display" value="block"/>
</c:if>
<div id="loginBlock" class="lightbox" style="display:${display};">
    <div class="lb-wrap">
        <a href="#" class="close">x</a>
        <div class="lb-content">
            <sf:form id="loginForm" name="loginForm" method="POST" action="login" modelAttribute="user">
                <h1><spring:message code="header.loginForm"/></h1>
                <div class="f-item">
                    <label for="user_name"><spring:message code="header.userName"/></label>
                    <sf:input type="text" id="user_name" name="userName" path="userName" autofocus="autofocus" required="required"/>

                </div>
                <div class="f-item">
                    <label for="password"><spring:message code="header.password"/></label>
                    <sf:input type="password" id="password" name="password" path="password" required="required"/>
                    <c:if test="${not empty loginOperationMessage}">
                        <h3 class="error-data"><spring:message code="${loginOperationMessage}"/></h3>
                    </c:if>
                </div>
                <div class="f-item checkbox">
                    <input type="checkbox" id="remember_me" name="checkbox" />
                    <label for="remember_me"><spring:message code="header.rememberMe"/></label>
                </div>
                <p>
                    <a id="forgotPasswordRef" href="#" title="Forgot password?">
                        <spring:message code="header.forgotPassword"/>
                    </a><br/>
                    <spring:message code="header.dontHaveAccount"/>
                    <a id="registerRef" href="#" title="Sign up">
                        <spring:message code="header.register"/>
                    </a>
                </p>
                <%--<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />--%>
                <input type="submit" id="signIn" value="<spring:message code="header.signIn"/>" class="gradient-button"/>
            </sf:form>
        </div>
    </div>
</div>

