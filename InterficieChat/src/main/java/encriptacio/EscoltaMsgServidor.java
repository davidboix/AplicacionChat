package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class EscoltaMsgServidor extends Thread {

    private JTextArea msgArr;
    private JTextArea clientArr;
    private Socket socket;
    private InputStream inputStream;
    private String nomUsuari;
    private Client client;
    private static CopyOnWriteArrayList<OutputStream> arrClients = new CopyOnWriteArrayList<>();
    

    /**
     * Definit constructor buit per poder inicialitzar objectes de tipus
     * EscoltaMsgServidor.
     */
    public EscoltaMsgServidor() {

    }

    /**
     * Definit constructor que utilitzarem per poder inicialitzar objectes de
     * tipus EscoltaMsgServidor i que es podra utilitzar per poder crear nous
     * Threads.
     *
     * @param socket Passarem un socket per parametres el qual sera el socket de
     * un client en questio.
     * @param inputStream Passarem un InputStream per poder rebre els missatges
     * que ens enviara el servidor.
     */
    public EscoltaMsgServidor(Socket socket, InputStream inputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
    }

    public EscoltaMsgServidor(Socket socket, InputStream inputStream, JTextArea msgArr) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.msgArr = msgArr;
    }

    public EscoltaMsgServidor(Socket socket, InputStream inputStream, JTextArea msgArr, String nomUsuari) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.msgArr = msgArr;
        this.nomUsuari = nomUsuari;
    }

    public EscoltaMsgServidor(JTextArea msgArr, JTextArea clientArr, Socket socket, InputStream inputStream, String nomUsuari, Client client) {
        this.msgArr = msgArr;
        this.clientArr = clientArr;
        this.socket = socket;
        this.inputStream = inputStream;
        this.nomUsuari = nomUsuari;
        this.client = client;
    }
    
    public EscoltaMsgServidor(Socket socket, InputStream inputStream, JTextArea msgArr, String nomUsuari, JTextArea clientArr) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.msgArr = msgArr;
        this.nomUsuari = nomUsuari;
        this.clientArr = clientArr;
    }

    public EscoltaMsgServidor(JTextArea msgArr) {
        this.msgArr = msgArr;
    }

    public EscoltaMsgServidor(JTextArea msgArr, Socket socket, InputStream inputStream, String nomUsuari) {
        this.msgArr = msgArr;
        this.socket = socket;
        this.inputStream = inputStream;

    }

    public EscoltaMsgServidor(Socket socket, InputStream inputStream, Client client) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.client = client;
    }

    /**
     * Getter definit per poder utilitzar els metodes de la classe Socket.
     *
     * @return Socket inicialitzat.
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Setter definit per si volem definir el socket del objecte de manera
     * manual, es a dir, sense utilitzar el constructor amb parametres.
     *
     * @param socket Socket parametritzat per inicialitzar el atribut de
     * l'objecte.
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Getter que utilitzarem per poder utilitzar els metodes de la classe
     * InputStream.
     *
     * @return InputStream que utilitzarem per poder utilitzar els metodes.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Setter definit per poder inicialitzar el atribut anomenat InputStream de
     * la classe EscoltaMsgServidor.
     *
     * @param is InputStream parametritzat per poder inicialitzar l'atribut
     * correctament.
     */
    public void setInputStream(InputStream is) {
        this.inputStream = is;
    }

    public JTextArea getMsgArr() {
        return msgArr;
    }

    public void setMsgArr(JTextArea msgArr) {
        this.msgArr = msgArr;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    /**
     * Funcio sobreescrita la qual ens aporta la classe Thread per poder
     * inicialitzar fils correctament definits i que realitzaran la funcio de
     * rebre missatges per part del Servidor.
     */
    @Override
    public void run() {
        try {
//            System.out.println("Aquest es el nom del usuari: " + this.client.getNomUsuari());
//            Servidor.arrNoms.add(this.client.getNomUsuari());
//            for (String row: Servidor.arrNoms) {
//                System.out.println(row);
//            }
            System.out.print("Escriu el missatge que vulguis al servidor: ");
            byte[] buffer = new byte[1024];
            while (!this.socket.isClosed()) {
                int intBytes = this.inputStream.read(buffer);
                if (intBytes != -1) {
                    String msg = new String(buffer, 0, intBytes);
                    System.out.println("\nMissatge del servidor: " + msg);
                    System.out.print("Escriu el missatge que vulguis al servidor: ");
                    this.afegirMissatgeTextArea(msg);
                    this.afegirClientsConnectats(msg);
                }
            }
        } catch (IOException ioe) {
            //ioe.printStackTrace();
            System.out.println("El socket s'ha tancat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio desenvolupada per poder retornar la data la qual ens trobem en
     * aquell moment en format cadena de text
     *
     * @return La data de avui en format cadena de text.
     */
    private String getData() {
        Calendar cal = Calendar.getInstance();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int any = cal.get(Calendar.YEAR);
        String data = dia + "/" + mes + "/" + any;

        if (!data.isEmpty()) {
            return data;
        }

        return "";
    }

    /**
     * Funcio desenvolupada per obtenir la hora, minuts i segons actuals en el
     * moment que fem la crida de la funcio
     *
     * TODO: Hem de revisar que el temps que apareix en el xat, aparegui de
     * forma correcta
     *
     * @return Hora, Minuts i Segons com una cadena de text.
     */
    private String getTemps() {
        LocalTime myObj = LocalTime.now();
        String hora = tractarTemps(myObj.getHour());
        String minuts = tractarTemps(myObj.getMinute());
        String segons = tractarTemps(myObj.getSecond());
        String temps = hora + ":" + minuts + ":" + segons;
        if (!temps.isEmpty()) {
            return temps;
        }
        return "";
    }

    private String tractarTemps(int temps) {
        String tempsActual = String.valueOf(temps);
        return tempsActual;
    }

    private void afegirMissatgeTextArea(String msg) {
        //System.out.println("msg: "+msg);
        String dataActual = getData();
        String horaActual = getTemps();
        String[] msgGood = msg.split("-/0/u/i/4/9<<z");
        System.out.println("msgGood: " + msgGood[0]);
        //msgArr.append(msg);
        String nomClient = this.getNomClient(msgGood);
        String msgClient = this.getMsgClient(msgGood);
        if (msgGood.length > 1) {
            //msgArr.append("[" + msgGood[1] + ": " + dataActual + " || " + horaActual + "]: " + msgGood[0] + "\n");
            //msgArr.append("[" + nomClient + ": " + dataActual + " || " + horaActual + "]: " + msgClient + "\n");
            this.msgArr.append(nomClient + ": [" + horaActual + "]: " + msgClient);
            this.saltLiniaTextArea(this.msgArr);
        } else {
            //this.msgArr.append("[" + dataActual + " || " + horaActual + "]: " + msgGood[0] + "\n");
            this.msgArr.append("Clients conectats: " + msgGood[0]);
        }
    }
    
    private void afegirClientsConnectats(String msg){
        String[] msgGood = msg.split("-/0/u/i/4/9<<z");
        System.out.println("msgGood: " + msgGood[0]);
        //msgArr.append(msg);
        String nomClient = this.getNomClient(msgGood);
        String msgClient = this.getMsgClient(msgGood);
        if (msgGood.length > 1) {
            //this.clientArr.append(nomClient);
            //this.saltLiniaTextArea(this.msgArr);
        } else {
            this.clientArr.append("\n"+msgGood[0]);
            this.saltLiniaTextArea(clientArr);
        }
    }

    private void saltLiniaTextArea(JTextArea jta) {
        String saltLinia = "\n";
        if (jta.isVisible()) {
            jta.append(saltLinia);
        }
    }

    private String getNomClient(String[] arrInfoClient) {
        if (arrInfoClient.length > 1) {
            String nomClient = arrInfoClient[1];
            System.out.println(nomClient);

            String nomCl = nomClient.substring(0, 1).toUpperCase();
            String finalClient = nomCl + nomClient.substring(1);
            if (!finalClient.isEmpty()) {
                return finalClient;
            }
        }
        return "";
    }

    private String getMsgClient(String[] arrInfoClient) {
        if (arrInfoClient.length > 1) {
            String msgClient = arrInfoClient[0];
            return msgClient;
        }
        return "";
    }

//    private void guardarNomClients(ArrayList<String> arrNom, String nomClient) {
//        if (!nomClient.isEmpty()) {
//            arrNom.add(nomClient);
//        }
//    }
}
