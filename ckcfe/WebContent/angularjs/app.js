var app = angular.module('myApp',['ngRoute']);

app.config(function($routeProvider) {
	console.log("im inn");
	$routeProvider
	.when('/signup',{
		controller:'KinController',
		templateUrl:'views/signup.html'
	})
	.when('/login',{
		controller:'KinController',
		templateUrl:'views/login.html'
	})
	.when('/', {
		templateUrl : 'views/home.html'
			
	})
})