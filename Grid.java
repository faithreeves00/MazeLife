// File/Class Name: Grid.java
// Author: Faith Reeves
// Class Purpose: To be used with "AmazenLife.java" to create grids, 
//                print grids, set cells to dead or alive, count a cell's
//                alive neighbors, return the status of a cell, and simulate
//                the grid's next generation. Also used to place the explorer,
//                gold, crumbs, and Xs in a maze (the grid's final generation) 
//                and attempt to find a path.

package amazenlife;

public class Grid {

    // declare data fields for width, height, and a 2D board array
    int width;
    int height;
    int[][] board;

    // declare a 2D array to store whether a cell has been visited or not
    boolean[][] visited;

    // declare boolean to store whether the user wants verbose or silent output
    boolean verbose = true;

    // Grid object constructor that takes in a width and height
    public Grid(int width, int height) {

        // update the width datafield to the given width
        this.width = width;

        // update the height datafield to the given height
        this.height = height;

        // assign the board's width and height to the new given values
        this.board = new int[width][height];

        // assign the "visited" board's width and height to the new given values
        this.visited = new boolean[width][height];
    }

    // method to change the user's chosen output to silent
    public void changeToSilent() {

        // set verbose boolean to false
        verbose = false;
    }

    // method to check whether the user wanted verbose or silent output
    public boolean checkVerbose() {

        // return the value of the verbose boolean
        return verbose;
    }

    // method used to print the grid
    public void printBoard() {

        // print out a dashed line to go above the grid
        System.out.println("---");

        // execute the for loop according the grid's height
        for (int y = 0; y < width; y++) {

            // begin the String with a line for the grid's left border
            String line = "|";

            // execute the for loop according to the grid's width
            for (int x = 0; x < height; x++) {

                // switch statement for each cell of the board
                switch (this.board[y][x]) {
                    
                    // if the cell is dead/0, execute the if statement
                    case 0 -> // add a "." to the line String
                        line += "$";
                        
                    // if the cell if alive/1, execute the else statement   
                    case 1 -> // add a "*" to the line String
                        line += "*";
                        
                    // if the cell contains the explorer/2, execute the statement    
                    case 2 -> // add an "E" to the line String
                        line += "E";
                        
                    // if the cell contains the gold/3, execute the statement    
                    case 3 -> // add a "G" to the line String
                        line += "G";
                        
                    // if the cell contains the crumb/4, execute the statement    
                    case 4 -> // add a "#" to the line String
                        line += "#";
                        
                    // if the cell contains the X/5, execute the statement    
                    case 5 -> // add an "X" to the line String
                        line += "X";
                    
                    // if nothing mathces a case, do nothing
                    default -> {
                    }
                }    
            }

            // end the String with a line for the grid's right border
            line += "|";

            // print out line/row that was just created
            System.out.println(line);
        }

        // print out a dashed line to go below the grid and then a new line
        System.out.println("---\n");
    }

    // method to set a cell to be alive/1 
    public void setAlive(int x, int y) {

        // set the given cell to be alive/1
        this.board[x][y] = 1;
    }

    // method to count how many alive naighbors a cell has
    public int countAliveNeighbors(int x, int y) {

        // declare and initialize the counter variable to 0
        int count = 0;

        // add 1 to the count variable for each alive neighbor the cell has
        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        // return the total number of alive neighbors
        return count;
    }

    // method to return the state (dead or alive) of a cell
    public int getState(int x, int y) {

        // if x is < 0 or x >= the width, execute the if statement
        if (x < 0 || x >= width) {

            // return that the cell is dead
            return 0;
        }

        // if y is < 0 or y is >= the height, execute the if statement
        if (y < 0 || y >= height) {

            // return that the cell is dead
            return 0;
        }

        // return the value of the cell
        return this.board[x][y];
    }

