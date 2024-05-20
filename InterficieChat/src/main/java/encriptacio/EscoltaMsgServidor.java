package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Calendar;
import javax.swing.JTextArea;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class EscoltaMsgServidor extends Thread {

    private JTextArea msgArr;
    private Socket socket;
    private InputStream inputStream;

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

    public EscoltaMsgServidor(JTextArea msgArr) {
        this.msgArr = msgArr;
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

    /**
     * Funcio sobreescrita la qual ens aporta la classe Thread per poder
     * inicialitzar fils correctament definits i que realitzaran la funcio de
     * rebre missatges per part del Servidor.
     */
//    @Override
    public void run2() {
        try {
            System.out.print("Escriu el missatge que vulguis al servidor: ");
            byte[] buffer = new byte[1024];
            while (!this.socket.isClosed()) {
                int intBytes = this.inputStream.read(buffer);
                if (intBytes != -1) {
                    String msg = new String(buffer, 0, intBytes);
                    System.out.println("\nMissatge del servidor: " + msg);
                    System.out.print("Escriu el missatge que vulguis al servidor: ");
                }
            }
        } catch (IOException ioe) {
            //ioe.printStackTrace();
            System.out.println("El socket s'ha tancat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.print("Escriu el missatge que vulguis al servidor: ");
            byte[] buffer = new byte[1024];
            while (!this.socket.isClosed()) {
                int intBytes = this.inputStream.read(buffer);
                if (intBytes != -1) {
                    String msg = new String(buffer, 0, intBytes);
                    System.out.println("\nMissatge del servidor: " + msg);
                    System.out.print("Escriu el missatge que vulguis al servidor: ");
                    this.afegirMissatgeTextArea(msg);
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
        String dataActual = getData();
        String horaActual = getTemps();
        msgArr.append("[" + dataActual + " || " + horaActual + "]: " + msg + "\n");
    }
}
