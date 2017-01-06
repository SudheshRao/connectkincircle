app.factory('KinService',function($http){
	
	var BASE_URL="http://localhost:8080/ckcbe/";
	//instantiation
	var kinService=this;
	
	kinService.signup=function(kin){console.log('signup service invoked');
		return $http.post(BASE_URL + "/signup",kin);
	}
	
	kinService.login=function(kin){console.log('login service invoked');
		return $http.post(BASE_URL + "/login",kin);
	}
	
	kinService.logout=function(){console.log('logout service invoked');
		return $http.put(BASE_URL + "/logout");
	}
	
	return kinService;

})