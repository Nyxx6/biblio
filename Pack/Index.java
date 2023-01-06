package Pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Index {

	public JFrame formLogIn;
	private JTextField uname;
	private JPasswordField pwd;
	private JLabel val;
	private JButton sub,view;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.formLogIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Index() {
		initialize();
	}
	private void initialize() {
		formLogIn = new JFrame();
		formLogIn.getContentPane().setBackground(new Color(143, 188, 143));
		formLogIn.setResizable(false);
		formLogIn.setBackground(new Color(224, 255, 255));
		formLogIn.setTitle("Log In");
		formLogIn.setFont(new Font("Arial", Font.BOLD, 12));
		formLogIn.setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/Pack/logo.png")));
		formLogIn.getContentPane().setFont(new Font("Arial", Font.BOLD, 12));
		formLogIn.setBounds(100, 100, 516, 307);
		formLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(143, 188, 143));
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		
		val = new JLabel("Se connecter");
		val.setBackground(Color.WHITE);
		val.setForeground(Color.BLACK);
		val.setLabelFor(panel);
		val.setHorizontalAlignment(SwingConstants.CENTER);
		val.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		
		view = new JButton("Visionner..");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formLogIn.dispose();
				ListBook v = new ListBook();
				v.frame.setVisible(true);
			}
		});
		view.setBackground(Color.GREEN);
		view.setFont(new Font("Arial", Font.BOLD, 13));
		GroupLayout groupLayout = new GroupLayout(formLogIn.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(380, Short.MAX_VALUE)
					.addComponent(view)
					.addGap(19))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(160)
					.addComponent(val, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(159, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(62)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(val, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(view, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		
		uname = new JTextField();
		uname.setBounds(154, 21, 200, 28);
		uname.setFont(new Font("Arial", Font.PLAIN, 13));
		uname.setColumns(10);
		
		JLabel pfield = new JLabel("Mot de passe");
		pfield.setBounds(35, 81, 108, 22);
		pfield.setFont(new Font("Arial", Font.BOLD, 14));
		
		pwd = new JPasswordField();
		pwd.setBounds(154, 75, 200, 28);
		pwd.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel ufield = new JLabel("Pseudo");
		ufield.setBounds(35, 27, 96, 22);
		ufield.setFont(new Font("Arial", Font.BOLD, 14));
		
		sub = new JButton("Valider");
		sub.setMnemonic(KeyEvent.VK_ENTER);
		sub.setBounds(146, 128, 91, 25);
		sub.setBackground(Color.YELLOW);
		sub.setForeground(Color.BLACK);
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Db db = Db.getInstance(); if(db.validAdmin(uname.getText().trim(), new String(pwd.getPassword()).trim())) {
					formLogIn.dispose();
					Main main = new Main();
					main.MainMenu.setVisible(true);
				}
				else val.setText("Try again!");				
			}
		});
		sub.setFont(new Font("Arial", Font.BOLD, 13));
		panel.setLayout(null);
		panel.add(uname);
		panel.add(pfield);
		panel.add(pwd);
		panel.add(ufield);
		panel.add(sub);
		formLogIn.getContentPane().setLayout(groupLayout);
	}
}
