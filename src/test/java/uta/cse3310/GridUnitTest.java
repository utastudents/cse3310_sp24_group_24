package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

public class GridUnitTest extends TestCase {

    public final int GridSize = 20;
    public final int GridArea = GridSize * GridSize;
    public final double TargetDensity = .67;
    public final int MaxWordCount = 275;
    public final int MinWordCount = 10;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GridUnitTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(GridUnitTest.class);
    }

    // Uses a set of words as a baseline for some set functionalities
    public void wordListSetup(List<String> WordList, boolean addWords) {
        // Primary words for testtryAllDirections and testtryLocation
        String[] Words = { "expressions", "exercises", "beautiful", "resumes", "familiar", "useful",
                "linked", "lyrics", "scout", "rest", "epic", "love", "ends", "dual" };
        for (String word : Words) {
            WordList.add((word).toUpperCase());
        }

        // Additional words for testminWordsAndMinDensity
        if (addWords == true) {
            // Since the current implementation of ReadInWordFile returns void, we currently
            // can't get a random list.
            String[] AdditionalWords = { "academic", "bookstore", "congratulations", "distributors", "dodge", "earn",
                    "fundamental", "generating", "ghost", "hammer", "headquarters", "identify", "instrumentation",
                    "jazz", "kernel", "knew", "lanes", "lights", "mainland", "many", "median", "mirrors", "prefix",
                    "prix", "same", "stylus", "treaty", "troubleshooting", "undergraduate", "unlock", "valid", "viking",
                    "wake", "watt", "xerox", "yellow", "zinc", "wind", "adobe", "acre", "ports", "mill", "custom",
                    "expo", "ever", "humor", "idol", "inch", "open", "opera", "order", "ours", "oxford", "page" };
            for (String word : AdditionalWords) {
                WordList.add((word).toUpperCase());
            }
        }
    }

    // Simple test to see if a word can be inserted in all possible directions
    // (testing with 20x20 grid)
    public void testtryAllDirections() {
        final List<String> WordList;
        WordList = new ArrayList<>();
        final char[][] WordSearchGrid;
        WordSearchGrid = new char[GridSize][GridSize];
        int wordsUsed = 0;
        int expectedCellsUsed = 0;

        // Add words to word list
        wordListSetup(WordList, false);

        // Create Grid object
        Grid testGrid = new Grid();

        // Code from the CreatWordSeachGrid function
        int MinCellsUsed = (int) (GridArea * TargetDensity);
        System.out.println("\nGridUnitTest: Testing all directions...\nGridArea = " + GridArea);
        System.out.println("TargetDensity = " + TargetDensity);
        System.out.println("MinCellsUsed= " + MinCellsUsed);
        int CellsUsed = 0;

        // Testing horizontal right (dir = 0)
        // Should NOT fit into 20x20 grid at r = 10, c = 10
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(0), 0, 10, 10);
        expectedCellsUsed = 0;
        assertEquals(expectedCellsUsed, CellsUsed);

        // Testing diagonal up right (dir = 3)
        // Should NOT fit into 20x20 grid at r = 10, c = 10
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(0), 3, 10, 10);
        assertEquals(expectedCellsUsed, CellsUsed);

        // Testing vertical up (dir = 5)
        // Should fit into 20x20 grid
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(0), 5, 10, 10);
        expectedCellsUsed = 11;
        assertEquals(expectedCellsUsed, CellsUsed);
        wordsUsed++;

        // Testing diagonal up left (dir = 6)
        // Should fit into 20x20 grid
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(0), 6, 10, 10);
        expectedCellsUsed = 21;
        assertEquals(expectedCellsUsed, CellsUsed);
        wordsUsed++;

        // Testing horizontal left (dir = 4)
        // Should fit into 20x20 grid
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(0), 4, 10, 10);
        expectedCellsUsed = 31;
        assertEquals(expectedCellsUsed, CellsUsed);
        wordsUsed++;

        // Testing diagonal up right (dir = 7)
        // Should NOT fit into 20x20 grid at r = 10, c = 10
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(0), 7, 10, 10);
        assertEquals(expectedCellsUsed, CellsUsed);

        // Testing vertical down (dir = 1)
        // Should NOT fit into 20x20 grid at r = 10, c = 10
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(0), 1, 10, 10);
        assertEquals(expectedCellsUsed, CellsUsed);

        // Testing diagonal down right (dir = 2)
        // Should NOT fit into 20x20 grid at r = 10, c = 10
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(0), 2, 10, 10);
        assertEquals(expectedCellsUsed, CellsUsed);

        // Unable to print resulting grid due to global variables in Grid.java

        // Check if it doesn't meet the minimum word requirements
        assertFalse(wordsUsed > MinCellsUsed);

        System.out.println("Finished testing all directions...\n");
    }

    // Extensive test to test a set of words fitting onto the grid in a particular
    // way
    public void testtryLocation() {
        // Initialize Variables
        final List<String> WordList;
        WordList = new ArrayList<>();
        final char[][] WordSearchGrid;
        final int GridSize = 10;
        final int GridArea = GridSize * GridSize;
        WordSearchGrid = new char[GridSize][GridSize];
        int expectedCellsUsed = 0;

        // Add words to word list
        wordListSetup(WordList, false);

        // Create Grid object
        Grid testGrid = new Grid();

        // Code from the CreatWordSeachGrid function
        int MinCellsUsed = (int) (GridArea * TargetDensity);
        System.out.println("\nGridUnitTest: Testing 10x10 Grid...\nGridArea = " + GridArea);
        System.out.println("TargetDensity = " + TargetDensity);
        System.out.println("MinCellsUsed= " + MinCellsUsed);
        int CellsUsed = 0;

        // Unable to test if word is not inserted into grid for being out of bounds, due
        // to overriding variables in Grid.java under a 10x10 grid.

        /**
         * Adding "exercises"
         * _ _ _ _ _ _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * _ _ _ _ X _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * _ _ _ _ R _ _ _ _ _
         * _ _ _ _ C _ _ _ _ _
         * _ _ _ _ I _ _ _ _ _
         * _ _ _ _ S _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * _ _ _ _ S _ _ _ _ _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(1), 1, 1, 4);
        expectedCellsUsed = 9;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "beautiful"
         * _ _ _ _ _ _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * _ _ _ _ X _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * _ _ _ _ R _ _ _ _ _
         * _ _ _ _ C _ _ _ _ _
         * _ L U F I T U A E B
         * _ _ _ _ S _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * _ _ _ _ S _ _ _ _ _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(2), 4, 6, 9);
        expectedCellsUsed = 17;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "resumes"
         * _ _ _ _ _ _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * R _ _ _ X _ _ _ _ _
         * E _ _ _ E _ _ _ _ _
         * S _ _ _ R _ _ _ _ _
         * U _ _ _ C _ _ _ _ _
         * M L U F I T U A E B
         * E _ _ _ S _ _ _ _ _
         * S _ _ _ E _ _ _ _ _
         * _ _ _ _ S _ _ _ _ _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(3), 1, 2, 0);
        expectedCellsUsed = 24;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "familiar"
         * _ _ _ _ _ _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * R _ _ _ X _ _ _ R _
         * E _ _ _ E _ _ A _ _
         * S _ _ _ R _ I _ _ _
         * U _ _ _ C L _ _ _ _
         * M L U F I T U A E B
         * E _ _ M S _ _ _ _ _
         * S _ A _ E _ _ _ _ _
         * _ F _ _ S _ _ _ _ _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(4), 3, 9, 1);
        expectedCellsUsed = 31;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "useful"
         * _ _ _ _ _ _ _ _ _ _
         * _ _ _ _ E _ _ _ _ _
         * R _ _ _ X _ _ _ R _
         * E _ _ _ E _ _ A _ _
         * S _ _ _ R _ I _ U _
         * U _ _ _ C L _ _ S _
         * M L U F I T U A E B
         * E _ _ M S _ _ _ F _
         * S _ A _ E _ _ _ U _
         * _ F _ _ S _ _ _ L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(5), 1, 4, 8);
        expectedCellsUsed = 36;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "linked"
         * _ _ _ _ _ _ _ _ _ _
         * _ D _ _ E _ _ _ _ _
         * R E _ _ X _ _ _ R _
         * E K _ _ E _ _ A _ _
         * S N _ _ R _ I _ U _
         * U I _ _ C L _ _ S _
         * M L U F I T U A E B
         * E _ _ M S _ _ _ F _
         * S _ A _ E _ _ _ U _
         * _ F _ _ S _ _ _ L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(6), 5, 6, 1);
        expectedCellsUsed = 41;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "lyrics"
         * _ _ _ _ _ _ _ _ _ _
         * _ D _ _ E _ _ _ _ _
         * R E _ _ X _ _ _ R _
         * E K _ _ E _ _ A _ _
         * S N S _ R _ I _ U _
         * U I _ C C L _ _ S _
         * M L U F I T U A E B
         * E _ _ M S R _ _ F _
         * S _ A _ E _ Y _ U _
         * _ F _ _ S _ _ L L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(7), 6, 9, 7);
        expectedCellsUsed = 46;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "scout"
         * _ _ _ _ _ S _ _ _ _
         * _ D _ _ E _ C _ _ _
         * R E _ _ X _ _ O R _
         * E K _ _ E _ _ A U _
         * S N S _ R _ I _ U T
         * U I _ C C L _ _ S _
         * M L U F I T U A E B
         * E _ _ M S R _ _ F _
         * S _ A _ E _ Y _ U _
         * _ F _ _ S _ _ L L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(8), 2, 0, 5);
        expectedCellsUsed = 51;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "rest"
         * _ _ _ _ _ S _ _ _ _
         * _ D _ R E _ C _ _ _
         * R E _ E X _ _ O R _
         * E K _ S E _ _ A U _
         * S N S T R _ I _ U T
         * U I _ C C L _ _ S _
         * M L U F I T U A E B
         * E _ _ M S R _ _ F _
         * S _ A _ E _ Y _ U _
         * _ F _ _ S _ _ L L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(9), 1, 1, 3);
        expectedCellsUsed = 55;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "epic"
         * _ _ _ _ _ S _ _ _ _
         * _ D _ R E C C _ _ _
         * R E _ E X I _ O R _
         * E K _ S E P _ A U _
         * S N S T R E I _ U T
         * U I _ C C L _ _ S _
         * M L U F I T U A E B
         * E _ _ M S R _ _ F _
         * S _ A _ E _ Y _ U _
         * _ F _ _ S _ _ L L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(10), 5, 4, 5);
        expectedCellsUsed = 59;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "love"
         * _ _ _ _ _ S E V O L
         * _ D _ R E C C _ _ _
         * R E _ E X I _ O R _
         * E K _ S E P _ A U _
         * S N S T R E I _ U T
         * U I _ C C L _ _ S _
         * M L U F I T U A E B
         * E _ _ M S R _ _ F _
         * S _ A _ E _ Y _ U _
         * _ F _ _ S _ _ L L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(11), 4, 0, 9);
        expectedCellsUsed = 63;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "ends"
         * _ _ E N D S E V O L
         * _ D _ R E C C _ _ _
         * R E _ E X I _ O R _
         * E K _ S E P _ A U _
         * S N S T R E I _ U T
         * U I _ C C L _ _ S _
         * M L U F I T U A E B
         * E _ _ M S R _ _ F _
         * S _ A _ E _ Y _ U _
         * _ F _ _ S _ _ L L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(12), 0, 0, 2);
        expectedCellsUsed = 66;
        assertEquals(expectedCellsUsed, CellsUsed);

        /**
         * Adding "dual"
         * _ _ E N D S E V O L
         * _ D _ R E C C _ _ _
         * R E _ E X I _ O R _
         * E K _ S E P _ A U _
         * S N S T R E I _ U T
         * U I _ C C L _ L S _
         * M L U F I T U A E B
         * E _ _ M S R _ U F _
         * S _ A _ E _ Y D U _
         * _ F _ _ S _ _ L L _
         */
        CellsUsed += testGrid.tryLocation(WordSearchGrid, WordList.get(13), 5, 8, 7);
        expectedCellsUsed = 69;
        assertEquals(expectedCellsUsed, CellsUsed);

        // Check if it meets the minimum number of cells
        assertTrue(CellsUsed > MinCellsUsed);

        // Unable to print resulting grid due to global variables in Grid.java

        System.out.println("Finished testing tryLocations...\n");

    }

    // Most other functions outside of tryLocation be either trivial, has randomness
    // built into the function, or is untestable due to global variables in
    // Grid.java.

    // Simple test that test placing words down with randomness involved. It is
    // difficult to get the exact values for the test, we are testing the minimum
    // words and minimum density.
    public void testminWordsAndMinDensity() {
        final List<String> WordList;
        WordList = new ArrayList<>();
        final char[][] WordSearchGrid;
        WordSearchGrid = new char[GridSize][GridSize];
        int wordsUsed = 0;

        // Add words to word list
        wordListSetup(WordList, true);

        // Create Grid object
        Grid testGrid = new Grid();

        // Code from the CreatWordSeachGrid function
        System.out.println("\nGridUnitTest: Testing random word placement...\nGridArea = " + GridArea);
        int CellsUsed = 0;

        // Insert words randomly into word grid
        for (String word : WordList) {
            CellsUsed += testGrid.tryPlaceWord(WordSearchGrid, word);
            wordsUsed++;
        }

        // Check if it meets the minimum and maximum word requirements
        assertTrue(MinWordCount < wordsUsed);
        assertTrue(MaxWordCount > wordsUsed);

        // Check if it meets the minimum density by using countValidWordCharacters
        double WordDensitiy = (double) CellsUsed / GridArea;
        System.out.println("WordDensitiy = " + WordDensitiy);
        assertTrue(WordDensitiy <= 1);
        assertTrue(WordDensitiy > TargetDensity);
        
        System.out.println("Finished testing random word placement\n");
    }

}