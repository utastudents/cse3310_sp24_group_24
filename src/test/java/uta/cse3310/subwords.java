package uta.cse3310;

import java.io.*;
import java.util.*;

public class subwords {
    public static void main(String[] args){

        String copy,filename;
        filename="words.txt";
        List<String> wordlist = new  ArrayList<>();
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

        List<String> filtered =new ArrayList<>();
        for( String p  : wordlist){

            for(int k=0; k<p.length(); k++){
                for(int l=0; l<p.length()-k;l++){
                    String sub = p.substring(l,l+k+1);
                    if(!(sub.equals(p))){
                        if(wordlist.contains(sub)){
                            if(!(filtered.contains(sub))){
                                filtered.add(sub);
                            }
                        }
                    }
                }
                    
            }
        }

        for(String r : filtered){
            System.out.println(r);
        }


    }
}
