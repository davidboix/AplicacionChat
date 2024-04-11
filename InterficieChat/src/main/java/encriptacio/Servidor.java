package encriptacio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author David Boix Sanchez provisional : i Oleh Plechiy Tupis Andriyovech
 * @version 1.0
 */
public class Servidor {

    private String ipServidor;
    private int portServidor;

    /**
     *
     * @param ipServidor
     * @param portServidor
     */
    public Servidor(String ipServidor, int portServidor) {
        this.ipServidor = ipServidor;
        this.portServidor = portServidor;
    }

    /**
     *
     * @return
     */
    public String getIpServidor() {
        return ipServidor;
    }

    /**
     *
     * @param ipServidor
     */
    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    /**
     *
     * @return
     */
    public int getPortServidor() {
        return portServidor;
    }

    /**
     *
     * @param portServidor
     */
    public void setPortServidor(int portServidor) {
        this.portServidor = portServidor;
    }

    /**
     * TODO: Verificar les dades
     *
     * @param portServidor
     * @return
     * @throws IOException
     */
    public Socket iniciarServidor(int portServidor) throws IOException {
        ServerSocket server = new ServerSocket(portServidor);
        System.out.println("Acceptem connexions...");
        Socket socket = server.accept();
        return socket;
    }

    /**
     *
     * @return 
     * @throws NoSuchAlgorithmException
     */
    public SecretKey generarKeyAES() throws NoSuchAlgorithmException {
        System.out.println("Generem clau simetrica...");
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecretKey clau = keygen.generateKey();
        return clau;
    }

}
