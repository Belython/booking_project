/*global $, jQuery, alert*/

function getRUser() {
    "use strict";
    var fistName = $("#r_first_name").val(),
        lastName = $("#r_last_name").val(),
        userName = $("#r_userName").val(),
        email = $("#r_email").val(),
        password = $("#r_password").val(),
        user = {
            firstName: fistName,
            lastName: lastName,
            login: userName,
            email: email,
            password: password
        };
    return user;
}

function getCsrfHeader() {
    "use strict";
    return $("meta[name='_csrf_header']").attr("content");
}

function getCsrfToken() {
    "use strict";
    return $("meta[name='_csrf']").attr("content");
}
