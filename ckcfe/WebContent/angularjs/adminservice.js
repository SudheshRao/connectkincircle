app.factory('AdminService',function($http){
	var BASE_URL="http://localhost:8080/ckcbe/";
	//instantiation
	var adminService=this;
	
	adminService.fetchKinsforAccess=function(){
		console.log('entering fetchallpersons in service')
		return $http.get(BASE_URL + "/provideaccess");
	}
	
	adminService.fetchbadges=function(){
		return $http.get(BASE_URL + "/fetchbadges");
	}
	
	adminService.permit=function(kin){
		console.log('entering fetchallpersons in service')
		return $http.post(BASE_URL + "/permit",kin);
	}
	
	adminService.deny=function(kin){
		console.log('entering fetchallpersons in service')
		return $http.post(BASE_URL + "/deny",kin);
	}
	return adminService;
})