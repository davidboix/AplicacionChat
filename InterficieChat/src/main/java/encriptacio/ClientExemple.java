package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.net.Socket;

import java.net.SocketException;
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
            boolean semafor = false;

            while (!semafor) {
                System.out.print("Escriu un missatge que vulguis al servidor: ");
                String msg = lector.nextLine();

                if (msg.equalsIgnoreCase("exit")) {
                    os.write(msg.getBytes());
                    System.out.println("Ens hem desonnectat del servidor...");
                    semafor = true;
                }
                os.write(msg.getBytes());
            }
            socket.close();
        } catch (SocketException se) {
            se.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un error en la connexio del client cap al servidor.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un error i per tant no s'ha executat correctament el client!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un problema general en el client");
        }
    }

}
