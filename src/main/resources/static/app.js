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
                 }).when('/graph/:id', {
                     controller: 'graphCtrl',
                     templateUrl: '/views/graph.html'
                 }).when('/companies/max', {
                     controller: 'maxCtrl',
                     templateUrl: '/views/companyMax.html'
                 }).when('/companyDetails/:id', {
                     controller: 'companyDetailsCtrl',
                     templateUrl: '/views/companyDetail.html'
                 }).when('/companies/min', {
                     controller: 'minCtrl',
                     templateUrl: '/views/companyMax.html'
                 }).when('/currencies', {
                         controller: 'currenciesController',
                         templateUrl: 'views/currencies.html'
                 }).when('/info', {
                     templateUrl: '/views/info.html'
                 }).when('/companies/:sortType', {
                     controller: 'companiesController',
                     templateUrl: '/views/companies.html'
                 }).otherwise({
              redirectTo: '/'
          });

                 $locationProvider.html5Mode({enabled: true, requireBase: false});
             }]);
