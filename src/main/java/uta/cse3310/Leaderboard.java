package uta.cse3310;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard
{
        // player player = new leaderboard p
    private List<Player> players;

    public Leaderboard() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player newP) {
        //Player newPlayer = new Player(name, score);
        players.add(newP);
        sortPlayers();
    }

    // You might also need a method to update a player's score
    public void updateScore(String name, int newScore) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                player.setPoints(newScore);
                break;
            }
        }
        sortPlayers(); // Sorting after updating the score
    }
    

   /*  List<player> sorted(int x) // Sort the player
    {
        List<player> oneone = new ArrayList<>();
        return oneone;
    } */
    private void sortPlayers() {
        Collections.sort(players, new Comparator<Player>()
     {

    @Override
    public int compare(Player p1, Player p2) {
    return Integer.compare(p2.getPoints(), p1.getPoints()); // Descending order
    }
        });
    }
    public List<Player> getPlayers() {
        return players;
    }
    
    
        

    //Reset the leaderboard for a new game
    public void resetLeaderboard() {
        players.clear();
    }
}