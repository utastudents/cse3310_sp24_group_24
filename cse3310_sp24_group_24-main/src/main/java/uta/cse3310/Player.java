package uta.cse3310;

public class Player {
    
    private int connectionID;
    private String PlayerName;
    private int PlayerPoints;
    private String PlayerColor;

    //Constructor
    public Player(int connectionID, String PlayerName, int PlayerPoints, String PlayerColor){
        this.connectionID = connectionID;
        this.PlayerName = PlayerName;
        this.PlayerPoints = PlayerPoints;
        this.PlayerColor = PlayerColor;
    }

    //Getter for player name
    public String getName(){

        return "players name";
    }

    //Getter for player points
    public int getPoints(){

        return 1;
    }
    //Getter for player color
    public String getColor(){

        return "player color";
    }
}
