<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="display" value="none"/>
<c:if test="${not empty registerOperationMessage}">
	<c:set var="display" value="block"/>
</c:if>

<div id="registrationBlock" class="lightbox" style="display:${display};">
	<div class="lb-wrap">
		<a href="#" class="close">x</a>
		<div class="lb-content">
			<sf:form name="registrationForm" method="POST" action="register" modelAttribute="user">
				<h1><spring:message code="registration.registration"/></h1>
				<div class="f-item">
					<label for="f_name"><spring:message code="registration.firstName"/></label>
					<sf:input type="text" id="f_name" name="firstName" path="firstName"/>
					<sf:errors path="firstName" cssClass="error-data"/>
				</div>
				<div class="f-item">
					<label for="l_name"><spring:message code="registration.lastName"/></label>
					<sf:input type="text" id="l_name" name="lastName" path="lastName"/>
					<sf:errors path="lastName" cssClass="error-data"/>
				</div>
				<div class="f-item">
					<label for="email"><spring:message code="registration.email"/></label>
					<sf:input type="text" id="email" name="email" path="email"/>
					<sf:errors path="email" cssClass="error-data"/>
				</div>
				<div class="f-item">
					<label for="login"><spring:message code="registration.login"/></label>
					<sf:input type="text" id="login" name="login" path="login"/>
					<sf:errors path="login" cssClass="error-data"/>
				</div>
				<div class="f-item">
					<label for="password"><spring:message code="registration.password"/></label>
					<sf:input type="password" id="password" name="password" path="password"/>
					<sf:errors path="password" cssClass="error-data"/>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
				<input type="submit" id="register" name="register" value="<spring:message code='registration.register'/>" class="gradient-button"/>
			</sf:form>
		</div>
	</div>
</div>

