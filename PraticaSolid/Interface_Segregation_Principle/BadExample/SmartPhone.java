package Interface_Segregation_Principle.BadExample;

public class SmartPhone implements Phone {

    @Override
    public void rings() {
        System.out.println("Smartphone is ringing!!!");
        
    }

    @Override
    public void call() {
        System.out.println("Smartphone is calling!!!");
        
    }

    @Override
    public void takePhoto() {
        System.out.println("Smartphone is taking a photo!!!");
    }
    
}
