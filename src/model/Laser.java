package model;

import javax.swing.JLabel;

/**
 *
 * @author Yendry Villalobos, Charlotte Rojas, Alvaro Martinez.
 */
public class Laser extends Thread {

    private JLabel laser;
    private int speed;
    public boolean flag;
    int id;

    /**
     * Este constructor nos permite crear un laser que recibirá un JLabel y su
     * respectivo id, y la velocidad del laser.
     *
     * @param laser es un JLabel que va a recibir esta clase para colocar el
     * laser.
     * @param pId sirve de base para diferenciar entre los laser´s de la nave y
     * de los invasores.
     * @param pSpeed es para aumentar la velocidad del laser.
     */
    public Laser(JLabel laser, int pId, int pSpeed) {
        this.laser = laser;
        this.id = pId;
        speed = pSpeed;
        flag = true;
    }

    /**
     * Método encargado de lanzar el laser en las posiciones de X y Y.
     */
    public void lanzarLaser() {
        try {
            for (int i = 0; i < 900; i++) {

                int x = laser.getX();
                int y = laser.getY();
                int w = laser.getWidth();
                int h = laser.getHeight();

                if (id == 0) {
                    y--;
                } else {
                    y++;
                }

                laser.setBounds(x, y, w, h);
                laser.getParent().repaint();
                laser.getParent().revalidate();
                sleep(speed);

            }

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

    /**
     * Método encargado de ejecutar el método de lanzarLaser().
     */
    public void run() {
        lanzarLaser();
    }
}
