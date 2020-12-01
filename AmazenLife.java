// File Name: AmazenLife.java
// Author: Faith Reeves
// Program Purpose: Allows the user to create their own grid and simulate
//                  Conway's "Game of Life" and then use the final generation as
//                  a maze. An "Explorer" is placed in the top left corner of the
//                  maze and tries to make its way to "Gold" in the bottom right
//                  corner.

package amazenlife;

import java.util.*;

public class AmazenLife {

    public static void main(String[] args) {

        // variable for users output format
        String outputForm;

        // variable for number of generations to be simulated
        int generations;

        // variable for the number of rows and cols the user wants
        String rowColAnswer;

        // variable for number of rows in grid
        int rows;

        // variable for number of columns in grid
        int columns;

        // create a Scanner to read in the user's input
        Scanner input = new Scanner(System.in);

        // prompt the user to choose an output format
        System.out.print("Enter \"v\" for verbose output or \"s\" for silent output: ");

        // store the user's answer
        outputForm = input.next();

        // get rid of any accidental whitespace or capitalization
        outputForm = outputForm.trim().toLowerCase();

        // if the user does not enter "v" or "s", execute if statement
        if (!"v".equals(outputForm) && !"s".equals(outputForm)) {

            // output error message
            System.out.println("Invalid input. Exiting Program.");

            // exit program
            System.exit(1);
        }

        // prompt the user for how many generations they want simulated
        System.out.print("How many generations would you like to be simulated?: ");

        try {
            // store the user's answer
            generations = input.nextInt();

            // if the user requests to simulate over 100 generations, execute if statement
            if (generations > 100) {

                // tell the user the issue and that you are exiting the program
                System.out.println("You may not simulate more than 100 generations. Exiting Program.");

                // exit the program
                System.exit(2);
            }

            // read the leftover new line
            input.nextLine();

            // prompt the user for the number of rows and columns in their grid
            System.out.print("Enter the number of rows and number of columns"
                    + " for your grid separated by a space: ");

            // store the user's answer ISSUE
            rowColAnswer = input.nextLine();

            // trim off any excess whitespace
            rowColAnswer = rowColAnswer.trim();

            // split the string at the space and store in an array
            String[] stringArray = rowColAnswer.split("\\s+");

            // convert element 0 of stringArray to an int and store in rows var
            rows = Integer.parseInt(stringArray[0]);

            // convert element 1 of stringArray to an int and store in columns var
            columns = Integer.parseInt(stringArray[1]);

            // create a Grid object with the given row and column sizes
            Grid myGrid = new Grid(rows, columns);

            // interate through the for loop according to the number of rows given
            for (int i = 0; i < rows; i++) {

                // prompt user to enter a row of numbers
                System.out.println("Enter " + columns + " 1's or 0's with no spaces in between: ");

                // store the users input
                String rowAnswer = input.nextLine();

                // get rid of any extra white space on the ends
                rowAnswer = rowAnswer.trim();

                // interate through the for loop according to the number of columns given
                for (int j = 0; j < columns; j++) {

                    // read in and store the user's input
                    char element = rowAnswer.charAt(j);

                    // check if each character of the string is a 1 or 0
                    if (element != '1' && element != '0') {

                        // output error message
                        System.out.println("You entered something that was not a 1 "
                                + "or 0. Exiting Program.");

                        // exit program
                        System.exit(6);
                    }

                    // convert input to an int
                    int intElement = Character.getNumericValue(element);

                    // if the user entered a 1, execute the if statement
                    if (intElement == 1) {

                        // make the appropriate cell "alive" FIX THIS!!!!!
                        myGrid.setAlive(i, j);
                        // i = 0, j = 2
                    }
                }
            }

            // execute the verbose if statement if the user entered a "v"
            // and print each generation
            if ("v".equals(outputForm)) {

                // execute the for loop according to the number of generations the
                // user requested to simulate
                for (int i = 0; i < generations; i++) {

                    // output the generation number of the next grid
                    System.out.println("Generation " + (i + 1));

                    // print the new grid
                    myGrid.printBoard();

                    // simulate a new generation unless its the last iteration of
                    // the for loop
                    if (generations - i > 1) {

                        // simlate a new grid
                        myGrid.step();

                    }

                }
            }

            // execute the silent if statement if the user entered an "s" 
            // and print only the last generation
            if ("s".equals(outputForm)) {

                // set the "verbose" boolean in Grid.java to false
                myGrid.changeToSilent();

                // output the message for the first/initial generation
                System.out.println("Generation 1");

                // print the initial grid
                myGrid.printBoard();

                // execute the for loop according to the number of generations the
                // user requested to simulate
                for (int j = 0; j < generations; j++) {

                    // if the counter is equal to one less than the number of 
                    // generations, execute the if statement
                    if (j == (generations - 1)) {

                        // output the generation number of the next grid
                        System.out.println("Generation " + (j + 1));

                        // print the new grid
                        myGrid.printBoard();
                    }

                    // simulate a new generation unless its the last iteration of
                    // the for loop
                    if (generations - j > 1) {

                        // simlate a new grid
                        myGrid.step();
                    }
                }
            }

            // tell the user you are about to solve the maze
            System.out.println("Maze Solver:");

            // set the intinial value of the "visited" array to true/unvisited 
            myGrid.setVisited(true);

            // if the gold is reachable, execute the statement
            if (myGrid.isReachable()) {

                // if the output is set to silent, execute the statement
                if (!myGrid.checkVerbose()) {

                    // print the final solution to the maze
                    myGrid.printBoard();

                }

                // tell the user their explorer found the gold
                System.out.println("Your explorer found the gold!");
            } // if the gold is NOT reachable, the else statement is executed
            else {

                // tell the user their explorer could NOT find the gold
                System.out.println("Your explorer could not find the gold.");
            }

        } // end of try block
        // if user's input is the incorrect data type, catch exception
        catch (InputMismatchException ex) {

            // output error message
            System.out.println("You entered an invalid data type. Exiting Program.");

            // exit program
            System.exit(3);
        } // if user's input is the incorrect data type, catch exception
        catch (NumberFormatException ex) {

            // output error message
            System.out.println("You entered an invalid data type. Exiting Program.");

            // exit program
            System.exit(4);
        } // if user's input is not long enough, catch exception
        catch (StringIndexOutOfBoundsException ex) {

            // output error message
            System.out.println("Your input was not long enough. Exiting Program.");

            // exit program
            System.exit(5);
        } // if user did not complete required input, catch exception
        catch (ArrayIndexOutOfBoundsException ex) {

            // output error message
            System.out.println("You only entered one digit. Exiting Program.");

            // exit program
            System.exit(7);
        }
    }
}

