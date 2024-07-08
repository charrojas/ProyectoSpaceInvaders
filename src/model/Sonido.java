package model;

import javax.sound.sampled.*;

/**
 *
 * @author Yendry Villalobos, Charlotte Rojas, Alvaro Martinez.
 */
public class Sonido extends Thread {

    private String file;
    private boolean loop;
    private Clip sonido;

    /**
     * Este constructor nos permite manejar adecuadamente el sonido.
     *
     * @param file es la ruta donde se encuentra el sonido que se desee colocar.
     * @param loop es para controlar la salida del sonido.
     */
    public Sonido(String file, boolean loop) {
        this.file = file;
        this.loop = loop;
    }

    /**
     * Enciende el hilo del sonido y además se asigna la ruta.
     */
    public void run() {
        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(getClass().getResource("/resources/" + file)));
            sonido.start();
            if (loop) {
                sonido.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
