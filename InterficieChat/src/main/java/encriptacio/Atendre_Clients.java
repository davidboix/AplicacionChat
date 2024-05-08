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

    private Socket newSocket;
    private String msgClient;
    private int qtClients;

    /**
     * TODO: Oleh: He intentat fer una global, pero quan entre un altre client
     * es reseteje.
     *
     * Aquesta variable NO serveix per a res ja que es un atribut del objecte i
     * per tant no es capaç de fer-ho servir
     *
     * int clientsNumber = 0;
     *
     */
    public Atendre_Clients(Socket newSocket) {
        this.newSocket = newSocket;
    }

    public Atendre_Clients(Socket cs, int qtClients) {
        this.newSocket = cs;
        this.qtClients = qtClients;
        /**
         * TODO: Inicialitzacio de la variable inservible.
         */
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
    /**
     * TODO: Revisar aquesta funcio ja que ens esta donant problemes.
     */
    public void correForestCorre() {
        try {
            /**
             * TODO: El servidor llegira un missatge de desconnexio per part del
             * client que sera DESCONNEXIO quan el client premi al boto de
             * desconnexio de la interficie
             */
            boolean semafor = false;
            final String MISSATGE_DESCONNEXIO = "exit";
            InputStream is = this.newSocket.getInputStream();
            OutputStream os = this.newSocket.getOutputStream();

            DataInputStream dis = new DataInputStream(newSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(newSocket.getOutputStream());

            System.out.println("Quantitat de clients connectats: " + this.qtClients);

            while (!semafor) {
                /**
                 * TODO: Haurem de utilitzar aquest espai de memoria per poder
                 * utilitzar de una forma mes correcta els missatges que envia
                 * desde el client.
                 *
                 * byte[] keyBytes = new byte[dis.readInt()];
                 * dis.readFully(keyBytes);
                 */

                byte[] buffer = new byte[500];
                int intBuffer = is.read(buffer);
//              TODO:Solucio proposada per CHATGPT
                String msg = new String(buffer, 0, intBuffer);

                /**
                 * TODO: Hem de revisar aquesta sentencia per decidir si
                 * utilitzarla o no.
                 *
                 * String msg = new String(keyBytes, 0, dis.read(keyBytes));
                 */
//              TODO: Solucio proposada per David Boix
                //String msg = new String(buffer);
                if (msg.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
                    Thread.sleep(2000);
                    System.out.println("El client numero " + this.qtClients + " s'ha desonnectat...");
                    this.qtClients--;
                    //TODO: Canviar el break per una condicio que ens deixi sortir del bucle
                    semafor = true;
                }

                System.out.println("Aquest es el missatge que ens envia des de el client: " + this.qtClients + ": " + msg);
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
            newSocket.close();
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
     * TODO: El servidor llegira un missatge de desconnexio per part del client
     * que sera 'exit' quan el client premi al boto de desconnexio de la
     * interficie
     */
    public void run() {
        try {

            /**
             * TODO: Variable assignada per Oleh que trobem que no fa falta en
             * aquest moment
             *
             * clientsNumber++;
             */
            /**
             * TODO: Crearem una instancia del servidor per poder utilitzar els
             * metodes que hem creat en el servidor
             */
            ServidorExmple server = new ServidorExmple();
            ArrayList<String> arrUsuaris = new ArrayList<>();
            ArrayList<Integer> arrSockets = new ArrayList<>();
            boolean semafor = false;
            final String MISSATGE_DESCONNEXIO = "exit";
            
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            /**
             * TODO: En aquest moment estem guardant la quantitat de clients
             * connectats al servidor actualment.
             */
            int qtClientsConnectats = server.getQtClients();

            DataInputStream dis = new DataInputStream(newSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(newSocket.getOutputStream());
            
            

            /**
             * TODO: Missatge de informacio posat per Oleh que trobem que no fa
             * falta posar-ho en aquest moment
             *
             * System.out.println("Connectats: " + clientsNumber);
             */
            while (!semafor) {

                /**
                 * TODO: Revisar aquest codi ja que no s'esta produint
                 * correctament.
                 *
                 * ServidorExmple server = new ServidorExmple(0);
                 * server.augmentarClientsConnectats();
                 */
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
                System.out.println("Aquest es el port del socket del client nº " + qtClientsConnectats + " que s'ha connectat: " + this.newSocket.getPort());
                this.inserirDadesMemoria(arrUsuaris, arrSockets, this.newSocket.getPort(), msg);
                /**
                 * TODO: Solucio proposada per David Boix
                 *
                 * String msg = new String(buffer);
                 */
                if (msg.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
                    Thread.sleep(2000);
                    System.out.println("El client numero " + qtClientsConnectats + " s'ha desonnectat...");

                    /**
                     * TODO: Revisar aquest codi ja que no sabem si funciona
                     * correctament
                     *
                     * server.decrementarClientsConnectats();
                     *
                     *
                     * TODO: Missatge assignat per Oleh que trobem que no falta
                     * en aquest moment i per tant queda comentat
                     *
                     * System.out.println("Numero " + clientsNumber + " s'ha
                     * desonnectat...");
                     *
                     * this.qtClients--;
                     *
                     * TODO: Us de la variable clientsNumber per poder realitzar
                     * la prova feta per Oleh
                     *
                     * this.clientsNumber--;
                     */
                    semafor = true;
                }
                /**
                 * TODO: Mostrarem el missatge que ens ha enviat el client per
                 * consola del servidor i el veurem satisfactoriament.
                 */

                System.out.println("Aquest es el missatge que ens envia des de el client " + qtClientsConnectats + ": " + msg);

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
            newSocket.close();
            /**
             * TODO: Crida de la funcio decrementarClientsConnectats de la
             * classe Servidor per poder realitzar la resta en 1 de clients
             * connectats en el servidor.
             */
            server.decrementarClientsConnectats();
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
    
    /**
     * TODO: Funcio booleana de prova per a que quan vegi el missatge de
     * desconnexio, retorni un true
     */
    public boolean prova() {
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
//                System.out.println("Numero " + clientsNumber + " s'ha desonnectat...");
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

    public void getMsgClient() {
        System.out.println("Arribem fins aqui");
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
