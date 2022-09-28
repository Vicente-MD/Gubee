package Liskov_Substitution_Principle.BadExample;

public class BankingAppWithdrawalService {
    private Account account;

    public BankingAppWithdrawalService(Account account) {
        this.account = account;
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }
}
