package com.mybank2u.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mybank2u.model.Account;

public class AccountDAO {
	
	Connection connection=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement =null;
	
	public boolean checkAccount(String id) {
		Boolean found = false;
		try {
		String sql = "SELECT id FROM accounts where id='" + id +"'";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
		found = true;
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return found;
	}	
	
	public Account get(String id) {
		Account account = null;
		try {
			account = new Account();
		String sql = "SELECT "
				+ "id,"
				+ "name,"
				+ "balance,"
				+ "online_pin,"
				+ "email "
				+ "FROM accounts "
				+ "where id='" + id +"'";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
			account.setId(resultSet.getString("id"));
			account.setName(resultSet.getString("name"));
			account.setBalance(resultSet.getString("balance"));
			account.setOnline_pin(resultSet.getString("online_pin"));
			account.setEmail(resultSet.getString("email"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}	
	
	public Account accounts() {
		Account accounts = null;
		try {
			accounts = new Account();
		String sql = "SELECT * "
				+ "FROM accounts "
				+ "ORDER id";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
			accounts.setId(resultSet.getString("id"));
			accounts.setName(resultSet.getString("name"));
			accounts.setBalance(resultSet.getString("balance"));
			accounts.setEmail(resultSet.getString("email"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}	
	
	public boolean updateOtp(Account account) {		
		boolean flag = false;
		try {
			
			String sql = "UPDATE accounts SET "		
					+ "online_pin = round(dbms_random.value(1000000000000000, 9999999999999999)), "
					+ "otp = " + account.getOtp() + ", "
					+ "opt_expire = TO_DATE(" + account.getOpt_expire() + ", 'yyyy-mm-dd hh24:mi:ss') "
					+ "where id = '" + account.getId() + "'";
	
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql.replace("\"", "'"));
			
			//System.out.println(sql);
			
			int i = preparedStatement.executeUpdate();
	        if (i > 0) {
	        	flag = true;
	        } else {
	        	flag = false;
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
			return flag;
	}
	
	public boolean debitAccount(String acc, String amount) {		
		boolean flag = false;
		try {
			
			String sql = "UPDATE accounts SET "		
					+ "balance = balance-" + amount + " "
					+ "where id = '" + acc + "' "
					+ "and balance > " + amount + "+20";
	
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql.replace("\"", "'"));
			
			//System.out.println(sql);
			
			int i = preparedStatement.executeUpdate();
	        if (i > 0) {
	        	flag = true;
	        } else {
	        	flag = false;
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
			return flag;
	}

	
//	public Account getAccount(String id) {		
//		
//		Account account = null;
//		try {
//			account = new Account();
//		String sql = "SELECT "
//				+ "id,"
//				+ "name,"
//				+ "balance,"
//				+ "creation_date,"
//				+ "last_transaction_seq,"
//				+ "email,"
//				+ "online_pin,"
//				+ "otp,"
//				+ "opt_expire "
//				+ "FROM accounts "
//				+ "where id='" + id +"'";
//		connection = DBConnectionUtil.openConnection();
//		statement = connection.createStatement();
//		resultSet = statement.executeQuery(sql);
//		if (resultSet.next()) {
//			account.setOtp(resultSet.getString("otp"));
//			account.setOpt_expire(resultSet.getString("opt_expire"));
//		}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return account;
//	}	

	
	
}
