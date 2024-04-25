package uta.cse3310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 8080;
    private static final List<String> names = new ArrayList<>();
    private static final List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastNames() {
        for (ClientHandler client : clients) {
            client.sendNames();
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private final PrintWriter out;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String name;
                while ((name = in.readLine()) != null) {
                    System.out.println("Received name from client: " + name);
                    names.add(name);
                    broadcastNames();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendNames() {
            for (String name : names) {
                out.println(name);
            }
        }
    }
}

