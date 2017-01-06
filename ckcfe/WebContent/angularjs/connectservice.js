app.factory('ConnectService',function($http){
	var connectService={};
	var BASE_URL ="http://localhost:8080/ckcbe/"
		
		connectService.connectrequest=function(name){
		console.log('service --- friendRequest');
		return $http.post(BASE_URL+ '/connectrequest',name);
		}	

	connectService.getAllConnects=function(){
		console.log('service --- friendRequest');
		return $http.get(BASE_URL + "/getAllConnects");
	}
	
	connectService.pendingrequest=function(){
		console.log('service --- pending request');
		return $http.get(BASE_URL + "/pendingrequest")
	}
	
	connectService.updateConnectRequest=function(connectStatus,fromId){
		console.log('service - update friend request')
		return $http.put(BASE_URL + "/updateConnectRequest/" + connectStatus +"/"+fromId)
	}
	
		connectService.getAllKins=function(){
		console.log('entering get All Kins')
		return $http.get(BASE_URL + "/getAllKins");
		}
	
	return connectService;
})