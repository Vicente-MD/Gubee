package Interface_Segregation_Principle.GoodExample;

public class Smartphone implements SmartphoneInterface {
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

    @Override
    public void connectToInternet() {
        System.out.println("Smartphone is connecting to internet!!!");
    }

}
