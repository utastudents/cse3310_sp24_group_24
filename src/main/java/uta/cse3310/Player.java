package uta.cse3310;

import java.util.List;
import java.util.ArrayList;

public class Player {
    
    private int connectionID;
    private String PlayerName;
    private int PlayerPoints;
    private String PlayerColor;
    private static List<String> playerList = new ArrayList<>();

    //Constructor
    public Player(int connectionID, String PlayerName, int PlayerPoints, String PlayerColor){
        this.connectionID = connectionID;
        this.PlayerName = PlayerName;
        this.PlayerPoints = PlayerPoints;
        this.PlayerColor = PlayerColor;
        playerList.add(PlayerName);
    }

    //Getter for player name
    public String getName(){

        return PlayerName;
    }

    //Getter for player points
    public int getPoints(){

        return 1;
    }
    //Getter for player color
    public String getColor(){

        return "player color";
    }

    //getter for player list
    public static List<String> getPlayerList() {
        return playerList;
    }

    public static void printPlayerList() {
        System.out.println("List of players:");
        for (String PlayerName : playerList) {
            System.out.println(PlayerName);}
        }


}
