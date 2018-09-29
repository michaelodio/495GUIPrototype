package bank;


public class Checking extends Account{

    Checking(int accountNumber, double initialDeposit){
        super(accountNumber);
        this.setBalance(initialDeposit);
    }

    @Override
    public AccountTypes getAccountType() {
        return AccountTypes.Checking;
    }
}