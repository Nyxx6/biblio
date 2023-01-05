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

public class Main {

	public JFrame MainMenu;

	public Main() {
		initialize();
		
	}
	
	private void initialize() {
		MainMenu = new JFrame();
		MainMenu.getContentPane().setBackground(Color.WHITE);
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
		b1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		b1.setBounds(44, 41, 160, 40);
		MainMenu.getContentPane().add(b1);
		
		JButton b2 = new JButton("Ajouter Membre");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				NewMem nm = new NewMem();
				nm.frmAjouterMembre.setVisible(true);
			}
		});
		b2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		b2.setBounds(44, 92, 160, 40);
		MainMenu.getContentPane().add(b2);
		
		JButton b4 = new JButton("Enregistrer Emprunt");
		b4.setFont(new Font("Arial Black", Font.PLAIN, 11));
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				Borrow b = new Borrow();
				b.frame.setVisible(true);
			}
		});
		b4.setBounds(42, 143, 162, 40);
		MainMenu.getContentPane().add(b4);
		
		JButton b7 = new JButton("Recherche Document");
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				ListBook lb = new ListBook();
				lb.frame.setVisible(true);
			}
		});
		b7.setFont(new Font("Arial Black", Font.PLAIN, 11));
		b7.setBounds(301, 41, 160, 40);
		MainMenu.getContentPane().add(b7);
		
		JButton b8 = new JButton("Rechercher Membre");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				ListMem lm = new ListMem();
				lm.frame.setVisible(true);
			}
		});
		b8.setFont(new Font("Arial Black", Font.PLAIN, 11));
		b8.setBounds(301, 92, 160, 40);
		MainMenu.getContentPane().add(b8);
		
		JLabel lblNewLabel = new JLabel("Library management system");
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(160, 0, 210, 30);
		MainMenu.getContentPane().add(lblNewLabel);
		
		JButton b5 = new JButton("Retourner Emprunt");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				Return lm = new Return();
				lm.frame.setVisible(true);
			}
		});
		b5.setFont(new Font("Arial Black", Font.PLAIN, 11));
		b5.setBounds(301, 143, 160, 40);
		MainMenu.getContentPane().add(b5);
		
		JButton lout = new JButton("Log out");
		lout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.dispose();
				Index i = new Index();
				i.formLogIn.setVisible(true);
			}
		});
		lout.setBackground(Color.WHITE);
		lout.setFont(new Font("Verdana", Font.BOLD, 14));
		lout.setBounds(386, 207, 75, 25);
	    lout.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
	    lout.setForeground(Color.RED);
		
		MainMenu.getContentPane().add(lout);

		
		MainMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Pack/logo.png")));
		MainMenu.setTitle("Main Menu");
		MainMenu.setResizable(false);
		MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenu.setBounds(100, 100, 546, 297);
		MainMenu.setVisible(true);
	}
}
