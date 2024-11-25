package chat;

/**
 *
 * @author Hugo Kart
 */

 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI {
    private final Server server;
    private JFrame frame;
    private JTextArea logArea;
    private JList<String> clientList;
    private DefaultListModel<String> clientListModel;
    private JButton stopButton;

    public ServerGUI(Server server) {
        this.server = server;
        initGUI();
    }

    private void initGUI() {
        frame = new JFrame("Servidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        frame.add(new JScrollPane(logArea), BorderLayout.CENTER);

        clientListModel = new DefaultListModel<>();
        clientList = new JList<>(clientListModel);
        frame.add(new JScrollPane(clientList), BorderLayout.EAST);

        stopButton = new JButton("Detener Servidor");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
                stopButton.setEnabled(false);
            }
        });
        frame.add(stopButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }

    public void updateClientList(java.util.List<String> usernames) {
        SwingUtilities.invokeLater(() -> {
            clientListModel.clear();
            for (String username : usernames) {
                clientListModel.addElement(username);
            }
        });
    }
}
