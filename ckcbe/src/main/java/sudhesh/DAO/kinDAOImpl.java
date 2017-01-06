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
public class kinDAOImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//authenticate kin
	public Kin authenticate(Kin kin) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Kin where name=?  and password=?");
		query.setString(0, kin.getName());
		query.setString(1, kin.getPassword());
		Kin validkin=(Kin)query.uniqueResult();
		session.flush();
		return validkin;
		
	}
	
	//get all Kins
	public List<Kin> getAllKins() {
		Session session= sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Kin");
		List<Kin> Kins=query.list();
		session.flush();
		return Kins;
	}
	
	//save kins
	public Kin saveKin(Kin Kin) {
		Session session=sessionFactory.getCurrentSession();
		session.save(Kin);
		session.flush();
		return Kin;		
	}

	//get kin by name
	public Kin getKinByName(String name) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Kin where name=:un");
		query.setParameter("un", name);
		Kin validkin=(Kin)query.uniqueResult();
		session.flush();
		return validkin;
	}
	
	//get kin by id
	public Kin getKinById(int id) {
		Session session=sessionFactory.getCurrentSession();
		Kin Kin=(Kin)session.get(Kin.class, id);
		session.flush();
		return Kin;
	}

	//update kin when logged in
	public Kin updateKin(Kin kin) {
		Session session=sessionFactory.getCurrentSession();
		Kin existingkin=(Kin)session.get(Kin.class, kin.getId());
		existingkin.setIsonline(kin.isIsonline()); 
		session.update(existingkin);
		session.flush();
		return existingkin;
	}

	//update kin when edited
	public Kin updateaKin(Kin kin) {
		Session session=sessionFactory.getCurrentSession();
		session.update(kin);
		session.flush();
		return kin;
	}
	
	//delete a kin
	public void deleteKin(int id) {
		Session session=sessionFactory.getCurrentSession();
		Kin Kin=(Kin)session.get(Kin.class, id);
		session.delete(Kin);
		session.flush();
		
	}


}
