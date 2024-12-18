/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chat;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
        
/**
 *
 * @author HP
 */
public class Cliente extends javax.swing.JFrame {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    //private BufferedWriter writer;
    private String username;

    /**
     * Creates new form Cliente
     */
    public Cliente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnsal = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        bEnvIma = new javax.swing.JButton();
        bEnArch = new javax.swing.JButton();
        btnsal2 = new javax.swing.JButton();

        btnsal.setBackground(new java.awt.Color(204, 255, 255));
        btnsal.setActionCommand("Salir");
        btnsal.setLabel("Salir");
        btnsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(102, 255, 204));

        btnEnviar.setBackground(new java.awt.Color(204, 255, 255));
        btnEnviar.setText("Boton de Enviar el Mensaje");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setForeground(new java.awt.Color(22, 22, 22));
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMensajeKeyReleased(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        bEnvIma.setBackground(new java.awt.Color(204, 255, 255));
        bEnvIma.setLabel("Enviar Imagen");
        bEnvIma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnvImaActionPerformed(evt);
            }
        });

        bEnArch.setBackground(new java.awt.Color(204, 255, 255));
        bEnArch.setActionCommand("Salir");
        bEnArch.setLabel("Enviar Archivo");
        bEnArch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnArchActionPerformed(evt);
            }
        });

        btnsal2.setBackground(new java.awt.Color(204, 255, 255));
        btnsal2.setActionCommand("Salir");
        btnsal2.setLabel("Salir");
        btnsal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsal2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bEnvIma, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnsal2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(bEnArch, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(bEnvIma)
                    .addComponent(bEnArch)
                    .addComponent(btnsal2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void sendMessage() {
        String message=txtMensaje.getText();
        if (!message.isEmpty()){
            writer.println(message);
            //writer.write(message);
            txtMensaje.setText("");
        }
    }
    
    String serverAddress = JOptionPane.showInputDialog(this, 
    "Ingrese la dirección IP del servidor:", 
    "Conectar al servidor", 
    JOptionPane.QUESTION_MESSAGE);
    
   public void startClient() {
    try {
        // Pedir al usuario la dirección IP del servidor
        String serverAddress = JOptionPane.showInputDialog(this, 
            "Ingrese la dirección IP del servidor:", 
            "Conectar al servidor", 
            JOptionPane.QUESTION_MESSAGE);

        // Si el usuario cancela o no introduce nada, detener la conexión
        if (serverAddress == null || serverAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se proporcionó dirección IP. Cerrando cliente.");
            System.exit(0);
        }

        // Conectar al servidor en el puerto definido (2483)
        socket = new Socket("localHost", 2483);

        // Inicializar el lector y escritor
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);

        // Pedir al usuario un nombre de usuario
        username = JOptionPane.showInputDialog("Escribe tu nombre:");
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se proporcionó un nombre de usuario. Cerrando cliente.");
            System.exit(0);
        }
        writer.println(username);

        // Crear un hilo para leer mensajes del servidor
        new Thread(this::readServerMessages).start();

        // Mostrar la ventana principal del cliente
        java.awt.EventQueue.invokeLater(() -> {
            setTitle(username);
            setVisible(true);
        });

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al conectarse al servidor: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        System.exit(0);
    }
}

    
    private void readServerMessages() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                txtArea.append(message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        //try {
            // TODO add your handling code here:
            //sendMessage();
        //} catch (IOException ex) {
        //    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txtMensajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            sendMessage();
        }
    }//GEN-LAST:event_txtMensajeKeyReleased

    private void btnsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalActionPerformed
        // TODO add your handling code here: 
        System.exit(0);
    }//GEN-LAST:event_btnsalActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
         // Limpia el área de texto y el campo de mensaje
    txtArea.setText(""); // Limpia el JTextArea
    txtMensaje.setText(""); // Limpia el JTextField
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void bEnArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnArchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bEnArchActionPerformed

    private void btnsal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsal2ActionPerformed
        // TODO add your handling code here:
        try {
        if (out != null) {
            out.println("Cliente se desconecta."); // Notifica al servidor
            out.close();
        }
        if (socket != null) {
            socket.close();
        }
    } catch (Exception ex) {
        System.err.println("Error al cerrar conexión: " + ex.getMessage());
    } finally {
        System.exit(0); // Termina el programa
    }
    }//GEN-LAST:event_btnsal2ActionPerformed

    private void bEnvImaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnvImaActionPerformed
        // TODO add your handling code here:
        try {
            String imagePath = null;
        // Suponiendo que imagePath es relativo al directorio de recursos
        Image img = ImageIO.read(getClass().getResource(imagePath));
        ImageIcon icon = new ImageIcon(img);
        // Aquí puedes enviar el icono a través del socket, por ejemplo, serializándolo
        // ...
    } catch (IOException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_bEnvImaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Cliente().setVisible(true);
//                
//            }
//        });
        java.awt.EventQueue.invokeLater(() -> {
            new Cliente().startClient();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bEnArch;
    private javax.swing.JButton bEnvIma;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnsal;
    public javax.swing.JButton btnsal2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables

}
