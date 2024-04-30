
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
 
 public class App extends WebSocketServer {
 
   // All games currently underway on this server are stored in
   // the vector ActiveGames
   private Vector<Game> ActiveGames = new Vector<Game>();
   private List<WebSocket> clients = new ArrayList<>();
   private List<Lobby> activeLobbies = new ArrayList<>();
 
   private Lobby L;
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
 
     connectionId++;
     Lobby L;
 
     clients.add(conn);
 
     System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
 
     ServerEvent E = new ServerEvent();
 
     E.ConnectionID = connectionId;
     Gson gson = new Gson();
     String jsonString = gson.toJson(E);
     conn.send(jsonString);
     System.out.println("sending " + jsonString);
 
    
 
     // Player newPlayer = new Player(playerName);
 
     // Lobby lobby = findAvailableLobby();
     // if (lobby == null) {
     // lobby = createNewLobby();
     // }
     // lobby.addPlayer(new Player(playerName)); // Add player to the lobby
 
     // Set the lobby as an attachment to the connection
     // conn.setAttachment(lobby);
 
     // Send lobby information to the connected client
     // Gson gson = new Gson();
     // String jsonString = gson.toJson(lobby);
     // conn.send(jsonString);
     // System.out.println("Sending "+jsonString);
 
     // Broadcast lobby information to all clients
     // broadcast(gson.toJson(lobby));
   }
 
   @Override
   public void onClose(WebSocket conn, int code, String reason, boolean remote) {
     System.out.println(conn + " has closed");
     Lobby lobby = conn.getAttachment();
     if (lobby != null) {
       Player player = lobby.getPlayerByName(conn.getAttachment()); // Retrieve player from the lobby
       if (player != null) {
         lobby.removePlayer(player); // Remove player from the lobby
         clients.remove(conn);
       }
     }
 
   }
 
   @Override
   public void onMessage(WebSocket conn, String message) 
   {
     Game G = new Game(stats);
     System.out.println("Received message from client: " + message);
 
     // Parse the incoming message to extract sender and content
     Gson gson = new Gson();
     
 
     // broadcast the recieved message
     // broadcast(message);
     // Player newPlayer = new Player(playerName);
 
     // Add player to the lobby
 
     // Set the lobby as anttachment to the connection
     // conn.setAttachment(lobby);
 
     // Send lobby information to the connected client
     // Gson gson = new Gson();
     // String jsonString = gson.toJson(lobby);
     // conn.send(jsonString);
     // System.out.println("Sending "+jsonString);
 
     // Broadcast lobby information to all clients
     // broadcast(gson.toJson(lobby));
     /*
     if (false) {
       NewNameEvent N = gson.fromJson(message, NewNameEvent.class);
       // Lobby lobbys = conn.getAttachment();
       // Lobby lobby = findAvailableLobby();
       if (L == null) {
         L = createNewLobby();
       }
       Player player = new Player(N.ConnectionID, N.playerName, 0, "");
       L.addPlayer(player);
 
       Message mO = gson.fromJson(message, Message.class);
       String sender = mO.getSender();
       String content = mO.getContent();
       System.out.println(sender + " : " + content);
       if ("playerNick".equals(mO.getContent()));
       {
         String chatMessage = sender + ":" + content;
         String jsonMessage = gson.toJson(new Message(player, content));
         broadcast(chatMessage);
         // broadcast(message);
       }
     }
       */
     
     // if (message.contains("content")) {
     // Use JSON parsing to extract sender and content
 
     // Message messageObject = gson.fromJson(message, Message.class);
 
     // Create the chat string
     // String chatstring = message.sender + ": " + message.content;
 
     // Broadcast the chat string
     // broadcast(message);
 
     // }
 
     if (L == null) {
       L = createNewLobby();
    
     }
 
     //Checking if the message is a newPlayer message
     if (message.indexOf("newPlayer") > 0) {
       
       // Received message from client: {"type":"newPlayer","playerName":"aaaa","ConnectioID":1}
       NewNameEvent N = gson.fromJson(message,NewNameEvent.class);
       System.out.println("The name is: " + N.playerName + ", The Connection ID is: " + N.ConnectionID + ", The color is: " + N.playerColor) ;

       Leaderboard LeadB = new Leaderboard();
       Player P = new Player(N.ConnectionID,N.playerName,0,N.playerColor);
       L.addPlayer(P);
       LeadB.addPlayer(P);

 
       // since the lobby has changed, let's send it out to everyone
   
       String jsonString = gson.toJson(L);
       String Lead_jsonString = gson.toJson(LeadB);
       //conn.send(jsonString);
       broadcast(jsonString);
       broadcast(Lead_jsonString);
       // Player.printPlayerList();
       // broadcastPlayerList();
       // }
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
         G.StartGame();
 
         //THIS PART NEEDS ADJUSTMENTS
         
 
         Message GridMessage = new Message(G.grid.WordSearchGrid);
         String GridJSONString = gson.toJson(GridMessage);
         conn.send(GridJSONString);
 
         //Sending WordBank to server
         Message WordBank = new Message(G.grid.WordsUsed);
         String WordBankJSONString = gson.toJson(WordBank);
         conn.send(WordBankJSONString);
       }
     }
   
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
   }
   // private void broadcastPlayerList() {
   // List<String> playerNames = Player.getPlayerList();
   // Gson gson = new Gson();
   // String jsonPlayerList = gson.toJson(playerNames);
   // JsonObject broadcastMessage = new JsonObject();
   // broadcastMessage.addProperty("type", "playerList");
   // broadcastMessage.add("players", gson.toJsonTree(playerNames));
 
   // Send the player list to all connected clients
   // for (WebSocket client : clients) {
   // client.send(broadcastMessage.toString());
   // }
   // }
   // broadcast(jsonPlayerList); // Send the player list to all connected clients
 
   // Construct the broadcast message
   // String sender = message.getSender();
   // String content = message.getContent();
 
   // Broadcast the message to all connected clients
   /*
    * public void broadcast(String message)
    * {
    * //Message broadcastMessage = new Message(message.sender, message.content);
    * //String jsonMessage = gson.toJson(broadcastMessage);
    * //System.out.println("broadcast " + jsonMessage);
    * //broadcast(jsonMessage);
    * for (WebSocket client : clients) {
    * client.send(message);
    * }
    * }
    */
 
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
 
   private Lobby findAvailableLobby() {
     for (Lobby lobby : activeLobbies) {
       if (!lobby.isFull()) {
         return lobby;
       }
     }
     return null;
   }
 
   private Lobby createNewLobby() {
     Lobby lobby = new Lobby();
     activeLobbies.add(lobby);
     return lobby;
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