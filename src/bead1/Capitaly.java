
package bead1;
import java.util.*;
import java.io.*;

/**
 *
 * @author Czeglédy Péter
 */

public class Capitaly {                    
    private static final Boolean MANUALDICEMODE = false;
    private Vector<Integer> dobasok;
    private static int diceindex;
    private final ArrayList<Mezo> palya;
    private int palyameret;
    public Vector<Jatekos> jatekosok;
    
    public Capitaly(){
        palya = new ArrayList<>();
        jatekosok = new Vector<>();
    }
    
    public Boolean gameOver(){
        return (jatekosok.size() == 1);
    }
    
    private int throwDice(){
        Random rn = new Random();
        int res = rn.nextInt(6) + 1;
        return res;
    }
    
    /**
     * A fájlból való beolvasás az alábbi sorrendben:
     * 1. a mezők száma
     * 2. mezők beolvasása és eltárolása
     * 3. a játékosok száma
     * 4. játékosok beolvasása és eltárolása
     * ( 5. előre megadott kockadobások esetén azok beolvasása és eltárolása ) 
     * @param fname a beolvasandó fájl neve
     */
    
    public void readFromFile(String fname) throws FileNotFoundException, InvalidInputException, InputMismatchException, NoSuchElementException{
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fname)));
        int palyameret = sc.nextInt();
        this.palyameret = palyameret;
        for(int i = 0; i < palyameret;i++){
            Mezo mezo;
            switch (sc.next()) {
                case "I":
                    mezo = new Ingatlan();
                    break;
                case "SZOL":
                    mezo = new Szolgaltatas(sc.nextInt());
                    break;
                case "SZER":
                    mezo = new Szerencse(sc.nextInt());
                    break;
                default:
                    throw new InvalidInputException();
            }
            palya.add(mezo);
        }
        int jatekosnum = sc.nextInt();
        for(int i = 0; i < jatekosnum;i++){
            Jatekos jatekos;
            String nev = sc.next();
            switch (sc.next()) {
                case "M":
                    jatekos = new MohoJatekos(nev);
                    break;
                case "O":
                    jatekos = new OvatosJatekos(nev);
                    break;
                case "T":
                    jatekos = new TaktikusJatekos(nev);
                    break;
                default:
                    throw new InvalidInputException();
            }
            jatekosok.add(jatekos);
        }
        
        if(MANUALDICEMODE){
            dobasok = new Vector<>();
            diceindex = 0;
            while(sc.hasNext()){
                dobasok.add(sc.nextInt());
            }
            if(dobasok.size() == 0) throw new InvalidInputException();
        }
    }
    
    /**
     * Ez a függvény bonyolítja le a játékmenetet.
     * Játékosonként dob a kockával és lép, utána ellenőrzi,
     * hogy van e már nyertes.Ha igen, kihirdeti és véget ér a játék
    */
    
    public void startGame(){
        int dice;
        while(!gameOver()){
            for(int i = 0; i < jatekosok.size();i++){
                if(MANUALDICEMODE){
                    if(diceindex == dobasok.size()){
                        diceindex = 0;
                    }
                        dice = dobasok.get(diceindex);
                        diceindex++;
                }else{
                    dice = throwDice();   
                }
                Jatekos currentPlayer = jatekosok.get(i);
                
                if( (currentPlayer.getPosition() + dice ) < palyameret){
                    currentPlayer.move(dice);
                }else{
                    int pos = (palyameret - (currentPlayer.getPosition() + dice)) * (-1);
                    currentPlayer.setPosition(pos);
                }

                currentPlayer.trespassProperty(palya.get(currentPlayer.getPosition()));
                System.out.println(currentPlayer.getName() + " jelenlegi vagyona: " + currentPlayer.getMoney());
                if(currentPlayer.isBankrupt()){
                    jatekosok.remove(i);
                    System.out.println(currentPlayer.name + " csodbe ment");
                    if(gameOver()) break;
                }
            }
        }
        System.out.println(jatekosok.get(0).name + " nyert");
    }
    
}
