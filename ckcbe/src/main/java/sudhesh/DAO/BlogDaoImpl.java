package sudhesh.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudhesh.model.BlogComment;
import sudhesh.model.BlogPost;
import sudhesh.model.Bloglikes;
import sudhesh.model.Kin;

@Repository
@Transactional
public class BlogDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}



	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	public void postblog(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogPost);
		session.flush();
	}
	
	
		public List<BlogPost> getBlogPosts() {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from BlogPost");
			List<BlogPost> blogPosts=query.list();
			session.flush();
			return blogPosts;
		}
		public BlogPost getBlogPost(int id) {
			Session session=sessionFactory.getCurrentSession();
			BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
			session.flush();
			return blogPost;
		}
		
		public BlogPost addBlogPostComment(Kin user, BlogComment blogComment) {
			Session session=sessionFactory.getCurrentSession();
		 blogComment.setCreatedBy(user);
		 blogComment.setCreatedOn(new Date());
		 BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogComment.getBlogPost().getId());
				 blogComment.setBlogPost(blogPost);
		 session.merge(blogComment);
		 session.flush();
		 return blogPost;
		 
		}
		public List<BlogComment> getBlogComments(int blogId) {
			Session session=sessionFactory.getCurrentSession();
			BlogPost blogPost=(BlogPost)session.get(BlogPost.class, blogId);
			List<BlogComment> blogComments=blogPost.getComments();
			session.flush();
			return blogComments;
		}



		public Bloglikes haskinliked(String name,int id) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Bloglikes where fromkin=:un and blogid=:bi");
			query.setParameter("un",name );
			query.setParameter("bi", id);
			Bloglikes bloglike=(Bloglikes) query.uniqueResult();
			session.flush();
			return bloglike;
		}



		public void save(Bloglikes bl) {
			Session session=sessionFactory.getCurrentSession();
			session.save(bl);
			session.flush();
			
		}



		public void deletelike(String name,int id) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("delete from Bloglikes where fromkin=:un and blogid=:bi");
			query.setParameter("un",name );
			query.setParameter("bi", id);
			query.executeUpdate();
			session.flush();
		}



		public List<Bloglikes> getBloglikes(String name) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Bloglikes where fromkin=:un");
			query.setParameter("un", name);
			List<Bloglikes> bloglikes=query.list();
			session.flush();
			return bloglikes;
		}



		public void update(BlogPost blog) {
			Session session=sessionFactory.getCurrentSession();
			session.update(blog);
			session.flush();
		}

		public void deletebloglikes(int id) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("delete from Bloglikes where blogid=:bi");
			query.setParameter("bi", id);
			query.executeUpdate();
			session.flush();
			
		}



		public void deleteblogcomments(BlogPost bp) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("delete from BlogComment where blogPost=:bi");
			query.setParameter("bi", bp);
			query.executeUpdate();
			session.flush();
			
		}



		public void deleteblog(int id) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("delete from BlogPost where id=:bi");
			query.setParameter("bi", id);
			query.executeUpdate();
			session.flush();
			
			
		}

}
