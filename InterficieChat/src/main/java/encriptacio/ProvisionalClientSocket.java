package encriptacio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class ProvisionalClientSocket {

    public static void main(String[] args) {
        try {
            System.out.println("Creant socket client...");

            Socket cs = new Socket("localhost", 4949);

            System.out.println("Establint connexio...");

            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            String msg = "Soc en David!";

            os.write(msg.getBytes());
            
            byte[] resposta = new byte[50];
            is.read(resposta);

            String respuesta = new String(resposta);
            System.out.println(respuesta);
            //cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
