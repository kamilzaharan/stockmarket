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
                             //default calculated value
                             $scope.calculatedValue='0.00';

                             // currency name, code and ID
                             $scope.currenciesFun = function () {
                                 showCurrencies.create().$promise
                                     .then(function (response) {
                                      $scope.currencies=response;
                                     })
                             }
                             $scope.currenciesFun();

                             //currency name and value
                             $scope.exchangeRate = function () {
                                  showExchangeRate.create().$promise
                                      .then(function (response) {
                                       $scope.exchangeRate=response;
                                      })
                              }
                              $scope.exchangeRate();

                              //return date
                              $scope.getLastDate = function () {
                                    getDate.create().$promise
                                        .then(function (response) {
                                         $scope.getLastDate=response;
                                        })
                                }
                              $scope.getLastDate();

                              //calculate value
                              $scope.calculate = function() {
                              var first=1.00;
                              var second=1.00;
                              angular.forEach($scope.exchangeRate, function(value, key){
                                     if(value.code == $scope.selected){
                                         if(typeof($scope.selected2)!='undefined'){
                                            first=value.value;
                                            first = first.replace(/,/g, '.')
                                            first=parseFloat(first).toFixed(4);
                                         }
                                     }
                                         if(value.code == $scope.selected2){
                                             if(typeof($scope.selected)!='undefined'){
                                                second=value.value;
                                                second = second.replace(/,/g, '.')
                                                second=parseFloat(second).toFixed(4);
                                              }
                                         }
                                     });
                                     $scope.calculatedValue='0.00';
                                     if(typeof($scope.selected)!='undefined' && typeof($scope.selected2)!='undefined')
                                     $scope.calculatedValue=parseFloat(Math.round((((parseFloat($scope.amount)*first)/second)) * 100) / 100).toFixed(2);
                                     if(typeof($scope.selected)=='PLN' && typeof($scope.selected2)!='undefined')
                                     $scope.calculatedValue= parseFloat($scope.amount/second).toFixed(2);
                                     if(typeof($scope.selected2)=='PLN' && typeof($scope.selected)!='undefined')
                                     $scope.calculatedValue= parseFloat($scope.amount*first).toFixed(2);
                                     if(typeof($scope.selected2)=='PLN' && typeof($scope.selected)=='PLN')
                                     $scope.calculatedValue= parseFloat($scope.amount);
                                 }
     }]);

cont.controller('currenciesChartCtrl', ['$scope', 'showExchangeRate', 'showCurrencyChartData',
                             function ($scope, showExchangeRate, showCurrencyChartData) {

          $scope.exchangeRate = function () {
               showExchangeRate.create().$promise
                   .then(function (response) {
                    $scope.exchangeRate=response;
                   })
           }
           $scope.exchangeRate();

           $scope.showChart = function (){
                $scope.exchangeRateChart = function () {
                    var i = 0;
                    angular.forEach($scope.exchangeRate, function(value, key){
                        i++;
                        if(value.code == $scope.selected3){
                        showCurrencyChartData.create({id: i}).$promise
                              .then(function (response) {
                               $scope.exchangeRateChart=response;
                               $scope.exchangeRateChartForTable=response;
                               
                              var data = $scope.exchangeRateChart.map(function(d) {
                                 d.y=(d.y).replace(/,/g, '.');
                                 d.x= d3.time.format("%Y-%m-%d").parse(d.x);
                                 return {
                                     x: d.x,
                                     y: Math.round((d.y)*10000)/10000
                                 };
                             });

                             var chart = fc.chart.cartesian(
                                fc.scale.dateTime().discontinuityProvider(fc.scale.discontinuity.skipWeekends()),
                                d3.scale.linear())
                                .margin({bottom: 50, right: 70})
                                .xDomain(fc.util.extent().fields(['x'])(data))
                                .xLabel("Data")
                                .yDomain(fc.util.extent().pad(0.3).fields(['y'])(data))
                                .yLabel("Wartość waluty");

                            var series = fc.series.bar()
                               .xValue(function(d) { return d.x; })
                               .yValue(function(d) { return d.y; });

                            chart.plotArea(series);

                            d3.select("#chart")
                                 .datum(data)
                                 .call(chart);
                                 })
                             }
                         })
                  }
              $scope.exchangeRateChart();
          }
    }]);



