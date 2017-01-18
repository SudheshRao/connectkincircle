app.controller('BlogDetailController',function($routeParams,$scope,BlogService){
	var id=$routeParams.id
	$scope.blogPost={}
	$scope.comment={body:'',blogPost:{}}
	$scope.blogliked={};

	
	$scope.blogPost=
		BlogService.getBlogDetail(id) 
		.then(function(response){
			console.log(response.data);
			console.log(response.status)
			$scope.blogPost=response.data;
			$scope.showComments=false;
			BlogService.getBloglikes()
			.then(function(response){
				$scope.blogliked=response.data;
			})
		},function(response){
			console.log(response.status)
		})
	
		$scope.editPost=function(){
		$scope.isEditPost=true;
		BlogService.editPost()
	}
	
	$scope.getComments=function(blogId){
		$scope.showComments=true;
		BlogService.getComments(blogId)
		.then(function(response){
			$scope.comments=response.data;
		},function(response){
			console.log('comments ' + response.status)
		})
		
	}
	$scope.ilike=function(id){
        BlogService.like(id)
        .then(function(response){
        	BlogService.getBlogPosts()
        	.then(function(response){
        		console.log(response.status);
        		$scope.blogPosts=response.data;
        		BlogService.getBloglikes()
        		.then(function(response){
        			$scope.blogliked=response.data;
        		})})
        },function(response){
        	console.log(response.status)
        })
	}
	$scope.addcomment=function(id){
		console.log(id);
		$scope.comment.blogPost.id =id;
		console.log( $scope.comment.blogPost.id);
		$scope.comment.blogPost=$scope.blogPost;
        BlogService.addComment($scope.comment)
        .then(function(response){
        	console.log(response.data)
        	console.log(response.status)
        	$scope.getComments(id);
        },function(response){
        	console.log(response.status)
        })
	}
	
})