package sudhesh.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudhesh.model.Job;

@Repository
@Transactional
public class jobDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		
		public void postJob(Job job) {
			Session session=sessionFactory.getCurrentSession();
			session.save(job);
			session.flush();

		}

		public List<Job> getAllJobs() {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Job");
			List<Job> jobs=query.list();
			session.flush();
			return jobs;
		}

}
