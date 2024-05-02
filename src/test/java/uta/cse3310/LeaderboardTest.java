/*package uta.cse3310;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LeaderboardTest {
    
    private Leaderboard leaderboard;

    @Before
    public void setUp() {
        leaderboard = new Leaderboard();
    }

    @Test
    public void testAddPlayer() {
        Player player1 = new Player(1, "Player1", 100, "Some string");
        leaderboard.addPlayer(player1);
        List<Player> players = leaderboard.getPlayers();
        assertEquals(1, players.size());
        assertEquals(player1, players.get(0));
    }
    
    @Test
    public void testUpdateScore() {
        Player player1 = new Player(1, "Player1", 100, "Some string");
        leaderboard.addPlayer(player1);
        leaderboard.updateScore("Player1", 200);
        assertEquals(200, player1.getPoints());
    }
    
    @Test
    public void testResetLeaderboard() {
        Player player1 = new Player(1, "Player1", 100, "Some string");
        leaderboard.addPlayer(player1);
        leaderboard.resetLeaderboard();
        List<Player> players = leaderboard.getPlayers();
        assertEquals(0, players.size());
    }
} */
