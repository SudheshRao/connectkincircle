var app = angular.module('myApp',['ngRoute','ngCookies']);

app.config(function($routeProvider) {
	console.log("configuring...");
	
	$routeProvider
	.when('/', {templateUrl : 'views/home.html'})//home page route
	
	.when('/calendar', {controller:'CalendarController',templateUrl : 'views/calendar.html'})//home page route
		
	.when('/signup',{controller:'KinController',templateUrl:'views/signup.html'})//signup page route
	
	.when('/login',{controller:'KinController',templateUrl:'views/login.html'})//login page route
	
	.when('/landingpage',{controller:'KIController',templateUrl:'views/landingpage.html'})//kin info page route
	
	.when('/searchkin',{controller:'ConnectController',templateUrl:'views/searchkin.html'})//search for kin route
	
	.when('/kinconnects',{controller:'ConnectController',templateUrl:'views/kinconnects.html'})//kin connected page route
	
	.when('/pendingrequest',{controller:'ConnectController',templateUrl:'views/pendingrequest.html'})//pendin request page route
	
	.when('/iuploadpicture',{templateUrl:'views/iuploadpicture.html'})//initial dp upload route
	
	.when('/uploadpicture',{templateUrl:'views/uploadpicture.html'})//change dp route
		
	.when('/searchjob',{controller:'JobController',templateUrl:'views/jobsearch.html'})//job search page route
	
	.when('/appliedjob',{controller:'JobController',templateUrl:'views/appliedjob.html'})//list of applied job page route

	.when('/blogDetail/:id',{controller:'BlogDetailController',templateUrl:'views/blogdetails.html'})

	.when('/blog',{controller:'BlogController',templateUrl:'views/blog.html'})//blog page route
	
	.when('/events',{controller:'EventController',templateUrl:'views/event.html'})//event page route
	
	.when('/chat',{controller:'ChatController',templateUrl:'views/chat.html'})//event page route
	
	//Admin Functions routes 
	
	.when('/adminfunctions',{controller:'AdminController',templateUrl:'views/adminfunctions.html'})//admin functions page route 
	
	.when('/provideaccess',{controller:'AdminController',templateUrl:'views/provideaccess.html'})//access provision page route
	
	.when('/denyaccess',{controller:'AdminController',templateUrl:'views/denyaccess.html'})//deny access page route
	
	.when('/changerole',{controller:'AdminController',templateUrl:'views/changerole.html'})//change role page route
	
	.when('/makeassistadmin',{controller:'AdminController',templateUrl:'views/makeassistadmin.html'})//make assist admin page route
	
	.when('/deletekin',{controller:'AdminController', templateUrl:'views/deletekin.html'})//delete kin page route
	
	.when('/postjob',{controller:'JobController',templateUrl:'views/postjob.html'})//post job form page route
	
	.when('/jobdetails',{controller:'JobController',templateUrl:'views/jobdetails.html'})//job details page route
	
	.when('/appliedjobdetails',{controller:'JobController',templateUrl:'views/appliedjobdetails.html'})//job details including applied kin page route
	
	.when('/removejob',{controller:'JobController',templateUrl:'views/removejob.html'})// remove job page route
	
})

	app.run(function($cookieStore,$rootScope,$location,KinService,ChatService){  
		console.log('app.run');

	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get('currentUser')
		$rootScope.prs=$cookieStore.get('pendingrequest');
		$rootScope.notify=$cookieStore.get('notifi');
		
		$rootScope.messages = [];
	    $rootScope.message = "";
	    $rootScope.max = 140;
	    
	    $rootScope.addMessage = function() {
	      ChatService.send($rootScope.currentUser.name + " : " + $rootScope.message);
	      $rootScope.message = "";
	    };
	    
	    ChatService.receive().then(null, null, function(message) {
	      $rootScope.messages.push(message);
	    });
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
