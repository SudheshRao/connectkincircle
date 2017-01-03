package sudhesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class UploadFile {
	
	@Id
	@GeneratedValue
	private int fid;
	private String fileName;
	private String kin;
	@Lob   
	@Column(name = "FILE_DATA")
	private byte[] data;
	
	

	public String getKin() {
		return kin;
	}

	public void setKin(String kin) {
		this.kin = kin;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	

}
