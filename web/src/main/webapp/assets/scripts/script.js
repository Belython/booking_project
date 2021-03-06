function main() {
    
    $(".payBillBtn").click(function (event) {
        payBill(event)
    });

    $("#destination").change(function (event) {
        getPosstibleDestinations(event);
    });

    $("#loginRef").click(function (event) {
        login();
    });

    $("#registerRef").click(function (event) {
        registration();
    });

    $("#forgotPasswordRef").click(function (event) {
        forgotPassword();
    });

    function payBill(event) {
        var button = event.target;
        var tableRow = $(button).parents().eq(1);
        var rowInputNodes = tableRow.find("input, select");
        var redactorForm = $(".redactorForm").has(tableRow);
        var command = redactorForm.children("[name='command']").serialize();
        var subCommand = redactorForm.children("[name='subCommand']").serialize();
        var rowParameters = rowInputNodes.serialize();
        var isAjaxRequest = "isAjaxRequest=true";
        var url = "controller?" + command + "&" + subCommand + "&" + isAjaxRequest + "&" + rowParameters;
        $.get(url, function (data, status) {
            $("#operationMessage").text(data);
        });
    }

    function getPosstibleDestinations(event) {
        var destination = $("#destination");
        var destinationName = destination.attr("name");
        var destinantonValue = destination.val();
        var destinationParameter = destinationName + "=" + destinantonValue;
        var context = "/booking/";
        var action = "get_destinations";
        var url = context + action + "?" + destinationParameter;
        $.get(url, function (data, status) {
            var possibleDestinations = $(data);
            var destinations = $("#destinations");
            destinations.empty();
            destinations.append(possibleDestinations);
        }, "html");
    }

    // function getPosstibleDestinations(event) {
    //     var destination = $("#destination");
    //     var destinationName = destination.attr("name");
    //     var destinantonValue = destination.val();
    //     var token = "{{csrf_token()}}";
    //     var action = "get_destinations";
    //     $.post(action, {
    //         destination: destinantonValue
    //     },
    //     function (data, status) {
    //         var possibleDestinations = $(data);
    //         var destinations = $("#destinations");
    //         destinations.empty();
    //         destinations.append(possibleDestinations);
    //     }, "html");
    // }

    function login() {
        $("#loginBlock").css("display", "block");
    }
    
    function registration() {
        $("#loginBlock").css("display", "none");
        $("#registrationBlock").css("display", "block");
    }

    function forgotPassword() {
        $("#loginBlock").css("display", "none");
        $("#remindPasswordBlock").css("display", "block");
    }
}

$("document").ready(main);






