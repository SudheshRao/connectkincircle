var app = angular.module('myApp',['ngRoute','ngCookies']);

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
	.when('/landingpage',{
		controller:'KinController',
		templateUrl:'views/landingpage.html'
	})
	.when('/searchkin',{
		controller:'ConnectController',
		templateUrl:'views/searchkin.html'
	})
	.when('/pendingrequest',{
		controller:'ConnectController',
		templateUrl:'views/pendingrequest.html'
	})
	.when('/iuploadpicture',{
		templateUrl:'views/iuploadpicture.html'
	})
	.when('/uploadpicture',{
		templateUrl:'views/uploadpicture.html'
	})
	.when('/adminfunctions',{
		controller:'AdminController',
		templateUrl:'views/adminfunctions.html'
	})
		.when('/provideaccess',{
		controller:'AdminController',
		templateUrl:'views/provideaccess.html'
	})
	.when('/denyaccess',{
		controller:'AdminController',
		templateUrl:'views/denyaccess.html'
	})
	.when('/changerole',{
		controller:'AdminController',
		templateUrl:'views/changerole.html'
	})
	.when('/makeassistadmin',{
		controller:'AdminController',
		templateUrl:'views/makeassistadmin.html'
	})
	.when('/deletekin',{
		controller:'AdminController', 
		templateUrl:'views/deletekin.html' 
	})
	.when('/postjob',{
		controller:'JobController',
		templateUrl:'views/postjob.html' 
	})
	.when('/jobdetails',{
		controller:'JobController',
		templateUrl:'views/jobdetails.html' 
	})
	.when('/searchjob',{
		controller:'JobController', 
		templateUrl:'views/jobsearch.html' 
	})
	.when('/appliedjob',{
		controller:'JobController', 
		templateUrl:'views/appliedjob.html' 
	})
	.when('/appliedjobdetails',{
		controller:'JobController', 
		templateUrl:'views/appliedjobdetails.html' 
	})
	.when('/removejob',{
		controller:'JobController', 
		templateUrl:'views/removejob.html' 
	})
	.when('/', {
		templateUrl : 'views/home.html'
			
	})
})
	app.run(function($cookieStore,$rootScope,$location,KinService){  
		console.log('app.run');

	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get('currentUser')
		
		$rootScope.logout=function(){
		console.log('logout function');
		KinService.logout().then(function(response){
			console.log("logged out successfully..");
			delete $rootScope.currentUser;
			$cookieStore.remove('currentUser');
			$location.path('/login');
			},
			
		function(response){
		console.log(response.status);
	})
	}})
