app.factory('EventService',function($http){
	
	var BASE_URL="http://localhost:8080/ckcbe/";
	var eventService=this;
	
	eventService.postevent=function(event){
		return $http.post(BASE_URL+"/event",event);
	}
	
	eventService.estatus=function(id,status){
		console.log(id + status);
		return $http.post(BASE_URL+"/estatus/" +id,status);
	}
	
	eventService.getevents=function(){
		return $http.get(BASE_URL+"/getevent")
	}
	
	eventService.geteventstatus=function(){
		return $http.get(BASE_URL+"/eventresponse")
	}
	
	return eventService;
})