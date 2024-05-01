package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    @Test
    public void testConstructor() {
        // Arrange & Act
        Statistics stats = new Statistics();

        // Assert
        assertEquals(0L, (long) stats.getRunningTime());
        assertEquals(0, (int) stats.getXWins());
        assertEquals(0, (int) stats.getOWins());
        assertEquals(0, (int) stats.getDraws());
        assertEquals(0, (int) stats.getTotalGames());
        assertEquals(0, (int) stats.getGamesInProgress());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        long expectedRunningTime = 200L;
        int expectedXWins = 9;
        int expectedOWins = 3;
        int expectedDraws = 8;
        int expectedTotalGames = 12;
        int expectedGamesInProgress = 3;

        Statistics stats = new Statistics();

        // Act
        stats.setRunningTime(expectedRunningTime);
        stats.setXWins(expectedXWins);
        stats.setOWins(expectedOWins);
        stats.setDraws(expectedDraws);
        stats.setTotalGames(expectedTotalGames);
        stats.setGamesInProgress(expectedGamesInProgress);

        // Assert
        assertEquals(expectedRunningTime, (long) stats.getRunningTime());
        assertEquals(expectedXWins, (int) stats.getXWins());
        assertEquals(expectedOWins, (int) stats.getOWins());
        assertEquals(expectedDraws, (int) stats.getDraws());
        assertEquals(expectedTotalGames, (int) stats.getTotalGames());
        assertEquals(expectedGamesInProgress, (int) stats.getGamesInProgress());
    }
}
