package controller;

import logic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Logic {
    private ListaCanciones listaCanciones;
    private Notification notification;
    private Usuario user;

    public Logic() {
    }

    public Logic(ListaCanciones listaCanciones, Notification notification, Usuario user) {
        this.listaCanciones = listaCanciones;
        this.notification = notification;
        this.user = user;
    }

    public void scheduleNotification(){
        //notification.setChoice(choice);
        loadSongs();
        notification.sendNotification(user, listaCanciones);
    }

    public void loadSongs(){
        // Cargar el archivo desde la carpeta resources
        InputStream inputStream = Main.class.getResourceAsStream("/canciones.txt");

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String linea;

                while ((linea = reader.readLine()) != null) {
                    // Divide la línea en partes separadas por comas
                    String[] partes = linea.split(",");

                    if (partes.length == 5) {
                        // Crea un objeto Cancion con los datos de la línea
                        String nombre = partes[0].trim();
                        String artista = partes[1].trim();
                        String emocion = partes[2].trim();
                        String ruta = partes[3].trim();
                        int popularidad = Integer.parseInt(partes[4].trim());

                        Cancion cancion;
                        cancion = new Cancion(nombre, artista, emocion, "src/main/resources/Songs/" + ruta, popularidad);

                        // Agrega la canción a la lista
                        listaCanciones.agregarCancion(cancion);
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

    public void emotionChoice(int choice){
        switch (choice) {
            case 1:
                user.setEstado("Feliz");
                break;
            case 2:
                user.setEstado("Triste");
                break;
            case 3:
                user.setEstado("Melancolico");
                break;
            case 4:
                user.setEstado("Enojado");
                break;
            case 5:
                user.setEstado("Euforico");
                break;
            case 6:
                user.setEstado("Enamorado");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public Notification getNotification() {
        return notification;
    }
}