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