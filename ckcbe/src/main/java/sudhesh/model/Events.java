package sudhesh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="events")
public class Events {
	
	@Id
	@GeneratedValue
	private int id;
	private Date postedOn;
	private String postedBy;
	private String title;
	@Lob
	private String body;
	private Date eventon;
	private String heldat;
	private int attending;
	private int maybe;
	private int notattending;
	
	
	public int getAttending() {
		return attending;
	}
	public void setAttending(int attending) {
		this.attending = attending;
	}
	public int getMaybe() {
		return maybe;
	}
	public void setMaybe(int maybe) {
		this.maybe = maybe;
	}
	public int getNotattending() {
		return notattending;
	}
	public void setNotattending(int notattending) {
		this.notattending = notattending;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getEventon() {
		return eventon;
	}
	public void setEventon(Date eventon) {
		this.eventon = eventon;
	}
	public String getHeldat() {
		return heldat;
	}
	public void setHeldat(String heldat) {
		this.heldat = heldat;
	}
	


}
