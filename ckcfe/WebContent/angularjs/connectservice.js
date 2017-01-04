app.factory('ConnectService',function($http){
	var connectService={};
	var BASE_URL ="http://localhost:8080/ckcbe/"
		
		connectService.connectrequest=function(name){
		console.log('service --- friendRequest');
		return $http.post(BASE_URL+ '/connectrequest',name);
		}	
	
	connectService.pendingrequest=function(){
		console.log('service --- pending request');
		return $http.get(BASE_URL + "/pendingrequest")
	}
	
		connectService.getAllKins=function(){
		console.log('entering get All Kins')
		return $http.get(BASE_URL + "/getAllKins");
		}
	
	return connectService;
})