package Pack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListMem {

	 JFrame frame;
	private JTextField n1,n2;
	private ButtonGroup bg;
	private JComboBox<Type> cat;
	private JRadioButton r1,r;
	private Db db = Db.getInstance();
	private ArrayList<Type> s = db.getTypeMem();
	private ArrayList<Member> list = db.getAllMems();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListMem window = new ListMem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void setradio() {
        if (r.isSelected()) { getMems();  }
        else { getblocked(); }
        }
	private DefaultTableModel add_mem_to_table(DefaultTableModel model,Object rowData[],Member m) {
		rowData[0] = m.getId();
        rowData[1] = m.getName1();
        rowData[2] = m.getName2();
        rowData[3] = m.getBirthd();
        rowData[4] = m.getAtdate();
        rowData[5] = m.getType();
        rowData[6] = m.getNborrowed();
        rowData[7] = m.state();
        rowData[8] = m.getNodelays();
        model.addRow(rowData);return model;
	}
	private void getMems() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	     Object rowData[] = new Object[10];
		 for(int i = 0; i < list.size(); i++)
	        { model=add_mem_to_table(model,rowData,list.get(i));} 
	}
	private void getblocked() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	     Object rowData[] = new Object[10];
	     for(int i = 0; i < list.size(); i++)
	        { if(!list.get(i).isState())
	        	model=add_mem_to_table(model,rowData,list.get(i));} 
	}
	
	private void searchMem() {Member m = new Member();Type t = new Type();
		if(!n1.getText().isBlank() && !n2.getText().isBlank()) {m.setName1(n1.getText());m.setName2(n2.getText());list = db.getMemByN1_N2(m);getMems();}
		else {
		if(!n1.getText().isBlank()) {m.setName1(n1.getText());list = db.getMemByN1(m);getMems();} else
		if(!n2.getText().isBlank()) {m.setName2(n2.getText());list = db.getMemByN2(m);getMems();}
		else
		if(cat.getSelectedIndex()>=0) {m.setType((Type)cat.getSelectedItem());
			t.setType(s.get(cat.getSelectedIndex()).getType());m.setType(t);list = db.getMemByType(m);getMems();
			}
		}
	}
	/**
	 * Create the application.
	 */
	public ListMem() {initialize();getMems();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(NewMem.class.getResource("/Pack/logo.png")));
		frame.setTitle("Ajouter membre");
		frame.setBounds(100, 100, 868, 369);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.ORANGE, Color.DARK_GRAY));
		panel.setBounds(15, 42, 785, 75);
		frame.getContentPane().add(panel);
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
		l2.setBounds(218, 11, 71, 14);
		panel.add(l2);
		
		JLabel l4 = new JLabel("Catègorie");
		l4.setFont(new Font("Verdana", Font.BOLD, 12));
		l4.setBounds(423, 10, 80, 16);
		panel.add(l4);
		
		n2 = new JTextField();
		n2.setFont(new Font("Verdana", Font.PLAIN, 12));
		n2.setColumns(10);
		n2.setBounds(218, 33, 179, 22);
		panel.add(n2);
		
		cat = new JComboBox<Type>();
		AutoCompleteDecorator.decorate(cat);
		for(int i=0; i<s.size();i++) {
		cat.addItem(s.get(i));}
		cat.setFont(new Font("Verdana", Font.PLAIN, 12));
		cat.setBounds(423, 33, 173, 22);
		panel.add(cat);

		JButton sub = new JButton("Chercher");
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.setSelected(false);
				searchMem();
			}
		});
		sub.setFont(new Font("Arial Black", Font.PLAIN, 13));
		sub.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.GREEN));
		sub.setFont(new Font("Arial Black", Font.PLAIN, 13));
		sub.setBounds(663, 46, 100, 23);
		panel.add(sub);
		
		JButton main = new JButton("Retour");
		main.setBounds(662, 6, 100, 23);
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
		
		bg = new ButtonGroup();
		
		JLabel l = new JLabel("Liste des membres");
		l.setFont(new Font("Arial Black", Font.PLAIN, 14));
		l.setBounds(258, 11, 154, 20);
		frame.getContentPane().add(l);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nom", "Prénom", "Date naissance", "Date inscription", "Type", "Emprunt", "Statut", "Avertissement"
			}
		){

		    @Override
		    public boolean isCellEditable(int row, int column) {
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
		
		r1 = new JRadioButton("Liste noire");
		r1.setFont(new Font("Verdana", Font.PLAIN, 12));
		bg.add(r1);
		r1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setradio();
			}
		});
		r = new JRadioButton("Tous");
		r.setSelected(true);
		r.setFont(new Font("Verdana", Font.PLAIN, 12));
		bg.add(r);
		r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list = db.getAllMems();
				setradio();
			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(js, GroupLayout.PREFERRED_SIZE, 810, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(574, Short.MAX_VALUE)
					.addComponent(r1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(r, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(50))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(122, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(r, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(r1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(js, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		frame.getContentPane().setLayout(layout);
	}
	}


