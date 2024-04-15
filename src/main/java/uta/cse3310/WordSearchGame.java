package uta.cse3310;
/*
Grid is array of buttons containing letters
*/
public class WordSearchGame{
    public PlayerType players;
    private Bank listOfWords; //all words in grid
    private ArrayList<String> wordsToFind = new ArrayList<String>(); //list of words that are left to find
    private String word = ""; //the word user has formed by clicking


    public WordSearchGame(){
    //what does this consist of exactly

    }

    public void wordfound(){
        //if word user formed are in the words left to find
        if(wordsToFind.contains(word)){
            //change color background
        }
        wordsToFind.remove(word); //if found remove word from words to find

        //if no more words left to find
        if(wordsToFind.size() == 0){
            //game ends, declare winner and exit back to main page
        }

    }

    public void clickOperation(){
        
    }

}