app.controller('BlogController',function($scope,$routeParams,$location,$window,BlogService){
	console.log('post blog invoked');

	var id=$routeParams.id;
	$scope.comment={body:'',blogPost:{id:'',title:''}};
	$scope.blogPost={};
	$scope.blogPosts=[];
	$scope.bloglikes={};
	$scope.trends=[];
	
	$scope.postblog=function(){
		console.log('post blog invoked');
		BlogService.postblog($scope.blogPost)
		.then(function(response){
			console.log(response.status);
			$window.location.reload();			
		},function(response){
			console.log(response.status);
		})
	}
	
	$scope.blogposts=
		BlogService.getBlogPosts()
	.then(function(response){
		console.log(response.status);
		$scope.blogPosts=response.data;
		BlogService.getBloglikes()
		.then(function(response){
			$scope.bloglikes=response.data;
		})
	},function(response){
		console.log(response.status);
	})
	
	/*$scope.blogPost=
		BlogService.getBlogDetail(id) //calling service function directly
		.then(function(response){
			console.log(response.data);
			console.log(response.status)
			$scope.blogPost=response.data;
			$scope.showComments=false;
		},function(response){
			console.log(response.status)
		})*/
	$scope.like=function(id){
        BlogService.like(id)
        .then(function(response){
        	BlogService.getBlogPosts()
        	.then(function(response){
        		console.log(response.status);
        		$scope.blogPosts=response.data;
        		BlogService.getBloglikes()
        		.then(function(response){
        			$scope.bloglikes=response.data;
        		})})
        },function(response){
        	console.log(response.status)
        })
	}
	$scope.addComment=function(id){
		console.log(id);
		$scope.comment.blogPost.id =id;
		console.log( $scope.comment.blogPost.id);
		$scope.comment.blogPost=$scope.blogPost;
        BlogService.addComment($scope.comment)
        .then(function(response){
        	console.log(response.data)
        	console.log(response.status)
        },function(response){
        	console.log(response.status)
        })
	}
	
})