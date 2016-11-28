<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="lightbox" style="display:block;">
	<div class="lb-wrap">
		<a href="#" class="close">x</a>
		<div class="lb-content">
			<form name="registrationForm" method="POST" action="controller">
				<h1>${registration_registration}</h1>
				<div class="f-item">
					<label for="f_name">${registration_firstName}</label>
					<input type="text" id="f_name" name="firstName"/>
				</div>
				<div class="f-item">
					<label for="l_name">${registration_lastName}</label>
					<input type="text" id="l_name" name="lastName"/>
				</div>
				<div class="f-item">
					<label for="email">${registration_email}</label>
					<input type="text" id="email" name="email"/>
				</div>
				<div class="f-item">
					<label for="login">${registration_login}</label>
					<input type="text" id="login" name="login"/>
				</div>
				<div class="f-item">
					<label for="password">${registration_password}</label>
					<input type="password" id="password"  name="password"/>
				</div>
				<div class="f-item checkbox">
					<input type="checkbox" id="newsletter" name="newsletter" />
					<label for="newsletter">Tell me about inspiring travel news, and exclusive discounts!</label>
				</div>
				<p>By clicking "Create Account" you confirm that you accept the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>. </p>
				<input type="submit" id="register" name="register" value="${registration_register}" class="gradient-button"/>
			</form>
		</div>
		${errorUserExists}
	</div>
</div>

