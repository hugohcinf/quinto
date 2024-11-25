package chat;

/**
 *
 * @author Hugo Kart
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int port = 2483;
    private final List<ClientHandler> clients = new ArrayList<>();
    private ServerGUI serverGUI;
    private ServerSocket serverSocket;
    private boolean running = false;

    public Server() {
        this.serverGUI = new ServerGUI(this);
    }

    public static void main(String[] args) {
        new Server().startServer();
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            serverGUI.displayMessage("Servidor corriendo... en el puerto: " + port);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                serverGUI.displayMessage("Cliente conectado");

                ClientHandler clientHandler = new ClientHandler(clientSocket, this, serverGUI);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            if (running) {
                serverGUI.displayMessage("Error en el servidor: " + e.getMessage());
            } else {
                serverGUI.displayMessage("Servidor detenido.");
            }
        }
    }

    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            for (ClientHandler client : clients) {
                client.closeConnection();
            }
            clients.clear();
            serverGUI.displayMessage("Servidor detenido.");
        } catch (IOException e) {
            serverGUI.displayMessage("Error al detener el servidor: " + e.getMessage());
        }
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
        serverGUI.displayMessage("Mensaje enviado a todos: " + message);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
        serverGUI.updateClientList(getClientUsernames());
    }

    public List<String> getClientUsernames() {
        List<String> usernames = new ArrayList<>();
        for (ClientHandler client : clients) {
            usernames.add(client.getUsername());
        }
        return usernames;
    }
}

