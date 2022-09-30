package Liskov_Substitution_Principle.BadExample;

public class FixedTermDepositAccount extends Account {

    public void deposit(double amount) {
        // deposit logic here
    }

    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals are not supported by FixedTermDepositAccount!!");
    }

}
