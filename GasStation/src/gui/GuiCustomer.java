 package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import sql.SqlCustomer;
import utils.MyException;

public class GuiCustomer extends JFrame {
	private int row = -1;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSurName;
	private JTextField txtCreditCard;
	private JButton btnAdd = new JButton("ADD");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnEdit = new JButton("Edit");
	// Gemizo to table me ta fields
	private String[] columns = new String[] { "ID", "Name", "Surname", "Credit Card" };
	private DefaultTableModel model = new DefaultTableModel(columns, 0);
	private JTable table = new JTable(model);
	// 4. sinithos to kano mesa ston cosntructor i ftiaxno mia methodo na paro ola
	// ta stoixeia apo ton pinaka tis Database
	// kai na ta valo sto model
	private KeyEvent evt;

	/**
	 * Create the frame.
	 */
	public GuiCustomer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 713, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("Id ");
		lblId.setBounds(20, 35, 46, 14);
		contentPane.add(lblId);
//		SqlCustomer.fillCustomerTable(model);
//		table.setAutoCreateRowSorter(true);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(107, 32, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(20, 66, 46, 14);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(107, 63, 86, 20);
		contentPane.add(txtName);

		JLabel lblSurname = new JLabel("SurName");
		lblSurname.setBounds(20, 94, 86, 14);
		contentPane.add(lblSurname);

		txtSurName = new JTextField();
		txtSurName.setColumns(10);
		txtSurName.setBounds(107, 91, 86, 20);
		contentPane.add(txtSurName);

		JLabel lblCreditCard = new JLabel("Credit Card");
		lblCreditCard.setBounds(20, 122, 86, 14);
		contentPane.add(lblCreditCard);

		txtCreditCard = new JTextField();
		txtCreditCard.setColumns(10);
		txtCreditCard.setBounds(107, 119, 86, 20);
		contentPane.add(txtCreditCard);

		/////////////// Add button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer();

			}
		});
		btnAdd.setBounds(585, 537, 89, 23);
		contentPane.add(btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 35, 456, 362);
		contentPane.add(scrollPane);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				row = table.getSelectedRow();
				fillForm();
			}
		});

		scrollPane.setViewportView(table);
		btnDelete.addActionListener(new ActionListener() { //////////////////////// DELETE BUTTON 
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
			}
		});

		/////////// Delete button
		btnDelete.setBounds(486, 537, 89, 23);
		contentPane.add(btnDelete);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { ////////////////////////////// EDIT BUTTON
				editCustomer();
				
			}
		});

		/////////// Edit button
		btnEdit.setBounds(387, 537, 89, 23);
		contentPane.add(btnEdit);
		SqlCustomer.fillCustomerTable(model);
		this.setVisible(true);
		this.setEnabled(true);
	}

	protected void editCustomer() {
		// TODO Auto-generated method stub
		if (row==-1) {
			JOptionPane.showMessageDialog(btnEdit, "please select a Customer first!!!","Error Message",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int ans = JOptionPane.showConfirmDialog(btnEdit, "Edit selected customer. Ar You sure","Attention",JOptionPane.YES_NO_OPTION);
		if(ans == JOptionPane.NO_OPTION) return;
		
		if(!MyException.isCardValidNumber((String)model.getValueAt(row,3))) {
			JOptionPane.showMessageDialog(null,"Wrong card number","ErrorMessage",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SqlCustomer.editCustomer(Integer.parseInt(txtId.getText()),txtName.getText(),txtSurName.getText(),
				txtCreditCard.getText());
								
				
		JOptionPane.showMessageDialog(btnEdit, "the Customer Updated","Information Message",JOptionPane.INFORMATION_MESSAGE);
		SqlCustomer.fillCustomerTable(model);
		repaint();
	}

	protected void deleteCustomer() {
		if (row==-1) {
			JOptionPane.showMessageDialog(btnDelete, "please select a Customer first!!!","Error Message",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int ans = JOptionPane.showConfirmDialog(btnDelete, "Delete selected customer","Deletion Are You Sure",JOptionPane.YES_NO_OPTION);
		if(ans == JOptionPane.NO_OPTION) return;
		int id =(int)model.getValueAt(row,0);
		SqlCustomer.deleteCustomer(id);
		model.removeRow(row);
		JOptionPane.showMessageDialog(btnDelete, "the Customer Deleted","Information Message",JOptionPane.INFORMATION_MESSAGE);
		repaint();
		
	}

	protected void fillForm() {
		// ID","Name","Surname","Credit Card"};
//				System.out.println("Epelexes tin grammi row");
		Customer c = null;
		try {
			c = SqlCustomer.findCustomer(table.getValueAt(row, 0).toString());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c != null) {
			this.txtId.setText(String.valueOf(c.getIdc()));
			this.txtName.setText(c.getName());
			this.txtSurName.setText(c.getSurname());
			this.txtCreditCard.setText(c.getCardNumber());
		}
	}

	protected void addCustomer() {
		
		
		Customer c;
		try {
			c = new Customer(txtName.getText(), txtSurName.getText(),this.txtCreditCard.getText());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			return;
		}
//		SqlCustomer.addCustomer(c,model);
		this.repaint();
		SqlCustomer.addCustomer(c);
		JOptionPane.showMessageDialog(btnAdd, "Customer Added succesfully!!!");
		SqlCustomer.fillCustomerTable(model);
	}
}
