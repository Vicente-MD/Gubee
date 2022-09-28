package Dependency_Inversion_Principle.BadExample;

public class Main {
    public static void main(String[] args) {
        DebitCard debitCard = new DebitCard();
        Person person = new Person(debitCard);
        person.debitCard.pay(10);
    }
}
