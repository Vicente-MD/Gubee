package Dependency_Inversion_Principle.GoodExample;

public class DebitCard implements PaymentMethod {
    
    public boolean pay(double amount) {
        // payment logic here
        System.out.println("Paid from Debit Card: " + amount);
        return true;
    }
    
}
