package encriptacio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author David Boix Sanchez i Oleh Plechiy Tupis Andriyovech
 * @version 1.0
 */
public class FilsEnviarInfoClients2 extends Thread {

    private String msg;
    private Socket socket;
    private ArrayList<String> arrMsg = new ArrayList<>();
    private ArrayList<Socket> arrSocket = new ArrayList<>();

    public FilsEnviarInfoClients2() {

    }
   

    public FilsEnviarInfoClients2(ArrayList<String> arrMsg, ArrayList<Socket> arrSocket) {
        this.arrMsg = arrMsg;
        this.arrSocket = arrSocket;
    }

    public FilsEnviarInfoClients2(Socket socket, ArrayList<String> arrMsg, ArrayList<Socket> arrSocket) {
        this.socket = socket;
        this.arrMsg = arrMsg;
        this.arrSocket = arrSocket;
    }

    public FilsEnviarInfoClients2(Socket socket, String msg, ArrayList<String> arrMsg, ArrayList<Socket> arrSocket) {
        this.socket = socket;
        this.msg = msg;
        this.arrMsg = arrMsg;
        this.arrSocket = arrSocket;
    }

    /**
     * TODO: Augmentar les linies de la documentacio de la funcio.
     *
     * Funcio creada per poder guardar els missatges que envien els diferents
     * clients al servidor
     *
     * @param msg Missatge enviat per el client.
     * @return Retornem un ArrayList per poder realitzar certes operacions amb
     * ell.
     */
    private ArrayList guardarMissatges(ArrayList<String> arrMsg, String msg) {
        if (!msg.isEmpty()) {
            arrMsg.add(msg);
        }
        return arrMsg;
    }

    /**
     * Funcio la qual llegira els missatges que envii el client i els retornara
     */
    public void run() {
        try {
            ServidorProves servidor = new ServidorProves();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            

//          DataInputStream dis = new DataInputStream(is);
//          DataOutputStream dos = new DataOutputStream(os);
            //TODO: aqui es connectara al client al qual li tornara els missatges
            //InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
            //socket.connect(addr);
            /**
             * TODO:
             *
             * Lllegira el missatge i el guardara en una variable
             *
             * byte[] buffer = new byte[500];
             *
             * int intBuffer = is.read(buffer);
             *
             * String msg = new String(buffer, 0, intBuffer);
             */
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            //printarMsgClients();
//            System.out.println("\nAquest es el missatge del client: " + this.msg);
//            os.write(msg.getBytes());
//            os.flush();
            out.writeObject(this.arrMsg);
            out.flush();
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
    
    private void printarMsgClients () {
        System.out.println("Aqui printem les dades del servidor.");
        for (String missatge : this.arrMsg) {
            System.out.println(missatge);
        }
    }

}
//package encriptacio;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.net.SocketException;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class FilsEnviarInfoClients extends Thread {
//
//    private String msg;
//    private CopyOnWriteArrayList<Socket> arrSocket;
//
//    public FilsEnviarInfoClients(String msg, CopyOnWriteArrayList<Socket> arrSocket) {
//        this.msg = msg;
//        this.arrSocket = arrSocket;
//    }
//
//    @Override
//    public void run() {
//        for (Socket clientSocket : arrSocket) {
//            try {
//                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
//                dos.writeUTF(msg);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
