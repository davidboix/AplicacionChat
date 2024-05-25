package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Atendre_Clients extends Thread {

    private Socket socket;
    static ArrayList<Socket> arrSocket;
    private OutputStream os;
    private static CopyOnWriteArrayList<OutputStream> arrClients = new CopyOnWriteArrayList<>();
    private ArrayList<String> arrNomClients = new ArrayList<>();

    /**
     * Constructor buit per poder realitzar instancies de la classe
     * Atendre_Clients.
     */
    public Atendre_Clients() {

    }

    /**
     * Constructor parametritzat creat per poder realitzar instancies de la
     * classe Atendre_Clients.
     *
     * @param socket Socket amb el qual inicialitzarem el atribut socket.
     */
    public Atendre_Clients(Socket socket) {
        this.socket = socket;
    }

    /**
     * Constructor parametritzat creat per poder realitzar instancies de la
     * classe Atendre_Clients.
     *
     * @param socket Socket amb el qual inicialitzarem el atribut socket.
     * @param arrSocket ArrayList de tipus Socket per inicialitzar el atribut
     * arrSocket.
     */
    public Atendre_Clients(Socket socket, ArrayList<Socket> arrSocket) {
        this.socket = socket;
        this.arrSocket = arrSocket;
    }

    /**
     * Constructor parametritzat creat per poder realitzar instancies de la
     * classe Atendre_Clients.
     *
     * @param socket Socket amb el qual inicialitzarem el atribut socket.
     * @param os OutputStream que utilitzarem per inicialitzar el atribut
     * anomenat os.
     */
    public Atendre_Clients(Socket socket, OutputStream os) {
        this.socket = socket;
        this.os = os;
    }

    /**
     * Constructor parametritzat creat per poder realitzar instancies de la
     * classe Atendre_Clients.
     *
     * @param socket Socket amb el qual inicialitzarem el atribut socket.
     * @param arrSocket ArrayList de tipus Socket per inicialitzar el atribut
     * arrSocket,.
     * @param arrNomClients ArrayList de tipus String per inicialitzar el
     * atribut arrNomClients.
     */
    public Atendre_Clients(Socket socket, ArrayList<Socket> arrSocket, ArrayList<String> arrNomClients) {
        this.socket = socket;
        this.arrSocket = arrSocket;
        this.arrNomClients = arrNomClients;
    }

    /**
     * Metode sobreescrit que heredem de la classe Thread per poder utilitzar la
     * classe com un fil.
     */
    @Override
    public void run() {
        try {
            Servidor servidor = new Servidor();
            InputStream is = this.socket.getInputStream();
            this.os = this.socket.getOutputStream();
            this.arrClients.add(os);
            String nomClient = Servidor.setNomClients(Servidor.arrNoms, is);
            this.enviarNomClient(nomClient);
            Servidor.getNomArrClients(Servidor.arrNoms);
            this.enviarNomsClientsConectats(servidor.arrNoms);
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
                        this.eliminarSocketArray(Servidor.arrSocket, this.socket);
                        this.deleteNomClient(Servidor.arrNoms, nomClient);
                        this.enviarNomsClientsConectats(servidor.arrNoms);
                        semafor = true;
                        break;
                    }
                }
                this.guardarMissatgesArrayList(servidor.arrMsg2, msg);
                this.mostrarMissatgesArrayList(servidor.arrMsg2);
                this.mostrarMissatgeUsuari(socket, msg);
                this.enviarMissatge(msg);
            }
            servidor.decrementarClientsConnectats(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio desenvolupada per poder eliminar els clients que son emmagatzemats
     * dins del ArrayList un cop aquests clients han realitzat l'operació de
     * l'exit.
     *
     * @param arrClients ArrayList on estan emmagatzemats els clients.
     * @param os El client que busquem dins del ArrayList.
     */
    private void eliminarClientArray(CopyOnWriteArrayList<OutputStream> arrClients, OutputStream os) {
        if (!arrClients.isEmpty()) {
            arrClients.remove(os);
        } else {
            System.out.println("El Array no te cap client per poder borrar.");
        }
    }

    /**
     * Funcio desenvolupada per poder guardar els diferents missatges que han
     * enviat els usuaris a un ArrayList.
     *
     * @param arrMsg ArrayList on es guardaran els missatges.
     * @param msg Missatge pertinent enviat per el usuari a guardar.
     * @return
     */
    private ArrayList guardarMissatgesArrayList(ArrayList<String> arrMsg, String msg) {
        if (!msg.isEmpty()) {
            arrMsg.add(msg);
        }
        return arrMsg;
    }

    /**
     * Funcio realitzada per poder veure per consola tots els missatges
     * emmagatzemats dins del ArrayList on guardem els missatges enviats per els
     * diferents clients conectats.
     *
     * @param arrMsg ArrayList que utilitzarem per poder veure els missatges per
     * consola.
     */
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

    /**
     * Funcio realitzada per veure per consola el missatge que acaba de enviar
     * un client especificant en la sortida, quin socket ho han realitzat i el
     * missatge en questio.
     *
     * @param socket Socket del client que ha enviat el missatge.
     * @param msg El missatge que ha enviat aquell client.
     */
    private void mostrarMissatgeUsuari(Socket socket, String msg) {
        if (!msg.isEmpty()) {
            System.out.println("\nMissatge enviat per el usuari amb el port del socket: " + socket.getPort() + " que hem rebut en el servidor: " + msg);
        }
    }

    /**
     * Funcio desenvolupada per poder enviar el missatge que ha enviat un
     * client, a tots els clients que es troben emmagatzemats en el ArrayList i
     * que estan conectats en el servidor.
     *
     * @param msg Missatge enviat per el usuari.
     */
    private void enviarMissatge(String msg) {
        for (OutputStream clients : arrClients) {
            try {
                clients.write(msg.getBytes());
                clients.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Funcio desenvolupada per poder enviar als clients que es troben conectats
     * en el servidor, l'entrada de un nou usuari en el servidor.
     *
     * @param nomClient El nom del client que s'ha conectat.
     */
    private void enviarMissatgeClientConectats(String nomClient) {
        String nomClientConnectat = "\nEl client amb nom: " + nomClient + " s'ha connectat satisfactoriament!\n";
        for (OutputStream clients : arrClients) {
            try {
                clients.write(nomClientConnectat.getBytes());
                clients.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Aquesta funcio la podrem utilitzar per poder borrar un socket el qual es
     * troba guardat en el ArrayList de sockets passant per parametres el
     * ArrayList en questio i el socket que volem eliminar.
     *
     * @param arrSockets ArrayList amb els sockets que tenim guardats.
     * @param socket El socket que volem eliminar.
     * @return En cas de que el socket hagui sigut eliminat correctament, ens
     * retornara TRUE, en canvi, si NO s'ha trobat el socket en el ArrayList,
     * ens retornara FALSE.
     */
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

    /**
     * Funcio desenvolupada per a la eliminacio del socket que es troba guardat
     * en el ArrayList de sockets.
     *
     * @param arrSockets ArrayList on estan guardats tots els sockets.
     * @param socket Socket que volem eliminar.
     * @return Retornara TRUE si l'operacio de eliminacio ha sigut satisfactori,
     * en cas contrari, el socket NO haura sigut eliminat o be, ens hem trobat
     * amb algun tipus de error.
     */
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

    /**
     * Funcio desenvolupada per poder saber si el socket que estem buscant
     * existeix dins del ArrayList amb els sockets restants.
     *
     * @param arrSockets ArrayList de tipus Socket amb els sockets guardats.
     * @param socket El socket que estem buscant dins del ArrayList.
     * @return En cas de que, el socket hagui sigut trobat dins del ArrayList,
     * retornarem TRUE, en cas contrari retornarem FALSE.
     */
    private boolean isSocketTrobat(ArrayList<Socket> arrSockets, Socket socket) {
        boolean isTrobat = false;
        for (Socket row : arrSockets) {
            if (row.equals(socket)) {
                isTrobat = true;
            }
        }
        return isTrobat;
    }

    /**
     * Funcio desenvolupada per poder enviar a tots els clients conectats en el
     * servidor, els clients que es troben conectats.
     *
     * @param arrClients ArrayList de tipus String amb els clients conectats.
     */
    private void enviarNomsClientsConectats(ArrayList<String> arrClients) {

        if (arrClients.size() > 0) {
            for (String row : arrClients) {
                this.enviarMissatge(row);
            }
        }
    }

    /**
     * Funcio desenvolupada per poder borrar el client que es troba conectat en
     * aquell moment amb el missatge clau que el client ens ha enviat.
     *
     * @param arrNomsClients ArrayList de tipus String amb els noms dels
     * clients.
     * @param nom El nom del client a borrar.
     */
    public void deleteNomClient(ArrayList<String> arrNomsClients, String nom) {
        if (arrNomsClients.size() > 0) {
            for (int i = 0; i < arrNomsClients.size(); i++) {
                if (arrNomsClients.get(i).trim().equalsIgnoreCase(nom.trim())) {
                    arrNomsClients.remove(i);
                }
            }
        }
        String msgDesconexio = "\nEl client amb nom: " + nom + " s'ha desconectat satisfactoriament\n";
        //TODO: Eliminar els souts.
        System.out.println("Despres de eliminar....");
        for (String row : arrNomsClients) {
            System.out.println(row);
        }

        this.enviarMissatge(msgDesconexio);
    }

    /**
     * Funcio desenvolupada per enviar el nom del client a tots els clients
     * conectats el missatge de que s'ha conectat.
     *
     * @param nom Nom del client que s'ha conectat.
     */
    private void enviarNomClient(String nom) {
        if (!nom.isEmpty() && nom != null) {
            this.enviarMissatgeClientConectats(nom);
        }
    }
}
