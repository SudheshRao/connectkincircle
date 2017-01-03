app.factory('AdminService',function($http){
	var BASE_URL="http://localhost:8080/ckcbe/";
	var adminService=this;
	
	adminService.kcr=function(kin){
		console.log('entering fetch kins for access in service')
		return $http.post(BASE_URL + "/changerole",kin);
	}
	
	//fetch kins for access provision
	adminService.fetchKinsforAccess=function(){
		console.log('entering fetch kins for access in service')
		return $http.get(BASE_URL + "/provideaccess");
	}
	
	//fetch kins to deny access provision and delete
	adminService.fetchKinstoDenyAccess=function(){
		console.log('entering fetch kins to deny access in service')
		return $http.get(BASE_URL + "/denyaccess");
	}

	//fetch kins to make assistadmin
	adminService.fetchKinstoAssistAdmin=function(){
		console.log('entering fetch kins to assist admin in service')
		return $http.get(BASE_URL + "/assistadmin");
	}
	
	//permit kins access
	adminService.permit=function(kin){
		console.log('entering permit kin.s access service')
		return $http.post(BASE_URL + "/permit",kin);
	}
	
	//deny kins access
	adminService.deny=function(kin){
		console.log('entering deny kin.s access in service')
		return $http.post(BASE_URL + "/deny",kin);
	}
	
	//delete kin
	adminService.deletekin=function(id){
		console.log('entering deny kin.s access in service')
		return $http.delete(BASE_URL + "/Kin/"+id);
	}
	
	//deny kins access temporarily
	adminService.denytemp=function(kin){
		console.log('entering deny kin.s access in service')
		return $http.post(BASE_URL + "/denytemp",kin);
	}
	
	//make assist admin
	adminService.makeassistadmin=function(id){
		console.log('entering make assist admin in service')
		return $http.post(BASE_URL + "/makeassistadmin/"+id);
	}
	
	//fetching badges for external info
	adminService.fetchbadges=function(){
		console.log('entering fetch badges in service')
		return $http.get(BASE_URL + "/fetchbadges");
	}
	
	return adminService;
})