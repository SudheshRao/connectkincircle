app.factory('JobService',function($http){
	var jobService={};
	var BASE_URL ="http://localhost:8080/ckcbe/"
		
	jobService.saveJob=function(job){
		return $http.post(BASE_URL + "/postJob" , job);
	}
	
	jobService.removejob=function(id){
		return $http.delete(BASE_URL + "/removeJob/"+id);
	}
	
	jobService.jobremove=function(id){
		return $http.delete(BASE_URL + "/jobremove/"+id);
	}
	
	jobService.applyjob=function(id){
		return $http.post(BASE_URL + "/applyjob/"+id);
	}
	
	jobService.fetchAppliedJob=function(){
		return $http.get(BASE_URL + "/appliedjob");
	}
	
	jobService.appliedjobdetails=function(id){
		return $http.get(BASE_URL + "/appliedjobdetails/"+id);
	}
	
	jobService.faj=function(){
		return $http.get(BASE_URL + "/faj");
	}
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL + "/getAllJobs");
	}
	
	return jobService;
})