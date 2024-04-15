package uta.cse3310;

import java.util.*;
import java.io.*;

public class leaderboardtest {

        private String name;
        private int points;

        public leaderboardtest(){
            name="";
            points=0;
        }

        public leaderboardtest(String name, int points){
            this.name= name;
            this.points=points;
        }

        public String getname(){

            return this.name;
        }
        public int getpoints(){

            return this.points;
        }

        public String toString(){
            return this.name + " " + this.points;
        }



        

    public static void main(String[] args){

        leaderboardtest who1= new leaderboardtest("home", 12);
        leaderboardtest who2= new leaderboardtest("rand", 1);
        leaderboardtest who3= new leaderboardtest("and", 3);
        leaderboardtest who4= new leaderboardtest("pants", 188);
        leaderboardtest who5= new leaderboardtest("ants", 55);

        List<leaderboardtest> players = new ArrayList<>();
        players.add(who1);
        players.add(who2);
        players.add(who3);
        players.add(who4);
        players.add(who5);

        Collections.sort(players,new sorting());

        int f=1;
        System.out.println("*******LEADERBOARD*******");
        for (leaderboardtest p: players){
            System.out.println(f+": " + p);
            f++;
        }
    }
}
class sorting implements Comparator<leaderboardtest> {
    @Override
    public int compare(leaderboardtest a, leaderboardtest b) {
        return b.getpoints() - a.getpoints();
    }
}

    

