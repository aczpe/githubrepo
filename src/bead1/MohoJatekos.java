
package bead1;

/**
 *
 * @author Czeglédy Péter
 */
public class MohoJatekos extends Jatekos{
    
    public MohoJatekos(String name){
        super(name);
    }
    
    @Override
    public void trespassProperty(Mezo mezo){
        mezo.trespass(this);
    }
    
    @Override
    public Boolean willingToBuy(){
        return true;
    }
    
}
