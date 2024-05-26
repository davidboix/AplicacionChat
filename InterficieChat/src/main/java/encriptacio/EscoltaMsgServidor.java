package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private static ArrayList<String> arrNoms = new ArrayList<>();
    public static int intClientsConectats;
    private ArrayList<String> arrNomClients = new ArrayList<>();

    /**
     * Definit constructor buit per poder inicialitzar objectes de tipus
     * EscoltaMsgServidor.
     */
    public EscoltaMsgServidor() {

    }

    /**
     * Getter per agafar el array dels noms del clients connectats.
     * @return Retorna el array dels clients connectats
     */
    public static ArrayList<String> getArrNoms() {
        return arrNoms;
    }

    /**
     * Setter per definir l'array del clients connectats
     * @param arrNoms Array que es difinira dels clients connectats
     */
    public static void setArrNoms(ArrayList<String> arrNoms) {
        EscoltaMsgServidor.arrNoms = arrNoms;
    }

    /**
     * Definit constructor per poder inicialitzar objectes de tipus
     * EscoltaMsgServidor. Aquest s'utilitza principalment per tractar els missatges que enviarà el client
     * al servidor
     * @param socket Objecte Socket per saber qui envia el missatge
     * @param inputStream InputStream del client per a que sigui possible enviar el missatge al servidor
     * @param msgArr Array del missatges per poder guardar els missatges del client
     * @param nomUsuari El nom del usuari que envia el missatge
     * @param clientArr Array de tots els clients connectats
     */
    public EscoltaMsgServidor(Socket socket, InputStream inputStream, JTextArea msgArr, String nomUsuari, JTextArea clientArr) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.msgArr = msgArr;
        this.nomUsuari = nomUsuari;
        this.clientArr = clientArr;
    }

    /**
     * Getter per agafar el numero de clients connectats
     * @return El numero de clients connectats
     */
    public static int getIntClientsConectats() {
        return intClientsConectats;
    }

    /**
     * Setter per definir el numero de clients connectats
     * @param intClientsConectats Numero de clients connectats que es definira
     */
    public static void setIntClientsConectats(int intClientsConectats) {
        EscoltaMsgServidor.intClientsConectats = intClientsConectats;
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

    /**
     * Getter que utilitzarem per poder agafar el Array dels missatges.
     *
     * @return Array dels missatges enviats.
     */
    public JTextArea getMsgArr() {
        return msgArr;
    }

    /**
     * Setter per poder definir l'array dels missatges enviats
     * @param msgArr Array dels missatges que es definiran
     */
    public void setMsgArr(JTextArea msgArr) {
        this.msgArr = msgArr;
    }

    /**
     * Getter que utilitzarem per poder agafar el nom del usuari.
     *
     * @return El nom de l'usuari.
     */
    public String getNomUsuari() {
        return nomUsuari;
    }

    /**
     * Setter per poder definir el nom de l'usuari
     * @param nomUsuari El nom de l'usuari que es definira
     */
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
            byte[] buffer = new byte[1024];
            while (!this.socket.isClosed()) {
                int intBytes = this.inputStream.read(buffer);
                if (intBytes != -1) {
                    String msg = new String(buffer, 0, intBytes);
                    this.afegirMissatgeTextArea(msg);
                    this.afegirClientsConnectats(msg);
                }
            }
        } catch (IOException ioe) {
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

    /**
     * Convertirà el temps del LocaTime a String
     * @param temps El temps del LocalTemps
     * @return El temps convertit a String
     */
    private String tractarTemps(int temps) {
        String tempsActual = String.valueOf(temps);
        return tempsActual;
    }

    /**
     * Funcio per afegir els missatges del servidor al textArea per a poder-los visualitzar
     * @param msg El missatge del servidor
     */
    private void afegirMissatgeTextArea(String msg) {
        String dataActual = getData();
        String horaActual = getTemps();
        String[] msgGood = msg.split("-/0/u/i/4/9<<z");
        System.out.println("msgGood: " + msgGood[0]);
        String nomClient = this.getNomClient(msgGood);
        String msgClient = this.getMsgClient(msgGood);
        if (msgGood.length > 1) {
            this.msgArr.append(nomClient + ": [" + horaActual + "]: " + msgClient);
            this.saltLiniaTextArea(this.msgArr);
        } else {
            
        }
    }

    /**
     * Funcio que afegirà els clients que es vagin connectant al servidor i els mostrara al TextArea de la esquerra
     * @param msg El missatge del servidor el qual tractarem per agafar només el nom
     */
    public void afegirClientsConnectats(String msg) {
        System.out.println("\nPassem per aqui...\n");
        Servidor servidor = new Servidor();
        servidor.augmentarClientsConnectats();
        
        for (String row : this.arrNomClients) {
            if (!row.equalsIgnoreCase(this.nomUsuari)) {
                this.arrNomClients.add(this.nomUsuari);        
            }
            System.out.println("EscoltaMsgServidor: " + row);
        }
        

        for (String row : this.arrNomClients) {
            System.out.println("EscoltaMsgServidor: " + row);
        }

        String[] msgGood = msg.split("-/0/u/i/4/9<<z");
        if (msgGood.length > 1) {
            
        } else {
            System.out.println("Aquestos son els clients conectats: " + msgGood[0]);
            this.clientArr.append(msgGood[0]);
        }
    }
    
    /**
     * Funcio que fa un salt de linia en el TextArea
     * @param jta El TextArea on es farà el salt de linia
     */
    private void saltLiniaTextArea(JTextArea jta) {
        String saltLinia = "\n";
        if (jta.isVisible()) {
            jta.append(saltLinia);
        }
    }

    /**
     * Agafe el nom del client del missatge enviat pel servidor
     * @param arrInfoClient el missatge del servidor
     * @return El nom del client que ha enviat el missatge
     */
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

    /**
     * Agafe el missatge del client del missatge del servidor
     * @param arrInfoClient Missatge del servidor
     * @return El missatge final que ha enviat el client.
     */
    private String getMsgClient(String[] arrInfoClient) {
        if (arrInfoClient.length > 1) {
            String msgClient = arrInfoClient[0];
            return msgClient;
        }
        return "";
    }
}