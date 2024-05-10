package encriptacio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ServidorExmple {
    // IP MongoDB al nuvol: 57.129.5.24
    //Port MongodBD al nuvol: 27017

    // Estructura MongoDB
    // Usuari de la BD: grup1
    // Password: gat123
    private static int qtClients;

    public ServidorExmple() {

    }

    /**
     *
     * @param qtClients
     */
    public ServidorExmple(int qtClients) {
        this.qtClients = qtClients;
    }

    /**
     * Funcio desenvolupada per poder augmentar el numero de clients que estan
     * connectats en aquell moment.
     */
    public void augmentarClientsConnectats() {
        this.qtClients++;
        System.out.println("Clients connectats actualment: " + this.qtClients);
    }

    /**
     * Funcio desenvolupada per poder decrementar el numero de clients que s'han
     * desconnectat en aquell moment.
     */
    public void decrementarClientsConnectats() {
        this.qtClients--;
        System.out.println("Clients connectats actualment: " + this.qtClients);
    }

    /**
     * Getter desenvolupat per poder obtenir la quantitat de clients connectats
     * en aquell moment.
     *
     * @return
     */
    public int getQtClients() {
        return qtClients;
    }

    /**
     * Setter desenvolupat per poder inicialitzar el numero de clients inicial o
     * similar.
     *
     * @param qtClients
     */
    public void setQtClients(int qtClients) {
        this.qtClients = qtClients;
    }

    public static void main(String[] args) {
        ServidorExmple server = new ServidorExmple();
        ArrayList<String> arrUsuaris = new ArrayList<>();
        ArrayList<Integer> arrSockets = new ArrayList<>();
        try {
            System.out.println("Creem el socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            serverSocket.bind(addr);

            boolean semafor = false;
            /**
             * TODO: Variable obsoleta en el moment que estem realitzant els
             * canvis pertinents.
             *
             * int pe = 1;
             */

            System.out.println("Servidor preparat per escoltar!");
            while (true) {
                /**
                 * TODO:Revisar funcionament (creiem que ha quedat obsolet) de
                 * aquestes variables per poder portar el control de clients
                 * connectats.
                 *
                 * pe++;
                 *
                 * server.setQtClients(pe);
                 *
                 * qtClients++;
                 *
                 * se.augmentarClientsConnectats();
                 */
                /**
                 * TODO: En aquest moment, estem començant a aceptar les
                 * connexions que estem rebent del client.
                 *
                 */
                Socket newSocket = serverSocket.accept();
                //String nomUser = server.llegirUsuaris(newSocket);
                //server.inserirDadesMemoria(arrUsuaris, arrSockets, newSocket.getPort(),nomUser);
                /**
                 * TODO: Hem de mirar de llegir el missatge en el servidor per
                 * poder baixar el qtClients i d'aquesta manera tenir un control
                 * sobre el contador.<- Creiem que aquest funcionament l'hem de
                 * realitzar en el fil i NO en el servidor
                 */
                server.augmentarClientsConnectats();
                /**
                 * TODO: Aquest es el fil que utilitzarem per poder escoltar
                 * tots els missatges que envien els clients al servidor.
                 */
                new Atendre_Clients(newSocket).start();

                /**
                 * TODO: Prova feta per Oleh de boolean per a si veu missatge de
                 * desconnexio, li resti, pero no funciona be ja que el que fara
                 * es aturar el while i no llegira l'altre client
                 *
                 * if(new Atendre_Clients(newSocket, qtClients).prova()){
                 * qtClients--; }
                 */
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
        } catch (SocketException se) {
            se.printStackTrace();
            System.err.println("\nERROR!\nLa connexio ha sigut detinguda inesperadament!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un error i per tant no s'ha executat correctament el servidor!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nERROR!\nHi ha hagut un error general i per tant el servidor no ha funcionat com toca!");
        }

    }

    /**
     * TODO: Hem de utilitzar aquesta funcio per deixar la funcio general lo mes
     * simplificada possible.
     *
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

    /**
     * TODO: Hem de revisar que el nom del client que fem servir actualment, es
     * unicament el numero de client que li ha tocat a aquell client, l'haurem
     * de modificar per el nom que fa servir per poder logejar-se.
     *
     * Funcio que hem desenvolupat per poder guardar en memoria les dades que fa
     * servir el usuari per poder connectar-se al servidor on aquestes dades
     * seran el nom del client i el port del socket del client que es connecta.
     *
     * @param arrUsuaris ArrayList que farem servir per poder guardar les dades
     * del usuari.
     * @param arrSockets ArrayList que farem servir per poder guardar el port
     * del socket del cient en especific.
     * @param portClient Port del client que farem servir per poder afegir-lo a
     * un arrayList.
     * @param nomClient Nom del client que farem servir per poder afegir-lo a un
     * ArrayList.
     */
    private void inserirDadesMemoria(ArrayList<String> arrUsuaris, ArrayList<Integer> arrSockets, int portClient, String nomClient) {
        arrUsuaris.add(nomClient);
        arrSockets.add(portClient);
        this.mostrarDadesArrayList(arrUsuaris, arrSockets);
    }

    /**
     * Funcio realitzada per poder mostrar les dades dels ArrayLists creats per
     * veure si s'esta realitzant correctament.
     */
    private void mostrarDadesArrayList(ArrayList<String> arrUsuaris, ArrayList<Integer> arrSockets) {
        for (String row : arrUsuaris) {
            System.out.println(row + " ");
        }
        for (int row : arrSockets) {
            System.out.println(row + "\n");
        }
    }

    /**
     * Funcio creada per poder llegir el nom del usuari i d'aquesta manera poder
     * tenir un control dels diferents usuaris que es connectin al servidor.
     */
    private String llegirUsuaris(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        byte[] buffer = new byte[500];
        int intBuffer = is.read(buffer);
        String msg = new String(buffer, 0, intBuffer);

        if (msg.isEmpty()) {
            return null;
        }

        return msg;
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

