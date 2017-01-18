app.factory('CalendarService',function($http){
	
	var BASE_URL="http://localhost:8080/ckcbe/";
	var calService=this;
	
	calService.savesrc=function(cal){
		return $http.post(BASE_URL+"/calendarsrc",cal);
	}
	
	calService.getsrc=function(){
		return $http.get(BASE_URL+"/getcalsrc")
	}
	
	return calService;
})