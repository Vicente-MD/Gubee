package Dependency_Inversion_Principle.BadExample;

public class DebitCard {
    public boolean pay(double amount) {
        // payment logic here
        System.out.println("Paid: " + amount);
        return true;
    }
}
