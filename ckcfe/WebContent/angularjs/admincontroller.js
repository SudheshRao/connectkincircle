app.controller('AdminController',function($http,$scope,$rootScope,$route,$location,AdminService){
	console.log('entering the admin controller')
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpasswprd:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};
	$scope.badges={kins:'',stat:'',statd:''};
	$scope.changedrole;

	//fetch kins for access provision
	$scope.fetchKinsforAccess=function(){
		console.log('fetch kin for access invoked');
		AdminService.fetchKinsforAccess()
		.then(
				function(response){
					$scope.kins=response.data;
					$location.path("/provideaccess");
				},
				function(error){
					console.log(error);
				});
	}
	
	//fetch kins to deny access provision
	$scope.fetchKinstoDenyAccess=function(){
		console.log('fetch kins to deny access invoked');
		AdminService.fetchKinstoDenyAccess()
		.then(
				function(response){
					$scope.kins=response.data;
					$location.path("/denyaccess");
				},
				function(error){
					console.log(error);
				});
	}
	
	//fetch kins to change role
	$scope.fetchKinstoChangeRole=function(){
		console.log('fetch kins to  change role invoked');
		AdminService.fetchKinstoDenyAccess()//fetches response required for changerole
		.then(
				function(response){
					$scope.kins=response.data;
					$location.path("/changerole");
				},
				function(error){
					console.log(error);
				});
	}
	
	//fetch kins to delete
	$scope.fetchKinstoDelete=function(){
		console.log('fetch kins to  delete invoked');
		AdminService.fetchall()
		.then(
				function(response){
					$scope.kins=response.data;
					$location.path("/deletekin");
				},
				function(error){
					console.log(error);
				});
	}
	
	//fetch kins to make kin assist admin
	$scope.fetchKinstoAssistAdmin=function(){
		console.log('fetch kins to makeassist admin invoked');
		AdminService.fetchKinstoAssistAdmin()
		.then(
				function(response){
					$scope.kins=response.data;
					$location.path("/makeassistadmin");
				},
				function(error){
					console.log(error);
				});
	}
	
	//permit kins access
	$scope.permit=function(kin){
		console.log('permit invoked');
		AdminService.permit(kin).then( function(o){
		$scope.fetchKinsforAccess();
		});
	}
	
	//deny kins access
	$scope.deny=function(kin){
		console.log('deny invoked');
		AdminService.deny(kin).then( function(d){
		$scope.fetchKinsforAccess();
		});
	}
	
	//delete kin
	$scope.deletekin=function(id){
		console.log('delete invoked');
		AdminService.deletekin(id).then( function(d){
		$scope.fetchKinstoDelete();
		})
	}
	
	//deny temporarily kins access
	$scope.denytemp=function(kin){
		console.log('temporary deny invoked');
		AdminService.denytemp(kin).then( function(d){
		$scope.fetchKinstoDenyAccess();
		});
	}
	
	//make assist admin
	$scope.makeassistadmin=function(id){
		console.log('make assist admin invoked');
		AdminService.makeassistadmin(id).then( function(q){
		$scope.fetchKinstoAssistAdmin();
		});
	}
	
	//change kins role
	$scope.changerole=function(kin){
		console.log('change role invoked');
		AdminService.kcr(kin).then(function(){
			$scope.fetchKinstoChangeRole();
		});
	}
	
	//fetching badges for external info
	function fetchbadges(){
		console.log("fetching badges...");
		AdminService.fetchbadges()
		.then(
				function(response){
					$scope.badges=response.data;
					});
	}
	
	//refresh compensator
	if($location.path()=='/deletekin'){if($rootScope.currentUser.role=='admin')$scope.fetchKinstoDelete();}
	if($location.path()=='/provideaccess'){if($rootScope.currentUser.role=='admin')$scope.fetchKinsforAccess();}
	if($location.path()=='/denyaccess'){if($rootScope.currentUser.role=='admin')$scope.fetchKinstoDenyAccess();}
	if($location.path()=='/changerole'){if($rootScope.currentUser.role=='admin')$scope.fetchKinstoChangeRole();}
	if($location.path()=='/makeassistadmin'){if($rootScope.currentUser.role=='admin')$scope.fetchKinstoAssistAdmin();}
	if($location.path()=='/adminfunctions'){if($rootScope.currentUser.role=='admin')fetchbadges();}
	
})
