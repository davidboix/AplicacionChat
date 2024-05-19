package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class EscoltaMsgServidor extends Thread {

    private Socket socket;
    private InputStream inputStream;

    /**
     * Definit constructor buit per poder inicialitzar objectes de tipus
     * EscoltaMsgServidor.
     */
    public EscoltaMsgServidor() {

    }

    /**
     * Definit constructor que utilitzarem per poder inicialitzar objectes de
     * tipus EscoltaMsgServidor i que es podra utilitzar per poder crear nous
     * Threads.
     *
     * @param socket Passarem un socket per parametres el qual sera el socket de
     * un client en questio.
     * @param inputStream Passarem un InputStream per poder rebre els missatges
     * que ens enviara el servidor.
     */
    public EscoltaMsgServidor(Socket socket, InputStream inputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
    }

    /**
     * Getter definit per poder utilitzar els metodes de la classe Socket.
     *
     * @return Socket inicialitzat.
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Setter definit per si volem definir el socket del objecte de manera
     * manual, es a dir, sense utilitzar el constructor amb parametres.
     *
     * @param socket Socket parametritzat per inicialitzar el atribut de
     * l'objecte.
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Getter que utilitzarem per poder utilitzar els metodes de la classe
     * InputStream.
     *
     * @return InputStream que utilitzarem per poder utilitzar els metodes.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Setter definit per poder inicialitzar el atribut anomenat InputStream de
     * la classe EscoltaMsgServidor.
     *
     * @param is InputStream parametritzat per poder inicialitzar l'atribut
     * correctament.
     */
    public void setInputStream(InputStream is) {
        this.inputStream = is;
    }

    /**
     * Funcio sobreescrita la qual ens aporta la classe Thread per poder
     * inicialitzar fils correctament definits i que realitzaran la funcio de
     * rebre missatges per part del Servidor.
     */
    @Override
    public void run() {
        try {
            System.out.print("Escriu el missatge que vulguis al servidor: ");
            byte[] buffer = new byte[1024];
            while (!this.socket.isClosed()) {
                int intBytes = this.inputStream.read(buffer);
                if (intBytes != -1) {
                    String msg = new String(buffer, 0, intBytes);
                    System.out.println("\nMissatge del servidor: " + msg);
                    System.out.print("Escriu el missatge que vulguis al servidor: ");
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
