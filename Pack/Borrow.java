package Pack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SpinnerModel;

public class Borrow {

	 JFrame frame;
	 private JTextField id_mem;
	 private JTextField isbn;
	 private JTextField id;
	 private JSpinner dt;
	 private ButtonGroup bg;
	 private JSpinner dt_ret;
	 private Db db = Db.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrow window = new Borrow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Book getidbook(Book b) {
		if(isbn.isEnabled()) {b.setIsbn(Integer.parseInt(isbn.getText()));
			b = db.getBookId(b);
		}
		else b.setId(Integer.parseInt(id.getText()));
		return b;
	}
	private void newborrow() {
		if((isbn.isEnabled() && isbn.getText().isBlank()) || (id.isEnabled() && id.getText().isBlank()) || id_mem.getText().isBlank()) {
			JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs!",
		               "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else { Book book = new Book(); book = getidbook(book);
		if(book.getId() > 0) {
			Borrowed b = new Borrowed(new Member(Integer.parseInt(id_mem.getText())), book, new SimpleDateFormat("yyyy-MM-dd").format(dt.getModel().getValue()),new SimpleDateFormat("yyyy-MM-dd").format(dt_ret.getModel().getValue()));
			String res = db.addBorrow(b);
			if( res == "ok")			
			JOptionPane.showMessageDialog(frame, "Enregistrement ajouté avec succés!",
	               "Ok", JOptionPane.DEFAULT_OPTION);
			else {
				JOptionPane.showMessageDialog(frame, res,
			               "Erreur", JOptionPane.WARNING_MESSAGE);	}			
		}
			else {
				JOptionPane.showMessageDialog(frame, "Erreur durant l'enregitrement.",
			               "Erreur", JOptionPane.ERROR_MESSAGE);	}	
	}
		}
	/**
	 * Create the application.
	 */

	
	public Borrow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 684, 297);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel l = new JLabel("Enregistrer emprunt");
		l.setFont(new Font("Arial Black", Font.PLAIN, 14));
		l.setBounds(255, 11, 157, 20);
		frame.getContentPane().add(l);
		
		JPanel panel = new JPanel();
		//panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.DARK_GRAY));
		panel.setBounds(86, 55, 399, 168);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 2, 10, 10));
		
		JLabel l1 = new JLabel("Identifiant du membre");
		l1.setFont(new Font("Verdana", Font.BOLD, 12));
		l1.setBounds(10, 11, 46, 14);
		panel.add(l1);
		
		id_mem = new JTextField();
		id_mem.setFont(new Font("Verdana", Font.PLAIN, 12));
		id_mem.setBounds(10, 33, 179, 22);
		panel.add(id_mem);
		id_mem.setColumns(10);
		
		bg = new ButtonGroup();
		JRadioButton r = new JRadioButton("Isbn du Livre");
		r.setFont(new Font("Verdana", Font.BOLD, 12));
		r.setSelected(true);
		panel.add(r);bg.add(r);
		r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r.isSelected()) {id.setText("");isbn.setEnabled(true);id.setEnabled(false);}
			}
		});
		
		isbn = new JTextField();
		isbn.setFont(new Font("Verdana", Font.PLAIN, 12));
		isbn.setColumns(10);
		panel.add(isbn);
		
		JRadioButton r1 = new JRadioButton("Identifiant du document");
		r1.setFont(new Font("Verdana", Font.BOLD, 12));
		panel.add(r1);bg.add(r1);
		r1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r1.isSelected()) {isbn.setText("");
				 isbn.setEnabled(false);id.setEnabled(true);
				 }
				
			}
		});
		
		id = new JTextField();
		id.setFont(new Font("Verdana", Font.PLAIN, 12));
		id.setColumns(10);
		id.setEnabled(false);
		panel.add(id);
		
		JLabel l2 = new JLabel("Enregitrer le :");
		panel.add(l2);
		l2.setFont(new Font("Verdana", Font.BOLD, 12));
		
		dt = new JSpinner(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_WEEK_IN_MONTH));
		dt.setFont(new Font("Verdana", Font.PLAIN, 12));
		JSpinner.DateEditor year = new JSpinner.DateEditor(dt,"YYYY-MM-DD");
		dt.setEditor(year);
		panel.add(dt);
		
		JLabel l3 = new JLabel("Retourner le :");
		l3.setFont(new Font("Verdana", Font.BOLD, 12));
		panel.add(l3);
		Date date = new Date();
		dt_ret = new JSpinner(new SpinnerDateModel(new Date(date.getTime() + (1000 * 60 * 60 * 24)*15), null, null, Calendar.DAY_OF_WEEK_IN_MONTH));
		dt_ret.setFont(new Font("Verdana", Font.PLAIN, 12));
		JSpinner.DateEditor ye = new JSpinner.DateEditor(dt_ret,"YYYY-MM-DD");
		dt_ret.setEditor(ye);
		panel.add(dt_ret);
		
		JButton sub = new JButton("Valider");
		sub.setBounds(534, 187, 100, 23);
		frame.getContentPane().add(sub);
		sub.setFont(new Font("Arial Black", Font.PLAIN, 13));
		sub.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.GREEN));
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newborrow();
			}
		});
		
		JButton main = new JButton("Retour");
		frame.getContentPane().add(main);
		main.setBounds(534, 120, 100, 23);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Main m = new Main();
				m.MainMenu.setVisible(true);
			}
		});
		main.setFont(new Font("Arial Black", Font.PLAIN, 13));
		main.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLUE));
		
		JButton view = new JButton("Visualiser");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ListBorrow m = new ListBorrow();
				m.frame.setVisible(true);
			}
		});
		view.setFont(new Font("Arial Black", Font.PLAIN, 13));
		view.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
		view.setBounds(534, 63, 100, 23);
		frame.getContentPane().add(view);
	}
}
