package uta.cse3310;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;



public class Grid {

    public final int GridSize = 20;
    public final int GridArea = GridSize * GridSize;
    public final double TargetDensity = .67;
    public final int MaxWordCount = 275;
    public final int MinWordCount = 10;
    public char[][] WordSearchGrid;
    public List<String> SolutionArray;
    public List<String> WordsUsed;
    public List<String> WordsUsedLocations;
    public List<String> WordList;
    static final Random random = new Random();
    public double GridDensity;

    public Grid() 
    {
        //Initialize Variables
        

        WordList = new ArrayList<>();
        WordSearchGrid = new char[GridSize][GridSize];
        SolutionArray = new ArrayList<>();
        WordsUsed = new ArrayList<>();
        WordsUsedLocations = new ArrayList<>();
        

        //Call function to read in words
        ReadInWordFile(WordList, GridSize);
    
        //Call function to create WordSearchGrid
        CreateWordSearchGrid(WordSearchGrid, WordList, SolutionArray, GridDensity);

        
        printResult(WordSearchGrid);

        
    }

    //Gen words 8 directions
    static final int[][] DIRS = {
            // Horizontal Right (0), Vertical Down (1), Diagonal Down Right (2), Diagonal Up
            // Right (3), Horizontal Left (4), Vertical Down (5), Diagonal Down Left (6),
            // Diagonal Up Left (7)
            { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 }, { -1, 0 }, { 0, -1 }, { -1, -1 }, { -1, 1 }
    };

    //Read in words from text file
    static void ReadInWordFile(List<String> WordList, int GridSize) {
        try (Scanner scanner = new Scanner(new FileReader("words.txt"))) 
        {
            while (scanner.hasNext()) 
            {
                //Create string to read next word in
                String word = scanner.next().trim().toLowerCase();

                //Adds word to word list if its over three letters long but less than the size of grid
                if (word.matches("^[a-z]{3," + GridSize + "}$")) 
                { // 3 is min word size limit
                    WordList.add(word.toUpperCase());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Handle file not found error
        }
    }

    //Create WordSearchGrid
    public void CreateWordSearchGrid(char[][] WordSearchGrid, List<String> WordList, List<String> SolutionArray, double GridDensity) {
    
            //Randomly shuffle WordList
            Collections.shuffle(WordList); 
            
            //Creating a MaxCellsUsed to make sure it hits the .67 density
            int MinCellsUsed = (int) (GridArea * TargetDensity);
            System.out.println("GridArea = " + GridArea);
            System.out.println("TargetDensity = " + TargetDensity);
            System.out.println("MinCellsUsed= " + MinCellsUsed);

            //Keep track of how many cells are filled with non random letters
            int CellsUsed = 0;

            for (String word : WordList) {
            
                CellsUsed += tryPlaceWord(WordSearchGrid, word);
                
                if (CellsUsed >= MinCellsUsed || SolutionArray.size() >= MaxWordCount) {
                    System.out.println("stopping grid creation");
                    System.out.println("cells used = "+CellsUsed);
                    System.out.println("solution array size = " + SolutionArray.size());
                    break; // Stop placing words if the target is reached or maximum words reached
                }
            }
            
        }
    
        public int tryPlaceWord(char[][] WordSearchGrid, String word){
            // Choses a random direction from DIRS and then random starting position
            int RandomDirection = random.nextInt(DIRS.length);
            int RandomPosition = random.nextInt(GridArea);

            // Choose a direction for the word
            for (int dir = 0; dir < DIRS.length; dir++) {
                dir = (dir + RandomDirection) % DIRS.length;
                
                // Choose a random position value for the row and column starting position.
                for (int pos = 0; pos < GridArea; pos++) {
                    pos = (pos + RandomPosition) % GridArea;
                    
                    // Moved r and c here so tryLocation is testable. -Chime
                    // r = Row, c = Column
                    int r = pos / GridSize;
                    int c = pos % GridSize;

                    int CellsUsed = tryLocation(WordSearchGrid, word, dir, r, c); 

                    if(CellsUsed > 0){
                        return CellsUsed;
                    }
                }
            }

            return 0;
        }

    public int tryLocation(char[][] WordSearchGrid, String word, int dir, int r, int c) {
        int length = word.length();

        // check bounds
        if ((DIRS[dir][0] == 1 && (length + c) > GridSize)
                || (DIRS[dir][0] == -1 && (length - 1) > c)
                || (DIRS[dir][1] == 1 && (length + r) > GridSize)
                || (DIRS[dir][1] == -1 && (length - 1) > r))
            return 0;

        int i, rr, cc, overlaps = 0;

        //check cells
        for (i = 0, rr = r, cc = c; i < length; i++) {
            if (WordSearchGrid[rr][cc] != 0 && WordSearchGrid[rr][cc] != word.charAt(i))
                return 0;

            cc += DIRS[dir][0];
            rr += DIRS[dir][1];
        }

        // place word
        for (i = 0, rr = r, cc = c; i < length; i++) {
            if (WordSearchGrid[rr][cc] == word.charAt(i))
                overlaps++;
            else
                WordSearchGrid[rr][cc] = word.charAt(i);

            if (i < length - 1) {
                cc += DIRS[dir][0];
                rr += DIRS[dir][1];
            }
        }

        int lettersPlaced = length - overlaps;

        if (lettersPlaced > 0)
            SolutionArray.add(String.format("%-10s (%d,%d) (%d,%d)", word, c, r, cc, rr));
            WordsUsed.add(word);
            WordsUsedLocations.add(String.format("(%d,%d)(%d,%d)", c, r, cc, rr));


        return lettersPlaced;
    }

    // Count the characters in valid words placed on the grid
    public int countValidWordCharacters(char[][] WordSearchGrid) {
        int count = 0;
        for (String solution : SolutionArray) {
            String word = solution.split("\\s+")[0]; // Extract the word
            count += word.length();
        }
        return count;
    }

    // print
    public void printResult(char[][] WordSearchGrid) {
        
        System.out.println("Number of words: " + SolutionArray.size());
        System.out.println("Density of the grid: " + (double) countValidWordCharacters(WordSearchGrid) / GridArea); // Print the density

        System.out.print("\n    ");

        for (int c = 0; c < GridSize; c++) {
            System.out.print(c + "  ");
        }

        System.out.println();

        for (int r = 0; r < GridSize; r++) {
            System.out.printf("%n%d  ", r);

            for (int c = 0; c < GridSize; c++) {
                if (WordSearchGrid[r][c] == 0) {
                    WordSearchGrid[r][c] = (char) ('A' + random.nextInt(26)); // Fill empty cells with random letters
                }
                System.out.printf(" %c ", WordSearchGrid[r][c]);
            }
        }

        System.out.println("\n");

        for (int i = 0; i < SolutionArray.size() - 1; i += 2) {
            System.out.printf("%s %s%n", SolutionArray.get(i), SolutionArray.get(i + 1));
        }

        if (SolutionArray.size() % 2 == 1) {
            System.out.println(SolutionArray.get(SolutionArray.size() - 1));
        }
    }
}