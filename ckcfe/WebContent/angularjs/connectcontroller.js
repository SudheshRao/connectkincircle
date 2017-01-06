app.controller('ConnectController',function($http,$scope,$rootScope,$location,$cookieStore,ConnectService){
	console.log('entering the connect controller')
	$scope.skins;
	$scope.skins;
	$scope.skin={id:'',name:'',password:'',confirmpasswprd:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};
	$scope.pendingrequest;
	
	$scope.connectrequest=function(name){
		console.log('connect request function')
		ConnectService.connectrequest(name)
		.then(function(response){
			console.log(response.status);
			alert('Connect request Sent')
			getAllKins().then(function(){$location.path('/searchkin')})
		},
		function(response){
			console.log(response.status);
		}
		)
	}
	
	$scope.kinconnects= function(){
		ConnectService.getAllConnects()
		.then(function(response){
			console.log('get all Connects controller')
			$scope.ckins= response.data;
		},
		function(response){
			console.log(response.data)
		})	
	}
	
	$scope.pendingrequest=function(){
		ConnectService.pendingrequest()
		.then(function(response){
			console.log('PENDING REQUEST')
			$scope.pendingrequest= response.data;
			
		},function(response){
			console.log(response.status)
		})
	}
	
	$scope.updatePendingRequest=function(fromId,connectStatus){
		ConnectService.updateConnectRequest(connectStatus,fromId)
		.then(function(response){
			console.log(response.status)
			if(connectStatus=='A'){
			alert('you have accepted the friend request. You and ' + fromId + " are friends");
			$location.path('/pendingrequest')
			}
			else{
				alert('You have denied the friend requet')
			}
		},function(response){
			console.log(response.log)
		})
	}

	function getAllKins(){
		console.log('entering get All Kins')
		ConnectService.getAllKins()
		.then(function(response){
			console.log(response.status); 
			$scope.skins=response.data;
			$rootScope.sKins=$scope.skins;
		},function(response){
			console.log(response.status)
		})
	}
	getAllKins();
	if($location.path()=='/pendingrequest'){if($rootScope.currentUser!=null)$scope.pendingrequest();}
	if($location.path()=='/kinconnects'){if($rootScope.currentUser!=null)$scope.kinconnects();}

})
