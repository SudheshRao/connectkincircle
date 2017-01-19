package sudhesh.controller;

import java.util.ArrayList;
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

import sudhesh.DAO.ConnectDaoImpl;
import sudhesh.DAO.kinDAOImpl;
import sudhesh.model.Connects;
import sudhesh.model.Errore;
import sudhesh.model.Kin;
import sudhesh.model.Notifications;

@Controller
public class ConnectController {
	
	
	@Autowired
	private ConnectDaoImpl connectdao;
	
	@Autowired
	private kinDAOImpl kindao;
	
	@RequestMapping(value="/connectrequest",method=RequestMethod.POST)
	public ResponseEntity<?> sendFriendRequest(@RequestBody String name,HttpSession session){
		Kin kin=(Kin) session.getAttribute("kin");
		if(kin==null)
			return new ResponseEntity<Errore>(new Errore(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
		else{
			connectdao.sendconnectRequest(kin.getName(),name);
			Notifications notify =new Notifications();
			notify.setFromkin(kin.getName());
			notify.setTokin(name);
			notify.setNotifiy("You have recieved a connect request from "+kin.getName());
			notify.setTim(Calendar.getInstance().getTime());
			connectdao.savenotification(notify);
			Notifications notifyme =new Notifications();
			notifyme.setFromkin("CKC");
			notifyme.setTokin(kin.getName());
			notifyme.setNotifiy("You have sent Connect request to "+name);
			notifyme.setTim(Calendar.getInstance().getTime());
			connectdao.savenotification(notifyme);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getAllConnects",method=RequestMethod.GET)
	public ResponseEntity<?> getAllFriends(HttpSession session){
		Kin kin=(Kin)session.getAttribute("kin");
		if(kin!=null){
		List<Kin> k=new ArrayList<Kin>();
		List<Connects> connects=connectdao.getConnects(kin.getName());
		if(connects!=null){
			for(Connects c:connects){
				if(c.getFromId()==kin.getName())
				k.add(kindao.getKinByName(c.getToId()));
				else k.add(kindao.getKinByName(c.getFromId()));

			}
		}
		return new ResponseEntity<List<Kin>>(k,HttpStatus.OK);
		}
		else
			return new ResponseEntity<Errore>(new Errore(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/updateConnectRequest/{connectStatus}/{fromId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@PathVariable(value="connectStatus") char connectstatus,@PathVariable(value="fromId") String fromId,HttpSession session){
		Kin kin=(Kin)session.getAttribute("kin");
		if(kin==null)
			return new ResponseEntity<Errore>(new Errore(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
		else{
			connectdao.updatePendingRequest(connectstatus,fromId,kin.getName());
			Notifications notify =new Notifications();
			if(connectstatus=='A')
			{notify.setFromkin(kin.getName());
			notify.setTokin(fromId);
			notify.setNotifiy(kin.getName()+" has accepted your Connect request ");
			notify.setTim(Calendar.getInstance().getTime());
			connectdao.savenotification(notify);}
			else{notify.setFromkin(kin.getName());
			notify.setTokin(fromId);
			notify.setNotifiy("Sorry!!! "+kin.getName()+" has denied your Connect request ");
			notify.setTim(Calendar.getInstance().getTime());
			connectdao.savenotification(notify);			
			}
				
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/pendingrequest",method=RequestMethod.GET)
	public ResponseEntity<?> getAllPendingRequest(HttpSession session){
		Kin kin=(Kin)session.getAttribute("kin");
		if(kin==null)
			return new ResponseEntity<Errore>(new Errore(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
		else{
			List<Kin> k=new ArrayList<Kin>();
			List<Connects> pendingRequest=connectdao.getPendingRequest(kin.getName());
			if(pendingRequest!=null){
				for(Connects c:pendingRequest){
					k.add(kindao.getKinByName(c.getFromId()));
				}
			}
			return new ResponseEntity<List<Kin>>(k,HttpStatus.OK);
		}
	}
	
	//get all Kins
		@RequestMapping(value="/getAllKins",method=RequestMethod.GET)
		public  ResponseEntity<?> getAllKins(HttpSession session){
			Kin pkin=(Kin) session.getAttribute("kin");
			if(pkin==null)
				return new ResponseEntity<Errore>(new Errore(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
			
			List<Kin> fkin=connectdao.getiConnects(pkin.getName());
			
			return new ResponseEntity<List<Kin>>(fkin,HttpStatus.OK);
		}

}
