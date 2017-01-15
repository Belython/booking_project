var app = angular.module('myApp', []);

angular.module('restModule').factory('Register', [$resource,
    function ($resource) {
        var fistName = $("#first_name").val();
        var lastName = $("#last_name").val();
        var userName = $("#login1").val();
        var email = $("#email").val();
        var password = $("#password1").val();
        var user = {
            firstName: fistName,
            lastName: lastName,
            login: userName,
            email: email,
            password: password
        };
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        return $resource('users', {}, {
            query: {
                method: 'POST',
                params: user,
                headers: {
                    '_csrf': token,
                    '_csrf_header': header
                },
                responseType: "json"
            }
        });
    }
]);


app.controller('Register', function($scope, Register) {
    $scope.some = function() {
        $scope.mat = Register.query;
    };
});
