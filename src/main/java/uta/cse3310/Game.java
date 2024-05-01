package uta.cse3310;

import com.google.gson.Gson;

public class Game {

    public Player player1;
    public Player player2;
    
    public Grid grid;
    public int GameId = 0;
    
    public Message GridMessage;
    public String GridJSONString;
    public Message WordBank;
    public String WordBankJSONString;

    public String ServerString;

    Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        //Create the grid instance
        grid = new Grid();
        Gson gson = new Gson();
        GameId++;

        Message GridMessage = new Message(grid.WordSearchGrid);
        //String GridJSONString = gson.toJson(GridMessage);
        
        Message WordBank = new Message(grid.WordsUsed);
        //String WordBankJSONString = gson.toJson(WordBank); 
        
        String ServerString = player1.toString();
    }

    public void SetBoard(PlayerType p, int[] b) {
        

    }

    public void StartGame() {
        
    }

  

    // This function returns an index for each player
    // It does not depend on the representation of Enums
    public int PlayerToIdx(PlayerType P) {
        int retval = 0;
        if (P == PlayerType.XPLAYER) {
            retval = 0;
        } else {
            retval = 1;
        }
        return retval;
    }

    public void Update(UserEvent U) {

    }
    public void Tick() {
        // this function can be called periodically if a
        // timer is needed.

    }
}
// In windows, shift-alt-F formats the source code
// In linux, it is ctrl-shift-I