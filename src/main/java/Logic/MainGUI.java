package Logic;

import com.mycompany.otroprojecttest.Cancion;
import com.mycompany.otroprojecttest.ListaCanciones;
import com.mycompany.otroprojecttest.NodoCancion;
import com.mycompany.otroprojecttest.Notification;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {
    private ListaCanciones lista;

    private JTextField nombreTextField;
    private JTextField artistaTextField;
    private JTextField emocionTextField;
    private final JTextArea listaTextArea;
    private final JButton configuracionButton;

    public MainGUI() {
        lista = new ListaCanciones();

        setTitle("FeelMe");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel artistaLabel = new JLabel("Artista:");
        JLabel emocionLabel = new JLabel("Emoción:");

        nombreTextField = new JTextField();
        artistaTextField = new JTextField();
        emocionTextField = new JTextField();

        JButton agregarButton = new JButton("Agregar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton buscarButton = new JButton("Buscar");
        JButton aleatorioButton = new JButton("Canción Aleatoria");

        listaTextArea = new JTextArea();
        listaTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaTextArea);
        panel.add(scrollPane);

        agregarButton.addActionListener((ActionEvent e) -> {
            String nombre = nombreTextField.getText();
            String artista = artistaTextField.getText();
            String emocion = emocionTextField.getText();
            Cancion cancion = new Cancion(nombre, artista, emocion);
            lista.agregarCancion(cancion);
            actualizarLista();
            limpiarCampos();
        });

        eliminarButton.addActionListener((ActionEvent e) -> {
            String nombre = nombreTextField.getText();
            lista.eliminarCancion(nombre);
            actualizarLista();
            limpiarCampos();
        });

        buscarButton.addActionListener((ActionEvent e) -> {
            String nombre = nombreTextField.getText();
            Cancion cancion = lista.buscarCancion(nombre);
            if (cancion != null) {
                JOptionPane.showMessageDialog(null, "Canción encontrada:\n" +
                        "Nombre: " + cancion.getNombre() + "\n" +
                                "Artista: " + cancion.getArtista() + "\n" +
                                        "Emoción: " + cancion.getEmocion());
            } else {
                JOptionPane.showMessageDialog(null, "Canción no encontrada.");
            }
        });

        aleatorioButton.addActionListener((ActionEvent e) -> {
            Cancion cancionAleatoria = lista.seleccionarCancionAleatoria();
            if (cancionAleatoria != null) {
                JOptionPane.showMessageDialog(null, "Canción Aleatoria Seleccionada:\n" +
                        "Nombre: " + cancionAleatoria.getNombre() + "\n" +
                                "Artista: " + cancionAleatoria.getArtista() + "\n" +
                                        "Emoción: " + cancionAleatoria.getEmocion());
            } else {
                JOptionPane.showMessageDialog(null, "La lista de canciones está vacía.");
            }
        });

        panel.add(nombreLabel);
        panel.add(nombreTextField);
        panel.add(artistaLabel);
        panel.add(artistaTextField);
        panel.add(emocionLabel);
        panel.add(emocionTextField);
        panel.add(agregarButton);
        panel.add(eliminarButton);
        panel.add(buscarButton);
        panel.add(aleatorioButton);
        panel.add(scrollPane);

        add(panel);
        configuracionButton = new JButton("Configuración de Notificaciones");
        panel.add(configuracionButton);

        configuracionButton.addActionListener((ActionEvent e) -> {
            // Abre un nuevo diálogo para configurar las notificaciones
            ConfiguracionDialog dialog = new ConfiguracionDialog(MainGUI.this);
            dialog.setVisible(true);
        });
    }

    private void actualizarLista() {
        StringBuilder listaCanciones = new StringBuilder();
        NodoCancion actual = lista.getPrimero();
        while (actual != null) {
            listaCanciones.append("Nombre: ").append(actual.getCancion().getNombre()).append("\n");
            listaCanciones.append("Artista: ").append(actual.getCancion().getArtista()).append("\n");
            listaCanciones.append("Emoción: ").append(actual.getCancion().getEmocion()).append("\n");
            listaCanciones.append("//////////////////////////\n");
            actual = actual.getSiguiente();
        }
        listaTextArea.setText(listaCanciones.toString()); // Actualizar el JTextArea con la lista
    }

    private void limpiarCampos() {
        nombreTextField.setText("");
        artistaTextField.setText("");
        emocionTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}

class ConfiguracionDialog extends JDialog {
    private JTextField intervaloTextField;
    private final JButton aplicarButton;

    public ConfiguracionDialog(JFrame parent) {
        super(parent, "Configuración de Notificaciones", true);
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

        getContentPane().add(panel);
    }
}
