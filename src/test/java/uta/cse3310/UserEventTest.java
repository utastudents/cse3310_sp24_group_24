package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserEventTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        int expectedGameId = 123;
        PlayerType expectedPlayerIdx = PlayerType.XPLAYER;
        int expectedButton = 4;

        // Act
        UserEvent userEvent = new UserEvent(expectedGameId, expectedPlayerIdx, expectedButton);

        // Assert
        assertEquals(expectedGameId, userEvent.GameId);
        assertEquals(expectedPlayerIdx, userEvent.PlayerIdx);
        assertEquals(expectedButton, userEvent.Button);
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        UserEvent userEvent = new UserEvent();

        // Assert
        assertEquals(0, userEvent.GameId);
        assertNull(userEvent.PlayerIdx);
        assertEquals(0, userEvent.Button);
    }
}
