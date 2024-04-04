
package bead1;

/**
 *
 * @author Czeglédy Péter
 */
public class Szolgaltatas implements Mezo{
    
    private int loss;
    
    public Szolgaltatas(int loss){
        this.loss = loss;
    }
    
    @Override 
    public void trespass(Jatekos jatekos){
        jatekos.setMoney(-loss);     
    }
    
    
}
