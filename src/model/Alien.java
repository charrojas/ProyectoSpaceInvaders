package model;

import static java.lang.Thread.sleep;
import javax.swing.JLabel;

/**
 *
 * @author Yendry Villalobos, Charlotte Rojas, Alvaro Martinez.
 */
public class Alien extends Thread {

    private JLabel uno;
    private int velocidad;
    int valor;
    private boolean exit = false;

    /**
     * Este constructor nos permite crear un alien que recibira un JLabel y su
     * respectivo valor.
     *
     * @param uno es un JLabel que va a recibir esta clase el cuál será un alien
     * en el espacio.
     * @param pValor es para añadirle el respectivo valor a cada alien.
     */
    public Alien(JLabel uno, int pValor) {
        this.uno = uno;
        velocidad = 10;
        valor = pValor;

    }

    public int getValor() {
        return valor;
    }

    public JLabel getLabel() {
        return uno;
    }

    /**
     * Este método es el encargado de mover los aliens a la derecha.
     */
    public void mDerecha() {
        if (!exit) {
            try {
                for (int i = 0; i < 340; i++) {

                    int x = uno.getX();
                    int y = uno.getY();
                    int w = uno.getWidth();
                    int h = uno.getHeight();

                    x++;

                    uno.setBounds(x, y, w, h);
                    if (uno.getParent() != null) {
                        uno.getParent().repaint();
                        uno.getParent().revalidate();
                    }
                    sleep(velocidad);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Este método es el encargado de mover los aliens a la izquierda.
     */
    public void mIzquierda() {
        if (!exit) {
            try {
                for (int i = 0; i < 340; i++) {

                    int x = uno.getX();
                    int y = uno.getY();
                    int w = uno.getWidth();
                    int h = uno.getHeight();
                    x--;

                    uno.setBounds(x, y, w, h);

                    if (uno.getParent() != null) {
                        uno.getParent().repaint();
                        uno.getParent().revalidate();
                    }
                    sleep(velocidad);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Este método es el encargado de mover los aliens hacia abajo.
     */
    public void mAbajo() {
        try {
            for (int i = 0; i < 10; i++) {

                int x = uno.getX();
                int y = uno.getY();
                int w = uno.getWidth();
                int h = uno.getHeight();

                y++;

                uno.setBounds(x, y, w, h);
                if (uno.getParent() != null) {
                    uno.getParent().repaint();
                    uno.getParent().revalidate();
                }

                sleep(velocidad);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método encargado de cambiar la variable exit y lograr salir del hilo
     * cuando se desee.
     */
    public void hStop() {
        exit = !exit;
    }

    /**
     * Método el cual ejecuta los métodos creados en la clase Alien en ese
     * respectivo orden, y además la condición se encarga de que los aliens
     * vayan más rápido conforme van bajando.
     */
    public void run() {
        //salirse 
        while (!exit) {
            mDerecha();
            mAbajo();
            mIzquierda();
            mAbajo();
            if (velocidad > 3) {
                velocidad--;
            }
        }
    }
}
