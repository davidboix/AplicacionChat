package encriptacio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorSocketGPT {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        try ( ServerSocket serverSocket = new ServerSocket()) {
            InetSocketAddress addr = new InetSocketAddress("localhost", 4949);
            serverSocket.bind(addr);

            byte i = 0;
            while (true) {
                System.out.println("Servidor escoltant... ja preparat");
                Socket newSocket = serverSocket.accept();
                System.out.println("El client numero " + i + " s'ha conectat");
                Persona persona = new Persona(newSocket, i);
                executor.execute(persona);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
