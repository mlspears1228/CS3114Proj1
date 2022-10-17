// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)


/**
 * Rectangle class that holds data for the location and size
 * 
 * @author Marlin Spears
 *
 * @version Sep 9, 2022
 *
 */
public class Rectangle {

    private int x, y, width, height;

    /**
     * Constructor for Rectangle class
     * 
     * @param x
     *            X coordinates
     * @param y
     *            Y coordinates
     * @param width
     *            width of the rectangle
     * @param height
     *            height of the rectangle
     */
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }


    /**
     * Getter method for X coordinates
     * 
     * @return x
     */
    public int getX() {
        return this.x;
    }


    /**
     * Getter method for Y coordinates
     * 
     * @return y
     */
    public int getY() {
        return this.y;
    }


    /**
     * Getter method for width
     * 
     * @return width
     */
    public int getWidth() {
        return this.width;
    }


    /**
     * Getter method for height
     * 
     * @return height
     */
    public int getHeight() {
        return this.height;
    }


    /**
     * Intersection method for finding if another rectangle
     * intersects with this one
     * 
     * @param other
     *            other rectangle to compare with
     * @return true if they intersect
     */
    public boolean doIntersect(Rectangle other) {
        if (other == null)
            return false;
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        if (this.x == other.getX()) {
            if (other.getY() > this.y && this.height > (other.getY()
                - this.y)) {
                return true;
            }
            return (other.getY() < this.y && other.getHeight() > (this.y - other
                .getY()));
        }
        if (this.y == other.getY()) {
            if (other.getX() > this.x && this.width > (other.getX() - this.x)) {
                return true;
            }

            return (other.getX() < this.x && other.getWidth() > (this.x - other
                .getX()));
        }

        if (this.y < other.getY() && this.x < other.getX()) {
            return (this.width > (other.getX() - this.x) || this.height > (other
                .getY() - this.y));
        }
        if (this.y > other.getY() && this.x > other.getX()) {
            return (other.getWidth() > (this.x - other.getX()) || other
                .getHeight() > (this.y - other.getY()));
        }
        if (this.x < other.getX() && this.y > other.getY()) {
            return (this.width > (other.getX() - this.x) || other
                .getHeight() > (this.y - other.getY()));
        }
        if (this.x > other.getX() && this.y < other.getY()) {
            return (other.getWidth() > (this.x - other.getX())
                || this.height > (other.getY() - this.y));
        }
        return false;
    }


    /**
     * ToString method for Rectangle class
     * 
     * @return "x, y, width, height"
     */
    public String toString() {
        return x + ", " + y + ", " + width + ", " + height;
    }

}
