package uta.cse3310;
import java.io.*;
import java.util.*;

public class Bank
{
   private double density,charCount,grid,check;
   private String copy, filename;

   List<String> gamelist = new ArrayList<>();
   Random picker = new Random();

   public Bank(){
    density = 0.67;
    grid= 50 * 50;
    charCount=0;
    check=0;
    
   }

    public String checklength()
    {
        String ok="";
        return ok;
    }

    public void readfile(String file)
    {
        file = filename;
        filename = "words.txt";
        try{
            File wordfile = new File(filename);
            Scanner input = new Scanner (wordfile);
            
            //add words to list
            while(input.hasNextLine()){
                copy = input.next();
                getWords(copy);
            }
            input.close();
        }catch(Exception e){}
    
    }
    
    public List<String> getWords(String x){
        gamelist.add(x);
        return gamelist;
    }

    public List<String> words()
    {
        List<String> usethis = new ArrayList<>();
        while(check<density){
            String oneword = gamelist.get(picker.nextInt(gamelist.size()));
            
            if(!usethis.contains(oneword)){
                usethis.add(oneword);
                for (int i = 0; i < oneword.length(); i++) {
                    if (Character.isLetter(oneword.charAt(i))) {
                        charCount++;
                    }
                }
            }
            //desnity check
            check=charCount/grid;
        }
        return usethis;
    }


}