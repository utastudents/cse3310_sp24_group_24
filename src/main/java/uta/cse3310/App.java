package uta.cse3310;

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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.time.Instant;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends WebSocketServer {

  // All games currently underway on this server are stored in the vector ActiveGames
  private Vector<Game> ActiveGames = new Vector<Game>();
  public List<Player> AllPlayers = new ArrayList<Player>();
  private int GameId = 1;
  private int connectionId = 0;
  private Instant startTime;
  private Statistics stats;

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

    //What number player you are
    connectionId++;

    //Message to show players address connected
    System.out.println("\n" + conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");

    //Create new instance of server event class
    ServerEvent E = new ServerEvent();

    //Search for a game needing a player (existing game)
    Game G = null;

    for (Game i : ActiveGames) 
    {
      if (i.Players == uta.cse3310.PlayerType.XPLAYER) 
      {
        G = i;
        System.out.println("found a match");
      }
    }

    //No matches? Create a new Game.
    if (G == null) 
    {
      G = new Game(stats);
      G.GameId = GameId;
      GameId++;
      //Add the first player
      G.Players = PlayerType.XPLAYER;
      ActiveGames.add(G);
      System.out.println(" creating a new Game");
    } else 
    {
      //Join an existing game
      System.out.println(" not a new game");
      G.Players = PlayerType.OPLAYER;
      G.StartGame();
    }

    //Create an event to go to only the new player
    E.YouAre = G.Players;
    E.GameId = G.GameId;

    // allows the websocket to give us the Game when a message arrives..
    // it stores a pointer to G, and will give that pointer back to us
    // when we ask for it
    conn.setAttachment(G);

    //Turning Java string to Json
    Gson gson = new Gson();
    //Note: only send to the single connection
    String jsonString = gson.toJson(E);
    //conn.send(jsonString);
    
    //Sending Grid to server
    Message GridMessage = new Message(G.grid.WordSearchGrid);
    String GridJSONString = gson.toJson(GridMessage);
    conn.send(GridJSONString);

    //Sending WordBank to server
    Message WordBank = new Message(G.grid.WordsUsed);
    String WordBankJSONString = gson.toJson(WordBank);
    conn.send(WordBankJSONString);
    
    //System.out.println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + connectionId + " "+ escape(jsonString));

    // Update the running time
    stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

    // The state of the game has changed, so lets send it to everyone
    //jsonString = gson.toJson(G);
    //System.out.println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
    //broadcast(jsonString);

  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    System.out.println(conn + " has closed");
    // Retrieve the game tied to the websocket connection
    Game G = conn.getAttachment();
    G = null;
  }

  @Override
  public void onMessage(WebSocket conn, String message) {

    Game G = conn.getAttachment();
    Gson gson = new Gson();

    System.out.println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "-" + " " + escape(message));
    
    //Checking if the incoming message is of player info then making a new player
    if(message.startsWith("PlayerInfo,") == true){
      StringTokenizer string = new StringTokenizer(message,",");
      string.nextToken();
      AllPlayers.add(new Player(connectionId, string.nextToken(), 0, string.nextToken()));
    }

    //Checking if the incoming message is to check a word, if test passes send to server and cross out correct word
    if(message.startsWith("WordCheck") == true)
    {
      int j = 0;
      Boolean Found = false;
      StringTokenizer string = new StringTokenizer(message," ");
      string.nextToken(); 
      String test = string.nextToken();
      for (String i : G.grid.WordsUsedLocations)
      {
        if(test.equals(i)){
          System.out.println("ITS ACTUALLY A WORD!");
          Message FoundWord = new Message(j);
          String FoundWordJSONString = gson.toJson(FoundWord);
          conn.send(FoundWordJSONString);
          Found = true;
          break;
        }
        j++;
      }
      if(Found == false){
        Message FoundWord = new Message(-1);
        String FoundWordJSONString = gson.toJson(FoundWord);
        conn.send(FoundWordJSONString);
      }

    }

    // Bring in the data from the webpage
    // A UserEvent is all that is allowed at this point
    GsonBuilder builder = new GsonBuilder();
    gson = builder.create();
    //UserEvent U = gson.fromJson(message, UserEvent.class);

    // Update the running time
    stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

    // Get our Game Object
    //Game G = conn.getAttachment();
    //G.Update(U);

    // send out the game state every time
    // to everyone
    String jsonString;
    jsonString = gson.toJson(G);

    //System.out.println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
    //broadcast(jsonString);
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message) {
    //System.out.println(conn + ": " + message);
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
