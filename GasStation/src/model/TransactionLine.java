package model;

public class TransactionLine {
	// idtl, quantity, itemPrice, netValue, discountPercent, discountValue,
	// totalValue
	private int idtl;
	private int quantity;
	private double itemPrice;
	private double netValue;
	private int discountPercent;
	private double discountValue;
	private double totalValue;

	public TransactionLine() {

	}

	public TransactionLine(int idtl, int quantity, double itemPrice, double netValue, int discountPercent,
			double discountValue, double totalValue) {
		this.idtl = idtl;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.netValue = netValue;
		this.discountPercent = discountPercent;
		this.discountValue = discountValue;
		this.totalValue = totalValue;
	}

	public TransactionLine(int quantity, double itemPrice, double netValue, int discountPercent, double discountValue,
			double totalValue) {
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.netValue = netValue;
		this.discountPercent = discountPercent;
		this.discountValue = discountValue;
		this.totalValue = totalValue;
	}

	public int getIdtl() {
		return idtl;
	}

	public void setIdtl(int idtl) {
		this.idtl = idtl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public double getNetValue() {
		return netValue;
	}

	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return "TransactionLine [idtl = " + idtl + ", quantity = " + quantity + ", itemPrice = " + itemPrice
				+ ", netValue = " + netValue + ", discountPercent = " + discountPercent + ", discountValue = "
				+ discountValue + ", totalValue = " + totalValue + "]";
	}

}
