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

import sudhesh.DAO.jobDaoImpl;
import sudhesh.model.Errore;
import sudhesh.model.Job;
import sudhesh.model.JobApplied;
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
			
	}}
	
	
		@RequestMapping(value="/removeJob/{id}",method=RequestMethod.DELETE)
		public ResponseEntity<?> removeJob(@PathVariable(value="id") int id){
			jobdao.removejob(id);
			return new ResponseEntity<Void>(HttpStatus.OK);		
		}

		@RequestMapping(value="/jobremove/{id}",method=RequestMethod.DELETE)
		public ResponseEntity<?> Jobremove(@PathVariable(value="id") int id,HttpSession session){
			Kin kin=(Kin)session.getAttribute("kin");
			jobdao.jobremove(id,kin.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);		
		}
		
		@RequestMapping(value="/applyjob/{id}",method=RequestMethod.POST)
		public ResponseEntity<?> ApplyJob(@PathVariable(value="id") int id,HttpSession session){
			Kin kin=(Kin)session.getAttribute("kin");	
			Job job=jobdao.getjob(id);
			JobApplied ajob = jobdao.getAppliedJobbyJid(id,kin.getId());
			if(ajob==null){
			JobApplied ja=new JobApplied();
			ja.setJobid(id);
			ja.setJobtitle(job.getJobtitle());
			ja.setKinid(kin.getId());
			ja.setKin(kin.getName());
			jobdao.applyjob(ja);
			return new ResponseEntity<Void>(HttpStatus.OK);	}
			else
				return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@RequestMapping(value="/appliedjob",method=RequestMethod.GET)
		public ResponseEntity<?> Appliedjob(HttpSession session){
			Kin kin=(Kin)session.getAttribute("kin");	
			List<JobApplied> job = jobdao.getAppliedJob(kin.getId());
			List<Job> cjob = new ArrayList<Job>();
			for(JobApplied j : job){
				Job jb = jobdao.getjob(j.getJobid());
				cjob.add(jb);
			}
			return new ResponseEntity<List<Job>>(cjob,HttpStatus.OK);		
		}

		@RequestMapping(value="/faj",method=RequestMethod.GET)
		public ResponseEntity<?> Faj(HttpSession session){
			Kin kin=(Kin)session.getAttribute("kin");	
			List<JobApplied> job = jobdao.getAppliedJob(kin.getId());
			return new ResponseEntity<List<JobApplied>>(job,HttpStatus.OK);		
		}	
		@RequestMapping(value="/appliedjobdetails/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> Appliedjobdetails(@PathVariable(value="id") int id){
			List<JobApplied> job = jobdao.getAppliedJobDetails(id);
			return new ResponseEntity<List<JobApplied>>(job,HttpStatus.OK);		
		}	
    @RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
    public ResponseEntity<?> getAllJobs(HttpSession session){
    	Kin kin=(Kin)session.getAttribute("kin");
    	if(kin==null){
    		Errore error=new Errore(1,"Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Errore>(error,HttpStatus.UNAUTHORIZED);//401
    	}
    	List<Job> jobs=jobdao.getAllJobs();
    	return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
    }
    

}
