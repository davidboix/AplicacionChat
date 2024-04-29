package encriptacio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorExmple {

    public static void main(String[] args) {
        
        try {
            System.out.println("Creem el socket servidor");
            ServerSocket serverSocket = new ServerSocket();

            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);

            System.out.println("Fem el bind. Ja acceptem connexions...");
            serverSocket.bind(addr);

            int conn = 1;

            while (true) {

                System.out.println("Acceptant connexions");
                Socket newSocket = serverSocket.accept();
                System.out.println("Conexión rebuda" + conn);
                new Atendre_Clients(newSocket).start();
                conn++;
            }
        } catch (Exception e) {

        }
    }
}
