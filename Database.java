package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Database {

		static Connection conn = null;

		public static Connection connect() {
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Chel\\Desktop\\BankUsers.sqlite");
				return conn;

			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,e);
				return null;
			}
		}






		        int AddAccount(String firstName, String lastName, String Username, String password, String Email, String phone, AccountTypes accountType, Double balance) {
		            int userId = -1;
		            int accountId = -1;
		            Connection connection = connect();
		            try {
		                connection.setAutoCommit(false);
		                //Add User
		                try (PreparedStatement addUser = connection.prepareStatement("INSERT INTO Users(FirstName,LastName,Username,Password,Email,Phone) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
		                	addUser.setString(1,firstName);
							addUser.setString(2,lastName);
							addUser.setString(3,Username);
							addUser.setString(4,password);
							addUser.setString(5,Email);
							addUser.setString(6,phone);
		                    addUser.executeUpdate();
		                    ResultSet addUserResults = addUser.getGeneratedKeys();
		                    if (addUserResults.next()) {
		                        userId = addUserResults.getInt(1);
		                    }
		                }
		                //Add Account
		                try (PreparedStatement addAccount = connection.prepareStatement("INSERT INTO Accounts(Type, Balance) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
		                    addAccount.setString(1, accountType.name());
		                    addAccount.setDouble(2, balance);
		                    addAccount.executeUpdate();
		                    ResultSet addAccountResults = addAccount.getGeneratedKeys();
		                    if (addAccountResults.next()) {
		                        accountId = addAccountResults.getInt(1);
		                    }
		                }
		                //Link User to Account
		                if (userId > 0 && accountId > 0) {
		                    try (PreparedStatement linkAccount = connection.prepareStatement("INSERT INTO Mappings(UserId, AccountId) VALUES(?,?)")) {
		                        linkAccount.setInt(1, userId);
		                        linkAccount.setInt(2, accountId);
		                        linkAccount.executeUpdate();
		                    }
		                    connection.commit();
		                } else {
		                    connection.rollback();
		                }
		                //Disconnect
		                connection.close();
		            } catch (SQLException ex) {
		                System.err.println("An error has occured." + ex.getMessage());
		            }
		            return accountId;
		        }

		        boolean UpdateAccount(int accountId, Double newBalance){
		            boolean success = false;
		            Connection connection = connect();
		            try {
		                try (PreparedStatement updateBalance = connection.prepareStatement(
		                        "UPDATE Accounts SET Balance = ? WHERE ID = ?")) {
		                    updateBalance.setDouble(1, newBalance);
		                    updateBalance.setInt(2, accountId);
		                    updateBalance.executeUpdate();
		                }
		                success = true;
		            } catch (SQLException ex) {
		                System.err.println("An error has occured." + ex.getMessage());
		            }
		            return success;
		        }



		            Customer GetAccount(int accountId) {
		                Customer customer = null;
		                Connection connection = connect();
		                try {
		                    try (PreparedStatement findUser = connection.prepareStatement(
		                            "SELECT FirstName,LastName,Username,Password,Email,Phone,Type,Balance "
		                            + "FROM Users a JOIN Mappings b on a.ID = b.UserId "
		                            + "JOIN Accounts c on b.AccountId = c.ID "
		                            + "WHERE c.ID = ?")) {
		                        findUser.setInt(1, accountId);
		                        ResultSet findUserResults = findUser.executeQuery();
		                        if (findUserResults.next()) {
		                            String firstName = findUserResults.getString("FirstName");
		                            String lastName = findUserResults.getString("LastName");
		                            String username = findUserResults.getString("Username");
		                            String password = findUserResults.getString("Password");
		                            String Email = findUserResults.getString("Email");
		                            String Phone = findUserResults.getString("Phone");
		                            String accountType = findUserResults.getString("Type");
		                            Double balance = findUserResults.getDouble("Balance");
		                            Account account;
		                            if (accountType.equals(AccountTypes.Checking.name())) {
		                                account = new Checking(accountId, balance);
		                            } else {
		                                account = new Savings(accountId, balance);
		                            }
		                            customer = new Customer(firstName, lastName, username, accountType, accountType, accountType, account);
		                        }
		                    }
		                } catch (SQLException ex) {
		                    System.err.println("An error has occured." + ex.getMessage());
		                }
		                return customer;
		            }

		            ArrayList<Customer> GetUserAccounts() {
		                ArrayList<Customer> customers = new ArrayList<Customer>();
		                Connection connection = connect();
		                try {
		                    try (PreparedStatement findUser = connection.prepareStatement(
		                            "SELECT AccountId,FirstName,LastName,Username,Password,Email,Phone,Type,Balance "
		                            + "FROM Users a JOIN Mappings b on a.ID = b.UserId "
		                            + "JOIN Accounts c on b.AccountId = c.ID")) {
		                        ResultSet findUserResults = findUser.executeQuery();
		                        while (findUserResults.next()) {
		                        	String firstName = findUserResults.getString("FirstName");
				                    String lastName = findUserResults.getString("LastName");
				                    String username = findUserResults.getString("Username");
				                    String password = findUserResults.getString("Password");
				                    String email = findUserResults.getString("Email");
				                    String phone = findUserResults.getString("Phone");
				                    String accountType = findUserResults.getString("Type");
				                    Double balance = findUserResults.getDouble("Balance");
				                    int accountId = findUserResults.getInt("AccountId");
				                    Account account;
				                    if (accountType.equals(AccountTypes.Checking.name())) {
				                        account = new Checking(accountId, balance);
				                    } else {
				                        account = new Savings(accountId, balance);
				                    }
				                    Customer customer = new Customer(firstName, lastName, username, password, email,phone, account);
				                    if(customer.getPassword().equals(LoginMenu.pw) && customer.getUsername().equals(LoginMenu.us)) {
				                    customers.add(customer);
				                    }
				                }
		                    }
		                } catch (SQLException ex) {
		                    System.err.println("An error has occured." + ex.getMessage());
		                }
		                return customers;
		            }
		        }






