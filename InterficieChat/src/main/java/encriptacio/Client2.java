/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package encriptacio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author dam
 */
public class Client2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        final String IPSERVIDOR = "localhost";
        final int PORT = 12345;
        try {
            Socket cs = new Socket(IPSERVIDOR, PORT);

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecretKey clau = keyGen.generateKey();

            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            DataInputStream dip = new DataInputStream(cs.getInputStream());
            
            byte[] keyBytes = clau.getEncoded();
            out.writeInt(keyBytes.length);
            out.write(keyBytes);
            //System.out.print("Escriu la contrasenya: ");
            //String msg = lector.nextLine();
            String msg = "tu santissima madre";
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, clau);
            byte[] msgEncriptat = aesCipher.doFinal(msg.getBytes());
            
            out.writeInt(msgEncriptat.length);
            out.write(msgEncriptat);
        
            System.out.println("Missatge encriptat i clau enviats al servidor...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
