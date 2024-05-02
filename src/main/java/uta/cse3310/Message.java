package uta.cse3310;

import java.util.List;

public class Message {
    private String type;
    private String message;
    private char[][] grid;
    private List<String> WordsUsed;
    private int FoundWordIndex;
    private String sender;
    private String content;
    private Game game;
    private Player player1;
    private Player player2;
    private int cIndex1;
    private int rIndex1;
    private int cIndex2;
    private int rIndex2;
    private String FindersName;
    private int gameID;


    public Message() {
        this.type = "Basic";
        String content;
    }

    
    public Message(String sender, String content) {
        this.type = "ChatMessage";
        this.sender = sender;
        this.content = content;
    }

    //Grid Message
    public Message(char[][] grid){
        this.type = "Grid";
        this.grid = grid;
    }

    //Word Bank Message
    public Message(List<String> WordsUsed){
        this.type = "WordBank";
        this.WordsUsed = WordsUsed;
    }

    //Found Word
    public Message(int FoundWordIndex, Player player1, Player player2, int cIndex1, int rIndex1, int cIndex2, int rIndex2, String FindersName, List<String> WordsUsed){
        this.type = "FoundWord";
        this.FoundWordIndex = FoundWordIndex;
        this.player1 = player1;
        this.player2 = player2;
        this.cIndex1 = cIndex1;
        this.rIndex1 = rIndex1;
        this.cIndex2 = cIndex2;
        this.rIndex2 = rIndex2;
        this.FindersName = FindersName;
        this.WordsUsed = WordsUsed;
    }

    public Message(Player player1, Player player2, char[][] grid, List<String> WordsUsed, int gameID){
        this.type = "Game";
        this.player1 = player1;
        this.player2 = player2;
        this.grid = grid;
        this.WordsUsed = WordsUsed;
        this.gameID = gameID;
    }
    
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
