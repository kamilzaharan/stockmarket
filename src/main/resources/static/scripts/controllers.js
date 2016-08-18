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

cont.controller('currenciesController', ['$scope', 'showCurrencies', 'showExchangeRate', 'getDate',
                             function ($scope, showCurrencies, showExchangeRate, getDate) {

                             //default amount
                             $scope.amount='100.00';

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

                               //to zwraca date
                               $scope.getDate = function () {
                               $scope.date = new Date();
                                }
                                $scope.getDate();

                                //to przelicza walute
                                $scope.calculate = function() {
                                    console.log($scope.selected);
                                    console.log($scope.selected2);
                                    console.log($scope.amount);
                                    //console.log($scope.exchangeRate);
                                    var first=1.00;
                                    var second=1.00;
                                    angular.forEach($scope.exchangeRate, function(value, key){
                                           // console.log($scope.selected);
                                            //console.log($scope.selected2);
                                            //console.log(value.code);
                                             if(value.code == $scope.selected){
                                              first=value.value;
                                              first = first.replace(/,/g, '.')
                                              first=parseFloat(first).toFixed(4);
                                             }
                                             if(value.code == $scope.selected2){
                                              second=value.value;
                                              second = second.replace(/,/g, '.')
                                              second=parseFloat(value.value).toFixed(4);
                                             }
                                             });
                                     console.log(first);
                                     console.log(second);
                                     console.log((parseFloat($scope.amount)*first)/second);
                                     $scope.kwota=parseFloat(Math.round((((parseFloat($scope.amount)*first)/second)) * 100) / 100).toFixed(2);

                                     console.log($scope.selected);
                                     console.log($scope.selected2);
                                     console.log($scope.kwota);


                                 }





                             }]);

cont.controller('companiesController', ['$scope', 'showCompanyList',
    function ($scope, showCompanyList) {

        //to znajdzie currency name, code i ID

        $scope.companies = function () {
            showCompanyList.create().$promise
                .then(function (response) {
                    $scope.companies=response;
                })
        };
        $scope.companies();

    }]);

cont.controller('graphCtrl', ['$scope', '$routeParams', 'getApproximation',
    function ($scope, $routeParams, getApproximation) {

        $scope.graphFun = function () {
            getApproximation.create({id: $routeParams.id}).$promise
                .then(function (response) {
                    $scope.approximation=response;

                    var data = $scope.approximation.map(function(d) {
                        return {
                            x: d.x,
                            y: d.y
                        };
                    });

                    var chart = fc.chart.cartesian(
                        d3.scale.linear(),
                        d3.scale.linear())
                        .yDomain(fc.util.extent().pad(0.2).fields(["y", "z"])(data))
                        .yLabel("Wartość akcji")
                        .yNice()
                        .yOrient("left")
                        .xDomain(fc.util.extent().fields(["x"])(data))
                        .xLabel("Czas [h]")

                        .chartLabel("Wartość akcji w ostatnich 30 dniach, albo jakiś inny tytuł wykresu")
                        .margin({left: 50, bottom: 20, top: 30});

                    var sinLine = fc.series.line()
                        .xValue(function(d) { return d.x; })
                        .yValue(function(d) { return d.y; });

                    chart.plotArea(sinLine);

                    d3.select("#chart")
                        .datum(data)
                        .call(chart);
                })
        };

        $scope.graphFun();
    }]);