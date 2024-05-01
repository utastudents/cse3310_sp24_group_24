package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.*;
import uta.cse3310.ServerEvent;

public class ServerEventTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        PlayerType expectedPlayerType = PlayerType.XPLAYER;
        int expectedGameId = 123;
        int expectedConnectionID = 456;
        String expectedType = "ID";

        // Act
        ServerEvent serverEvent = new ServerEvent();

        serverEvent.YouAre = PlayerType.XPLAYER;
        serverEvent.GameId = 123;
        serverEvent.ConnectionID = 456;
        serverEvent.type = "ID";

        // Assert
        assertEquals(expectedPlayerType, serverEvent.YouAre);
        assertEquals(expectedGameId, serverEvent.GameId);
        assertEquals(expectedConnectionID, serverEvent.ConnectionID);
        assertEquals(expectedType, serverEvent.type);
    }
}
