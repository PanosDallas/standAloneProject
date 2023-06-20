package sql;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.Main;
import utils.MyException;



public class SqlEmployee extends SqlHandle {

	// ide, name, surname, hire_date_start,hire_date_end,salary_per_month,employee_type
	public static void addEmployee(Employee e) {
//		ide, name, surname, hire_date_start, hire_date_end, salary_per_month, employee_type
		String sql = "INSERT INTO `fuel_station`.`employee` (`name`, `surname`,`hire_date_start`,`hire_date_end`,`salary_per_month`,`employee_type`) "
				+ "VALUES ('" + e.getName() + "', '"
				+ e.getSurname() + "', '"
				+ e.getHireDateStart() + "', '"
				+ e.getHireDateEnd() + "', '"
				+ e.getSalaryPerMonth() + "', '"
				+ e.getEmployeeType()+"'"+");";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
			System.out.println("Prostethike epityxos o " + e.getName()+" "+e.getSurname()
			+" pou ksekinise thn ergasia tou stis "+e.getHireDateStart()+"kai teleiose stis "+e.getHireDateEnd()
			+". O miniaios misthos tou einai "+e.getSalaryPerMonth()+" kai o typos tou einai " + e.getEmployeeType());
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}
	
	public static void fillEmployeeTable(DefaultTableModel model) {
		String sql = "SELECT `ide`,`name`, `surname`,`hire_date_start`,`hire_date_end`,`salary_per_month` FROM `fuel_station`.`employee`";
		for(int i = model.getRowCount()-1;i>=0;i--) {
			model.removeRow(i);
		}
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				//name, surname, hire_date_start, hire_date_end, salary_per_month
				int ide = rs.getInt("ide"); // Edo pairneis ta field names apo ton table
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Date start = rs.getDate("hire_date_start");
				Date end = rs.getDate("hire_date_end");
				double salary = rs.getDouble("salary_per_month");
				Object[] obj = {ide,name,surname,start,end,salary};
				model.addRow(obj);
				
				// Tha mporouses na to kaneis me mia grammi
//				results.add(new Provider(rs.getInt("idp"),rs.getString("name"),rs.getDouble("credit")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;
	}

	public static Employee findEmployee(String i) {
		String sql = "SELECT * FROM `fuel_station`.`employee` WHERE ide="+i+";";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			//name, surname, hire_date_start, hire_date_end, salary_per_month
			//ide, name, surname, hire_date_start, hire_date_end, salary_per_month, employee_type
			int ide = rs.getInt("ide"); // Edo pairneis ta field names apo ton table
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Date start = rs.getDate("hire_date_start");
			Date end = rs.getDate("hire_date_end");
			double salary = rs.getDouble("salary_per_month");
			String type = rs.getString("employee_type");
			Main.employeeType employeeType = Main.employeeType.STAFF;
			if(type.equals("MANAGER"))employeeType = Main.employeeType.MANAGER;
			else if(type.equals("CASHIER"))employeeType = Main.employeeType.CASHIER;
			else if(type.equals("STAFF"))employeeType = Main.employeeType.STAFF;
//			else edo tha kaneis exception
			LocalDate lStart = LocalDate.now();
			LocalDate lEnd = LocalDate.now();
			if(start!=null)
				lStart = MyException.convertDateToLocalDate(start);
			if(end!=null)
			    lEnd = MyException.convertDateToLocalDate(end);
			Employee e = null;
			try {
				e = new Employee(ide, name,surname,lStart ,lEnd,salary,employeeType);
			} catch (MyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return e;
			
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
	
	public static void deleteEmployee(int id) {  /////////////////// Delete a customer
		String sql = "DELETE FROM `fuel_station`.`employee` WHERE (`ide` = '"+id+"')";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
//LocalDate hireDateStart, LocalDate hireDateEnd,double salaryPerMonth, model.Main.employeeType employeeType
public static void editEmployee(int id,String name,String surname,String cardNumber) {
		
		//idc, name, surname, car_number
		String sql = "UPDATE `fuel_station`.`customer` SET `name` = '"+name
										+"', `car_number` = '"+cardNumber
										+"', `surname` = '"+surname
										+"' WHERE (`idc` = '"+id+"');";
		try {
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
// gemizo ton pinaka sto item

}
