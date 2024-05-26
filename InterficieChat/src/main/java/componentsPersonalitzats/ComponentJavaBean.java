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
    private String usuari;
    private String contrasenya;
    private String ip;

//    public ComponentJavaBean() {
//        this.setLayout(new BorderLayout());
//        JPanel panelHeader = this.personalitzacioPanel(new GridBagLayout());
//
//        JPanel panelEsquerra = this.personalitzacioPanel(new BorderLayout());
//        JPanel panelCentralEsquerra = this.personalitzacioPanel(new GridBagLayout());
//
//        JPanel panelMain = this.personalitzacioPanel(new GridBagLayout());
//        
//        GridBagConstraints c = new GridBagConstraints();
//        
//        JPanel panelDreta = this.personalitzacioPanel(new BorderLayout());
//        JPanel panelFooter = this.personalitzacioPanel(new GridBagLayout());
//
//        JLabel titol = this.personalitzacioTitol();
//        JTextField text = this.personalitzacioJTextField("Escriu aqui el nom de la coleccio: ");
//        JButton boto = this.personalitzacioBoto();
//
//        JComboBox<String> comboBox = this.personalitzacioComboBox();
//        
//        JLabel ipLabel = this.personalitzacioTitol();
//        JTextField textIp = this.personalitzacioJTextField("Escriu aqui la ip del servidor ");
//        
//        JLabel contraLabel = this.personalitzacioTitol();
//        JTextField textContra = this.personalitzacioJTextField("Escriu aqui la contasenya del servidor ");
//        
//        JLabel usuariLabel = this.personalitzacioTitol();
//        JTextField textUsuari = this.personalitzacioJTextField("Escriu aqui l'usuari del servidor ");
//
//        this.setVisible(true);
//        this.setSize(400, 300);
//
//        //Posicio nort
//        panelHeader.add(titol);
//        this.add(panelHeader, BorderLayout.NORTH);
//
//        //Posicio centre
//        c.gridx = 20;
//        c.gridy = 0;
//        panelMain.add(text);
//        panelMain.add(textIp, c);
//        this.add(panelMain, BorderLayout.CENTER);
//
//        //Posicio esquerra
//        panelCentralEsquerra.add(comboBox);
//        panelEsquerra.add(panelCentralEsquerra, BorderLayout.CENTER);
//        this.add(panelEsquerra, BorderLayout.WEST);
//
//        //Posicio dreta
//        this.add(panelDreta, BorderLayout.EAST);
//
//        //Posicio footer
//        panelFooter.add(boto);
//        this.add(panelFooter, BorderLayout.SOUTH);
//
//        this.funcionalitatBoto(boto, text, comboBox);
//        this.realitzarOperacions(comboBox);
//    }
    
    /**
     * Classe main del component JavaBean
     */
    public ComponentJavaBean() {
        this.setLayout(new BorderLayout());

        JPanel panelHeader = this.personalitzacioPanel(new GridBagLayout());
        JPanel panelEsquerra = this.personalitzacioPanel(new BorderLayout());
        JPanel panelCentralEsquerra = this.personalitzacioPanel(new GridBagLayout());
        JPanel panelMain = this.personalitzacioPanel(new GridBagLayout());
        JPanel panelDreta = this.personalitzacioPanel(new BorderLayout());
        JPanel panelFooter = this.personalitzacioPanel(new GridBagLayout());

        JLabel titol = this.personalitzacioTitol();
        JTextField text = this.personalitzacioJTextField("Escriu aqui el nom de la coleccio");
        JButton boto = this.personalitzacioBoto();
        JComboBox<String> comboBox = this.personalitzacioComboBox();

        JTextField textIp = this.personalitzacioJTextField("Escriu aqui la ip del servidor");
        JTextField textContra = this.personalitzacioJTextField("Escriu aqui la contasenya del servidor");
        JTextField textUsuari = this.personalitzacioJTextField("Escriu aqui l'usuari del servidor");
        JTextField textPort = this.personalitzacioJTextField("Escriu aqui el port del servidor");
        
        this.setVisible(true);
        this.setSize(400, 300);

        panelHeader.add(titol);
        this.add(panelHeader, BorderLayout.NORTH);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        panelMain.add(textUsuari, c);
        panelMain.add(textIp, c);
        panelMain.add(textContra, c);
        panelMain.add(textPort, c);
        panelMain.add(text, c);

        this.add(panelMain, BorderLayout.CENTER);

        panelCentralEsquerra.add(comboBox);
        panelEsquerra.add(panelCentralEsquerra, BorderLayout.CENTER);
        this.add(panelEsquerra, BorderLayout.WEST);

        this.add(panelDreta, BorderLayout.EAST);
        panelFooter.add(boto);
        this.add(panelFooter, BorderLayout.SOUTH);

        this.funcionalitatBoto(boto, text, textUsuari, textIp, textContra, textPort, comboBox);
        this.realitzarOperacions(comboBox);
    }

    /**
     * Constructor per a inicialitzar objectes del javaBean
     * @param nom Nom del client
     */
    public ComponentJavaBean(String nom) {
        this.nom = nom;
    }

    /**
     *  Getter per a agafar el nom del client
     * @return El nom del client
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter per a inicialitzar el nom del client
     * @param nom Nom del client a inicialitzar
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Funcio per a personalitzar el Jpanel
     * @param lm LayoutManager per a personalitzar el layout del jPanel
     * @return El JPanel
     */
    private JPanel personalitzacioPanel(LayoutManager lm) {
        JPanel jp = new JPanel();
        jp.setLayout(lm);
        return jp;
    }

    /**
     * Personalitza el titol del JavaBean
     * @return El titol personalitzat
     */
    private JLabel personalitzacioTitol() {
        JLabel jl = new JLabel();
        jl.setBackground(Color.GREEN);
        jl.setForeground(Color.BLACK);
        jl.setText("Java Bean");
        jl.setFont(new Font("Serif", Font.BOLD, 20));
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        return jl;
    }

    
    /**
     * Personalitza el textfield del JavaBean
     * @param placeHolder El placeholder que volem posar al textField
     * @return El textfield personalitzat
     */
    private JTextField personalitzacioJTextField(String placeHolder) {
        JTextField jtf = new JTextField();
        jtf.setSize(80, 40);
        jtf.setPreferredSize(new Dimension(220, 30));
        jtf.setFont(new Font("Serif", Font.BOLD, 12));
        jtf.setForeground(Color.GRAY);
        jtf.setText(placeHolder);
        this.controlPlaceHolder(jtf, placeHolder);
        return jtf;
    }

    /**
     * Personalitza el boto de javaBean
     * @return El boto personalitzat
     */
    private JButton personalitzacioBoto() {
        JButton jb = new JButton();
        jb.setForeground(Color.GREEN);
        jb.setFont(new Font("Serif", Font.PLAIN, 12));
        jb.setPreferredSize(new Dimension(180, 30));
        jb.setText("Realitzar operacio");
        return jb;
    }

    /**
     * Personalitza el comboBox del JavaBean
     * @return El comboBox personalitzat
     */
    private JComboBox<String> personalitzacioComboBox() {
        JComboBox<String> jcb = new JComboBox<>();
        jcb.setFont(new Font("Serif", Font.PLAIN, 12));
        jcb.addItem("Crear");
        jcb.addItem("Consultar");
        //jcb.addItem("Actualitzar");
        jcb.addItem("Eliminar");
        return jcb;
    }

    /**
     * Funcio la qual agafara el que s'escriu al textfield i creara una colecio amb el text o borrara una existent
     * @param jtf Contingut del textfield
     * @param usuari Usuari del missatge del mongoDB
     * @param ip Ip del servidor
     * @param contra Contrasenya del servidor
     * @param port Port del servidor
     * @param jcb Combo box del JavaBean
     */
    private void controlJTextField(JTextField jtf, JTextField usuari, JTextField ip, JTextField contra, JTextField port, JComboBox jcb) {

        String text = jtf.getText();
        String usuariTxt = usuari.getText();
        String ipTxt = ip.getText();
        String contraTxt = contra.getText();
        String portTxt = port.getText();
        String operacio = String.valueOf(jcb.getSelectedItem());

        if (text.isEmpty() || text.equalsIgnoreCase("Escriu aqui el nom de la coleccio: ")) {
            System.out.println("No fem res...");
            return;
        }

        System.out.println("Aquest es el nom de la coleccio: " + text);
        if (operacio.equalsIgnoreCase("Crear")) {
            this.crearColeccio(text, usuariTxt, ipTxt, contraTxt, portTxt);
            System.out.println("Anem a crear una nova coleccio...");
            return;
        }
        
        System.out.println("Anem a borrar una coleccio...");
        this.eliminarColeccio(text, usuariTxt, ipTxt, contraTxt, portTxt);

    }

    /**
     * Funcio que ens direccionara a una altra funcio per fer la funcio del boto
     * @param jb El boto que fa la funcio
     * @param jtf Contingut del textfield
     * @param usuari Usuari del missatge del mongoDB
     * @param ip Ip del servidor
     * @param contra Contrasenya del servidor
     * @param port ort del servidor
     * @param jcb ombo box del JavaBean
     */
    private void funcionalitatBoto(JButton jb, JTextField jtf, JTextField usuari,JTextField ip,JTextField contra,JTextField port, JComboBox jcb) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlJTextField(jtf, usuari, ip, contra, port, jcb);
            }
        });
    }

    /**
     * Funcio per aa realitzar les operacions
     * @param jcb ComboBox del JavaBean
     */
    private void realitzarOperacions(JComboBox jcb) {
        jcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcioSeleccionada = String.valueOf(jcb.getSelectedItem());
                System.out.println(opcioSeleccionada);
            }
        });
    }

    /**
     * Funcio la qual fara apareixer i desapareixer el placeholder quan l'usuari cliqui al TextField
     * @param jtf El textfield on anira el placeholder
     * @param placeHolder El contingut del placeholder
     */
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

    /**
     * Funcio la qual està encargada de crear la coleccio a la base de dades
     * @param nomColeccio El nom de la nova coleccio
     * @param usuari L'usuari de la base de dades
     * @param ip La Ip de la base de dades
     * @param contra La contrasenya de la base de dades
     * @param port El port de la base de dades
     */
    private void crearColeccio(String nomColeccio, String usuari, String ip, String contra, String port) {
        try {
            final String DB_SRV_USR = usuari;
            final String DB_SRV_PWD = contra;
            final String DB_URL = ip;
            final String DB_PORT = port;
            final String URLCONNEXIO = "mongodb://" + DB_SRV_USR + ":" + DB_SRV_PWD + "@" + DB_URL + ":" + DB_PORT;

            //final String URLCONNEXIO = "mongodb://localhost:27017";

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

    /**
     * Funcio la qual està encargada de eliminar la coleccio a la base de dades
     * @param nomColeccio El nom de la nova coleccio
     * @param usuari L'usuari de la base de dades
     * @param ip La Ip de la base de dades
     * @param contra La contrasenya de la base de dades
     * @param port El port de la base de dades
     */
    private void eliminarColeccio(String nomColeccio, String usuari, String ip, String contra, String port) {
        try {
            final String DB_SRV_USR = usuari;
            final String DB_SRV_PWD = contra;
            final String DB_URL = ip;
            final String DB_PORT = port;
            String URLCONNEXIO = "mongodb://" + DB_SRV_USR + ":" + DB_SRV_PWD + "@" + DB_URL + ":" + DB_PORT;

            //final String URLCONNEXIO = "mongodb://localhost:27017";

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
