package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import model.Customer;
import utils.MyException;



public class SqlCustomer extends SqlHandle {


	// idc, name, surname, car_number
//	public static void addCustomer(Customer c, DefaultTableModel model) {
	public static void addCustomer(Customer c) {
		String sql = "INSERT INTO `fuel_station`.`customer` (`name`, `surname`,`car_number`) "
				+ "VALUES ('" + c.getName() + "', '"
				+ c.getSurname() + "', '"
				+ c.getCardNumber() + "');";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void fillCustomerTable(DefaultTableModel model) {
//idc, name, surname, car_number
		String sql = "SELECT `idc`,`name`, `surname`,`car_number` FROM `fuel_station`.`customer`";
		for(int i = model.getRowCount()-1;i>=0;i--) {
			model.removeRow(i);
		}
		try {
			Statement stm = SqlCustomer.conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				//idc,name, surname, car_number
				int idc = rs.getInt("idc"); // Edo pairneis ta field names apo ton table
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String car_number = rs.getString("car_number");
				Object[] obj = {idc,name,surname,car_number};
				model.addRow(obj);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;
	}
	
	public static Customer findCustomer(String i) throws MyException  {
		String sql = "SELECT * FROM `fuel_station`.`customer` WHERE idc="+i+";";
		try {
			Statement stm = SqlCustomer.conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			//name, surname, car_number
			//idc, name, surname, car_number
			int idc = rs.getInt("idc"); // Edo pairneis ta field names apo ton table
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String car_number = rs.getString("car_number");
			
			Customer c = null;
			c = new Customer(idc, name,surname,car_number );
			return c;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}		
		
		
		return null;
	}

	public static void deleteCustomer(int id) {  /////////////////// Delete a customer
		String sql = "DELETE FROM `fuel_station`.`customer` WHERE (`idc` = '"+id+"')";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public static void editCustomer(int id,String name,String surname,String cardNumber) {
//		UPDATE `fuel_station`.`customer` SET `name` = 'MAnolakis', `surname` = 'Mastoras' WHERE (`idc` = '10');
		//idc, name, surname, car_number
		String sql = "UPDATE `fuel_station`.`customer` SET `name` = '"+name
										+"', `surname` = '"+surname
										+"', `car_number` = '"+cardNumber
										+"' WHERE (`idc` = '"+id+"');";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
}
