package Pack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class NewBook {

	public JFrame frmAjouterOuvrage;
	private JTextField isbn,title,pos;
	private JTextArea auth;
	private JComboBox<Section> sec;
	private JComboBox<Author> cba;
	private JComboBox<Editor> edit;
	private JSpinner edate;
	private ButtonGroup bg;
	private JRadioButton r,r1,r2;
	private Db db = Db.getInstance();
	ArrayList<Section> s = db.getSection();
	ArrayList<Author> a = db.getAuth();
	ArrayList<Editor> e = db.getEdit();
	private Vector<Author> list_auth = new Vector<Author>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBook window = new NewBook();
					window.frmAjouterOuvrage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewBook() {
		initialize();
	}
	public String getradio() {
        if (r1.isSelected()) { isbn.setText("0"); return r1.getText();  }
        else if(r2.isSelected()) { isbn.setText("0"); return r2.getText();  }       
	return "Livre";}
    
	private void newbook() {
		if((isbn.isEnabled() && isbn.getText().isBlank()) || title.getText().isBlank() || pos.getText().isBlank()) {
			JOptionPane.showMessageDialog(frmAjouterOuvrage, "Veuillez remplir tous les champs!",
		               "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else {
		Book b = new Book(getradio(), Integer.parseInt(isbn.getText()), pos.getText(), title.getText(), list_auth, s.get(sec.getSelectedIndex()),
					e.get(edit.getSelectedIndex()), new SimpleDateFormat("yyyy-MM-dd").format(edate.getModel().getValue()));
			b = db.addBook(b);
			if(b.getId() > 0)
			JOptionPane.showMessageDialog(frmAjouterOuvrage, "Oeuvre ajouté avec succés!",
	               "Ok", JOptionPane.DEFAULT_OPTION);
			else 
				JOptionPane.showMessageDialog(frmAjouterOuvrage, "Oeuvre existe déjà!",
			               "Erreur", JOptionPane.ERROR_MESSAGE);System.out.println(b.getId());	}	
		//System.out.println(getradio());
	}
	/* Save authors from cba to txt area */
	private void cbauth() {
		
		if(!list_auth.contains(a.get(cba.getSelectedIndex()))) {
		list_auth.addElement(a.get(cba.getSelectedIndex()));
		auth.insert(cba.getSelectedItem().toString()+"\n", auth.getCaretPosition());
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjouterOuvrage = new JFrame();
		frmAjouterOuvrage.setIconImage(Toolkit.getDefaultToolkit().getImage(NewBook.class.getResource("/Pack/logo.png")));
		frmAjouterOuvrage.setTitle("Ajouter ouvrage");
		frmAjouterOuvrage.setBounds(100, 100, 861, 351);
		frmAjouterOuvrage.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAjouterOuvrage.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.ORANGE, Color.DARK_GRAY));
		panel.setBounds(15, 42, 689, 259);
		frmAjouterOuvrage.getContentPane().add(panel);
		panel.setLayout(null);
		
		isbn = new JTextField();
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
				if(r.isSelected()) {isbn.setEnabled(true);}
			}
		});
		
		JLabel l2 = new JLabel("Titre");
		l2.setFont(new Font("Verdana", Font.BOLD, 12));
		l2.setBounds(10, 66, 46, 14);
		panel.add(l2);
		
		title = new JTextField();
		title.setFont(new Font("Verdana", Font.PLAIN, 12));
		title.setBounds(10, 88, 154, 22);
		panel.add(title);
		title.setColumns(10);
		
		JLabel l3 = new JLabel("Edition");
		l3.setFont(new Font("Verdana", Font.BOLD, 12));
		l3.setBounds(10, 121, 46, 14);
		panel.add(l3);
		
		JLabel l4 = new JLabel("Auteur(s)");
		l4.setFont(new Font("Verdana", Font.BOLD, 12));
		l4.setBounds(10, 176, 72, 14);
		panel.add(l4);
		
		bg = new ButtonGroup();
		r1 = new JRadioButton("Magazine");
		r1.setFont(new Font("Verdana", Font.PLAIN, 11));
		r1.setBounds(156, 34, 84, 23);
		panel.add(r1);
		r1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r1.isSelected()) {
				 isbn.setEnabled(false);
				 }
				else isbn.setEnabled(true);
			}
		});
		r2 = new JRadioButton("Dissertation");
		r2.setFont(new Font("Verdana", Font.PLAIN, 11));
		r2.setBounds(242, 34, 99, 23);
		panel.add(r2);
		r2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r2.isSelected()) {
				 isbn.setEnabled(false);
				 }
				else isbn.setEnabled(true);
			}
		});
		bg.add(r);bg.add(r2);bg.add(r1);
