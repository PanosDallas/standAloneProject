package model;

import utils.MyException;

// Bhma 1 kano tin class extends Throwable
public class Customer extends Throwable{
	// idc, name, surname, car_number
	private int idc;
	private String name;
	private String surname;
	private String cardNumber;
	
	public Customer() {
	}
    // BHMA 2 syndeo tin methodo poy tha elegxo me tin MyException 
	public Customer(int idc, String name, String surname, String c) throws MyException {
		// BHMA 3 kano ton elegxo kai petao tin exairesi
		if (!MyException.isCardValidNumber(c)) throw new MyException("Lathos arithmos kartas");
		this.idc = idc;
		this.name = name;
		this.surname = surname;
		this.cardNumber = c;
	}

	public Customer(String name, String surname, String c) throws MyException {
		if (!MyException.isCardValidNumber(c)) throw new MyException("Lathos arithmos kartas");
		this.name = name;
		this.surname = surname;
		this.cardNumber = c;
	}

	public int getIdc() {
		return idc;
	}

	public void setIdc(int idc) {
		this.idc = idc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) throws MyException {
		if (!MyException.isCardValidNumber(cardNumber)) throw new MyException("Lathos arithmos kartas");
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "Customer [idc = " + idc + ", name = " + name + ", surname = " + surname + ", Credit card number = "
				+ cardNumber + "]";
	}

}
