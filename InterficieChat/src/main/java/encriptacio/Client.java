package encriptacio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            socket.connect(addr);
            System.out.println("Ens conectem...");
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            
            Scanner entradaUsuario = new Scanner(System.in);
            Scanner entradaServidor = new Scanner(socket.getInputStream());
            PrintWriter salidaServidor = new PrintWriter(socket.getOutputStream(), true);
            
            Scanner entradaServer = new Scanner(socket.getInputStream());
            PrintWriter sortidaServer = new PrintWriter(socket.getOutputStream(), true);

//            Thread filCreat = new Thread(() -> {
//                while (true) {
//                    if (entradaServer.hasNextLine()) {
//                        String mensaje = entradaServer.nextLine();
//                        System.out.println("Mensaje del servidor: " + mensaje);
//                    }
//                }
//            });
//            filCreat.start();

            boolean semafor = false;
//            String msgConnexio = "Connexio";
//            os.write(msgConnexio.getBytes());
            //llegirArrayList(socket);
            Thread hiloRecepcion = new Thread(() -> {
                    while (true) {
                        if (entradaServidor.hasNextLine()) {
                            System.out.println("klk");
                            String mensaje = entradaServidor.nextLine();
                            System.out.println("Mensaje del servidor: " + mensaje);
                        }
                    }
                });
                hiloRecepcion.start();

            while (!semafor) {
                //llegirArrayList(socket);
                System.out.print("Escriu un missatge que vulguis al servidor: ");
                String msg = lector.nextLine();
                os.write(msg.getBytes());
                //sortidaServer.println(msg);
                //llegirArrayList(socket);
                
                
                if (msg.equalsIgnoreCase("exit")) {
                    os.write(msg.getBytes());
                    System.out.println("Ens hem desconnectat del servidor...");
                    semafor = true;
                }
                
                //llegirArrayList(socket);
                //llegirArrayList(Servidor.arrSocket);

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

}