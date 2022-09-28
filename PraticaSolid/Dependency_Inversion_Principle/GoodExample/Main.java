package Dependency_Inversion_Principle.GoodExample;

public class Main {
    public static void main(String[] args) {
        
        PaymentMethod cash = new Cash();
        PaymentMethod debitCard = new DebitCard();
        PaymentMethod creditCard = new CreditCard();

        Person p1 = new Person(cash);
        Person p2 = new Person(debitCard);
        Person p3 = new Person(creditCard);

        p1.pay(55.92);
        p2.pay(90.85);
        p3.pay(32.21);
    }
}
