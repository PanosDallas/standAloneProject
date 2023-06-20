package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHandle {

	public static Connection conn = null;

	public static void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fuel_station", "root", "Pass123!@#");
		//	System.out.println("Egine i syndesi me tin vasi epityxos");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		if (conn != null)
			try {
				conn.close();
				//System.out.println("EKleise i syndesi me tin vasi epityxos");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
