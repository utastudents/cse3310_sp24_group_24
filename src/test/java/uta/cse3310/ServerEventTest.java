package uta.cse3310;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        serverEvent.GameId = 111;
        serverEvent.ConnectionID = 345;
        serverEvent.type = "ID";

        // Assert
        assertEquals(expectedPlayerType, serverEvent.YouAre);
        assertEquals(expectedGameId, serverEvent.GameId);
        assertEquals(expectedConnectionID, serverEvent.ConnectionID);
        assertEquals(expectedType, serverEvent.type);
    }
}
