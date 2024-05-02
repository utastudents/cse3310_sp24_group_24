package uta.cse3310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSearchGameTest {

    public static void main(String[] args) {
        //Word list and number of words
        List<String> wordsToFind = new ArrayList<>(Arrays.asList("apple", "banana", "orange"));
        int numOfWords = wordsToFind.size();

        //Player types
        int playerO = 0; // 0 represents O player
        int playerX = 1; // 1 represents X player

        //Words found by players
        String wordFoundByO = "apple";
        String wordFoundByX = "banana";
        String wordNotInList = "grape";

        // Mocking game messages
        String[] Msg = new String[2];
        Msg[0] = "";
        Msg[1] = "";

        //Statistics
        int OWins = 0;
        int XWins = 0;

        //Game
        int gamesInProgress = 0;

        //Testing word found for player O
        if (playerO == 0 && wordsToFind.contains(wordFoundByO)) {
            wordsToFind.remove(wordFoundByO);
            numOfWords--;
            Msg[0] = numOfWords + " words left to find!";
            Msg[1] = numOfWords + " words left to find!";
            OWins++;
        }

        //Check if the values are as expected
        if (numOfWords == 2 && Msg[0].equals("2 words left to find!") && Msg[1].equals("2 words left to find!") && OWins == 1) {
            System.out.println("Test 1 passed");
        } else {
            System.out.println("Test 1 failed");
        }

        //Testing word found for player X
        if (playerX == 1 && wordsToFind.contains(wordFoundByX)) {
            wordsToFind.remove(wordFoundByX);
            numOfWords--;
            Msg[0] = numOfWords + " words left to find!";
            Msg[1] = numOfWords + " words left to find!";
            XWins++;
        }

        //Check if the values are as expected
        if (numOfWords == 1 && Msg[0].equals("1 words left to find!") && Msg[1].equals("1 words left to find!") && XWins == 1) {
            System.out.println("Test 2 passed");
        } else {
            System.out.println("Test 2 failed");
        }

        //Testing word not found in the list
        if (playerO == 0 && wordsToFind.contains(wordNotInList)) {
            wordsToFind.remove(wordNotInList);
            numOfWords--;
            Msg[0] = numOfWords + " words left to find!";
            Msg[1] = numOfWords + " words left to find!";
            OWins++;
        }

        //Check if the values are as expected
        if (numOfWords == 1 && Msg[0].equals("1 words left to find!") && Msg[1].equals("1 words left to find!") && OWins == 1) {
            System.out.println("Test 3 passed");
        } else {
            System.out.println("Test 3 failed");
        }
    }
}