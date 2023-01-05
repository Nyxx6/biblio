package Pack;

public class Borrowed {
	private int id;
	private Member m;
	private Book b;
	private String atdate;
	private String duedate;
	private String returned;
	private String condition;
	public Borrowed() {}
	public Borrowed(Member m, Book b, String atdate, String duedate) {
		this.m = m;
		this.b = b;
		this.atdate = atdate;
		this.duedate = duedate;
	}
	public Borrowed(Member m, Book b, String duedate, String returned, String condition) {
		this.m = m;
		this.b = b;
		this.duedate = duedate;
		this.returned = returned;
		this.condition = condition;
	}
	public Borrowed(int id, Member m, Book b, String atdate, String duedate, String returned, String condition) {
		this.id = id;
		this.m = m;
		this.b = b;
		this.atdate = atdate;
		this.duedate = duedate;
		this.returned = returned;
		this.condition = condition;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getM() {
		return m;
	}
	public void setM(Member m) {
		this.m = m;
	}
	public Book getB() {
		return b;
	}
	public void setB(Book b) {
		this.b = b;
	}
	public String getAtdate() {
		return atdate;
	}
	public void setAtdate(String atdate) {
		this.atdate = atdate;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getReturned() {
		return returned;
	}
	public void setReturned(String returned) {
		this.returned = returned;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
