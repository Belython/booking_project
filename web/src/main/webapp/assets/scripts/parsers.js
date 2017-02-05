/*global $, jQuery, alert*/

function getRUser() {
    "use strict";
    var fistName = $("#r_first_name").val(),
        lastName = $("#r_last_name").val(),
        userName = $("#r_user_name").val(),
        email = $("#r_email").val(),
        password = $("#r_password").val(),
        user = {
            firstName: fistName,
            lastName: lastName,
            userName: userName,
            email: email,
            password: password
        };
    return user;
}

function parseOrder() {
    "use strict";
    var destination = $("#destination").val(),
        checkInDate = $("#checkInDate").val(),
        checkOutDate = $("#checkOutDate").val(),
        totalRooms = $("#totalRooms").val(),
        totalPersons = $("#totalPersons").val(),
        order = {
            destination: destination,
            checkInDate: checkInDate,
            checkOutDate: checkOutDate,
            totalRooms: totalRooms,
            totalPersons: totalPersons
        };
    return order;
}

function getCsrfHeader() {
    "use strict";
    return $("meta[name='_csrf_header']").attr("content");
}

function getCsrfToken() {
    "use strict";
    return $("meta[name='_csrf']").attr("content");
}
