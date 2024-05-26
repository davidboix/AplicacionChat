package encriptacio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;

public class Client {

    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private String nomUsuari;
    public static String missatgeClient;

    /**
     * Definit constructor buit per poder inicialitzar objectes de tipus Client.
     */
    public Client() {

    }

    /**
     * Getter per a agafar el socket del client
     *
     * @return Retorna el socket del client
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Setter per definir el socket del client
     *
     * @param socket Socket que es definirà a l'objecte
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Getter per a agafar el inputStream del client
     *
     * @return Retorna el inputStream del client
     */
    public InputStream getIs() {
        return is;
    }

    /**
     * Setter per definir el socket del client
     *
     * @param is InputStream que es definirà a l'objecte
     */
    public void setIs(InputStream is) {
        this.is = is;
    }

    /**
     * Getter per a agafar el outputStream del client
     *
     * @return Retorna el outputStream del client
     */
    public OutputStream getOs() {
        return os;
    }

    /**
     * Setter per definir el socket del client
     *
     * @param os OutputStream que es definirà a l'objecte
     */
    public void setOs(OutputStream os) throws IOException {
        this.os = os;
    }

    /**
     * Getter per a agafar el missatge del client
     *
     * @return Retorna el missatge del client
     */
    public static String getMissatgeClient() {
        return missatgeClient;
    }

    /**
     * Setter per definir el socket del client
     *
     * @param missatgeClient Missatge del client que es definirà a l'objecte
     */
    public static void setMissatgeClient(String missatgeClient) {
        Client.missatgeClient = missatgeClient;
    }

    /**
     * Getter per a agafar el nom del client
     *
     * @return Retorna el nom del client
     */
    public String getNomUsuari() {
        return nomUsuari;
    }

    /**
     * Setter per definir el socket del client
     *
     * @param nomUsuari Nom del client que es definirà a l'objecte
     */
    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    /**
     * Main que farem servir per poder fer les proves entre client i servidor
     * sense utilitzar la interficie grafica.
     *
     * TODO: Haurem de comentar aquesta funcio per a que NO deixi de funcionar
     * en la interficie grafica.
     *
     * @param args
     */
    public static void main(String[] args) {
        setConnexio();
    }

