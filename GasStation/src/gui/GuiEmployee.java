package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Main;
import sql.SqlCustomer;
import sql.SqlEmployee;
import utils.MyException;

public class GuiEmployee extends JFrame {
	private int row = -1;
	private JPanel contentPane = new JPanel();
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtHireDateStart;
	private JTextField txtHireDateEnd;
	private JTextField txtSalaryPerMonth;
	private JButton btnAdd = new JButton("ADD");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnEdit = new JButton("Edit");
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnStaff = new JRadioButton("Staff");
	private JRadioButton rdbtnManager = new JRadioButton("Manager");
	private JRadioButton rdbtnCashier = new JRadioButton("Cashier");
//	Gia na ftiaxeis Jtable 4 kiniseis
	// 1. ftiaxneis ton pinaka String me ta onomata ton stilon
	private String[] columns = new String[] { "ID", "Name", "Surname", "Hire", "End", "Salary" };
	// 2. Ftiaxneis to model kai vazeis ton pinaka tou vimatos 1. stin stili 0
	private DefaultTableModel model = new DefaultTableModel(columns, 0);
	// 3. Ftiaxneis otn pinaka me constructor to model (Fyika mporeis na kaneis me
	// keno cosntructor kai meta na kaneis table.setModel(model)
	private JTable table = new JTable(model);
	// 4. sinithos to kano mesa ston cosntructor i ftiaxno mia methodo na paro ola
	// ta stoixeia apo ton pinaka tis Database
	// kai na ta valo sto model

	

	/**
	 * Create the frame.
	 */

	public GuiEmployee() {

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 100, 14);
		contentPane.add(lblId);
		// To vima 4 gemizo ton pinaka
		SqlEmployee.fillEmployeeTable(model);
		table.setAutoCreateRowSorter(true);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(true);
		txtId.setBounds(120, 8, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 39, 100, 14);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(120, 36, 86, 20);
		contentPane.add(txtName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 67, 100, 14);
		contentPane.add(lblSurname);

		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(120, 64, 86, 20);
		contentPane.add(txtSurname);

		JLabel lblHireDateStart = new JLabel("Hire Date Start");
		lblHireDateStart.setBounds(10, 98, 100, 14);
		contentPane.add(lblHireDateStart);

		txtHireDateStart = new JTextField();
		txtHireDateStart.setColumns(10);
		txtHireDateStart.setBounds(120, 95, 86, 20);
		contentPane.add(txtHireDateStart);

		JLabel lblHireDateEnd = new JLabel("Hire Date End");
		lblHireDateEnd.setBounds(10, 126, 100, 14);
		contentPane.add(lblHireDateEnd);

		txtHireDateEnd = new JTextField();
		txtHireDateEnd.setColumns(10);
		txtHireDateEnd.setBounds(120, 123, 86, 20);
		contentPane.add(txtHireDateEnd);

		JLabel lblSalaryPerMonth = new JLabel("Salary Per Month");
		lblSalaryPerMonth.setBounds(10, 154, 100, 14);
		contentPane.add(lblSalaryPerMonth);

		txtSalaryPerMonth = new JTextField();
		txtSalaryPerMonth.setColumns(10);
		txtSalaryPerMonth.setBounds(120, 151, 86, 20);
		contentPane.add(txtSalaryPerMonth);

		JLabel lblEmployeeType = new JLabel("Employee Type");
		lblEmployeeType.setBounds(10, 182, 100, 14);
		contentPane.add(lblEmployeeType);

		///////////////////////////////////////////////////////////// Add Button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployee();
			}
		});
		btnAdd.setBounds(10, 269, 77, 23);
		contentPane.add(btnAdd);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1044, 691);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

