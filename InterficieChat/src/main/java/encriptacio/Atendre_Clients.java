package encriptacio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 *
 * Correu Begoña: berritja.cicles@gmail.com
 *
 */
public class Atendre_Clients extends Thread {

    private Socket socket;
    private ArrayList<String> arrMsg = new ArrayList<>();
    private ArrayList<Socket> arrSocket = new ArrayList<>();

    public Atendre_Clients() {

    }

    public Atendre_Clients(Socket socket) {
        this.socket = socket;
    }

    public Atendre_Clients(Socket socket, ArrayList<Socket> arrSocket) {
        this.socket = socket;
        this.arrSocket = arrSocket;
        
    }

    public Atendre_Clients(Socket socket, ArrayList<Socket> arrSocket, ArrayList<String> arrMsg) {
        this.socket = socket;
        this.arrSocket = arrSocket;
        this.arrMsg = arrMsg;
    }

    private ArrayList guardarMissatgesArrayList(ArrayList<String> arrMsg, String msg) {
        if (!msg.isEmpty()) {
            arrMsg.add(msg);
        }
        return arrMsg;
    }

    private ArrayList guardarClientsArrayList(ArrayList<Socket> arrSocket, Socket socket) {
        if (!socket.isClosed()) {
            arrSocket.add(socket);
        }
        return arrSocket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public ArrayList<String> getArrMsg() {
        return arrMsg;
    }

    public ArrayList<Socket> getArrSocket() {
        return arrSocket;
    }

    public void setArrMsg(ArrayList<String> arrMsg) {
        this.arrMsg = arrMsg;
    }

    public void setArrSocket(ArrayList<Socket> arrSocket) {
        this.arrSocket = arrSocket;
    }

    /**
     * TODO: El servidor llegira un missatge de desconnexio per part del client
     * que sera 'exit' quan el client premi al boto de desconnexio de la
     * interficie
     */
    public void run() {
        try {
            /**
             * TODO: Crearem una instancia del servidor per poder utilitzar els
             * metodes que hem creat en el servidor
             */
            Servidor servidor = new Servidor();
            
            boolean semafor = false;
            boolean connexioTancada = false;
            final String MISSATGE_DESCONNEXIO = "exit";

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            /**
             * TODO: En aquest moment estem guardant la quantitat de clients
             * connectats al servidor actualment.
             */
            int qtClients = servidor.getQtClients();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            while (!semafor) {
                /**
                 * TODO: Hem de revisar el us dels objectes DataInputStream i el
                 * DataOutputStream per poder llegir els missatges que s'envien
                 * desde el client i aixi poder solucionar varis errors que ens
                 * podrem trobar en el futur
                 *
                 * byte[] keyBytes = new byte[dis.readInt()];
                 * dis.readFully(keyBytes);
                 */

                byte[] buffer = new byte[500];
                int intBuffer = is.read(buffer);

                /**
                 * TODO:Solucio proposada per CHATGPT
                 */
                String msg = new String(buffer, 0, intBuffer);
                /**
                 * TODO: Solucio proposada per David Boix
                 *
                 * String msg = new String(buffer);
                 */
                if (msg.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
                    Thread.sleep(2000);
                    System.out.println("El client numero " + qtClients + " i amb socket : " + socket.getPort() + " s'ha desonnectat...");
                    connexioTancada = servidor.eliminarSocket(this.arrSocket, socket);
                    semafor = true;
                    System.out.println("Hem passat per aqui...");
                }
                /**
                 * TODO: Aqui es quan obrirem el nou fil per poder enviar el
                 * missatge de retorn al client.
                 */
                //En aquest punt guardarem els missatges en un ArrayList.
                boolean comprovarExit = this.comprovarMsgDesconnexio(msg);
                if (!comprovarExit) {
                    this.guardarMissatgesArrayList(servidor.arrMsg2, msg);
                    //En aquest punt mostrarem els valors dels ArrayLists.
                    servidor.getDadesLlistes(this.arrSocket, servidor.arrMsg2);
                    if (!connexioTancada) {
                        //new FilsEnviarInfoClients(socket, this.arrMsg, this.arrSocket).start();
                        new FilsEnviarInfoClients(socket, servidor.arrMsg2, this.arrSocket).start();
                    }                    
                }
                //new FilsEnviarInfoClients(this.arrMsg, this.arrSocket).start();

                /**
                 * TODO: Mostrarem el missatge que ens ha enviat el client per
                 * consola del servidor i el veurem satisfactoriament.
                 */
                //System.out.println("Aquest es el missatge que ens envia des de el client " + qtClients + ": " + msg);
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
            /**
             * TODO: Creiem que ha de anar aqui la desconnexio del client ja que
             * si es surt del bucle, significa que el client ha introduit la
             * paraula 'exit' i per tant, significa que el client ha volgut
             * desconnectar-se del servidor
             */
            //socket.close();
            /**
             * TODO: Crida de la funcio decrementarClientsConnectats de la
             * classe Servidor per poder realitzar la resta en 1 de clients
             * connectats en el servidor.
             */
            servidor.decrementarClientsConnectats();
        } catch (SocketException se) {
            se.printStackTrace();
            System.err.println("\nERROR!\nS'ha produit un problema al intentar connectar un fil al socket.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("\nERROR!\nHi ha hagut un error per poder connectar...");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            System.err.println("\nERROR!\nHi ha hagut un error de connexio");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nERROR!\nHi ha un metode que no ha funcionat correctament");
        }
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
    
    private boolean comprovarMsgDesconnexio (String msg) {
        String exit = "exit";
        if (msg.equalsIgnoreCase(exit)) return true;
        return false;
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
