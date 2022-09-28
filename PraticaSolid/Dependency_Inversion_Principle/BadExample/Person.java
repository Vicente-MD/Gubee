package Dependency_Inversion_Principle.BadExample;

public class Person {
    
    public DebitCard debitCard;

    public Person(DebitCard debitCard){
        this.debitCard = debitCard;
    }
}
