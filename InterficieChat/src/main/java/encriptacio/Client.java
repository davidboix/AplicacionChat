package encriptacio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public Client() {

    }

    /**
     * Main que farem servir per poder fer les proves entre client i servidor
     * sense utilitzar la interficie grafica.
     *
     * TODO: Haurem de comentar aquesta funcio per a que NO deixi de funcionar
     * en la interficie grafica.
     *
     * @param args
     */
    public static void main(String[] args) {
        setConnexio();
    }

    public static void main2(String[] args) {
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

                //llegirArrayList(socket);
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
        System.out.println("Missatge rebut del servidor: " + msgRebut);
    }

    /**
     * Funcio creada provisionalment per poder rebre tot el ArrayList que conte
     * tots els missatges enviats per un client en questio.
     *
     * @param socket
     */
    private static void llegirArrayList(Socket socket) {
        //private static void llegirArrayList(ArrayList<Socket> arrSocket) {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ArrayList<String> msgClients = (ArrayList<String>) ois.readObject();

            for (String row : msgClients) {
                System.out.println(row);
            }
        } catch (EOFException eof) {
            System.err.println("\nCLIENT: No hi han mes missatges a mostrar...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nCLIENT:Hi ha hagut un error general...");
        }
    }

    /**
     * TODO: Afegir una descripcio mes objectiva, es a dir, mes extensa.
     *
     * Funcio creada per poder fer la connexio en el servidor utilitzant el
     * metode en questio.
     */
    public static void setConnexio() {
        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            socket.connect(addr);

            Scanner lector = new Scanner(System.in);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            new EscoltaMsgServidor(socket, is).start();

            boolean semafor = enviarMissatgeServidor(lector, os, socket);;

            if (!semafor) {
                socket.close();
            }
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
     * Funcio creada per poder enviar missatges al servidor i a mes a mes, es
     * tractara la paraula clau definida per poder desconectar-se del servidor
     * correctament.
     *
     * @param lector Objecte de tipus Scanner que ens servira per poder escriure
     * missatges per consola i enviar-los.
     * @param os Objecte de tipus OutputStream que ens servira per poder enviar
     * els missatges escrits per consola.
     * @param socket Objecte de tipus Socket que ens servira per identificar
     * quin socket hem de tancar.
     * @return Retornara cert si el usuari no ha enviat la paraula clau
     * anomenada 'exit' i per tant podra seguir enviant missatges i el cas
     * contrari, si el usuari envia el missatge 'exit' retornara fals i per
     * consequencia, es realitzara la desconexio.
     */
    private static boolean enviarMissatgeServidor(Scanner lector, OutputStream os, Socket socket) {
        boolean sortir = false;
        try {
            while (!sortir) {
                String msg = lector.nextLine();
                if (msg.equalsIgnoreCase("exit")) {
                    os.write(msg.getBytes());
                    System.out.println("Ens hem desconnectat del servidor correctament...");
                    sortir = true;
                    socket.close();
                }
                os.write(msg.getBytes());
            }
            return sortir;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortir;
    }

}