    // mehtod to generate a new grid generation
    public void step() {

        // create a new board of the same width and height
        int[][] newBoard = new int[width][height];

        // iterate through the for loop according to the grid's height
        for (int y = 0; y < height; y++) {

            // iterate through the for loop according to the grid's width
            for (int x = 0; x < width; x++) {

                // store the number of alive neighbors in a variable
                int aliveNeighbors = countAliveNeighbors(x, y);

                // if the cell is alive, execute the if statement
                if (getState(x, y) == 1) {

                    // if there are less than 2 alive neighbors, execute the
                    // if statement
                    if (aliveNeighbors < 2) {

                        // assign the cell's status to 0 (dead)
                        newBoard[x][y] = 0;
                    } 

                    // if there are 2 or 3 alive neighbors, execute the else if
                    // statement
                    else if (aliveNeighbors == 2 || aliveNeighbors == 3) {

                        // assign the cell's status to 1 (alive)
                        newBoard[x][y] = 1;
                    } 
                    
                    // if there are more than 3 alive neighbors, execute the 
                    // else if statement 
                    else if (aliveNeighbors > 3) {

                        // assign the cell's status to 0 (dead)
                        newBoard[x][y] = 0;
                    }
                } 

                // if the cell is dead, execute the else statement
                else {

                    // if the cell has 3 neighbors, execute the if statement
                    if (aliveNeighbors == 3) {

                        // assign the cell's status to 1 (alive)
                        newBoard[x][y] = 1;
                    }
                }
            }
        }

        // replace the old grid with the new grid
        this.board = newBoard;
    }

