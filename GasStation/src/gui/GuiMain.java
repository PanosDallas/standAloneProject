package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.SqlCustomer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiMain extends JFrame {

	private JButton btnExit = new JButton("Exit");
    private JPanel contentPane = new JPanel();

	/**
	 * Create the frame.
	 */
	public GuiMain() {
		SqlCustomer.connect();
				
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\LatestJava\\GasStation\\GasStation\\src\\pictures\\station.png"));
		lblNewLabel.setBounds(10, 11, 549, 595);
		contentPane.add(lblNewLabel);
		
		JButton btnCustomer = new JButton("Customers");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GuiCustomer();
			}
		});
		btnCustomer.setBounds(644, 62, 89, 23);
		contentPane.add(btnCustomer);
		
		JButton btnEmployees = new JButton("Employees");
		btnEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GuiEmployee();
			}
		});
		btnEmployees.setBounds(644, 106, 89, 23);
		contentPane.add(btnEmployees);
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitFormGasStation();
			}
		});
		btnExit.setBounds(644, 278, 89, 23);
		contentPane.add(btnExit);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 869, 659);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnItems = new JButton("Items");
		btnItems.addActionListener(new ActionListener() { ///////////////////// ITEMS
			public void actionPerformed(ActionEvent e) {
				new GuiItem();
			}
		});
		btnItems.setBounds(644, 149, 89, 23);
		contentPane.add(btnItems);
		
		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GuiTransaction();
			}
		});
		btnInvoice.setBounds(644, 191, 89, 23);
		contentPane.add(btnInvoice);
		this.setVisible(true);
		this.setEnabled(true);
		
		
	}



	protected void exitFormGasStation() {
		int answer = JOptionPane.showConfirmDialog(btnExit, "Exit from Program?","Exit" ,JOptionPane.YES_NO_OPTION);
		if(answer==JOptionPane.YES_OPTION) {
			SqlCustomer.disconnect();
			this.dispose();
		}
		
	}
}