    /**
     * Main el qual s'utilitza per fer les proves de la classe per la consola,
     * no s'utilitza en la interficie
     *
     * @param args
     */
    public static void main2(String[] args) {
        Scanner lector = new Scanner(System.in);
        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            socket.connect(addr);
            System.out.println("Ens conectem...");
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            boolean semafor = false;

            while (!semafor) {
                System.out.print("Escriu un missatge que vulguis al servidor: ");
                String msg = lector.nextLine();
                os.write(msg.getBytes());

                System.out.println("S'ha enviat el missatge correctament!...");

                if (msg.equalsIgnoreCase("exit")) {
                    os.write(msg.getBytes());
                    System.out.println("Ens hem desconnectat del servidor...");
                    semafor = true;
                }

                //llegirArrayList(socket);
            }
            socket.close();
        } catch (SocketException se) {
            se.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un error en la connexio del client cap al servidor.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un error i per tant no s'ha executat correctament el client!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un problema general en el client");
        }
    }

    /**
     * Funcio creada per poder llegirs els diferents missatges que ens envien
     * desde el servidor.
     *
     * @param is
     * @param msgArr
     */
    public static void llegirMissatgeServidor(InputStream is, JTextArea msgArr) throws IOException {
        byte[] buffer = new byte[500];
        int intBuffer = is.read(buffer);
        String msgRebut = new String(buffer, 0, intBuffer);
        System.out.println("Missatge rebut del servidor: " + msgRebut);
    }

    /**
     * Funcio creada per poder rebre tot el ArrayList que conte tots els
     * missatges enviats per un client en questio.
     *
     * @param socket Objecte socket per a poder saber de qui son els missatges
     * @param msgArr Array en concret la qual llegirem
     */
    public static void llegirArrayList(Socket socket, JTextArea msgArr) {
        //private static void llegirArrayList(ArrayList<Socket> arrSocket) {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ArrayList<String> msgClients = (ArrayList<String>) ois.readObject();
            int j = 0;
            System.out.println("Entrem");
            for (String row : msgClients) {
                System.out.println(row);
                msgArr.append(j + ": " + msgClients.get(j) + "/n");
                System.out.println("Entrem2");
            }
        } catch (EOFException eof) {
            System.err.println("\nCLIENT: No hi han mes missatges a mostrar...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nCLIENT:Hi ha hagut un error general...");
        }
    }

    /**
     * Funcio per poder fer la connexio en el servidor utilitzant el metode en
     * questio.
     */
    public static void setConnexio() {
        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            socket.connect(addr);

            Scanner lector = new Scanner(System.in);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            Client cl = new Client();

            boolean semafor = enviarMissatgeServidor(lector, os, socket);

            if (!semafor) {
                socket.close();
            }

        } catch (SocketException se) {
            se.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un error en la connexio del client cap al servidor.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un error i per tant no s'ha executat correctament el client!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nERROR!\nHi ha hagut un problema general en el client");
        }
    }

    /**
     * Funcio que s'utilitza quan es connecta un client, enviar el nom del
     * client al servidor
     *
     * @param textAreaMissatge textArea que senviara al fil EscoltaMsgServidor
     * per moder ficar els missatges enviats i que es reben
     * @param nom El nom del client connectat
     * @param clientsConnectats textArea el qual s'enviara al fil
     * EscoltaMsgServidor per a poder tractar el nom del client i informar quan
     * es connecti o desconnecti
     */
    public void crearConnexio(JTextArea textAreaMissatge, String nom, JTextArea clientsConnectats) {

        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            socket.connect(addr);
            System.out.println("Ens conectem...");
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            this.setSocket(socket);
            this.setOs(os);
            this.setNomUsuari(nom);
            this.setIs(is);
            if (!this.getNomUsuari().equalsIgnoreCase("exit")) {
                os.write(this.getNomUsuari().getBytes());
            }
            new EscoltaMsgServidor(socket, is, textAreaMissatge, this.getNomUsuari(), clientsConnectats).start();
            
            /**
             * TODO:Posarem un altre fil per poder fer que el client estigui
             * escoltant tot el rato nous clients que es conecten al nostre
             * sistema.
             */
        } catch (IOException e) {
            System.out.println("No s'ha pogut connectar al servidor");
        }
    }

    /**
     * Funcio la qual s'utilitzar per rebre els missatges i enviar-los al
     * servidor.
     *
     * @param textAreaMissatge textArea per a poder posar els missatges enviats
     * a la interficie
     */
    public void crearConnexio(JTextArea textAreaMissatge) {

        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            socket.connect(addr);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            this.setSocket(socket);
            this.setOs(os);

            System.out.println("Ens conectem...");
            is = socket.getInputStream();
            this.setIs(is);
            enviarMissatgeServidor(this.getOs(), this.getSocket(), this.getNomUsuari());

            //new EscoltaMsgServidor(socket, is, textAreaMissatge).start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut connectar al servidor");
        }
    }

    /**
     * Funcio creada per poder enviar missatges al servidor i a mes a mes, es
     * tractara la paraula clau definida per poder desconectar-se del servidor
     * correctament.
     *
     * @param lector Objecte de tipus Scanner que ens servira per poder escriure
     * missatges per consola i enviar-los.
     * @param os Objecte de tipus OutputStream que ens servira per poder enviar
     * els missatges escrits per consola.
     * @param socket Objecte de tipus Socket que ens servira per identificar
     * quin socket hem de tancar.
     * @return Retornara cert si el usuari no ha enviat la paraula clau
     * anomenada 'exit' i per tant podra seguir enviant missatges i el cas
     * contrari, si el usuari envia el missatge 'exit' retornara fals i per
     * consequencia, es realitzara la desconexio.
     */
    public static boolean enviarMissatgeServidor(Scanner lector, OutputStream os, Socket socket) {
        boolean sortir = false;
        try {
            while (!sortir) {
                String msg = lector.nextLine();
                if (msg.equalsIgnoreCase("exit")) {
                    os.write(msg.getBytes());
                    System.out.println("Ens hem desconnectat del servidor correctament...");
                    sortir = true;
                    socket.close();
                    return sortir;
                }
                os.write(msg.getBytes());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortir;
    }

    /**
     * Funcio la qual enviarà el missatge rebut del textField al servidor
     *
     * @param os OutputStream per a poder escriure el missatge al servidor
     * @param socket Objecte socket per a saber de que es el missatge enviat
     * @param missatge El missatge en concret que s'enviara
     * @return Es retornara un true si es detecta que el missatge enviat es un
     * exit, el qual donara la confirmacio de que el client vol desconnectar-se
     * i tancara el socket
     */
    public static boolean enviarMissatgeServidor(OutputStream os, Socket socket, String missatge) {
        System.out.println("\n\nAquest es el client: " + socket);
        boolean sortir = false;
        try {
            if (missatge.equalsIgnoreCase("exit")) {
                os.write(missatge.getBytes());
                System.out.println("Ens hem desconnectat del servidor correctament...");
                sortir = true;
                socket.close();
                return sortir;
            }

            os.write(missatge.getBytes());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortir;
    }

    /**
     * Funcio la qual mostrarà els clients connectats per consola
     */
    public void mostrarClientsConnectats() {
        System.out.println("\nAquestos son els clients conectats...");
        for (String nomClients : Servidor.arrNoms) {
            System.out.println(nomClients);
        }
    }

    /**
     * Funcio per posar el nom del client a la variable global
     *
     * @param nomUsuari El qual s'utilitzarà per posar el nom del client
     */
    public void setNomClients(String nomUsuari) {
        if (!nomUsuari.isEmpty()) {
            Servidor.arrNoms.add(nomUsuari);
        }
    }
}
