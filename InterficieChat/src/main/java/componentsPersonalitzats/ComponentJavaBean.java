package componentsPersonalitzats;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.bson.Document;

/**
 *
 * @author David Boix Sanchez i Oleh Plechiy Tupis Andriyovech
 * @version 1.0
 */
public class ComponentJavaBean extends JPanel {

    private String nom;

    public ComponentJavaBean() {
        this.setLayout(new BorderLayout());
        JPanel panelHeader = this.personalitzacioPanel(new GridBagLayout());

        JPanel panelEsquerra = this.personalitzacioPanel(new BorderLayout());
        JPanel panelCentralEsquerra = this.personalitzacioPanel(new GridBagLayout());

        JPanel panelMain = this.personalitzacioPanel(new GridBagLayout());

        JPanel panelDreta = this.personalitzacioPanel(new BorderLayout());
        JPanel panelFooter = this.personalitzacioPanel(new GridBagLayout());

        JLabel titol = this.personalitzacioTitol();
        JTextField text = this.personalitzacioJTextField("Escriu aqui el nom de la coleccio: ");
        JButton boto = this.personalitzacioBoto();

        JComboBox<String> comboBox = this.personalitzacioComboBox();

        this.setVisible(true);
        this.setSize(400, 300);

        //Posicio nort
        panelHeader.add(titol);
        this.add(panelHeader, BorderLayout.NORTH);

        //Posicio centre
        panelMain.add(text);
        this.add(panelMain, BorderLayout.CENTER);

        //Posicio esquerra
        panelCentralEsquerra.add(comboBox);
        panelEsquerra.add(panelCentralEsquerra, BorderLayout.CENTER);
        this.add(panelEsquerra, BorderLayout.WEST);

        //Posicio dreta
        this.add(panelDreta, BorderLayout.EAST);

        //Posicio footer
        panelFooter.add(boto);
        this.add(panelFooter, BorderLayout.SOUTH);

        this.funcionalitatBoto(boto, text, comboBox);
        this.realitzarOperacions(comboBox);
    }

    public ComponentJavaBean(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private JPanel personalitzacioPanel(LayoutManager lm) {
        JPanel jp = new JPanel();
        jp.setLayout(lm);
        return jp;
    }

    private JLabel personalitzacioTitol() {
        JLabel jl = new JLabel();
        jl.setBackground(Color.GREEN);
        jl.setForeground(Color.BLACK);
        jl.setText("Java Bean");
        jl.setFont(new Font("Serif", Font.BOLD, 20));
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        return jl;
    }

    private JTextField personalitzacioJTextField(String placeHolder) {
        JTextField jtf = new JTextField();
        jtf.setSize(80, 40);
        jtf.setPreferredSize(new Dimension(200, 30));
        jtf.setFont(new Font("Serif", Font.BOLD, 12));
        jtf.setForeground(Color.GRAY);
        jtf.setText(placeHolder);
        this.controlPlaceHolder(jtf, placeHolder);
        return jtf;
    }

    private JButton personalitzacioBoto() {
        JButton jb = new JButton();
        jb.setForeground(Color.GREEN);
        jb.setFont(new Font("Serif", Font.PLAIN, 12));
        jb.setPreferredSize(new Dimension(180, 30));
        jb.setText("Realitzar operacio");
        return jb;
    }

    private JComboBox<String> personalitzacioComboBox() {
        JComboBox<String> jcb = new JComboBox<>();
        jcb.setFont(new Font("Serif", Font.PLAIN, 12));
        jcb.addItem("Crear");
        jcb.addItem("Consultar");
        //jcb.addItem("Actualitzar");
        jcb.addItem("Eliminar");
        return jcb;
    }

    private void controlJTextField(JTextField jtf, JComboBox jcb) {

        String text = jtf.getText();
        String operacio = String.valueOf(jcb.getSelectedItem());

        if (text.isEmpty() || text.equalsIgnoreCase("Escriu aqui el nom de la coleccio: ")) {
            System.out.println("No fem res...");
            return;
        }

        System.out.println("Aquest es el nom de la coleccio: " + text);
        if (operacio.equalsIgnoreCase("Crear")) {
            this.crearColeccio(text);
            System.out.println("Anem a crear una nova coleccio...");
            return;
        }
        
        System.out.println("Anem a borrar una coleccio...");
        this.eliminarColeccio(text);

    }

    private void funcionalitatBoto(JButton jb, JTextField jtf, JComboBox jcb) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlJTextField(jtf, jcb);
            }
        });
    }

    private void realitzarOperacions(JComboBox jcb) {
        jcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcioSeleccionada = String.valueOf(jcb.getSelectedItem());
                System.out.println(opcioSeleccionada);
            }
        });
    }

    private void controlPlaceHolder(JTextField jtf, String placeHolder) {
        jtf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String text = jtf.getText();
                if (text.equals(placeHolder)) {
                    jtf.setText("");
                    jtf.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = jtf.getText();
                if (text.isEmpty()) {
                    jtf.setText(placeHolder);
                    jtf.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void crearColeccio(String nomColeccio) {
        try {
//            final String DB_SRV_USR = "grup1";
//            final String DB_SRV_PWD = "gat123";
//            final String DB_URL = "57.129.5.24";
//            final String DB_PORT = "27017";
//            String URLCONNEXIO = "mongodb://" + DB_SRV_USR + ":" + DB_SRV_PWD + "@" + DB_URL + ":" + DB_PORT;

            final String URLCONNEXIO = "mongodb://localhost:27017";

            MongoClientURI uri = new MongoClientURI(URLCONNEXIO);

            try ( MongoClient mongoClient = new MongoClient(uri)) {
                //MongoDatabase database = mongoClient.getDatabase(DB_SRV_USR);
                MongoDatabase database = mongoClient.getDatabase("proves");
                MongoCollection<Document> comptes = database.getCollection(nomColeccio);

                System.out.println("Hem creat una nova coleccio amb el nom de: " + nomColeccio);

            } catch (Exception e) {

            }

        } catch (Exception e) {

        }
    }

    private void eliminarColeccio(String nomColeccio) {
        try {
//            final String DB_SRV_USR = "grup1";
//            final String DB_SRV_PWD = "gat123";
//            final String DB_URL = "57.129.5.24";
//            final String DB_PORT = "27017";
//            String URLCONNEXIO = "mongodb://" + DB_SRV_USR + ":" + DB_SRV_PWD + "@" + DB_URL + ":" + DB_PORT;

            final String URLCONNEXIO = "mongodb://localhost:27017";

            MongoClientURI uri = new MongoClientURI(URLCONNEXIO);

            try ( MongoClient mongoClient = new MongoClient(uri)) {
                //MongoDatabase database = mongoClient.getDatabase(DB_SRV_USR);
                MongoDatabase database = mongoClient.getDatabase("proves");
                MongoCollection<Document> comptes = database.getCollection(nomColeccio);

                comptes.drop();
                System.out.println("Hem creat una nova coleccio!!");

            } catch (Exception e) {

            }

        } catch (Exception e) {

        }
    }

}
