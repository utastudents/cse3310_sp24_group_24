package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    public  List<Player> players;
    private List<Player> readyQueue;
    //private List<Player> leaderboard;
    private PlayerType gameController; // Added field to track game controller
    private final int MAX_PLAYERS_PER_GAME = 10; // Maximum players 
    private String type = "Lobby";

    public Lobby() {
        players = new ArrayList<>();
        readyQueue = new ArrayList<>();
        gameController = PlayerType.NOPLAYER; // Initially, no player controls the game
    }

    public synchronized void addPlayer(Player player) {
        // Check if maximum players limit is reached
        if (players.size() >= MAX_PLAYERS_PER_GAME) {
            System.out.println("Maximum players reached. Can't add more players.");
            return;
        }

        // Assign player type
        if (gameController == PlayerType.NOPLAYER) {
            //player.setPlayerType(PlayerType.XPLAYER); // First player becomes X
            setGameController(player);
        } else {
            //player.setPlayerType(PlayerType.OPLAYER); // Others become O
        }

        players.add(player);
    }

    public synchronized void removePlayer(Player player) {
        players.remove(player);
        readyQueue.remove(player);
        //if (gameController.equals(player.getPlayerType())) {
            // If the player being removed is the game controller, assign control to the next player
        //    gameController = getNextGameController();
        //}
    }

    public synchronized Player getPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null; // Player not found
    }

    public synchronized List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public synchronized void addToReadyQueue(Player player) {
        if (!readyQueue.contains(player)) {
            readyQueue.add(player);
        }
        // Start the game if the game controller is ready and there are enough players
        if (isGameControllerReady() && readyQueue.size() >= MAX_PLAYERS_PER_GAME) {
            startGame();
        }
    }

    public synchronized void removeFromReadyQueue(Player player) {
        readyQueue.remove(player);
    }

    public synchronized List<Player> getReadyQueue() {
        return new ArrayList<>(readyQueue);
    }

    public synchronized int getReadyQueueSize(){
        return readyQueue.size();
    }

    public synchronized void startGame() {
        // Logic to start the game
    }

    private synchronized void setGameController(Player player) {
        //gameController = player.getPlayerType();
    }

    private synchronized PlayerType getNextGameController() {
        //for (Player player : players) {
            //if (player.getPlayerType() != gameController) {
            //    return player.getPlayerType();
        //    }
        //}
        //return PlayerType.NOPLAYER; // If no player remains, set game controller to NOPLAYER
        return null;
    }

    private synchronized boolean isGameControllerReady() {
        //for (Player player : players) {
            //if (player.getPlayerType() == gameController && !readyQueue.contains(player)) {
            //    return false; // Game controller is not ready
            //}
        //}
        return true; // Game controller is ready
    }

    public synchronized boolean isFull() {
        return players.size() >= MAX_PLAYERS_PER_GAME;
    }
}
