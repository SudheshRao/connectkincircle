package sudhesh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notifications {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nid;
	private String fromkin;
	private String tokin;
	private String notifiy;
	private Date tim;
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getFromkin() {
		return fromkin;
	}
	public void setFromkin(String fromkin) {
		this.fromkin = fromkin;
	}
	public String getTokin() {
		return tokin;
	}
	public void setTokin(String tokin) {
		this.tokin = tokin;
	}
	public String getNotifiy() {
		return notifiy;
	}
	public void setNotifiy(String notifiy) {
		this.notifiy = notifiy;
	}
	public Date getTim() {
		return tim;
	}
	public void setTim(Date tim) {
		this.tim = tim;
	}

	
	

}
