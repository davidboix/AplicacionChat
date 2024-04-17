package exercicis.interficiechat;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import encriptacio.Servidor;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.bson.Document;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class Registre extends javax.swing.JFrame {

    /**
     * Creates new form Registre
     */
    public Registre() {
        initComponents();
        inicialitzarTextInputs();
        afegirIcono();
        //inicialitzarServidor();
    }
// /u0000

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaGeneral = new javax.swing.JPanel();
        footerVista = new javax.swing.JPanel();
        botoAltaUsuari = new javax.swing.JButton();
        headerVista = new javax.swing.JPanel();
        titolRegistre = new javax.swing.JLabel();
        mainVista = new javax.swing.JPanel();
        etiquetaNom = new javax.swing.JLabel();
        etiquetaCognom = new javax.swing.JLabel();
        etiquetaEdat = new javax.swing.JLabel();
        etiquetaCorreu = new javax.swing.JLabel();
        etiquetaUsuari = new javax.swing.JLabel();
        etiquetaPassword = new javax.swing.JLabel();
        inputNom = new componentsPersonalitzats.JTextFieldPersonalitzat();
        inputCognom = new componentsPersonalitzats.JTextFieldPersonalitzat();
        inputEdat = new componentsPersonalitzats.JTextFieldPersonalitzat();
        inputCorreu = new componentsPersonalitzats.JTextFieldPersonalitzat();
        inputUsuari = new componentsPersonalitzats.JTextFieldPersonalitzat();
        inputPassword = new componentsPersonalitzats.JPasswordPlaceholder();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registre");

        vistaGeneral.setLayout(new java.awt.BorderLayout());

        footerVista.setBackground(new java.awt.Color(203, 219, 242));

        botoAltaUsuari.setBackground(new java.awt.Color(125, 165, 221));
        botoAltaUsuari.setText("Donat d'Alta");
        botoAltaUsuari.setToolTipText("Donar-te de alta en el nostre chat");
        botoAltaUsuari.setPreferredSize(new java.awt.Dimension(180, 26));
        botoAltaUsuari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoAltaUsuariActionPerformed(evt);
            }
        });
        footerVista.add(botoAltaUsuari);

        vistaGeneral.add(footerVista, java.awt.BorderLayout.PAGE_END);

        headerVista.setBackground(new java.awt.Color(203, 219, 242));

        titolRegistre.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        titolRegistre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titolRegistre.setText("Registre");
        titolRegistre.setToolTipText("Titol del registre en el nostre chat");
        headerVista.add(titolRegistre);

        vistaGeneral.add(headerVista, java.awt.BorderLayout.PAGE_START);

        mainVista.setBackground(new java.awt.Color(203, 219, 242));
        mainVista.setLayout(null);

        etiquetaNom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaNom.setText("Nom");
        mainVista.add(etiquetaNom);
        etiquetaNom.setBounds(260, 20, 30, 20);

        etiquetaCognom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaCognom.setText("Cognoms");
        mainVista.add(etiquetaCognom);
        etiquetaCognom.setBounds(260, 80, 59, 20);

        etiquetaEdat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaEdat.setText("Edat");
        mainVista.add(etiquetaEdat);
        etiquetaEdat.setBounds(260, 140, 27, 20);

        etiquetaCorreu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaCorreu.setText("Correu");
        mainVista.add(etiquetaCorreu);
        etiquetaCorreu.setBounds(260, 200, 42, 20);

        etiquetaUsuari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaUsuari.setText("Usuari");
        mainVista.add(etiquetaUsuari);
        etiquetaUsuari.setBounds(260, 260, 39, 20);

        etiquetaPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaPassword.setText("Contrasenya");
        mainVista.add(etiquetaPassword);
        etiquetaPassword.setBounds(260, 320, 77, 20);
        mainVista.add(inputNom);
        inputNom.setBounds(260, 50, 150, 23);
        mainVista.add(inputCognom);
        inputCognom.setBounds(260, 110, 150, 23);
        mainVista.add(inputEdat);
        inputEdat.setBounds(260, 170, 150, 23);
        mainVista.add(inputCorreu);
        inputCorreu.setBounds(260, 230, 150, 23);
        mainVista.add(inputUsuari);
        inputUsuari.setBounds(260, 290, 150, 23);
        mainVista.add(inputPassword);
        inputPassword.setBounds(260, 350, 150, 22);

        vistaGeneral.add(mainVista, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Funcio que farem servir per poder donar de alta usuaris en el nostre
     * sistema gestor de bases de dades
     *
     * @param evt Parametre que ens servira per poder activar la funcio ja sigui
     * utilitzant el ratoli o el teclat
     */
    private void botoAltaUsuariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoAltaUsuariActionPerformed
        System.out.println(this.inputNom.getText());
        boolean isNomValid = validarInputs(this.inputNom, this.etiquetaNom);
        boolean isCognomValid = validarInputs(this.inputCognom, this.etiquetaCognom);
        boolean isEdatValid = validarInputs(this.inputEdat, this.etiquetaEdat);

        boolean isCorreuValid = validarInputs(this.inputCorreu, this.etiquetaCorreu);

        if (isCorreuValid) {
            boolean correuValidat = validarEmail(this.inputCorreu);
            if (correuValidat) {
                System.out.println("El email es correcte");
            } else {
                // TODO: Quan el correu sigui invalid, hem de mostrar un 
                // missatge avisant al usuari en que ha fallat.
                System.out.println("El email es incorrecte");
            }
        }

        boolean isUsuariValid = validarInputs(this.inputUsuari, this.etiquetaUsuari);

        String password = tractarPassword(this.inputPassword);

        if (!password.isEmpty()) {
            boolean isPasswordValid = validarPassword(password);

            if (isPasswordValid) {
                System.out.println("La contrasenya es correcta");
            } else {
                System.out.println("Siusplau, introdueix una contraenya que estigui entre 8 caracters i 20 caracters");
            }

            if (isCognomValid && isNomValid && isEdatValid && isCorreuValid && isUsuariValid) {
                System.out.println("Tot esta correcte!");

                inicialitzarDades(this.inputNom.getText(), this.inputCognom.getText(), Integer.valueOf(this.inputEdat.getText()), this.inputCorreu.getText(), this.inputUsuari.getText(), this.inputPassword.getText());
            }
        } else {
            System.out.println("Esta buit");
        }
    }//GEN-LAST:event_botoAltaUsuariActionPerformed
    /**
     * Funcio desenvolupada per poder validar que un JTextField no pugui quedar
     * buit
     *
     * @param jtf Textfield que validarem per comprovar si esta buit o no
     * @param etiqueta Etiqueta que ens mostrara la informacio del JTextField
     * que ha quedat buit
     * @return Retornara fals si hi ha un JTextField buit i per tant mostrara un
     * missatge i retornara cert si el JTextField ha quedat emplenat.
     */
    private boolean validarInputs(JTextField jtf, JLabel etiqueta) {

        if (jtf.getText().isEmpty()) {
            //TODO: Faltara afegir un JOptionPane per avisar al usuari de que el input NO pot quedar buit
            System.out.println("No pot quedar buit -> " + etiqueta.getText());
            return false;
        }
        return true;
    }

    /**
     * Funcio que utilitzarem per inicialitzar els inputs amb un text
     * personalitzat per cada un de ells
     */
    private void inicialitzarTextInputs() {

        this.inputNom.setPlaceHolder("Introdueix el teu nom");
        this.inputNom.setText(this.inputNom.getPlaceHolder());

        this.inputCognom.setPlaceHolder("Introdueix el teu cognom");
        this.inputCognom.setText(this.inputCognom.getPlaceHolder());

        this.inputEdat.setPlaceHolder("Introdueix la teva edat");
        this.inputEdat.setText(this.inputEdat.getPlaceHolder());

        this.inputCorreu.setPlaceHolder("Introdueix el teu correu");
        this.inputCorreu.setText(this.inputCorreu.getPlaceHolder());

        this.inputUsuari.setPlaceHolder("Introdueix el nom de l'usuari");
        this.inputUsuari.setText(this.inputUsuari.getPlaceHolder());

        this.inputPassword.setPlaceHolder("Introdueix la contrasenya");
        this.inputPassword.setText(this.inputPassword.getPlaceHolder());
    }

    /**
     * Funcio que utilitzarem per poder validar el email utilitzant una
     * expressio regular per a que, d'aquesta manera el email compleixi unes
     * condicions
     *
     * @param email El email que farem servir per validar
     * @return Retornarem cert si el email compleix amb les condicions de la
     * expressio regular, si es el cas contrari retornarem fals
     */
    private boolean validarEmail(JTextField email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.getText().trim());
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * Funcio que farem servir per poder validar una contrasenya amb uns certs
     * parametres utilitzant expressions regulars per a que el usuari
     * introdueixi una contrasenya amb unes condicions
     *
     * @param password La contrasenya passant-la com a text pla.
     * @return Retorna cert si s'ha introduit una contrasenya que compleixi les
     * condicions establertes i si es el contrari, retornara fals i no
     * s'introduira la contrasenya correctament
     */
    private boolean validarPassword(String password) {
        //String expressioRegular = "^[a-zA-Z0-9._!?-]{8,20}$";
        String expressioRegular = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(expressioRegular);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * Funcio que farem servir per poder afegir una icona al boto de alta de
     * usuari per a que el usuari tingui una idea de el que fa aquell boto
     */
    private void afegirIcono() {
        ImageIcon iconoUsuari = new ImageIcon("src\\main\\java\\img\\altaUsuari.png");
        Image iconoUsuariModificar = iconoUsuari.getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH);
        ImageIcon iconoBuscar = new ImageIcon(iconoUsuariModificar);
        this.botoAltaUsuari.setIcon(iconoBuscar);
    }

    /**
     * Funcio creada per poder passar la contrasenya que s'introdueix dins de un
     * component JPasswordField a una cadena de text
     *
     * @param jpe Per parametres passarem el component que volem tractar
     * @return Retornarem la contrasenya com una cadena de text si la cadena ha
     * estat inicialitzada i el cas contrari si la cadena de text no ha estat
     * inicialitzada
     */
    private String tractarPassword(JPasswordField jpe) {
        String password = "";

        char[] arrayPassword = jpe.getPassword();

        for (char msg : arrayPassword) {
            password += msg;
        }
        if (!password.isEmpty()) {
            return password;
        }

        return "";
    }

    /**
     * Funcio que farem servir per poder inserir els nous usuaris al nostre
     * sistema gestor de bases de dades
     *
     * @param nom Nom de l'usuari propietari del compte
     * @param cognom Congom de l'usuari propietari del compte.
     * @param edat Edat de l'usuari propietari del compte.
     * @param correuUsuari Correu personal del usuari propietari del compte.
     * @param nomUsuari Nom usuari que fara servir per poder logejar-se dins de
     * l'aplicacio del xat.
     * @param password Contrasenya del usuari que fara servir per poder
     * logejar-se dins de l'aplicacio del xat.
     */
    private void inicialitzarDades(String nom, String cognom, int edat, String correuUsuari, String nomUsuari, String password) {
        amagarInfoWarnings();
        final String URLCONNEXIO = "mongodb://localhost:27017";

        MongoClientURI uri = new MongoClientURI(URLCONNEXIO);

        try ( MongoClient mongoClient = new MongoClient(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Cuentas");
            MongoCollection<Document> comptes = database.getCollection("comptes");

            //TODO: Haurem de verificar que poden haver mes de un usuari amb el meu mateix nom i per tant no farem el insert a la base de dades.
            Document nouUsuari = new Document("nomUsuari", nom)
                    .append("cognomUsuari", cognom)
                    .append("edatUsuari", edat)
                    .append("edatUsuari", edat)
                    .append("correuUsuari", correuUsuari)
                    .append("nomUser", nomUsuari)
                    .append("contrasenyaUsuari", password);

            comptes.insertOne(nouUsuari);
            System.out.println("Hem introduit un nou usuari...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void amagarInfoWarnings() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);
        mongoLogger.setUseParentHandlers(false);
    }

    private void inicialitzarServidor() {
        final String IP = "localhost";
        final int PORT = 12345;
        Servidor servidor = new Servidor(IP, PORT);
        
        servidor.iniciServidor(servidor.getIpServidor(), servidor.getPortServidor());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botoAltaUsuari;
    private javax.swing.JLabel etiquetaCognom;
    private javax.swing.JLabel etiquetaCorreu;
    private javax.swing.JLabel etiquetaEdat;
    private javax.swing.JLabel etiquetaNom;
    private javax.swing.JLabel etiquetaPassword;
    private javax.swing.JLabel etiquetaUsuari;
    private javax.swing.JPanel footerVista;
    private javax.swing.JPanel headerVista;
    private componentsPersonalitzats.JTextFieldPersonalitzat inputCognom;
    private componentsPersonalitzats.JTextFieldPersonalitzat inputCorreu;
    private componentsPersonalitzats.JTextFieldPersonalitzat inputEdat;
    private componentsPersonalitzats.JTextFieldPersonalitzat inputNom;
    private componentsPersonalitzats.JPasswordPlaceholder inputPassword;
    private componentsPersonalitzats.JTextFieldPersonalitzat inputUsuari;
    private javax.swing.JPanel mainVista;
    private javax.swing.JLabel titolRegistre;
    private javax.swing.JPanel vistaGeneral;
    // End of variables declaration//GEN-END:variables
}
