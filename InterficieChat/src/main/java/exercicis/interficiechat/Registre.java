package exercicis.interficiechat;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import encriptacio.Servidor;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        this.initComponents();
        this.inicialitzarTextInputs();
        this.afegirIcono();
        this.setExtendedState(MAXIMIZED_BOTH);
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
        java.awt.GridBagConstraints gridBagConstraints;

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
        inputCorreu = new componentsPersonalitzats.JTextFieldPersonalitzat();
        inputUsuari = new componentsPersonalitzats.JTextFieldPersonalitzat();
        inputPassword = new componentsPersonalitzats.JPasswordPlaceholder();
        inputEdat = new com.toedter.calendar.JDateChooser();
        menuGeneral = new javax.swing.JMenuBar();
        menuNavegacio = new javax.swing.JMenu();
        menuOpcioVistaPrincipal = new javax.swing.JMenuItem();
        menuOpcioLogin = new javax.swing.JMenuItem();
        menuSortir = new javax.swing.JMenu();
        menuOpcioSortir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registre");

        vistaGeneral.setLayout(new java.awt.BorderLayout());

        footerVista.setBackground(new java.awt.Color(203, 219, 242));
        footerVista.setToolTipText("Espai que l'usuari fara servir per poder donar-se de alta en ");

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
        headerVista.setToolTipText("Espai reservat per el titol de la interficie");

        titolRegistre.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        titolRegistre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titolRegistre.setText("Registre");
        titolRegistre.setToolTipText("Titol del registre en el nostre chat");
        headerVista.add(titolRegistre);

        vistaGeneral.add(headerVista, java.awt.BorderLayout.PAGE_START);

        mainVista.setBackground(new java.awt.Color(203, 219, 242));
        mainVista.setToolTipText("Espai de la interficie grafica on s'hi anotara les dades del usuari.");
        mainVista.setLayout(new java.awt.GridBagLayout());

        etiquetaNom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaNom.setText("Nom");
        etiquetaNom.setToolTipText("Titol del JTextField del nom per donar un context al usuari.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        mainVista.add(etiquetaNom, gridBagConstraints);

        etiquetaCognom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaCognom.setText("Cognoms");
        etiquetaCognom.setToolTipText("Titol del JTextField del cognom per donar un context al usuari.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        mainVista.add(etiquetaCognom, gridBagConstraints);

        etiquetaEdat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaEdat.setText("Edat");
        etiquetaEdat.setToolTipText("Titol del JDateChooser per donar un context al usuari.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        mainVista.add(etiquetaEdat, gridBagConstraints);

        etiquetaCorreu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaCorreu.setText("Correu");
        etiquetaCorreu.setToolTipText("Titol del JTextField del correu per donar un context al usuari.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        mainVista.add(etiquetaCorreu, gridBagConstraints);

        etiquetaUsuari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaUsuari.setText("Usuari");
        etiquetaUsuari.setToolTipText("Titol del JTextField del usuari per donar un context al usuari.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        mainVista.add(etiquetaUsuari, gridBagConstraints);

        etiquetaPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        etiquetaPassword.setText("Contrasenya");
        etiquetaPassword.setToolTipText("Titol del JTextField del password per donar un context al usuari.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        mainVista.add(etiquetaPassword, gridBagConstraints);

        inputNom.setToolTipText("Espai on s'anotara el nom real de l'usuari");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        mainVista.add(inputNom, gridBagConstraints);

        inputCognom.setToolTipText("Espai on anira anotat el cognom real del usuari.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        mainVista.add(inputCognom, gridBagConstraints);

        inputCorreu.setToolTipText("Correu de l'usuari que es vol registrar ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        mainVista.add(inputCorreu, gridBagConstraints);

        inputUsuari.setToolTipText("Nom de l'usuari que s'utilitzara per realitzar el inici de sessió.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        mainVista.add(inputUsuari, gridBagConstraints);

        inputPassword.setToolTipText("Contrasenya que s'utilitzara per iniciar sessio.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        mainVista.add(inputPassword, gridBagConstraints);

        inputEdat.setToolTipText("Calendari desplegable on s'indicara la data de naixement.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        mainVista.add(inputEdat, gridBagConstraints);

        vistaGeneral.add(mainVista, java.awt.BorderLayout.CENTER);

        menuGeneral.setBackground(new java.awt.Color(203, 219, 242));
        menuGeneral.setOpaque(true);

        menuNavegacio.setText("Navegació");

        menuOpcioVistaPrincipal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpcioVistaPrincipal.setBackground(new java.awt.Color(203, 219, 242));
        menuOpcioVistaPrincipal.setText("Vista Principal");
        menuOpcioVistaPrincipal.setOpaque(true);
        menuOpcioVistaPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpcioVistaPrincipalActionPerformed(evt);
            }
        });
        menuNavegacio.add(menuOpcioVistaPrincipal);

        menuOpcioLogin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpcioLogin.setBackground(new java.awt.Color(203, 219, 242));
        menuOpcioLogin.setText("Inici Sessió");
        menuOpcioLogin.setOpaque(true);
        menuOpcioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpcioLoginActionPerformed(evt);
            }
        });
        menuNavegacio.add(menuOpcioLogin);

        menuGeneral.add(menuNavegacio);

        menuSortir.setText("Sortir");

        menuOpcioSortir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpcioSortir.setBackground(new java.awt.Color(203, 219, 242));
        menuOpcioSortir.setText("Sortir");
        menuOpcioSortir.setOpaque(true);
        menuOpcioSortir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpcioSortirActionPerformed(evt);
            }
        });
        menuSortir.add(menuOpcioSortir);

        menuGeneral.add(menuSortir);

        setJMenuBar(menuGeneral);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
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
//        boolean isEdatValid = validarInputs(this.inputEdat, this.etiquetaEdat);

        boolean isCorreuValid = validarInputs(this.inputCorreu, this.etiquetaCorreu);

        if (isCorreuValid) {
            boolean correuValidat = validarEmail(this.inputCorreu);
            if (correuValidat) {
                System.out.println("El email es correcte");
            } else {
                /**
                 * TODO: Quan el correu sigui invalid, hem de mostrar un
                 * missatge avisant al usuari en que ha fallat.
                 */
                JOptionPane jop = new JOptionPane();
                Icon imagenLabel = new ImageIcon("src\\main\\java\\img\\passwordErroni.png");
                String[] opcions = {"Acceptar"};

                int correcte = jop.showOptionDialog(
                        null,
                        "El email es incorrecte.",
                        "Email incorrecte",
                        jop.DEFAULT_OPTION,
                        jop.WARNING_MESSAGE,
                        imagenLabel,
                        opcions,
                        opcions[0]
                );

                if (correcte > 0) {
                    System.out.println("S'ha pulsat aceptar");
                } else {
                    System.out.println("S'ha cancelat");
                }

                System.out.println("El email es incorrecte");
            }
        }

        boolean isUsuariValid = validarInputs(this.inputUsuari, this.etiquetaUsuari);

        String password = tractarPassword(this.inputPassword);
        String contrasenyaEncriptada = "";
        if (!password.isEmpty()) {
            boolean isPasswordValid = validarPassword(password);

            if (isPasswordValid) {
//                En aquest punt la contrasenya sera encriptada
                contrasenyaEncriptada = encriptarPassword(password);
            } else {
                System.out.println("Siusplau, introdueix una contraenya que estigui entre 8 caracters i 20 caracters");
            }

//            if (isCognomValid && isNomValid && isEdatValid && isCorreuValid && isUsuariValid) {
            int edat = tractarEdat();
            inicialitzarDades(this.inputNom.getText(), this.inputCognom.getText(), edat, this.inputCorreu.getText(), this.inputUsuari.getText(), contrasenyaEncriptada);
//            System.out.println("Hem introduit un nou usuari!");
//            }
        } else {
            System.out.println("Esta buit");
        }
    }//GEN-LAST:event_botoAltaUsuariActionPerformed
    /**
     * TODO: Falta afegir la documentacio JavaDoc.
     *
     * @param evt
     */
    private void menuOpcioVistaPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpcioVistaPrincipalActionPerformed
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        this.mostrarFinestra(vistaPrincipal);
        this.tancarFinestraActual();
    }//GEN-LAST:event_menuOpcioVistaPrincipalActionPerformed
    /**
     * TODO: Falta afegir la documentacio JavaDoc.
     *
     * @param evt
     */
    private void menuOpcioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpcioLoginActionPerformed
        Login login = new Login();
        this.mostrarFinestra(login);
        this.tancarFinestraActual();
    }//GEN-LAST:event_menuOpcioLoginActionPerformed
    /**
     * TODO: Falta afegir la documentacio JavaDoc.
     *
     * @param evt
     */
    private void menuOpcioSortirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpcioSortirActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuOpcioSortirActionPerformed
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
//        DavidBoix1234!
        final String DB_SRV_USR = "grup1";
        final String DB_SRV_PWD = "gat123";
        final String DB_URL = "57.129.5.24";
        final String DB_PORT = "27017";
        String URLCONNEXIO = "mongodb://" + DB_SRV_USR + ":" + DB_SRV_PWD + "@" + DB_URL + ":" + DB_PORT;

        MongoClientURI uri = new MongoClientURI(URLCONNEXIO);

        try ( MongoClient mongoClient = new MongoClient(uri)) {
            MongoDatabase database = mongoClient.getDatabase("grup1");
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
            System.out.println("uHem introduit un nou usuari...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio desenvolupada per amagar els missatges que apareixen en la consola
     * a causa de la connexio que realitzem al servidor de MongoDB
     *
     */
    private void amagarInfoWarnings() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);
        mongoLogger.setUseParentHandlers(false);
    }

    /**
     * Funcio obsoleta
     */
    private void inicialitzarServidor() {
        final String IP = "localhost";
        final int PORT = 12345;
        Servidor servidor = new Servidor(IP, PORT);
        servidor.iniciServidor(servidor.getIpServidor(), servidor.getPortServidor());
    }

    /**
     * Funcio que ens servira per poder emmagatzemar la contrasenya encriptada
     * utilitzant HASH del usuari dins del nostre sistema gestor de base de
     * dades.
     */
    private String encriptarPassword(String password) {
        final String IP = "localhost";
        final int PORT = 12345;
        try ( Socket cs = new Socket(IP, PORT)) {
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            DataInputStream dip = new DataInputStream(cs.getInputStream());

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecretKey clau = keyGen.generateKey();

            byte[] keyBytes = clau.getEncoded();
            out.writeInt(keyBytes.length);
            out.write(keyBytes);

            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, clau);
            byte[] msgEncriptat = aesCipher.doFinal(password.getBytes());

            out.writeInt(msgEncriptat.length);
            out.write(msgEncriptat);

            System.out.println("Missatge encriptat i clau enviats al servidor...");
            /**
             * A partir de aqui encriptem rebem la contrasenya encriptada i amb
             * el hash posat.
             */
            byte[] keyBytesServ = new byte[dip.readInt()];
            dip.readFully(keyBytesServ);
            SecretKey clauServidor = new SecretKeySpec(keyBytesServ, "AES");
            byte[] msgEncriptats = new byte[dip.readInt()];
            dip.readFully(msgEncriptats);
            Cipher aesCipher2 = Cipher.getInstance("AES");
            aesCipher2.init(Cipher.DECRYPT_MODE, clau);
            byte[] msgDesencriptat = aesCipher2.doFinal(msgEncriptats);
            String missatge = new String(msgDesencriptat);
            System.out.println("Aquesta es la contrasenya desencriptada amb el hash: " + missatge);
            return missatge;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * TODO: Revisar fitxer 'proves' per acabar de perfilar l'edat del usuari.
     *
     * @return Edat de l'usuari.
     */
    private int tractarEdat() {
        Date data = this.inputEdat.getDate();
        Calendar cal = Calendar.getInstance();
        Calendar dataActual = Calendar.getInstance();
        cal.setTime(data);
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int diaCalendari = cal.get(Calendar.DAY_OF_MONTH);
        int mesCalendari = cal.get(Calendar.MONTH);
        int anyCalendari = cal.get(Calendar.YEAR);
        int anyActual = dataActual.get(Calendar.YEAR);
        return anyActual - anyCalendari;
    }

    /**
     * TODO: Faltar afegir la documentacio JavaDoc.
     *
     *
     * @param jframe
     */
    private void mostrarFinestra(JFrame jframe) {
        jframe.setVisible(true);
        jframe.setExtendedState(MAXIMIZED_BOTH);
    }
    /**
     * TODO: Falta afegir documentacio JavaDoc.
     */
    private void tancarFinestraActual () {
        this.setVisible(false);
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
    private com.toedter.calendar.JDateChooser inputEdat;
    private componentsPersonalitzats.JTextFieldPersonalitzat inputNom;
    private componentsPersonalitzats.JPasswordPlaceholder inputPassword;
    private componentsPersonalitzats.JTextFieldPersonalitzat inputUsuari;
    private javax.swing.JPanel mainVista;
    private javax.swing.JMenuBar menuGeneral;
    private javax.swing.JMenu menuNavegacio;
    private javax.swing.JMenuItem menuOpcioLogin;
    private javax.swing.JMenuItem menuOpcioSortir;
    private javax.swing.JMenuItem menuOpcioVistaPrincipal;
    private javax.swing.JMenu menuSortir;
    private javax.swing.JLabel titolRegistre;
    private javax.swing.JPanel vistaGeneral;
    // End of variables declaration//GEN-END:variables
}
