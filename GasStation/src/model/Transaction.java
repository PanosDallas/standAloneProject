package model;

import java.time.LocalDate;

public class Transaction {
	// idt, date, paymentMethod, totalValue
	private int idt;
	private LocalDate date;
	private Main.paymentMethod paymentMethod;
	private double totalValue;

	public Transaction() {

	}

	public Transaction(int idt, LocalDate date, model.Main.paymentMethod paymentMethod, double totalValue) {
		this.idt = idt;
		this.date = date;
		this.paymentMethod = paymentMethod;
		this.totalValue = totalValue;
	}
	public Transaction(LocalDate date, model.Main.paymentMethod paymentMethod, double totalValue) {
		this.date = date;
		this.paymentMethod = paymentMethod;
		this.totalValue = totalValue;
	}

	public int getIdt() {
		return idt;
	}

	public void setIdt(int idt) {
		this.idt = idt;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Main.paymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Main.paymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return "Transaction [idt=" + idt + ", date=" + date + ", paymentMethod=" + paymentMethod + ", totalValue="
				+ totalValue + "]";
	}

	
	
}
