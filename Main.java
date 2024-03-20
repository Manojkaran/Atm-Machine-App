package com.project;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		int accNo;
		int pin;
		String Name;
		int balance;
		bankDao bank = new bankDao();
		System.out.println("-------------------");
		System.out.println("Welcome to SBI bank");
		System.out.println("Enter account number");
		accNo = sc.nextInt();
		System.out.println("Enter pin           ");
		pin = sc.nextInt();
		boolean verify = bank.pinVerification(accNo, pin);
		if (verify == true) {
			bank.bankPojo(accNo);
			int key = 0;
			while (key != 5) {
				System.out.println("-------------------");
				System.out.println("-------------------");
				System.out.println("1.Deposit amount   ");
				System.out.println("2.withdraw amount    ");
				System.out.println("3.Pin change       ");
				System.out.println("4.Balance check    ");
				System.out.println("5.Exit             ");
				System.out.println("-------------------");
				key = sc.nextInt();
				switch (key) {
				case 1: {
					System.out.println("Enter amount to be Deposited");
					int depsoit = sc.nextInt();
					boolean bool = bank.deposit(accNo, depsoit);
					if (bool == true) {
						System.out.println("The Amount is deposited sucessfully");
						System.out.println("Balance:- " + bank.balanceCheck(accNo));
					}
					break;
				}
				case 2: {
					System.out.println("Enter amount to be withdraw sucessfully");
					int withdraw = sc.nextInt();
					boolean bool = bank.withdraw(accNo, withdraw);
					if (bool == true) {
						System.out.println("The Amount is withdrawn");
						System.out.println("Balance:- " + bank.balanceCheck(accNo));
					}
					break;
				}
				case 3: {
					System.out.println("Enter new pin to change");
					pin = sc.nextInt();
					boolean bool = bank.pinChange(accNo, pin);
					if (bool == true) {
						System.out.println("The pin number changed sucessfully");
					}
					break;
				}
				case 4: {
					balance = bank.balanceCheck(accNo);
					System.out.println("Balance:- " + balance);
					break;
				}
				case 5: {
					key = 5;
					System.out.println("Thank you for banking");
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + key);
				}
			}
		} else
			System.out.println("Wrong pin");
	}
}
