app.controller('AdminController',function($http,$scope,$rootScope,$location,AdminService){
	console.log('entering the admin controller')
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpasswprd:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};

	$scope.fetchKinsforAccess=function(){
		console.log('entering fetchall persons in controller')
		AdminService.fetchKinsforAccess()
		.then(
				function(response){
					$scope.kins=response.data;
					$rootScope.ap=$scope.kins;
					$location.path("/provideaccess");
				},
				function(error){
					console.log(error);
				}
		)
	}
})