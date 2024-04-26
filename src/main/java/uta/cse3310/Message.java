package uta.cse3310;

import java.util.List;

public class Message {
    public String type;
    public String message;
    public char[][] grid;
    public List<String> WordsUsed;
    public int FoundWordIndex;

    //Basic Message
    public Message(String message){
        this.type = "message";
        this.message = message;
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

    
}
