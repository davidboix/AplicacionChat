package encriptacio;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bson.Document;

/**
 *
 * @author David Boix Sanchez provisional : i Oleh Plechiy Tupis Andriyovech
 * @version 1.0
 */
public class Servidor {

    private String ipServidor;
    private int portServidor;
    public Servidor () {
        
    }
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
     * @return @throws NoSuchAlgorithmException
     */
    public SecretKey generarKeyAES() throws NoSuchAlgorithmException {
        System.out.println("Generem clau simetrica...");
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecretKey clau = keygen.generateKey();
        return clau;
    }

    public void iniciServidor(String ip, int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor obert...");
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connectat...");

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream dip = new DataInputStream(socket.getInputStream());

                byte[] keyBytes = new byte[dip.readInt()];
                dip.readFully(keyBytes);

                SecretKey clau = new SecretKeySpec(keyBytes, "AES");

                int msgLength = dip.readInt();
                byte[] msgEncriptat = new byte[msgLength];
                dip.readFully(msgEncriptat);

                Cipher aesCipher = Cipher.getInstance("AES");
                aesCipher.init(Cipher.DECRYPT_MODE, clau);
                byte[] msgDesencriptat = aesCipher.doFinal(msgEncriptat);

                String missatge = new String(msgDesencriptat);
                System.out.println("Missatge desencriptat: " + missatge);

                socket.close();
                System.out.println("Servidor tornant a escoltar...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPassword(String password) {
        final String URLCONNEXIO = "mongodb://localhost:27017";

        MongoClientURI uri = new MongoClientURI(URLCONNEXIO);

        try ( MongoClient mongoClient = new MongoClient(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Cuentas");
            MongoCollection<Document> comptes = database.getCollection("comptes");

            //TODO: Haurem de verificar que poden haver mes de un usuari amb el meu mateix nom i per tant no farem el insert a la base de dades.
            Document nouUsuari = new Document("contrasenyaUsuari", password);

            comptes.insertOne(nouUsuari);
            System.out.println("Hem introduit un nou usuari...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final String IP = "localhost";
        final int PORT = 12345;
        Servidor servidor = new Servidor();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Servidor obert...");

            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connectat...");

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream dip = new DataInputStream(socket.getInputStream());

                byte[] keyBytes = new byte[dip.readInt()];
                dip.readFully(keyBytes);
                
                SecretKey clau = new SecretKeySpec(keyBytes, "AES");

                int msgLength = dip.readInt();
                byte[] msgEncriptat = new byte[msgLength];
                dip.readFully(msgEncriptat);
                
                Cipher aesCipher = Cipher.getInstance("AES");
                aesCipher.init(Cipher.DECRYPT_MODE, clau);
                
                byte[] msgDesencriptat = aesCipher.doFinal(msgEncriptat);
                String missatge = new String(msgDesencriptat);
                
                String base64String = Base64.getEncoder().encodeToString(msgEncriptat);
                servidor.setPassword(base64String);
                
                System.out.println("Missatge desencriptat: " + missatge);
                byte [] b1 = missatge.getBytes();
                
                md.update(b1);
                
                System.out.println();
                
                byte [] resum = md.digest();
                
                System.out.println("RESUMEN " + "SHA-256: " + new String(resum));
                
                socket.close();
                System.out.println("Servidor tornant a escoltar...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
