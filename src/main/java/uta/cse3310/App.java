
// This is example code provided to CSE3310 Fall 2022
// You are free to use as is, or changed, any of the code provided

// Please comply with the licensing requirements for the
// open source packages being used.

// This code is based upon, and derived from the this repository
//            https:/thub.com/TooTallNate/Java-WebSocket/tree/master/src/main/example

// http server include is a GPL licensed package from
//            http://www.freeutils.net/source/jlhttp/

/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package uta.cse3310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.time.Instant;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.*;

public class App extends WebSocketServer {

  // All games currently underway on this server are stored in
  // the vector ActiveGames
  private Vector<Game> ActiveGames = new Vector<Game>();
  private List<WebSocket> clients = new ArrayList<>();
  private List<Lobby> activeLobbies = new ArrayList<>();
  Gson gson = new Gson();
  public Lobby L = new Lobby();
  
  private int connectionId = 0;

  private Instant startTime;

  private Statistics stats;
   // Version number for the project. For project testing and to meet a requirement for grading.
   //String version = getVersion();
  //public String versionNumber = System.getenv("VERSION");


  public App(int port) {
    super(new InetSocketAddress(port));

  }

  public App(InetSocketAddress address) {
    super(address);
  }

  public App(int port, Draft_6455 draft) {
    super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
  }

  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake) {

    connectionId++;
    
    //conn.setAttachment(L);
  
    clients.add(conn);

    System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");

    ServerEvent E = new ServerEvent();

    E.ConnectionID = connectionId;
    
    String jsonString = gson.toJson(E);
    conn.send(jsonString);
    System.out.println("sending " + jsonString);

    
    // Add version next to title
    String versionNumber = System.getenv("VERSION");
    Message typehash = new Message(versionNumber);
    String versionJSONString = gson.toJson(typehash);
    broadcast(versionJSONString);
    // Some stuff here that converts from GSON to JSON and send it over to the HTML

  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    System.out.println(conn + " has closed");

    if (L != null) {
      Player player = conn.getAttachment(); // Retrieve player from the lobby
      System.out.println(player.getName() + " has disconnected");
      if (player != null) {
        L.removePlayer(player); // Remove player from the lobby
        clients.remove(conn);
        String jsonString = gson.toJson(L);
        broadcast(jsonString);
      }
    }
    for(Game i : ActiveGames){
      //WIP
      //will check if the name of the leaver was in an active game 
      //THIS WILL DELETE THE GAME ROOM IF SOMEONE LEAVES AND SEND THEM BACK TO LOBBY
      System.out.println(i);
    }
  }

  @Override
  public void onMessage(WebSocket conn, String message) 
  {
    //Print out received message
    System.out.println("Received message from client: " + message);

    // Parse the incoming message to extract sender and content
    Gson gson = new Gson();


    String versionNumber = System.getenv("VERSION");
    Message typehash = new Message(versionNumber);
    String versionJSONString = gson.toJson(typehash);
    broadcast(versionJSONString);
    
    if (message.startsWith("ChatMessage") == true){
      StringTokenizer string = new StringTokenizer(message,"|");
      string.nextToken(); //Get rid of ChatMessage label
      String sender = string.nextToken();
      String content = string.nextToken();
      Message ChatMessage = new Message(sender, content);
      String ChatMessageJSONString = gson.toJson(ChatMessage);
      broadcast(ChatMessageJSONString);
    }

    if (message.startsWith("Leaderboard") == true){
      StringTokenizer string = new StringTokenizer(message," ");
      string.nextToken(); //Get rid of Leaderboard lable
      String PlayerName = string.nextToken();
      for(Player i : L.players){
        if(PlayerName.equals(i.getName())){
          i.setPoints(1);
        }
      }
      String jsonString = gson.toJson(L);
      broadcast(jsonString);
    }

    //Checking if the message is a newPlayer message
    if (message.indexOf("newPlayer") > 0) {
      
      // Received message from client: {"type":"newPlayer","playerName":"aaaa","ConnectioID":1}
      NewNameEvent N = gson.fromJson(message,NewNameEvent.class);
      System.out.println("The name is: " + N.playerName + ", The Connection ID is: " + N.ConnectionID + ", The color is: " + N.playerColor) ;
      Player P = new Player(N.ConnectionID,N.playerName,0,N.playerColor);
      conn.setAttachment(P);
      L.addPlayer(P);

      // since the lobby has changed, let's send it out to everyone
      String jsonString = gson.toJson(L);
      broadcast(jsonString);
    
    }

    //Checking if the message is a player readying up
    if (message.indexOf("readyPlayer") > 0) {
      NewNameEvent N = gson.fromJson(message,NewNameEvent.class);
      L.addToReadyQueue(L.getPlayerByName(N.playerName));
      
      // since the lobby has changed, let's send it out to everyone
  
      String jsonString = gson.toJson(L);
      broadcast(jsonString);
    }

    if (message.startsWith("GamePlayer") == true) {
      StringTokenizer string = new StringTokenizer(message," ");
      string.nextToken();
      String name1 = string.nextToken();
      String name2 = string.nextToken();

      if(L.getReadyQueueSize() < 2)
      {
        //ADD SOMETHING HERE TO DISPLAY NOT ENOUGH READY ON HTML
        System.out.println("not enough to start");
      }else{
        L.removeFromReadyQueue(L.getPlayerByName(name1));
        L.removeFromReadyQueue(L.getPlayerByName(name2));
        String jsonString = gson.toJson(L);
        broadcast(jsonString);
        Game G = new Game(L.getPlayerByName(name1), L.getPlayerByName(name2));
        conn.setAttachment(G);
        ActiveGames.add(G);
        Message GameMessage = new Message(G.player1, G.player2, G.grid.WordSearchGrid, G.grid.WordsUsed, ActiveGames.size());
        String GameJSONString = gson.toJson(GameMessage);
        broadcast(GameJSONString);
      }
    }
  
    if(message.startsWith("WordCheck") == true)
    {
      int j = 0;
      Boolean Found = false;
      StringTokenizer string = new StringTokenizer(message," ");
      string.nextToken(); 
      String name = string.nextToken();
      int gameNumber = Integer.parseInt(string.nextToken());
      Game G = ActiveGames.get(gameNumber - 1);
      String test = string.nextToken();
      

      for (String i : G.grid.WordsUsedLocations)
      {
        if(test.equals(i)){
          System.out.println("ITS ACTUALLY A WORD!");
          Message FoundWord = new Message(j, G.player1, G.player2, Integer.parseInt(string.nextToken()), Integer.parseInt(string.nextToken()), Integer.parseInt(string.nextToken()), Integer.parseInt(string.nextToken()), name, G.grid.WordsUsed);
          String FoundWordJSONString = gson.toJson(FoundWord);
          broadcast(FoundWordJSONString);
          Found = true;
          break;
        }
        j++;
      }
      if(Found == false){
        Message FoundWord = new Message(-1, G.player1, G.player2,Integer.parseInt(string.nextToken()), Integer.parseInt(string.nextToken()), Integer.parseInt(string.nextToken()), Integer.parseInt(string.nextToken()), name, G.grid.WordsUsed);
        String FoundWordJSONString = gson.toJson(FoundWord);
        broadcast(FoundWordJSONString);
      }
  }
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message) {
    System.out.println(conn + ": " + message);
  }

  @Override
  public void onError(WebSocket conn, Exception ex) {
    ex.printStackTrace();
    if (conn != null) {
      // some errors like port binding failed may not be assignable to a specific
      // websocket
    }
  }

  @Override
  public void onStart() {
    setConnectionLostTimeout(0);
    stats = new Statistics();
    startTime = Instant.now();
  }
/* 
  public String getVersion() {
    return versionNumber;
  }*/

  private String escape(String S) {
    // turns " into \"
    String retval = new String();
    // this routine is very slow.
    // but it is not called very often
    for (int i = 0; i < S.length(); i++) {
      Character ch = S.charAt(i);
      if (ch == '\"') {
        retval = retval + '\\';
      }
      retval = retval + ch;
    }
    return retval;
  }

  public static void main(String[] args) {

    // Set up the http server
    int port = 9024;
    HttpServer H = new HttpServer(port, "./html");
    H.start();
    System.out.println("http Server started on port: " + port);

    // create and start the websocket server

    port = 9124;
    App A = new App(port);
    A.setReuseAddr(true);
    A.start();
    System.out.println("websocket Server started on port: " + port);

  }
}
