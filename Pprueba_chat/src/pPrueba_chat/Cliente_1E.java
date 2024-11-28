/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pPrueba_chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

/**
 *
 * @author Esme
 */
public class Cliente_1E {
    private static Socket socket;
    private static PrintWriter writer;
    
    public static void main(String[] args) {
        // Solicitar el nombre del usuario
        //username = JOptionPane.showInputDialog(null, "Por favor, escribe tu nombre:");
        final String nombre = JOptionPane.showInputDialog(null, "Por favor, escribe tu nombre:");
      
        JFrame frame = new JFrame("Chat" );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        //frame.setLayout(new BorderLayout());
        
        // Crear el panel con imagen de fondo
        Image fondo = Toolkit.getDefaultToolkit().getImage("C:/Users/Esme/Documents/NetBeansProjects/Pprueba_chat/src/Imagenes/f1.jpg");
        JPanel backgroundPanel = new ImagePanel(fondo);
        backgroundPanel.setLayout(new BorderLayout());

        
        
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Hacer el área de texto no editable
        textArea.setBackground(Color.cyan);
        textArea.setForeground(Color.black);
        textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(textArea); // Agregar scroll
        backgroundPanel.add(scrollPane, BorderLayout.CENTER); // Agregar el área de texto al centro

 
        // Crear el panel para los controles de entrada
        JPanel panel = new JPanel();
        //JPanel panel = new ImagePanel("C:\\Users\\Esme\\Documents\\NetBeansProjects\\Pprueba_chat\\src\\Imagenes\\f1.jpg"); 
        panel.setLayout(new FlowLayout()); // Usar FlowLayout para el panel
        //panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //panel.setBackground(Color.DARK_GRAY);
        
        JTextField textField = new JTextField(30);
        textField.setFont(new Font("Centaur", Font.PLAIN, 18));
        JButton sendButton = new JButton("Enviar");
        sendButton.setBackground(Color.YELLOW);
        sendButton.setForeground(Color.BLACK);
        sendButton.setFont(new Font("Geometr212 BkCn BT", Font.BOLD, 16));
        JButton fileButton = new JButton("Enviar Archivo");
        fileButton.setFont(new Font("Geometr212 BkCn BT", Font.BOLD, 16));
        fileButton.setBackground(Color.MAGENTA);
         
        // Conectar al servidor
        // Intentar conectar al servidor usando ToleranciaFallos
        boolean conectado = ToleranciaFallos.manejarConexion();
        if (conectado) {
            try {
                socket = new Socket("localhost", 2483);
                writer = new PrintWriter(socket.getOutputStream(), true);
            
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No se pudo conectar al servidor.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return; // Salir si no
        }
        // Enviar mensaje
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                writer.println(message);
                //textArea.append("Yo: " + message + "\n");
                textArea.append(nombre + ":" + message + "\n");
                textField.setText("");
            }
        });

        // Enviar archivo
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File fileToSend = fileChooser.getSelectedFile();
                    enviarArchivo(fileToSend);
                }
            }
        });

        // Configurar la interfaz
        // Agregar componentes al panel
        panel.add(textField);
        panel.add(sendButton);
        panel.add(fileButton);  

        // Agregar el panel al marco en la parte inferior
        backgroundPanel.add(panel, BorderLayout.SOUTH);
        frame.add(backgroundPanel);
        frame.setVisible(true);
    }
    
    
    
    // Clase personalizada para el JPanel con imagen de fondo
    static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    

    private static void enviarArchivo(File fileToSend) {
        try (FileInputStream fileInputStream = new FileInputStream(fileToSend);
             OutputStream outputStream = socket.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            System.out.println("Archivo enviado: " + fileToSend.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}