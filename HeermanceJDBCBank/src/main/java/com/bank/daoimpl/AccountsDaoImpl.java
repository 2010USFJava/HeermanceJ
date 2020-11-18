package com.bank.daoimpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.bank.bean.Accounts;
import com.bank.bean.Accounts.accountType;
import com.bank.dao.AccountsDAO;
import com.bank.exceptions.NegativeAmountException;
import com.bank.exceptions.NotMoneyException;
import com.bank.exceptions.OverdraftException;
import com.bank.menu.Menu;
import com.bank.util.ConnFactory;
import com.bank.util.LogThis;

public class AccountsDaoImpl implements AccountsDAO{
	
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public long newAccount(Accounts a) throws SQLException {
		long acctid = 0;
		Connection conn = cf.getConnection();
		String sql = "insert into \"Accounts\" values(DEFAULT,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1,a.getType().toString());
		ps.setBoolean(2,a.isAccountStatus());
		ps.setDouble(3,a.getBalance());
		int affectedRows = ps.executeUpdate();
		if(affectedRows>0) {
			ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					acctid = rs.getLong(1);
			}
		}
		LogThis.LogIt("info", "Account "+acctid+ " was applied for.");
		return acctid;
	}
	
	@Override
	public List<Accounts> getInactiveAccounts() throws SQLException {
		List<Accounts> acctlist = new ArrayList<>();
		Connection conn = cf.getConnection();
		String sql = "select * from \"Accounts\" where \"Account_Status\"= 'false'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Accounts a = null;
		while(rs.next()) {
			a = new Accounts(rs.getLong(1),accountType.valueOf(rs.getString(2)),rs.getBoolean(3),rs.getDouble(4));
			acctlist.add(a);
		}
		System.out.println(acctlist.toString());
		return acctlist;
	}
	
	@Override
	public void updateAccountStatus(long acctid) throws SQLException{
		Connection conn = cf.getConnection();
		String sql2 = "update \"Accounts\" set \"Account_Status\"='true' where \"Account_ID\"=?";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ps2.setLong(1, acctid);
		ps2.executeUpdate();
		LogThis.LogIt("info", "Account " +acctid+ " has been approved.");
	}
	
	@Override
	public Accounts getAccountByID(long acctid) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from \"Accounts\" where \"Account_ID\"=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, acctid);
		ResultSet rs = ps.executeQuery();
		Accounts acct = null;
		if(rs!=null) {
			while(rs.next()) {
				acct = new Accounts(rs.getLong(1),accountType.valueOf(rs.getString(2)),rs.getBoolean(3),rs.getDouble(4));
				return acct;
			}
		}
		return null;
	}
	
	@Override
	public void deposit(long acctid, double amount,String username) throws SQLException {
		Connection conn = cf.getConnection();
		AccountsDaoImpl adi = new AccountsDaoImpl();
		Accounts acct = adi.getAccountByID(acctid);
		
		double balance = acct.getBalance();
		double updateBal = 0;
		try {
			if(amount <0) {
				throw new NegativeAmountException();
			}
			else {
				updateBal = balance + amount;
				acct.setBalance(updateBal);
				balance = acct.getBalance();
				String sql = "update \"Accounts\" set \"Balance\"=? where \"Account_ID\"=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, balance);
				ps.setLong(2, acctid);
				ps.executeUpdate();
				System.out.println("Your new balance is "+balance);
				LogThis.LogIt("info", acctid+" had a deposit of $"+amount+" for a new balance of $"+balance);
			}
		}catch(NegativeAmountException ex1) {
			ex1.getMessage();
			FileInputStream fis;
			try {
				CustomerDaoImpl cdi = new CustomerDaoImpl();
				fis = new FileInputStream("database.properties");
				Properties p = new Properties();
				p.load(fis);
				String auser = (String) p.get("auser");
				if(auser.equals(username)) {
					Menu.adminMenu(username);
				}else {
					Menu.customerMenu(cdi.getCustomerByUsername(username));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void withdraw(long acctid,double amount,String username) throws SQLException {
		Connection conn = cf.getConnection();
		AccountsDaoImpl adi = new AccountsDaoImpl();
		Accounts acct = adi.getAccountByID(acctid);
		
		double balance = acct.getBalance();
		double updateBal = 0;
		try {
			if(amount <0) {
				throw new NegativeAmountException();
			}
			else if (amount > balance) {
				throw new OverdraftException();
			}
			else {
				updateBal = balance - amount;
				acct.setBalance(updateBal);
				balance = acct.getBalance();
				String sql = "update \"Accounts\" set \"Balance\"=? where \"Account_ID\"=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, balance);
				ps.setLong(2, acctid);
				ps.executeUpdate();
				System.out.println("Your new balance is "+balance);
				LogThis.LogIt("info", acctid+" had a withdraw of $"+amount+" for a new balance of $"+balance);
			}
		}catch(NegativeAmountException ex1) {
			ex1.getMessage();
			FileInputStream fis;
			try {
				CustomerDaoImpl cdi = new CustomerDaoImpl();
				fis = new FileInputStream("database.properties");
				Properties p = new Properties();
				p.load(fis);
				String auser = (String) p.get("auser");
				if(auser.equals(username)) {
					Menu.adminMenu(username);
				}else {
					Menu.customerMenu(cdi.getCustomerByUsername(username));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch(NotMoneyException ex2) {
			ex2.getMessage();
			FileInputStream fis;
			try {
				CustomerDaoImpl cdi = new CustomerDaoImpl();
				fis = new FileInputStream("database.properties");
				Properties p = new Properties();
				p.load(fis);
				String auser = (String) p.get("auser");
				if(auser.equals(username)) {
					Menu.adminMenu(username);
				}else {
					Menu.customerMenu(cdi.getCustomerByUsername(username));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void transfer(long acctid1, long acctid2,double amount,String username) throws SQLException {
		Connection conn = cf.getConnection();
		AccountsDaoImpl adi = new AccountsDaoImpl();
		Accounts acct1 = adi.getAccountByID(acctid1);
		Accounts acct2 = adi.getAccountByID(acctid2);
		
		double balance1 = acct1.getBalance();
		double balance2 = acct2.getBalance();
		double updateBal1 = 0;
		double updateBal2=0;
		try {
			if(amount <0) {
				throw new NegativeAmountException();
			}
			else if (amount > balance1) {
				throw new OverdraftException();
			}
			else {
				updateBal1 = balance1 - amount;
				acct1.setBalance(updateBal1);
				balance1 = acct1.getBalance();
				String sql = "update \"Accounts\" set \"Balance\"=? where \"Account_ID\"=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, balance1);
				ps.setLong(2, acctid1);
				ps.executeUpdate();
				
				updateBal2 = balance2 + amount;
				acct2.setBalance(updateBal2);
				balance2 = acct2.getBalance();
				String sql2 = "update \"Accounts\" set \"Balance\"=? where \"Account_ID\"=?";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setDouble(1, balance2);
				ps2.setLong(2, acctid2);
				ps2.executeUpdate();
				
				System.out.println("Your new balance in "+acct1+" is "+balance1);
				System.out.println("Your new balance in "+acct2+" is "+balance2);
				LogThis.LogIt("info", acctid1 +" transferred $"+amount+" to "+acct2);
			}
		}catch(NegativeAmountException ex1) {
			ex1.getMessage();
			FileInputStream fis;
			try {
				CustomerDaoImpl cdi = new CustomerDaoImpl();
				fis = new FileInputStream("database.properties");
				Properties p = new Properties();
				p.load(fis);
				String auser = (String) p.get("auser");
				if(auser.equals(username)) {
					Menu.adminMenu(username);
				}else {
					Menu.customerMenu(cdi.getCustomerByUsername(username));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch(NotMoneyException ex2) {
			ex2.getMessage();
			FileInputStream fis;
			try {
				CustomerDaoImpl cdi = new CustomerDaoImpl();
				fis = new FileInputStream("database.properties");
				Properties p = new Properties();
				p.load(fis);
				String auser = (String) p.get("auser");
				if(auser.equals(username)) {
					Menu.adminMenu(username);
				}else {
					Menu.customerMenu(cdi.getCustomerByUsername(username));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deleteAccount(long acctid) throws SQLException {
		Connection conn = cf.getConnection();
		String sql1 = "select \"Balance\" from \"Accounts\" where \"Account_ID\"=?";
		PreparedStatement ps1 = conn.prepareStatement(sql1);
		ps1.setLong(1, acctid);
		ResultSet rs1 = ps1.executeQuery();
		int num = 0;
		if(rs1!=null) {
			while(rs1.next()) {
				num=rs1.getInt(1);
			}
		}
		if(num!=0) {
			System.out.println("This account has a balance. Please empty this account before proceeding.");
			
		}
		String sql2 = "delete from \"Accounts\" where \"Account_ID\"=?";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ps2.setLong(1,acctid);
		ps2.executeUpdate();		
		LogThis.LogIt("info", "Account "+acctid+" has been deleted.");
	}

}
