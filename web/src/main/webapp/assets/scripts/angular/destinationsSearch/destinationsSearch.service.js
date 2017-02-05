angular
    .module('hotelsSearch')
    .factory('Destinations', ['$resource',
        function ($resource) {
            "use strict";
            return $resource('destinations', {},
                {
                    query: {
                        method: 'GET',
                        params: {destination: '@destination'},
                        isArray: true
                        // responseType: 'html'
                    }
                });
        }
    ]);