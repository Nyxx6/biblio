package Pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Db {
	private static String us = "root";
	private static String ps = "";
	private static String url = "jdbc:mysql://localhost:3306/lib_db";
	public static Connection conn = null;
	private static Db db;
	Statement stmt;
	
	private Db() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Db.conn = DriverManager.getConnection(url,us,ps);
			stmt = conn.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Conn Db: "+e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public static Db getInstance() {
	        if (db==null) return (new Db()); 
	        else return null;
	    }
	/*public static void main(String[] args) {
		db = Db.getInstance();
	}*/
	/*BOOK QUERIES
	public Book searchById(int Id) {
		
	}
	public Book searchByTitle(String title) {
		
	}
	searchByAuth
	searchByEdit
	searchByDatePreparedStatement stmt = conn.prepareStatement("insert into Book values('"+ISBN+"','"+Title+"'') ");
	searcBySec
	searchByIsbn*/
	/*MEMBER QUERIES*/
	public boolean validAdmin(String uname, String pwd) {
        try {
        	ResultSet rs = stmt.executeQuery("select * from admin where pseudo='"+uname+"' and pwd='"+pwd+"'");                      
            if(rs.next()) { return true; }   
            } catch (Exception e) { System.out.println(e.toString()); }
        return false;
	}
	
	public ArrayList<Section> getSection() {
		ArrayList<Section> sec = new ArrayList<Section>();
		try {
        	ResultSet rs = stmt.executeQuery("select * from section");                      
        	while (rs.next()) {
                sec.add(new Section(rs.getInt(1), rs.getString(2))); }
            } catch (Exception e) { System.out.println(e.toString()); }
		return sec;
	}
	
	public Type getTypeId(Type s) {
		try {ResultSet rs = stmt.executeQuery("select id from member_type where type='"+s.getType()+"'");
        	if(rs.next()) s.setId(rs.getInt(1));
            } catch (Exception e) { System.out.println(e.toString()); }
		return s;
	}
	
	public ArrayList<Author> getAuth() {
		ArrayList<Author> a = new ArrayList<Author>();
		try {
        	ResultSet rs = stmt.executeQuery("select * from author");                      
        	while (rs.next()) {
                a.add(new Author(rs.getInt(1), rs.getString(2), rs.getString(3))); }
            } catch (Exception e) { System.out.println(e.toString()); }
		return a;
	}
	
	public ArrayList<Editor> getEdit() {
		ArrayList<Editor> e = new ArrayList<Editor>();
		try {
        	ResultSet rs = stmt.executeQuery("select * from editor");                      
        	while (rs.next()) {
                e.add(new Editor(rs.getInt(1), rs.getString(2))); }
            } catch (Exception ex) { System.out.println(ex.toString()); }
		return e;
	}
	
	public Section addSection(Section s) {
		try {
        	stmt.executeUpdate("insert into section (label) values('"+s.getLabel()+"')"); 
        	ResultSet rs = stmt.executeQuery("select id from section where label='"+s.getLabel()+"'");
        	if(rs.next()) s.setId(rs.getInt(1));
            } catch (Exception e) { System.out.println(e.toString()); }
		return s;
	}
	
	public Editor addEdit(Editor s) {
		try {
        	stmt.executeUpdate("insert into editor (name) values('"+s.getName().replace("'", "''")+"')"); 
        	ResultSet rs = stmt.executeQuery("select id from editor where name='"+s.getName().replace("'", "''")+"'");
        	if(rs.next()) s.setId(rs.getInt(1));
            } catch (Exception e) { System.out.println(e.toString()); }
		return s;
	}
	
	public Author addAuth(Author s) {
		try {
        	stmt.executeUpdate("insert into author (name1,name2) values('"+s.getName1().replace("'", "''")+"', '"+s.getName2().replace("'", "''")+"')"); 
        	ResultSet rs = stmt.executeQuery("select id from author where name1='"+s.getName1().replace("'", "''")+"' and name2='"+s.getName2().replace("'", "''")+"'");
        	if(rs.next()) s.setId(rs.getInt(1));
            } catch (Exception e) { System.out.println(e.toString()); }
		return s;
	}
	
	public ArrayList<Type> getTypeMem() {
		ArrayList<Type> e = new ArrayList<Type>();
		try {
        	ResultSet rs = stmt.executeQuery("select * from member_type");                      
        	while (rs.next()) {
                e.add(new Type(rs.getInt(1), rs.getString(2), rs.getInt(3))); }
            } catch (Exception ex) { System.out.println(ex.toString()); }
		return e;
	}
	
	public Type addType(Type s) {
		try {
        	stmt.executeUpdate("insert into member_type (type,nbmax) values('"+s.getType()+"', '"+s.getNbmax()+"')"); 
        	ResultSet rs = stmt.executeQuery("select id from member_type where type='"+s.getType()+"'");
        	if(rs.next()) s.setId(rs.getInt(1));
            } catch (Exception e) { System.out.println(e.toString()); }
		return s;
	}
	
	public Member addMem(Member s) {
		try {
			ResultSet rs = stmt.executeQuery("select id from member where name1='"+s.getName1().replace("'", "''")+"' and name2='"+s.getName2().replace("'", "''")+"'");
			if(rs.next()) return s;
        	stmt.executeUpdate("insert into member (name1,name2,birthd,id_type) values('"+s.getName1().replace("'", "''")+"', '"+s.getName2().replace("'", "''")+"', '"+s.getBirthd()+"', '"+s.getType().getId()+"')"); 
        	rs = stmt.executeQuery("select id from member where name1='"+s.getName1().replace("'", "''")+"' and name2='"+s.getName2().replace("'", "''")+"'");
        	if(rs.next()) s.setId(rs.getInt(1));
            } catch (Exception e) { System.out.println(e.toString()); }
		return s;
	}

	public Book addBook(Book s) {
		try {ResultSet rs; 
		if(s.getType()=="Livre") {
			rs = stmt.executeQuery("select id from book where ISBN='"+s.getIsbn()+"'");
    		if(rs.next()) return s;}
		else {rs = stmt.executeQuery("select id from book where id='"+s.getId()+"'");
			if(rs.next()) return s;}
        	stmt.executeUpdate("insert into book (type,isbn,position,title,id_edit,id_sec) values('"+s.getType()+"', '"+s.getIsbn()+"', '"+s.getPos()+"', '"+s.getTitle().replace("'", "''")+"', '"+s.getEdit().getId()+"', '"+s.getSec().getId()+"')"); 
        	rs = stmt.executeQuery("SELECT max(book.id) from book");
    		s.setId(rs.getInt(1));        	
        	for(Author a : s.getAuth()) {
        	stmt.executeUpdate("insert into book_auth (id_book,id_auth) values('"+s.getId()+"', '"+a.getId()+"')");}
            } catch (Exception e) { System.out.println(e.toString()); }
		return s;
	}
	
	ArrayList<Book> getAllBooks() {
        ArrayList<Book> b = new ArrayList<>();Vector<Author> t;boolean a;int id=0;
        try {
            ResultSet rs = stmt.executeQuery("select book.id,type,isbn,position,title,name1,name2,atdate,editor.name,section.label,available from book,author,book_auth,editor,section where book.id=id_book and author.id=id_auth and id_sec=section.id and id_edit=editor.id ORDER BY book.id");
            while (rs.next()) {a = true;
            	if(b.size()>0 && id==rs.getInt(1)) { id=rs.getInt(1);t = b.get(b.size()-1).getAuth();t.add(new Author(rs.getString(6),rs.getString(7)));}
            	else {	id=rs.getInt(1);t = new Vector<Author>(); if(rs.getInt(11)<1)  a = false;t.add(new Author(rs.getString(6),rs.getString(7)));
            			b.add(new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),t,new Section(rs.getString(10)),new Editor(rs.getString(9)),rs.getString(8),a));
            	}
            }

        } catch (Exception e) {  System.out.println(e);  }

        return b;
    }
	
	ArrayList<Member> getAllMems() {
        ArrayList<Member> b = new ArrayList<>();
        try {boolean a = true;
            ResultSet rs = stmt.executeQuery("select member.id,name1,name2,birthd,atdate,type,nborrowed,state,nodelays from member_type,member where member.id_type=member_type.id");
            while (rs.next()) {if(rs.getInt(8)<1)  a = false;
            	b.add(new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),new Type(rs.getString(6)),rs.getInt(7),a,rs.getInt(9)));
            }}
        catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	
	public Book getBookId(Book s) {
		try {
			ResultSet rs = stmt.executeQuery("select id from book where ISBN='"+s.getIsbn()+"'"); 
        	if(rs.next()) s.setId(rs.getInt(1));
            } catch (Exception e) { System.out.println(e.toString()); }
		return s;
	}
	
	public ArrayList<Member> getMemByN1(Member s) {
	        ArrayList<Member> b = new ArrayList<>();
	        try {boolean a;
	            ResultSet rs = stmt.executeQuery("select member.id,name1,name2,birthd,atdate,type,nborrowed,state,nodelays from member_type,member where name1 LIKE '"+s.getName1().replace("'", "''")+"%' and member.id_type=member_type.id");
	            while (rs.next()) {if(rs.getInt(8)<1)  a = false;else a = true;
	            	b.add(new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),new Type(rs.getString(6)),rs.getInt(7),a,rs.getInt(9)));
	            }} 
	        catch (Exception e) {  System.out.println(e);  }
	        return b;
	    }
	public ArrayList<Member> getMemByN2(Member s) {
        ArrayList<Member> b = new ArrayList<>();
        try {boolean a;
            ResultSet rs = stmt.executeQuery("select member.id,name1,name2,birthd,atdate,type,nborrowed,state,nodelays from member_type,member where name2 LIKE '"+s.getName2().replace("'", "''")+"%' and member.id_type=member_type.id");
            while (rs.next()) {if(rs.getInt(8)<1)  a = false;else a = true;
            	b.add(new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),new Type(rs.getString(6)),rs.getInt(7),a,rs.getInt(9)));
            }} 
        catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	public ArrayList<Member> getMemByN1_N2(Member s) {
        ArrayList<Member> b = new ArrayList<>();
        try {boolean a;
            ResultSet rs = stmt.executeQuery("select member.id,name1,name2,birthd,atdate,type,nborrowed,state,nodelays from member_type,member where name1 LIKE '"+s.getName1().replace("'", "''")+"%' and name2 LIKE '"+s.getName2().replace("'", "''")+"%' and member.id_type=member_type.id");
            while (rs.next()) {if(rs.getInt(8)<1)  a = false;else a = true;
            	b.add(new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),new Type(rs.getString(6)),rs.getInt(7),a,rs.getInt(9)));
            }} 
        catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	public ArrayList<Member> getMemByType(Member s) {
        ArrayList<Member> b = new ArrayList<>();boolean a;ResultSet rs;Type t = new Type();
        t.setType(s.getType().toString()); t=this.getTypeId(t);
        try {
        	rs = stmt.executeQuery("select member.id,name1,name2,birthd,atdate,type,nborrowed,state,nodelays from member_type,member where type= '"+t.getType()+"' and member.id_type=member_type.id");
            while (rs.next()) {if(rs.getInt(8)<1)  a = false;else a = true;
            	b.add(new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),new Type(rs.getString(6)),rs.getInt(7),a,rs.getInt(9)));
            }} 
        catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	public String addBorrow(Borrowed s) {
		try {
			ResultSet rs = stmt.executeQuery("select available from book where id='"+s.getB().getId()+"'");
			if(!rs.next() || rs.getInt(1)<1) return "Livre non disponible!";
			//check if N_user<<N_max (state=0 or member.nborrowed=nbmax or nodelays=5)
			rs = stmt.executeQuery("select state,nborrowed,member_type.id,nbmax from member,member_type where id_type = member_type.id and member.id='"+s.getM().getId()+"'");
			if(rs.next()) { Type t = new Type(rs.getInt(3),rs.getInt(4)); s.getM().setState(rs.getInt(1));s.getM().setNborrowed(rs.getInt(2));
				if(!s.getM().isState()) return "Membre bloqué!!";if(s.getM().getNborrowed()==t.getNbmax()) return "Atteint nombre maximale ("+t.getNbmax()+")!";}
			stmt.executeUpdate("update book SET available = 0 WHERE book.id ='"+s.getB().getId()+"'");
			stmt.executeUpdate("insert into borrowed (id_member,id_book,atdate,duedate) values('"+s.getM().getId()+"', '"+s.getB().getId()+"', '"+s.getAtdate()+"', '"+s.getDuedate()+"')"); 
			stmt.executeUpdate("update member SET nborrowed = nborrowed + 1 WHERE member.id ='"+s.getM().getId()+"'");
		} catch (Exception e) { System.out.println(e.toString()); }
		return "ok";
	}
		
	ArrayList<Borrowed> getAllBorrows() {
        ArrayList<Borrowed> b = new ArrayList<>();
        try {Book book; Member m;
            ResultSet rs = stmt.executeQuery("select name1,name2,ISBN,title,borrowed.atdate,duedate,borrowed.returnedat,borrowed.condition,borrowed.id from book,member,borrowed where id_book=book.id and id_member=member.id");
            while (rs.next()) {
            	m = new Member(); book = new Book();
            	m.setName1(rs.getString(1));m.setName2(rs.getString(2));book.setIsbn(rs.getInt(3));book.setTitle(rs.getString(4));
            	b.add(new Borrowed(rs.getInt(9),m,book,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            	
            }

        } catch (Exception e) {  System.out.println(e);  }

        return b;
    }
	
	public boolean retBorrow(Borrowed s) {
		try {
			ResultSet rs = stmt.executeQuery("select id,duedate,returnedat from borrowed where id_book='"+s.getB().getId()+"' and id_member='"+s.getM().getId()+"'");
			if(rs.next()) { s.setId(rs.getInt(1)); s.setDuedate(rs.getString(2)); s.setReturned(rs.getString(3));
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date d1 = sdf.parse(s.getDuedate());Date d2 = sdf.parse(s.getReturned());
			if((d2.getTime() - d1.getTime())/((1000 * 60 * 60 * 24)%365) != 0) {
				stmt.executeQuery("select nodelays from member where id='"+s.getM().getId()+"'");
				if(rs.next()) {s.getM().setNodelays(rs.getInt(1));
				if(s.getM().getNodelays()==4) stmt.executeUpdate("update member set state = 0 where id='"+s.getM().getId()+"'");
				}
			stmt.executeUpdate("update member set nodelays = nodelays + 1 where id='"+s.getM().getId()+"'");}
			stmt.executeUpdate("update book set available = 1 where id='"+s.getB().getId()+"'");
			stmt.executeUpdate("update borrowed set returnedat = '"+s.getReturned()+"', borrowed.condition = '"+s.getCondition()+"' where borrowed.id = '"+s.getId()+"'"); 
			stmt.executeUpdate("update member SET nborrowed = nborrowed - 1 WHERE member.id ='"+s.getM().getId()+"'");
			return true;}
			
		} catch (Exception e) {  System.out.println(e);  }
		return false;
	}
	
	ArrayList<Book> getBookByIsbn(Book s) {
        ArrayList<Book> b = new ArrayList<>();Vector<Author> t;boolean a;int id=0;
        try {
            ResultSet rs = stmt.executeQuery("select book.id,type,isbn,position,title,name1,name2,atdate,editor.name,section.label,available from book,author,book_auth,editor,section where ISBN LIKE '"+s.getIsbn()+"%' and book.id=id_book and author.id=id_auth and id_sec=section.id and id_edit=editor.id ORDER BY book.id");
            while (rs.next()) {a = true;
            	if(b.size()>0 && id==rs.getInt(1)) { id=rs.getInt(1);t = b.get(b.size()-1).getAuth();t.add(new Author(rs.getString(6),rs.getString(7)));}
            	else {	id=rs.getInt(1);t = new Vector<Author>(); if(rs.getInt(11)<1)  a = false;t.add(new Author(rs.getString(6),rs.getString(7)));
            			b.add(new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),t,new Section(rs.getString(10)),new Editor(rs.getString(9)),rs.getString(8),a));
            	}
            }
        } catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	
	ArrayList<Book> getBookByEdit(Book s) {
        ArrayList<Book> b = new ArrayList<>();Vector<Author> t;boolean a;int id=0;
        try {
            ResultSet rs = stmt.executeQuery("select book.id,type,isbn,position,title,name1,name2,atdate,editor.name,section.label,available from book,author,book_auth,editor,section where editor.name LIKE '%"+s.getEdit()+"%' and book.id=id_book and author.id=id_auth and id_sec=section.id and id_edit=editor.id ORDER BY book.id");
            while (rs.next()) {a = true;
            	if(b.size()>0 && id==rs.getInt(1)) { id=rs.getInt(1);t = b.get(b.size()-1).getAuth();t.add(new Author(rs.getString(6),rs.getString(7)));}
            	else {	id=rs.getInt(1);t = new Vector<Author>(); if(rs.getInt(11)<1)  a = false;t.add(new Author(rs.getString(6),rs.getString(7)));
            			b.add(new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),t,new Section(rs.getString(10)),new Editor(rs.getString(9)),rs.getString(8),a));
            	}
            }
        } catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	
	ArrayList<Book> getBookByAuth(Book s) {
        ArrayList<Book> b = new ArrayList<>();Vector<Author> t;boolean a;
        try {System.out.println(s.getAuth().get(0).getName1());
            ResultSet rs = stmt.executeQuery("select book.id,type,isbn,position,title,name1,name2,atdate,editor.name,section.label,available from book,author,book_auth,editor,section where (author.name1 LIKE '%"+s.getAuth().get(0).getName1()+"%' or author.name2 LIKE '%"+s.getAuth().get(0).getName2()+"%') and book.id=id_book and author.id=id_auth and id_sec=section.id and id_edit=editor.id GROUP by book.id");
            while (rs.next()) {a = true;
            	t = new Vector<Author>(); t.add(new Author(rs.getString(6),rs.getString(7))); if(rs.getInt(11)<1)  a = false;
            	b.add(new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),t,new Section(rs.getString(10)),new Editor(rs.getString(9)),rs.getString(8),a));
            
            }
        } catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	
	ArrayList<Book> getBookByTitle(Book s) {
        ArrayList<Book> b = new ArrayList<>();Vector<Author> t;boolean a;int id=0;
        try {
            ResultSet rs = stmt.executeQuery("select book.id,type,isbn,position,title,name1,name2,atdate,editor.name,section.label,available from book,author,book_auth,editor,section where book.title LIKE '%"+s.getTitle()+"%' and book.id=id_book and author.id=id_auth and id_sec=section.id and id_edit=editor.id ORDER BY book.id");
            while (rs.next()) {a = true;
            	if(b.size()>0 && id==rs.getInt(1)) { id=rs.getInt(1);t = b.get(b.size()-1).getAuth();t.add(new Author(rs.getString(6),rs.getString(7)));}
            	else {	id=rs.getInt(1);t = new Vector<Author>(); if(rs.getInt(11)<1)  a = false;t.add(new Author(rs.getString(6),rs.getString(7)));
            			b.add(new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),t,new Section(rs.getString(10)),new Editor(rs.getString(9)),rs.getString(8),a));
            	}
            }
        } catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	
	ArrayList<Book> getBookBySec(Book s) {
        ArrayList<Book> b = new ArrayList<>();Vector<Author> t;boolean a;int id=0;
        try {
            ResultSet rs = stmt.executeQuery("select book.id,type,isbn,position,title,name1,name2,atdate,editor.name,section.label,available from book,author,book_auth,editor,section where section.label LIKE '%"+s.getSec().getLabel()+"%' and book.id=id_book and author.id=id_auth and id_sec=section.id and id_edit=editor.id ORDER BY book.id");
            while (rs.next()) {a = true;
            	if(b.size()>0 && id==rs.getInt(1)) { id=rs.getInt(1);t = b.get(b.size()-1).getAuth();t.add(new Author(rs.getString(6),rs.getString(7)));}
            	else {	id=rs.getInt(1);t = new Vector<Author>(); if(rs.getInt(11)<1)  a = false;t.add(new Author(rs.getString(6),rs.getString(7)));
            			b.add(new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),t,new Section(rs.getString(10)),new Editor(rs.getString(9)),rs.getString(8),a));
            	}
            }
        } catch (Exception e) {  System.out.println(e);  }
        return b;
    }
	
	
}
