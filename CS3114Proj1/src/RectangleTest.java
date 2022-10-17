// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)


import student.TestCase;

/**
 * Test class for Rectangle class
 * 
 * @author Marlin Spears
 *
 * @version Sep 12, 2022
 *
 */
public class RectangleTest extends TestCase {

    private Rectangle nullRect;
    private Rectangle test;
    private Rectangle sameCoords;

    /**
     * set up method that is run before each test method
     */
    @Override
    public void setUp() {
        test = new Rectangle(5, 5, 5, 5);
        nullRect = null;
        sameCoords = new Rectangle(5, 5, 3, 3);

    }


    /**
     * Tests the getX method
     */
    public void testGetX() {
        assertTrue(test.getX() == 5);
        Exception exception = null;
        try {
            nullRect.getX();
        }
        catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);
    }


    /**
     * Tests the getY method
     */
    public void testGetY() {
        assertTrue(test.getY() == 5);
        Exception exception = null;
        try {
            nullRect.getY();
        }
        catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);
    }


    /**
     * Tests the getWidth method
     */
    public void testGetWidth() {
        assertTrue(test.getWidth() == 5);
        Exception exception = null;
        try {
            nullRect.getWidth();
        }
        catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);
    }


    /**
     * Tests the getHeight method
     */
    public void testGetHeight() {
        assertTrue(test.getHeight() == 5);
        Exception exception = null;
        try {
            nullRect.getHeight();
        }
        catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);
    }


    /**
     * Tests the doIntersect method
     * 
     * Only true if the rectangles intersect (Does not include touching)
     */
    public void testDoIntersect() {

        Exception exception = null;
        try {
            nullRect.doIntersect(test);
        }
        catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);

        // test for null
        // always false
        assertFalse(test.doIntersect(nullRect));

        // test for same coordinates
        // always true
        assertTrue(test.doIntersect(sameCoords));

        // test for same x
        /**
         * First if:
         * 
         * True True = True
         * True False = False
         * False True = False
         */
        Rectangle sameX1TT = new Rectangle(5, 6, 5, 5);
        Rectangle sameX1TF = new Rectangle(5, 10, 5, 5);
        Rectangle sameX1FT = new Rectangle(5, 4, 1, 1);

        assertTrue(test.doIntersect(sameX1TT));
        assertFalse(test.doIntersect(sameX1TF));
        assertFalse(test.doIntersect(sameX1FT));

        /**
         * Second if:
         * 
         * True True = True
         * True False = False
         * False True = False
         */
        Rectangle sameX2TT = new Rectangle(5, 4, 5, 5);
// Rectangle sameX2TF = new Rectangle(5, 4, 1, 1); Repeated
// Rectangle sameX2FT = new Rectangle(5, 10, 5, 5); Repeated

        assertTrue(test.doIntersect(sameX2TT));
// assertFalse(test.doIntersect(sameX2TF)); Repeated
// assertFalse(test.doIntersect(sameX2FT)); Repeated

        // test for same y
        /**
         * First if:
         * 
         * True True = True
         * True False = False
         * False True = False
         */
        Rectangle sameY1TT = new Rectangle(6, 5, 5, 5);
        Rectangle sameY1TF = new Rectangle(10, 5, 5, 5);
        Rectangle sameY1FT = new Rectangle(4, 5, 1, 1);

        assertTrue(test.doIntersect(sameY1TT));
        assertFalse(test.doIntersect(sameY1TF));
        assertFalse(test.doIntersect(sameY1FT));

        /**
         * Second if:
         * 
         * True True = True
         * True False = False
         * False True = False
         */
        Rectangle sameY2TT = new Rectangle(4, 5, 5, 5);
// Rectangle sameY2TF = new Rectangle(4, 5, 1, 1); Repeated
// Rectangle sameY2FT = new Rectangle(10, 5, 5, 5); Repeated

        assertTrue(test.doIntersect(sameY2TT));
// assertFalse(test.doIntersect(sameY2TF)); Repeated
// assertFalse(test.doIntersect(sameY2FT)); Repeated

        // test for less x and less y
        /**
         * Possibilities:
         * 
         * True True = True
         * True False = False
         * False True = False
         */
