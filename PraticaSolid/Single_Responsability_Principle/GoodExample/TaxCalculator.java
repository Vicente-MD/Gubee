package Single_Responsability_Principle.GoodExample;
public class TaxCalculator {
    public double calculateTax(double taxRate, double price) {
        return price * taxRate;
    }
}
