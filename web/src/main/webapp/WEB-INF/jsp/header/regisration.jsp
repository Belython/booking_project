<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="display" value="none"/>
<c:if test="${not empty registerOperationMessage}">
	<c:set var="display" value="block"/>
</c:if>

<script type="text/javascript" src="${context}/assets/scripts/angular_scripts.js"></script>

<div ng-app="myApp" ng-controller="myCtrl" id="registrationBlock" class="lightbox" style="display:${display};">
	<div class="lb-wrap">
		<a href="#" class="close">x</a>
		<div class="lb-content">
			<sf:form name="registrationForm" method="POST" action="users" modelAttribute="user">
				<h1><spring:message code="registration.registration"/></h1>
				<div class="f-item">
					<label for="r_first_name"><spring:message code="registration.firstName"/></label>
					<sf:input type="text" id="r_first_name" name="firstName" path="firstName"/>
					<sf:errors path="firstName" cssClass="error-data"/>
				</div>
				<div class="f-item">
					<label for="r_last_name"><spring:message code="registration.lastName"/></label>
					<sf:input type="text" id="r_last_name" name="lastName" path="lastName"/>
					<sf:errors path="lastName" cssClass="error-data"/>
				</div>
				<div class="f-item">
					<label for="r_email"><spring:message code="registration.email"/></label>
					<sf:input type="email" id="r_email" name="email" path="email"/>
					<sf:errors path="email" cssClass="error-data"/>
				</div>
				<div class="f-item">
					<label for="r_username"><spring:message code="registration.userName"/></label>
					<sf:input type="text" id="r_username" name="userName" path="userName"/>
					<sf:errors path="userName" cssClass="error-data"/>
				</div>
				<div class="f-item">
					<label for="r_password"><spring:message code="registration.password"/></label>
					<sf:input type="password" id="r_password" name="password" path="password"/>
					<sf:errors path="password" cssClass="error-data"/>
				</div>
				<%--<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
				<%--<input type="hidden" id="csrf_header" name="_csrf_header" value="${_csrf.headerName}" />--%>
				<%--<input type="submit" id="register" name="register" value="<spring:message code='registration.register'/>" class="gradient-button"/>--%>
				<input type="button" id="register" name="register"
					   value="<spring:message code='registration.register'/>" class="gradient-button"/>
			</sf:form>
		</div>
	</div>
</div>


