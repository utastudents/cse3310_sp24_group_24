package uta.cse3310;

import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;


public class PlayerTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        int expectedConnectionID = 987654;
        String expectedPlayerName = "Duy";
        int expectedPlayerPoints = 123456;
        String expectedPlayerColor = "Red";

        // Act
        Player player = new Player(expectedConnectionID, expectedPlayerName, expectedPlayerPoints, expectedPlayerColor);

        // Assert
        assertEquals(expectedConnectionID, player.getConnectionID());
        assertEquals(expectedPlayerName, player.getName());
        assertEquals(expectedPlayerPoints, player.getPoints());
        assertEquals(expectedPlayerColor, player.getColor());
    }

    @Test
    public void testSetPoints() {
        // Arrange
        Player player = new Player(123456, "Duy", 0, "Red");
        int expectedPoints = 987654;

        // Act
        player.setPoints(expectedPoints);

        // Assert
        assertEquals(expectedPoints, player.getPoints());
    }

    @Test
    public void testPlayerList() {
        // Arrange
        Player player1 = new Player(123456, "Duy", 0, "Red");
        Player player2 = new Player(654321, "Estefany", 0, "Blue");
        Player player3 = new Player(789012, "Chime", 0, "Green");
        Player player4 = new Player(123490, "Nancy", 0, "Purple");
        Player player5 = new Player(123478, "Tres", 0, "Black");

        // Act
        List<String> playerList = Player.getPlayerList();

        // Assert
        assertEquals(5, playerList.size());
        assertTrue(playerList.contains("Duy"));
        assertTrue(playerList.contains("Estefany"));
        assertTrue(playerList.contains("Chime"));
        assertTrue(playerList.contains("Nancy"));
        assertTrue(playerList.contains("Tres"));
    
    }
    
    @After
    public void tearDown() {
        // Clean up the playerList after each test
        Player.getPlayerList().clear();
    }
}
