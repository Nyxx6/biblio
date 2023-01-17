package Pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class NewMem {

	JFrame frmAjouterMembre;
	private JTextField n1,n2;
	private JSpinner dt;
	private JComboBox<Type> cat;
	private Db db = Db.getInstance();
	private ArrayList<Type> s = db.getTypeMem();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewMem window = new NewMem();
					window.frmAjouterMembre.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void newmem() {
		if(n1.getText().isBlank() || n2.getText().isBlank()) {
			JOptionPane.showMessageDialog(frmAjouterMembre, "Veuillez remplir tous les champs!",
		               "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else {
			Member m = new Member(n1.getText(), n2.getText(), new SimpleDateFormat("yyyy-MM-dd").format(dt.getModel().getValue()), s.get(cat.getSelectedIndex()));
			m = db.addMem(m);
			if(m.getId() > 0)
			JOptionPane.showMessageDialog(frmAjouterMembre, "Membre"+m.getName1()+"ajouté avec succés!",
	               "Ok", JOptionPane.DEFAULT_OPTION);
			else JOptionPane.showMessageDialog(frmAjouterMembre, "Membre "+m.getName1()+" existe déjà!",
		               "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Create the application.
	 */
	public NewMem() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjouterMembre = new JFrame();
		frmAjouterMembre.setIconImage(Toolkit.getDefaultToolkit().getImage(NewMem.class.getResource("/Pack/logo.png")));
		frmAjouterMembre.setTitle("Ajouter membre");
		frmAjouterMembre.setBounds(100, 100, 686, 330);
		frmAjouterMembre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAjouterMembre.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.ORANGE, Color.DARK_GRAY));
		panel.setBounds(15, 42, 511, 219);
		frmAjouterMembre.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("Nom");
		l1.setFont(new Font("Verdana", Font.BOLD, 12));
		l1.setBounds(10, 11, 46, 14);
		panel.add(l1);
		
		n1 = new JTextField();
		n1.setFont(new Font("Verdana", Font.PLAIN, 12));
		n1.setBounds(10, 33, 179, 22);
		panel.add(n1);
		n1.setColumns(10);
		
		JLabel l2 = new JLabel("Prénom");
		l2.setFont(new Font("Verdana", Font.BOLD, 12));
		l2.setBounds(10, 66, 71, 14);
		panel.add(l2);
		
		JLabel l3 = new JLabel("Date de naissance");
		l3.setFont(new Font("Verdana", Font.BOLD, 12));
		l3.setBounds(10, 121, 140, 16);
		panel.add(l3);
		
		JLabel l4 = new JLabel("Catègorie");
		l4.setFont(new Font("Verdana", Font.BOLD, 12));
		l4.setBounds(300, 10, 80, 16);
		panel.add(l4);
		
		n2 = new JTextField();
		n2.setFont(new Font("Verdana", Font.PLAIN, 12));
		n2.setColumns(10);
		n2.setBounds(10, 88, 179, 22);
		panel.add(n2);
		
		cat = new JComboBox<Type>();
		AutoCompleteDecorator.decorate(cat);
		for(int i=0; i<s.size();i++) {
		cat.addItem(s.get(i));}
		cat.setFont(new Font("Verdana", Font.PLAIN, 12));
		cat.setBounds(300, 34, 173, 22);
		panel.add(cat);
		
		dt = new JSpinner(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_WEEK_IN_MONTH));
		dt.setFont(new Font("Verdana", Font.PLAIN, 12));
		JSpinner.DateEditor year = new JSpinner.DateEditor(dt,"YYYY-MM-DD");
		dt.setEditor(year);
		dt.setBounds(10, 144, 179, 20);
		panel.add(dt);

		JButton sub = new JButton("Valider");
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newmem();
			}
		});
		sub.setFont(new Font("Arial Black", Font.PLAIN, 13));
		sub.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.GREEN));
		sub.setFont(new Font("Arial Black", Font.PLAIN, 13));
		sub.setBounds(384, 173, 89, 23);
		panel.add(sub);
		
		JLabel l = new JLabel("Ajouter un membre");
		l.setFont(new Font("Arial Black", Font.PLAIN, 14));
		l.setBounds(258, 11, 154, 20);
		frmAjouterMembre.getContentPane().add(l);
		
		JButton main = new JButton("Retour");
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAjouterMembre.dispose();
				Main m = new Main();
				m.MainMenu.setVisible(true);
			}
		});
		main.setFont(new Font("Arial Black", Font.PLAIN, 13));
		main.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLUE));
		main.setBounds(549, 223, 100, 23);
		frmAjouterMembre.getContentPane().add(main);
		
		JButton add_cat = new JButton("Catègorie");
		add_cat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Type c = new Type();
				JTextField t = new JTextField();
				JTextField n = new JTextField();
			    JOptionPane.showOptionDialog(frmAjouterMembre, new Object[] {"Entrer la nouvelle catégorie:", t, "Nombre max d'exemplaires à emprunter:", n},
			    		  "Ajouter Catégorie Membre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			     if(!(t.getText().isBlank() && n.getText().isBlank())) {
			     c.setType(t.getText()); c.setNbmax(Integer.parseInt(n.getText()));	db.addType(c);   cat.addItem(c);}
			}
		});
		add_cat.setFont(new Font("Arial Black", Font.PLAIN, 13));
		add_cat.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
		add_cat.setBounds(549, 68, 100, 23);
		frmAjouterMembre.getContentPane().add(add_cat);
	}
}
