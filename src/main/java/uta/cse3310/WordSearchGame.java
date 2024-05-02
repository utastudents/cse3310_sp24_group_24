package uta.cse3310;

import java.util.List;
import java.util.ArrayList;

public class WordSearchGame{
    public PlayerType players;
    public Statistics S;
    public List<String> wordsToFind;
    public String word;
    public int numOfWords;
    public String[] Msg;
    Grid G = new Grid();

    WordSearchGame(Statistics sta){
        S = sta;
        S.GamesInProgress++;
        wordsToFind = new ArrayList<>(G.WordsUsed);

        Msg =  new String[2];
        Msg[0] = "Welcome to Word Search Game";
        Msg[1] = "";

    }
    //get words from bank & compare the string to all words
    public void wordfound(UserEvent U){
        //if word user formed are in the words left to find\
        if (U.PlayerIdx == PlayerType.OPLAYER) {
            if(wordsToFind.contains(word)){
                wordsToFind.remove(word); //if found remove word from words to find
                numOfWords--;
                Msg[0] = numOfWords + "words left to find!";
                Msg[1] = numOfWords + "words left to find!";
                //update score
                S.OWins++;
            }
        } else {
            if(wordsToFind.contains(word)){
                wordsToFind.remove(word); //if found remove word from words to find
                numOfWords--;
                Msg[0] = numOfWords + "words left to find!";
                Msg[1] = numOfWords + "words left to find!";
                //update score
                S.XWins++;
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