'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);

//Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/user', {
        templateUrl: '/login.html',
        controller: LoginController
    }).when('/cars', {
        templateUrl: 'html/cars/layout.html',
        controller: CarController
    }).when('/trains', {
        url: '/trains',
        templateUrl: 'html/trains/layout.html',
        controller: TrainController
    }).when('/railwaystations', {
        templateUrl: 'html/railwaystations/layout.html',
        controller: RailwayStationController
    });
    $routeProvider.otherwise({redirectTo: '/cars'});
}]);
