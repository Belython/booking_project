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
    })
        .done(function (data, dataType, request) {
            var responseStatus = request.getResponseHeader("Response-Status");
            switch (responseStatus) {
                case "validation error": {
                    refreshChildErrorFields($("#registrationForm"));
                    for (let fieldError of data) {
                        var fieldName = fieldError.field,
                            errorMessage = fieldError.defaultMessage;
                        $("#r_" + fieldName + "_error")
                            .removeClass("display-none")
                            .addClass("display-inline")
                            .empty()
                            .append(errorMessage);
                    }
                    break;
                }
                case "Successful created": {
                    $("#registrationForm")
                        .find(".error-data")
                        .removeClass("display-inline")
                        .addClass("display-none");
                    alert(data);
                }

            }
        })
        .fail(function(data) {
        if (console && console.log ) {
            console.log("Error data:", data.slice( 0, 100 ) );
        }
    });
}

function getPosstibleDestinations(event) {
    "use strict";
    var destinantonValue = $("#destination").val();
    $.ajax({
        data: {
            destination: destinantonValue
        },
        dataType: "json",
        method: "GET",
        url: "Destinations"
    })
        .done(function (data) {
            var destinations = $("#destinations");
            destinations.empty();
            for (let destination of data) {
                var value ="\"" + destination.location.country + ", " + destination.location.city + ", " + destination.hotelName + "\"",
                    option = "<option value=" + value + "\/>";
                destinations.append(option);
            }
            $("#destination").focus();
        });
}

function getHotels() {
    "use strict";
    var destinantonValue = $("#destination").val();
    $.ajax({
        data: parseOrder(),
        dataType: "html",
        method: "GET",
        url: "hotels"
    })
        .done(function (data) {
            document.open();
            document.write(data);
            document.close();
        });
}

function refreshChildErrorFields(parent) {
    "use strict";
    $(parent)
        .find(".error-data")
        .removeClass("display-inline")
        .addClass("display-none")
        .empty();
    return parent;
}
