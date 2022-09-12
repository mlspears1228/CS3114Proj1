// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)
package cs3114proj1;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * @author Marlin Spears
 *
 * @version 2022.09.09
 * 
 */
public class Instruct {
    
    private String fileName;
    private SkipList<String, Rectangle> skipList;
    
    /**
     * New Instruct object
     * 
     * @param fileName
     *          File name to be read
     */
    public Instruct(String fileName) {
        this.fileName = fileName;
        skipList = new SkipList<String, Rectangle>();
    }
    
    /**
     * Reads the data file line by line then passes to instructGeneral method
     * 
     * @throws FileNotFoundException
     */
    public void readFile() {
        File f = new File(fileName);
        
        Scanner file;
        try {
            file = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found");
            e.printStackTrace();
            return;
        }
        String line;
        while (file.hasNextLine()) {
            line = file.nextLine();
            String[] instr = line.trim().split("\\s+");
            if (instr[0].compareTo("insert") == 0) {
                insert(instr[1], instr[2], instr[3], instr[4], instr[5]);
                continue;
            }
            if (instr[0].compareTo("dump") == 0) {
                dump();
                continue;
            }
            if (instr[0].compareTo("remove") == 0) {
                if (instr.length == 2) {
                    removeName(instr[1]);
                }
                else {
                    removeCoords(instr[1], instr[2], instr[3], instr[4]);
                }
                continue;
            }
            if (instr[0].compareTo("regionSearch") == 0) {
                regionSearch(instr[1], instr[2], instr[3], instr[4]);
                continue;
            }
            if (instr[0].compareTo("intersections") == 0) {
                intersections();
                continue;
            }
            if (instr[0].compareTo("search") == 0) {
                search(instr[1]);
                continue;
            }
            
        }
        file.close();
        
    }
    
    public void insert(String name, String x, String y, String w, String h) {
        
    }
    
    public void dump() {
        
        System.out.println("SkipList dump:");
        for (int i = 0; i < skipList.getSize() + 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Node has depth ");
            KVPair<Integer, String> temp = skipList.get(i);
            sb.append(temp.key() + ", Value (");
            if (temp.value() == null) {
                sb.append("null)");
            }
            else {
                sb.append(temp.value() + ")");
            }
            System.out.println(sb);
            
        }
        System.out.println("SkipList size is: " + skipList.getSize());
        
    }
    
    public void removeName(String name) {
        
    }
    
    public void removeCoords(String x, String y, String w, String h) {
        
    }
    
    public void regionSearch(String x, String y, String w, String h) {
        
    }
    
    public void intersections() {
        
    }
    
    public void search(String name) {
        KVPair<String, Rectangle>[] output = skipList.search(name);
        if (output == null) {
            System.out.println("Rectangle not found: (" + name + ")");
        }
        else {
            System.out.println("Rectangles found:");
            for (int i = 0; output[i] != null; i++) {
                System.out.println("(" + output[i].toString() + ")");
            }  
        }
    }

}
