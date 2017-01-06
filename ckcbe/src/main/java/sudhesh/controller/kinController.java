package sudhesh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

import sudhesh.DAO.UploadFileDaoImpl;
import sudhesh.DAO.kinDAOImpl;
import sudhesh.model.Errore;
import sudhesh.model.Kin;
import sudhesh.model.UploadFile;

@Controller
public class KinController {
	
	@Autowired
	private kinDAOImpl kindao;

	@Autowired
	private UploadFileDaoImpl uploadFileDao;
		
	//login a kin
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Kin kin, HttpSession session){		
		
		Kin validKin=kindao.authenticate(kin);//authenicate the kin
		
		if(validKin==null){
			Errore error=new Errore(1,"Username and password doesnt exists...");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED); }//return error if he doesnt exist
		else{		
			//check for status(whether admin has provided the access or not)
			if(validKin.isStatus()==true){
				//if authorized login
				session.setAttribute("kin", validKin);//store kin in a session attribute
				validKin.setIsonline(true);//set online
				kindao.updateKin(validKin);//update kin
				return new ResponseEntity<Kin>(validKin,HttpStatus.OK);//return complete details of kin
			}
			else{//if not authorized
				Errore error=new Errore(3,"you are still not authorized....Contact Admin");
				return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);//return an error
				}
			}
	}
	
	//logout a kin
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<Kin> logout(HttpSession session){
		
		Kin kin = (Kin) session.getAttribute("kin");
		
		if(kin!=null){//if kin exists
			kin.setIsonline(false);//set kin offline
			kindao.updateKin(kin);}//update kin
		
		session.removeAttribute("kin");//remove kin from session
		session.invalidate();//invalidate the session
		return new ResponseEntity<Kin>(HttpStatus.OK);//return the status
	}

	//register a Kin
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponseEntity<?> signup(@RequestBody Kin Kin,HttpSession session) throws IOException{
		
		Kin checkkin=kindao.getKinByName(Kin.getName());
		
		if(checkkin==null){//if there is no kin by the name 
			System.out.println(Kin.getPassword() +"  "+ Kin.getConfirmpassword());
			if(Kin.getPassword().equals(Kin.getConfirmpassword())){
				Kin.setStatus(false);
				Kin.setIsonline(false);
				Kin savedkin = kindao.saveKin(Kin);
				session.setAttribute("kini",savedkin);//save kin
				//save initial dp as the logo of ckc
		        FileInputStream fin= new FileInputStream("C:/Users/K.S.Raghavendra Bhat/workspace/ckcfe/WebContent/resources/images/connectkincircle1.png");
		        FileOutputStream fout=new FileOutputStream("C:/Users/K.S.Raghavendra Bhat/workspace/ckcfe/WebContent/resources/dp/"+savedkin.getName());  
		        int i=0;  
		        while((i=fin.read())!=-1){  
		        fout.write((byte)i);  
		        }
		        fout.close();
		        fin.close();
				return new ResponseEntity<Kin>(savedkin,HttpStatus.OK);//return kin in case saved
			}
			else{
				Errore error=new Errore(5,"passwords dont match");
				return new ResponseEntity<Errore>(error , HttpStatus.CONFLICT);
				}//return error if kin could not be saved
		}else{
			Errore error=new Errore(2,"Couldnt insert user details ");
			return new ResponseEntity<Errore>(error , HttpStatus.CONFLICT);//return error if kin could not be saved
		}
		
	}		

	//get all Kins
	@RequestMapping(value="/getallkins",method=RequestMethod.GET)
	public  ResponseEntity<List<Kin>> getAllKins(){
		
		List<Kin> Kins=kindao.getAllKins();
	
		if(Kins.isEmpty())
			return new ResponseEntity<List<Kin>>(HttpStatus.NO_CONTENT);
	
			return new ResponseEntity<List<Kin>>(Kins,HttpStatus.OK);
	}
	
	//get Kin by id
	@RequestMapping(value="/getkinbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<Kin> getKinById(@PathVariable(value="id") int id){
	
		Kin Kin=kindao.getKinById(id);
	
		if(Kin==null)
			return new ResponseEntity<Kin>(HttpStatus.NOT_FOUND);
	
			return new ResponseEntity<Kin>(Kin,HttpStatus.OK);
		
	}

	//update a Kin
	@RequestMapping(value="/updatekin/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Kin> updateKin(@PathVariable int id,@RequestBody Kin Kin){
		
		Kin updatedKin=kindao.updateKin(Kin);
		
		if(Kin==null)
			return new ResponseEntity<Kin>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<Kin>(updatedKin,HttpStatus.OK);	
			
	}
	
	//delete a Kin
	@RequestMapping(value="/deletekin/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteKin(@PathVariable int id){
		
		Kin Kin=kindao.getKinById(id);
		try{//delete kins dp in frontend
     		File file=new File("C:/Users/K.S.Raghavendra Bhat/workspace/ckcfe/WebContent/resources/dp/"+Kin.getName());
     		file.delete();	
     		}catch(Exception e){e.printStackTrace();}
		
        UploadFile uploadFile = uploadFileDao.getFile(Kin.getName());
        if(uploadFile!=null)
        uploadFileDao.deletepic(uploadFile);//delete kins dp in DB
		kindao.deleteKin(id);//delete kin
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	

}
