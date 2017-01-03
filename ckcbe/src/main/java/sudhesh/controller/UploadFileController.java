package sudhesh.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sudhesh.DAO.UploadFileDaoImpl;
import sudhesh.model.Kin;
import sudhesh.model.UploadFile;

@Controller
public class UploadFileController {

		@Autowired
		private UploadFileDaoImpl uploadFileDao;
		
		@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	    public String handleFileUpload( HttpSession session,@RequestParam CommonsMultipartFile fileUpload) throws Exception {
	         Kin kin=(Kin)session.getAttribute("kin");
	         if(kin==null)
	        	 throw new RuntimeException("Not logged in");
	         
	         if (fileUpload != null ) {
	             CommonsMultipartFile aFile = fileUpload;
	 			
	                UploadFile uploadFile = uploadFileDao.getFile(kin.getName());
	                if(uploadFile!=null){
	                uploadFile.setFileName(aFile.getOriginalFilename());
	                uploadFile.setData(aFile.getBytes());
	                uploadFileDao.update(uploadFile);
	                }
	                UploadFile getUploadFile=uploadFileDao.getFile(kin.getName());
		             byte[] imagefiles=getUploadFile.getData();  
		         	try{
		         		File file=new File("C:/Users/K.S.Raghavendra Bhat/workspace/ckcfe/WebContent/resources/dp/"+kin.getName());
		         		FileOutputStream fos = new FileOutputStream(file);
		         		fos.write(imagefiles);
		         		fos.close();
		         		}catch(Exception e){
		         		e.printStackTrace();
		         		}
		      	               
	         }
	 
	         return "redirect:http://localhost:8080/ckcfe/";
	    }	
		
		@RequestMapping(value = "/initialUpload", method = RequestMethod.POST)
	    public String initialFileUpload( HttpSession session,@RequestParam CommonsMultipartFile fileUpload) throws Exception {
	         Kin kin=(Kin)session.getAttribute("kini");
	         if(kin==null)
	        	 throw new RuntimeException("Not logged in");
	         
	         if (fileUpload != null ) {
	             CommonsMultipartFile aFile = fileUpload;
	             UploadFile uploadFile = new UploadFile();
	                uploadFile.setFileName(aFile.getOriginalFilename());
	                uploadFile.setData(aFile.getBytes()); 
	                uploadFile.setKin(kin.getName());
	                uploadFileDao.save(uploadFile);
	                
		                UploadFile getUploadFile=uploadFileDao.getFile(kin.getName());
			             byte[] imagefiles=getUploadFile.getData();  
			         	try{
			         		File file=new File("C:/Users/K.S.Raghavendra Bhat/workspace/ckcfe/WebContent/resources/dp/"+kin.getName());
			         		FileOutputStream fos = new FileOutputStream(file);
			         		fos.write(imagefiles);
			         		fos.close();
			         		}catch(Exception e){
			         		e.printStackTrace();
			         		}
	                    session.removeAttribute("kini");
	            		session.invalidate();
	         }
	 
	         return "redirect:http://localhost:8080/ckcfe/";
	    }	
	    	@RequestMapping(value="/getFile",method=RequestMethod.GET)
	    	public ResponseEntity<?> getFile(HttpSession session){
	    		
	    		Kin kin=(Kin)session.getAttribute("kin");
	    	UploadFile uploadFile=uploadFileDao.getFile(kin.getName());
	    	byte[] imagefiles=uploadFile.getData();
	    	return new ResponseEntity<byte[]>(imagefiles,HttpStatus.OK);
	    }


}
