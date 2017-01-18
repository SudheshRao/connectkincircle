package sudhesh.controller;


import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sudhesh.DAO.BlogDaoImpl;
import sudhesh.model.BlogComment;
import sudhesh.model.BlogPost;
import sudhesh.model.Bloglikes;
import sudhesh.model.Errore;
import sudhesh.model.Kin;

@RestController
public class BlogController {

		@Autowired
		private BlogDaoImpl blogdao;
		
		@RequestMapping(value="/blog", method = RequestMethod.POST)
	    public ResponseEntity<?> addBlog( @RequestBody BlogPost blog,HttpSession session) {
			Kin kin=(Kin)session.getAttribute("kin");
			if(kin==null){
				Errore error=new Errore(1,"Unauthroized Kin");
				return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
			}
			blog.setCreatedBy(kin);
			blog.setCreatedOn(Calendar.getInstance().getTime());
			blogdao.postblog(blog);
	        return new ResponseEntity<BlogPost>(HttpStatus.OK);
	    }

		
		@RequestMapping(value = "/blogpost", method = RequestMethod.GET)
		public ResponseEntity<?> getBlogList(HttpSession session){
			Kin Kin=(Kin)session.getAttribute("kin");
			if(Kin==null){
				Errore error=new Errore(1,"Unauthroized Kin");
				return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
			}
			List<BlogPost> Blogs=blogdao.getBlogPosts();
			return new ResponseEntity<List<BlogPost>>(Blogs,HttpStatus.OK);
		}
		
		@RequestMapping(value = "/deleteblog/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteblog(@PathVariable(value="id") int id,HttpSession session){
			Kin Kin=(Kin)session.getAttribute("kin");
			if(Kin==null){
				Errore error=new Errore(1,"Unauthroized Kin");
				return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
			}
			blogdao.deletebloglikes(id);
			BlogPost bp = blogdao.getBlogPost(id);
			blogdao.deleteblogcomments(bp);
			blogdao.deleteblog(id);
			return new ResponseEntity<List<BlogPost>>(HttpStatus.OK);
		}
		
		@RequestMapping(value = "/bloglikes", method = RequestMethod.GET)
		public ResponseEntity<?> getBlogLikes(HttpSession session){
			Kin Kin=(Kin)session.getAttribute("kin");
			if(Kin==null){
				Errore error=new Errore(1,"Unauthroized Kin");
				return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
			}
			List<Bloglikes> Bloglikes=blogdao.getBloglikes(Kin.getName());
			return new ResponseEntity<List<Bloglikes>>(Bloglikes,HttpStatus.OK);
		}
		
		 @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
		    public ResponseEntity<?> addBlogComment( @RequestBody BlogComment blogComment,HttpSession session) {
		    	Kin Kin=(Kin)session.getAttribute("kin");
				if(Kin==null){
					Errore error=new Errore(1,"Unauthroized Kin");
					return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
				}
				BlogPost bp=blogComment.getBlogPost();
				BlogPost Blog=blogdao.getBlogPost(bp.getId());
				if(Blog==null){
					Errore error=new Errore(2,"Blog not found");
					return new ResponseEntity<Errore>(error,HttpStatus.NOT_FOUND);
				}
		        BlogPost createdBlog= blogdao.addBlogPostComment(Kin, blogComment);
		        createdBlog.setNc(createdBlog.getNc()+1);
		        return new ResponseEntity<BlogPost>(createdBlog,HttpStatus.OK);
		    }
		
		@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getBlog(@PathVariable(value="id") int id,HttpSession session){
			Kin Kin=(Kin)session.getAttribute("kin");
			if(Kin==null){
				Errore error=new Errore(1,"Unauthroized Kin");
				return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
			}
			BlogPost Blog=blogdao.getBlogPost(id);
			return new ResponseEntity<BlogPost>(Blog,HttpStatus.OK);
		}
		
		@RequestMapping(value = "/like/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> likeBlog(@PathVariable(value="id") int id,HttpSession session){
			Kin Kin=(Kin)session.getAttribute("kin");
			if(Kin==null){
				Errore error=new Errore(1,"Unauthroized Kin");
				return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
			}
			BlogPost Blog=blogdao.getBlogPost(id);
			Bloglikes iskin=blogdao.haskinliked(Kin.getName(),id);
			if(iskin==null){
			Bloglikes bl=new Bloglikes();
			bl.setBlogid(id);
			bl.setFromkin(Kin.getName());
			blogdao.save(bl);
			Blog.setLikes(Blog.getLikes()+1);
			blogdao.update(Blog);
			}
			else{	
			blogdao.deletelike(Kin.getName(),id);
			Blog.setLikes(Blog.getLikes()-1);
			blogdao.update(Blog);
			}	
			return new ResponseEntity<BlogPost>(Blog,HttpStatus.OK);
		}
		
		@RequestMapping(value="/getblogcomments/{blogId}",method=RequestMethod.GET)
		public ResponseEntity<?> getBlogComments(@PathVariable(value="blogId")int blogId,HttpSession session){
			Kin Kin=(Kin)session.getAttribute("kin");
			if(Kin==null){
				Errore error=new Errore(1,"Unauthroized Kin");
				return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
			}
			List<BlogComment> blogComments=blogdao.getBlogComments(blogId);
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);		
		}
		
	   
		
	
}
