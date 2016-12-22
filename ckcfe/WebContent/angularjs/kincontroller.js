app.controller('KinController',function($scope,$location,KinService){
	console.log('entering the controller')
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpasswprd:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''}
	$scope.message;
	//signup code
	$scope.save=function(){
		console.log('Signup controller enabled')
		KinService.saveKin($scope.kin).then(
		function(d){
			console.log(d.status)
		}	,
		function(d){
			console.log(d.status)
		}
		);
	}
	//login code
	$scope.submit=function(){
		console.log('login controller enabled')
	KinService.authenticate($scope.kin)
		.then(function(response){
			if(response.status==401)
				{
				  $scope.message="Invalid Username and Password";
				  $location.path("/login");
				}
			else{
				$scope.kin=response.data;
				$location.path("/");
			}
		})
	}
})