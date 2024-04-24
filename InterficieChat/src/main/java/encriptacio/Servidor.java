package encriptacio;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bson.Document;

/**
 *
 * @author David Boix Sanchez provisional i Oleh Plechiy Tupis Andriyovech
 * @version 1.0
 */
public class Servidor {

    private String ipServidor;
    private int portServidor;

    public Servidor() {

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

                //socket.close();
                System.out.println("Servidor tornant a escoltar...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPassword(String nomUsuari, String password) {
        final String URLCONNEXIO = "mongodb://localhost:27017";

        MongoClientURI uri = new MongoClientURI(URLCONNEXIO);

        try ( MongoClient mongoClient = new MongoClient(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Cuentas");
            MongoCollection<Document> comptes = database.getCollection("comptes");

            //TODO: Haurem de verificar que poden haver mes de un usuari amb el meu mateix nom i per tant no farem el insert a la base de dades.
            Document nouUsuari = new Document("nomUsuari", nomUsuari).append("contrasenyaUsuari", password);

            comptes.insertOne(nouUsuari);
            System.out.println("Hem introduit un nou usuari...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void amagarInfoWarnings() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);
        mongoLogger.setUseParentHandlers(false);
    }

    public String getPassword(String nomUsuari) {
        String password = "";
        final String URLCONNEXIO = "mongodb://localhost:27017";
        MongoClientURI uri = new MongoClientURI(URLCONNEXIO);
        try ( MongoClient mongoClient = new MongoClient(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Cuentas");
            MongoCollection<Document> comptes = database.getCollection("comptes");

            long numUsuari = comptes.countDocuments(Filters.eq("nomUsuari", nomUsuari));
            if (numUsuari > 0) {
                FindIterable<Document> resultatUsuaris = comptes.find(Filters.eq("nomUsuari", nomUsuari));
                for (Document row : resultatUsuaris) {
                    password = row.getString("contrasenyaUsuari");
                }
                if (!password.isEmpty()) {
                    System.out.println("Passem per aki");
                    return password;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public void informarQtClients(SecretKey clau, Cipher aesCipher, DataOutputStream out, String contadorClients) {
        try {
            byte[] keyByte = clau.getEncoded();
            out.writeInt(keyByte.length);
            out.write(keyByte);
            aesCipher.init(Cipher.ENCRYPT_MODE, clau);
            byte[] msgAEncriptar = aesCipher.doFinal(contadorClients.getBytes());
            out.writeInt(msgAEncriptar.length);
            out.write(msgAEncriptar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarMsgClient(String missatge, SecretKey clau, byte[] msgEncriptat, Cipher aesCipher, DataOutputStream out) {
        try {
            byte[] keyByte = clau.getEncoded();
            out.writeInt(keyByte.length);
            out.write(keyByte);
            aesCipher.init(Cipher.ENCRYPT_MODE, clau);
            byte[] msgAEncriptar = aesCipher.doFinal(missatge.getBytes());
            out.writeInt(msgAEncriptar.length);
            out.write(msgAEncriptar);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void encriptarPassword(String missatge, Socket socket, Servidor servidor, SecretKey clau, byte[] msgEncriptat, Cipher aesCipher, DataOutputStream out) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b1 = missatge.getBytes();
            md.update(b1);
            byte[] resum = md.digest();
            String contrasenyaHash = Base64.getEncoder().encodeToString(resum);
            /**
             * Funcio que cridem per a que el servidor envii al client la
             * contrasenya encriptada
             */
            servidor.enviarMsgClient(contrasenyaHash, clau, msgEncriptat, aesCipher, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final String IP = "localhost";
        final int PORT = 12345;
        int qtClients = 0;

        Servidor servidor = new Servidor();
        servidor.amagarInfoWarnings();

        try ( ServerSocket server = new ServerSocket(PORT);) {
            System.out.println("Servidor obert...");
            while (true) {
                Socket socket = server.accept();
                qtClients++;
                System.out.println("Clients connectats: " + qtClients);

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream dip = new DataInputStream(socket.getInputStream());

                byte[] keyBytes = new byte[dip.readInt()];
                dip.readFully(keyBytes);
                
                SecretKey clau = new SecretKeySpec(keyBytes, "AES");

                byte[] msgEncriptat = new byte[dip.readInt()];
                dip.readFully(msgEncriptat);

                Cipher aesCipher = Cipher.getInstance("AES");
                aesCipher.init(Cipher.DECRYPT_MODE, clau);
                
                byte[] msgDesencriptat = aesCipher.doFinal(msgEncriptat);
                String missatge = new String(msgDesencriptat);
                System.out.println("Missatge desencriptat: " + missatge);
                
                /**
                 * Funcio que cridem per encriptar la contrasenya amb hash un
                 * cop hem desencriptat la contrasenya que el client ens envia
                 */
                
                servidor.encriptarPassword(missatge, socket, servidor, clau, msgEncriptat, aesCipher, out);
                
                System.out.println("\nServidor tornant a escoltar...");
                /*Comentat per fer proves
                /**
                 * Codi de exemple per comparar 2 contrasenyes utilitzant hash
                 */
                /*
                byte[] b1 = missatge.getBytes();
                md.update(b1);
                byte[] resum = md.digest();
                String base64Stringss = Base64.getEncoder().encodeToString(resum);
                servidor.setPassword("boix",base64Stringss);
                String password = servidor.getPassword("boix");
                byte[] b2 = password.getBytes();
                md.update(b2);
                byte[] resum2 = md.digest();
                if (Arrays.equals(resum, resum2)) {
                    System.out.println("Les contrasenyes son iguasl");
                } else {
                    System.out.println("Diferents!");
                }
                System.out.println("RESUMEN SHA-256: " + new String(resum));
                System.out.println("RESUMEN2 SHA-256: " + new String(resum2));
                 */

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
