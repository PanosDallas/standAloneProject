package gui;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.DateLabelFormatter;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import sql.SqlCustomer;
import sql.SqlEmployee;

public class GuiTransaction extends JFrame {

	private JPanel contentPane = new JPanel();
	private JTextField txtCustomerId = new JTextField();;
	private JLabel lblCustomerId;
	private int row = -1;
	private String[] customerColumns = new String[] { "ID", "Name", "Surname", "Credit Card" };
	private DefaultTableModel customerModel = new DefaultTableModel(customerColumns, 0);
	private JTable customerTable = new JTable(customerModel);
	private JScrollPane customerScrollPane = new JScrollPane();
	private String[] employeeColumns = new String[] { "ID", "Name", "Surname", "Hire", "End", "Salary" };
	private DefaultTableModel employeeModel = new DefaultTableModel(employeeColumns, 0);
	private JTable employeeTable = new JTable(employeeModel);
	JScrollPane employeeScrollPane = new JScrollPane();
	private final JLabel lblEmployeeId = new JLabel("Employee id");
	private final JTextField txtEmployeeId = new JTextField();
	private final JLabel lblDate = new JLabel("Date");
	//private final JTextField txtDate = new JTextField();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnCash = new JRadioButton("Cash");
	private JRadioButton rdbtncard = new JRadioButton("Credit Card");
	/// Date Picker
	private UtilDateModel dateModel = new UtilDateModel();
	private JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
	private JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
	
	
	/**
	 * Create the frame.
	 */
	public GuiTransaction() {
//		txtDate.setBounds(147, 139, 96, 20);
//		txtDate.setColumns(10);
//		txtDate.setText(String.valueOf(LocalDateTime.now()));
		
		txtEmployeeId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectEmployeeToInvoice();
			}
		});
		txtEmployeeId.setBounds(147, 108, 96, 20);
		txtEmployeeId.setColumns(10);
		setTitle("Invoice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 998, 660);
		this.setVisible(true);
		this.setEnabled(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Invoice No: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 976, 29);
		contentPane.add(lblNewLabel);
		
		lblCustomerId = new JLabel("Customer id");
		lblCustomerId.setBounds(48, 76, 101, 21);
		contentPane.add(lblCustomerId);
		txtCustomerId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				selectCustomerToInvoice();
			}
		});
		
		
		txtCustomerId.setBounds(147, 76, 96, 20);
		contentPane.add(txtCustomerId);
		txtCustomerId.setColumns(10);
		lblEmployeeId.setBounds(48, 108, 101, 21);
		
		contentPane.add(lblEmployeeId);
		
		contentPane.add(txtEmployeeId);
		lblDate.setBounds(48, 139, 101, 21);
		
		contentPane.add(lblDate);
		
		//contentPane.add(txtDate);
		datePicker.setBounds(147, 139, 184, 31);
		contentPane.add(datePicker);
		
		buttonGroup.add(rdbtnCash);
		rdbtnCash.setBounds(147, 177, 72, 21);
		contentPane.add(rdbtnCash);
		buttonGroup.add(rdbtncard);
		rdbtncard.setBounds(147, 202, 109, 23);
		contentPane.add(rdbtncard);
		
	}


	protected void selectEmployeeToInvoice() {
		employeeScrollPane.setViewportView(employeeTable);
		employeeScrollPane.setBounds(300, 135, 456, 362);
		contentPane.add(employeeScrollPane);
		SqlEmployee.fillEmployeeTable(employeeModel);
		repaint();
		employeeTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				row = employeeTable.getSelectedRow();
				// vale to id tou customer mesa sto txtCustomerId kai kanto editable.
				txtEmployeeId.setText(String.valueOf( employeeModel.getValueAt(row,0)));
				employeeScrollPane.setVisible(false);
			}
		});
		
				
	}


	protected void selectCustomerToInvoice() {
		
		customerScrollPane.setViewportView(customerTable);
		customerScrollPane.setBounds(300, 135, 456, 362);
		contentPane.add(customerScrollPane);
		SqlCustomer.fillCustomerTable(customerModel);
		repaint();
		customerTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				row = customerTable.getSelectedRow();
				// vale to id tou customer mesa sto txtCustomerId kai kanto editable.
				txtCustomerId.setText(String.valueOf( customerModel.getValueAt(row,0)));
				customerScrollPane.setVisible(false);
			}
		});
		
	}
}
