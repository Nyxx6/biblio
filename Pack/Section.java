package Pack;

public class Section {
	private int id;
	private String label;
	
	public Section(int id,String label) {
		this.id = id;
		this.label = label;
	}
	
	public Section() {}

	public Section(String l) {
		label = l;	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	public String toString() {
		return getLabel();
	}
}