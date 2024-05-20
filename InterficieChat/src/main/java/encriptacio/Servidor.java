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

/**
 * TODO: Prova de realitzar aquestos passos per no haver de crear nous
 * arraylists cada cop que
 *
 * 1 - Crearem el ArrayList al Servidor en ves de el fil Atendre_Clients.
 *
 * 2 - En el bucle while assignare una variable per guardar el ArrayList.
 *
 * 3 - Llavors li passarem el ArrayList per parametres a la instancia de
 * Atendre_Clients.
 *
 * Solucio Alternativa:
 *
 * Guardar el socket en la BD de MongoDB.
 *
 * Quan un client es desonnecti, el borrarem tant de la MongoBD, com de memoria
 * i del servidor.
 *
 * O inserirem tot el ArrayList a MongoDB, i quan un usuari es desconnecti,
 * borrarem tot el ArrayList i el tornarem a inserir sense aquell client que
 * s'ha desconnectat.
 *
 */
public class Servidor {
    // IP MongoDB al nuvol: 57.129.5.24
    //Port MongodBD al nuvol: 27017

    // Estructura MongoDB
    // Usuari de la BD: grup1
    // Password: gat123
    private static int qtClients;
    public static String ipServidor;
    static ArrayList<Socket> arrSocket = new ArrayList<>();
    private ArrayList<String> arrMsg = new ArrayList<>();
    static ArrayList<String> arrMsg2 = new ArrayList<>();

    public Servidor() {

    }

    /**
     *
     * @param qtClients
     */
    public Servidor(int qtClients) {
        this.qtClients = qtClients;
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

    public ArrayList<Socket> getArrSocket() {
        return arrSocket;
    }

    public void setArrSocket(ArrayList<Socket> arrSocket) {
        this.arrSocket = arrSocket;
    }

    public ArrayList<String> getArrMsg() {
        return arrMsg;
    }

    public void setArrMsg(ArrayList<String> arrMsg) {
        this.arrMsg = arrMsg;
    }

    public static String getIpServidor() {
        return ipServidor;
    }

    public static void setIpServidor(String ipServidor) {
        Servidor.ipServidor = ipServidor;
    }

    /**
     * Funcio desenvolupada per poder augmentar el numero de clients que estan
     * connectats en aquell moment.
     */
    public void augmentarClientsConnectats() {
        this.qtClients++;
        System.out.println("\nClients connectats actualment: " + this.qtClients);
    }

    /**
     * Funcio desenvolupada per poder decrementar el numero de clients que s'han
     * desconnectat en aquell moment.
     */
    public void decrementarClientsConnectats(Socket socket) {
        System.out.println("S'ha desconectat el client amb socket: " + socket);
        this.qtClients--;
        System.out.println("Clients connectats actualment: " + this.qtClients);
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();

        try {
            System.out.println("Creem el socket servidor");
            ServerSocket serverSocket = new ServerSocket();

            //Adreça utilitzada per fer les proves.
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);

            //Adreça utilitzada per aixecar el servidor utilitzant interficie grafica.
            //InetSocketAddress addr = new InetSocketAddress(ipServidor, 5556);
            serverSocket.bind(addr);

            boolean semafor = false;

            System.out.println("Servidor preparat per escoltar!");
            while (true) {
                Socket newSocket = serverSocket.accept();
                
                servidor.augmentarClientsConnectats();
                servidor.guardarClientsArrayList(servidor.arrSocket, newSocket);
                
                //servidor.mostrarClientsConectats(servidor.arrSocket);
                
                servidor.getUltimClientConectat(servidor.arrSocket);
                                
                new Atendre_Clients(newSocket).start();
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

    private ArrayList guardarClientsArrayList(ArrayList<Socket> arrSocket, Socket socket) {
        if (!socket.isClosed()) {
            arrSocket.add(socket);
        }
        return arrSocket;
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
        this.getDadesArrayList(arrUsuaris, arrSockets);
    }

    /**
     * Funcio realitzada per poder mostrar les dades dels ArrayLists creats per
     * veure si s'esta realitzant correctament.
     */
    private void getDadesArrayList(ArrayList<String> arrUsuaris, ArrayList<Integer> arrSockets) {
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

    /**
     * Funcio que crearem per poder visualitzar tots els missatges que envien
     * els diferents clients per poder treballar amb un ArrayList general i NO
     * independent de cada client que es connecti.
     */
    public void getDadesLlistes(ArrayList<Socket> arrSocket, ArrayList<String> arrMsg) throws IOException {
        System.out.println("\nSockets clients...");
        for (Socket row : arrSocket) {
            System.out.println(row);
        }
        System.out.println("\nMissatges clients...");
        for (String row : arrMsg) {
            System.out.println(row);
        }
    }

    public boolean eliminarSocket(ArrayList<Socket> arrSocket, Socket socket) {
        try {
            socket.close();
            arrSocket.remove(socket);
            return true;
        } catch (SocketException se) {
            se.printStackTrace();
            System.out.println("ERROR!\nS'ha produit un error quan s'ha volgut tancar una connexio...");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("ERROR!\nS'ha produit un error alhora de voler tancar la connexio...");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR!\nS'ha produit un error que no estem controlant...");
        }
        return false;
    }

    private String llegirMissatgeClient(InputStream is) throws IOException {
        byte[] buffer = new byte[500];
        int intBuffer = is.read(buffer);
        String msg = new String(buffer, 0, intBuffer);
        if (!msg.isEmpty()) {
            return msg;
        } else {
            System.out.println("\nServidor: El missatge esta buit...");
        }

        return "";
    }

    private void setMsgClients(String msg, ArrayList<String> arrMsg) {
        if (!msg.isEmpty()) {
            arrMsg.add(msg);
        }
    }

    private void mostrarClientsConectats(ArrayList<Socket> arrSocket) {
        System.out.println("Aquestos son els clients que estan conectats: ");
        for (Socket socket : arrSocket) {
            System.out.println(socket);
        }
    }

    /**
     * Funcio que ens mostra el ultim client que s'ha conectat en el nostre
     * servidor i que haura d'enviar al client quan es conecta...
     *
     * @param arrSocket ArrayList amb tots els clients conectats...
     */
    private void getUltimClientConectat(ArrayList<Socket> arrSocket) {
        Socket socketClient = null;
        for (Socket socket : arrSocket) {
            socketClient = socket;
        }
        System.out.println("Aquest es el ultim client que s'ha connectat al servidor: " + socketClient);
    }
}

//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class Servidor {
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
