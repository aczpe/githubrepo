
package bead1;

/**
 *
 * @author Czeglédy Péter
 */
public class OvatosJatekos extends Jatekos{
    
    private int treshold;
    
    public OvatosJatekos(String name){
        super(name);
        treshold = 5000;
    }
    
    public void setTreshold(int treshold){
        this.treshold = treshold;
    }
    
    @Override
    public void trespassProperty(Mezo mezo){
        mezo.trespass(this);
    }
    
    @Override 
    public Boolean willingToBuy(){
        return (money > treshold);
    }
    
    @Override
    public void setPosition(int position){
        this.position = position;
        treshold = money/2;
    }
   
}
