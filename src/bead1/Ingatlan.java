
package bead1;

/**
 *
 * @author Czeglédy Péter
 */
public class Ingatlan implements Mezo{
    
    private Jatekos owner;
    private Boolean hasHouse;
    private Boolean hasOwner;
    
    public Ingatlan(){
        this.hasHouse = false;
        this.hasOwner = false;
    }
    
    /**
     * Ez a függvény eldönti hogy a magát a telket veszi meg a játékos vagy 
     * csak házat vesz rá,majd lebonyolítja a tranzakciót
     * @param jatekos az a játékos aki éppen rálépett a mezőre
     */
    
    private void purchase(Jatekos jatekos){        
       if(!this.hasOwner){
           owner = jatekos;
           hasOwner = true;
           jatekos.setMoney(-1000);
           jatekos.property.add(this);
       }else{
           if(!this.hasHouse){
               jatekos.setMoney(-4000);
               hasHouse = true;
           }
       }
    }
    
       /**
     * Ez a függvény eldönti hogy mekkora bírságot kell kiszabni,majd lebonyolítja a tranzakciót
     * @param jatekos az a játékos aki éppen rálépett a mezőre
     */
    
    private void tax(Jatekos jatekos){
          if(!this.hasHouse){
               jatekos.setMoney(-2000);
               owner.setMoney(2000);
          }else{
              jatekos.setMoney(-500);
              owner.setMoney(500);
          }        
    }
    
    public Boolean getHasOwner(){
        return hasOwner;
    }
    
    public Jatekos getOwner(){
        return owner;
    }
    
    public void resetOwner(){
        hasOwner = false;
    }
    
    /**
    * Az alábbi függvény dönti el,hogy a mezőre rálépő játékos
    * bírságot kap,vagy megveszi a telket.Ennek meghatározására
    * megnézi hogy az ingatlannak van e már tulajdonosa és ha igen 
    * az aktuális játékos-e.Ebben az esetben lekéri a játékos stratégiáját
    * és annak megfelelően meghívja a purchase függvényt.Ha van tulajdonos és
    * az nem az aktuális játékos akkor bírságot szab ki a tax függvénnyel rá.
    * @param  jatekos  az a játékos aki éppen rálépett a mezőre
    */
    
    @Override
    public void trespass(Jatekos jatekos){
        if((!hasOwner) || owner.name.equals(jatekos.getName())){
            if(jatekos.willingToBuy()){
                purchase(jatekos);
            }        
        }else{
            tax(jatekos);
        }
    }
}
