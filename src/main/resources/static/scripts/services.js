'use strict';

var services = angular.module('services', ['ngResource']);

services.factory('companyFactory', function ($resource) {
    return $resource('http://localhost:8080/addCompany', {}, {
        create: {method: 'POST'}
    })
});

services.factory('showComapnies', function ($resource) {
    return $resource('http://localhost:8080/getAllCompanies', {}, {
        create: {method: 'GET'}
    })
});

services.factory('companies', function () {
    var companies;
    companies = {};
    
    companies.list = [];
    companies.add = function (company) {
        
    }
});

services.factory('showCurrencies', function ($resource) {
    return $resource('http://localhost:8080/getAllCurrencies', {}, {
        create: {method: 'GET', isArray: true}
    })
});

services.factory('showExchangeRate', function ($resource) {
    return $resource('http://localhost:8080/getExchangeRate', {}, {
        create: {method: 'GET', isArray: true}
    })
});

services.factory('showCurrencyChartData', function ($resource) {
    return $resource('http://localhost:8080/getCurrencyChartData/:id', {}, {
        create: {method: 'GET', isArray: true}
    })
});

services.factory('getDate', function ($resource) {
    return $resource('http://localhost:8080/getExchangeRateDate', {}, {
        create: {method: 'GET', isArray: false}
    })
});

services.factory('showCompanyList', function ($resource) {
    return $resource('http://localhost:8080/companiesList/:sortType', {}, {
        create: {method: 'GET', params: {sortType: '@sortType'}, isArray: true}
    })
});

services.factory('getApproximation', function ($resource) {
    return $resource('http://localhost:8080/getApproximation/:id', {}, {
        create: {method: 'GET', params: {id: '@id'}, isArray: true}
    })
});

services.factory('getCompanyDetails', function ($resource) {
    return $resource('http://localhost:8080/companyDetails/:id', {}, {
        create: {method: 'GET', params: {id: '@id'}, isArray: true}
    })
});

services.factory('stats', function ($resource) {
    return $resource('http://localhost:8080/companyDetails/:id/stats', {}, {
        create: {method: 'GET', params: {id: '@id'}, isArray: false}
    })
});

services.factory('maxIncrease', function ($resource) {
    return $resource('http://localhost:8080/companies/max', {}, {
        create: {method: 'GET', isArray: true}
    })
});

services.factory('maxDecrease', function ($resource) {
    return $resource('http://localhost:8080/companies/min', {}, {
        create: {method: 'GET', isArray: true}
    })
});

services.factory('addCompanies', function ($resource) {
    return $resource('http://localhost:8080/newCompany', {}, {
        create: {method: 'GET', isArray: false}
    })
});

