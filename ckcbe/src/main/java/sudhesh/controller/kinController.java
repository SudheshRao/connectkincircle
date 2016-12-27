package sudhesh.controller;

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

import sudhesh.DAO.kinDAOImpl;
import sudhesh.model.Errore;
import sudhesh.model.Kin;

@Controller
public class KinController {
	
	@Autowired
	private kinDAOImpl kindao;

	
	//login a kin
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Kin kin, HttpSession session){
		
	Kin validKin=kindao.authenticate(kin);
	if(validKin==null){
		Errore error=new Errore(1,"Username and password doesnt exists...");
		return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED); 
	}
	else{
		Kin checkkin=kindao.getKinByName(kin.getName());
		if(checkkin.isStatus()==true){
			
			session.setAttribute("kin", validKin);
			validKin.setIsonline(true);
			kindao.updateKin(validKin); 
			session.setAttribute("profile", validKin);
			return new ResponseEntity<Kin>(validKin,HttpStatus.OK);
		}
		else{
			Errore error=new Errore(3,"you are still not authorized....");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
			}
	}

	}
	
	//logout a kin
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<Kin> logout(HttpSession session)	{
		Kin validKin = (Kin) session.getAttribute("kin");
		if(validKin!=null){
			validKin.setIsonline(false);
			kindao.updateKin(validKin); 
		}
		session.removeAttribute("kin");
		session.invalidate();
		return new ResponseEntity<Kin>(HttpStatus.OK);
	}

	//register a Kin
		@RequestMapping(value="/Kin",method=RequestMethod.POST)
		public ResponseEntity<?> createKin(@RequestBody Kin Kin){
			Kin checkkin=kindao.getKinByName(Kin.getName());
			if(checkkin==null){
				
			Kin.setStatus(false);
			Kin.setIsonline(false);
			Kin savedkin = kindao.saveKin(Kin);
			if(savedkin.getId()==0){
				Errore error=new Errore(2,"Couldnt insert user details ");
				return new ResponseEntity<Errore>(error , HttpStatus.CONFLICT);
			}
			else
				return new ResponseEntity<Kin>(savedkin,HttpStatus.OK);
			}
			else
			{
				Errore error=new Errore(2,"Couldnt insert user details ");
				return new ResponseEntity<Errore>(error , HttpStatus.CONFLICT);
			}
				
		}
		
		

	//get all Kins
	@RequestMapping(value="/Kin",method=RequestMethod.GET)
	public  ResponseEntity<List<Kin>> getAllKins(){
		
		List<Kin> Kins=kindao.getAllKins();
	
		if(Kins.isEmpty())
			return new ResponseEntity<List<Kin>>(HttpStatus.NO_CONTENT);
	
			return new ResponseEntity<List<Kin>>(Kins,HttpStatus.OK);
	}
	
	//get Kin by id
	@RequestMapping(value="/Kin/{id}",method=RequestMethod.GET)
	public ResponseEntity<Kin> getKinById(@PathVariable(value="id") int id){
	
		Kin Kin=kindao.getKinById(id);
	
		if(Kin==null)
			return new ResponseEntity<Kin>(HttpStatus.NOT_FOUND);
	
			return new ResponseEntity<Kin>(Kin,HttpStatus.OK);
		
	}

	//update a Kin
	@RequestMapping(value="/Kin/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Kin> updateKin(@PathVariable int id,@RequestBody Kin Kin){
		
		Kin updatedKin=kindao.updateKin(Kin);
		
		if(Kin==null)
			return new ResponseEntity<Kin>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<Kin>(updatedKin,HttpStatus.OK);	
			
	}
	
	//delete a Kin
	@RequestMapping(value="/Kin/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteKin(@PathVariable int id){
		Kin Kin=kindao.getKinById(id);
		if(Kin==null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		else{
		kindao.deleteKin(id);
		return new ResponseEntity<Void>(HttpStatus.OK);}
	}

	

}
