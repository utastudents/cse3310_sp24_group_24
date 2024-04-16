package uta.cse3310;
import java.util.ArrayList;
/*
Grid is array of buttons containing letters
Words made up of buttons 
*/
public class WordSearchGame{
    public PlayerType players;
    private Bank listOfWords; //all words in grid
    private ArrayList<String> wordsToFind = new ArrayList<String>(); //list of words that are left to find
    private String word = ""; //the word user has formed by clicking

    private boolean foundWord;
    private int xPos; //row button is on
    private int yPos; //col button is on


    public WordSearchGame(){
    //

    }

    public void wordfound(){
        //if word user formed are in the words left to find
        if(wordsToFind.contains(word)){
            
        }
        wordsToFind.remove(word); //if found remove word from words to find

        //if no more words left to find
        if(wordsToFind.size() == 0){
            //game ends, declare winner and exit back to main page
        }

    }

    //user selects word by via clicking operation
    public void clickOperation(){

        
    }
    public void setFoundWord(boolean foundWord) {
		this.foundWord = foundWord;
		if(foundWord){}
			//set color to players color
	}

}