package encriptacio;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author David Boix Sanchez
 * @version 1.0
 * 
 */
public class Atendre_Clients extends Thread {

    private Socket newSocket;
    private String msgClient;
    
    public Atendre_Clients (Socket cs) {
        newSocket = cs;
    }
    public Atendre_Clients(Socket cs, String msgClient) {
        newSocket = cs;
        this.msgClient = msgClient;
    }

    public void setMsgClient(String msgClient) {
        this.msgClient = msgClient;
    }

    public String getMsgClient() {
        return this.msgClient;
    }

    public void run() {
        try {
            /**
             * TODO: El servidor llegira un missatge de desconnexio per part del
             * client que sera DESCONNEXIO quan el client premi al boto de
             * desconnexio de la interficie
             *
             */
            boolean semafor = false;
            final String MISSATGE_DESCONNEXIO = getMsgClient();
            while (!semafor) {
                Thread.sleep(60000);
                
                String msgDesconnexio = demanarDesconnexio(MISSATGE_DESCONNEXIO);
                if (msgDesconnexio.equalsIgnoreCase(MISSATGE_DESCONNEXIO)) {
                    //Arriba el missatge de desconnexio i retornar true
                    System.out.println("\nArribem dins del condicional de Atendre_Client");
                    semafor = tancarConnexio(newSocket, MISSATGE_DESCONNEXIO);
                    System.out.println("\nTanquem nou socket...");
                }
                System.out.println("\nSeguim amb la connexio...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR!\n Hem entrat en un error...");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("ERROR!\nHi ha hagut un error de interrupcio");
        } catch (Exception e) {
            System.err.println("ERROR!\nHi ha un metode que no ha funcionat correctament");
        }
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
    public boolean tancarConnexio(Socket socket, String desconnexio) throws Exception {
        try {
            final String DESCONNEXIO = "DESCONNEXIO";
            if (desconnexio.equalsIgnoreCase(DESCONNEXIO)) {
                Thread.sleep(10000);
                socket.close();
                System.out.println("S'ha desconectat el usuari correctament");
                return true;
            }
            System.out.println("No s'ha realitzat correctament");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String demanarDesconnexio(String missatgeClientDesconnexio) {
        return "DESCONNEXIO";
    }
}
