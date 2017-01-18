package sudhesh.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sudhesh.DAO.IDaoImpl;
import sudhesh.model.BlogPost;
import sudhesh.model.Bloglikes;
import sudhesh.model.Errore;
import sudhesh.model.Kin;
import sudhesh.model.Notifications;

@Controller
public class IController {
	
	@Autowired
	private IDaoImpl idao;
	
	@RequestMapping(value="/iblog", method = RequestMethod.GET)
    public ResponseEntity<?> getBlog(HttpSession session) {
		Kin kin=(Kin)session.getAttribute("kin");
		if(kin==null){
			Errore error=new Errore(1,"Unauthroized Kin");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> bp = idao.getiblog(kin);
		if(bp!=null)
        return new ResponseEntity<List<BlogPost>>(bp,HttpStatus.OK);
		else return new ResponseEntity<BlogPost>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "/ibloglikes", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogLikes(HttpSession session){
		Kin Kin=(Kin)session.getAttribute("kin");
		if(Kin==null){
			Errore error=new Errore(1,"Unauthroized Kin");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Bloglikes> Bloglikes=idao.getiBloglikes(Kin.getName());
		return new ResponseEntity<List<Bloglikes>>(Bloglikes,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public ResponseEntity<?> notification(HttpSession session){
		Kin Kin=(Kin)session.getAttribute("kin");
		if(Kin==null){
			Errore error=new Errore(1,"Unauthroized Kin");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Notifications> n=idao.getnotification(Kin.getName());
		return new ResponseEntity<List<Notifications>>(n,HttpStatus.OK);
	}



}
