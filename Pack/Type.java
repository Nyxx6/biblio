package Pack;

public class Type {
	private int id;
	private String type;
	private int nbmax;	
	
	public Type() {}
	public Type(int id, String type, int nbmax) {
		this.id = id;
		this.type = type;
		this.nbmax = nbmax;
	}
	public Type(int id, int nbmax) {
		this.id = id;
		this.nbmax = nbmax;
	}
	
	public Type(String t) {
	type = t;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNbmax() {
		return nbmax;
	}

	public void setNbmax(int nbmax) {
		this.nbmax = nbmax;
	}
	
	public String toString() {
		return getType();
	}
}