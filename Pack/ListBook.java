package Pack;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;

public class ListBook {

	public JFrame frame;
	private JTextField isbn,title;
	private JComboBox<Section> sec;
	private JComboBox<Author> cba;
	private JComboBox<Editor> edit;
	private JSpinner edate;
	private ButtonGroup bg;
	private JRadioButton r,r1,r2;
	private Db db = Db.getInstance();
	ArrayList<Section> s = new ArrayList<Section>();
	ArrayList<Author> a = new ArrayList<Author>();
	ArrayList<Editor> e = new ArrayList<Editor>();
	private ArrayList<Book> list = db.getAllBooks();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListBook window = new ListBook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void get_type(String s) {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	     Object rowData[] = new Object[10];
	     for(int i = 0; i < list.size(); i++)
	        { if(list.get(i).getType().equals(s))
	        	model=add_book_to_table(model,rowData,list.get(i));} 
	}
	/**
	 * Create the application.
	 */
	private DefaultTableModel add_book_to_table(DefaultTableModel model,Object rowData[],Book b) {
		rowData[0] = b.getIsbn();
        rowData[1] = b.getType();
        rowData[2] = b.getPos();
        rowData[3] = b.getTitle();
        rowData[4] = b.ListAuth();
        rowData[5] = b.getDate();
        rowData[6] = b.getEdit();
        rowData[7] = b.getSec();
        rowData[8] = b.Available();
        model.addRow(rowData);return model;
	}
	private void getBooks() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	     Object rowData[] = new Object[10];
		 for(int i = 0; i < list.size(); i++)
	        { model=add_book_to_table(model,rowData,list.get(i));} 
	}
	
	public ListBook() {
		s.add(new Section());
		s.addAll(db.getSection());
		a.add(new Author());
		a.addAll(db.getAuth());
		e.add(new Editor());
		e.addAll(db.getEdit());
		initialize();
		getBooks();
	}
	public String getradio() {
        if (r1.isSelected()) {  return r1.getText();  }
        else if(r2.isSelected()) return r2.getText();        
	return "Book";}
    public void get_av(JCheckBox c) {
    	list = db.getAllBooks();
    	ArrayList<Book> book = new ArrayList<Book>();
    	if(c.isSelected()) {
    		for(int i=0; i<list.size();i++) {
    			if(list.get(i).isAvailable()) book.add(list.get(i));
    		}
    	} else {
    		for(int i=0; i<list.size();i++) {
    			if(!list.get(i).isAvailable()) book.add(list.get(i));
    		}
    	}
    	list=book;getBooks();
    }
	/* Save authors from cba to txt area */
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 13));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(NewBook.class.getResource("/Pack/logo.png")));
		frame.setBounds(100, 100, 851, 419);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLUE, Color.DARK_GRAY));
		panel.setBounds(15, 42, 810, 171);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel l = new JLabel("Chercher un ouvrage");
		l.setFont(new Font("Arial Black", Font.PLAIN, 14));
		l.setBounds(335, 11, 165, 20);
		frame.getContentPane().add(l);
		
		isbn = new JTextField();
		isbn.addMouseListener(new MouseAdapter() { 
		    public void mouseExited(MouseEvent me) { 
		    	if(!isbn.getText().isBlank()) {
		    	Book b = new Book();
		    	b.setIsbn(Integer.parseInt(isbn.getText()));
		        list = db.getBookByIsbn(b);
		        getBooks();}
		       } 
		     });
		isbn.setFont(new Font("Verdana", Font.PLAIN, 12));
		isbn.setBounds(10, 33, 140, 22);
		panel.add(isbn);
		isbn.setColumns(10);

		r = new JRadioButton("ISBN du Livre");
		r.setSelected(true);
		r.setFont(new Font("Verdana", Font.BOLD, 12));
		r.setBounds(10, 7, 127, 23);
		panel.add(r);
		r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r.isSelected()) {isbn.setEnabled(true);get_type("Livre");}	
				}
		});
		
		JLabel l2 = new JLabel("Titre");
		l2.setFont(new Font("Verdana", Font.BOLD, 12));
		l2.setBounds(238, 11, 46, 14);
		panel.add(l2);
		
		title = new JTextField();
		title.addMouseListener(new MouseAdapter() { 
		    public void mouseExited(MouseEvent me) { 
		    	if(!title.getText().isBlank()) {
		    	Book b = new Book();
		    	b.setTitle(title.getText());
		        list = db.getBookByTitle(b);
		        getBooks();}
		       } 
		     });
		title.setFont(new Font("Verdana", Font.PLAIN, 12));
		title.setBounds(238, 33, 154, 22);
		panel.add(title);
		title.setColumns(10);
		
		JLabel l3 = new JLabel("Edition");
		l3.setFont(new Font("Verdana", Font.BOLD, 12));
		l3.setBounds(238, 66, 46, 14);
		panel.add(l3);
		
		JLabel l4 = new JLabel("Auteur(s)");
		l4.setFont(new Font("Verdana", Font.BOLD, 12));
		l4.setBounds(444, 66, 72, 14);
		panel.add(l4);
		
		bg = new ButtonGroup();
		r1 = new JRadioButton("Magazine");
		r1.setFont(new Font("Verdana", Font.PLAIN, 11));
		r1.setBounds(10, 87, 84, 23);
		panel.add(r1);
		r1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r1.isSelected()) {get_type(r1.getText());
				 isbn.setEnabled(false);
				 }
				else {isbn.setEnabled(true);}
			}
		});
		
		r2 = new JRadioButton("Dissertation");
		r2.setFont(new Font("Verdana", Font.PLAIN, 11));
		r2.setBounds(116, 88, 99, 23);
		panel.add(r2);
		r2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r2.isSelected()) { get_type(r2.getText());
				 isbn.setEnabled(false);
				 }
				else {isbn.setEnabled(true);}
			}
		});
		bg.add(r);bg.add(r2);bg.add(r1);
