package chat;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final Server server;
    private final ServerGUI serverGUI;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientHandler(Socket socket, Server server, ServerGUI serverGUI) {
        this.socket = socket;
        this.server = server;
        this.serverGUI = serverGUI;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Solicitar nombre de usuario al cliente
            out.println("Ingrese su nombre de usuario:");
            username = in.readLine();
            serverGUI.displayMessage("Usuario conectado: " + username);
            server.broadcastMessage(username + " se ha unido al chat.");

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                server.broadcastMessage(username + ": " + message);
            }
        } catch (IOException e) {
            serverGUI.displayMessage("Error con el cliente: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            server.removeClient(this);
            server.broadcastMessage(username + " ha salido del chat.");
            serverGUI.displayMessage("Conexión cerrada para: " + username);
        } catch (IOException e) {
            serverGUI.displayMessage("Error al cerrar conexión: " + e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }
}