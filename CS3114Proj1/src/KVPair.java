// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)


/**
 * KVPair class
 * 
 * Easily be able to connect a piece of data to a key for identification
 * 
 * @param <K>
 *            key
 * @param <E>
 *            value
 * @author Marlin Spears
 *
 * @version Sep 10, 2022
 *
 */
public class KVPair<K extends Comparable<K>, E>
    implements Comparable<KVPair<K, E>> {

    private K theKey;
    private E theVal;

    /**
     * Constructor for the KVPair class.
     * 
     * @param k
     *            theKey
     * @param v
     *            theVal
     */
    public KVPair(K k, E v) {
        theKey = k;
        theVal = v;
    }


    /**
     * Compare KVPairs
     * 
     * @param it
     *            KVPairs to compare
     * 
     * @return 0 if the KVPairs are equal
     */
    public int compareTo(KVPair<K, E> it) {
        return theKey.compareTo(it.key());
    }


    /**
     * Compare against a key
     * 
     * @param it
     *            key object
     * 
     * @return 0 if the keys are equal
     */
    public int compareTo(K it) {
        return theKey.compareTo(it);
    }


    /**
     * Getter method for the key
     * 
     * @return theKey
     */
    public K key() {
        return theKey;
    }


    /**
     * Getter method for the value
     * 
     * @return theVal
     */
    public E value() {
        return theVal;
    }


    /**
     * ToString method
     * 
     * @return theKey, theVal
     */
    public String toString() {
        if (theKey == null || theVal == null) {
            return null;
        }

        return theKey.toString() + ", " + theVal.toString();
    }

}
