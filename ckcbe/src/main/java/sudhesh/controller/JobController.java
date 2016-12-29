package sudhesh.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sudhesh.DAO.jobDaoImpl;
import sudhesh.model.Errore;
import sudhesh.model.Job;
import sudhesh.model.Kin;


@Controller
public class JobController {
	
	@Autowired
	private jobDaoImpl jobdao;

	@RequestMapping(value="/postJob",method=RequestMethod.POST)
	public ResponseEntity<?> postJob(@RequestBody Job job,HttpSession session){
		
		Kin user=(Kin)session.getAttribute("kin");	
		if(user==null){	
			Errore error=new Errore(1,"Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);
		}
		else{
			        job.setPostedby(user.getName());
			        job.setPostedbyId(user.getId());
	                job.setPostedon(Calendar.getInstance().getTime());
					jobdao.postJob(job);
				return new ResponseEntity<Void>(HttpStatus.OK);
			
	}
	
}
    @RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
    public ResponseEntity<?> getAllJobs(HttpSession session){
    	Kin user=(Kin)session.getAttribute("kin");
    	if(user==null){
    		Errore error=new Errore(1,"Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);//401
    	}
    	List<Job> jobs=jobdao.getAllJobs();
    	return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
    }
    

}
