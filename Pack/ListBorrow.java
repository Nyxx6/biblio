package Pack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;

public class ListBorrow {

	JFrame frame;
	private JTable table;
	private Db db = Db.getInstance();
	private ArrayList<Borrowed> list = db.getAllBorrows();
	private ButtonGroup bg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListBorrow window = new ListBorrow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private DefaultTableModel add_row_to_table(DefaultTableModel model,Object rowData[],Borrowed b) {
		rowData[0] = b.getM().getName1();
        rowData[1] = b.getM().getName2();
        rowData[2] = b.getB().getIsbn();
        rowData[3] = b.getB().getTitle();
        rowData[4] = b.getAtdate();
        rowData[5] = b.getDuedate();
        rowData[6] = b.getReturned();
        rowData[7] = b.getCondition();
        model.addRow(rowData);return model;
	}
	private void getBorrows() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	     Object rowData[] = new Object[10];
		 for(int i = 0; i < list.size(); i++)
	        { model=add_row_to_table(model,rowData,list.get(i));} 
	}
	
	private void getReturned() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	     Object rowData[] = new Object[10];
		 for(int i = 0; i < list.size(); i++)
	        { if(list.get(i).getCondition() != null)
			 model=add_row_to_table(model,rowData,list.get(i));} 
	}
	
	private void getNotReturned() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	     Object rowData[] = new Object[10];
		 for(int i = 0; i < list.size(); i++)
	        { if(list.get(i).getCondition() == null)
			 model=add_row_to_table(model,rowData,list.get(i));} 
	}
	
	private void selectRow() {                                     
        int index = table.getSelectedRow();
        if(list.get(index).getCondition() == null) {
        frame.dispose();
		Return rb = new Return();
        if(index>=0) {
        TableModel model = table.getModel();        
		rb.id_mem.setText(Integer.toString(list.get(index).getM().getId()));	
		rb.isbn.setText(model.getValueAt(index, 3).toString());
		if(list.get(index).getB().getId() < 1)
		rb.id.setText(""); else rb.id.setText(Integer.toString(list.get(index).getB().getId()));
		}
        rb.frame.setVisible(true);}
    
	}             

	/**
	 * Create the application.
	 */
	public ListBorrow() {
		initialize(); getBorrows();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton main = new JButton("Retour");
		main.setBounds(20, 266, 100, 25);
		frame.getContentPane().add(main);
		main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Main m = new Main();
				m.MainMenu.setVisible(true);
			}
		});
		main.setFont(new Font("Arial Black", Font.PLAIN, 13));
		main.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLUE));
		
		JLabel l = new JLabel("Liste des emprunts");
		l.setFont(new Font("Arial Black", Font.PLAIN, 14));
		l.setBounds(335, 11, 165, 20);
		frame.getContentPane().add(l);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nom", "Prénom", "ISBN", "Titre du livre", "Date d'emprunt", "Date retour emprunt", "Retourné le:", "Condition de retour"
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
		js.setLocation(20, 40);
		js.setViewportView(table);
		
		JButton add = new JButton("Ajouter");
		add.setLocation(780, 214);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Borrow lb = new Borrow();
				lb.frame.setVisible(true);
			}
		});
		add.setFont(new Font("Arial Black", Font.PLAIN, 13));
		add.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
		
		JButton ret = new JButton("Retourner");
		ret.setLocation(167, 264);
		ret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectRow();
			}
		});
		ret.setFont(new Font("Arial Black", Font.PLAIN, 13));
		ret.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
		bg = new ButtonGroup();
		JRadioButton all = new JRadioButton("Tous");
		all.setSelected(true);
		all.setFont(new Font("Times New Roman", Font.PLAIN, 12));bg.add(all);
		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBorrows();
			}
		});
		
		JRadioButton ye_ret = new JRadioButton("Empruntés seulement");
		ye_ret.setFont(new Font("Times New Roman", Font.PLAIN, 12));bg.add(ye_ret);
		ye_ret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getReturned();
			}
		});
		
		JRadioButton no_ret = new JRadioButton("Non rendus seulement");
		no_ret.setFont(new Font("Times New Roman", Font.PLAIN, 12));bg.add(no_ret);
		no_ret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNotReturned();
			}
		});
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(20)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(all)
							.addGap(18)
							.addComponent(no_ret)
							.addGap(18)
							.addComponent(ye_ret)
							.addGap(10))
						.addComponent(js, GroupLayout.PREFERRED_SIZE, 804, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(ret, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(add, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ye_ret)
						.addComponent(no_ret)
						.addComponent(all))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(js, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(add, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(ret, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(layout);
	}
}
