package logic;

import java.io.*;
import java.util.Scanner;

public class CheckUp {
    private String user;
    private String password;

    public CheckUp() {
    }

    public CheckUp(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public User logIn() {
        Scanner scanner = new Scanner(System.in);
        User thisUser = null;
        LinkedList userList = new LinkedList();
        uplodingFiles(userList);

        if (getUser() == null && getPassword() == null) {
            System.out.print("Nombre de usuario: ");
            String userName = scanner.next();
            System.out.print("Edad: ");
            int age = scanner.nextInt();
            System.out.print("Correo Electrónico: ");
            String email = scanner.next();
            System.out.print("Contraseña: ");
            String password = scanner.next();
            saveUser(userName, age, email, password);
        } else {
            if (!userList.registeredUser(getUser(), getPassword())) {
                thisUser = logIn();
            } else {
                thisUser = userList.searchUser(user);
            }
        }

        System.out.println("Bienvenido, " + user);
        return thisUser;
    }

    public void uplodingFiles(LinkedList myLinkedList) {
        // Cargar el archivo desde la carpeta resources
        InputStream inputStream = CheckUp.class.getResourceAsStream("/usuarios.txt");

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String linea;

                while ((linea = reader.readLine()) != null) {
                    // Divide la línea en partes separadas por comas
                    String[] partes = linea.split(",");

                    if (partes.length == 4) {
                        // Crea un objeto Cancion con los datos de la línea
                        String user = partes[0].trim();
                        int age = Integer.parseInt(partes[1].trim());
                        String email = partes[2].trim();
                        String password = partes[3].trim();

                        User newUser = new User(user, age, email, password);

                        // Agrega la canción a la lista
                        myLinkedList.insert(newUser);
                    } else {
                        System.out.println("Error en el formato de la línea: " + linea);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("No se pudo encontrar el archivo en resources.");
        }
    }

    public static void saveUser(String user, int age, String email, String password) {
        try {
            // Abre el archivo en modo de escritura y agrega datos al final sin borrar el contenido existente
            FileWriter writer = new FileWriter("usuarios.txt", true);

            // Crea una cadena con los datos separados por comas
            String datos = user + "," + age + "," + email + "," + password + "\n";

            // Escribe la cadena en el archivo
            writer.write(datos);

            // Cierra el archivo
            writer.close();

            System.out.println("Información agregada al archivo con éxito.");
        } catch (IOException e) {
            System.err.println("Error al guardar la información en el archivo: " + e.getMessage());
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}