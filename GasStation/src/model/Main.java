package model;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import gui.GuiMain;

public class Main {
	public static final DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final SimpleDateFormat dfrm = new SimpleDateFormat("dd/MM/yyyy");
	public static final DateTimeFormatter dtfrm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	 
	public static void main(String[] args) {
		new GuiMain();
		//SqlCustomer.addCustomer(c1);
		//SqlCustomer.disconnect();
	}

	
	public static enum employeeType{
		MANAGER,
		STAFF,
		CASHIER
	}
	public static enum itemType{
		FUEL,
		PRODUCT,
		SERVICE
	}
	public static enum paymentMethod{
		CREDITCARD,
		CASH
	}

}

/*
• Customer: ID, Name, Surname, CardNumber
• Item: ID, Code, Description, ItemType (enum), Price, Cost
• Employee: ID, Name, Surname, HireDateStart,HireDateEnd, SallaryPerMonth, EmployeeType (enum)
• Transaction: ID, Date, EmployeeID, CustomerID, PaymentMethod (enum), TotalValue
• TransactionLine: ID, TransactionID, ItemID, Quantity,ItemPrice, NetValue, DiscountPercent, DiscountValue,TotalValue.
• Ledger: Year, Month, Income, Expenses, Total
*/