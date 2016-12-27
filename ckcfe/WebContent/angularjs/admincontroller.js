app.controller('AdminController',function($http,$scope,$rootScope,$route,$location,AdminService){
	console.log('entering the admin controller')
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpasswprd:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};
	$scope.badges={kins:'',stat:''};

	$scope.fetchKinsforAccess=function(){
		console.log('entering fetch kins for access controller')
		AdminService.fetchKinsforAccess()
		.then(
				function(response){
					$scope.kins=response.data;
					$rootScope.ap=$scope.kins;
					console.log($rootScope.ap);
					$location.path("/provideaccess");
				},
				function(error){
					console.log(error);
				}
		)
	}
	$scope.permit=function(kino){
		console.log('entering permit control');
		$scope.kin=kino;
		AdminService.permit($scope.kin);
		$scope.fetchKinsforAccess();
		$route.reload();
	}
	
	$scope.deny=function(kino){
		console.log('entering deny control');
		$scope.kin=kino;
		AdminService.deny($scope.kin);
		$scope.fetchKinsforAccess();
		$location.path("/provideaccess");
		$route.reload();
	}
	
	function fetchbadges(){
		console.log("fetching badges");
		AdminService.fetchbadges()
		.then(
				function(response){
					$scope.badges=response.data;
					$rootScope.badge=$scope.badges;
					console.log($rootScope.badge);
					})
	}
	fetchbadges();
})