    // method to figure out if the explorer can reach the gold
    public boolean isReachable() {
        
        // start the maze at the top left corner (0, 0)
        int i = 0, j = 0;

        // place the explorer in the top left corner
        placeExplorer(i, j);

        // initialize the variables for where the gold will be placed
        int fx = width - 1;
        int fy = height - 1;

        // place the gold in the bottom right corner (width - 1, height - 1)
        placeGold(fx, fy);

        // if the output is verbose, print the first stage of the board
        if (verbose) {
            printBoard();
        }

        // create an instance of FaithsStack
        FaithsStack<Node> s = new FaithsStack<>();

        // create a node with the starting position
        Node temp = new Node(i, j);

        // push the node onto the stack
        s.push(temp);

        // place a crumb on the starting position
        placeCrumb(i, j);

        // begining of try statement
        try {

            // while the stack is empty, iterate through the while loop
            while (!s.isEmpty()) {

                // store the top node on the stack in the temp variable
                temp = s.peek();
                
                // move left, right, top, bottom, or back according to the 
                // value of the temp node's dir variable
                int d = temp.getDir();
                i = temp.getX();
                j = temp.getY();

                // increment the direction variable and push the node onto the 
                // stack again
                temp.setDir(temp.getDir() + 1);
                s.pop();
                s.push(temp);

                // if the gold is found, return true
                if (i == fx && j == fy) {
                    return true;
                }

                // switch statement for the direction variable
                switch (d) {
                    
                    // if d = 0, check the up direction
                    case 0 -> {
                        
                        // checking the up direction
                        if (i - 1 >= 0 && (this.board[i - 1][j] == 0
                                || this.board[i - 1][j] == 3)
                                && this.visited[i - 1][j]) {
                            
                            // give the temp1 node a new value
                            Node temp1 = new Node(i - 1, j);
                            
                            // change this cell's status to false/visited
                            this.visited[i - 1][j] = false;
                            
                            // push the node onto the stack
                            s.push(temp1);

                            // place the explorer in the new position
                            placeExplorer(i - 1, j);

                            // place the crumb in the old position
                            placeCrumb(i, j);

                            // if the output is verbose, execute statement
                            if (verbose) {

                                // tell the user what direction the explorer moved
                                System.out.println("The explorer moved up");

                                // print the current status of the maze
                                printBoard();

                            }

                        }
                    }
                    
                    // if d = 1, check the left direction
                    case 1 -> {
                        
                        // checking the left direction
                        if (j - 1 >= 0 && (this.board[i][j - 1] == 0
                                || this.board[i][j - 1] == 3)
                                && this.visited[i][j - 1]) {
                            
                            // give the temp1 node a new value
                            Node temp1 = new Node(i, j - 1);
                            
                            // change this cell's status to false/visited
                            this.visited[i][j - 1] = false;
                            
                            // push the node onto the stack
                            s.push(temp1);

                            // place the explorer in the new position
                            placeExplorer(i, j - 1);

                            // place the crumb in the old position
                            placeCrumb(i, j);

                            // if the output is verbose, execute statement
                            if (verbose) {

                                // tell the user what direction the explorer moved
                                System.out.println("The explorer moved left");

                                // print the current status of the maze
                                printBoard();

                            }

                        }
                    }
                    
                    // if d = 2, check the down direction
                    case 2 -> {

                        // checking the down direction
                        if (i + 1 < width && (this.board[i + 1][j] == 0
                                || this.board[i + 1][j] == 3)
                                && this.visited[i + 1][j]) {
                            
                            // give the temp1 node a new value
                            Node temp1 = new Node(i + 1, j);
                            
                            // change this cell's status to false/visited
                            this.visited[i + 1][j] = false;
                            
                            // push the node onto the stack
                            s.push(temp1);

                            // place the explorer in the new position
                            placeExplorer(i + 1, j);

                            // place the crumb in the old position
                            placeCrumb(i, j);

                            // if the output is verbose, execute statement
                            if (verbose) {

                                // tell the user what direction the explorer moved
                                System.out.println("The explorer moved down");

                                // print the current status of the maze
                                printBoard();

                            }

                        }
                    }
                    
                    // if d = 3, check the right direction
                    case 3 -> {

                        // checking the right direction
                        if (j + 1 < height && (this.board[i][j + 1] == 0
                                || this.board[i][j + 1] == 3)
                                && this.visited[i][j + 1]) {
                            
                            // give the temp1 node a new value
                            Node temp1 = new Node(i, j + 1);
                            
                            // change this cell's status to false/visited
                            this.visited[i][j + 1] = false;
                            
                            // push the node onto the stack
                            s.push(temp1);

                            // place the explorer in the new position
                            placeExplorer(i, j + 1);

                            // place the crumb in the old position
                            placeCrumb(i, j);

                            // if the output is verbose, execute statement
                            if (verbose) {

                                // tell the user what direction the explorer moved
                                System.out.println("The explorer moved right");

                                // print the current status of the maze
                                printBoard();

                            }

                        }
                    }
                    
                    // if the explorer can't go in any direction, move it back
                    // to its previous position
                    default -> {
                        
                        // set the cell to true/unvisited
                        this.visited[temp.getX()][temp.getY()] = true;

                        // pop the top value off of the stack
                        s.pop();

                        // place explorer in previous position
                        placeExplorer(s.peek().getX(), s.peek().getY());

                        // leave an X where the explorer used to be
                        placeX(i, j);

                        // if the output is verbose, execute the statement
                        if (verbose) {

                            // tell the user the explorer moved back
                            System.out.println("The explorer went back");

                            // print the current status of the maze
                            printBoard();

                        }

                    } // end of default
                    
                } // end of switch statement
                
            } // end of while loop
            
        } // end of try
        
        // catch the EmptyStackException
        catch (EmptyStackException ex) {

            // do not give the user a message
        }

        // if the stack is empty and no path to the gold is found, return false 
        return false;
    }

    // method to initialize the "visited" 2D array to true/unvisited
    public void setVisited(boolean b) {
        
        // iterate through each element of the board
        for (boolean[] visited1 : this.visited) {
            for (int j = 0; j < visited1.length; j++) {
                
                // set the cell's value to the boolean that was passed (true)
                visited1[j] = b;
            }
        }

    }

    // method to place the explorer in the maze
    public void placeExplorer(int x, int y) {

        // set the given cell to hold the explorer
        this.board[x][y] = 2;
    }

    // method to place the gold in the maze
    public void placeGold(int x, int y) {

        // set the given cell to hold the gold
        this.board[x][y] = 3;
    }

    // method to place a crumb in the maze
    public void placeCrumb(int x, int y) {

        // set the given cell to hold the crumb
        this.board[x][y] = 4;
    }

    // method to place an X in the maze
    public void placeX(int x, int y) {

        // set the given cell to hold the X
        this.board[x][y] = 5;
    }

} // end of class
