'use strict';

var myApp = angular.module('myApp', ['services', 'controllers', 'ngRoute'])


    .config(['$routeProvider', '$locationProvider',
             function ($routeProvider, $locationProvider) {
                 $routeProvider.when('/users', {
                    templateUrl: '/views/users.html',
                    controller: 'usersController'
                }).when('/wyniki', {
                         controller: 'mainCtrl',
                         templateUrl: 'wyniki.html'
                 }).otherwise({
                                  redirectTo: '/'
                              });

                 $locationProvider.html5Mode({enabled: true, requireBase: false});
             }]);
