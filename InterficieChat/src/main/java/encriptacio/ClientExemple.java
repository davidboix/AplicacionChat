package encriptacio;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientExemple {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        try {
            Socket clientSocket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            clientSocket.connect(addr);
            System.out.println("Ens conectem...");
            while (true) {
                System.out.println("Vol seguir connectat?: ");
                String res = lector.next();
                if (res.equalsIgnoreCase("no")) {
                    clientSocket.close();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
