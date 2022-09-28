package Liskov_Substitution_Principle.GoodExample;

public abstract class WithdrawableAccount extends Account {
    protected abstract void withdraw(double amount);
    @Override
    protected abstract void deposit(double amount);
}
