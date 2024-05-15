package encriptacio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class ServidorSocketProvisional {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 4949);

            serverSocket.bind(addr);
            byte i = 0;
            while (true) {
                System.out.println("Servidor escoltant... ja preparat");
                Socket newSocket = serverSocket.accept();
                i++;
                InputStream is = newSocket.getInputStream();
                OutputStream os = newSocket.getOutputStream();

                byte[] msg = new byte[500];

                is.read(msg);

                String miss = "Connexio numero -> " + i + "\n" + new String(msg);

                System.out.println(miss);
                String qtClients = "Ets la connexio numero: " + i;
                os.write(qtClients.getBytes());

                newSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
