package encriptacio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class ProvisionalPersona implements Runnable {

    private Socket socket;
    private byte i;

    public ProvisionalPersona(Socket socket, byte i) {
        this.socket = socket;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            byte[] msg = new byte[50];
            is.read(msg);

            String miss = "Connexio numero -> " + i + "\n" + new String(msg);

            System.out.println(miss);

            String qtClients = "Ets la connexio numero: " + i;
            os.write(qtClients.getBytes());
            //socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
