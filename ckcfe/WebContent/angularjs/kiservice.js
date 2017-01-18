app.factory('KIService',function($http){
	
	var BASE_URL="http://localhost:8080/ckcbe/";
	//instantiation
	var kiService=this;
	
	kiService.getblog=function(){
	return $http.get(BASE_URL + "/iblog");
	}
	kiService.getibloglikes=function(){
		return $http.get(BASE_URL+"/ibloglikes")
	}
	kiService.notification=function(){
		return $http.get(BASE_URL+"/notification")
	}
	return kiService;
})