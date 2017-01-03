package sudhesh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JobApplied {
	
	@Id
	@GeneratedValue
	private int ajid;
	private int jobid;
	private int kinid;
	private String jobtitle;
	private String kin;
	public int getAjid() {
		return ajid;
	}
	public void setAjid(int ajid) {
		this.ajid = ajid;
	}
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public int getKinid() {
		return kinid;
	}
	public void setKinid(int kinid) {
		this.kinid = kinid;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}
	
}
