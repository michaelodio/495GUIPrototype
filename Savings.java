package bank;


public class Savings extends Account{

    Savings(int accountNumber, double initialDeposit){
        super(accountNumber);
        this.setBalance(initialDeposit);
    }

    @Override
    public AccountTypes getAccountType() {
        return AccountTypes.Savings;
    }
}