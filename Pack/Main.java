package Pack;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;

public class Main {

	public JFrame MainMenu;

	public Main() {
		initialize();
		
	}
	
	private void initialize() {
		MainMenu = new JFrame();
		MainMenu.getContentPane().setBackground(new Color(189, 183, 107));
		MainMenu.getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
		MainMenu.getContentPane().setLayout(null);
		
		JButton b1 = new JButton("Ajouter Document");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				NewBook nb = new NewBook();
				nb.frmAjouterOuvrage.setVisible(true);
			}
		});
		b1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		b1.setBackground(Color.WHITE);
		b1.setFont(new Font("Tahoma", Font.BOLD, 13));
		b1.setBounds(46, 62, 172, 40);
		MainMenu.getContentPane().add(b1);
		
		JButton b2 = new JButton("Ajouter Membre");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				NewMem nm = new NewMem();
				nm.frmAjouterMembre.setVisible(true);
			}
		});
		b2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		b2.setBackground(Color.WHITE);
		b2.setFont(new Font("Tahoma", Font.BOLD, 13));
		b2.setBounds(440, 62, 172, 40);
		MainMenu.getContentPane().add(b2);
		
		JButton b4 = new JButton("Enregistrer Emprunt");
		b4.setFont(new Font("Tahoma", Font.BOLD, 13));
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				Borrow b = new Borrow();
				b.frame.setVisible(true);
			}
		});
		b4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		b4.setBackground(Color.WHITE);
		b4.setBounds(244, 62, 174, 40);
		MainMenu.getContentPane().add(b4);
		
		JButton b7 = new JButton("Recherche Document");
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				ListBook lb = new ListBook();
				lb.frame.setVisible(true);
			}
		});
		b7.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		b7.setFont(new Font("Tahoma", Font.BOLD, 13));
		b7.setBackground(Color.WHITE);
		b7.setBounds(46, 113, 172, 40);
		MainMenu.getContentPane().add(b7);
		
		JButton b8 = new JButton("Rechercher Membre");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				ListMem lm = new ListMem();
				lm.frame.setVisible(true);
			}
		});
		b8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		b8.setBackground(Color.WHITE);
		b8.setFont(new Font("Tahoma", Font.BOLD, 13));
		b8.setBounds(440, 113, 172, 40);
		MainMenu.getContentPane().add(b8);
		
		JLabel l = new JLabel("Gestion de la bibliothèque");
		l.setHorizontalAlignment(SwingConstants.CENTER);
		l.setFont(new Font("Arial Black", Font.PLAIN, 15));
		l.setBounds(218, 0, 226, 30);
		MainMenu.getContentPane().add(l);
		
		JButton b5 = new JButton("Vérifier Emprunt");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				ListBorrow lm = new ListBorrow();
				lm.frame.setVisible(true);
			}
		});
		b5.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		b5.setBackground(Color.WHITE);
		b5.setFont(new Font("Tahoma", Font.BOLD, 13));
		b5.setBounds(244, 113, 172, 40);
		MainMenu.getContentPane().add(b5);
		
		JButton lout = new JButton("Quitter");
		lout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				Index i = new Index();
				i.formLogIn.setVisible(true);
			}
		});
		lout.setBackground(Color.WHITE);
		lout.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lout.setBounds(576, 4, 76, 25);
	    lout.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	    lout.setForeground(new Color(255, 0, 0));
		
		MainMenu.getContentPane().add(lout);

		
		MainMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Pack/logo.png")));
		MainMenu.setTitle("Main Menu");
		MainMenu.setResizable(false);
		MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenu.setBounds(100, 100, 678, 252);
		MainMenu.setVisible(true);
	}
}
