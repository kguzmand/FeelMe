package Logic;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Notification {
    private final Queue<LocalTime> timeQueue;
    int hours, freq, occurrences;
    public Notification() {
        this.freq = 15;
        this.hours = 1;
        this.occurrences = 4;
        this.timeQueue = new Queue<>();
    }

    public Notification(int freq, int hours){
        this.freq = freq;
        this.hours = hours;
        this.occurrences = hours * 60 / freq;
        this.timeQueue = new Queue<>();
    }

    private void setUpNotification(){
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
        while (!timeQueue.empty()) {
            LocalTime notificationTime = timeQueue.dequeue();
            if (notificationTime != null) {
                scheduleNotification(scheduler, notificationTime);
            }
        }
        scheduler.shutdown();
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
                trayIcon.displayMessage("FeelMe", "¡Es hora de expresar lo que sientes!", MessageType.INFO);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El sistema no admite la bandeja del sistema.");
        }
    }
}