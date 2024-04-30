package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientExemple {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);

            socket.connect(addr);
            System.out.println("Ens conectem...");

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            while (true) {
                System.out.println("Vol seguir connectat?: ");
                String res = lector.next();
                //En aquest moment l'hi hem enviat el missatge al client
                os.write(res.getBytes());
                
                if (res.equalsIgnoreCase("DESCONNEXIO")) {
                    socket.close();
                    break;
                }
            }

        } catch (IOException ioe) {
            System.err.println("ERROR\nNo estem poden fer la connexio...");
        }
    }

}
