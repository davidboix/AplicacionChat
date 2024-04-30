package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 *
 */
//public class Atendre_Clients extends Thread {
//
//    private Socket newSocket;
//    private String msgClient;
//
//    public Atendre_Clients(Socket cs) {
//        newSocket = cs;
//    }
//
//    public Atendre_Clients(Socket cs, String msgClient) {
//        newSocket = cs;
//        this.msgClient = msgClient;
//    }
//
//    public void setMsgClient(String msgClient) {
//        this.msgClient = msgClient;
//    }
//
//    public String getMsgClient() {
//        return this.msgClient;
//    }
//
//    public void run(String nom) {
//        try {
//            /**
//             * TODO: El servidor llegira un missatge de desconnexio per part del
//             * client que sera DESCONNEXIO quan el client premi al boto de
//             * desconnexio de la interficie
//             *
//             */
//            boolean semafor = false;
//            final String MISSATGE_DESCONNEXIO = "DESCONNEXIO";
//            InputStream is = newSocket.getInputStream();
//            OutputStream os = newSocket.getOutputStream();
//            while (!semafor) {
////                Thread.sleep(60000);
//                byte[] buffer = new byte[500];
//                is.read(buffer);
//                String msgDesconnexio = new String(buffer).trim();
////                String msgDesconnexio = demanarDesconnexio(MISSATGE_DESCONNEXIO);
//                //String msgDesconnexio = "DESCONNEXIO";
//
//                if (msgDesconnexio.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
//                    //Arriba el missatge de desconnexio i retornar true
//                    System.out.println("\nArribem dins del condicional de Atendre_Client");
//                    semafor = tancarConnexio(newSocket, MISSATGE_DESCONNEXIO);
//                    System.out.println("\nTanquem nou socket...");
//                }
//                System.out.println("\nSeguim amb la connexio...");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("ERROR!\n Hem entrat en un error...");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.err.println("ERROR!\nHi ha hagut un error de interrupcio");
//        } catch (Exception e) {
//            System.err.println("ERROR!\nHi ha un metode que no ha funcionat correctament");
//        }
//    }
//
//    /**
//     * Funcio que farem servir per poder desconnectar el client del servidor
//     * quan arribi un missatge en clau per poder realitzar l'operacio
//     *
//     * @param socket Socket que arribara per part del client per poder indicar
//     * quin socket volem desconnectar
//     * @param desconnexio Paraula clau que farem servir per realitzar la
//     * desconnexio
//     * @return Retornarem cert en cas de que l'operacio s'hagui realitzat
//     * correctament i en cas contrari, no es realitzara correctament la
//     * desconnexio i no es produira
//     */
//    public boolean tancarConnexio(Socket socket, String desconnexio) throws Exception {
//        try {
//            final String DESCONNEXIO = "DESCONNEXIO";
//            if (desconnexio.equalsIgnoreCase(DESCONNEXIO)) {
//                Thread.sleep(10000);
//                socket.close();
//                System.out.println("S'ha desconectat el usuari correctament");
//                return true;
//            }
//            System.out.println("No s'ha realitzat correctament");
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    private String demanarDesconnexio(String missatgeClientDesconnexio) {
//        return "DESCONNEXIO";
//    }
//}
public class Atendre_Clients extends Thread {

    private Socket newSocket;
    private String DEMANAR_CONNEXIO;

    public Atendre_Clients(Socket newSocket) {
        this.newSocket = newSocket;
    }
    

    public Atendre_Clients(Socket cs, String DEMANAR_CONNEXIO) {
        newSocket = cs;
        this.DEMANAR_CONNEXIO = DEMANAR_CONNEXIO;
    }

    public void run() {
        try {
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();
            while (true) {
                boolean cagar = llegirDesconnexio(is, DEMANAR_CONNEXIO);
                if (cagar) {
                    System.out.println("Client disconnected");
                    newSocket.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean llegirDesconnexio(InputStream is, String msgDesconnexio) throws Exception {
        try {
            byte[] msg = new byte[500];
            is.read(msg);
            String desconnexio = new String(msg).trim(); // trim() removes leading and trailing whitespaces
            System.out.println(desconnexio);
            if (msgDesconnexio.equalsIgnoreCase(desconnexio)) {
                return true;
            } else {
                System.out.println("NO TANQUEM CONNEXIO");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nERROR!\n El metode valoraNom ha petat :( ");
        }
        return false;
    }
}
