package uta.cse3310;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard {
    private List<Player> players;

    public Leaderboard() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(String name) {
        Player newPlayer = new Player(name);
        players.add(newPlayer);
        sortPlayers();
    }

    // You might also need a method to update a player's score
    public void updateScore(String name, int newScore) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                player.setScore(newScore);
                break;
            }
        }
        sortPlayers();
    }

    private void sortPlayers() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getScore(), p1.getScore()); // Descending order
            }
        });
    }

    public List<Player> getSortedPlayers() {
        return players;
    }

    //Reset the leaderboard for a new game
    public void resetLeaderboard() {
        players.clear();
    }
}
