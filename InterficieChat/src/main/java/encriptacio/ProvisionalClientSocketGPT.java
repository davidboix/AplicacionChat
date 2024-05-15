package encriptacio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ProvisionalClientSocketGPT {
    public static void main(String[] args) {
        try {
            System.out.println("Creant socket client...");

            Socket cs = new Socket("localhost", 4949);

            System.out.println("Establint connexio...");

            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            String msg = "Hola Servidor!";
            
            os.write(msg.getBytes());

            // Leer la respuesta del servidor continuamente
            byte[] buffer = new byte[50];
            while (true) {
                int bytesRead = is.read(buffer);
                if (bytesRead == -1) {
                    // El servidor cerró la conexión
                    System.out.println("El servidor ha cerrado la conexión.");
                    break;
                }
                
                String respuesta = new String(buffer, 0, bytesRead).trim();
                System.out.println("Resposta del servidor: " + respuesta);

                // Aquí puedes decidir si quieres seguir leyendo o cerrar la conexión
                // Por ejemplo, si el servidor envía "FIN" como señal de cierre:
                if ("FIN".equals(respuesta)) {
                    break;
                }
            }

            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
