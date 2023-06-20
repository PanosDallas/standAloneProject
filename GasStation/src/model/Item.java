package model;

//ID, Code, Description, ItemType (enum), Price, Cost
public class Item {
	// idi, code, description, itemType , price, cost
	private int idi;
	private String code;
	private String description;
	private Main.itemType itemType;
	private double price;
	private double cost;

	public Item() {}

	public Item(int idi, String code, String description, model.Main.itemType itemType, double price, double cost) {
		this.idi = idi;
		this.code = code;
		this.description = description;
		this.itemType = itemType;
		this.price = price;
		this.cost = cost;
	}
	public Item( String code, String description, model.Main.itemType itemType, double price, double cost) {
		this.code = code;
		this.description = description;
		this.itemType = itemType;
		this.price = price;
		this.cost = cost;
	}

	public int getIdi() {
		return idi;
	}

	public void setIdi(int idi) {
		this.idi = idi;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Main.itemType getItemType() {
		return itemType;
	}

	public void setItemType(Main.itemType itemType) {
		this.itemType = itemType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Item [idi=" + idi + ", code=" + code + ", description=" + description + ", itemType=" + itemType
				+ ", price=" + price + ", cost=" + cost + "]";
	}
	
	
}
