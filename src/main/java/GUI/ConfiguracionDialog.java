package GUI;

import logic.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfiguracionDialog extends JDialog {
    private JTextField intervaloTextField;
    //private final JButton aplicarButton;

    public ConfiguracionDialog(JFrame parent) {
        /*super(parent, "Configuración de Notificaciones", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel intervaloLabel = new JLabel("Intervalo de notificación (minutos):");
        intervaloTextField = new JTextField();
        panel.add(intervaloLabel);
        panel.add(intervaloTextField);

        aplicarButton = new JButton("Aplicar");
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(aplicarButton);

        aplicarButton.addActionListener((ActionEvent e) -> {
            // Obtener el intervalo ingresado por el usuario
            String intervaloStr = intervaloTextField.getText();
            try {
                int intervalo = Integer.parseInt(intervaloStr);
                // Crear una instancia de Notification con el intervalo
                Notification notification = new Notification(intervalo, 1);
                // Iniciar las notificaciones
                notification.sendNotification();
                setVisible(false); // Cierra el diálogo después de aplicar la configuración
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un intervalo válido.");
            }
        });

        getContentPane().add(panel);*/
    }
}
