package encriptacio;

import java.net.Socket;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class FilUsuarisConectats extends Thread{
    
    private Socket socket;
    private String nomClient;

    public FilUsuarisConectats(Socket socket, String nomClient) {
        this.socket = socket;
        this.nomClient = nomClient;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
            
    
    @Override 
    public void run() {
        
    }
}
