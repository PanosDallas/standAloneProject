package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import model.Item;
import model.Main;
import sql.SqlEmployee;
import sql.SqlItem;

public class GuiItem extends JFrame {
	private int row = -1;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtCode;
	private JTextField txtDescription;
	private JTextField txtPrice;
	private JTextField txtCost;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnFuel = new JRadioButton("Fuel");
	private JRadioButton rdbtnProduct = new JRadioButton("Product");
	private JRadioButton rdbtnService = new JRadioButton("Service");

	private JButton btnAdd = new JButton("ADD");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnEdit = new JButton("Edit");

//	Gia na ftiaxeis Jtable 4 kiniseis
	// 1. ftiaxneis ton pinaka String me ta onomata ton stilon
	private String[] columns = new String[] { "ID", "Code", "Description", "Price", "Cost" };
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
	public GuiItem() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 996, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		SqlItem.fillItemTable(model);
		table.setAutoCreateRowSorter(true);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(38, 23, 97, 20);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(157, 23, 96, 20);
		txtId.setEditable(false);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(38, 54, 97, 20);
		contentPane.add(lblCode);

		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(157, 54, 96, 20);
		contentPane.add(txtCode);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(38, 85, 97, 20);
		contentPane.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(157, 85, 96, 20);
		contentPane.add(txtDescription);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(38, 116, 97, 20);
		contentPane.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(157, 116, 96, 20);
		contentPane.add(txtPrice);

		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(38, 147, 97, 20);
		contentPane.add(lblCost);

		txtCost = new JTextField();
		txtCost.setColumns(10);
		txtCost.setBounds(157, 147, 96, 20);
		contentPane.add(txtCost);

		JLabel lblItemType = new JLabel("Type");
		lblItemType.setBounds(38, 178, 97, 20);
		contentPane.add(lblItemType);

		buttonGroup.add(rdbtnFuel);
		rdbtnFuel.setBounds(154, 177, 109, 23);
		contentPane.add(rdbtnFuel);

		buttonGroup.add(rdbtnProduct);
		rdbtnProduct.setBounds(154, 203, 109, 23);
		contentPane.add(rdbtnProduct);

		buttonGroup.add(rdbtnService);
		rdbtnService.setBounds(154, 229, 109, 23);
		contentPane.add(rdbtnService);
		//	/////////////////////////////////////////////////////////// Add Button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addItem();
			}
		});
		btnAdd.setBounds(28, 277, 77, 23);
		contentPane.add(btnAdd);
		///////////////////////////////////////////////////////////// Delete Button
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteItem(txtId.getText());
				// threads ?????
			}
		});
		///////////////////////////////////////////////////////////// Edit Button
		btnDelete.setBounds(115, 277, 89, 23);
		contentPane.add(btnDelete);

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editItem();
			}
		});
		btnEdit.setBounds(214, 277, 89, 23);
		contentPane.add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(359, 39, 589, 317);
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
		
		
		setVisible(true);
		setEnabled(true);
	}

	protected void editItem() {
	}

	protected void deleteItem(String text) {
		// TODO Auto-generated method stub

	}

	protected void addItem() {
		Main.itemType itemType = Main.itemType.FUEL;
		double price = 0.0;
		double cost = 0.0;
		try {
			price = Double.parseDouble(txtPrice.getText());
			cost =  Double.parseDouble(txtCost.getText());
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(btnAdd,"Error not valid number","Error Message",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (this.rdbtnFuel.isSelected()) {
			itemType = Main.itemType.FUEL;
		} else if (this.rdbtnProduct.isSelected()) {
			itemType = Main.itemType.PRODUCT;
		} else if (this.rdbtnService.isSelected()) {
			itemType = Main.itemType.SERVICE;
		} else {
			JOptionPane.showMessageDialog(btnAdd, "Please Select Item Type", "ErrorMessage",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Item i;
		i = new Item(txtCode.getText(), txtDescription.getText(),itemType,price,cost);
		if(SqlItem.addItem(i)) {
			SqlItem.fillItemTable(model);
		}
	}

	protected void fillForm() { // tha gemiso tin forma
		// TODO Auto-generated method stub

	}
}
