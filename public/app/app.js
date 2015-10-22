/**
 * Created by shane on 8/4/15.
 */
'use strict';

(function(){
    angular
        .module('cs', [
            'ui.router'
        ])
        .config(['$stateProvider', '$urlRouterProvider', configFunc
        ])
        .run([]);

    function configFunc($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'templates/pages/home.html'
            })
            .state('contact', {
                url: '/contact',
                templateUrl: 'templates/pages/contact.html'
            });
    }
})();