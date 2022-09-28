package Interface_Segregation_Principle.BadExample;

public class LandlinePhone implements Phone{

    @Override
    public void rings() {
        System.out.println("LandlinePhone is ringing!!!");
        
    }

    @Override
    public void call() {
        System.out.println("LandlinePhone is calling!!!");
        
    }

    @Override
    public void takePhoto(){
        throw new UnsupportedOperationException("This phone does not have cammera!!!");
    }
    
    
}