cont.controller('companiesController', ['$scope', '$routeParams', 'showCompanyList',
    function ($scope, $routeParams, showCompanyList) {

        $scope.companies = function () {
            showCompanyList.create({sortType: $routeParams.sortType}).$promise
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
                        .margin({bottom: 30, right: 60})
                        .yDomain(fc.util.extent().pad(0.2).fields(["y", "z"])(data))
                        .yLabel("Wartość akcji")
                        .yNice()
                        .yOrient("left")
                        .xDomain(fc.util.extent().fields(["x"])(data))
                        .xLabel("Czas [h]")

                        .chartLabel("Aproksymacja akcji")
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

cont.controller('companyDetailsCtrl', ['$scope', '$routeParams', 'getCompanyDetails', 'stats',
    function ($scope, $routeParams, getCompanyDetails, stats) {

        $scope.graphFun = function () {
            getCompanyDetails.create({id: $routeParams.id}).$promise
                .then(function (response) {
                    $scope.details=response;

                    var data = $scope.details.map(function(d) {
                        return {
                            x: d.x,
                            y: d.y
                        };
                    });

                    var chart = fc.chart.cartesian(
                        d3.scale.linear(),
                        d3.scale.linear())
                        .margin({bottom: 30, right: 60})
                        .yDomain(fc.util.extent().pad(0.2).fields(["y", "z"])(data))
                        .yLabel("Wartość akcji")
                        .yNice()
                        .yOrient("left")
                        .xDomain(fc.util.extent().fields(["x"])(data))
                        .xLabel("Czas")

                        .chartLabel("Wartości akcji")
                        .margin({left: 50, bottom: 20, top: 30});

                    var sinLine = fc.series.line()
                        .xValue(function(d) { return d.x; })
                        .yValue(function(d) { return d.y; });

                    chart.plotArea(sinLine);

                    d3.select("#chart")
                        .datum(data)
                        .call(chart);

                    $scope.statistics = function() {
                        stats.create({id: $routeParams.id}).$promise
                            .then(function (response) {
                                $scope.statistics = response;

                                $scope.avg = $scope.statistics.average;
                                $scope.variance = $scope.statistics.variance;
                                $scope.standardDeviation = $scope.statistics.standardDeviation;
                                $scope.median = $scope.statistics.median;

                            })
                    };
                    $scope.statistics();
                })
        };

        $scope.graphFun();
    }]);

cont.controller('maxCtrl', ['$scope', 'maxIncrease',
    function ($scope, maxIncrease) {

        $scope.graphFun = function () {
            maxIncrease.create().$promise
                .then(function (response) {
                    $scope.stockValues=response;

                    var data = $scope.stockValues.map(function(d) {
                        return {
                            x: d.x,
                            y: d.y
                        };
                    });

                    var chart = fc.chart.cartesian(
                        d3.scale.linear(),
                        d3.scale.linear())
                        .margin({bottom: 30, right: 60})
                        .yDomain(fc.util.extent().pad(0.2).fields(["y", "z"])(data))
                        .yLabel("Wartość akcji")
                        .yNice()
                        .yOrient("left")
                        .xDomain(fc.util.extent().fields(["x"])(data))
                        .xLabel("Czas")

                        .chartLabel("Wartości akcji")
                        .margin({left: 80, bottom: 20, top: 30});

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

cont.controller('minCtrl', ['$scope', 'maxDecrease',
    function ($scope, maxDecrease) {

        $scope.graphFun = function () {
            maxDecrease.create().$promise
                .then(function (response) {
                    $scope.stockValues=response;

                    var data = $scope.stockValues.map(function(d) {
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
                        .xLabel("Czas")

                        .chartLabel("Wartość akcji w ostatnich 30 dniach")
                        .margin({left: 80, bottom: 20, top: 30});

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

cont.controller('newCompanies', ['$scope', 'addCompanies',
    function ($scope, addCompanies) {

        $scope.addCompanies = function () {
            addCompanies.create().$promise
                .then(function (response) {
                    $scope.companies=response;
                })
        };
        $scope.addCompanies();
    }]);
