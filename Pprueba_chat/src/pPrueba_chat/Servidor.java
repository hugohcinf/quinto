/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pPrueba_chat;

/**
 *
 * @author Esme
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
     private final int port = 2483;
    private final List<ClientHandler> clients = new ArrayList<>();
    private ServerGUI serverGUI;
    
    public Servidor(){
        serverGUI=new ServerGUI(this);
    }
    
    
    public static void main(String[] args) {
        new Servidor().startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor corriendo... se usa el puerto: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado");

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
    
    public List<String> getClientUsernames(){
        List<String> usernames=new ArrayList<>();
        for(ClientHandler client : clients){
            usernames.add(client.getUsername());
        }
        return usernames;
    }
}
