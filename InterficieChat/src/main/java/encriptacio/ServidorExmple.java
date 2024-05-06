package encriptacio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorExmple {
    // IP MongoDB al nuvol: 57.129.5.24
    //Port MongodBD al nuvol: 27017

    // Estructura MongoDB
    // Usuari de la BD: grup1
    // Password: gat123
    
    public static void main(String[] args) {

        try {
            System.out.println("Creem el socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            serverSocket.bind(addr);

            boolean semafor = false;
            int qtClients = 0;

            while (true) {
                qtClients++;
                System.out.println("Servidor preparat per escoltar!");
                Socket newSocket = serverSocket.accept();
                //System.out.println("Quantitat de clients connectats: " + qtClients);
                new Atendre_Clients(newSocket, qtClients).start();
                //Prova de boolean per a si veu missatge de desconnexio, li resti, pero no funciona be ja que el que fara es aturar el while i no llegira l'altre client
//                if(new Atendre_Clients(newSocket, qtClients).prova()){
//                    qtClients--;
//                }
                
//                while (!semafor) {
//
//                    InputStream is = newSocket.getInputStream();
//                    OutputStream os = newSocket.getOutputStream();
//                    /**
//                     * TODO: Desde el servidor, hem de rebre el missatge de
//                     * desconnexio que ens envia el client per poder
//                     * desconnectar
//                     *
//                     */
//                    boolean cagar = llegirDesconnexio(newSocket, is, os, DEMANAR_CONNEXIO, qtClients);
//                    if (cagar) {
//                        newSocket.close();
//                        qtClients--;
//                        System.out.println("Connexions actuals: " + qtClients);
//                        semafor = !semafor;
//
//                    }
//                    
//                }
//                new Atendre_Clients(newSocket).start;
////                Atendre_Clients atendreClientsqtClients = new Atendre_Clients(newSocket);
//                atendreClientsqtClients.start();
//
//                boolean desconnectar = llegirDesconnexio(newSocket, is, os, DEMANAR_CONNEXIO);
//                if (desconnectar) {
//                    System.out.println("Rebem missatge del cient");
//
//                    atendreClientsqtClients.setMsgClient(DEMANAR_CONNEXIO);
//                    byte[] msg = new byte[500];
//                    is.read(msg);
//                    String prova = new String();
//                    if (atendreClientsqtClients.getMsgClient().equalsIgnoreCase(DEMANAR_CONNEXIO)) {
//                        atendreClientsqtClients.tancarConnexio(newSocket, DEMANAR_CONNEXIO);
//                        System.out.println("Tanquem connexio");
//                        qtClients--;
//                    } else {
//                        System.out.println("No ha tancat connexio");
//                    }
//                }
//                qtClients--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Funcio provisional creada per poder augmentar les connexions que es
     * realitzen en el servidor i portar un control sobre elles
     *
     * @param qtClients La quantitat de clients el qual ens trobem en aquell
     * moment.
     * @return Retorna la quantitat de clients actuals augmentada en 1.
     */
    private static int augmentarConnexio(int qtClients) {
        return qtClients;
    }

    /**
     * TODO: Funcio que haurem de desenvolupar mes tard per poder saber si estem
     * fent un tractament correcte
     *
     * @param socket
     * @param is
     * @param os
     * @param msgDesconnexio
     * @param i
     * @return
     * @throws Exception
     */
    private static boolean llegirDesconnexio(Socket socket, InputStream is, OutputStream os, String msgDesconnexio, int i) throws Exception {
        try {
            byte[] msg = new byte[500];
            is.read(msg);
            String desconnexio = new String(msg);
            System.out.println(desconnexio);
            if (msgDesconnexio.equalsIgnoreCase(desconnexio)) {
                i--;
                return true;
            } else {
                System.out.println("NO TANQUEM CONNEXIO");
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nERROR!\n El metode valoraNom ha petat :( ");
        }
        return false;
    }
}

//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class ServidorExmple {
//
//    public static void main(String[] args) {
//        try {
//            final String DEMANAR_CONNEXIO = "DESCONNEXIO";
//            System.out.println("Creem el socket servidor");
//            ServerSocket serverSocket = new ServerSocket();
//
//            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
//
//            System.out.println("Fem el bind. Ja acceptem connexions...");
//            serverSocket.bind(addr);
//
//            
//
//            // Thread to handle new connections
//            Thread acceptThread = new Thread(() -> {
//                int qtClients = 0;
//                boolean semafor = false;
//                while (true) {
//                    try {
//                        System.out.println("Acceptant connexions");
//                        Socket newSocket = serverSocket.accept();
//                        qtClients++;
//                        System.out.println("Conexió rebuda: " + qtClients + " clients connectats");
//
//                        // Create a new thread for each client
//                        new Atendre_Clients(newSocket).start();
//                        InputStream is = newSocket.getInputStream();
//                        OutputStream os = newSocket.getOutputStream();
//
//                        boolean cagar = llegirDesconnexio(newSocket, is, os, DEMANAR_CONNEXIO, qtClients);
//                        if (cagar) {
//                            newSocket.close();
//                            qtClients--;
//                            System.out.println("Connexions actuals: " + qtClients);
//                            semafor = !semafor;
//
//                        }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                }
//            });
//            acceptThread.start();
//
//            // Main thread to read disconnection messages
//            while (true) {
//                Thread.sleep(1000); // Sleep for 1 second to avoid busy-waiting
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static boolean llegirDesconnexio(Socket socket, InputStream is, OutputStream os, String msgDesconnexio, int i) throws Exception {
//        try {
//            byte[] msg = new byte[500];
//            is.read(msg);
//            String desconnexio = new String(msg).trim(); // trim() removes leading and trailing whitespaces
//            System.out.println(desconnexio);
//            if (msgDesconnexio.equalsIgnoreCase(desconnexio)) {
//                return true;
//            } else {
//                System.out.println("NO TANQUEM CONNEXIO");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("\nERROR!\n El metode valoraNom ha petat :( ");
//        }
//        return false;
//    }
//}

