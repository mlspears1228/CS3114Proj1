// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)


import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * Class for reading the input file and instructing the program
 * 
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
     *            File name to be read
     */
    public Instruct(String fileName) {
        this.fileName = fileName;
        skipList = new SkipList<String, Rectangle>();
    }


    /**
     * Reads the data file line by line then passes to argument method
     * 
     * 
     */
    public void readFile() {
        File f = new File(fileName);

        Scanner file;
        try {
            file = new Scanner(f);
        }
        catch (FileNotFoundException e) {
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


    /**
     * Insert argument method inserts new rectangle
     * 
     * @param name
     *            Name of rectangle
     * @param x
     *            X coordinates of rectangle
     * @param y
     *            Y coordinates of rectangle
     * @param w
     *            width of rectangle
     * @param h
     *            height of rectangle
     */
    public void insert(String name, String x, String y, String w, String h) {
        int xCoord = Integer.parseInt(x);
        int yCoord = Integer.parseInt(y);
        int width = Integer.parseInt(w);
        int height = Integer.parseInt(h);
        Rectangle r = new Rectangle(xCoord, yCoord, width, height);
        KVPair<String, Rectangle> kvp = new KVPair<String, Rectangle>(name, r);

        if (!Character.isLetter(name.charAt(0))) {
            System.out.println("Rectangle rejected: (" + kvp.toString() + ")");
            return;
        }
        if (width <= 0 || height <= 0) {
            System.out.println("Rectangle rejected: (" + kvp.toString() + ")");
            return;
        }
        if (xCoord < 0 || yCoord < 0) {
            System.out.println("Rectangle rejected: (" + kvp.toString() + ")");
            return;
        }
        if (xCoord + width > 1024 || yCoord + height > 1024) {
            System.out.println("Rectangle rejected: (" + kvp.toString() + ")");
            return;
        }

        skipList.insert(kvp);
    }


    /**
     * Dump argument method prints every rectangle in list
     */
    public void dump() {

        System.out.println("SkipList dump:");
        for (int i = 0; i < skipList.getSize() + 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Node has depth ");
            KVPair<Integer, KVPair<String, Rectangle>> temp = skipList.get(i);
            sb.append(temp.key() + ", Value (");
            if (temp.value().toString() == null) {
                sb.append("null)");
            }
            else {
                sb.append(temp.value().toString() + ")");
            }
            System.out.println(sb);

        }
        System.out.println("SkipList size is: " + skipList.getSize());

    }


    /**
     * Remove-By-Name argument method removes a rectangle with matching name
     * 
     * @param name
     *            Name of rectangle to remove
     */
    public void removeName(String name) {
        KVPair<String, Rectangle> temp = new KVPair<String, Rectangle>(name,
            null);
        temp = skipList.remove(temp);
        if (temp == null) {
            System.out.println("Rectangle not removed: (" + name + ")");

        }
        else {
            System.out.println("Rectangle removed: (" + temp.toString() + ")");
        }
    }


    /**
     * Remove-By-Coords argument method removes a rectangle with matching coords
     * 
     * @param x
     *            X coordinates of rectangle to be removed
     * @param y
     *            Y coordinates of rectangle to be removed
     * @param w
     *            Width of rectangle to be removed
     * @param h
     *            Height of rectangle to be removed
     */
    public void removeCoords(String x, String y, String w, String h) {
        int xCoord = Integer.parseInt(x);
        int yCoord = Integer.parseInt(y);
        int width = Integer.parseInt(w);
        int height = Integer.parseInt(h);
        Rectangle r = new Rectangle(xCoord, yCoord, width, height);
        KVPair<String, Rectangle> temp = new KVPair<String, Rectangle>(null, r);
        temp = skipList.remove(temp);
        if (temp == null) {
            System.out.println("Rectangle not removed: (" + r.toString() + ")");
        }
        else {
            System.out.println("Rectangle removed: (" + temp.toString() + ")");
        }
    }


    /**
     * Region Search argument method returns all rectangles within a region
     * 
     * @param x
     *            X coordinates to search
     * @param y
     *            Y coordinates to search
     * @param w
     *            Width to search
     * @param h
     *            Height to search
     */
    public void regionSearch(String x, String y, String w, String h) {
        int xCoord = Integer.parseInt(x);
        int yCoord = Integer.parseInt(y);
        int width = Integer.parseInt(w);
        int height = Integer.parseInt(h);

        Rectangle r = new Rectangle(xCoord, yCoord, width, height);
        if (width <= 0 || height <= 0) {
            System.out.println("Rectangle rejected: (" + r.toString() + ")");
            return;
        }
        System.out.println("Rectangles intersecting region (" + r.toString()
            + "):");
        for (int i = 0; i < skipList.getSize() + 1; i++) {
            KVPair<String, Rectangle> temp = skipList.get(i).value(); // get
                                                                      // each
                                                                      // KVPair
            if (r.doIntersect(temp.value())) { // If these rectangles
                                               // intersect...
                System.out.println("(" + temp + ")"); // Print it out
            }
        }
    }


    /**
     * Intersections argument method prints all rectangles that intersect
     */
    public void intersections() {
        System.out.println("Intersections pairs:");
        for (int i = 0; i < skipList.getSize() + 1; i++) {
            KVPair<String, Rectangle> temp = skipList.get(i).value(); // get
                                                                      // each
                                                                      // KVPair
            for (int j = 0; j < skipList.getSize() + 1; j++) {
                KVPair<String, Rectangle> compare = skipList.get(j).value(); // get
                                                                             // each
                                                                             // KVPair
                                                                             // to
                                                                             // compare
                if (temp.value().doIntersect(compare.value())) {
                    System.out.println("(" + temp.toString() + " | " + compare
                        .toString() + ")");
                }
            }
        }
    }


    /**
     * Search argument method searches for a rectangle with the matching name
     * 
     * @param name
     *            name of rectangle to be searched for
     */
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
