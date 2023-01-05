package Pack;

public class Member {
	public Member(int id, String name1, String name2, String birthd, String atdate, Type type, int nborrowed,
			boolean state, int nodelays) {
		this.id = id;
		this.name1 = name1;
		this.name2 = name2;
		this.birthd = birthd;
		this.atdate = atdate;
		this.type = type;
		this.nborrowed = nborrowed;
		this.state = state;
		this.nodelays = nodelays;
	}

	public Member() {
	}

	private int id = 0;
	private String name1;
	private String name2;
	private String birthd;
	private String atdate;
	private Type type;
	private int nborrowed;
	private boolean state = true;	
	private int nodelays = 0;
	
	public Member(String name1, String name2, String birthd, Type type) {
		this.name1 = name1;
		this.name2 = name2;
		this.birthd = birthd;
		this.type = type;
	}
	
	public Member(int id, String name1, String name2, String birthd, Type type) {
		this.id = id;
		this.name1 = name1;
		this.name2 = name2;
		this.birthd = birthd;
		this.type = type;
	}
	
	public Member(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {		
		this.id = id;
	}
	
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getBirthd() {
		return birthd;
	}

	public void setBirthd(String birthd) {
		this.birthd = birthd;
	}

	public String getAtdate() {
		return atdate;
	}

	public void setAtdate(String atdate) {
		this.atdate = atdate;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getNborrowed() {
		return nborrowed;
	}

	public void setNborrowed(int nborrowed) {
		this.nborrowed = nborrowed;
	}

	public boolean isState() {
		return state;
	}
	public String state() {
		if(isState())
		return "";
		return "Bloqué";
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public void setState(int state) {
		if(state>0) this.state = true;
		else this.state = false;
	}

	public int getNodelays() {
		return nodelays;
	}

	public void setNodelays(int nodelays) {
		this.nodelays = nodelays;
	}
	
}
