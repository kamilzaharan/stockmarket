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
                 }).when('/networkConfiguration', {
                     // controller: 'networkConfigurationCtrl',
                     templateUrl: 'views/networkConfiguration.html'
                 }).when('/graph', {
                     controller: 'graphCtrl',
                     templateUrl: 'views/graph.html'
                 }).when('/currencies', {
                         controller: 'currenciesController',
                         templateUrl: 'views/currencies.html'
                 }).when('/companies', {
                     controller: 'companiesController',
                     templateUrl: 'views/companies.html'
                 }).otherwise({
              redirectTo: '/'
          });

                 $locationProvider.html5Mode({enabled: true, requireBase: false});
             }]);
