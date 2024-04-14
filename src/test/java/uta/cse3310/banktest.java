package uta.cse3310;

import java.io.*;
import java.util.*;

public class banktest {

    public static void main(String[] args){

        //System.out.println("...working...");

        double density,charCount,grid,check;
        density = 0.67;
        grid= 50 * 50;
        List<String> wordlist = new  ArrayList<>();

        String copy,filename;
        filename="words.txt";
        
        //open file
        try{
            File wordfile = new File(filename);
            Scanner input = new Scanner (wordfile);
            
            //add words to list
            while(input.hasNextLine()){
                copy = input.next();
                wordlist.add(copy);
            }
            input.close();
        }catch(Exception e){}

        /*
        for (String t : wordlist){
            System.out.println(t);
        }
         */

        charCount=0;
        check=0;
        Random picker = new Random();
        List<String> newList = new  ArrayList<>();

        //density check
        while(check<density){
            String oneword = wordlist.get(picker.nextInt(wordlist.size()));
            
            //if randomly choosen word already in list, pick new word
            //counting letters of words added.
            if(!newList.contains(oneword)){
                newList.add(oneword);
                for (int i = 0; i < oneword.length(); i++) {
                    if (Character.isLetter(oneword.charAt(i))) {
                        charCount++;
                    }
                }
            }
            //desnity check
            check=charCount/grid;
        }

        /* 
        System.out.println("////////////start of list");
        for(String b : newList){
            System.out.println(b);
        }
        System.out.println("////////////end of list");
       System.out.println("Letter Count: "+ charCount+" , Density ratio: "+check);
        */
       
    }    
}
