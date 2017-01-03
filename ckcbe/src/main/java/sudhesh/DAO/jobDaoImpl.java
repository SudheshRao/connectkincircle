package sudhesh.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudhesh.model.Job;
import sudhesh.model.JobApplied;
import sudhesh.model.Kin;

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

		public void removejob(int id) {
			Session session=sessionFactory.getCurrentSession();
			Job job=(Job)session.get(Job.class, id);
			session.delete(job);
			session.flush();			
		}

		public Job getjob(int id) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Job where id=:un");
			query.setParameter("un", id);
			Job job=(Job) query.uniqueResult();
			session.flush();
			return job;			
		}

		public void applyjob(JobApplied ja) {
			Session session=sessionFactory.getCurrentSession();
			session.save(ja);
			session.flush();
			
		}

		public List<JobApplied> getAppliedJob(int id) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from JobApplied where kinid=:kid");
			query.setParameter("kid", id);
			List<JobApplied> jobs=query.list();
			session.flush();
			return jobs;
		}

		public JobApplied getAppliedJobbyJid(int jid, int kiid) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from JobApplied where jobid=:id and kinid=:kid");
			query.setParameter("id", jid);
			query.setParameter("kid", kiid);
			JobApplied job=(JobApplied) query.uniqueResult();
			session.flush();
			return job;
		}

		public void jobremove(int jid, int kiid) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("delete from JobApplied where jobid=:id and kinid=:kid");
			query.setParameter("id", jid);
			query.setParameter("kid", kiid);
			query.executeUpdate();
			session.flush();
			
		}

		public List<JobApplied> getAppliedJobDetails(int id) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from JobApplied where jobid=:jid");
			query.setParameter("jid", id);
			List<JobApplied> jobs=query.list();
			session.flush();
			return jobs;
		}

}
