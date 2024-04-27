package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

public class GridUnitTest extends TestCase {

    public final int GridSize = 10;
    public final int GridArea = GridSize * GridSize;
    public final double TargetDensity = .67;
    public final int MaxWordCount = 275;
    public final int MinWordCount = 10;
    // public char[][] WordSearchGrid;
    // public List<String> SolutionArray;
    // public List<String> WordsUsed;
    // public List<String> WordsUsedLocations;
    // public List<String> WordList;
    // static final Random random = new Random();
    // public double GridDensity;

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

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    // Extensive test to test a set of words fitting onto the grid in a particular way
    public void testtryLocation() {
        // Initialize Variables
        final List<String> WordList;
        WordList = new ArrayList<>();
        final char[][] WordSearchGrid;
        WordSearchGrid = new char[GridSize][GridSize];

        // Create Grid object
        Grid testGrid = new Grid();

        // Code from the CreatWordSeachGrid
        int MinCellsUsed = (int) (GridArea * TargetDensity);
        System.out.println("GridArea = " + GridArea);
        System.out.println("TargetDensity = " + TargetDensity);
        System.out.println("MinCellsUsed= " + MinCellsUsed);
        int CellsUsed = 0;

        /**
         * WordList - Size Of Word
         * ===========================
         * expressions - [Does Not Fit]
         * exercises - 9 characters
         * beautiful - 9 characters
         * resumes - 7 characters
         * familiar - 8 characters
         * useful - 6 characters
         * linked - 6 characters
         * lyrics - 6 characters
         * scout - 5 characters
         * rest - 4 characters
         * epic - 4 characters
         * love - 4 characters
         * ends - 4 characters
         * dual - 4 characters
         */

        // Add words to word list individually
        WordList.add(("expressions").toUpperCase()); // index 0
        WordList.add(("exercises").toUpperCase()); // index 1
        WordList.add(("beautiful").toUpperCase()); // index 2
        WordList.add(("resumes").toUpperCase()); // index 3
        WordList.add(("familiar").toUpperCase()); // index 4
        WordList.add(("useful").toUpperCase()); // index 5
        WordList.add(("linked").toUpperCase()); // index 6
        WordList.add(("lyrics").toUpperCase()); // index 7
        WordList.add(("scout").toUpperCase()); // index 8
        WordList.add(("rest").toUpperCase()); // index 9
        WordList.add(("epic").toUpperCase()); // index 10
        WordList.add(("love").toUpperCase()); // index 11
        WordList.add(("ends").toUpperCase()); // index 12
        WordList.add(("dual").toUpperCase()); // index 13

        // Unable to test if word is not inserted into grid for being out of bounds, due to different grid sizes
        int expectedCellsUsed = 0;

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
    }
}
