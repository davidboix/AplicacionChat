//package encriptacio;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.net.SocketException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// *
// * @author David Boix Sanchez
// * @version 1.0
// *
// * Correu Begoña: berritja.cicles@gmail.com
// *
// */
//public class Atendre_Clients extends Thread {
//
//    private Socket socket;
//    private ArrayList<String> arrMsg = new ArrayList<>();
//    private ArrayList<Socket> arrSocket = new ArrayList<>();
//
//    public Atendre_Clients() {
//
//    }
//
//    public Atendre_Clients(Socket socket) {
//        this.socket = socket;
//    }
//
//    public Atendre_Clients(Socket socket, ArrayList<Socket> arrSocket) {
//        this.socket = socket;
//        this.arrSocket = arrSocket;
//
//    }
//
//    public Atendre_Clients(Socket socket, ArrayList<Socket> arrSocket, ArrayList<String> arrMsg) {
//        this.socket = socket;
//        this.arrSocket = arrSocket;
//        this.arrMsg = arrMsg;
//    }
//
//    private ArrayList guardarMissatgesArrayList(ArrayList<String> arrMsg, String msg) {
//        if (!msg.isEmpty()) {
//            arrMsg.add(msg);
//        }
//        return arrMsg;
//    }
//
//    private ArrayList guardarClientsArrayList(ArrayList<Socket> arrSocket, Socket socket) {
//        if (!socket.isClosed()) {
//            arrSocket.add(socket);
//        }
//        return arrSocket;
//    }
//
//    public void setSocket(Socket socket) {
//        this.socket = socket;
//    }
//
//    public Socket getSocket() {
//        return socket;
//    }
//
//    public ArrayList<String> getArrMsg() {
//        return arrMsg;
//    }
//
//    public ArrayList<Socket> getArrSocket() {
//        return arrSocket;
//    }
//
//    public void setArrMsg(ArrayList<String> arrMsg) {
//        this.arrMsg = arrMsg;
//    }
//
//    public void setArrSocket(ArrayList<Socket> arrSocket) {
//        this.arrSocket = arrSocket;
//    }
//
//    /**
//     * TODO: El servidor llegira un missatge de desconnexio per part del client
//     * que sera 'exit' quan el client premi al boto de desconnexio de la
//     * interficie
//     */
//    public void run() {
//        try {
//            /**
//             * TODO: Crearem una instancia del servidor per poder utilitzar els
//             * metodes que hem creat en el servidor
//             */
//            Servidor servidor = new Servidor();
//            Scanner entradaCliente = new Scanner(socket.getInputStream());
//            boolean semafor = false;
//            boolean connexioTancada = false;
//            final String MISSATGE_DESCONNEXIO = "exit";
//
//            InputStream is = socket.getInputStream();
//            OutputStream os = socket.getOutputStream();
//
//            /**
//             * TODO: En aquest moment estem guardant la quantitat de clients
//             * connectats al servidor actualment.
//             */
//            int qtClients = servidor.getQtClients();
//
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//
//            while (!semafor) {
//                //ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//                //out.writeObject(this.arrMsg);
//                //ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//                /**
//                 * TODO: Hem de revisar el us dels objectes DataInputStream i el
//                 * DataOutputStream per poder llegir els missatges que s'envien
//                 * desde el client i aixi poder solucionar varis errors que ens
//                 * podrem trobar en el futur
//                 *
//                 * byte[] keyBytes = new byte[dis.readInt()];
//                 * dis.readFully(keyBytes);
//                 */
//
//                byte[] buffer = new byte[500];
//                int intBuffer = is.read(buffer);
//
//                /**
//                 * TODO:Solucio proposada per CHATGPT
//                 */
//                String msg = new String(buffer, 0, intBuffer);
//                /**
//                 * TODO: Solucio proposada per David Boix
//                 *
//                 * String msg = new String(buffer);
//                 */
//                if (msg.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
//                    Thread.sleep(2000);
//                    System.out.println("El client numero " + qtClients + " i amb socket : " + socket.getPort() + " s'ha desonnectat...");
//                    connexioTancada = servidor.eliminarSocket(this.arrSocket, socket);
//                    semafor = true;
//                }
//
//                boolean comprovarExit = this.comprovarMsgDesconnexio(msg);
//                String msgEnviament = null;
//                if (!comprovarExit) {
//                    this.guardarMissatgesArrayList(servidor.arrMsg2, msg);
//                    //En aquest punt mostrarem els valors dels ArrayLists.
//                    //servidor.getDadesLlistes(this.arrSocket, servidor.arrMsg2);
//                    //Proves de enviament dels missatges al client de volta.
//                    System.out.println("Aquestos son els missatges guardats en el array...");
//                    System.out.println();
//                    for (String msgClient : servidor.arrMsg2) {
//                        System.out.println(msgClient);
//                        msgEnviament = msgClient;
//                    }
//                    mostrarMissatge(msgEnviament);
//                    for (Socket socket : this.arrSocket) {
//                        os.write(msgEnviament.getBytes());
//                    }
////                    if (!connexioTancada) {
////                        //new FilsEnviarInfoClients(socket, this.arrMsg, this.arrSocket).start();
////                        new FilsEnviarInfoClients(socket, servidor.arrMsg2, this.arrSocket).start();
////                    }                    
//                }
//            }
//            /**
//             * TODO: Crida de la funcio decrementarClientsConnectats de la
//             * classe Servidor per poder realitzar la resta en 1 de clients
//             * connectats en el servidor.
//             */
//            servidor.decrementarClientsConnectats();
//        } catch (SocketException se) {
//            se.printStackTrace();
//            System.err.println("\nERROR!\nS'ha produit un problema al intentar connectar un fil al socket.");
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//            System.err.println("\nERROR!\nHi ha hagut un error per poder connectar...");
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//            System.err.println("\nERROR!\nHi ha hagut un error de connexio");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("\nERROR!\nHi ha un metode que no ha funcionat correctament");
//        }
//    }
//
//    /**
//     * TODO: Hem de revisar que el nom del client que fem servir actualment, es
//     * unicament el numero de client que li ha tocat a aquell client, l'haurem
//     * de modificar per el nom que fa servir per poder logejar-se.
//     *
//     * Funcio que hem desenvolupat per poder guardar en memoria les dades que fa
//     * servir el usuari per poder connectar-se al servidor on aquestes dades
//     * seran el nom del client i el port del socket del client que es connecta.
//     *
//     * @param arrUsuaris ArrayList que farem servir per poder guardar les dades
//     * del usuari.
//     * @param arrSockets ArrayList que farem servir per poder guardar el port
//     * del socket del cient en especific.
//     * @param portClient Port del client que farem servir per poder afegir-lo a
//     * un arrayList.
//     * @param nomClient Nom del client que farem servir per poder afegir-lo a un
//     * ArrayList.
//     */
//    private void inserirDadesMemoria(ArrayList<String> arrUsuaris, ArrayList<Integer> arrSockets, int portClient, String nomClient) {
//        arrUsuaris.add(nomClient);
//        arrSockets.add(portClient);
//        this.mostrarDadesArrayList(arrUsuaris, arrSockets);
//    }
//
//    /**
//     * Funcio realitzada per poder mostrar les dades dels ArrayLists creats per
//     * veure si s'esta realitzant correctament.
//     */
//    private void mostrarDadesArrayList(ArrayList<String> arrUsuaris, ArrayList<Integer> arrSockets) {
//        for (String row : arrUsuaris) {
//            System.out.println(row + " ");
//        }
//        for (int row : arrSockets) {
//            System.out.println(row + "\n");
//        }
//    }
//
//    private boolean comprovarMsgDesconnexio(String msg) {
//        String exit = "exit";
//        if (msg.equalsIgnoreCase(exit)) {
//            return true;
//        }
//        return false;
//    }
//
//    private void mostrarMissatge(String msg) {
//        try {
//            if (!msg.isEmpty()) {
//                System.out.println("Aquest es el ultim missatge enviat pel client: " + msg);
//            } else {
//                System.out.println("El missatge NO pot quedar buit.");
//            }
//        } catch (NullPointerException npe) {
//            System.out.println("El missatge NO pot ser NULL.");
//        } catch (Exception e) {
//            System.out.println("Hi ha hagut un error.");
//        }
//    }
//}
package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Atendre_Clients extends Thread {

    private Socket socket;
    private OutputStream os;
    /**
     * TODO: Revisar alternativa al us de aquest arraylist en especial.
     */
    private static CopyOnWriteArrayList<OutputStream> arrClients = new CopyOnWriteArrayList<>();

    public Atendre_Clients(Socket socket) {
        this.socket = socket;
    }

    public Atendre_Clients(Socket socket, OutputStream os) {
        this.socket = socket;
        this.os = os;
    }
    
    

    public void run() {
        try {
            Servidor servidor = new Servidor();
            InputStream is = socket.getInputStream();
            os = socket.getOutputStream();
            arrClients.add(os);
            
            this.enviarClientsConectats(servidor.arrSocket);

            byte[] buffer = new byte[1024];
            int intBuffer;
            boolean semafor = false;
            while (!semafor) {
                intBuffer = is.read(buffer);

                String msg = new String(buffer, 0, intBuffer);

                if (msg.equalsIgnoreCase("exit")) {
                    boolean isTrobat = this.isSocketTrobat(servidor.arrSocket, this.socket);
                    if (isTrobat) {
                        this.eliminarClientArray(this.arrClients, this.os);
                        this.enviarMissatgeDesconexio(servidor.arrSocket, this.socket);
                        this.eliminarSocketArray(servidor.arrSocket, this.socket);
                        //enviarMissatge("El usuari amb socket " + this.socket + " s'ha desconectat satisfactoriament...");
                        semafor = true;
                        /**
                         * TODO: Investigar alternativa al us del break degut a
                         * que NO es una bona practica per utilitzar per sortir
                         * de un bucle.
                         */
                        break;
                    }
                }

                this.guardarMissatgesArrayList(servidor.arrMsg2, msg);
                this.mostrarMissatgesArrayList(servidor.arrMsg2);
                this.mostrarMissatgeUsuari(socket, msg);
                enviarMissatge(msg);

            }
            //arrClients.remove(os);
            //socket.close();
            servidor.decrementarClientsConnectats(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarClientArray(CopyOnWriteArrayList<OutputStream> arrClients, OutputStream os) {
        if (!arrClients.isEmpty()) {
            arrClients.remove(os);
        } else {
            System.out.println("El Array no te cap client per poder borrar.");
        }

    }

    private ArrayList guardarMissatgesArrayList(ArrayList<String> arrMsg, String msg) {
        if (!msg.isEmpty()) {
            arrMsg.add(msg);
        }
        return arrMsg;
    }

    private void mostrarMissatgesArrayList(ArrayList<String> arrMsg) {
        try {
            System.out.println("Aquestos son els missatges guardats en el ArrayList: ");
            for (String row : arrMsg) {
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarMissatgeUsuari(Socket socket, String msg) {
        if (!msg.isEmpty()) {
            System.out.println("\nMissatge enviat per el usuari amb el port del socket: " + socket.getPort() + " que hem rebut en el servidor: " + msg);
        }
    }

    private void enviarMissatge(String msg) {
        System.out.println();
        for (OutputStream clients : arrClients) {
            try {
                clients.write((msg + "\n").getBytes());
                clients.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void enviarMissatgeDesconexio(ArrayList<Socket> arrClients, Socket socket) {
        Socket socketClient = null;
        for (Socket row : arrClients) {
            if (row.equals(socket)) {
                socketClient = row;
            }
        }

        //boolean isExistent = this.isSocketExistent(arrClients, socketClient);
        //if (isExistent) {
        String msgDesconexio = "\nEl client " + socketClient + " s'ha desconectat satisfactoriament";
        enviarMissatge(msgDesconexio);
//        } else {
//            System.out.println("\nEl client no s'ha trobat i per tant, no es pot enviar cap missatge");
//        }
    }

    private boolean eliminarSocketArray(ArrayList<Socket> arrSockets, Socket socket) {
        boolean isTrobat = false;
        Socket socketClient = null;
        for (Socket row : arrSockets) {
            if (row.equals(socket)) {
                socketClient = row;
            }
        }

        boolean isBorrat = this.isArrayListSocketExistent(arrSockets, socketClient);
        
        if (isBorrat) {
            return isTrobat = true;
        }
        
        System.out.println("\nEl client NO s'ha borrat del array correctament");
        return isTrobat;

    }

    private boolean isArrayListSocketExistent(ArrayList<Socket> arrSockets, Socket socket) {
        boolean isTrobat = false;
        try {
            if (!socket.equals(null)) {
                arrSockets.remove(socket);
                isTrobat = true;
            }
        } catch (NullPointerException npe) {
            System.out.println("El objecte es null");
        } catch (Exception e) {
            System.out.println("Error general");
        }
        return isTrobat;
    }
    
    private boolean isSocketTrobat (ArrayList<Socket> arrSockets, Socket socket) {
        boolean isTrobat = false;
        for (Socket row: arrSockets) {
            if (row.equals(socket)) {
                isTrobat = true;
            }
        }
        return isTrobat;
    }
    
    private void enviarClientsConectats(ArrayList<Socket> arrSockets) {
        System.out.println("------------Aquestos son els usuaris conectats actualment...-----------------");
        if (arrSockets.size () > 0) {
            for (Socket row: arrSockets) {
                enviarMissatge(String.valueOf(row));
            }
        } else {
            enviarMissatge("NO hi ha cap client conectat");
        }
    }
}
