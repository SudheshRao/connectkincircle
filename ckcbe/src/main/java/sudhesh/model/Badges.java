package sudhesh.model;

public class Badges {
	int kins;
	int stat;
	int statd;
	public Badges(int kins,int stat, int statd){
		super();
		this.kins = kins;
		this.stat = stat;
		this.statd = statd;
	}

	public int getKins() {
		return kins;
	}
	public void setKins(int kins) {
		this.kins = kins;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int statd) {
		this.stat = statd;
	}
	public int getStatd() {
		return statd;
	}
	public void setStatd(int statd) {
		this.statd = statd;
	}

}
