'use strict';

var myApp = angular.module('myApp', ['services', 'controllers', 'ngRoute'])

    .config(['$routeProvider', '$locationProvider',
             function ($routeProvider, $locationProvider) {
                 $routeProvider.when('/', {
                     controller: 'exampleCtrl',
                     templateUrl: 'example/example.html'
                 }).when('/wyniki', {
                         controller: 'mainCtrl',
                         templateUrl: 'wyniki.html'
                 }).when('/prognozy', {
                //     controller: 'prognozyCtrl',
                     templateUrl: '../prognozy/prognozy.html'
                 }).otherwise({
                                  redirectTo: '/'
                              });

                 $locationProvider.html5Mode({enabled: true, requireBase: false});
             }]);
