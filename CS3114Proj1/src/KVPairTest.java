// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)


import student.TestCase;

/**
 * Test class for KVPair
 *
 * @author Marlin Spears
 *
 * @version Sep 12, 2022
 *
 */
public class KVPairTest extends TestCase {

    private KVPair<String, Integer> test;
    private KVPair<String, Integer> nullTest;
    private String string;

    /**
     * set up method that is run before each test method
     */
    @Override
    public void setUp() {
        string = new String("Test string");
        test = new KVPair<String, Integer>(string, 1);
        nullTest = new KVPair<String, Integer>(null, null);
    }


    /**
     * Tests the compareTo method for KVPairs given and key given
     * 
     * should give >0 if this string > other string
     * <0 if this string < other string
     * =0 if this string == other string
     * 
     * test for null throws exception
     */
    public void testCompareTo() {

        KVPair<String, Integer> neg = new KVPair<String, Integer>("Z", -1);
        KVPair<String, Integer> pos = new KVPair<String, Integer>("A", 1);
        KVPair<String, Integer> zero = new KVPair<String, Integer>(string, 0);
        // Given KVPair
        assertTrue(test.compareTo(neg) < 0);
        assertTrue(test.compareTo(pos) > 0);
        assertTrue(test.compareTo(zero) == 0);

        // Given key
        assertTrue(test.compareTo("Z") < 0);
        assertTrue(test.compareTo("A") > 0);
        assertTrue(test.compareTo(string) == 0);

        Exception exception = null;
        try {
            nullTest.compareTo(string);
        }
        catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);

        exception = null;
        try {
            nullTest.compareTo(test);
        }
        catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);

    }


    /**
     * Tests the key method
     * 
     * see if it is equal
     */
    public void testKey() {
        assertTrue(test.key().equals(string));
        assertNull(nullTest.key());
    }


    /**
     * Tests the value method
     * 
     * see if it is equal
     */
    public void testValue() {
        assertTrue(test.value().equals(1));
        assertNull(nullTest.value());

    }


    /**
     * Tests the toString method
     * 
     * test null cases
     * test if we get expected string
     */
    public void testToString() {
        KVPair<String, Integer> keyNull = new KVPair<String, Integer>(null, 0);
        KVPair<String, Integer> valNull = new KVPair<String, Integer>("Hi",
            null);

        assertNull(nullTest.toString());
        assertNull(keyNull.toString());
        assertNull(valNull.toString());

        assertTrue(test.toString().equals(string + ", " + 1));

    }

}
