package encriptacio;

import java.io.IOException;
import java.net.Socket;

public class Atendre_Clients extends Thread {

    private Socket newSocket = null;

    public Atendre_Clients(Socket cs) {
        newSocket = cs;
    }

    public void run() {
        try {
            Thread.sleep(10000);
            newSocket.close();
            System.out.println("Tanquem nou socket...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
