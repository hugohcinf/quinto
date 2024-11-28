/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pPrueba_chat;

/**
 *
 * @author Esme
 */

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ServerGUI extends JFrame {
    private Servidor server;
    private DefaultListModel<String> connectedClientsModel;

    public ServerGUI(Servidor server) {
        this.server = server;
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Servidor del chat");
        pack();
        setVisible(true);
    }

    private void initComponents() {
        connectedClientsModel = new DefaultListModel<>();
        JList<String> connectedClientsList = new JList<>(connectedClientsModel);
        JScrollPane scrollPane = new JScrollPane(connectedClientsList);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void updateConnectedClients(List<String> usernames) {
        SwingUtilities.invokeLater(() -> {
            connectedClientsModel.clear();
            connectedClientsModel.addAll(usernames);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServerGUI(new Servidor()));
    }
}
