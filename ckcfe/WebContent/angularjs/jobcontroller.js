app.controller('JobController',function($scope,$location,$rootScope,JobService){
	$scope.job={id:'',jobtitle:'',jd:'',js:'',skills:'',salary:'',loctaion:'',notice:'',postedon:'',postedby:'',postedbyId:''};
	$scope.jobs;
	
	$scope.saveJob=function(){
		console.log('entering post job in job controller');

		JobService.saveJob($scope.job)
		.then(function(response){
			console.log("successfully inserted job details");
			alert("Posted job successfully");
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
		})
	}
	
	$scope.removejob=function(id){
		console.log('entering remove job in job controller');

		JobService.removejob(id)
		.then(function(response){
			getAllJobs();
			alert("Removed job successfully");
			$location.path('/removejob');
		})
	}
	
	$scope.jobremove=function(id){
		console.log('entering remove job in job controller'+id);

		JobService.jobremove(id)
		.then(function(response){
			alert("Removed job successfully");
			$scope.fetchAppliedJob();
		})
	}
	
	$scope.fetchAppliedJob=function(){
		console.log('entering search applied job in job controller');

		JobService.fetchAppliedJob()
		.then(function(response){
			$rootScope.ja=response.data;
			$location.path('/appliedjob');
		})
	}
	$scope.applyjob=function(id){
		console.log('entering apply job in job controller');

		JobService.applyjob(id)
		.then(function(response){
			$scope.fetchAppliedJob();
		})
	}
	
	$scope.appliedjobdetails=function(id){
		console.log('entering search applied job in job controller');
		$rootScope.sjid=id;
		JobService.appliedjobdetails(id)
		.then(function(response){
			$rootScope.ajd=response.data;
			$location.path('/appliedjobdetails');
		})
	}
	
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