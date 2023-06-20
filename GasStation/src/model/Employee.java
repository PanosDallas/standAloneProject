package model;

import java.time.LocalDate;

import utils.MyException;

public class Employee extends Throwable {
//ide, name, surname, hire_date_start,hire_date_end, salary_per_month, employee_type
	private int ide;
	private String name;
	private String surname;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private LocalDate hireDateStart;
	private LocalDate hireDateEnd;
	private double salaryPerMonth;
	private Main.employeeType employeeType;

	public Employee() {	}

	
	
	public Employee(String name, String surname, LocalDate hireDateStart, LocalDate hireDateEnd,
			double salaryPerMonth, model.Main.employeeType employeeType) throws MyException {
		this.name = name;
		this.surname = surname;
		this.hireDateStart = hireDateStart;
		this.hireDateEnd = hireDateEnd;
		if(MyException.validateSalary(salaryPerMonth)) throw new MyException("Salary > 0 & Salary < 9999.99");
		this.salaryPerMonth = salaryPerMonth;
		this.employeeType = employeeType;
	}
	
	public Employee(int ide, String name, String surname, LocalDate hireDateStart, LocalDate hireDateEnd,
			double salaryPerMonth, model.Main.employeeType employeeType) throws MyException {
		this.ide = ide;
		this.name = name;
		this.surname = surname;
		this.hireDateStart = hireDateStart;
		this.hireDateEnd = hireDateEnd;
		if(MyException.validateSalary(salaryPerMonth)) throw new MyException("Salary > 0 & Salary < 9999.99");
		this.salaryPerMonth = salaryPerMonth;
		this.employeeType = employeeType;
	}
	

	public int getIde() {
		return ide;
	}

	public void setIde(int ide) {
		this.ide = ide;
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

	public LocalDate getHireDateStart() {
		return hireDateStart;
	}

	public void setHireDateStart(LocalDate hireDateStart) {
		this.hireDateStart = hireDateStart;
	}

	public LocalDate getHireDateEnd() {
		return hireDateEnd;
	}

	public void setHireDateEnd(LocalDate hireDateEnd) {
		this.hireDateEnd = hireDateEnd;
	}

	public double getSalaryPerMonth() {
		return salaryPerMonth;
	}

	public void setSalaryPerMonth(double salaryPerMonth) {
		this.salaryPerMonth = salaryPerMonth;
	}

	public Main.employeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(Main.employeeType employeeType) {
		this.employeeType = employeeType;
	}



	@Override
	public String toString() {
		return "Employee [ide=" + ide + ", name=" + name + ", surname=" + surname + ", hireDateStart=" + hireDateStart
				+ ", hireDateEnd=" + hireDateEnd + ", salaryPerMonth=" + salaryPerMonth + ", employeeType="
				+ employeeType + "]";
	}

	
	
}
