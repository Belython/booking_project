<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="registrationBlock" class="lightbox" style="display:none;">
	<div class="lb-wrap">
		<a href="#" class="close">x</a>
		<div class="lb-content">
			<form name="registrationForm" method="POST" action="register">
				<h1><spring:message code="registration.registration"/></h1>
				<div class="f-item">
					<label for="f_name"><spring:message code="registration.firstName"/></label>
					<input type="text" id="f_name" name="firstName"/>
				</div>
				<div class="f-item">
					<label for="l_name"><spring:message code="registration.lastName"/></label>
					<input type="text" id="l_name" name="lastName"/>
				</div>
				<div class="f-item">
					<label for="email"><spring:message code="registration.email"/></label>
					<input type="text" id="email" name="email"/>
				</div>
				<div class="f-item">
					<label for="login"><spring:message code="registration.login"/></label>
					<input type="text" id="login" name="login"/>
				</div>
				<div class="f-item">
					<label for="password"><spring:message code="registration.password"/></label>
					<input type="password" id="password"  name="password"/>
				</div>
				<div class="f-item checkbox">
					<input type="checkbox" id="newsletter" name="newsletter" />
					<label for="newsletter">Tell me about inspiring travel news, and exclusive discounts!</label>
				</div>
				<p>By clicking "Create Account" you confirm that you accept the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>. </p>
				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
				<input type="submit" id="register" name="register" value="<spring:message code='registration.register'/>" class="gradient-button"/>
			</form>
		</div>
	</div>
</div>

