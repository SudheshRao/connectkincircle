package sudhesh.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sudhesh.DAO.adminDaoImpl;
import sudhesh.DAO.kinDAOImpl;
import sudhesh.model.Badges;
import sudhesh.model.Kin;

@Controller
public class AdminController {
	@Autowired
	private adminDaoImpl admindao;
	@Autowired
	private kinDAOImpl kindao;
	
	//get kins for access provision
	@RequestMapping(value="/provideaccess",method=RequestMethod.GET)
	public  ResponseEntity<List<Kin>> getKinsforAccess(){
		List<Kin> Kins=admindao.getKinsforAccess();	
		if(Kins.isEmpty())
			return new ResponseEntity<List<Kin>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Kin>>(Kins,HttpStatus.OK);
	}
	
	//get kins to deny access provision
		@RequestMapping(value="/denyaccess",method=RequestMethod.GET)
		public  ResponseEntity<List<Kin>> getKinstoDenyAccess(){
			List<Kin> Kins=admindao.getKinstoDenyAccess();
		
			if(Kins.isEmpty())
				return new ResponseEntity<List<Kin>>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<List<Kin>>(Kins,HttpStatus.OK);
		}
		
		//get kins to make assista dmin
		@RequestMapping(value="/assistadmin",method=RequestMethod.GET)
		public  ResponseEntity<List<Kin>> assistadmin(){
			
			List<Kin> Kins=admindao.getKinstoAssistAdmin();
		
			if(Kins.isEmpty())
				return new ResponseEntity<List<Kin>>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<List<Kin>>(Kins,HttpStatus.OK);
		}		
	
		
	//permit access
	@RequestMapping(value="/permit",method=RequestMethod.POST)
	public  ResponseEntity<?> permit(@RequestBody Kin kin) throws IOException{
		kin.setStatus(true);
		kindao.updateaKin(kin);
		return new ResponseEntity<Kin>(HttpStatus.OK);
	}
	
	//deny access
	@RequestMapping(value="/deny",method=RequestMethod.POST)
	public  ResponseEntity<?> deny(@RequestBody Kin kin){
		try{
     		File file=new File("C:/Users/K.S.Raghavendra Bhat/workspace/ckcfe/WebContent/resources/dp/"+kin.getName());
     		file.delete();	
     		}catch(Exception e){e.printStackTrace();}
		kindao.deleteKin(kin.getId());
		return new ResponseEntity<Kin>(HttpStatus.OK);
	}
	
	//deny access temporarily
		@RequestMapping(value="/denytemp",method=RequestMethod.POST)
		public  ResponseEntity<?> denytemp(@RequestBody Kin kin){
			kin.setStatus(false);
			kindao.updateaKin(kin);
			return new ResponseEntity<Kin>(HttpStatus.OK);
		}
		
		//make assist admin
		@RequestMapping(value="/makeassistadmin/{id}",method=RequestMethod.POST)
		public ResponseEntity<Void> makeassistadmin(@PathVariable int id){
			Kin Kin=kindao.getKinById(id);
			Kin.setRole("assistadmin");
			kindao.updateaKin(Kin);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		//changerole
				@RequestMapping(value="/changerole",method=RequestMethod.POST)
				public ResponseEntity<Void> Changerole(@RequestBody Kin kin){
					Kin Kin=kindao.getKinById(kin.getId());
					Kin.setRole(kin.getRole());
					kindao.updateaKin(Kin);
					return new ResponseEntity<Void>(HttpStatus.OK);
				}
	//fetch badges
	@RequestMapping(value="/fetchbadges",method=RequestMethod.GET)
	public  ResponseEntity<?> fetchbadges(){
		List<Kin> Kins=admindao.getKinsforAccess();
		List<Kin> allkins=kindao.getAllKins();
		List<Kin> skins=admindao.getKinstoDenyAccess();
		int statd=skins.size();
		int stat=Kins.size();
		int kins=allkins.size();
		Badges badges = new Badges(kins, stat, statd);
		return new ResponseEntity<Badges>(badges,HttpStatus.OK);
	}

}
