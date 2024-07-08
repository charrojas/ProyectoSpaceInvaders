package controller;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import model.*;
import view.GuiEspacio;

/**
 *
 * @author Yendry Villalobos, Charlotte Rojas, Alvaro Martinez.
 */
public class Controller implements ActionListener {

    private GuiEspacio guiEspacio;
    private Sonido sonido;

    /**
     * Este constructor llama al método de initEvents() y se genera la instancia
     * de la guiEspacio().
     */
    public Controller() {
        guiEspacio = new GuiEspacio();
        initEvents();
    }

    /**
     * Este método es el encargado de agregar el escucha de los botones.
     */
    private void initEvents() {
        //Nombre jugador
        guiEspacio.getBtnIniciar().addActionListener(this);
        //Menu
        guiEspacio.getBtnStart().addActionListener(this);
        guiEspacio.getBtnInstrucciones().addActionListener(this);
        guiEspacio.getBtnAcercaD().addActionListener(this);
        //Instrucciones
        guiEspacio.getBtnAtras().addActionListener(this);
        guiEspacio.getBtnSalir().addActionListener(this);
        //Acerca de
        guiEspacio.getAtras().addActionListener(this);
        guiEspacio.getSalir().addActionListener(this);
        //Game over   
        guiEspacio.getTerminar().addActionListener(this);
        //Game Won
        guiEspacio.getBtnTerminar().addActionListener(this);
        //Espacio
        guiEspacio.getTerminarJuego().addActionListener(this);
        guiEspacio.init();

    }

    /**
     * ActionListener es un controlador de eventos que ejecuta una tarea cuando
     * una determinada acción se ejecuta, dependiendo en este caso de los
     * botones.
     *
     * @param e es el evento.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //GUI INICIAL
        if (e.getSource() == guiEspacio.btnIniciar) {
            guiEspacio.remove(guiEspacio.pFondo);
            guiEspacio.add(guiEspacio.pFondoMenu, BorderLayout.CENTER);
            guiEspacio.repaint();
            guiEspacio.revalidate();
        }

        //GUI MENU
        if (e.getSource() == guiEspacio.btnStart) {
            guiEspacio.remove(guiEspacio.pFondoMenu);
            guiEspacio.add(guiEspacio.pEspacio, BorderLayout.CENTER);
            guiEspacio.repaint();
            guiEspacio.revalidate();
            guiEspacio.nombreJugador.setText(guiEspacio.campoName.getText());

            guiEspacio.aliensRangoUno(guiEspacio.alien);
            guiEspacio.aliensRangoDos(guiEspacio.alien);
            guiEspacio.aliensRangoTres(guiEspacio.alien);

            guiEspacio.thread2.start();
            guiEspacio.thread3.start();

            //cliente
            Socket socket = null;
            try {
                socket = new Socket("localhost", 80);
                System.out.println("Cliente conectado");
            } catch (IOException ex) {
                System.out.println("Cliente no se pudo conectar");
            }
            OutputStream output;
            InputStream input;
            try {

                guiEspacio.nombreJugador.setText(guiEspacio.campoName.getText());
                output = socket.getOutputStream();
                DataOutputStream data = new DataOutputStream(output);
                data.writeUTF(guiEspacio.campoName.getText());

                input = socket.getInputStream();
                ObjectInputStream object = new ObjectInputStream(input);
                Jugador jugador = (Jugador) object.readObject();
                guiEspacio.campoName.setText(jugador.getNombreJugador());

            } catch (IOException ex) {
                System.out.println("No se pudo enviar la solicitud");
            } catch (ClassNotFoundException ex) {
            }

        }

        if (e.getSource() == guiEspacio.btnInstrucciones) {
            guiEspacio.remove(guiEspacio.pFondoMenu);
            guiEspacio.add(guiEspacio.pInstrucciones, BorderLayout.CENTER);
            guiEspacio.repaint();
            guiEspacio.revalidate();
        }

        if (e.getSource() == guiEspacio.btnAcercaD) {
            guiEspacio.remove(guiEspacio.pFondoMenu);
            guiEspacio.add(guiEspacio.pAcercaD, BorderLayout.CENTER);
            guiEspacio.repaint();
            guiEspacio.revalidate();
        }
        //GUI ACERCAD
        if (e.getSource() == guiEspacio.atras) {
            guiEspacio.remove(guiEspacio.pAcercaD);
            guiEspacio.add(guiEspacio.pFondoMenu, BorderLayout.CENTER);
            guiEspacio.repaint();
            guiEspacio.revalidate();
        }
        if (e.getSource() == guiEspacio.salir) {
            System.exit(0);
        }
        //GUI INSTRUCCIONES
        if (e.getSource() == guiEspacio.btnAtras) {
            guiEspacio.remove(guiEspacio.pInstrucciones);
            guiEspacio.add(guiEspacio.pFondoMenu, BorderLayout.CENTER);
            guiEspacio.repaint();
            guiEspacio.revalidate();
        }
        if (e.getSource() == guiEspacio.btnSalir) {
            System.exit(0);
        }
        //GUI GAME OVER
        if (e.getSource() == guiEspacio.terminar) {
            System.exit(0);
        }

        //GUI GAME WON
        if (e.getSource() == guiEspacio.btnTerminar) {
            System.exit(0);
        }

        //GUI ESPACIO
        if (e.getSource() == guiEspacio.terminarJuego) {
            guiEspacio.gameOver();
        }
    }
}
