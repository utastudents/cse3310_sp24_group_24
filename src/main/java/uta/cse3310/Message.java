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

    public Message() {
        // Default constructor
        String content;
    }

    public Message(Player sender, String content) {
        this.sender = sender.getName();
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
    public Message(int FoundWordIndex){
        this.type = "FoundWord";
        this.FoundWordIndex = FoundWordIndex;

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
