/**
 * Created by shane on 10/22/15.
 */
'use strict';

(function() {
    angular
        .module('cs')
        .controller('HomeCtrl', ['$scope', '$log', HomeCtrl]);

    function HomeCtrl($scope, $log) {
        $log.log('In HomeCtrl');
    }
})();