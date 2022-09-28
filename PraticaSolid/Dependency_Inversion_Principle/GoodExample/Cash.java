package Dependency_Inversion_Principle.GoodExample;

public class Cash implements PaymentMethod {
    
    public boolean pay(double amount) {
        // payment logic here
        System.out.println("Paid in Cash: " + amount);
        return true;
    }
    
}
