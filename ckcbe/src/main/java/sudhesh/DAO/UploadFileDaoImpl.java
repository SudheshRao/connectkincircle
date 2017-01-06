package sudhesh.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudhesh.model.UploadFile;

@Repository
@Transactional
public class UploadFileDaoImpl {
	
		@Autowired
		private SessionFactory sessionFactory;
		
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		public void save(UploadFile uploadFile) {
			Session session=sessionFactory.getCurrentSession();
			session.save(uploadFile);
			session.flush();
		}


		public UploadFile getFile(String username) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from UploadFile where kin=?");
			query.setParameter(0, username);
	        UploadFile uploadFile=(UploadFile)query.uniqueResult();
			session.flush();
			return uploadFile;
		}

		public void update(UploadFile uploadFile) {
			Session session=sessionFactory.getCurrentSession();
			session.update(uploadFile);
			session.flush();
			
		}

		public void deletepic(UploadFile uploadFile) {
			Session session=sessionFactory.getCurrentSession();
			session.delete(uploadFile);
			session.flush();
			
		}

	}

