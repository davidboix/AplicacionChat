package encriptacio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

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

    public FilsEnviarInfoClients(Socket socket) {
        this.socket = socket;
    }
    /**
     * Funcio la qual llegira els missatges que envii el client i els retornara
     */
    public void run() {
        Scanner lector = new Scanner(System.in);
        try {
            ServidorExmple server = new ServidorExmple();

            boolean semafor = false;
            final String MISSATGE_DESCONNEXIO = "exit";
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();


            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
            //TODO: aqui es connectara al client al qual li tornara els missatges
            //InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            //socket.connect(addr);
            System.out.println("Ens conectem...");

            while (!semafor) {
                //TODO: llegira el missatge i el guardara en una variable
                byte[] buffer = new byte[500];
                int intBuffer = is.read(buffer);
                String msg = new String(buffer, 0, intBuffer);

                if (msg.equalsIgnoreCase("exit")) {
                    //TODO: enviara el missatge llegit
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
