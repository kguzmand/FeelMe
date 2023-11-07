package logic;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ReproductorDeMusica {
    private Clip clip;
    private boolean pausado;
    private final CountDownLatch latch = new CountDownLatch(1);

    public ReproductorDeMusica() {
        this.clip = null;
        this.pausado = false;
    }

    public void reproducirCancion(String rutaDelArchivo) {
        if(clip != null){
            detenerCancion();
        }

        try {
            File file = new File(rutaDelArchivo);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    latch.countDown();
                }
            });

            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean pausarCancion() {
        if (clip != null && clip.isRunning()) {
            pausado = true;
            clip.stop();
        }else{
            detenerCancion();
            return false;
        }
        return true;
    }

    public void continuarCancion() {
        if (clip != null && pausado) {
            pausado = false;
            clip.start();
        }
    }

    public void detenerCancion() {
        if (clip != null) {
            clip.close();
        }
    }
}
