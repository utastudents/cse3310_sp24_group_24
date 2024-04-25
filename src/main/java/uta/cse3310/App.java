
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


public class App extends WebSocketServer {

  // All games currently underway on this server are stored in
  // the vector ActiveGames
  private Vector<Game> ActiveGames = new Vector<Game>();
  private List<WebSocket> clients = new ArrayList<>();
  private List<Lobby> activeLobbies = new ArrayList<>();


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

    clients.add(conn);

    System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");

    ServerEvent E = new ServerEvent();

    String playerName = handshake.getFieldValue("playerName");

    Player newPlayer = new Player(playerName);

    Lobby lobby = findAvailableLobby();
    if (lobby == null) {
      lobby = createNewLobby();
    }
    lobby.addPlayer(new Player(playerName)); // Add player to the lobby

    // Set the lobby as an attachment to the connection
    conn.setAttachment(lobby);

    // Send lobby information to the connected client
    Gson gson = new Gson();
    conn.send(gson.toJson(lobby));

    // Broadcast lobby information to all clients
    broadcast(gson.toJson(lobby));
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
  public void onMessage(WebSocket conn, String message) {
    System.out.println("Received message from client: " + message);

    // Parse the incoming message to extract sender and content
    Gson gson = new Gson();
    Message receivedMessage = gson.fromJson(message, Message.class);

    // Construct the broadcast message
    String sender = receivedMessage.getSender();
    String content = receivedMessage.getContent();
    Message broadcastMessage = new Message(sender, content);
    String jsonMessage = gson.toJson(broadcastMessage);

    // Broadcast the message to all connected clients
    for (WebSocket client : clients) {
        client.send(jsonMessage);
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
