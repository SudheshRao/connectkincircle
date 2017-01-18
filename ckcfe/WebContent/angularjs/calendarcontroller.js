app.controller('CalendarController',function($scope,$routeParams,$sce,$location,$window,CalendarService){
	
	$scope.calendarsrc={};
	
	$scope.savesrc=function(){
			CalendarService.savesrc($scope.calendarsrc)
			}
	
	$scope.savedsrc=
		CalendarService.getsrc()
		.then(function(response){
			$scope.calendarsrc=response.data;
			$scope.currentProjectUrl = $sce.trustAsResourceUrl($scope.calendarsrc.src);
			console.log(response.status);
		})
})