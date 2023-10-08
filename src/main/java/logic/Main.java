package logic;

import GUI.MainGUI;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*SwingUtilities SwingUtilities = null;
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });*/

        ListaCanciones listaCanciones = new ListaCanciones();
        // Cargar el archivo desde la carpeta resources
        InputStream inputStream = Main.class.getResourceAsStream("/canciones.txt");

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String linea;

                while ((linea = reader.readLine()) != null) {
                    // Divide la línea en partes separadas por comas
                    String[] partes = linea.split(",");

                    if (partes.length == 3) {
                        // Crea un objeto Cancion con los datos de la línea
                        String nombre = partes[0].trim();
                        String artista = partes[1].trim();
                        String emocion = partes[2].trim();

                        Cancion cancion = new Cancion(nombre, artista, emocion);

                        // Agrega la canción a la lista
                        listaCanciones.agregarCancion(cancion);
                    } else {
                        System.out.println("Error en el formato de la línea: " + linea);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Ahora tienes la lista de canciones cargada desde el archivo en resources
            // Puedes trabajar con la listaCanciones según tus necesidades.
        } else {
            System.err.println("No se pudo encontrar el archivo en resources.");
        }

        Scanner scanner = new Scanner(System.in);
        LinkedList userList = new LinkedList();

        System.out.println("FEEL ME");
        System.out.println("¡Bienvenido¡");
        System.out.println("1. Ingresar");
        System.out.println("2. Registrarse");
        int choice = scanner.nextInt();
        String user;

        while (true) {
            if (choice == 2) {
                System.out.print("Nombre de usuario: ");
                String nombreUsuario = scanner.next();
                System.out.print("Edad: ");
                int edad = scanner.nextInt();
                System.out.print("Correo Electrónico: ");
                String correoElectronico = scanner.next();
                System.out.print("Contraseña: ");
                String newPassword = scanner.next();

                Usuario newUser = new Usuario(nombreUsuario, edad, correoElectronico, newPassword);
                userList.insert(newUser);
                System.out.println("Usuario registrado con éxito.");
                choice = 1;
            } else {
                System.out.print("Ingresa tu usuario: ");
                user = scanner.next();
                System.out.print("Ingresa tu contraseña: ");
                String password = scanner.next();
                if (userList.registeredUser(user, password)) {
                    break;
                } else {
                    System.out.println("Usuario o contraseña incorrecta. Intenta nuevamente");
                }
            }
        }

        System.out.println("Bienvenido, " + user);
        Usuario thisUser = userList.searchUser(user);
        Notification notification = new Notification(2, 1);
        notification.sendNotification(thisUser, listaCanciones);

    }
}