JLabel l5 = new JLabel("Position");
l5.setFont(new Font("Verdana", Font.BOLD, 12));
l5.setBounds(385, 10, 72, 14);
panel.add(l5);
pos = new JTextField();
pos.setFont(new Font("Verdana", Font.PLAIN, 12));
pos.setColumns(10);
pos.setBounds(385, 33, 154, 22);
panel.add(pos);
JLabel l6 = new JLabel("Section");
l6.setFont(new Font("Verdana", Font.BOLD, 12));
l6.setBounds(385, 67, 72, 14);
panel.add(l6);
sec = new JComboBox<Section>();
sec.setFont(new Font("Verdana", Font.PLAIN, 11));
AutoCompleteDecorator.decorate(sec);
for(int i=0; i<s.size();i++) {
sec.addItem(s.get(i));}
sec.setBounds(385, 89, 154, 22);
panel.add(sec);
JCheckBox av = new JCheckBox("Disponible");
av.setSelected(true);
av.setFont(new Font("Verdana", Font.BOLD, 12));
av.setBounds(385, 198, 97, 23);
panel.add(av);
edate = new JSpinner(new SpinnerDateModel(new Date(), null, new Date(), Calendar.YEAR));
edate.setFont(new Font("Verdana", Font.PLAIN, 11));
edate.setBounds(385, 145, 97, 20);
JSpinner.DateEditor year = new JSpinner.DateEditor(edate,"yyyy");
edate.setEditor(year);
panel.add(edate);
JLabel l7 = new JLabel("Date édition");
l7.setFont(new Font("Verdana", Font.BOLD, 12));
l7.setBounds(385, 122, 97, 14);
panel.add(l7);

JButton sub = new JButton("Valider");
sub.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		newbook();
	}
});
sub.setFont(new Font("Arial Black", Font.PLAIN, 13));
sub.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.GREEN));
sub.setBounds(568, 225, 100, 25);
panel.add(sub);
JScrollPane sp = new JScrollPane();
sp.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
sp.setBounds(182, 198, 197, 52);
panel.add(sp);
auth = new JTextArea();
auth.setForeground(new Color(0, 0, 0));
auth.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.YELLOW));
auth.setBackground(SystemColor.text);
sp.setViewportView(auth);
auth.setEditable(false);
auth.setFont(new Font("Arial", Font.PLAIN, 12));
edit = new JComboBox<Editor>();
edit.setFont(new Font("Verdana", Font.PLAIN, 11));
AutoCompleteDecorator.decorate(edit);
for(int i=0; i<e.size();i++) {
edit.addItem(e.get(i));}
edit.setBounds(10, 144, 156, 22);
panel.add(edit);
cba = new JComboBox<Author>();
cba.setFont(new Font("Verdana", Font.PLAIN, 11));
AutoCompleteDecorator.decorate(cba);
for(int i=0; i<a.size();i++) {
cba.addItem(a.get(i));}
cba.setBounds(10, 201, 156, 22);
cba.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {cbauth();}});
panel.add(cba);

JLabel reset = new JLabel("Supprimer");
reset.addMouseListener(new MouseAdapter() { 
    public void mousePressed(MouseEvent me) { 
       auth.setText("");list_auth.clear();
      } 
    }); 
reset.setBackground(Color.PINK);
reset.setHorizontalAlignment(SwingConstants.CENTER);
reset.setFont(new Font("Verdana", Font.ITALIC, 11));
reset.setBounds(242, 177, 72, 14);
panel.add(reset);

JLabel l = new JLabel("Ajouter un ouvrage");
l.setFont(new Font("Arial Black", Font.PLAIN, 14));
l.setBounds(301, 11, 151, 20);
frmAjouterOuvrage.getContentPane().add(l);
JButton add_edit = new JButton("\r\nEdition");
add_edit.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
		Editor edt = new Editor();
		JTextField name = new JTextField();
	     JOptionPane.showOptionDialog(frmAjouterOuvrage, new Object[] {"Entrer le nom d'édition:", name},
	    		  "Ajouter Edition", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
	     if(!(name.getText().isBlank())) {
	     edt.setName(name.getText());	db.addEdit(edt);   edit.addItem(edt); e.add(edt);}
	}
});
add_edit.setBounds(725, 68, 100, 25);
frmAjouterOuvrage.getContentPane().add(add_edit);
add_edit.setFont(new Font("Arial Black", Font.PLAIN, 13));
add_edit.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
JButton add_auth = new JButton("Autheur");
add_auth.setBounds(725, 127, 100, 25);
frmAjouterOuvrage.getContentPane().add(add_auth);
add_auth.setFont(new Font("Arial Black", Font.PLAIN, 13));
add_auth.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
add_auth.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
		Author edt = new Author();
		JTextField n1 = new JTextField();
		JTextField n2 = new JTextField();
	     JOptionPane.showOptionDialog(frmAjouterOuvrage, new Object[] {"Entrer le nom de l'auteur:", n2, "Entrer le prénom de l'auteur:", n1},
	    		  "Ajouter Auteur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
	     if(!(n1.getText().isBlank() && n2.getText().isBlank())) {
	     edt.setName1(n1.getText());	edt.setName2(n2.getText());    db.addAuth(edt);    cba.addItem(edt); a.add(edt);}
	}
});
JButton add_sec = new JButton("Section");
add_sec.setBounds(725, 190, 100, 25);
frmAjouterOuvrage.getContentPane().add(add_sec);
add_sec.setFont(new Font("Arial Black", Font.PLAIN, 13));
add_sec.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
add_sec.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ev) {
		Section edt = new Section();
		JTextField t = new JTextField();
	     JOptionPane.showOptionDialog(frmAjouterOuvrage, new Object[] {"Entrer le nom de la section:", t},
	    		  "Ajouter Section", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
	     if(!(t.getText().isBlank())) {
	     edt.setLabel(t.getText());	db.addSection(edt);	sec.addItem(edt); s.add(edt);}
	}
});
JButton main = new JButton("Retour");
main.setBounds(725, 264, 100, 25);
frmAjouterOuvrage.getContentPane().add(main);
main.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		frmAjouterOuvrage.dispose();
		Main m = new Main();
		m.MainMenu.setVisible(true);
	}
});
main.setFont(new Font("Arial Black", Font.PLAIN, 13));
main.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLUE));
	}
}
