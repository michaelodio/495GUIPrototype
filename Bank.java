package bank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Bank implements Serializable {

    private Database database = new Database();

    Customer openAccount(String firstName, String lastName, String Username, String password, String Email, String phone, AccountTypes accountType, Double balance) {
        int accountId = database.AddAccount(firstName, lastName, Username, password, Email, phone, accountType, balance);
        Customer customer = database.GetAccount(accountId);
        return customer;
    }

    Customer getCustomer(int accountId) {
        return database.GetAccount(accountId);
    }

    ArrayList<Customer> getCustomers() {
        return database.GetUserAccounts();
    }




    public void withdraw(int accountId, double amount) throws Exception {
        Customer customer = getCustomer(accountId);

        if (amount  > customer.getAccount().getBalance()) {
            throw new Exception();
        }
        double newBalance = customer.getAccount().getBalance() - amount;
        database.UpdateAccount(accountId, newBalance);
    }

    void deposit(int accountId, double amount) throws Exception {
        Customer customer = getCustomer(accountId);
        if (amount <= 0) {
            throw new Exception();
        }
        double interest = checkInterest(customer.getAccount().getBalance(), amount);
        double amountToDeposit = amount + (amount * interest);
        database.UpdateAccount(accountId, customer.getAccount().getBalance() + amountToDeposit);
    }

    public double checkInterest(double balance, double amount) {
        double interest = 0;
        if (balance + amount > 10000) {
            interest = 0.05;
        } else {
            interest = 0.02;
        }
        return interest;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
