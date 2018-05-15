public class iris{
	
	private double sepalLen;
	private double sepalWid;
	private double petalLen;
	private double petalWid;
	private String type;
	
	public iris(double sepalLen, double sepalWid, double petalLen, double petalWid, String type) {
		this.sepalLen = sepalLen;
		this.sepalWid = sepalWid;
		this.petalLen = petalLen;
		this.petalWid = petalWid;
		this.type = type;
	}
	
	public double getSepalLen() {
		return sepalLen;
	}


	public void setSepalLen(double sepalLen) {
		this.sepalLen = sepalLen;
	}


	public double getSepalWid() {
		return sepalWid;
	}


	public void setSepalWid(double sepalWid) {
		this.sepalWid = sepalWid;
	}


	public double getPetalLen() {
		return petalLen;
	}


	public void setPetalLen(double petalLen) {
		this.petalLen = petalLen;
	}


	public double getPetalWid() {
		return petalWid;
	}


	public void setPetalWid(double petalWid) {
		this.petalWid = petalWid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
}
