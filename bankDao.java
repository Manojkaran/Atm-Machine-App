package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bankDao {
	Connection con;
	Statement cs;
	ResultSet rs;

	public void getdbConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Amanoj@123");
		cs = con.createStatement();
	}

	public boolean pinVerification(int accNo, int pin) throws SQLException, ClassNotFoundException {
		getdbConnection();
		rs = cs.executeQuery("select * from customer where accNo=" + accNo + "");
		while (rs.next()) {
			int orgPin = rs.getInt(2);
			if (orgPin == pin)
				return true;
		}
		return false;
	}

	public String getName(int accNo, int pin) throws SQLException, ClassNotFoundException {
		getdbConnection();
		rs = cs.executeQuery("select * from customer where accNo=" + accNo + "");
		String Name = null;
		while (rs.next()) {
			Name = rs.getString(3);
			return Name;
		}
		return null;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		bankDao b = new bankDao();
		System.out.println(b.pinVerification(2, 1234));
	}

	public boolean pinChange(int accNo, int pin) throws SQLException {
		rs = cs.executeQuery("select * from customer where accNo=" + accNo + "");
		int rows = cs.executeUpdate("update customer set pin=" + pin + " where accNo=" + accNo + "");
		if (rows > 0) {
			return true;
		}
		return false;
	}

	public boolean deposit(int accNo, int deposit) throws SQLException {
		int balance = 0;
		rs = cs.executeQuery("select * from customer where accNo=" + accNo + "");
		while (rs.next()) {
			balance = rs.getInt(4);
		}
		balance = deposit + balance;
		int rows = cs.executeUpdate("update customer set balance=" + balance + " where accNo=" + accNo + "");
		if (rows > 0) {
			return true;
		}
		return false;
	}

	public boolean withdraw(int accNo, int withdraw) throws SQLException {
		int balance = 0;
		rs = cs.executeQuery("select * from customer where accNo=" + accNo + "");
		while (rs.next()) {
			balance = rs.getInt(4);
		}
		if (balance > withdraw) {
			balance = balance - withdraw;
			int rows = cs.executeUpdate("update customer set balance=" + balance + " where accNo=" + accNo + "");
			if (rows > 0) {
				return true;
			}
		}
		return false;
	}

	public int balanceCheck(int accNo) throws SQLException {
		int balance = 0;
		rs = cs.executeQuery("select * from customer where accNo=" + accNo + "");
		while (rs.next()) {
			balance = rs.getInt(4);
		}
		return balance;
	}

	public void bankPojo(int accNo) throws SQLException {
		rs = cs.executeQuery("select * from customer where accNo=" + accNo + "");
		int pin = 0, balance = 0;
		String Name = null;
		while (rs.next()) {
			pin = rs.getInt(2);
			Name = rs.getString(3);
			balance = rs.getInt(4);
		}
		customerPojo customerPojo = new customerPojo(accNo, pin, Name, balance);
		System.out.println("Welcome       :-"+customerPojo.getName());
		System.out.println("Account Number:-"+customerPojo.getAccNo());
		System.out.println("Balance       :-"+customerPojo.getBalance());
	}
}
