app.factory('AdminService',function($http){
	var BASE_URL="http://localhost:8080/ckcbe/";
	//instantiation
	var adminService=this;
	
	adminService.fetchKinsforAccess=function(){
		console.log('entering fetchallpersons in service')
		return $http.get(BASE_URL + "/provideaccess");
	}
	return adminService;
})