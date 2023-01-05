package Pack;

import java.util.Vector;

public class Book {
	private int id;
	private String type;
	private int isbn = 0;
	private String pos;
	private String title;
	private Vector<Author> auth;
	private Section sec;
	private Editor edit;
	private String date;
	private boolean available = true;
	public Book(int id, String type, int isbn, String pos, String title, Vector<Author> auth, Section sec, Editor edit, String date) {
		this.id = id = 0;
		this.type = type;
		this.isbn = isbn;
		this.pos = pos;
		this.title = title;
		this.auth = auth;
		this.sec = sec;
		this.edit = edit;
		this.date = date;
	}
	public Book(int id, String type, int isbn, String pos, String title, Vector<Author> auth, Section sec, Editor edit, String date, boolean a) {
		this.id = id = 0;
		this.type = type;
		this.isbn = isbn;
		this.pos = pos;
		this.title = title;
		this.auth = auth;
		this.sec = sec;
		this.edit = edit;
		this.date = date;
		available = a;
	}
	public Book(String type, int isbn, String pos, String title, Vector<Author> auth, Section sec, Editor edit, String date) {
		this.isbn = isbn;
		this.type = type;
		this.pos = pos;
		this.title = title;
		this.auth = auth;
		this.sec = sec;
		this.edit = edit;
		this.date = date;
	}
	public Book() {
		this.id = 0;
		this.isbn = 0;
		this.pos = "";
		this.title = "";
		this.auth = null;
		this.sec = null;
		this.edit = null;
		this.date = "";
		this.available = false;
	}
	public Book(int id, String type, int isbn, String pos, String title, Section sec, Editor edit, String date, boolean a) {
		this.id = id = 0;
		this.type = type;
		this.isbn = isbn;
		this.pos = pos;
		this.title = title;
		this.sec = sec;
		this.edit = edit;
		this.date = date;
		available = a;
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
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Vector<Author> getAuth() {
		return auth;
	}
	public String ListAuth() {
		String s = auth.get(0).toString()+"\n";
		for(int i=1; i<auth.size();i++) {
			s = s + auth.get(i).toString();
		}
		return s;
	}
	public void setAuth(Vector<Author> auth) {
		this.auth = auth;
	}
	public Section getSec() {
		return sec;
	}
	public void setSec(Section sec) {
		this.sec = sec;
	}
	public Editor getEdit() {
		return edit;
	}
	public void setEdit(Editor edit) {
		this.edit = edit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isAvailable() {
		return available;
	}
	public String Available() {
		if(isAvailable())
		return "Disponible";
		return "Non Disponible";
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
