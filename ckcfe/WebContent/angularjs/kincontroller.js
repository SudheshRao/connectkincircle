app.controller('KinController',function($http,$scope,$rootScope,$location,$cookieStore,KinService){
	console.log('entering the controller')
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpasswprd:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};
	$scope.errore={code:'',message:''};
	$scope.mess;
	

	//signup code
	$scope.save=function(){
		KinService.saveKin($scope.kin).then(
		function(d){
			console.log('kin saved')
			console.log(d.status);
			$location.path("/iuploadpicture");
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
			$cookieStore.put('currentUser',$rootScope.currentUser);
			$location.path("/");},
			function(response){
				 $scope.errore=response.data;
				  $scope.mess=$scope.errore;
				  $location.path("/login");
			});

		}
})
