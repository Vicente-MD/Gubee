package Interface_Segregation_Principle.GoodExample;

public class LandlinePhoneBlack implements LandlinePhone {
    @Override
    public void rings() {
        System.out.println("LandlinePhone is ringing!!!");
        
    }

    @Override
    public void call() {
        System.out.println("LandlinePhone is calling!!!");
        
    }

    @Override
    public void sendFax(){
        System.out.println("LandlinePhone is sending fax!!!");
    }
}
