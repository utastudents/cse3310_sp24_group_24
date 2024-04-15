package uta.cse3310;

import java.util.Arrays;

public class Grid {

    public static char[][] grid; // Stores the words in the grid
    final static int gridLength = 50; // Length of the grid
    private int borderLength; // Not sure if this is necessary
    private Bank Usedwords;

    public Grid() {
        Bank FinalBank = new Bank();
        char[][] grid = new char[gridLength][gridLength];

        // Initilaize the grid
        createGrid(grid);

        // Print the grid
        printGrid(grid);
    }

    public void createGrid(char[][] grid) {
        // Initialize The Grid
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                grid[i][j] = '-';
            }
        }
        return;
    }

    // TO-DO: Finish fillWords
    public void fillWords(final int FinalBank) {
        // Density value
        int character = 0;
        // Check word density before continuing loop

        return;
    }

    // TO-DO: Finish fillLetters
    public void fillLetters(char grid[][], String word, int posStartX, int posStartY, int orientation) {
        // Convert word string to character array
        char[] ch = word.toCharArray();

        // Finds the length of the word
        int size = word.length();

        // Orientation position values 
        int posIncrementX = 0, posIncrementY = 0;

        // // TO-DO: Check and sets orientation position values
        // switch (orientation) {
        //     case HORIZONAL_R:
        //         posIncrementX = 1;
        //         break;
        //     case HORIZONAL_L:
        //         posIncrementX = -1;
        //         break;
        //     case VERTICAL_U:
        //         posIncrementY = 1;
        //         break;
        //     case VERTICAL_D:
        //         posIncrementY = -1;
        //         break;
        //     case DIAGONAL_UR:
        //         posIncrementX = 1;
        //         posIncrementY = 1;
        //         break;
        //     case DIAGONAL_DR:
        //         posIncrementX = 1;
        //         posIncrementY = -1;
        //         break;
        // }

        // Check the whole word against the grid before placing the word
        int i = 0;
        while (i < size) {
            if (!((Character.compare(grid[posStartX][posStartY], '-') == 0))
                    && !(Character.compare(grid[posStartX][posStartY], ch[i]) == 0)) {
                // Word can't be placed
                return;
            }

            // Increment by one
            i++;
        }

        // Word can be placed, so place word down
        // TO-DO: Count the number of times a specific letter is used
        // TO-DO: Count the number of times a certain orientation is used

        return;
    }

    // Prints the Word Grid
    public static void printGrid(char grid[][]) {
        for (int i = 0; i < gridLength; i++) {
            // System.out.print(i+1 + " "); // Prints the row number as debug
            for (int j = 0; j < gridLength; j++) {
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int checkDensity() {
        return 1;
    }

    public int checkOrientation() {
        return 1;
    }

    public String FillerLettersRatio() {
        return "FillerLettersRatio";
    }

}
