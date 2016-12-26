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
	.when('/adminfunctions',{
		controller:'AdminController',
		templateUrl:'views/adminfunctions.html'
	})
		.when('/provideaccess',{
		controller:'AdminController',
		templateUrl:'views/provideaccess.html'
	})
	.when('/', {
		templateUrl : 'views/home.html'
			
	})
})