///////////////////////////////////////////////////////////// Delete Button
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteEmployee(txtId.getText());
//				threads ?????
			}
		});
		btnDelete.setBounds(97, 269, 89, 23);
		contentPane.add(btnDelete);

		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setBounds(192, 269, 89, 23);
		contentPane.add(btnEdit);

		buttonGroup.add(rdbtnStaff);
		rdbtnStaff.setBounds(116, 178, 109, 23);
		contentPane.add(rdbtnStaff);
		buttonGroup.add(rdbtnManager);
		rdbtnManager.setBounds(116, 203, 109, 23);
		contentPane.add(rdbtnManager);
		buttonGroup.add(rdbtnCashier);
		rdbtnCashier.setBounds(116, 229, 109, 23);
		contentPane.add(rdbtnCashier);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(359, 39, 663, 417);
		contentPane.add(scrollPane);
		////////////// Pano ston pinaka diavazo ta klik toy pontikiou
		// Vazo sto table mia MouseAdapter kai epikalypto tin mouseCliked
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				row = table.getSelectedRow();
				fillForm();
			}
		});

		scrollPane.setViewportView(table);
		this.setVisible(true);
		this.setEnabled(true);
	}// end of constructor

	protected void fillForm() {
//ID","Name","Surname","Hire","End","Salary"};
//		System.out.println("Epelexes tin grammi row");
		Employee emp = SqlEmployee.findEmployee(table.getValueAt(row, 0).toString());
		if (emp != null) {
			this.txtId.setText(String.valueOf(emp.getIde()));
			this.txtName.setText(emp.getName());
			this.txtSurname.setText(emp.getSurname());
			this.txtHireDateStart.setText(emp.getHireDateStart().format(Main.frm));
			this.txtHireDateEnd.setText(emp.getHireDateEnd().format(Main.frm));
			this.txtSalaryPerMonth.setText(String.valueOf(emp.getSalaryPerMonth()));
			if (emp.getEmployeeType().equals(Main.employeeType.MANAGER)) {
				this.rdbtnManager.setSelected(true);
			} else if (emp.getEmployeeType().equals(Main.employeeType.CASHIER)) {
				this.rdbtnCashier.setSelected(true);
			} else {
				this.rdbtnStaff.setSelected(true);
			}
		}
	}

	protected void deleteEmployee(String id) { /////////////// DELETE an EMployee
		if (id==null || id.isBlank() || id.isEmpty()) {
			JOptionPane.showMessageDialog(btnDelete, "please select a Employee first!!!","Error Message",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int ans = JOptionPane.showConfirmDialog(btnDelete, "Delete selected Employee","Deletion Are You Sure",JOptionPane.YES_NO_OPTION);
		if(ans == JOptionPane.NO_OPTION) return;
		int idn =Integer.parseInt(id);
		SqlEmployee.deleteEmployee(idn);
		model.removeRow(row);
		JOptionPane.showMessageDialog(btnDelete, "the Customer Deleted","Information Message",JOptionPane.INFORMATION_MESSAGE);
		repaint();
	}

	protected void addEmployee() {
		String name = "";
		String surname = "";
		LocalDate hireDateStart = LocalDate.now();
		LocalDate hireDateEnd = LocalDate.now();
		double salaryPerMonth = 0.0;
		Main.employeeType employeeType = Main.employeeType.STAFF;
		try {
			hireDateStart = LocalDate.parse(txtHireDateStart.getText(), Main.frm);
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(btnAdd, "Wrong Date (dd/MM/yyyy)", "ErrorMessage", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			hireDateEnd = LocalDate.parse(txtHireDateEnd.getText(), Main.frm);
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(btnAdd, "Wrong Date (dd/MM/yyyy)", "ErrorMessage", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			salaryPerMonth = Double.parseDouble(txtSalaryPerMonth.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(btnAdd, "Wrong salary (9999.99)", "ErrorMessage", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (this.rdbtnStaff.isSelected()) {
			employeeType = Main.employeeType.STAFF;
		} else if (this.rdbtnManager.isSelected()) {
			employeeType = Main.employeeType.MANAGER;
		} else if (this.rdbtnCashier.isSelected()) {
			employeeType = Main.employeeType.CASHIER;
		} else {
			JOptionPane.showMessageDialog(btnAdd, "Please Select Employee Type", "ErrorMessage",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Employee e;
		try {
			e = new Employee(txtName.getText(), txtSurname.getText(), hireDateStart, hireDateEnd, salaryPerMonth,
					employeeType);
		} catch (MyException e1) {
			return;
		}
		SqlEmployee.addEmployee(e);
		JOptionPane.showMessageDialog(btnAdd, "Employee Added succesfully!!!");
		SqlEmployee.fillEmployeeTable(model);
	}

}
