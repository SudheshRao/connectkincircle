app.factory('KinService',function($http){
	var BASE_URL="http://localhost:8080/ckcbe/";
	//instantiation
	var kinService=this;
	
	kinService.saveKin=function(kin){
		console.log('entering save person in service')
		return $http.post(BASE_URL + "/Kin",kin)
		.then(function(response){
			console.log(response.status)
			console.log(response.headers)
			return response.status
		},function(reponse){
			console.log(response.status)
			return response.status
		})
	}
	
	kinService.authenticate=function(kin){
		console.log('entering login person in service')
		return $http.post(BASE_URL + "/login",kin);
	}
	
	return kinService;

})