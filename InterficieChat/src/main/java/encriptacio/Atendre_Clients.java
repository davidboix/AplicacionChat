package encriptacio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class Atendre_Clients extends Thread {

    private Socket newSocket;
    private String msgClient;
    private int qtClients;
    //he intentat fer una global, pero quan entre un altre client es reseteje
    int clientsNumber = 0;

    public Atendre_Clients(Socket newSocket) {
        this.newSocket = newSocket;
    }

    public Atendre_Clients(Socket cs, int qtClients) {
        this.newSocket = cs;
        this.qtClients = qtClients;
        //this.qtClients = 0;
    }

    /**
     * TODO: Necessitem utilitzar aquestos metodes per poder inicialitzar un
     * missatge de un client pero ns sabem com.
     */
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
    //TODO: Revisar aquesta funcio ja que ens esta donant problemes.
    public void correForestCorre() {
        try {
            /**
             * TODO: El servidor llegira un missatge de desconnexio per part del
             * client que sera DESCONNEXIO quan el client premi al boto de
             * desconnexio de la interficie
             */
            boolean semafor = false;
            final String MISSATGE_DESCONNEXIO = "exit";
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            DataInputStream dis = new DataInputStream(newSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(newSocket.getOutputStream());

            System.out.println("Quantitat de clients connectats: " + this.qtClients);
            while (!semafor) {

                byte[] keyBytes = new byte[dis.readInt()];
                dis.readFully(keyBytes);

                byte[] buffer = new byte[500];
                int intBuffer = is.read(buffer);
//              TODO:Solucio proposada per CHATGPT
                //String msg = new String(buffer, 0, intBuffer);
                String msg = new String(keyBytes, 0, dis.read(keyBytes));
//              TODO: Solucio proposada per David Boix
                //String msg = new String(buffer);
                System.out.println("Aquest es el missatge que ens envia des de el client: " + this.qtClients + ": " + msg);

                if (msg.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
                    Thread.sleep(2000);
                    this.qtClients--;
                    System.out.println("El client numero " + this.qtClients + " s'ha desonnectat...");
                    newSocket.close();
                    //TODO: Canviar el break per una condicio que ens deixi sortir del bucle
                    semafor = true;
                }
//                System.out.println("Hem tancat sessio");
//                newSocket.close();

//                byte[] buffer = new byte[500];
//                is.read(buffer);
////                String msgDesconnexio = new String(buffer).trim();
//                String msgDesconnexio = demanarDesconnexio(MISSATGE_DESCONNEXIO);
////                String msgDesconnexio = "DESCONNEXIO";
//
//                if (msgDesconnexio.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
//                    //Arriba el missatge de desconnexio i retornar true
//                    System.out.println("\nArribem dins del condicional de Atendre_Client");
//                    semafor = tancarConnexio(newSocket, MISSATGE_DESCONNEXIO);
//                    System.out.println("\nTanquem nou socket...");
//                }
//                System.out.println("\nSeguim amb la connexio...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR!\n Hem entrat en un error...");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("ERROR!\nHi ha hagut un error de connexio");
        } catch (Exception e) {
            System.err.println("ERROR!\nHi ha un metode que no ha funcionat correctament");
        }
    }
    //funcio booleana de prova per a que quan vegi el missatge de desconnexio, retorni un true
    public boolean prova(){
        try {
            /**
             * TODO: El servidor llegira un missatge de desconnexio per part del
             * client que sera DESCONNEXIO quan el client premi al boto de
             * desconnexio de la interficie
             */
            final String MISSATGE_DESCONNEXIO = "exit";
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            DataInputStream dis = new DataInputStream(newSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(newSocket.getOutputStream());


                byte[] buffer = new byte[500];
                int intBuffer = is.read(buffer);
                String msg = new String(buffer, 0, intBuffer);

                if (msg.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
                    Thread.sleep(2000);
                    System.out.println("El client numero " + this.qtClients + " s'ha desonnectat...");
                    System.out.println("Numero " + clientsNumber + " s'ha desonnectat...");
                    
                    newSocket.close();
                    return true;
                }
                
                return false;
//                System.out.println("Hem tancat sessio");
//                newSocket.close();

//                byte[] buffer = new byte[500];
//                is.read(buffer);
////                String msgDesconnexio = new String(buffer).trim();
//                String msgDesconnexio = demanarDesconnexio(MISSATGE_DESCONNEXIO);
////                String msgDesconnexio = "DESCONNEXIO";
//
//                if (msgDesconnexio.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
//                    //Arriba el missatge de desconnexio i retornar true
//                    System.out.println("\nArribem dins del condicional de Atendre_Client");
//                    semafor = tancarConnexio(newSocket, MISSATGE_DESCONNEXIO);
//                    System.out.println("\nTanquem nou socket...");
//                }
//                System.out.println("\nSeguim amb la connexio...");
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR!\n Hem entrat en un error...");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("ERROR!\nHi ha hagut un error de connexio");
        } catch (Exception e) {
            System.err.println("ERROR!\nHi ha un metode que no ha funcionat correctament");
        }
        return false;
    }

    public void run() {
        try {
            /**
             * TODO: El servidor llegira un missatge de desconnexio per part del
             * client que sera DESCONNEXIO quan el client premi al boto de
             * desconnexio de la interficie
             */
            clientsNumber++;
            boolean semafor = false;
            final String MISSATGE_DESCONNEXIO = "exit";
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            DataInputStream dis = new DataInputStream(newSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(newSocket.getOutputStream());

            System.out.println("Quantitat de clients connectats: " + this.qtClients);
            System.out.println("Connectats: " + clientsNumber);
            
            while (!semafor) {

//                byte[] keyBytes = new byte[dis.readInt()];
//                dis.readFully(keyBytes);

                byte[] buffer = new byte[500];
                int intBuffer = is.read(buffer);
//              TODO:Solucio proposada per CHATGPT
                String msg = new String(buffer, 0, intBuffer);
//                String msg = new String(keyBytes, 0, dis.read(keyBytes));
//              TODO: Solucio proposada per David Boix
                //String msg = new String(buffer);
                System.out.println("Aquest es el missatge que ens envia des de el client: " + this.qtClients + ": " + msg);

                if (msg.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
                    Thread.sleep(2000);
                    System.out.println("El client numero " + this.qtClients + " s'ha desonnectat...");
                    System.out.println("Numero " + clientsNumber + " s'ha desonnectat...");
                    this.qtClients--;
                    //qtClients--;
                    clientsNumber--;
                    newSocket.close();
                    semafor = true;
                }
//                System.out.println("Hem tancat sessio");
//                newSocket.close();

//                byte[] buffer = new byte[500];
//                is.read(buffer);
////                String msgDesconnexio = new String(buffer).trim();
//                String msgDesconnexio = demanarDesconnexio(MISSATGE_DESCONNEXIO);
////                String msgDesconnexio = "DESCONNEXIO";
//
//                if (msgDesconnexio.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
//                    //Arriba el missatge de desconnexio i retornar true
//                    System.out.println("\nArribem dins del condicional de Atendre_Client");
//                    semafor = tancarConnexio(newSocket, MISSATGE_DESCONNEXIO);
//                    System.out.println("\nTanquem nou socket...");
//                }
//                System.out.println("\nSeguim amb la connexio...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR!\n Hem entrat en un error...");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("ERROR!\nHi ha hagut un error de connexio");
        } catch (Exception e) {
            System.err.println("ERROR!\nHi ha un metode que no ha funcionat correctament");
        }
    }
    /**
     * Funcio que farem servir per poder desconnectar el client del servidor
     * quan arribi un missatge en clau per poder realitzar l'operacio
     *
     * @param socket Socket que arribara per part del client per poder indicar
     * quin socket volem desconnectar
     * @param desconnexio Paraula clau que farem servir per realitzar la
     * desconnexio
     * @return Retornarem cert en cas de que l'operacio s'hagui realitzat
     * correctament i en cas contrari, no es realitzara correctament la
     * desconnexio i no es produira
     */
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
}
//public class Atendre_Clients extends Thread {
//
//    private Socket newSocket;
//    private String DEMANAR_CONNEXIO;
//
//    public Atendre_Clients(Socket newSocket) {
//        this.newSocket = newSocket;
//    }
//    
//
//    public Atendre_Clients(Socket cs, String DEMANAR_CONNEXIO) {
//        newSocket = cs;
//        this.DEMANAR_CONNEXIO = DEMANAR_CONNEXIO;
//    }
//
//    public void run() {
//        try {
//            InputStream is = newSocket.getInputStream();
//            OutputStream os = newSocket.getOutputStream();
//            while (true) {
//                boolean cagar = llegirDesconnexio(is, DEMANAR_CONNEXIO);
//                if (cagar) {
//                    System.out.println("Client disconnected");
//                    newSocket.close();
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private boolean llegirDesconnexio(InputStream is, String msgDesconnexio) throws Exception {
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