// Rectangle lXLYPTT = new Rectangle(6, 6, ); not needed
// Rectangle lXLYPTF; not needed essentially less x and greater y
// Rectangle lXLYPFT; not needed essentially greater x and less y

        /**
         * If statement:
         * 
         * True True = true
         * True False = true
         * False True = true
         * False False = false
         */
        Rectangle lXLY1TT = new Rectangle(6, 6, 5, 5);
        Rectangle lXLY1TF = new Rectangle(6, 10, 5, 5);
        Rectangle lXLY1FT = new Rectangle(10, 6, 5, 5);
        Rectangle lXLYFF = new Rectangle(10, 10, 5, 5);

        assertTrue(test.doIntersect(lXLY1TT));
        assertTrue(test.doIntersect(lXLY1TF));
        assertTrue(test.doIntersect(lXLY1FT));
        assertFalse(test.doIntersect(lXLYFF));

        // test for greater x and greater y
        /**
         * Possibilities:
         * 
         * True True = True
         * True False = False
         * False True = False
         */
// Rectangle gXGYPTT;
// Rectangle gXGYPTF;
// Rectangle gXGYPFT;

        /**
         * If statement:
         * 
         * True True = true
         * True False = true
         * False True = true
         * False False = false
         */
        Rectangle gXGY1TT = new Rectangle(4, 4, 5, 5);
        Rectangle gXGY1TF = new Rectangle(4, 1, 5, 4);
        Rectangle gXGY1FT = new Rectangle(1, 4, 4, 5);
        Rectangle gXGYFF = new Rectangle(1, 1, 4, 4);

        assertTrue(test.doIntersect(gXGY1TT));
        assertTrue(test.doIntersect(gXGY1TF));
        assertTrue(test.doIntersect(gXGY1FT));
        assertFalse(test.doIntersect(gXGYFF));

        // test for less x and greater y
        /**
         * Possibilities:
         * 
         * True True = True
         * True False = False
         * False True = False
         */
// Rectangle lxGYPTT;
// Rectangle lxGYPTF;
// Rectangle lxGYPFT;

        /**
         * If statement:
         * 
         * True True = true
         * True False = true
         * False True = true
         * False False = false
         */
        Rectangle lxGY1TT = new Rectangle(6, 4, 5, 5);
        Rectangle lxGY1TF = new Rectangle(6, 1, 5, 4);
        Rectangle lxGY1FT = new Rectangle(10, 4, 5, 5);
        Rectangle lxGYFF = new Rectangle(10, 1, 5, 4);

        assertTrue(test.doIntersect(lxGY1TT));
        assertTrue(test.doIntersect(lxGY1TF));
        assertTrue(test.doIntersect(lxGY1FT));
        assertFalse(test.doIntersect(lxGYFF));

        // test for greater x and less y
        /**
         * Possibilities:
         * 
         * True True = True
         * True False = False
         * False True = False
         */
// Rectangle gXLYPTT;
// Rectangle gXLYPTF;
// Rectangle gXLYPFT;

        /**
         * If statement:
         * 
         * True True = true
         * True False = true
         * False True = true
         * False False = false
         */
        Rectangle gXLY1TT = new Rectangle(4, 6, 5, 5);
        Rectangle gXLY1TF = new Rectangle(4, 10, 5, 5);
        Rectangle gXLY1FT = new Rectangle(1, 6, 4, 5);
        Rectangle gXLYFF = new Rectangle(1, 10, 4, 5);

        assertTrue(test.doIntersect(gXLY1TT));
        assertTrue(test.doIntersect(gXLY1TF));
        assertTrue(test.doIntersect(gXLY1FT));
        assertFalse(test.doIntersect(gXLYFF));
    }


    /**
     * Tests the toString method
     */
    public void testToString() {
        Exception exception = null;
        try {
            nullRect.toString();
        }
        catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof NullPointerException);

        assertTrue(test.toString().equals(5 + ", " + 5 + ", " + 5 + ", " + 5));
    }

}
