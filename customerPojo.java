package com.project;

public class customerPojo 
{
	private int accNo;
	private int pin;
	private String Name;
	private int balance;
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public customerPojo(int accNo, int pin, String name, int balance) {
		super();
		this.accNo = accNo;
		this.pin = pin;
		Name = name;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "customerDao [accNo=" + accNo + ", Name=" + Name + ", balance=" + balance + "]";
	}
	
	
}
