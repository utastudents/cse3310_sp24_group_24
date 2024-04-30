package uta.cse3310;

import com.google.gson.Gson;

public class Game {

    PlayerType Players;
    public PlayerType CurrentTurn;
    public PlayerType[] Button;
    
    public Grid grid;
    

    public String[] Msg;
    public int GameId;
    public Statistics Stats;

    Game(Statistics s) {
        Stats = s;
        Button = new PlayerType[9];
        
        
        grid = new Grid();
        
        // initialize it
       

        Players = PlayerType.XPLAYER;
        CurrentTurn = PlayerType.NOPLAYER;
        // Shown to the user, 0 is XPLAYER
        // 1 is OPLAYER
        Msg = new String[2];
        //Msg[0] = "Waiting for other player to join";
        //Msg[1] = "";
        Msg[0] = "THE WORD SEARCH GAME";
        Msg[1] = "THE WORD SEARCH GAME";
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
        // System.out.println("The user event is " + U.PlayerIdx + " " + U.Button);

        if ((CurrentTurn == U.PlayerIdx) && (CurrentTurn == PlayerType.OPLAYER || CurrentTurn == PlayerType.XPLAYER)) {
            // Move is legitimate, lets do what was requested

            // Note that a button is going to be set for every UserEvent !

            // Is the button not taken by X or O?
            if (Button[U.Button] == PlayerType.NOPLAYER) {
                // System.out.println("the button was 0, setting it to" + U.PlayerIdx);
                Button[U.Button] = U.PlayerIdx;
                if (U.PlayerIdx == PlayerType.OPLAYER) {
                    CurrentTurn = PlayerType.XPLAYER;
                    Msg[1] = "Other Players Move.";
                    Msg[0] = "Your Move.";
                } else {
                    CurrentTurn = PlayerType.OPLAYER;
                    Msg[0] = "Other Players Move.";
                    Msg[1] = "Your Move.";
                }
            } else {
                Msg[PlayerToIdx(U.PlayerIdx)] = "Not a legal move.";
            }

            // Check for winners, losers, and a draw

         
            }
        }
    

    public void Tick() {
        // this function can be called periodically if a
        // timer is needed.

    }
}
// In windows, shift-alt-F formats the source code
// In linux, it is ctrl-shift-I