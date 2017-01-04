package sudhesh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sudhesh.DAO.ConnectDaoImpl;
import sudhesh.model.Connects;
import sudhesh.model.Errore;
import sudhesh.model.Kin;
import sudhesh.model.Notifications;

@Controller
public class ConnectController {
	
	
	@Autowired
	private ConnectDaoImpl connectdao;
	
	@RequestMapping(value="/connectrequest",method=RequestMethod.POST)
	public ResponseEntity<?> sendFriendRequest(@RequestBody String name,HttpSession session){
		Kin kin=(Kin) session.getAttribute("kin");
		if(kin==null)
			return new ResponseEntity<Errore>(new Errore(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
		else{
			connectdao.sendconnectRequest(kin.getName(),name);
			Notifications notify =new Notifications();
			notify.setFrom(kin.getName());
			notify.setTo(name);
			notify.setNotification("You have recieved a connect request from "+name);
			connectdao.savenotification(notify);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/pendingrequest",method=RequestMethod.GET)
	public ResponseEntity<?> getAllPendingRequest(HttpSession session){
		Kin kin=(Kin)session.getAttribute("kin");
		if(kin==null)
			return new ResponseEntity<Errore>(new Errore(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
		else{
			List<Connects> pendingRequest=connectdao.getPendingRequest(kin.getName());
			return new ResponseEntity<List<Connects>>(pendingRequest,HttpStatus.OK);
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
