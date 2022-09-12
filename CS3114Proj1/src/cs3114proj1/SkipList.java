// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)
package cs3114proj1;

import java.lang.reflect.Array;
import java.util.Random;
import student.TestableRandom;

/**
 * @author Marlin Spears
 *
 * @version Sep 10, 2022
 *
 */
public class SkipList<K extends Comparable<K>, E> {
    private static class SkipNode<K extends Comparable<K>, E> {
        private KVPair<K, E> rec;
        private SkipNode<K, E>[] forward;
        
        @SuppressWarnings("unchecked")
        public SkipNode(K key, E elem, int level) {
            rec = new KVPair<K, E>(key, elem);
            forward = new SkipNode[level + 1];
            for (int i = 0; i < level; i++) {
                forward[i] = null;
            }
        }
            
        public E element() {
            return rec.value();
        }
        
        public K key() {
            return rec.key();
        }
        
        public String toString() {
            return rec.toString();
        }
        
    }
    
    
    
    private SkipNode<K, E> head;
    private int level;
    private int size;
    static private Random ran;
    
    public SkipList() {
        head = new SkipNode<K, E>(null, null, 0);
        level = -1;
        size = 0;
        ran = new TestableRandom();
    }
    
    public int getSize() {
        return size;
    }
    
    public KVPair<Integer, String> get(int index) {
        if (index < 0 || size <= index) {
            return null;
        }
        SkipNode<K, E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.forward[0];
        }
        return new KVPair<Integer, String>(current.forward.length, current.toString());
        
    }
    
    
    
    public int randomLevel() {
        int lev;
        for (lev = 0; ran.nextBoolean(); lev++);
        return lev;
    }
    
    
    public boolean insert(KVPair<K, E> it) {
        int newLevel = randomLevel();
        Comparable<K> k = it.key();
        if (level < newLevel) {
            adjustHead(newLevel);
        }
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode[])Array.newInstance(SkipList.SkipNode.class, level+1);
        SkipNode<K, E> x = head;    // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (k.compareTo(x.forward[i].key()) > 0)) {
                x = x.forward[i];
            }
            update[i] = x;              // Track end at level i
        }
        x = new SkipNode<K, E>(it.key(), it.value(), newLevel);
        for (int i = 0; i <= newLevel; i++) {       // Splice into list
            x.forward[i] = update[i].forward[i];    // Who x points to
            update[i].forward[i] = x;               // Who points to x
        }
        size++;                         // Increment dictionary size
        return true;
    }
    
    public KVPair<K, E> remove(KVPair<K, E> it) {
        Comparable<K> k = it.key();
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode[])Array.newInstance(SkipList.SkipNode.class, level+1);
        SkipNode<K, E> x = head;    // Start at header node
        for (int i = level; i >= 0; i--) { // Find remove position
            while ((x.forward[i] != null) && (k.compareTo(x.forward[i].key()) > 0)) {
                x = x.forward[i];
            }
            update[i] = x;              // Track end at level i
        }
        x = x.forward[0];               // Move to actual record
        if ((x != null) && (k.compareTo(x.key()) == 0)) {
            for (int i = 0; i <= level; i++) {   
                if (update[i].forward[i] == x) {    // If its pointing to x...
                    update[i].forward[i] = x.forward[i]; // Point to what x was pointing to
                }
            }
            adjustLevel();
            size--;                         // Decrement dictionary size
            return new KVPair<K, E>(x.key(), x.element());
        }
        else {return null;}
    }
    
    public KVPair<K, E> removeByValue(KVPair<K, E> it) {
        E e = it.value();
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode[])Array.newInstance(SkipList.SkipNode.class, level+1);
        SkipNode<K, E> x = head;    // Start at header node
        while ((x.forward[0] != null) && !(e.equals(x.forward[0].element()))) {
            for (int i = level; i>= 0; i--) {
                if (e.equals(x.forward[i].element())) {
                    update[i] = x;
                }
            }
            x = x.forward[0];
        }
        update[0] = x;
        
        x = x.forward[0];               // Move to actual record
        if ((x != null) && (e.equals(x.element()))) {
            for (int i = 0; i <= level; i++) {   
                if (update[i].forward[i] == x) {    // If its pointing to x...
                    update[i].forward[i] = x.forward[i]; // Point to what x was pointing to
                }
            }
            adjustLevel();
            size--;                         // Decrement dictionary size
            return new KVPair<K, E>(x.key(), x.element());
        }
        else {return null;}
    }
    
    @SuppressWarnings("unchecked")
    public KVPair<K, E>[] search(Comparable<K> key) {
        SkipNode<K, E> x = head;                // Dummy header node
        for (int i = level; i >= 0; i--) {      // For each level...
            while ((x.forward[i] != null) && 
                            (key.compareTo(x.forward[i].key()) > 0)) { // go forward
                x = x.forward[i];               // Go one last step
            }
        }
        x = x.forward[0];   // Move to actual record, if it exists
        if ((x != null) && (key.compareTo(x.key()) == 0)) {
            KVPair<K, E>[] output = new KVPair[size];
            for (int i = 0; key.compareTo(x.key()) == 0; i++) {
                output[i] = new KVPair<K, E>(x.key(), x.element());
                x = x.forward[0];
            }
            return output;
        }
        else {return null;}
    }
    
    
    
    private void adjustHead(int newLevel) {
        SkipNode<K, E> temp = head;
        head = new SkipNode<K, E>(null, null, newLevel);
        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];
        }
        level = newLevel;
    }
    
    private void adjustLevel() {
        while ((level > 1) && head.forward[level] == null) {
            level--;
        }
    }
}
    


