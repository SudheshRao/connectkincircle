app.controller('KinController',function($http,$scope,$rootScope,$location,KinService){
	console.log('entering the controller')
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpasswprd:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};
	$scope.errore={code:'',message:''};
	
	//signup code
	$scope.save=function(){
		console.log('Signup controller enabled')
		KinService.saveKin($scope.kin).then(
		function(d){
			console.log(d.status);
			$location.path("/login");
		}	
		);
	}
	//login code
	$scope.submit=function(){
		console.log('login controller enabled')
	KinService.authenticate($scope.kin)
		.then(function(response){
			$scope.kin=response.data;
			$rootScope.currentUser=$scope.kin;
			$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;	    	 
			$location.path("/");},
			function(response){
				 $scope.errore=response.data;
				  $rootScope.mess=$scope.errore;
				  $location.path("/login");
			});

		}
	//logout code
	$rootScope.logout=function(){
		console.log('logout function');
		delete $rootScope.currentUser;		
		KinService.logout().then(function(response){
			console.log("logged out successfully..");
			$scope.message="Logged out Successfully";
			$location.path('/login');
			},
			
		function(response){
		console.log(response.status);
	})
	}
})