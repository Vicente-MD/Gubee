package Dependency_Inversion_Principle.GoodExample;

public class Person {
    
    private PaymentMethod paymentMethod;

    public void pay(double amount) {
        this.paymentMethod.pay(amount);
    }

    public Person(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
