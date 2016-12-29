app.controller('AdminController',function($http,$scope,$rootScope,$route,$location,AdminService){
	console.log('entering the admin controller')
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpasswprd:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};
	$scope.badges={kins:'',stat:'',statd:''};

	//fetch kins for access provision
	$scope.fetchKinsforAccess=function(){
		console.log('entering fetch kins for access in controller')
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
	
	//fetch kins to deny access provision
	$scope.fetchKinstoDenyAccess=function(){
		console.log('entering fetch kins to  deny access in controller')
		AdminService.fetchKinstoDenyAccess()
		.then(
				function(response){
					$scope.kins=response.data;
					$rootScope.ad=$scope.kins;
					$location.path("/denyaccess");
				},
				function(error){
					console.log(error);
				}
		)
	}
	
	//fetch kins to delete
	$scope.fetchKinstoDelete=function(){
		console.log('entering fetch kins to  delete in controller')
		AdminService.fetchKinstoDenyAccess()
		.then(
				function(response){
					$scope.kins=response.data;
					$rootScope.dk=$scope.kins;
					$location.path("/deletekin");
				},
				function(error){
					console.log(error);
				}
		)
	}
	
	//fetch kins to make kin assist admin
	$scope.fetchKinstoAssistAdmin=function(){
		console.log('entering fetch kins to make him assist admin in controller')
		AdminService.fetchKinstoAssistAdmin()
		.then(
				function(response){
					$scope.kins=response.data;
					$rootScope.aa=$scope.kins;
					console.log($rootScope.aa);
					$location.path("/makeassistadmin");
				},
				function(error){
					console.log(error);
				}
		)
	}
	
	//permit kins access
	$scope.permit=function(kino){
		console.log('entering permit kin.s access in controller');
		$scope.kin=kino;
		AdminService.permit($scope.kin).then( function(o){
		$scope.fetchKinsforAccess().then(function(p){
			$location.path("/provideaccess")
		})})
	}
	
	//deny kins access
	$scope.deny=function(kino){
		console.log('entering deny kin.s access in controller');
		$scope.kin=kino;
		AdminService.deny($scope.kin).then( function(d){
		$scope.fetchKinsforAccess().then(function(b){
			$location.path("/provideaccess")
		})})
		
	}
	
	//delete kin
	$scope.deletekin=function(id){
		console.log('entering delete kin in controller');
		AdminService.deletekin(id).then( function(d){
		$scope.fetchKinstoDelete();
		})
		
	}
	//deny temporarily kins access
	$scope.denytemp=function(kino){
		console.log('entering deny kin.s access temporarily in controller');
		$scope.kin=kino;
		AdminService.denytemp($scope.kin).then( function(d){
		$scope.fetchKinstoDenyAccess().then(function(b){
			$location.path("/denyaccess")
		})})
		
	}
	
	//make assist admin
	$scope.makeassistadmin=function(id){
		console.log('entering make assist admin in controller');
		AdminService.makeassistadmin(id).then( function(q){
		$scope.fetchKinstoAssistAdmin()})
	}
	
	
	//fetching badges for external info
	function fetchbadges(){
		console.log("fetching badges in controller");
		AdminService.fetchbadges()
		.then(
				function(response){
					$scope.badges=response.data;
					$rootScope.badge=$scope.badges;
					})
	}
	
	//refresh compensator
	if($location.path()=='/deletekin'){$scope.fetchKinstoDelete();}
	if($location.path()=='/provideaccess'){$scope.fetchKinsforAccess();}
	if($location.path()=='/denyaccess'){$scope.fetchKinstoDenyAccess();}
	if($location.path()=='/makeassistadmin'){$scope.fetchKinstoAssistAdmin();}
	fetchbadges();
})