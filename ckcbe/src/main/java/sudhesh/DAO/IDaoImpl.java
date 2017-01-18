package sudhesh.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudhesh.model.BlogPost;
import sudhesh.model.Bloglikes;
import sudhesh.model.Kin;
import sudhesh.model.Notifications;

@Repository
@Transactional
public class IDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<BlogPost> getiblog(Kin i) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where createdBy=:un");
		query.setParameter("un", i);
		List<BlogPost> blogPosts=query.list();
		session.flush();
		return blogPosts;
	}

	public List<Bloglikes> getiBloglikes(String name) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Bloglikes where fromkin=:un");
		query.setParameter("un", name);
		List<Bloglikes> bloglikes=query.list();
		session.flush();
		return bloglikes;
	}

	public List<Notifications> getnotification(String name) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Notifications where tokin=:un");
		query.setParameter("un", name);
		List<Notifications> nt=query.list();
		session.flush();
		return nt;
	}

}
