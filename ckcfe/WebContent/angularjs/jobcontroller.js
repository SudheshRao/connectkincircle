app.controller('JobController',function($scope,$location,$rootScope,JobService){
	$scope.job={id:'',jobtitle:'',jd:'',js:'',skills:'',salary:'',loctaion:'',notice:'',postedon:'',postedby:'',postedbyId:''};
	$scope.jobs;
	$scope.ajd;
	
	//save job
	$scope.saveJob=function(){
		console.log('save job invoked');
		JobService.saveJob($scope.job)
		.then(function(response){
			console.log("job inserted");
			$location.path('/searchjob');
		},function(response){
			console.log("failure " +response.status);
			if(response.status==401){
				$location.path('/login')
			}
			else{
			console.log(response.data.message)
			$location.path('/postJob')
			}
		});
	}
	
	//remove job by admin
	$scope.removejob=function(id){
		console.log('remove job invoked(admin)');
		JobService.removejob(id)
		.then(function(response){
			getAllJobs();
			$location.path('/removejob');
		})
	}
	
	//remove job
	$scope.jobremove=function(id){
		console.log('job remove invoked');
		JobService.jobremove(id)
		.then(function(response){
			$scope.fetchAppliedJob();
		})
	}
	
	//fetch applied job
	$scope.fetchAppliedJob=function(){
		console.log('fetch applied job invoked');
		JobService.fetchAppliedJob()
		.then(function(response){
			$scope.jobs=response.data;
			$location.path('/appliedjob');
		})
	}
	
	//apply for a job
	$scope.applyjob=function(id){
		console.log('apply job invoked');
		JobService.applyjob(id)
		.then(function(response){
			$scope.fetchAppliedJob();
		})
	}
	
	//kins applied job details
	$scope.appliedjobdetails=function(id){
		console.log('applied no jon details invoked');
		$rootScope.sjid=id;
		JobService.appliedjobdetails(id)
		.then(function(response){
			$rootScope.ajd=response.data;
			$location.path('/appliedjobdetails');
		})
	}
	
	//getalljobs
	function getAllJobs(){
		console.log('entering get All jobs')
		JobService.getAllJobs()
		.then(function(response){
			console.log(response.status); 
			$scope.jobs=response.data;
			$rootScope.jb=$scope.jobs;
			JobService.faj().then(function(r){
				$rootScope.applied=r.data;
			})
		},function(response){
			console.log(response.status)
		})
	}
	getAllJobs();
	if($location.path()=='/appliedjob'){$scope.fetchAppliedJob();}

})