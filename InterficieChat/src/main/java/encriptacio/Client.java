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

    public Client() {

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) throws IOException {
        this.os = os;
    }

    public static String getMissatgeClient() {
        return missatgeClient;
    }

    public static void setMissatgeClient(String missatgeClient) {
        Client.missatgeClient = missatgeClient;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

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
     * TODO: Revisar logica de la funcio ja i separar-ho per capes per no
     * tenir-ho tot en una classe.
     *
     * Funcio creada per poder llegirs els diferents missatges que ens envien
     * desde el servidor.
     */
    public static void llegirMissatgeServidor(InputStream is, JTextArea msgArr) throws IOException {
        byte[] buffer = new byte[500];
        int intBuffer = is.read(buffer);
        String msgRebut = new String(buffer, 0, intBuffer);
        System.out.println("Missatge rebut del servidor: " + msgRebut);
    }

    /**
     * Funcio creada provisionalment per poder rebre tot el ArrayList que conte
     * tots els missatges enviats per un client en questio.
     *
     * @param socket
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
     * TODO: Afegir una descripcio mes objectiva, es a dir, mes extensa.
     *
     * Funcio creada per poder fer la connexio en el servidor utilitzant el
     * metode en questio.
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
            //new EscoltaMsgServidor(socket, is).start();
            new EscoltaMsgServidor(socket, is, cl).start();

            //boolean semafor = enviarMissatgeServidor(os, socket);
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

    public void crearConnexio(JTextArea textAreaMissatge, String nom, JTextArea clientsConnectats) {

        try {
            Socket socket = new Socket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            socket.connect(addr);
            this.setNomUsuari(nom);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            System.out.println("Client: Clients conectats....");
            for (String row: Servidor.arrNoms) {
                System.out.println("Clients: " + row);
            }
            
            this.setSocket(socket);
            this.setOs(os);
            os.write(nom.getBytes());
            System.out.println("Ens conectem...");
            is = socket.getInputStream();
            this.setIs(is);

            new EscoltaMsgServidor(socket, is, textAreaMissatge, this.getNomUsuari(), clientsConnectats).start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut connectar al servidor");
        }
    }

    /**
     * Funcio diferent parametrizada.
     *
     * @param textAreaMissatge
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

            new EscoltaMsgServidor(socket, is, textAreaMissatge).start();
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
            os.write("oleh".getBytes());
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

    public void mostrarClientsConnectats() {
        System.out.println("\nAquestos son els clients conectats...");
        for (String nomClients : Servidor.arrNoms) {
            System.out.println(nomClients);
        }
    }

    public void setNomClients(String nomUsuari) {
        if (!nomUsuari.isEmpty()) {
            Servidor.arrNoms.add(nomUsuari);
        }
    }
}
