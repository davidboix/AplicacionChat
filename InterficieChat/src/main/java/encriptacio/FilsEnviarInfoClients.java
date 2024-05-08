package encriptacio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author David Boix Sanchez i Oleh Plechiy Tupis Andriyovech
 * @version 1.0
 */
public class FilsEnviarInfoClients extends Thread {

    private String msg;
    private Socket socket;

    public FilsEnviarInfoClients() {

    }

    private FilsEnviarInfoClients(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        
    }

}
