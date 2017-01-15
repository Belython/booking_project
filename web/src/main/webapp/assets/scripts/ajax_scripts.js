/*global $, jQuery, alert*/

function setCsrf() {
    "use strict";
    $(document).ajaxSend(function(e, xhr) {
        xhr.setRequestHeader(getCsrfHeader(), getCsrfToken());
    });
}

function addUser() {
    "use strict";
    setCsrf();
    $.ajax({
        data: getRUser(),
        dataType: "json",
        method: "POST",
        url: "users"
    }).fail(function(data) {
        if (console && console.log ) {
            console.log("Error data:", data.slice( 0, 100 ) );
        }
    });
}
