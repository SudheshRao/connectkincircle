package sudhesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sudhesh.DAO.adminDaoImpl;
import sudhesh.model.Kin;

@Controller
public class adminController {
	@Autowired
	private adminDaoImpl admindao;
	
	//get kins for access provision
	@RequestMapping(value="/provideaccess",method=RequestMethod.GET)
	public  ResponseEntity<List<Kin>> getKinsforAccess(){
		System.out.println("hi");
		List<Kin> Kins=admindao.getKinsforAccess();
	
		if(Kins.isEmpty())
			return new ResponseEntity<List<Kin>>(HttpStatus.NO_CONTENT);
	
			return new ResponseEntity<List<Kin>>(Kins,HttpStatus.OK);
	}

}
