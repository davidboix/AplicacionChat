package encriptacio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorExmple {

    public static void main(String[] args) {
        try {
            final String DEMANAR_CONNEXIO = "DESCONNEXIO";
            System.out.println("Creem el socket servidor");
            ServerSocket serverSocket = new ServerSocket();

            InetSocketAddress addr = new InetSocketAddress("localhost", 5556);

            System.out.println("Fem el bind. Ja acceptem connexions...");
            serverSocket.bind(addr);

            int qtClients = 0;

            while (true) {
                qtClients++;
                System.out.println("Acceptant connexions");
                Socket newSocket = serverSocket.accept();
                System.out.println("Conexión rebuda: " + qtClients);
                InputStream is = newSocket.getInputStream();
                OutputStream os = newSocket.getOutputStream();
                /**
                 * TODO: Desde el servidor, hem de rebre el missatge de
                 * desconnexio que ens envia el client per poder desconnectar
                 */
                Atendre_Clients atendreClientsqtClients = new Atendre_Clients(newSocket);
                atendreClientsqtClients.start();
                boolean desconnectar = llegirDesconnexio(newSocket, is, os, DEMANAR_CONNEXIO);
                if (desconnectar) {
                    System.out.println("Rebem missatge del cient");
                    atendreClientsqtClients.setMsgClient(DEMANAR_CONNEXIO);
                    if (atendreClientsqtClients.getMsgClient().equalsIgnoreCase(DEMANAR_CONNEXIO)) {
                        atendreClientsqtClients.tancarConnexio(newSocket, DEMANAR_CONNEXIO);
                        System.out.println("Tanquem connexio");
                        qtClients--;
                    }
                    System.out.println("No ha tancat connexio");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean llegirDesconnexio(Socket socket, InputStream is, OutputStream os, String msgDesconnexio) throws Exception {
        try {
            byte[] msg = new byte[500];
            is.read(msg);
            String desconnexio = new String(msg);
            System.out.println(desconnexio);
            if (desconnexio.equalsIgnoreCase(desconnexio)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("ERROR!\n El metode valoraNom ha petat :( ");
        }
        return false;
    }
}
