package chat;

/**
 *
 * @author Hugo Kart
 */

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server server;
    private final ServerGUI serverGUI;
    private BufferedReader input;
    private PrintWriter output;
    private String username;

    public ClientHandler(Socket clientSocket, Server server, ServerGUI serverGUI) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.serverGUI = serverGUI;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            // Leer nombre de usuario al conectarse
            output.println("Ingrese su nombre de usuario:");
            username = input.readLine();
            serverGUI.displayMessage(username + " se ha conectado.");
            server.broadcastMessage(username + " se ha unido al chat.");
            serverGUI.updateClientList(server.getClientUsernames());

            String message;
            while ((message = input.readLine()) != null) {
                server.broadcastMessage(username + ": " + message);
            }
        } catch (IOException e) {
            serverGUI.displayMessage("Cliente desconectado: " + e.getMessage());
        } finally {
            try {
                closeConnection();
            } catch (IOException e) {
                serverGUI.displayMessage("Error al cerrar conexión: " + e.getMessage());
            }
            server.removeClient(this);
            serverGUI.displayMessage(username + " ha salido.");
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public void closeConnection() throws IOException {
        if (clientSocket != null && !clientSocket.isClosed()) {
            clientSocket.close();
        }
    }

    public String getUsername() {
        return username;
    }
}