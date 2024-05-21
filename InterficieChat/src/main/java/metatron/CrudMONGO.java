package metatron;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.Date;
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
                System.out.println("Hem fet connexio...");
                return md;
            }
            System.out.println("NO hem fet connexio...");
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
                System.out.println("No estem dins del MongoDB.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comptes;
    }

    public void getDades(String nomUsuari) {

        try {
            MongoCollection mc = this.getColeccio();
            long numDocuments = mc.countDocuments(Filters.eq("nomUser", nomUsuari));
            if (numDocuments > 0) {
                System.out.println("existeixen documents...");
            }
        } catch (IllegalStateException ise) {
            System.out.println("Hi ha hagut un problema ");
            //ise.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setDadesMsg(String nomUser, String msg, String data) {

        this.setUrlConnexio(this.inicialitzarServidor());
        MongoClientURI mcu = new MongoClientURI(this.getUrlConnexio());
        try ( MongoClient mc = new MongoClient(mcu)) {
            MongoCollection<Document> mongoC = this.accedirColeccions(mc, this.getNomColeccio());
            
            Document missatgeNou = new Document("nomUsuari", "david")
                    .append("missatgeUsuari", msg)
                    .append("dataMissatge", data);
            mongoC.insertOne(missatgeNou);
            System.out.println("S'ha introduit un nou missatge...");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
