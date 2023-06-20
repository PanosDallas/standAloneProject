package sql;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Item;
import model.Main;
import utils.MyException;

public class SqlItem extends SqlHandle {

//	idi, code, description, item_type, price, cost, idtl, idt, idc
	public static boolean addItem(Item i) {
//		
		String sql = "INSERT INTO `fuel_station`.`item` (`code`, `description`,`item_type`,`price`,`cost`) "
				+ "VALUES ('" + i.getCode() + "', '" + i.getDescription() + "', '" + i.getItemType().toString() + "', '"
				+ i.getPrice() + "', '" + i.getCost() + "');";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
			JOptionPane.showMessageDialog(null, "Added successfully item " + i.getCode(), "Information Message",
					JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (com.mysql.cj.jdbc.exceptions.MysqlDataTruncation ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error Price or Cost out of range", "Error Message",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error in Data Base", "Error Message", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		return false;
	}

	public static void fillItemTable(DefaultTableModel model) {
//		idi, code, description, item_type, price, cost, idtl, idt, idc
		String sql = "SELECT `idi`,`code`, `description`,`price`,`cost` FROM `fuel_station`.`item`";
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				// name, surname, hire_date_start, hire_date_end, salary_per_month
				int idi = rs.getInt("idi"); // Edo pairneis ta field names apo ton table
				String code = rs.getString("code");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				double cost = rs.getDouble("cost");
				Object[] obj = { idi, code, description, price, cost };
				model.addRow(obj);

				// Tha mporouses na to kaneis me mia grammi
//				results.add(new Provider(rs.getInt("idp"),rs.getString("name"),rs.getDouble("credit")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public static Item findItem(String i) {
		String sql = "SELECT * FROM `fuel_station`.`item` WHERE idi=" + i + ";";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				//idi, code, description, item_type, price, cost, idtl, idt, idc
				int idi = rs.getInt("idi"); // Edo pairneis ta field names apo ton table
				String code = rs.getString("code");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				double cost = rs.getDouble("cost");
				String type = rs.getString("item_type");
				Main.itemType itemType = Main.itemType.FUEL;
				if (type.equals("FUEL"))
					itemType = Main.itemType.FUEL;
				else if (type.equals("PRODUCT"))
					itemType = Main.itemType.PRODUCT;
				else if (type.equals("SERVICE"))
					itemType = Main.itemType.SERVICE;
//			else edo tha kaneis exception
				
			
				Item i1 = null;
				
					i1 = new Item(idi, code, description, itemType, price, cost);
				return i1;

				// Tha mporouses na to kaneis me mia grammi
//			results.add(new Provider(rs.getInt("idp"),rs.getString("name"),rs.getDouble("credit")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public static void deleteEmployee(int id) { /////////////////// Delete a customer
		String sql = "DELETE FROM `fuel_station`.`employee` WHERE (`ide` = '" + id + "')";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//LocalDate hireDateStart, LocalDate hireDateEnd,double salaryPerMonth, model.Main.employeeType employeeType
	public static void editEmployee(int id, String name, String surname, String cardNumber) {

		// idc, name, surname, car_number
		String sql = "UPDATE `fuel_station`.`customer` SET `name` = '" + name + "', `car_number` = '" + cardNumber
				+ "', `surname` = '" + surname + "' WHERE (`idc` = '" + id + "');";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
