package sudhesh.DAO;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudhesh.model.Connects;
import sudhesh.model.Kin;
import sudhesh.model.Notifications;

@Repository
@Transactional
public class ConnectDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Connects> getConnects(String name) {
		Session session= sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Connects where (toId=? or fromId=?) and status=?");
		query.setString(0, name);
	      query.setString(1, name);
	      query.setCharacter(2, 'A');
		List<Connects> connects=query.list();
		session.flush();
		return connects;
	}
	public List<Kin> getiConnects(String name) {
		Session session= sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("select * from kin where name in (select name from Kin where name!=? minus(select to_id from connects where from_id=? union select from_id from connects where to_id=?))");
		query.setString(0, name);
		query.setString(1, name);
		query.setString(2, name);
		query.addEntity(Kin.class);
		List<Kin> kin=query.list();
		return kin;
	}

	public void sendconnectRequest(String from, String to) {
		Session session=sessionFactory.getCurrentSession();
		Connects connect=new Connects();
		connect.setFromId(from);
		connect.setToId(to);
		connect.setStatus('P');
		session.save(connect);
		session.flush();
		
	}
	
	public List<Connects> getPendingRequest(String name) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Connects where toId=? and status=?");
		query.setString(0, name);
		query.setCharacter(1, 'P');
		List<Connects> pendingRequest=query.list();
		session.flush();
		return pendingRequest;
	}

	public void savenotification(Notifications notify) {
		Session session=sessionFactory.getCurrentSession();
		session.save(notify);
		session.flush();
		
	}

	public void updatePendingRequest(char connectstatus, String fromId, String name) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("update Connects set status=? where fromId=? and toId=?");
		query.setCharacter(0, connectstatus);
		query.setString(1, fromId);
		query.setString(2, name);
		int count=query.executeUpdate();
		System.out.println("Number of records updated " + count);
		session.flush();
		
	}
}
