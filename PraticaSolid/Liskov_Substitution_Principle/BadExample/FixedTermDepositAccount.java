package Liskov_Substitution_Principle.BadExample;

public class FixedTermDepositAccount extends Account {

    protected void deposit(double amount) {
        // deposit logic here
    }

    protected void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals are not supported by FixedTermDepositAccount!!");
    }

}
