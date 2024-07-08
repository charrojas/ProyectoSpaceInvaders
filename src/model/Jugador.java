package model;

/**
 *
 * @author Yendry Villalobos, Charlotte Rojas, Alvaro Martinez.
 */
public class Jugador {

    public String nombreJugador;

    /**
     * Este constructor nos permite crear un jugador agregando el nombre del
     * mismo.
     *
     * @param nombreJugador es el nombre que será utilizado por el jugador.
     */
    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    @Override
    public String toString() {
        return nombreJugador;
    }

}
