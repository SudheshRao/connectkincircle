package sudhesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
			return new ResponseEntity<List<Kin>>(Kins,HttpStatus.OK);
	}
	
	//permit access
	@RequestMapping(value="/permit",method=RequestMethod.POST)
	public  ResponseEntity<?> permit(@RequestBody Kin kin){
		kin.setStatus(true);
		kindao.updateaKin(kin);
		System.out.println(kin.isStatus());
		return new ResponseEntity<Kin>(HttpStatus.OK);
	}
	
	//deny access
	@RequestMapping(value="/deny",method=RequestMethod.POST)
	public  ResponseEntity<?> deny(@RequestBody Kin kin){
		System.out.println("hi");
		kindao.deleteKin(kin.getId());
		return new ResponseEntity<Kin>(HttpStatus.OK);
	}
	
	//fetch badges
	@RequestMapping(value="/fetchbadges",method=RequestMethod.GET)
	public  ResponseEntity<?> fetchbadges(){
		List<Kin> Kins=admindao.getKinsforAccess();
		List<Kin> allkins=kindao.getAllKins();
		int stat=Kins.size();
		int kins=allkins.size();
		Badges badges = new Badges(kins, stat);
		return new ResponseEntity<Badges>(badges,HttpStatus.OK);
	}

}
