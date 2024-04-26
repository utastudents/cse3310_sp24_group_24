package uta.cse3310;

public class WordSearchGame{
    public PlayerType players;


    WordSearchGame(){

    }
    
    public void hint(){

    }
<<<<<<< HEAD
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
=======

    public void wordfound(){

    }

    public void hintTimer(){

    }

    public void clickOperation(){
        
    }
>>>>>>> 7803fc1f1cc3ca512bb4b01f39e435109f14d5f1

    

    }
}