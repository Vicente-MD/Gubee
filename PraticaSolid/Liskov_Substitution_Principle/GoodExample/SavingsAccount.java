package Liskov_Substitution_Principle.GoodExample;

public class SavingsAccount extends WithdrawableAccount {

    protected void deposit(double amount) {
        // deposit logic here
    }

    protected void withdraw(double amount) {
        // withdraw logic here
    }
}