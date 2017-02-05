angular
    .module('hotelsSearch')
    .factory('Hotels', ['$resource',
        function ($resource) {
            "use strict";
            return $resource('hotels', {},
                {
                    makeOrder: {
                        method: 'GET',
                        params: {
                            destination: '@destination',
                            checkInDate: '@checkInDate',
                            checkOutDate: '@checkOutDate',
                            totalRooms: '@totalRooms',
                            totalPersons: '@totalPersons'
                        }
                    }
                });
        }
    ]);