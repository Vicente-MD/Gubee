package Liskov_Substitution_Principle.GoodExample;

public class BankingAppWithdrawalService {

    private WithdrawableAccount account;

    public BankingAppWithdrawalService(WithdrawableAccount account) {
        this.account = account;
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }
    
}