JLabel l6 = new JLabel("Section");
l6.setFont(new Font("Verdana", Font.BOLD, 12));
l6.setBounds(444, 11, 72, 14);
panel.add(l6);
sec = new JComboBox<Section>();
sec.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
    	if(sec.getSelectedIndex()>0) {
    	Book b = new Book();
    	b.setSec(s.get(sec.getSelectedIndex()));
        list = db.getBookBySec(b);
        getBooks();b=null;}
       } 
     });
sec.setFont(new Font("Verdana", Font.PLAIN, 11));
AutoCompleteDecorator.decorate(sec);
for(int i=0; i<s.size();i++) {
sec.addItem(s.get(i));}
sec.setBounds(444, 33, 154, 22);
panel.add(sec);
edate = new JSpinner(new SpinnerDateModel(new Date(), null, new Date(), Calendar.YEAR));
edate.setFont(new Font("Verdana", Font.PLAIN, 11));
edate.setBounds(620, 34, 72, 22);
JSpinner.DateEditor year = new JSpinner.DateEditor(edate,"yyyy");
edate.setEditor(year);
panel.add(edate);
JLabel l7 = new JLabel("Date édition");
l7.setFont(new Font("Verdana", Font.BOLD, 12));
l7.setBounds(617, 11, 97, 14);
panel.add(l7);
edit = new JComboBox<Editor>();
edit.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
    	if(edit.getSelectedIndex()>0) {
    	Book b = new Book();
    	b.setEdit(e.get(edit.getSelectedIndex()));
        list = db.getBookByEdit(b);
        getBooks();b=null;}
       } 
     });
edit.setFont(new Font("Verdana", Font.PLAIN, 11));
AutoCompleteDecorator.decorate(edit);
for(int i=0; i<e.size();i++) {
edit.addItem(e.get(i));}
edit.setBounds(238, 88, 154, 22);
panel.add(edit);
cba = new JComboBox<Author>();
cba.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
    	Vector<Author> v = new Vector<Author>();
    	if(cba.getSelectedIndex()>0) {
    	Book b = new Book();v.add(a.get(cba.getSelectedIndex()));
    	b.setAuth(v);
        list = db.getBookByAuth(b);
        getBooks();v=null;b=null;}
       } 
     });
cba.setFont(new Font("Verdana", Font.PLAIN, 11));
AutoCompleteDecorator.decorate(cba);
for(int i=0; i<a.size();i++) {
cba.addItem(a.get(i));}
cba.setBounds(442, 88, 156, 22);
panel.add(cba);
JButton main = new JButton("Retour");
main.setBounds(10, 135, 100, 25);
panel.add(main);
main.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		Main m = new Main();
		m.MainMenu.setVisible(true);
	}
});
main.setFont(new Font("Arial Black", Font.PLAIN, 13));
main.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLUE));

JCheckBox av = new JCheckBox("Disponible");
av.addMouseListener(new MouseAdapter() { 
    public void mousePressed(MouseEvent me) {
		get_av(av);
	}
});
av.setSelected(true);
av.setFont(new Font("Verdana", Font.BOLD, 12));
av.setBounds(620, 90, 97, 23);
panel.add(av);
JLabel reset = new JLabel("Réinitialiser");
reset.addMouseListener(new MouseAdapter() { 
    public void mousePressed(MouseEvent me) { 
        list = db.getAllBooks();
        isbn.setText("");title.setText("");
        av.setSelected(true);cba.setSelectedIndex(0);sec.setSelectedIndex(0);edit.setSelectedIndex(0);
        getBooks();
       } 
     });
reset.setForeground(Color.RED);
reset.setHorizontalAlignment(SwingConstants.CENTER);
reset.setFont(new Font("Times New Roman", Font.BOLD, 12));
reset.setBounds(683, 140, 97, 20);
panel.add(reset);

table = new JTable();
table.setModel(new DefaultTableModel(
	new Object[][] {
	},
	new String[] {
		"Isbn", "Type", "Position", "Title", "Auteurs", "Date", "Edition", "Section", "Disponible"
	}
){

    @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
});
table.setBorder(new LineBorder(new Color(128, 128, 128)));
table.setBounds(50, 300, 800, -182);
//frame.getContentPane().add(table);
JScrollPane js = new JScrollPane();
js.setSize(750, 427);
js.setLocation(32, -100);
js.setViewportView(table);
javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
layout.setHorizontalGroup(
	layout.createParallelGroup(Alignment.LEADING)
		.addGroup(layout.createSequentialGroup()
			.addGap(15)
			.addComponent(js, GroupLayout.PREFERRED_SIZE, 810, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(200, Short.MAX_VALUE))
);
layout.setVerticalGroup(
	layout.createParallelGroup(Alignment.LEADING)
		.addGroup(layout.createSequentialGroup()
			.addGap(220)
			.addComponent(js, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(115, Short.MAX_VALUE))
);
frame.getContentPane().setLayout(layout);

//frame.pack();
}
}

