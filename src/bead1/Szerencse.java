
package bead1;

/**
 *
 * @author Czeglédy Péter
 */
public class Szerencse implements Mezo{
    private int gain;
    
    public Szerencse(int gain){
        this.gain = gain;        
    }
    
    @Override 
    public void trespass(Jatekos jatekos){
        jatekos.setMoney(gain);      
    }
}
