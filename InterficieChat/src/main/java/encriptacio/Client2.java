package encriptacio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class Client2 {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        final String IPSERVIDOR = "localhost";
        final int PORT = 12345;
        try ( Socket cs = new Socket(IPSERVIDOR, PORT);
                DataOutputStream out = new DataOutputStream(cs.getOutputStream());
                DataInputStream dip = new DataInputStream(cs.getInputStream());) {

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecretKey clau = keyGen.generateKey();
            
            byte[] keyBytes = clau.getEncoded();
            out.writeInt(keyBytes.length);
            out.write(keyBytes);

            //System.out.print("Escriu la contrasenya: ");
            //String msg = lector.nextLine();
            String msg = "Hola, el meu nom es Oleh";
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, clau);
            byte[] msgEncriptat = aesCipher.doFinal(msg.getBytes());
            
            out.writeInt(msgEncriptat.length);
            out.write(msgEncriptat);

            System.out.println("Missatge encriptat i clau enviats al servidor...");

            byte[] keyBytesServ = new byte[dip.readInt()];
            dip.readFully(keyBytesServ);

            SecretKey clauServidor = new SecretKeySpec(keyBytesServ, "AES");
            //SecretKey clauServidor = new SecretKeySpec(dip.readNBytes(keyBytes.length), "AES");

            byte[] msgEncriptats = new byte[dip.readInt()];
            dip.readFully(msgEncriptats);
                        
            Cipher aesCipher2 = Cipher.getInstance("AES");
            aesCipher2.init(Cipher.DECRYPT_MODE, clau);

            byte[] msgDesencriptat = aesCipher2.doFinal(msgEncriptats);
            String missatge = new String(msgDesencriptat);
//            String base64String = Base64.getEncoder().encodeToString(msgDesencriptat);
            System.out.println("Quantitat clients connectats: " + missatge);
            Thread.sleep(5000);
            dip.close();
            out.close();
            cs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
