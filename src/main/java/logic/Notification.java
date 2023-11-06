package logic;

import java.awt.*;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class Notification {
    private final Queue<LocalTime> timeQueue;
    private StackArray<LocalDateTime> notificationHistory;
    int hours, freq, occurrences;

    public Notification() {
        this.freq = 15;
        this.hours = 1;
        this.occurrences = 4;
        this.timeQueue = new Queue<>();
        this.notificationHistory = new StackArray<>(100); // Cambia el tamaño si es necesario
    }

    public Notification(int freq, int hours) {
        this.freq = freq;
        this.hours = hours;
        this.occurrences = hours * 60 / freq;
        this.timeQueue = new Queue<>();
        this.notificationHistory = new StackArray<>(100); // Cambia el tamaño si es necesario
    }

    private void setUpNotification() {
        LocalTime currentTime = LocalTime.now();
        timeQueue.enqueue(currentTime);
        for (int i = 1; i <= hours; i++) {
            for (int j = 1; j <= 60; j += freq) {
                currentTime = currentTime.plusMinutes(freq);
                timeQueue.enqueue(currentTime);
            }
            currentTime = currentTime.plusHours(1); // Add one hour after each cycle of minutes
        }
    }

    public void sendNotification(Usuario user, ListaCanciones listaCanciones) {
        setUpNotification();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        boolean firstNotification = true;
        while (!timeQueue.empty()) {
            LocalTime notificationTime = timeQueue.dequeue();
            if (notificationTime != null) {
                scheduleNotification(scheduler, notificationTime, user, listaCanciones);
                //Graba la 1ra notificacion
                if (firstNotification) {
                    recordFirstNotificationTime();
                    firstNotification = false;
                }
            }
        }
        scheduler.shutdown();
    }

    private void recordFirstNotificationTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        notificationHistory.push(currentTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma");
        String formattedTime = currentTime.format(formatter);
        // Imprimir la fecha y hora formateada
        System.out.println("Fecha y hora de la primera notificación: " + formattedTime);
    }

    private void scheduleNotification(ScheduledExecutorService scheduler, LocalTime notificationTime, Usuario user, ListaCanciones listaCanciones) {
        LocalTime currentTime = LocalTime.now();
        long initialDelay = Duration.between(currentTime, notificationTime).getSeconds();

        // Schedule the notification for the specified time using a lambda expression
        scheduler.schedule(() -> showNotification(user, listaCanciones), initialDelay, TimeUnit.SECONDS);
    }

    private void showNotification(Usuario user, ListaCanciones listaCanciones) {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            // Obtener la URL del recurso desde la carpeta de recursos raíz
            ClassLoader classLoader = getClass().getClassLoader();
            URL imageURL = classLoader.getResource("LogoFeelMe.png");

            if (imageURL != null) {
                Image iconImage = Toolkit.getDefaultToolkit().getImage(imageURL);
                TrayIcon trayIcon = new TrayIcon(iconImage);

                try {
                    // Agregar un ActionListener al TrayIcon
                    trayIcon.addActionListener(e -> {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Me siento...");
                        System.out.println("1. Feliz");
                        System.out.println("2. Triste");
                        System.out.println("3. Melancolico");
                        System.out.println("4. Enojado");
                        System.out.println("5. Euforico");
                        System.out.println("6. Enamorado");

                        int choice = parseInt(scanner.next());

                        switch (choice){
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
                        Cancion newSong = listaCanciones.seleccionarCancionAleatoria(choice);

                        ReproductorDeMusica playSong = new ReproductorDeMusica();
                        // Reproducir la canción en un hilo separado
                        Thread reproductorThread = new Thread(() -> {
                            playSong.reproducirCancion(newSong.getRuta());
                        });
                        reproductorThread.start();

                        boolean flag = true;

                        while (flag){
                            System.out.println("1. Pausar");
                            System.out.println("2. Continuar");

                            int option = parseInt(scanner.next());

                            switch (option) {
                                case 1:
                                    flag = playSong.pausarCancion();
                                    break;
                                case 2:
                                    playSong.continuarCancion();
                                    break;
                                default:
                                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                                    break;
                            }
                        }
                    });

                    tray.add(trayIcon);
                    trayIcon.displayMessage("FeelMe", "¡Es hora de expresar lo que sientes!", TrayIcon.MessageType.INFO);

                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No se pudo cargar la imagen del recurso.");
            }
        } else {
            System.out.println("El sistema no admite la bandeja del sistema.");
        }
    }
}