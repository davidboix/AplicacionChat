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
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Servidor {

    private static int qtClients;
    public static String ipServidor;
    static ArrayList<Socket> arrSocket = new ArrayList<>();
    public static ArrayList<String> arrNoms = new ArrayList<>();
    private ArrayList<String> arrMsg = new ArrayList<>();
    static ArrayList<String> arrMsg2 = new ArrayList<>();
    public static ArrayList<String> arrNomsClients = new ArrayList<>();
    private static ArrayList<OutputStream> arrNomsOS = new ArrayList<>();

    /**
     * Creat constructor buit per poder crear instancies de la classe Servidor.
     */
    public Servidor() {

    }

    /**
     * Constructor creat per poder inicialitzar el servidor amb el atribut IP
     * que el usuari passara per parametres.
     *
     * @param ipServidor Atribut de tipus String que utilitzarem per
     * inicialitzar la IP.
     */
    public Servidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    /**
     * Constructor desenvolupat per poder realitzar instancies de la classe
     * Servidor passant per parametres el numero de clients conectats en aquell
     * moment.
     *
     * @param qtClients Atribut anomenat qtClients de tipus int per poder
     * inicialitzar la quantitat de clients conectats.
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

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
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
    public static void decrementarClientsConnectats(Socket socket) {
        System.out.println("S'ha desconectat el client amb socket: " + socket);
        qtClients--;
        //System.out.println("Clients connectats actualment: " + this.qtClients);
    }

    public static void main(String[] args) {
        iniciServidor();
    }

    /**
     *
     * Funcio creada per poder augmentar les connexions que es realitzen en el
     * servidor i portar un control sobre elles
     *
     * @param qtClients La quantitat de clients el qual ens trobem en aquell
     * moment.
     * @return Retorna la quantitat de clients actuals augmentada en 1.
     */
    private static int augmentarConnexio(int qtClients) {
        return qtClients;
    }

    /**
     * Funcio desenvolupada per guardar els sockets dels clients que es conecten
     * en el servidor en un ArrayList de tipus Socket.
     *
     * @param arrSocket ArrayList de tipus Socket on estaran guardats tots els
     * sockets dels clients conectats.
     * @param socket Socket del client que s'ha conectat en el servidor que
     * guardarem en el ArrayList de tipus Socket.
     * @return Retornarem el ArrayList de tipus Socket amb els sockets dels
     * clients guardats dins.
     */
    private ArrayList guardarClientsArrayList(ArrayList<Socket> arrSocket, Socket socket) {
        if (!socket.isClosed()) {
            arrSocket.add(socket);
        }
        return arrSocket;
    }

    /**
     * TODO: Funcio obsoleta Funcio que haurem de desenvolupar mes tard per
     * poder saber si estem fent un tractament correcte
     *
     * @param socket
     * @param is
     * @param os
     * @param msgDesconnexio
     * @param i
     * @return
     * @throws Exception
     */
//    private static boolean llegirDesconnexio(Socket socket, InputStream is, OutputStream os, String msgDesconnexio, int i) throws Exception {
//        try {
//            byte[] msg = new byte[500];
//            is.read(msg);
//            String desconnexio = new String(msg);
//            System.out.println(desconnexio);
//            if (msgDesconnexio.equalsIgnoreCase(desconnexio)) {
//                i--;
//                return true;
//            } else {
//                System.out.println("NO TANQUEM CONNEXIO");
//            }
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("\nERROR!\n El metode valoraNom ha petat :( ");
//        }
//        return false;
//    }
    /**
     * TODO: Funcio obsoleta.
     *
     *
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
//    private void inserirDadesMemoria(ArrayList<String> arrUsuaris, ArrayList<Integer> arrSockets, int portClient, String nomClient) {
//        arrUsuaris.add(nomClient);
//        arrSockets.add(portClient);
//        this.getDadesArrayList(arrUsuaris, arrSockets);
//    }
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

    /**
     * Funcio realitzada per poder eliminar el socket del ArrayList on tenim
     * guardats els sockets dels clients conectats.
     *
     * @param arrSocket ArrayList de tipus Socket que tractarem per poder
     * eliminar el socket del client en questio.
     * @param socket Socket del client que volem eliminar del ArrayList.
     * @return Retornara TRUE si es el cas de que el socket que hem passat per
     * parametres es troba en el ArrayList i per tant es borri, en cas contrari,
     * retornara FALSE si el client NO s'ha eliminat del ArrayList.
     */
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

    /**
     * Funcio realitzada per poder llegir els missatges que envia el client al
     * servidor per aixi poder mostrar-los en la interficie.
     *
     * @param is InputStream que utilitzarem per poder llegir els missatges del
     * client.
     * @return Retornarem el missatge que ens ha enviat el client si es el cas
     * que el client ha enviat un missatge, en cas contrari, retornarem un
     * String buit.
     * @throws IOException Llançarem la excepcio de error si ha sigut el cas de
     * que hi hagui hagut un error en el moment de llegir un missatge del
     * client.
     */
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

    /**
     * Funcio realitzada per poder assignar el missatge que ha enviat el client
     * a un ArrayList de tipus String i aixi tenir un control dels missatges
     * dels missatges enviats per el client.
     *
     * @param msg El missatge en format String que ens ha enviat el client.
     * @param arrMsg ArrayList de tipus String que guardarem els missatges del
     * client.
     */
    private void setMsgClients(String msg, ArrayList<String> arrMsg) {
        if (!msg.isEmpty()) {
            arrMsg.add(msg);
        }
    }

    /**
     * TODO: Revisar funcio ja que no es crida en cap lloc.
     *
     *
     * Funcio que ens mostra el ultim client que s'ha conectat en el nostre
     * servidor i que haura d'enviar al client quan es conecta...
     *
     * @param arrSocket ArrayList amb tots els clients conectats...
     */
//    private void getUltimClientConectat(ArrayList<Socket> arrSocket) {
//        Socket socketClient = null;
//        for (Socket socket : arrSocket) {
//            socketClient = socket;
//        }
//        System.out.println("Aquest es el ultim client que s'ha connectat al servidor: " + socketClient);
//    }
    /**
     * TODO: Revisar funcio ja que no es crida en cap lloc.
     *
     * @param arrNomsClients
     * @param is
     * @return
     */
    public static String setNomClients(ArrayList<String> arrNomsClients, InputStream is) {
        String nomUsuari = null;
        try {
            byte[] buffer = new byte[1024];
            int intBuffer = is.read(buffer);
            nomUsuari = new String(buffer, 0, intBuffer);
            if (!nomUsuari.isEmpty()) {
                setNomArrClients(arrNomsClients, nomUsuari);
            }
            if (nomUsuari != null) {
                return nomUsuari;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * TODO:Revisar funcio ja que no es crida en cap lloc.
     *
     * @param is
     * @return
     */
//    public String setNomClients(InputStream is) {
//        String nomUsuari = null;
//        try {
//            byte[] buffer = new byte[1024];
//            int intBuffer = is.read(buffer);
//            nomUsuari = new String(buffer, 0, intBuffer);
//
//            if (!nomUsuari.isEmpty()) {
//                this.setNomClients(nomUsuari);
//            }
//
//            if (nomUsuari != null) {
//                return nomUsuari;
//            }
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "";
//    }
    /**
     * Funcio realitzada per poder guardar el nom del client que es conecta en
     * el servidor en un ArrayList de tipus String.
     *
     * @param arrNomsClients ArrayList de tipus String que utilitzarem per
     * guardar els noms dels clients que es conectin en el servidor.
     * @param nomClient Nom del client que guardarem dins del ArrayList.
     */
    private static void setNomArrClients(ArrayList<String> arrNomsClients, String nomClient) {
        if (!nomClient.isEmpty()) {
            arrNomsClients.add(nomClient + "\n");
        }
    }

    /**
     * TODO: Revisar funcio ja que no es crida en cap lloc.
     *
     * @param nomClient
     */
//    private void setNomClients(String nomClient) {
//        if (!nomClient.isEmpty()) {
//            //arrNomsClients.add(nomClient + "\n");
//            this.arrNomsClients.add(nomClient + ",\n");
//        }
//    }
    /**
     * Funcio realitzada per poder veure per consola quins clients estan
     * guardats en el ArrayList de tipus String
     *
     * @param arrNomsClients ArrayList de tipus String on estan guardats els
     * clients.
     */
    public static void getNomArrClients(ArrayList<String> arrNomsClients) {
        if (arrNomsClients.size() > 0) {
            System.out.println("Noms clients: ");
            for (String row : arrNomsClients) {
                System.out.println(row);
            }
        } else {
            System.out.println("No hi han clients en el array...");
        }
    }

    /**
     * TODO: Revisar funcio ja que no
     */
//    public void getNomArrClients() {
//        if (this.arrNomsClients.size() > 0) {
//            System.out.println("Noms clients: ");
//            for (String row : this.arrNomsClients) {
//                System.out.println(row);
//            }
//        } else {
//            System.out.println("No hi han clients en el array...");
//        }
//    }
    /**
     * Funcio que utilitzem per poder borrar els clients que es troben en el
     * ArrayList un cop el client realitzi la operació de sortida.
     *
     * @param arrNomsClients ArrayList de tipus String on estan guardats els
     * clients conectats.
     * @param nom Nom del client que estem buscant en el ArrayList.
     */
    public static void deleteNomClient(ArrayList<String> arrNomsClients, String nom) {
        for (int i = 0; i < arrNomsClients.size(); i++) {
            if (arrNomsClients.get(i).trim().equalsIgnoreCase(nom)) {
                arrNomsClients.remove(i);
            }
        }
        System.out.println("Despres de eliminar...");
        getNomArrClients(arrNomsClients);
    }

    /**
     * Funcio realitzada per poder aixecar el servidor amb una IP definida per
     * el usuari.
     *
     * @param ipServidor Parametre que utilitzem per inicialitzar el servidor
     * amb una IP definida.
     */
    public void iniciServidor(String ipServidor) {
        Servidor servidor = new Servidor();
        try {
            System.out.println("Creem el socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress(ipServidor, 5556);
            serverSocket.bind(addr);
            boolean semafor = false;
            System.out.println("Servidor preparat per escoltar!");
            while (true) {
                Socket newSocket = serverSocket.accept();
                InputStream is = newSocket.getInputStream();
                servidor.augmentarClientsConnectats();
                servidor.guardarClientsArrayList(servidor.arrSocket, newSocket);
                new Atendre_Clients(newSocket, servidor.arrSocket, servidor.arrNomsClients).start();
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
     * TODO: Eliminar funcio un cop hem acabat de realitzar totes les proves...
     */
    public static void iniciServidor() {
        Servidor servidor = new Servidor();
        try {
            System.out.println("Creem el socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            serverSocket.bind(addr);
            boolean semafor = false;
            System.out.println("Servidor preparat per escoltar!");
            while (true) {
                Socket newSocket = serverSocket.accept();
                InputStream is = newSocket.getInputStream();
                servidor.augmentarClientsConnectats();
                servidor.guardarClientsArrayList(servidor.arrSocket, newSocket);
                new Atendre_Clients(newSocket, servidor.arrSocket, servidor.arrNomsClients).start();
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
     * Funcio realitzada per poder guardar els noms dels clients en un ArrayList
     * de tipus String.
     *
     * @param nomUsuari Nom del client que s'ha conectat en el servidor.
     */
    private void guardarNomsClients(String nomUsuari) {
        this.arrNomsClients.add(nomUsuari);
    }

    /**
     * TODO: Revisar funcio ja que creiem que no la cridem en lloc.
     *
     * Funcio realitzada per poder inserir els clients en el ArrayList de tipus
     * String per despres mostrar per consola els clients que estan guardats en
     * el ArrayList.
     *
     * @param is InputStream que u
     */
//    private void getNomClient(InputStream is) {
//        try {
//            byte[] buffer = new byte[1024];
//            int intBuffer = is.read(buffer);
//            String nomClient = new String(buffer, 0, intBuffer);
//            this.guardarNomsClients(nomClient);
//            for (String row : this.arrNomsClients) {
//                System.out.println("Nom clients..." + row);
//            }
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * TODO: Revisar funcio ja que creiem que no la utilitzem per a res. Funcio
     * creada per poder enviar els noms dels clients que es troben guardats en
     * el ArrayList a tots els clients conectats en aquell moment en el
     * servidor.
     *
     * @param OS O
     */
//    public void enviarNomsClientsConectats(CopyOnWriteArrayList<OutputStream> OS) {
//        if (this.arrNomsClients.size() > 0) {
//            for (String row : this.arrNomsClients) {
//                this.enviarMissatge(row, OS);
//            }
//        }
//    }
    /**
     * * TODO: Revisar funcio ja que creiem que no la utilitzem per a res.
     *
     * @param msg
     * @param arrClients
     */
//    private void enviarMissatge(String msg, CopyOnWriteArrayList<OutputStream> arrClients) {
//        for (OutputStream os : arrClients) {
//            try {
//                os.write(msg.getBytes());
//                os.flush();
//            } catch (Exception e) {
//
//            }
//        }
//    }
    /**
     * TODO: Revisar funcio ja que creiem que no la utilitzem per a res.
     *
     * @param nom
     * @param arrClients
     */
//    public void deleteNomClient(String nom, CopyOnWriteArrayList<OutputStream> arrClients) {
//        if (this.arrNomsClients.size() > 0) {
//            for (int i = 0; i < this.arrNomsClients.size(); i++) {
//                if (this.arrNomsClients.get(i).equalsIgnoreCase(nom)) {
//                    this.arrNomsClients.remove(i);
//                }
//            }
//        }
//        String msgDesconexio = "\nEl client amb nom: " + nom + " s'ha desconectat satisfactoriament\n";
//        System.out.println(msgDesconexio);
//        this.enviarMissatge(msgDesconexio, arrClients);
//    }
    /**
     * TODO: Revisar funcio ja que creiem que no la utilitzem per a res.
     *
     * @param arrClients
     */
//    public void coneixerUltimConectat(CopyOnWriteArrayList<OutputStream> arrClients) {
//        String nomUsuari = null;
//        for (String row : this.arrNomsClients) {
//            nomUsuari = row;
//        }
//        System.out.println("Ultim client connectat: " + nomUsuari);
//        this.enviarMissatge(nomUsuari, arrClients);
//    }
    public void enviarContrasenyaEncriptada(InputStream is, OutputStream os) {

        int port = 8080;
        ServerSocket server = null;
        Socket socket = null;
        try  {
            server = new ServerSocket(port);
            System.out.println("Servidor obert...");
            socket = server.accept();
            System.out.println("Client connectat...");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream dip = new DataInputStream(socket.getInputStream());

            byte[] keyBytes = new byte[dip.readInt()];
            dip.readFully(keyBytes);

            SecretKey clau = new SecretKeySpec(keyBytes, "AES");

            int msgLength = dip.readInt();
            byte[] msgEncriptat = new byte[msgLength];
            dip.readFully(msgEncriptat);

            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.DECRYPT_MODE, clau);
            byte[] msgDesencriptat = aesCipher.doFinal(msgEncriptat);

            String missatge = new String(msgDesencriptat);
            System.out.println("Missatge desencriptat: " + missatge);

            this.encriptarPassword(missatge, socket, clau, msgEncriptat, aesCipher, out);
            //socket.close();
            System.out.println("Servidor tornant a escoltar...");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                    System.out.println("Socket tancat.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (server != null && !server.isClosed()) {
                try {
                    server.close();
                    System.out.println("Servidor tancat.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void encriptarPassword(String missatge, Socket socket, SecretKey clau, byte[] msgEncriptat, Cipher aesCipher, DataOutputStream out) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b1 = missatge.getBytes();
            md.update(b1);
            byte[] resum = md.digest();
            String contrasenyaHash = Base64.getEncoder().encodeToString(resum);
            /**
             * Funcio que cridem per a que el servidor envii al client la
             * contrasenya encriptada
             */
            this.enviarMsgClient(contrasenyaHash, clau, msgEncriptat, aesCipher, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarMsgClient(String missatge, SecretKey clau, byte[] msgEncriptat, Cipher aesCipher, DataOutputStream out) {
        try {
            byte[] keyByte = clau.getEncoded();
            out.writeInt(keyByte.length);
            out.write(keyByte);
            aesCipher.init(Cipher.ENCRYPT_MODE, clau);
            byte[] msgAEncriptar = aesCipher.doFinal(missatge.getBytes());
            out.writeInt(msgAEncriptar.length);
            out.write(msgAEncriptar);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
