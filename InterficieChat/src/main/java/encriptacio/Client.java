package encriptacio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import javax.crypto.Cipher;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class Client {

    public static void main(String[] args) {

        final String IPSERVIDOR = "localhost";
        final int PORT = 12345;
        try {
            Socket cs = new Socket(IPSERVIDOR, PORT);
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();
            
            KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
            KeyPair keypair = keygen.generateKeyPair();
            
            Cipher rsaCipher = Cipher.getInstance("RSA");
            String msg = "Prova de misatge";
            rsaCipher.init(Cipher.ENCRYPT_MODE,keypair.getPublic());
            byte [] msgXifrat = rsaCipher.doFinal(msg.getBytes());
            
            
            String msxXfr = new String(msgXifrat);
            os.write(msxXfr.getBytes());
            
            //String msgClient = IPSERVIDOR + "!" + PORT + "!" + msgXifrat;
            //os.write(msgClient.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

