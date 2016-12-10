<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="display" value="none"/>
<spring:hasBindErrors name="user">
    <c:set var="display" value="block"/>
</spring:hasBindErrors>

<div id="loginBlock" class="lightbox" style="display:${display};">
    <div class="lb-wrap">
        <a href="#" class="close">x</a>
        <div class="lb-content">
            <sf:form id="loginForm" name="loginForm" method="POST" action="login" modelAttribute="user">
                <h1>Log in</h1>
                <div class="f-item">
                    <label for="login">${header_login}</label>
                    <sf:input type="text" id="login" name="login" path="login" autofocus="autofocus" required="required"/>
                    <sf:errors path="login" cssClass="error-validation"/>
                </div>
                <div class="f-item">
                    <label for="password">${header_password}</label>
                    <sf:input type="password" id="password" name="password" path="password" required="required"/>
                    <sf:errors path="password" cssClass="error-validation"/>
                </div>
                <div class="f-item checkbox">
                    <input type="checkbox" id="remember_me" name="checkbox" />
                    <label for="remember_me">Remember me next time</label>
                </div>
                <p>
                    <a href="controller?command=goToRemindPassword" title="Forgot password?">
                        ${header_forgotPassword}
                    </a><br/>
                    Dont have an account yet?
                    <a id="registerRef" href="#" title="Sign up">
                        ${header_register}
                    </a>
                </p>
                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                <input type="submit" id="signIn" value="${header_signIn}" class="gradient-button"/>
            </sf:form>
        </div>
    </div>
</div>

