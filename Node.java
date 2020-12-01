// File Name: Node.java
// Author: Faith Reeves
// Program Purpose: used to create Node objects that store coordinates and a 
//                  direction variable. Node objects are pushed and popped from
//                  instances of FaithsStack

package amazenlife;

public class Node {

    // declare datafields for position and direction variables
    private int x, y;
    private int dir;

    // constructor for Node object
    public Node(int i, int j) {
        
        // set the x and y variables to the given values
        this.x = i;
        this.y = j;

        // default value for direction set to 0 (up) 
        this.dir = 0;
    }

    // method that returns the value of x
    public int getX() {
        return x;
    }

    // method to set the x variable to a new given value
    public void setX(int x) {
        this.x = x;
    }

    // method that returns the value of y
    public int getY() {
        return y;
    }

    // method to set the y variable to a new given value
    public void setY(int y) {
        this.y = y;
    }

    // method that returns the value of the direction variable
    public int getDir() {
        return dir;
    }

    // method to set the direction variable to a new given value
    public void setDir(int dir) {
        this.dir = dir;
    }
}
