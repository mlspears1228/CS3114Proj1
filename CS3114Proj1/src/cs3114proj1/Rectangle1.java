// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)
package cs3114proj1;

import java.io.FileNotFoundException;

/**
 * Project Runner class
 * 
 * @author Marlin Spears
 * 
 * @version 2022.09.09
 */
public class Rectangle1 {
    
    public static void main(String[] argv) throws FileNotFoundException {
        
        if (argv.length == 1) {
            Instruct instr = new Instruct(argv[0]);
            instr.readFile();
        }
        
        else {
            
        }
    }
    

}
