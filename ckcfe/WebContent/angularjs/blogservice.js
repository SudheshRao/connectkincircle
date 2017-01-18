app.factory('BlogService',function($http){
	
	var BASE_URL="http://localhost:8080/ckcbe/";
	var blogService=this;
	
	blogService.postblog=function(blogpost){
		return $http.post(BASE_URL+"/blog",blogpost);
	}
	
	blogService.getBlogPosts=function(){
		return $http.get(BASE_URL+"/blogpost")
	}
	
	blogService.getBloglikes=function(){
		return $http.get(BASE_URL+"/bloglikes")
	}
	
	blogService.like=function(id){
		return $http.get(BASE_URL+"/like/"+id)
	}
	
	blogService.deleteblog=function(id){
		return $http.delete(BASE_URL+"/deleteblog/"+id)
	}
	
	blogService.getBlogDetail=function(id){
		return $http.get(BASE_URL+"/get/"+ id)
	}
	
	blogService.addComment=function(comment){
		return $http.post(BASE_URL+"/addcomment",comment)
	}
	
	blogService.getComments=function(blogId){
		return $http.get(BASE_URL+"/getblogcomments/"+blogId)
	}
	
	return blogService;
})