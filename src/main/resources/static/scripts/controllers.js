'use strict';

var cont = angular.module('controllers', ['services']);

cont.controller('exampleCtrl', ['$scope', 'companyFactory',
                             function ($scope, companyFactory) {
                                 $scope.addCompany = function () {
                                     companyFactory.create($scope.company).$promise
                                         .then(function (response) {
                                             if (response.status == 200) {
                                                 alert("Dodałem firmę " + $scope.companySymbol)
                                             } else {
                                                 console.log(response.statusCode);
                                                 alert("nie dostalem zwrotu 200, ale o dziwo dzialam");
                                             }
                                         });
                                 };
                                 
                                 $scope.showShit = function () {
                                     showComapnies.create().$promise
                                         .then(function (response) {
                                             
                                         })
                                 }
                             }]);

cont.controller('currenciesController', ['$scope', 'showCurrencies', 'showExchangeRate',
                             function ($scope, showCurrencies, showExchangeRate) {

                             //to znajdzie currency name, code i ID

                                 $scope.currenciesFun = function () {
                                     showCurrencies.create().$promise
                                         .then(function (response) {
                                          $scope.currencies=response;
                                         })
                                 }
                                 $scope.currenciesFun();



                             //to znajdzie currency name i value
                                 $scope.exchangeRate = function () {
                                      showExchangeRate.create().$promise
                                          .then(function (response) {
                                           $scope.exchangeRate=response;
                                          })
                                  }
                                  $scope.exchangeRate();



                             }]);