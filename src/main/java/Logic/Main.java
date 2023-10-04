package Logic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
        Notification notification = new Notification(2,1);
        notification.sendNotification(); // Start the notification process.

        Scanner scanner = new Scanner(System.in);
        LinkedList userList = new LinkedList();

        while (true) {
            System.out.println("Registro de nuevos usuarios uwu");
            System.out.println("1. Registrar un nuevo usuario");
            System.out.println("2. Mostrar todos los usuarios registrados");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nombre de usuario: ");
                    String nombreUsuario = scanner.next();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    System.out.print("Correo Electrónico: ");
                    String correoElectronico = scanner.next();
                    System.out.print("Contraseña: ");
                    String password = scanner.next();
                    System.out.print("¿Como te sientes?: ");
                    String estado = scanner.next();

                    Usuario newUser = new Usuario(nombreUsuario, edad, correoElectronico, password, estado);
                    userList.insert(newUser);
                    System.out.println("Usuario registrado con éxito.");
                    break;
                case 2:
                    userList.print();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
