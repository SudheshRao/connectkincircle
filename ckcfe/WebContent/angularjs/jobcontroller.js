app.controller('JobController',function($scope,$location,$rootScope,JobService){
	$scope.job={id:'',jobtitle:'',jd:'',js:'',skills:'',salary:'',loctaion:'',notice:'',postedon:'',postedby:'',postedbyId:''};
	$scope.jobs;
	
	$scope.saveJob=function(){
		console.log('entering post job in job controller');
		JobService.saveJob($scope.job)
		.then(function(response){
			console.log("successfully inserted job details");
			alert("Posted job successfully");
			$location.path('/home');
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
	
	function getAllJobs(){
		console.log('entering get All jobs')
		JobService.getAllJobs()
		.then(function(response){
			console.log(response.status); 
			$scope.jobs=response.data;  
			
		},function(response){
			console.log(response.status)
		})
	}
	getAllJobs();
		
	
})