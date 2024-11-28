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
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Servidor server;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final String username;
    
    public ClientHandler(Socket clientSocket, Servidor server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
        this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
        this.username = reader.readLine();
        server.broadcastMessage(username + " se ha unido");
        server.broadcastMessage("USUARIOS: " + server.getClientUsernames());
    }
    
    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.equalsIgnoreCase("BYE")) {
                    break;
                } else if ((message.startsWith("PRIVADO")||(message.startsWith(("privado"))))) {
                    String[] parts = message.split(" ", 3);
                    if (parts.length == 3) {
                        sendPrivateMessage(parts[1], username + " (privado): " + parts[2]);
                    }
                } else {
                    server.broadcastMessage(username + ": " + message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                server.removeClient(this);
                server.broadcastMessage(username + " ya se fue");
                server.broadcastMessage("Usuarios en linea" + server.getClientUsernames());
                System.out.println(server.getClientUsernames());
                //server.broadcastMessage("/users" + server.getClients());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    private void sendPrivateMessage(String recipient, String message) {
        for (ClientHandler client : server.getClients()) {
            if (client.getUsername().equals(recipient)) {
                client.sendMessage(message);
                return;
            }
        }
        sendMessage("No se encuentra el Usuario '" + recipient + "'" );
    }

    public String getUsername() {
        return username;
    }
}
