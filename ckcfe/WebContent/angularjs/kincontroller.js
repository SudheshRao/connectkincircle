app.controller('KinController',function($http,$scope,$rootScope,$location,$cookieStore,KinService){
	
	console.log('entering Kin controller');
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpassword:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};
	$scope.errore={code:'',message:''};

	//signup code
	$scope.signup=function(){console.log('signup controller invoked')	
		KinService.signup($scope.kin)
		.then(function(response){	
			console.log('signup successfull '+response.status);
			$location.path("/iuploadpicture");},//route to upload dp
			function(response){
				$scope.errore=response.data;
				$location.path("/signup");//route to signup page in case of error
			});
		}
	
	//login code
	$scope.login=function(){console.log('login controller invoked')
		KinService.login($scope.kin)
		.then(function(response){
			console.log('logged-in '+response.status);
			$scope.kin=response.data;
			$rootScope.currentUser=$scope.kin;//accessable throughout application
			$cookieStore.put('currentUser',$rootScope.currentUser);//storing it in cookie 
			$location.path("/landingpage");},//route to landingpage
			function(response){
				$scope.errore=response.data;
				$location.path("/login");//route to login page in case of error
			});
		}
})
