app.controller('KIController',function($http,$scope,$rootScope,$route,$location,$cookieStore,KIService,BlogService,ConnectService){
	
	$scope.kins;
	$scope.kin={id:'',name:'',password:'',confirmpassword:'',gender:'',email:'',phoneno:'',dob:'',isonline:'',status:'',role:'',post:''};
	$scope.blogs={};
	$scope.ilikes={};

	$scope.initialize=
		KIService.getblog()
		.then(function(response){
			$scope.blogs=response.data;
			KIService.getibloglikes()
			.then(function(responsea){
				$scope.ilikes=responsea.data;
			})
			});
	$scope.pendingrequest=
		ConnectService.pendingrequest()
		.then(function(response){
			$rootScope.prs= response.data;
			$cookieStore.put('pendingrequest',$rootScope.prs);
		},function(response){
			console.log(response.status)
		})
	
	$scope.ilike=function(id){
        BlogService.like(id)
        .then(function(response){
        	KIService.getblog()
    		.then(function(response){
    			$scope.blogs=response.data;
    			KIService.getibloglikes()
    			.then(function(responsea){
    				$scope.ilikes=responsea.data;
    			})
    			})})
	}
	$scope.deleteblog=function(id){
		BlogService.deleteblog(id)
		.then(function(response){
			$scope.initialize;
			$route.reload();
		})
	}
	$scope.notification=
		KIService.notification().then(function(response){
			$rootScope.notify=response.data;
			$cookieStore.put('notifi',$rootScope.notify);
		})
})