app.factory('AdminService',function($http){
	var BASE_URL="http://localhost:8080/ckcbe/";
	var adminService=this;
	
	//fetch all kins
	adminService.fetchall=function(){
		return $http.get(BASE_URL + "/getallkins");
	}
	
	//change kins role
	adminService.kcr=function(kin){
		return $http.post(BASE_URL + "/changerole",kin);
	}
	
	//fetch kins for access provision
	adminService.fetchKinsforAccess=function(){
		return $http.get(BASE_URL + "/provideaccess");
	}
	
	//fetch kins to deny access provision and delete
	adminService.fetchKinstoDenyAccess=function(){
		return $http.get(BASE_URL + "/denyaccess");
	}


	
	//fetch kins to make assistadmin
	adminService.fetchKinstoAssistAdmin=function(){
		return $http.get(BASE_URL + "/assistadmin");
	}
	
	//permit kins access
	adminService.permit=function(kin){
		return $http.post(BASE_URL + "/permit",kin);
	}
	
	//deny kins access
	adminService.deny=function(kin){
		return $http.post(BASE_URL + "/deny",kin);
	}
	
	//delete kin
	adminService.deletekin=function(id){
		return $http.delete(BASE_URL + "/deletekin/"+id);
	}
	
	//deny kins access temporarily
	adminService.denytemp=function(kin){
		return $http.post(BASE_URL + "/denytemp",kin);
	}
	
	//make assist admin
	adminService.makeassistadmin=function(id){
		return $http.post(BASE_URL + "/makeassistadmin/"+id);
	}
	
	//fetching badges for external info
	adminService.fetchbadges=function(){
		return $http.get(BASE_URL + "/fetchbadges");
	}
	
	return adminService;
})