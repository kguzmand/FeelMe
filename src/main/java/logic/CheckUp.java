package logic;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CheckUp {
    private String user, password, email;

    private int age;

    public CheckUp(String user, String email, int age, String password) {
        this.user = user;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public CheckUp(String user, String password) {
        this.user = user;
        this.password = password;
        this.email = null;
        this.age = 0;
    }

    public User logIn() {
        Scanner scanner = new Scanner(System.in);
        User thisUser = null;
        LinkedList userList = new LinkedList();
        uplodingFiles(userList);

        if (getEmail() != null && getAge() != 0) {
            thisUser = new User(getUser(), getAge(), getEmail(), getPassword());
            userList.insert(thisUser);
            saveUser(getUser(), getAge(), getEmail(), getPassword());
        } else {
            if (!userList.registeredUser(getUser(), getPassword())) {
                thisUser = logIn();
            } else {
                thisUser = userList.searchUser(user);
            }
        }

        System.out.println("Bienvenido, " + thisUser.getNombreUsuario());
        return thisUser;
    }

    public void uplodingFiles(LinkedList myLinkedList) {
        // Ruta absoluta al archivo "usuarios.txt" fuera del JAR/WAR
        String filePath = "usuarios.txt";

        try {
            Path path = Paths.get(filePath);

            if (Files.exists(path)) {
                // Leer todas las líneas del archivo
                BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
                String linea;

                while ((linea = reader.readLine()) != null) {
                    // Divide la línea en partes separadas por comas
                    String[] partes = linea.split(",");

                    if (partes.length == 4) {
                        // Crea un objeto User con los datos de la línea
                        String user = partes[0].trim();
                        int age = Integer.parseInt(partes[1].trim());
                        String email = partes[2].trim();
                        String password = partes[3].trim();

                        User newUser = new User(user, age, email, password);

                        // Agrega el usuario a la lista
                        myLinkedList.insert(newUser);
                    } else {
                        System.out.println("Error en el formato de la línea: " + linea);
                    }
                }

                reader.close();
            } else {
                System.err.println("No se pudo encontrar el archivo: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }

    public int getAge() { return age; }
}