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
                os.write(msg.getBytes());

                if (msg.equalsIgnoreCase("exit")) {
                    os.write(msg.getBytes());
                    System.out.println("Ens hem desconnectat del servidor...");
                    semafor = true;
                }
                llegirMissatgeServidor(is);
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

    /**
     * TODO: Revisar logica de la funcio ja i separar-ho per capes per no
     * tenir-ho tot en una classe.
     *
     * Funcio creada per poder llegirs els diferents missatges que ens envien
     * desde el servidor.
     */
    private static void llegirMissatgeServidor(InputStream is) throws IOException {
        byte[] buffer = new byte[500];
        int intBuffer = is.read(buffer);
        String msgRebut = new String(buffer, 0, intBuffer);
        String[] arrMsg = msgRebut.split("\n");
        System.out.println("missatges rebut per part de servidor...");
        for (String row : arrMsg) {
            System.out.println(row);
        }
        System.out.println("Missatge rebut del servidor: " + msgRebut);
    }

}
