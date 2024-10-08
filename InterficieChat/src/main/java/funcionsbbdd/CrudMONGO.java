package funcionsbbdd;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import javax.swing.JTextArea;
import org.bson.Document;

/**
 *
 * @author David Boix Sanchez i Oleh Plechiy Tupis Andriyovech
 * @version 1.0
 */
public class CrudMONGO {

    private String ipServidor;
    private String nomColeccio;
    private String nomDocument;
    private String usuariServidor;
    private String passwordServidor;
    private int portServidor;
    private String urlConnexio;

    public CrudMONGO() {

    }

    public CrudMONGO(String ipServidor, String nomColeccio, String nomDocument, String usuariServidor, String passwordServidor, int portServidor) {
        this.ipServidor = ipServidor;
        this.nomColeccio = nomColeccio;
        this.nomDocument = nomDocument;
        this.usuariServidor = usuariServidor;
        this.passwordServidor = passwordServidor;
        this.portServidor = portServidor;
    }

    public CrudMONGO(String ipServidor, String nomColeccio, String nomDocument, String usuariServidor, String passwordServidor, int portServidor, String urlConnexio) {
        this.ipServidor = ipServidor;
        this.nomColeccio = nomColeccio;
        this.nomDocument = nomDocument;
        this.usuariServidor = usuariServidor;
        this.passwordServidor = passwordServidor;
        this.portServidor = portServidor;
        this.urlConnexio = urlConnexio;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public String getNomColeccio() {
        return nomColeccio;
    }

    public void setNomColeccio(String nomColeccio) {
        this.nomColeccio = nomColeccio;
    }

    public String getNomDocument() {
        return nomDocument;
    }

    public void setNomDocument(String nomDocument) {
        this.nomDocument = nomDocument;
    }

    public String getUsuariServidor() {
        return usuariServidor;
    }

    public void setUsuariServidor(String usuariServidor) {
        this.usuariServidor = usuariServidor;
    }

    public String getPasswordServidor() {
        return passwordServidor;
    }

    public void setPasswordServidor(String passwordServidor) {
        this.passwordServidor = passwordServidor;
    }

    public int getPortServidor() {
        return portServidor;
    }

    public void setPortServidor(int portServidor) {
        this.portServidor = portServidor;
    }

    public String getUrlConnexio() {
        return urlConnexio;
    }

    public void setUrlConnexio(String urlConnexio) {
        this.urlConnexio = urlConnexio;
    }

    public CrudMONGO inicialitzarMongo() {
        final String DB_SRV_USR = "grup1";
        final String DB_SRV_PWD = "gat123";
        final String DB_URL = "57.129.5.24";
        final int DB_PORT = 27017;
        final String nomColeccio = "comptes";
        CrudMONGO cm = new CrudMONGO();

        cm.setUsuariServidor(DB_SRV_USR);
        cm.setPasswordServidor(DB_SRV_PWD);
        cm.setIpServidor(DB_URL);
        cm.setPortServidor(DB_PORT);
        cm.setNomColeccio(nomColeccio);
        return cm;
    }

    public CrudMONGO(String ipServidor, int portServidor, String usuariServidor, String passwordServidor, String nomColeccio) {
        this.ipServidor = ipServidor;
        this.nomColeccio = nomColeccio;
        this.usuariServidor = usuariServidor;
        this.passwordServidor = passwordServidor;
        this.portServidor = portServidor;
    }

    public CrudMONGO(String ipServidor, int portServidor, String usuariServidor, String passwordServidor) {
        this.ipServidor = ipServidor;
        this.portServidor = portServidor;
        this.usuariServidor = usuariServidor;
        this.passwordServidor = passwordServidor;
    }

    @Override
    public String toString() {
        return "CrudMONGO{" + "ipServidor=" + ipServidor + ", nomColeccio=" + nomColeccio + ", nomDocument=" + nomDocument + ", usuariServidor=" + usuariServidor + ", passwordServidor=" + passwordServidor + ", portServidor=" + portServidor + ", urlConnexio=" + urlConnexio + '}';
    }

    private String inicialitzarServidor() {
        final String URLCONNEXIO = "mongodb://" + this.getUsuariServidor() + ":" + this.getPasswordServidor() + "@" + this.getIpServidor() + ":" + this.getPortServidor();
        return URLCONNEXIO;
    }

    public MongoDatabase realitzarConnexio() {
        this.setUrlConnexio(this.inicialitzarServidor());
        MongoClientURI uri = new MongoClientURI(this.getUrlConnexio());
        MongoDatabase md = null;
        try ( MongoClient mc = new MongoClient(uri)) {
            md = mc.getDatabase(this.getUsuariServidor());
            if (md != null) {
                return md;
            }
            return md;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md;
    }

    private MongoCollection<Document> getColeccio() {
        MongoCollection<Document> comptes = null;

        try {
            MongoDatabase md = this.realitzarConnexio();
            if (md != null) {
                comptes = md.getCollection(this.getNomColeccio());
                if (comptes != null) {
                    return comptes;
                }
                return comptes;
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comptes;
    }

    private void recorrerUsers(MongoCollection<Document> mc, String nomUsuari) {
        try {
            FindIterable<Document> resUsuaris = mc.find(Filters.eq("nomUser", nomUsuari));
            for (Document row : resUsuaris) {
                System.out.println("Nom usuari: " + row.getString("nomUser"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUsers(String nomUsuari) {
        this.setUrlConnexio(this.inicialitzarServidor());
        MongoClientURI mcu = new MongoClientURI(this.getUrlConnexio());

        try ( MongoClient mc = new MongoClient(mcu)) {
            MongoDatabase db = mc.getDatabase(this.getUsuariServidor());
            MongoCollection<Document> mongoC = db.getCollection(this.getNomColeccio());
            long numDocuments = mongoC.countDocuments(Filters.eq("nomUser", nomUsuari));
            if (numDocuments > 0) {
                this.recorrerUsers(mongoC, nomUsuari);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MongoCollection<Document> accedirColeccions(MongoClient mc, String nomColeccio) {
        MongoDatabase db = mc.getDatabase(this.getUsuariServidor());
        MongoCollection<Document> mongoC = db.getCollection(nomColeccio);
        return mongoC;
    }

    public boolean setDadesMsg(String nomUser, String msg, String data) {
        
        this.setUrlConnexio(this.inicialitzarServidor());
        
        MongoClientURI mcu = new MongoClientURI(this.getUrlConnexio());

        try ( MongoClient mc = new MongoClient(mcu)) {
            MongoDatabase database = mc.getDatabase(this.getUsuariServidor());
            MongoCollection<Document> mongoC = database.getCollection(this.getNomColeccio());

            try {
                System.out.println(mongoC.countDocuments());;
            } catch (Exception e) {

            }

            Document missatgeNou = new Document("nomUsuari", nomUser)
                    .append("missatgeUsuari", msg)
                    .append("dataMissatge", data);
            mongoC.insertOne(missatgeNou);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String tractarData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String data = formatter.format(date);
        String[] arrData = data.split(" ");
        String dataFormat = arrData[0];
        return data;
    }

    public void getMsgData(String data, JTextArea jta) {
        this.setUrlConnexio(this.inicialitzarServidor());
        MongoClientURI mcu = new MongoClientURI(this.getUrlConnexio());
        try ( MongoClient mc = new MongoClient(mcu)) {
            MongoDatabase database = mc.getDatabase(this.getUsuariServidor());
            MongoCollection<Document> mongoC = database.getCollection(this.getNomColeccio());

            long numDocuments = mongoC.countDocuments(Filters.eq("dataMissatge", data));

            if (numDocuments > 0) {
                FindIterable<Document> resUsuaris = mongoC.find(Filters.eq("dataMissatge", data));
                for (Document row : resUsuaris) {
                    String nomUsuari = row.getString("nomUsuari");
                    String msg = row.getString("missatgeUsuari");
                    String dataMsg = row.getString("dataMissatge");
                    jta.append(nomUsuari + ": [" + dataMsg + "] " + msg);
                    jta.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
