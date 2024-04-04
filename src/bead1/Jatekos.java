
package bead1;

import java.util.*;
/**
 *
 * @author Czeglédy Péter
 */
public abstract class Jatekos {
    protected String name;
    protected int money;
    protected Vector<Ingatlan> property;
    protected int position;
    
    public Jatekos(String name){
        this.name = name;
        this.money = 10000;
        this.position = 0;
        this.property = new Vector<>();
    }
    
    public void move(int amount){
        this.position += amount;
    }
    
    public Boolean isBankrupt(){
        if(money < 0){
            for(Ingatlan p : property){
                p.resetOwner();
            }
            this.property.clear();    
            return true;
        }
        return false;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPosition(){
        return position;
    }
    
    public int getMoney(){
        return money;
    }
            
    public void setMoney(int amount){
        this.money += amount;
    }

    public void setPosition(int position){
        this.position = position;
    }
    
    /**
     * Jelzi a mezőnek, hogy ráléptek,a többit pedig a mező saját trespass
     * függvénye bonyolítja le
     * @param mezo az a mező amire a játékos rálép
     */
    
    public abstract void trespassProperty(Mezo mezo);
    
    public abstract Boolean willingToBuy();
}
