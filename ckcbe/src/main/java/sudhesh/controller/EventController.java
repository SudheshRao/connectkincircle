package sudhesh.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sudhesh.DAO.EventDaoImpl;
import sudhesh.model.Errore;
import sudhesh.model.EventStatus;
import sudhesh.model.Events;
import sudhesh.model.Kin;

@Controller
public class EventController {
	
	@Autowired
	private EventDaoImpl eventdao;
	
	@RequestMapping(value="/event", method = RequestMethod.POST)
    public ResponseEntity<?> addEvent( @RequestBody Events event,HttpSession session) {
		Kin kin=(Kin)session.getAttribute("kin");
		if(kin==null){
			Errore error=new Errore(1,"Unauthroized Kin");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
		}
		event.setPostedBy(kin.getName());
		event.setPostedOn(Calendar.getInstance().getTime());
		eventdao.postevent(event);
        return new ResponseEntity<Events>(HttpStatus.OK);
    }

	@RequestMapping(value = "/getevent", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogList(HttpSession session){
		Kin Kin=(Kin)session.getAttribute("kin");
		if(Kin==null){
			Errore error=new Errore(1,"Unauthroized Kin");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Events> event=eventdao.getEvents();
		return new ResponseEntity<List<Events>>(event,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/estatus/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> getEstatus(@PathVariable(value="id") int id,@RequestBody String status,HttpSession session){
		Kin Kin=(Kin)session.getAttribute("kin");
		if(Kin==null){
			Errore error=new Errore(1,"Unauthroized Kin");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
		}
		Events event=eventdao.getevent(id);
		System.out.println(event.getId() + id +status);
		EventStatus es=eventdao.geteventstaus(id,Kin.getName());
		if(es==null){
			EventStatus er=new EventStatus();
			er.setAttending(status);
			er.setEventid(id);
			er.setKin(Kin.getName());
			eventdao.save(er);
			switch(status){
			case "yes":event.setAttending(event.getAttending()+1);eventdao.update(event);break;
			case "maybe":event.setMaybe(event.getMaybe()+1);eventdao.update(event);break;
			case "no":event.setNotattending(event.getNotattending()+1);eventdao.update(event);break;
			}
		}
			else{	
			switch(status){
			case "yes":{
					switch(es.getAttending()){
						case "yes":{eventdao.delete(id,Kin.getName());
									event.setAttending(event.getAttending()-1);
									eventdao.update(event);
									break;}
						case "maybe":{event.setMaybe(event.getMaybe()-1);
									  event.setAttending(event.getAttending()+1);
									  eventdao.update(event);
									  es.setAttending(status);
									  eventdao.updatees(es);
									  break;}
						case "no":{event.setNotattending(event.getNotattending()-1);
								   event.setAttending(event.getAttending()+1);
								   eventdao.update(event);
								   es.setAttending(status);
								   eventdao.updatees(es);
								   break;}
						}	
					break;
					}
			case "maybe":{
				switch(es.getAttending()){
				case "maybe":{eventdao.delete(id,Kin.getName());
							event.setMaybe(event.getMaybe()-1);
							eventdao.update(event);
							break;}
				case "yes":{event.setAttending(event.getAttending()-1);
							event.setMaybe(event.getMaybe()+1);
							eventdao.update(event);
							es.setAttending(status);
							eventdao.updatees(es);
							break;}
				case "no":{event.setNotattending(event.getNotattending()-1);
						   event.setMaybe(event.getMaybe()+1);
						   eventdao.update(event);
						   es.setAttending(status);
						   eventdao.updatees(es);
						   break;}
				}	
			break;
			}
			case "no":{
				switch(es.getAttending()){
				case "no":{eventdao.delete(id,Kin.getName());
							event.setNotattending(event.getNotattending()-1);
							eventdao.update(event);
							break;}
				case "maybe":{event.setMaybe(event.getMaybe()-1);
							  event.setNotattending(event.getNotattending()+1);
							  eventdao.update(event);
							  es.setAttending(status);
							  eventdao.updatees(es);
							  break;}
				case "yes":{event.setAttending(event.getAttending()-1);
				 		   event.setNotattending(event.getNotattending()+1);
						   eventdao.update(event);
						   es.setAttending(status);
						   eventdao.updatees(es);
						   break;}
				}	
			break;
			}
			}
		}
			return new ResponseEntity<List<Events>>(HttpStatus.OK);
		
		
	}

	@RequestMapping(value = "/eventresponse", method = RequestMethod.GET)
	public ResponseEntity<?> geteventresponse(HttpSession session){
		Kin Kin=(Kin)session.getAttribute("kin");
		if(Kin==null){
			Errore error=new Errore(1,"Unauthroized Kin");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
		}
		List<EventStatus> eventstatus=eventdao.getEventStatus(Kin.getName());
		return new ResponseEntity<List<EventStatus>>(eventstatus,HttpStatus.OK);
	}

}
