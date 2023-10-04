package logic;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    public void sendNotification() {
        setUpNotification();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        boolean firstNotification = true;
        while (!timeQueue.empty()) {
            LocalTime notificationTime = timeQueue.dequeue();
            if (notificationTime != null) {
                scheduleNotification(scheduler, notificationTime);
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

    private void scheduleNotification(ScheduledExecutorService scheduler, LocalTime notificationTime) {
        LocalTime currentTime = LocalTime.now();
        long initialDelay = Duration.between(currentTime, notificationTime).getSeconds();

        // Schedule the notification for the specified time
        scheduler.schedule(this::showNotification, initialDelay, TimeUnit.SECONDS);
    }

    private void showNotification() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image iconImage = Toolkit.getDefaultToolkit().getImage("LogoFeelMe.png");
            TrayIcon trayIcon = new TrayIcon(iconImage);

            try {
                tray.add(trayIcon);
                trayIcon.displayMessage("FeelMe", "¡Es hora de expresar lo que sientes!", TrayIcon.MessageType.INFO);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El sistema no admite la bandeja del sistema.");
        }
    }
}
