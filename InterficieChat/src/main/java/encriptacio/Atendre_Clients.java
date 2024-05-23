package encriptacio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Atendre_Clients extends Thread {

    private Socket socket;
    static ArrayList<Socket> arrSocket;
    private OutputStream os;
    /**
     * TODO: Revisar alternativa al us de aquest arraylist en especial.
     */
    private static CopyOnWriteArrayList<OutputStream> arrClients = new CopyOnWriteArrayList<>();
    private ArrayList<String> arrNomClients = new ArrayList<>();

    public Atendre_Clients(Socket socket) {
        this.socket = socket;
    }

    public Atendre_Clients(Socket socket, ArrayList<Socket> arrSocket) {
        this.socket = socket;
        this.arrSocket = arrSocket;
    }

    public Atendre_Clients(Socket socket, OutputStream os) {
        this.socket = socket;
        this.os = os;
    }
    public Atendre_Clients(Socket socket, ArrayList<Socket> arrSocket, ArrayList<String> arrNomClients){
        this.socket = socket;
        this.arrSocket = arrSocket;
        this.arrNomClients = arrNomClients;
    }

    public void run() {
        try {
            Servidor servidor = new Servidor();
            InputStream is = socket.getInputStream();
            os = socket.getOutputStream();
            arrClients.add(os);
            
            //String nomClient = servidor.setNomClients(servidor.arrNoms, is);
            String nomClient = Servidor.setNomClients(Servidor.arrNoms, is);
            
            //EscoltaMsgServidor.rebreArray(Servidor.arrNoms);
            EscoltaMsgServidor.setArrNoms(Servidor.arrNoms);
            
            //servidor.getNomArrClients(servidor.arrNoms);
            Servidor.getNomArrClients(Servidor.arrNoms);

            //this.enviarClientsConectats(servidor.arrSocket);
            this.enviarNomsClientsConectats(servidor.arrNoms);
            //this.enviarNomsClientsConectats(Servidor.arrNoms);

            byte[] buffer = new byte[1024];
            int intBuffer;
            
            boolean semafor = false;
            while (!semafor) {
                intBuffer = is.read(buffer);

                String msg = new String(buffer, 0, intBuffer);

                if (msg.equalsIgnoreCase("exit")) {
                    boolean isTrobat = this.isSocketTrobat(servidor.arrSocket, this.socket);
                    if (isTrobat) {
                        System.out.println("Passem per el exit...");
                        this.eliminarClientArray(this.arrClients, this.os);
                        this.enviarMissatgeDesconexio(servidor.arrSocket, this.socket);
                        this.eliminarSocketArray(Servidor.arrSocket, this.socket);
                        
                        //this.deleteNomClient(servidor.arrNoms, nomClient);
                        //this.enviarClientsMissatgeDesconexio(Servidor.arrNoms, msg);
                        //Servidor.deleteNomClient(Servidor.arrNoms, nomClient);                      
                        
                        this.deleteNomClient(Servidor.arrNoms, nomClient);
                                                
                        semafor = true;
                        break;
                    }
                }

                this.guardarMissatgesArrayList(servidor.arrMsg2, msg);
                this.mostrarMissatgesArrayList(servidor.arrMsg2);
                this.mostrarMissatgeUsuari(socket, msg);
                this.enviarMissatge(msg);

            }
            //arrClients.remove(os);
            //socket.close();

            servidor.decrementarClientsConnectats(socket);

//            if (desconnectatListener != null) {
//                desconnectatListener.onClientDisconnect(socket);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarClientArray(CopyOnWriteArrayList<OutputStream> arrClients, OutputStream os) {
        if (!arrClients.isEmpty()) {
            arrClients.remove(os);
        } else {
            System.out.println("El Array no te cap client per poder borrar.");
        }

    }

    private ArrayList guardarMissatgesArrayList(ArrayList<String> arrMsg, String msg) {
        if (!msg.isEmpty()) {
            arrMsg.add(msg);
        }
        return arrMsg;
    }

    private void mostrarMissatgesArrayList(ArrayList<String> arrMsg) {
        try {
            System.out.println("Aquestos son els missatges guardats en el ArrayList: ");
            for (String row : arrMsg) {
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarMissatgeUsuari(Socket socket, String msg) {
        if (!msg.isEmpty()) {
            System.out.println("\nMissatge enviat per el usuari amb el port del socket: " + socket.getPort() + " que hem rebut en el servidor: " + msg);
        }
    }

    private void enviarMissatge(String msg) {
        for (OutputStream clients : arrClients) {
            try {
                clients.write(msg.getBytes());
                clients.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void enviarMissatgeDesconexio(ArrayList<Socket> arrClients, Socket socket) {
        Socket socketClient = null;
        for (Socket row : arrClients) {
            if (row.equals(socket)) {
                socketClient = row;
            }
        }

        String msgDesconexio = "\nEl client " + socketClient + " s'ha desconectat satisfactoriament";
        this.enviarMissatge(msgDesconexio);
    }
    
    private void enviarClientsMissatgeDesconexio(ArrayList<String> arrClients, String nom) {
        String nomClient = null;
        for (String row: arrClients) {
            if (row.equalsIgnoreCase(nom)) {
                nomClient = nom;
            }
        }
        if (nomClient != null) {
            String msgDesconexio = "\nEl client " + nomClient + " s'ha desconectat satisfactoriament";
            this.enviarMissatge(msgDesconexio);
        }
        
    }

    private boolean eliminarSocketArray(ArrayList<Socket> arrSockets, Socket socket) {
        boolean isTrobat = false;
        Socket socketClient = null;
        for (Socket row : arrSockets) {
            if (row.equals(socket)) {
                socketClient = row;
            }
        }

        boolean isBorrat = this.isArrayListSocketExistent(arrSockets, socketClient);

        if (isBorrat) {
            return isTrobat = true;
        }

        System.out.println("\nEl client NO s'ha borrat del array correctament");
        return isTrobat;

    }

    private boolean isArrayListSocketExistent(ArrayList<Socket> arrSockets, Socket socket) {
        boolean isTrobat = false;
        try {
            if (!socket.equals(null)) {
                arrSockets.remove(socket);
                isTrobat = true;
            }
        } catch (NullPointerException npe) {
            System.out.println("El objecte es null");
        } catch (Exception e) {
            System.out.println("Error general");
        }
        return isTrobat;
    }

    private boolean isSocketTrobat(ArrayList<Socket> arrSockets, Socket socket) {
        boolean isTrobat = false;
        for (Socket row : arrSockets) {
            if (row.equals(socket)) {
                isTrobat = true;
            }
        }
        return isTrobat;
    }

    private void enviarNomsClientsConectats(ArrayList<String> arrClients) {

        if (arrClients.size() > 0) {
            for (String row : arrClients) {
                this.enviarMissatge(row);

            }
        }
    }

    public void deleteNomClient(ArrayList<String> arrNomsClients, String nom) {
        if (arrNomsClients.size() > 0) {
            for (String row : arrNomsClients) {
                if (row.equalsIgnoreCase(nom)) {
                    arrNomsClients.remove(nom);
                }
            }
        }
        String msgDesconexio = "El client amb nom: " + nom + " s'ha desconectat satisfactoriament";       
        this.enviarMissatge(msgDesconexio);
    }
}
