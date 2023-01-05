package Pack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

public class ListBorrow {

	JFrame frame;
	private JTable table;
	private Db db = Db.getInstance();
	private ArrayList<Borrowed> list = db.getAllBorrows();

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
	
	private void getBorrows() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
	        Object rowData[] = new Object[10];
	        for(int i = 0; i < list.size(); i++)
	        {
	            rowData[0] = list.get(i).getM().getName1();
	            rowData[1] = list.get(i).getM().getName2();
	            rowData[2] = list.get(i).getB().getIsbn();
	            rowData[3] = list.get(i).getB().getTitle();
	            rowData[4] = list.get(i).getAtdate();
	            rowData[5] = list.get(i).getDuedate();
	            rowData[6] = list.get(i).getReturned();
	            rowData[7] = list.get(i).getCondition();
	            model.addRow(rowData);
	        }
	}
	
	private void selectRow() {                                     
        int index = table.getSelectedRow();
        frame.dispose();
		Return rb = new Return();
        if(index>=0) {
        TableModel model = table.getModel();        
		rb.id_mem.setText(Integer.toString(list.get(index).getM().getId()));	
		rb.isbn.setText(model.getValueAt(index, 3).toString());
		if(list.get(index).getB().getId() < 1)
		rb.id.setText(""); else rb.id.setText(Integer.toString(list.get(index).getB().getId()));
		}
        rb.frame.setVisible(true);
    
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
		main.setBounds(725, 264, 100, 25);
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
		js.setLocation(32, -100);
		js.setViewportView(table);
		
		JButton add = new JButton("Ajouter");
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
		ret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectRow();
			}
		});
		ret.setFont(new Font("Arial Black", Font.PLAIN, 13));
		ret.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
		
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(20)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(add, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(ret, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(js, GroupLayout.PREFERRED_SIZE, 804, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(46)
					.addComponent(js, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(add, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(ret, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(layout);
	}

}
