angular
    .module('hotelsSearch')
    .controller('destinationsCtrl', function ($scope, Destinations, Hotels) {
        var vm = this;
        vm.getDestinations = function () {
            vm.possibleDestinations = Destinations.query({destination: vm.destination}, function () {
            });
        };
        vm.makeOrder = function () {
            vm.resp = Hotels.makeOrder({
                destination: vm.destination,
                checkInDate: $('#checkInDate').val(),
                checkOutDate: $('#checkOutDate').val(),
                totalRooms: vm.totalRooms,
                totalPersons: vm.totalPersons
            });
        };
    });