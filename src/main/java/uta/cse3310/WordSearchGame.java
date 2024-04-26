package uta.cse3310;
import java.util.ArrayList;

public class WordSearchGame{
    public PlayerType Players;
    public Bank listOfWords; //all words in grid
    public ArrayList<String> wordsToFind = new ArrayList<String>(); //list of words that are left to find
    public String word = ""; //the word user has formed by clicking
    public String[] Msg;
    public Statistics S; 
    public int numOfWords;

    public WordSearchGame(Statistics sta){
        S = sta;
        Msg = new String[2];
        //S.GamesInProgress++; 
        Players = PlayerType.XPLAYER;
        numOfWords = wordsToFind.size();
        Msg[0] = "Waiting for other player to join";
        Msg[1] = "";
    }

    public int PlayerToIdx(PlayerType P) {
        int retval = 0;
        if (P == PlayerType.XPLAYER) {
            retval = 0;
        } else {
            retval = 1;
        }
        return retval;
    }
    public void wordfound(UserEvent U){
        //if word user formed are in the words left to find\
        if (U.PlayerIdx == PlayerType.OPLAYER) {
            if(wordsToFind.contains(word)){
                wordsToFind.remove(word); //if found remove word from words to find
                numOfWords--;
                Msg[0] = numOfWords + "words left to find!";
                Msg[1] = numOfWords + "words left to find!";
                //update score
            }
        } else {
            if(wordsToFind.contains(word)){
                wordsToFind.remove(word); //if found remove word from words to find
                numOfWords--;
                Msg[0] = numOfWords + "words left to find!";
                Msg[1] = numOfWords + "words left to find!";
                //update score
            }
        }
        //if no more words left to find
        if(wordsToFind.size() == 0){
            //game ends, declare winner and exit back to main page
            Msg[0] = "Game Over!";
            Msg[1] = "Game Over!";
        }

    

    }
}