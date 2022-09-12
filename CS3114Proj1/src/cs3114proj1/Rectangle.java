// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Marlin Spears (mlspears1228)
package cs3114proj1;

/**
 * @author Marlin Spears
 *
 * @version Sep 9, 2022
 *
 */
public class Rectangle {
    
    private int x, y, width, height;
    
    public Rectangle(int x, int y, int width, int height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
        
    }
    
    public int getX() { return this.x; }
    
    public int getY() { return this.y; }
    
    public int getWidth() { return this.width; }
    
    public int getHeight() { return this.height; }
    
    public boolean doIntersect(Rectangle other) {
        if (other == null) return false;
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        if (this.x == other.getX()) {
            if (other.getY() > this.y && this.height > (other.getY() - this.y)) {
                return true;
            }
            if (other.getY() < this.y && other.getHeight() > (this.y - other.getY())) {
                return true;
            }
            return false;
        }
        if (this.y == other.getY() && this.width > other.getX()) {
            if (other.getX() > this.x && this.width > (other.getX() - this.x)) {
                return true;
            }
            if (other.getY() < this.y && other.getWidth() > (this.y - other.getY())) {
                return true;
            }
            return false;
        }
        
        if (this.y < other.getY() && this.x < other.getX()) {
            if (this.width > (other.getX() - this.x) || this.height > (other.getY() - this.y)) {
                return true;
            }
            return false;
        }
        if (this.y > other.getY() && this.x > other.getX()) {
            if (other.getWidth() > (this.x - other.getX()) || other.getHeight() > (this.y - other.getY())) {
                return true;
            }
            return false;
        }
        if (this.x < other.getX() && this.y > other.getY()) {
            if (this.width > (other.getX() - this.x) || other.getHeight() > (this.y - other.getY())) {
                return true;
            }
            return false;
        }
        if (this.x > other.getX() && this.y < other.getY()) {
            if(other.getWidth() > (this.x - other.getX()) || this.height > (other.getY() - this.y)) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    
    public String toString() {
        return x + ", " + y + ", " + width + ", " + height;
    }
    

}
