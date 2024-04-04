
package bead1;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Czeglédy Péter
 */
public class Main {

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {
        
        System.out.println("Enter the name of the file");
        Scanner sc = new Scanner(System.in);
 
        String fname = sc.nextLine();
        sc.close();

        Capitaly capitaly = new Capitaly();
        try {
            capitaly.readFromFile(fname);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input!ime");
            System.exit(-1);
        } catch (NoSuchElementException ex) {
            System.out.println("Missing element in input file!");
            System.exit(-1);
        }
        capitaly.startGame();
    }

}
