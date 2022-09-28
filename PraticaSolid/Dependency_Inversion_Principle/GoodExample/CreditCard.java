package Dependency_Inversion_Principle.GoodExample;

public class CreditCard implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        // payment logic here
        System.out.println("Paid from Credit Card: " + amount);
        return true;
    }
    
}
