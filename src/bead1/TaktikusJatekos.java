
package bead1;

/**
 *
 * @author Czeglédy Péter
 */
public class TaktikusJatekos extends Jatekos{
    
    private int counter = 1;
    
    public TaktikusJatekos(String name){
    super(name);
    }
    
    @Override
    public void trespassProperty(Mezo mezo){
        mezo.trespass(this);
    }

    @Override 
    public Boolean willingToBuy(){
        ++counter;
        return (counter % 2 == 0);
    }
}
