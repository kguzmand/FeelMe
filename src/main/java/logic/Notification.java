package logic;

import java.awt.*;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Notification {
    private final Queue<LocalTime> timeQueue;
    private StackArray<LocalDateTime> notificationHistory;
    private int hours, freq, occurrences;
    private int option = 3;
    private final Object lock = new Object();
    private final Object myLock = new Object();
    private int choice = 0;
    private CountDownLatch choiceLatch = new CountDownLatch(1);
    private CountDownLatch optionLatch = new CountDownLatch(1);


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

    public void sendNotification(User user, SongList songList) {
        setUpNotification();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        boolean firstNotification = true;
        while (!timeQueue.empty()) {
            LocalTime notificationTime = timeQueue.dequeue();
            if (notificationTime != null) {
                scheduleNotification(scheduler, notificationTime, user, songList);
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

    private void scheduleNotification(ScheduledExecutorService scheduler, LocalTime notificationTime, User user, SongList songList) {
        LocalTime currentTime = LocalTime.now();
        long initialDelay = Duration.between(currentTime, notificationTime).getSeconds();

        // Schedule the notification for the specified time using a lambda expression
        scheduler.schedule(() -> showNotification(user, songList), initialDelay, TimeUnit.SECONDS);
    }

    private void showNotification(User user, SongList songList) {
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
                        try {
                            choiceLatch.await(); // Esperar hasta que choice tenga un valor diferente de cero
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        Cancion newSong = songList.seleccionarCancionAleatoria(choice);
                        MusicPlayer playSong = new MusicPlayer();

                        // Reproducir la canción en un hilo separado
                        Thread reproductorThread = new Thread(() -> {
                            playSong.reproducirCancion(newSong.getRuta());
                        });
                        reproductorThread.start();

                        boolean flag = true;
                        while (flag) {
                            switch (getOption()) {
                                case 1:
                                    flag = playSong.pausarCancion();
                                    setOption(3);
                                    break;
                                case 2:
                                    playSong.continuarCancion();
                                    setOption(3);
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("Opcion no valida");
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

    public void setOption(int value) {
        synchronized (lock) {
            option = value;
            optionLatch.countDown();
        }
    }

    public int getOption() {
        try {
            optionLatch.await(); // Espera hasta que se actualice la variable option
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            return option;
        }
    }

    public void setChoice(int choice) {
        this.choice = choice;
        choiceLatch.countDown(); // Liberar el CountDownLatch
    }

    public void resetChoiceLatch() {
        choiceLatch = new CountDownLatch(1);
    }
}