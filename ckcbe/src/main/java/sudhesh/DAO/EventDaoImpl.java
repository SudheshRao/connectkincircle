package sudhesh.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sudhesh.model.Bloglikes;
import sudhesh.model.EventStatus;
import sudhesh.model.Events;

@Repository
@Transactional
public class EventDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void postevent(Events event) {
		Session session=sessionFactory.getCurrentSession();
		session.save(event);
		session.flush();
	}
	
	
		public List<Events> getEvents() {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Events");
			List<Events> event=query.list();
			session.flush();
			return event;
		}

		public EventStatus geteventstaus(int id, String name) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from EventStatus where kin=:un and eventid=:bi");
			query.setParameter("un",name );
			query.setParameter("bi", id);
			EventStatus es=(EventStatus) query.uniqueResult();
			session.flush();
			return es;
		}

		public void save(EventStatus er) {
			Session session=sessionFactory.getCurrentSession();
			session.save(er);
			session.flush();
			
		}

		public Events getevent(int id) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Events where id=:bi");
			query.setParameter("bi", id);
			Events es=(Events) query.uniqueResult();
			session.flush();
			return es;
		}

		public void delete(int id, String name) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("delete from EventStatus where kin=:un and eventid=:bi");
			query.setParameter("un",name );
			query.setParameter("bi", id);
			query.executeUpdate();
			session.flush();
			
		}

		public void update(Events event) {
			Session session=sessionFactory.getCurrentSession();
			session.update(event);
			session.flush();
			
		}

		public void updatees(EventStatus es) {
			Session session=sessionFactory.getCurrentSession();
			session.update(es);
			session.flush();
			
		}

		public List<EventStatus> getEventStatus(String name) {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from EventStatus where kin=:un");
			query.setParameter("un", name);
			List<EventStatus> es=query.list();
			session.flush();
			return es;
		}

	

}
