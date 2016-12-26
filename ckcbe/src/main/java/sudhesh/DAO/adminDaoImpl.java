package sudhesh.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudhesh.model.Kin;

@Repository
@Transactional
public class adminDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//get kins for access provision
	public List<Kin> getKinsforAccess() {
			Session session= sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Kin where status =:un");
			query.setParameter("un", false);
			List<Kin> Kins=query.list();
			session.flush();
			return Kins;

	}

}
