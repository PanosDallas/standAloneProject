package utils;

import java.awt.Component;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import model.Main;

public class MyException extends Exception{

	public MyException(Component graphObject, String message) {
		JOptionPane.showMessageDialog(graphObject,message,"ErrorMessage",JOptionPane.ERROR_MESSAGE);
	}
	public MyException( String message) {
		JOptionPane.showMessageDialog(null,message,"ErrorMessage",JOptionPane.ERROR_MESSAGE);
	}
	///////////////////////// credit card validation start ////////////////////////////////
    public static boolean isCardValidNumber(String cardNumber)
    {
    	long number = 0;
    	try {
    		 number = Long.parseLong(cardNumber);
    	}
    	catch(NumberFormatException e){
    		return false;
    	}
        return (getSize(number) >= 13 &&
               getSize(number) <= 16) &&
               (prefixMatched(number, 4) ||
                prefixMatched(number, 5) ||
                prefixMatched(number, 37) ||
                prefixMatched(number, 6)) &&
              ((sumOfDoubleEvenPlace(number) +
                sumOfOddPlace(number)) % 10 == 0);
    }
 
    // Get the result from Step 2
    public static int sumOfDoubleEvenPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
         
        return sum;
    }
 
    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }
 
    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");       
        return sum;
    }
 
    // Return true if the digit d is a prefix for number
    public static boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }
 
    // Return the number of digits in d
    public static int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }
 
    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getPrefix(long number, int k)
    {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
	///////////////////////// credit card validation end ////////////////////////////////
	
	
	
	
	
	public static boolean validateSalary(double salary) {
		if(salary<0 || salary>9999.99) {
			return true;
		}else
		return false;
	}
	public boolean ifStartDateIsAfterEndDate(String sStart,String sEnd) {
		LocalDate start=LocalDate.now(), end =LocalDate.now();
		try {
			start= LocalDate.parse(sStart,Main.frm);
			end= LocalDate.parse(sEnd,Main.frm);
		}
		catch(DateTimeParseException e) {
			JOptionPane.showMessageDialog(null,"Wrong Date (dd/MM/yyyy)","ErrorMessage",JOptionPane.ERROR_MESSAGE);
			return true;
		}
		if(end.isBefore(start)) return true;
		// kai edo mporeis na kaneis plithos elegxon symperalamvanoumenou kai to format
		return false;
	}
	public static LocalDate convertDateToLocalDate(Date d) {
		try {
			LocalDate date =Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		  
		 return date;
		}
		catch(Exception e) {
			System.out.println("Den katrafera na metatrepso tin imeromnia se LocalDate");
			// kai edo na atidraseis
			return LocalDate.now();
		}
		
	}
	public static boolean isCreditCardInvalid(String card) {
		if(card.isBlank() || card.isEmpty() || card.length()!=16) return true;
		if(!card.matches("[0-9]+")) return true;
		//Gia kapoia sovari douleia https://java2blog.com/luhn-algorithm-java/
		return false;
	}
}
/*
American Express 			378282246310005
American Express 			371449635398431
American Express Corporate 	378734493671000
Australian BankCard			5610591081018250
Diners Club					30569309025904
Diners Club					38520000023237
Discover					6011111111111117

Discover

6011000990139424

JCB

3530111333300000

JCB

3566002020360505

MasterCard

5555555555554444

MasterCard

5105105105105100

Visa

4111111111111111

Visa

4012888888881881 
 */ 
