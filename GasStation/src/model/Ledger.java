package model;

public class Ledger {
	// year, month, income, expenses, total
	private int year;
	private int month;
	private double income;
	private double expenses;
	private double total;

	public Ledger() {

	}

	public Ledger(int year, int month, double income, double expenses, double total) {
		this.year = year;
		this.month = month;
		this.income = income;
		this.expenses = expenses;
		this.total = total;
	}

	public Ledger(int month, double income, double expenses, double total) {
		this.month = month;
		this.income = income;
		this.expenses = expenses;
		this.total = total;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Ledger [year = " + year + ", month = " + month + ", income = " + income + ", expenses = " + expenses
				+ ", total = " + total + "]";
	}

}
